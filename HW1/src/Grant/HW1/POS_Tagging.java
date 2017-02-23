
package Grant.HW1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.POSSample;


public class POS_Tagging {

	public static void main(String[] args) throws Exception {
		
		String fileName = "news article.txt";
		StringBuilder strFile = new StringBuilder();
		BufferedReader textReader = new BufferedReader(new FileReader(fileName));
		char[] buffer = new char[512];
		int num = 0;
		
		// Loading sentence detector and pos-tagger models
		InputStream detectStream = new FileInputStream("en-sent.bin");
		InputStream posStream = new FileInputStream("en-pos-maxent.bin");
		SentenceModel model_detect = new SentenceModel(detectStream);
		POSModel model_pos = new POSModel(posStream);
		
		// Instantiating the sentenceDetectorME and POSTaggerME classes
		SentenceDetectorME detector = new SentenceDetectorME(model_detect);
		POSTaggerME tagger = new POSTaggerME(model_pos);
		
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
		
		// Tokenize each sentence into words run pos-tagging on the tokens
		for(int i = 0; i < sentences.length; i++) {
			System.out.println();
			System.out.println("===Sentence " + (i + 1) + " ====");
			String tokens[] = whitespaceTokenizer.tokenize(sentences[i]);
			String[] tags = tagger.tag(tokens);
			POSSample sample = new POSSample(tokens, tags);
			System.out.println(sample.toString());
		}	
	}
}

