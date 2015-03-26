package net.blaklizt.symbiosis.sym_core.manager;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_proximity.ProximityScanner;
import net.blaklizt.symbiosis.sym_tts_engine.TextToSpeechEngine;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Symbiosis implements Runnable
{
	private static final Logger log4j = Logger.getLogger(Symbiosis.class.getSimpleName());

	private TextToSpeechEngine textToSpeechEngine;

	private ProximityScanner proximityScanner;

	private static Symbiosis symbiosis;

	public static Symbiosis getInstance()
	{
		return symbiosis;
	}

	private Symbiosis() { symbiosis = this; }

	public void run()
	{
		textToSpeechEngine.speak("Hello " + Configuration.getProperty("masterName")
			 + ", my name is " + Configuration.getProperty("coreManagerName") + "...");
		proximityScanner.start();
	}

    public static void main(String[] args)
    {
		try
		{
			new ClassPathXmlApplicationContext("sym_core-spring-context.xml");
			Symbiosis.getInstance();
			new Thread(Symbiosis.getInstance()).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }

	public void setTextToSpeechEngine(TextToSpeechEngine textToSpeechEngine) {
		log4j.debug("Assigning textToSpeechEngine to Symbiosis");
		this.textToSpeechEngine = textToSpeechEngine;
	}

	public void setProximityScanner(ProximityScanner proximityScanner) {
		log4j.debug("Assigning proximityScanner to Symbiosis");
		this.proximityScanner = proximityScanner;
	}

}
