import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class HangmanGame {
    private static final List<String> WORDS = new ArrayList<>();

    static {
        // Add a variety of unique words to the list
        WORDS.add("programming");
        WORDS.add("computer");
        WORDS.add("java");
        WORDS.add("hangman");
        WORDS.add("algorithm");
        WORDS.add("intelligence");
        WORDS.add("python");
        WORDS.add("javascript");
        WORDS.add("developer");
        WORDS.add("application");
        WORDS.add("database");
        WORDS.add("network");
        WORDS.add("keyboard");
        WORDS.add("monitor");
        WORDS.add("framework");
        WORDS.add("software");
        WORDS.add("guitar");
        WORDS.add("elephant");
        WORDS.add("beach");
        WORDS.add("vacation");
        WORDS.add("mountain");
        WORDS.add("ocean");
        WORDS.add("pizza");
        WORDS.add("painting");
        WORDS.add("telescope");
        WORDS.add("piano");
        WORDS.add("universe");
        WORDS.add("butterfly");
        WORDS.add("adventure");
        WORDS.add("chocolate");
        WORDS.add("sunshine");
        WORDS.add("guitarist");
        WORDS.add("elephant");
        WORDS.add("sunflower");
        WORDS.add("exploration");
        WORDS.add("moonlight");
        WORDS.add("waterfall");
        WORDS.add("sushi");
        WORDS.add("photography");
        WORDS.add("constellation");
        WORDS.add("starfish");
        WORDS.add("rainforest");
        WORDS.add("gallery");
        WORDS.add("anime");
        WORDS.add("pain");
        WORDS.add("demon");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String currentWord = getRandomWord();
        StringBuilder guessedWord = new StringBuilder();
		for (int i = 0; i < currentWord.length(); i++) {
    		guessedWord.append('_');
	}

        int remainingGuesses = 6;
        int clueCount = 3; // Maximum number of clues
        boolean usedAllClues = false;

        System.out.println("Welcome to Hangman!");
        System.out.println("You have 6 attempts to guess the word.");

        while (remainingGuesses > 0) {
            System.out.println("Current word: " + guessedWord);
            System.out.println("Remaining guesses: " + remainingGuesses);
            System.out.print("Enter a letter or type 'clue' for a clue: ");

            String input = scanner.next().toLowerCase();

            if (input.equals("clue")) {
                if (!usedAllClues && clueCount > 0) {
                    int randomIndex;
                    do {
                        randomIndex = random.nextInt(currentWord.length());
                    } while (guessedWord.charAt(randomIndex) != '_');
                    guessedWord.setCharAt(randomIndex, currentWord.charAt(randomIndex));
                    clueCount--;
                } else {
                    if (usedAllClues) {
                        System.out.println("You have used all your clues.");
                    } else {
                        usedAllClues = true;
                        System.out.println("You have used all your clues. No more clues available.");
                    }
                }
            } else if (input.length() == 1) {
                boolean isCorrectGuess = false;
                for (int i = 0; i < currentWord.length(); i++) {
                    if (currentWord.charAt(i) == input.charAt(0)) {
                        guessedWord.setCharAt(i, input.charAt(0));
                        isCorrectGuess = true;
                    }
                }

                if (!isCorrectGuess) {
                    remainingGuesses--;
                }

                if (guessedWord.toString().equals(currentWord)) {
                    System.out.println("Congratulations, you guessed the word: " + currentWord);
                    break;
                }
            } else {
                System.out.println("Please enter a single letter or 'clue'.");
            }
        }

        if (remainingGuesses == 0) {
            System.out.println("You run out of guesses. The word was: " + currentWord);
        }

        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();

        if (playAgain.equals("no")) {
            System.out.println("Thanks for playing!");
            scanner.close();
        } else if (playAgain.equals("yes")) {
            System.out.println();
            main(args);
        }
    }

    private static String getRandomWord() {
        if (WORDS.isEmpty()) {
            System.out.println("No more words available.");
            System.exit(0);
        }
        int index = new Random().nextInt(WORDS.size());
        String word = WORDS.remove(index);
        return word;
    }
}
