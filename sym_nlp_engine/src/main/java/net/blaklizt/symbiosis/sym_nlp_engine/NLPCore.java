package net.blaklizt.symbiosis.sym_nlp_engine;

import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 10/17/13
 * Time: 11:42 AM
 */
public class NLPCore
{
	NLPCore()
	{

	}

	String process(String inputSoundFile)
	{
		try
		{
			FileInputStream modelIn = new FileInputStream(inputSoundFile);

			try
			{
				ParserModel model = new ParserModel(modelIn);

				Parser parser = ParserFactory.create(model);

				

//				SentenceModel model = new SentenceModel(modelIn);
//
//				SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
//
//				String sentences[] = sentenceDetector.sentDetect("  First sentence. Second sentence. ");
//
//
//				TokenizerModel tokenizerModel = new TokenizerModel(modelIn);
//
//				Tokenizer tokenizer = new TokenizerME(tokenizerModel);
//
//				String tokens[] = tokenizer.tokenize(String.valueOf(sentences));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (modelIn != null)
				{
					try { modelIn.close(); }
					catch (IOException e) { }
				}
			}
		}
		catch (FileNotFoundException ex)
		{
			return null;
		}
		return null;
	}
}
