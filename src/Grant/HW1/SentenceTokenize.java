
package Grant.HW1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.WhitespaceTokenizer;


public class SentenceTokenize {

	public static void main(String[] args) throws Exception {
		
		String fileName = "news article.txt";
		StringBuilder strFile = new StringBuilder();
		BufferedReader textReader = new BufferedReader(new FileReader(fileName));
		char[] buffer = new char[512];
		int num = 0;
		
		// Loading sentence detector model
		InputStream inputStream = new FileInputStream("en-sent.bin");
		SentenceModel model = new SentenceModel(inputStream);
		
		// Instantiating the sentenceDetectorME class
		SentenceDetectorME detector = new SentenceDetectorME(model);
		
		// Instantiating the whitespaceTokenizer class
		WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
		
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
		inputStream.close();
		
		// Tokenize each sentence into words and print out each word
		for(int i = 0; i < sentences.length; i++) {
			System.out.println();
			System.out.println("===Sentence " + (i + 1) + " ====");
			String tokens[] = whitespaceTokenizer.tokenize(sentences[i]);
			for (String token : tokens) {
				System.out.println(token);
			}
		}
	}
}

