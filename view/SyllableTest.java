package view;

import java.util.ArrayList;

public class SyllableTest {
	
	public static void main() {

		 Syllable s = new Syllable();
		 ArrayList<String> split1 = s.word2syllables("carta");
		 System.out.println(split1);
	}

}
