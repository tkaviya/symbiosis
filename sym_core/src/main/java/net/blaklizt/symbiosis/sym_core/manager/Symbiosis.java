package net.blaklizt.symbiosis.sym_core.manager;

import net.blaklizt.symbiosis.sym_common.ConfigurationEngine;
import net.blaklizt.symbiosis.sym_proximity.ProximityScanner;
import net.blaklizt.symbiosis.sym_tts_engine.TextToSpeechEngine;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Symbiosis implements Runnable
{
	private static final Logger log4j = Logger.getLogger(Symbiosis.class.getSimpleName());

	private TextToSpeechEngine textToSpeechEngine;

	private ProximityScanner proximityScanner;

	private ConfigurationEngine configurationEngine;

	private static Symbiosis symbiosis;

	public static Symbiosis getInstance()
	{
		return symbiosis;
	}

	private Symbiosis() { symbiosis = this; }

//	public Symbiosis(ConfigurationEngine configurationEngine,
//		ProximityScanner proximityScanner, TextToSpeechEngine textToSpeechEngine)
//	{
//		this.configurationEngine = configurationEngine;
//		this.proximityScanner = proximityScanner;
//		this.textToSpeechEngine = textToSpeechEngine;
//	}

	public void run()
	{
		textToSpeechEngine.speak("Hello Master, my name is " + configurationEngine.getCoreManagerName() + "...");
		proximityScanner.start();
	}

    public static void main(String[] args)
    {
		try
		{
			new ClassPathXmlApplicationContext("spring-context.xml");
			new Thread(Symbiosis.getInstance()).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }

	public void setConfigurationEngine(ConfigurationEngine configurationEngine) {
		this.configurationEngine = configurationEngine;
	}

	public void setTextToSpeechEngine(TextToSpeechEngine textToSpeechEngine) {
		this.textToSpeechEngine = textToSpeechEngine;
	}

	public void setProximityScanner(ProximityScanner proximityScanner) {
		this.proximityScanner = proximityScanner;
	}

}
