package net.blaklizt.symbiosis.sym_sync.server.file;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/03/21
 * Time: 8:17 AM
 */
public class SymSyncFile implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String filePath;
	private String fileChecksum;
	private byte[] fileData;
	private long size;

	public SymSyncFile(String fileName, String filePath, long size, String fileChecksum)
	{
		this.fileName = fileName;
		this.filePath = filePath;
		this.size = size;
		this.fileChecksum = fileChecksum;
	}

	public SymSyncFile(String fileName, String filePath, long size, String fileChecksum, byte[] fileData)
	{
		this(fileName, filePath, size, fileChecksum);
		this.fileData = fileData;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getFileChecksum()
	{
		return fileChecksum;
	}

	public void setFileChecksum(String fileMD5Hash)
	{
		this.fileChecksum = fileMD5Hash;
	}

	public byte[] getFileData()
	{
		return fileData;
	}

	public void setFileData(byte[] fileData)
	{
		this.fileData = fileData;
	}

	public long getSize()
	{
		return size;
	}

	public void setSize(long size)
	{
		this.size = size;
	}

	public String toBase64String()
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream( baos );
			oos.writeObject(this);
			oos.close();
			return Base64.encodeBase64String(baos.toByteArray());
		}
		catch (Exception ex) {
			return null;
		}
	}
}
