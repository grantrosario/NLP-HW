
package Grant.HW1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import opennlp.tools.sentdetect.*;
import opennlp.tools.tokenize.*;
import opennlp.tools.namefind.*;
import opennlp.tools.util.*;


public class NameFinder {

	public static void main(String[] args) throws Exception {
		
		String fileName = "news article.txt";
		StringBuilder strFile = new StringBuilder();
		BufferedReader textReader = new BufferedReader(new FileReader(fileName));
		char[] buffer = new char[512];
		int num = 0;
		
		// Loading sentence detector, name finder, and location finder models
		InputStream detectStream = new FileInputStream("en-sent.bin");
		InputStream nameStream = new FileInputStream("en-ner-person.bin");
		InputStream locStream = new FileInputStream("en-ner-location.bin");
		SentenceModel model_detect = new SentenceModel(detectStream);
		TokenNameFinderModel model_name = new TokenNameFinderModel(nameStream);
		TokenNameFinderModel model_loc = new TokenNameFinderModel(locStream);
		
		// Instantiating the sentenceDetectorME class
		SentenceDetectorME detector = new SentenceDetectorME(model_detect);
		
		// Instantiating the whitespaceTokenizer class
		WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
		
		// Instantiating the NameFinderME class
		NameFinderME nameFinder = new NameFinderME(model_name);
		NameFinderME locFinder = new NameFinderME(model_loc);
		
		// Append each line of news article to strFile
		while((num = textReader.read(buffer)) != -1) {
				String current = String.valueOf(buffer, 0, num);
				strFile.append(current);
				buffer = new char[512];
		}
		
		// Detecting the sentences
		String sentences[] = detector.sentDetect(strFile.toString());
		
		
		// Close BufferedReader
		textReader.close();
		detectStream.close();
		nameStream.close();
		locStream.close();
		
		// Tokenize each sentence into words and print out each word
		for(int i = 0; i < sentences.length; i++) {
			System.out.println();
			System.out.println("===Sentence " + (i + 1) + " ====");
			
			String tokens[] = whitespaceTokenizer.tokenize(sentences[i]);
			
			Span nameSpans[] = nameFinder.find(tokens);
			if (nameSpans.length == 0) System.out.println("No names in this sentence");
			
			Span locSpans[] = locFinder.find(tokens);
			if (locSpans.length == 0) System.out.println("No locations in this sentence");
			
			for (Span name : nameSpans) {
				System.out.println(name.toString());
			}
			
			for (Span loc : locSpans) {
				System.out.println(loc.toString());
			}
			
		}
	}
}

