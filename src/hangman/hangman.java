package hangman;

import java.util.Scanner;

public class hangman {
    private static final String[] ANSWERS = {"chicken", "cow", "bird", "fish", "dog", "cat", "snake", "zebra", "kangaroo"};
    private static final int MAX = 6;
    private static char[][] map = {
            {'_', '_', '_', '_', ' '},
            {'|', ' ', ' ', '|', ' '},
            {'|', ' ', ' ', ' ', ' '},
            {'|', ' ', ' ', ' ', ' '},
            {'|', ' ', ' ', ' ', ' '},
            {'|', ' ', ' ', ' ', ' '},
            {'|', '_', '_', '_', '_'}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = ANSWERS[(int) (Math.random() * ANSWERS.length)];
        
        char[] randomWord = new char[word.length()];
        for(int i = 0; i < randomWord.length; i++) {
            randomWord[i] = '-';
        }
        
        int tries = 0;

        System.out.println("Welcome to Hangman! Feel free to try guessing the word! (Psss its an animal)");

        while(tries < MAX) {
            System.out.print("Current word: ");
            for(char a : randomWord) {
                System.out.print(a + " ");
            }
            
            System.out.println();

            System.out.print("Enter a letter: ");
            char guess = sc.next().charAt(0);

            boolean guessCorrection = false;
            for(int i = 0; i < word.length(); i++) {
                if(word.charAt(i) == guess) {
                    randomWord[i] = guess;
                    guessCorrection = true;
                }
            }

            if(!guessCorrection) {
                tries++;
                updateMap(tries);
                System.out.println("Incorrect guess! You have " + (MAX - tries) + " tries left.");
            } 
            else {
                System.out.println("Correct guess!");
            }

            if(String.valueOf(randomWord).equals(word)) {
                System.out.println("Congratulations, you guessed the word and still has " + (MAX - tries) + " chances(s) remaining.");
                System.out.println("The word was " + word + ".");
                break;
            }
        }

        if(tries == MAX) {
            System.out.println("You failed to guess the word. The word was " + word + ".");
        }
    }

    private static void updateMap(int tries) {
        switch (tries){
            case 1:
                map[2][3] = 'O';
                break;
            case 2:
                map[3][3] = '|';
                break;
            case 3:
                map[3][2] = '/';
                break;
            case 4:
                map[3][4] = '\\';
                break;
            case 5:
                map[4][2] = '/';
                break;
            case 6:
                map[4][4] = '\\';
                break;
        }
        
        for(char[] row : map) {
            for(char a : row) {
                System.out.print(a);
            }
            System.out.println();
        }
    }
}
