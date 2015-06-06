package net.blaklizt.symbiosis.sym_tts_engine;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: tsungai1
 * Date: 2013/04/05
 * Time: 9:27 AM
 */
@Service
public class FestivalEngine implements TextToSpeechEngine {

	public void speak(String text)
    {
        System.out.println(text);
    }
}
