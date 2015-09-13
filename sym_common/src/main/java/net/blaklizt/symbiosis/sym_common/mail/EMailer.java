package net.blaklizt.symbiosis.sym_common.mail;

import net.blaklizt.symbiosis.sym_common.configuration.ThreadPoolManager;
import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;

public class EMailer implements Runnable{

	String host="null";
	String recipients[];
	String subject;
	String message;
	String from;
	String contentType="text/plain";
	String imageLocation;
	String contentID;
	boolean isMultipart=false;
	String attachmentfilenames[]=null;
	MimeMultipart multipart;
	Logger log = Logger.getLogger(this.getClass());
	
    public EMailer(String recipients[], String subject, String message , String from,String host,String contentType) 
    {
    	this.recipients=recipients;
    	this.subject=subject;
    	this.message=message;
    	this.from=from;
    	this.host=host;
    	this.contentType=contentType;
    }

    /**
     * This constructor creates a HTML email with a logo and attachments
     * @param recipients
     * @param subject
     * @param message
     * @param from
     * @param host
     * @param contentType
     * @param imageLocation
     * @param contentID
     * @param attachmentfilenames
     */
    public EMailer(String recipients[], String subject, String message , String from,String host,String contentType, String imageLocation, String contentID,String attachmentfilenames[]) 
    {
    	this.recipients=recipients;
    	this.subject=subject;
    	this.message=message;
    	this.from=from;
    	this.host=host;
    	this.contentType=contentType;
    	this.imageLocation=imageLocation;
    	this.contentID=contentID;
    	this.attachmentfilenames=attachmentfilenames;
    	isMultipart=true;
    }
    
    /**
     * Creates HTML email with logo but no attachment
     * @param recipients
     * @param subject
     * @param message
     * @param from
     * @param host
     * @param contentType
     * @param imageLocation
     * @param contentID
     */
    public EMailer(String recipients[], String subject, String message , String from,String host,String contentType, String imageLocation, String contentID) 
    {
    	this.recipients=recipients;
    	this.subject=subject;
    	this.message=message;
    	this.from=from;
    	this.host=host;
    	this.contentType=contentType;
    	this.imageLocation=imageLocation;
    	this.contentID=contentID;
    	isMultipart=true;
    }
    
    public EMailer(String recipients[], String subject,String from,String host,MimeMultipart multipart) 
    {
    	this.recipients=recipients;
    	this.subject=subject;
    	this.from=from;
    	this.host=host;
    	this.multipart=multipart;
    	isMultipart=true;
    }
    
    public EMailer(String recipients[], String subject, String message ,String contentType) 
    {
		//get alarm email address
		String from = CommonUtilities.getConfiguration("submitter");
		String host = CommonUtilities.getConfiguration("mail.smtp.host");
		
    	this.recipients=recipients;
    	this.subject=subject;
    	this.message=message;
    	this.from=from;
    	this.host=host;
    	this.contentType=contentType;
    }

	//Send the email
	public void run() 
	{
		try
		{
		    //Set the host smtp address
		    Properties props = CommonUtilities.resourceBundleToProperties(ResourceBundle.getBundle("properties/mail"));

		    // create some properties and get the default Session
		    Authenticator auth = new PopupAuthenticator(
					props.getProperty("mail.smtp.submitter"),
					props.getProperty("mail.smtp.submitter.password"));

		    Session session = Session.getInstance(props,auth);
		
		    // create a message
		    //Session session = Session.getDefaultInstance(props, null);
		    Message msg = new MimeMessage(session);
		
		    // set the from and to address
		    InternetAddress addressFrom = new InternetAddress(from);
		    msg.setFrom(addressFrom);
		
		    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
		    for (int i = 0; i < recipients.length; i++)
		    {
		        addressTo[i] = new InternetAddress(recipients[i]);
		        addressTo[i].validate();
		    }
		    
		    String addresses = "{";
		    for (int c = 0; c < addressTo.length; c++)
		    {
		    	addresses += addressTo[c].getAddress();
		    	if (c + 1 != addressTo.length)
		    		addresses += ',';
		    }
		    addresses += "}";
		    
			log.info("Sending email with subject: " + subject + " to addresses " + addresses + " using host: " + host);

		    msg.setRecipients(Message.RecipientType.TO, addressTo);
		    msg.setSubject(subject);
		    
			if(isMultipart)
				if(multipart==null)
					msg=setMultiPartMessage(msg);
				else
					msg.setContent(multipart);
			else
				msg.setContent(message, contentType);
			
		    Transport.send(msg);

			String allRecipients = "";
			for (String recipient : recipients)
			{
				allRecipients += recipient + ",";
			}

		    log.info("Email with subject: " + subject + " sent to: {" +
					 allRecipients.substring(0,allRecipients.length() - 1) + "}");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("An error occurred sending mail. Message: " + ex.getMessage());
		}
	} 
    
	public Message setMultiPartMessage(Message msg)
	{
		try
		{
	        // This HTML mail has to 2 parts, the BODY and the embedded image
	        MimeMultipart multipart = new MimeMultipart("related");

	        // First add the html part
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message,contentType);
	        multipart.addBodyPart(messageBodyPart);
	        
	        // Add the image part
	        messageBodyPart = new MimeBodyPart();
	        DataSource fds = new FileDataSource(imageLocation);
	        messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID",contentID);
	        multipart.addBodyPart(messageBodyPart);
	        
	        if(attachmentfilenames!=null)
	        for(int j=0;j<attachmentfilenames.length;j++)
	        {
	        	 messageBodyPart = new MimeBodyPart();
	        	 DataSource source =  new FileDataSource(attachmentfilenames[j]);
	        	 messageBodyPart.setDataHandler(new DataHandler(source));
	        	 String filename=attachmentfilenames[j].substring(attachmentfilenames[j].lastIndexOf(File.separatorChar)+1);
	        	 messageBodyPart.setFileName(filename);
	        	 multipart.addBodyPart(messageBodyPart);
	        }
	        
		    msg.setContent(multipart);
		    return msg;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return msg;
		}
	}
	
	public static MimeMultipart createMultipartMessage(byte[] attachment, String message, String fileName, String attachmentFileType) 
	{
		try 
		{
			MimeMultipart multipart = new MimeMultipart("related");

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(message, "text/html");
			multipart.addBodyPart(messageBodyPart);

			
			//Add attachment 
			messageBodyPart = new MimeBodyPart(); 
			DataSource att = new javax.mail.util.ByteArrayDataSource(attachment,attachmentFileType);
			messageBodyPart.setDataHandler(new DataHandler(att));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);
			 
			 
			// Add the image part 
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource("blaklizt.png");
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID","blakliztlogo");
			multipart.addBodyPart(messageBodyPart);
			
			return multipart;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	
	class PopupAuthenticator extends Authenticator
	{
		String username;
		String password;

		public PopupAuthenticator(String username,String password)
		{
			this.username = username;
			this.password = password;
		}

		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(username, password);
		}
	}
	
    //test main
    public static void main(String[] args)
    {
	    ThreadPoolManager.schedule(
		    new EMailer(new String []{"bhpenetrator@gmail.com"},"Street password.","Testing", "blaklizt@gmail.com","smtp.gmail.com","text/html"));
    }
}
