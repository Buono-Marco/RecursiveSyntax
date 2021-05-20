package com.marcobuono.recursivesyntax;
/*
 * Below is an output of my program:
 * 
 * 1.  Spider Man believes that Richard Nixon is big.
 * 
 * 2.  Tolkien looks for all elegant bald polite polite alien but
 *     last elephant finds a fish who hates Spider Man.
 *     
 * 3.  Spider Man believes that all elephant believes that Miss America believes
 *     that Spider Man knows some alien and few elephant who talks
 *     finds every man who believes that last elephant is huge.
 *     
 * 4.  Fred walks.
 * 
 * 5.  other woman who believes that a polite happy pretty alien is
 *     big knows Captain Kirk or Tolkien is huge and last unicorn
 *     flies nor Spider Man listens.
 *     
 * 6.  Spider Man listens.
 * 
 * 7.  every unicorn believes that every dog who is happy flies.
 * 
 * 8.  every man listens and last fish who is polite believes that a unicorn is pretty.
 * 
 * 9.  Spider Man finds your unicorn.
 * 
 * 10. Captain Kirk is huge so last happy angry bald man who talks believes that
 *     other dog who sleeps plays other dog.
 *     
 */


/**
 * 
 * The program implements the Lab 3/Part 1 rules to generate random sentences.
 *
 */
public class RandomSentences {
	
	// Everything need to build sentences
	static final String[] conjunction = {"and", "or", "but", "because", "for", "nor", "yet", "so"};
	static final String[] proper_noun = {"Fred", "Jane", "Richard Nixon", "Miss America", "Spider Man", "Doctor Who", "Captain Kirk", "Tolkien"};
	static final String[] common_noun = {"man", "woman", "fish", "elephant", "unicorn", "dog", "cat", "alien"};
	static final String[] determiner = {"a", "the", "every", "some", "all", "last", "other", "few", "last", "your"};
	static final String[] adjective = {"big", "tiny", "pretty", "bald", "huge", "happy", "quiet", "angry", "polite", "elegant"};
	static final String[] intransitive_verb = { "runs", "jumps", "talks", "sleeps", "dances", "flies", "walks", "listens"};
	static final String[] transitive_verb = { "loves", "hates", "sees", "knows", "looks for", "finds", "plays", "teaches"};
	
	public static void main(String[] args) {
		int i = 0;
		while (i < 10) { // produce only 10 sentences
			sentence();
			System.out.println(".\n\n");
			i++;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Oops, something went wrong: " + e.getMessage());
			}
		}
	}
	
	/**
	 * The method, given an array, returns an element randomly
	 * @param listOfStrings
	 * @return a casual element of the array
	 */
	public static String randomItem(String[] listOfStrings) {
		int i = (int)(Math.random() * listOfStrings.length);
		return listOfStrings[i];
	}
	
	/**
	 * The function call the simpleSentece() method to
	 * produce a sentence. There is some probability
	 * that add a conjunction and recalls itself.
	 */
	public static void sentence() {
		System.out.print(simpleSentence());
		if (Math.random() > 0.7) {
			System.out.print(" " + randomItem(conjunction) + " ");
			sentence();
		}
	}
	
	/**
	 * The method produce a simple sentence calling
	 * other two method, nounPhrase() and verbPhrase()
	 * @return the simple sentence
	 */
	public static String simpleSentence() {
		String simpleSentence = nounPhrase() + " " + verbPhrase();
		return simpleSentence;
	}
	
	/**
	 * The method return a noun, proper or common, and
	 * can add a verb so to produce another sentence
	 * 
	 * @return the noun or the noun plus a verb
	 */
	public static String nounPhrase() {
		String nounPhrase = "";
		// It is more likely to go to the second branch
		if (Math.random() > 0.7) { 
			nounPhrase += randomItem(proper_noun);
		} else {
			nounPhrase += randomItem(determiner);
			double i = Math.random();
			while (i > 0.7) { // The chances of adding more adjectives are very low
				nounPhrase += " " + randomItem(adjective);
				i = Math.random();
			}
			nounPhrase += " " + randomItem(common_noun);
			if (Math.random() > 0.6) { // Here the odds increase slightly
				nounPhrase += " who " + verbPhrase();
			}
		}
		return nounPhrase;
	}

	/**
	 * The method randomly return a verb, intransitive or
	 * transitive, or the 'is' verb and an adjective, or
	 * a new simple sentence
	 * 
	 * @return a verb or another simple sentence
	 */
	public static String verbPhrase() {
		String verbPhrase = "";
		 int randomChoice = (int)(1 + Math.random() * 4);
		 switch (randomChoice) {
		 	case 1:
		 		verbPhrase += randomItem(intransitive_verb);
		 		break;
		 	case 2:
		 		verbPhrase += randomItem(transitive_verb) + " " + nounPhrase();
		 		break;
		 	case 3:
		 		verbPhrase += "is " + randomItem(adjective);
		 		break;
		 	case 4:
		 		verbPhrase += "believes that " + simpleSentence();
		 		break;
		 }
		return verbPhrase;
	}
}
