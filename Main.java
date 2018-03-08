import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        int choice = -1;

        try {
            User.loadUsers();
        } catch (FileNotFoundException exception) {
            System.out.println("No one user found. Please registrate first user.");
        }

        while (choice != 0) {
            System.out.println("\nChoose what you want to do:");
            System.out.println("1 - log in");
            System.out.println("2 - sign up");
            System.out.println("0 - exit program");
            System.out.print("Your choice: ");
            choice = Choice.getChoice(2);

            if (choice == 1) {
                try {
                    User.logIn();
                } catch (IOException exception) {
                    System.out.println("Wrong username or password.");
                }
            }

            if (choice == 2) {
                try {
                    User.signUp();
                } catch (IOException exception) {
                    System.out.println("This login already exists! Registration aborted.");
                }
            }

        }
    }
}