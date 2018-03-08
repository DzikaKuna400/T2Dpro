import java.util.Scanner;

public class Choice {
    public static int getChoice(int maxInput){
        boolean isCorrect = false;
        int choice = -1;

        while(!isCorrect)
        {
            Scanner read = new Scanner(System.in);
            String choiseString = read.nextLine();
            try{
                choice = Integer.parseInt(choiseString);
            }
            catch (NumberFormatException e){
                System.out.println("Your choice has to be a number.");
            }

            if(choice<0 || choice >maxInput) System.out.println("Choose one of the options above.");
            else isCorrect = true;

        }
        return choice;
    }
}
