package Grant.HW1;

import opennlp.tools.tokenize.WhitespaceTokenizer;
public class SentenceTokenize {

	public static void main(String[] args) {
		
		String sentence = "This is a certain sentence containing some words and what not according to my say so.";
		
		WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
		
		String tokens[] = whitespaceTokenizer.tokenize(sentence);
		
		for(String token : tokens) {
			System.out.println(token);
		}

	}

}
