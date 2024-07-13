import java.util.Random;
import java.util.Scanner;

public class Number_Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        
        int playAgain;
        do {
            int numberToGuess = random.nextInt(100) + 1; 
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            while (!guessedCorrectly) {
                System.out.print("Enter your guess (1-100): ");
                int guess = scanner.nextInt();
                attempts++;
                
                if (guess < numberToGuess) {
                    System.out.println("Too low. Try again.");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high. Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the number!");
                    System.out.println("It took you " + attempts + " attempt(s) to guess.");
                    guessedCorrectly = true;
                }
            }
            
            System.out.print("Do you want to play again? (1 for yes, 0 for no): ");
            playAgain = scanner.nextInt();
            
        } while (playAgain == 1);
        
        System.out.println("Thank you for playing the Number Guessing Game!");
        scanner.close();
    }
}
