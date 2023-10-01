import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int lowerLimit = 1;
        int upperLimit = 100;
        int maxAttempts = 10;
        int roundsWon = 0;
        int totalAttempts = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Guess the Number game!");

        while (playAgain) {
            int secretNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("I'm thinking of a number between " + lowerLimit + " and " + upperLimit + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You guessed the number (" + secretNumber + ") in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    roundsWon++;
                    totalAttempts += attempts;
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + secretNumber + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            if (!playAgainInput.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing! You won " + roundsWon + " round(s) with an average of " +
                (roundsWon > 0 ? (double) totalAttempts / roundsWon : 0) + " attempts per round.");
    }
}


