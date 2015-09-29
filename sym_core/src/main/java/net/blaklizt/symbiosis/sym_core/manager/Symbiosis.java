package net.blaklizt.symbiosis.sym_core.manager;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.configuration.ThreadPoolManager;
import net.blaklizt.symbiosis.sym_proximity.ProximityScanner;
import net.blaklizt.symbiosis.sym_tts_engine.TextToSpeechEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Symbiosis implements Runnable
{
	private static final Logger logger = Logger.getLogger(Symbiosis.class.getSimpleName());

	@Autowired private TextToSpeechEngine textToSpeechEngine;

	@Autowired private ProximityScanner proximityScanner;

	private static Symbiosis symbiosis;

	public static Symbiosis getInstance()
	{
		if (symbiosis == null)
			symbiosis = new Symbiosis();
		return symbiosis;
	}

	private Symbiosis()
	{
		symbiosis = this;
		ThreadPoolManager.schedule(Symbiosis.getInstance());
	}

	public void run()
	{
		textToSpeechEngine.speak("Hello " + Configuration.getSymbiosisProperty("masterName")
			 + ", my name is " + Configuration.getSymbiosisProperty("coreManagerName") + "...");
		proximityScanner.start();
	}

    public static void main(String[] args)
    {
		try
		{
			new ClassPathXmlApplicationContext("sym_core-spring-context.xml");
			Symbiosis.getInstance();
			ThreadPoolManager.schedule(Symbiosis.getInstance());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }

	public void setTextToSpeechEngine(TextToSpeechEngine textToSpeechEngine) {
		logger.fine("Assigning textToSpeechEngine to Symbiosis");
		this.textToSpeechEngine = textToSpeechEngine;
	}

	public void setProximityScanner(ProximityScanner proximityScanner) {
		logger.fine("Assigning proximityScanner to Symbiosis");
		this.proximityScanner = proximityScanner;
	}

}
