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
		
		String sentence = "This is a certain sentence containing some words and what not according to my say so.";
		String fileName = "news article.txt";
		StringBuilder strFile = new StringBuilder();
		BufferedReader textReader = new BufferedReader(new FileReader(fileName));
		char[] buffer = new char[512];
		int num = 0;
		
		WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
		
		String tokens[] = whitespaceTokenizer.tokenize(sentence);
		
		for(String token : tokens) {
			System.out.println(token);
		}

	}

}
