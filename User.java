import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class User {

    private String name;
    private String surname;
    private String login;
    private int password;

    private Calendar calendar = new Calendar();

    public User(String name, String surname, String login, int password){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public static HashSet<User> users = new HashSet<>();
    private static String fileName = "users.txt";

    public static void loadUsers() throws FileNotFoundException {
            File usersFile = new File(fileName);
            Scanner read = new Scanner(usersFile);

            String readName;
            String readSurname;
            String readLogin;
            int readPassword;
            while(read.hasNextLine()){
                readName = read.nextLine();
                readSurname = read.nextLine();
                readLogin = read.nextLine();
                readPassword = Integer.parseInt(read.nextLine());
                users.add(new User(readName, readSurname, readLogin, readPassword));
            }
            read.close();
    }

    public static void signUp() throws IOException{
        Scanner read = new Scanner(System.in);

        System.out.println("\nPlease fill in fields below:\n");

        String[] input = new String[4];
        String[] fields = new String[4];

        fields[0]="Name";
        fields[1]="Surname";
        fields[2]="Login";
        fields[3]="Password";

        for (int i=0; i<4; i++){
            System.out.println(fields[i]+": ");
            input[i] = read.nextLine();
        }

        boolean loginConflict = false;
        for (User user : users){
            if (input[2].equals(user.login)) loginConflict = true;
        }
        if(loginConflict) throw new IOException();
        else {
            users.add(new User(input[0], input[1], input[2], input[3].hashCode()));

            Writer write = new FileWriter(fileName, true);
            write.write(input[0] + "\n");
            write.write(input[1] + "\n");
            write.write(input[2] + "\n");
            write.write(Integer.toString(input[3].hashCode()) + "\n");
            write.close();
            System.out.println("\nRegistration completed! Now you can log in.");
        }
    }

    public static void logIn() throws IOException {
        Scanner read = new Scanner(System.in);
        System.out.println("Your login: ");
        String login = read.nextLine();
        User me = null;
        for (User user : users){
            if (login.equals(user.login))
            me = user;
        }
        if(me == null) throw new IOException();

        System.out.println("Your password: ");
        String password = read.nextLine();
        int pwd = password.hashCode();
        if (pwd != me.password) throw new IOException();
        else
            me.userInterface();
    }

    private void userInterface() {
        int choice = -1;
        calendar.setCalendarPath(login+".txt");
        try{
            calendar.loadMyCalendar();
        } catch (FileNotFoundException exception) {
            System.out.println("\n>>> You don't have any tasks <<< ;)\n");
        }
        while (choice != 0) {
            System.out.println("\nWhat do you want to do now?");
            System.out.println("1) Show my thigs2do");
            System.out.println("2) Add new task");
            System.out.println("0) Back");
            System.out.print("Your choice: ");
            choice = Choice.getChoice(2);

            if (choice == 1) {
                calendar.showMyTasks();
            }

            if (choice == 2) {
                int choice1 = -1;
                while (choice1 != 0) {
                    System.out.println("\nWhat kind of task do you want to add?");
                    System.out.println("1) Add new request");
                    System.out.println("2) Add new outgoing");
                    System.out.println("3) Add new meeting");
                    System.out.println("4) Add new trip");
                    System.out.println("0) Return to previous menu");
                    System.out.print("Your choice: ");
                    choice1 = Choice.getChoice(4);
                    switch (choice1) {
                        case 1:
                            try {
                                calendar.addNewRequest();
                            } catch (IOException exception){
                                System.out.println("IOException");
                            }
                            break;
                        case 2:
                            try {
                                calendar.addNewOutgoing();
                            } catch (IOException exception){
                                System.out.println("IOException");
                            }
                            break;
                        case 3:
                            try{
                                calendar.addNewMeeting();
                            } catch (IOException exception){
                                System.out.println("IOException");
                            }

                            break;
                        case 4:
                            try {
                                calendar.addNewTrip();
                            } catch (IOException exception){
                                System.out.println("IOException");
                            }
                            break;
                    }
                }
            }

        }
    }
}
