package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Request the length of the secret code from the user
        int numberOfDigits = requestSecretCodeLength(scanner);

        // Request the number of symbols from the user
        int numberOfSymbols = requestNumberOfSymbols(scanner);

        // Check if the code length exceeds the number of unique symbols
        if (numberOfDigits > numberOfSymbols) {
            System.out.println("Error: it's not possible to generate a code with a length of " + numberOfDigits +
                    " with " + numberOfSymbols + " unique symbols.");
            System.exit(0);
        }

        // Generate a secret code with the requested length
        String secretNumber = generateSecretCode(numberOfDigits, numberOfSymbols);

        System.out.print("The secret is prepared: ");
        for (int i = 0; i < numberOfDigits; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-" + (numberOfSymbols <= 10 ? (char) ('0' + numberOfSymbols - 1) : '9') +
                (numberOfSymbols > 10 ? ", a-" + (char) ('a' + numberOfSymbols - 11) : "") + ").\n");

        System.out.println("Okay, let's start a game!");

        // Start the game
        playGame(scanner, secretNumber);
    }

    // Method to request the length of the secret code from the user
    public static int requestSecretCodeLength(Scanner scanner) {
        System.out.println("Please, enter the secret code's length:");
        String input = scanner.nextLine();

        if (!isNumeric(input)) {
            System.out.println("Error: \"" + input + "\" isn't a valid number.");
            System.exit(0);
        }

        int numberOfDigits = Integer.parseInt(input);

        // Check for negative or zero length
        if (numberOfDigits <= 0) {
            System.out.println("Error: the length of the secret code must be a positive number.");
            System.exit(0);
        }

        // Check if the code length exceeds the allowable limit
        if (numberOfDigits > 36) {
            System.out.println("Error: can't generate a secret number with a length of " + numberOfDigits +
                    " because there aren't enough unique symbols.");
            System.exit(0);
        }

        return numberOfDigits;
    }

    // Method to request the number of symbols from the user
    public static int requestNumberOfSymbols(Scanner scanner) {
        System.out.println("Input the number of possible symbols in the code:");
        String input = scanner.nextLine();

        if (!isNumeric(input)) {
            System.out.println("Error: \"" + input + "\" isn't a valid number.");
            System.exit(0);
        }

        int numberOfSymbols = Integer.parseInt(input);

        // Check for negative or zero number of symbols
        if (numberOfSymbols <= 0) {
            System.out.println("Error: the number of possible symbols must be a positive number.");
            System.exit(0);
        }

        // Check if the number of symbols exceeds the allowable limit
        if (numberOfSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }

        return numberOfSymbols;
    }

    // Helper method to check if a string is a valid integer
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Method to generate a secret code with unique digits
    public static String generateSecretCode(int numberOfDigits, int numberOfSymbols) {
        Random random = new Random(System.nanoTime());
        StringBuilder secretNumber = new StringBuilder();
        String usableCharacters = ALPHABET.substring(0, numberOfSymbols);

        while (secretNumber.length() < numberOfDigits) {
            char randomChar = usableCharacters.charAt(random.nextInt(numberOfSymbols));

            if (secretNumber.indexOf(String.valueOf(randomChar)) == -1) {
                secretNumber.append(randomChar);
            }
        }

        return secretNumber.toString();
    }

    // Method to conduct the game
    public static void playGame(Scanner scanner, String secretNumber) {
        int turnNumber = 1;

        while (true) {
            System.out.println("Turn " + turnNumber + ":");
            String userGuess = scanner.nextLine();
            int[] result = evaluateGuess(secretNumber, userGuess);
            int bulls = result[0];
            int cows = result[1];

            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");

            if (bulls == secretNumber.length()) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            turnNumber++;
        }
    }

    // Method to evaluate the user's guess (determine the number of bulls and cows)
    public static int[] evaluateGuess(String secretNumber, String userGuess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < secretNumber.length(); i++) {
            char secretChar = secretNumber.charAt(i);
            char userChar = userGuess.charAt(i);

            if (secretChar == userChar) {
                bulls++;
            } else if (userGuess.contains(Character.toString(secretChar))) {
                cows++;
            }
        }

        return new int[] {bulls, cows};
    }
}

