import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.TreeMap;

public class Calendar {

    private TreeMap<LocalDateTime,Task> myTasks = new TreeMap<>();
    private String calendarPath;

    public void setCalendarPath(String calendarPath) {
        this.calendarPath = calendarPath;
    }

    public void addNewMeeting() throws IOException {
        Scanner read = new Scanner(System.in);

        System.out.println("\nPlease fill in fields below:\n");

        String[] input = new String[10];
        String[] fields = new String[10];

        fields[0]="Meeting title";
        fields[1]="Category";
        fields[2]="Time";
        fields[3]="Country";
        fields[4]="City";
        fields[5]="Address";
        fields[6]="Room number";
        fields[7]="With who";
        fields[8]="Duration hours";
        fields[9]="Duration minutes";

        System.out.println(fields[0]+": ");
        input[0] = read.nextLine();

        Category category = null;
        boolean isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[1]+": ");
            input[1] = read.nextLine();
            try {
                category = Category.valueOf(input[1].toUpperCase());
            } catch (IllegalArgumentException exception){
                System.out.println("You have to choose between following categories: Relax, Bussiness, School, Job");
                isCorrect = false;
            }
        }

        LocalDateTime time = null;
        isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[2]+": ");
            input[2] = read.nextLine();
            try {
                time = LocalDateTime.parse(input[2]);
            } catch (DateTimeParseException exception){
                System.out.println("The time must be entered in following format: YYYY-MM-DDTHH:MM, where letter T must separate a date and an hour");
                isCorrect = false;
            }
        }

        System.out.println(fields[3]+" (you can leave this field empty): ");
        input[3] = read.nextLine();
        if (input[3].equals("")) input[3]="none";

        isCorrect = false;
        while(!isCorrect) {
            System.out.println(fields[4] + ": ");
            input[4] = read.nextLine();
            if (input[4].equals("")) System.out.println("You cannot leave this field empty.");
            else isCorrect = true;
        }

        isCorrect = false;
        while(!isCorrect) {
            System.out.println(fields[5] + ": ");
            input[5] = read.nextLine();
            if (input[5].equals("")) System.out.println("You cannot leave this field empty.");
            else isCorrect = true;
        }

        int room = -2;
        isCorrect = false;
        while(!isCorrect){
            System.out.println(fields[6]+" (you can leave this field empty): ");
            input[6] = read.nextLine();
            if (input[6].equals("")) input[6]="-1";
            try {
                room = Integer.parseInt(input[6]);
            } catch (NumberFormatException exception){
                System.out.println("Room number must be a number! (sic!)");
            }
            if (room < -1) System.out.println("A room number must equals at least 0 (or (-1) if not specified)");
            else isCorrect = true;
        }

        Location location = new Location(input[3], input[4], input[5], room);

        System.out.println(fields[7]+" (you can leave this field empty): ");
        input[7] = read.nextLine();
        if (input[7].equals("")) input[7]="none";


        int durationHours = -1;
        int durationMinutes = -1;
        boolean areCorrectBoth = false;
        while(!areCorrectBoth) {
            isCorrect = false;

            durationHours = -1;
            while (!isCorrect) {
                System.out.println(fields[8] + " (you can leave this field empty): ");
                input[8] = read.nextLine();
                if (input[8].equals("")) input[8] = "0";
                try {
                    durationHours = Integer.parseInt(input[8]);
                } catch (NumberFormatException exception) {
                    System.out.println("Time in hours must be a number! (sic!)");
                }
                if (durationHours < 0) System.out.println("Time in hours must be a positive number");
                else isCorrect = true;
            }

            durationMinutes = -1;
            isCorrect = false;
            while (!isCorrect) {
                System.out.println(fields[9] + " (you can leave this field empty): ");
                input[9] = read.nextLine();
                if (input[9].equals("")) input[9] = "0";
                try {
                    durationMinutes = Integer.parseInt(input[9]);
                } catch (NumberFormatException exception) {
                    System.out.println("Time in minutes must be a number! (sic!)");
                }
                if (durationMinutes < 0 || durationMinutes >= 60) System.out.println("Time in minutes must be between 0 and 59");
                else isCorrect = true;
            }

            if (durationHours+durationMinutes == 0) System.out.println("The duration time must be at least 1 minute!");
            else areCorrectBoth = true;
        }

        myTasks.put(time, new Meeting(input[0], category, time, location, input[7], durationHours, durationMinutes));

        Writer write = new FileWriter(calendarPath, true);
        write.write("M\n");
        write.write(input[0]+"\n");
        write.write(input[1]+"\n");
        write.write(input[2]+"\n");
        write.write(input[3]+"\n");
        write.write(input[4]+"\n");
        write.write(input[5]+"\n");
        write.write(input[6]+"\n");
        write.write(input[7]+"\n");
        write.write(input[8]+"\n");
        write.write(input[9]+"\n");
        write.close();
    };
    public void addNewOutgoing() throws IOException {
        Scanner read = new Scanner(System.in);

        System.out.println("\nPlease fill in fields below:\n");

        String[] input = new String[10];
        String[] fields = new String[10];

        fields[0]="Outgoing title";
        fields[1]="Category";
        fields[2]="Time";
        fields[3]="Country";
        fields[4]="City";
        fields[5]="Address";
        fields[6]="Room number";
        fields[7]="With who";
        fields[8]="Duration hours";
        fields[9]="Duration minutes";

        System.out.println(fields[0]+": ");
        input[0] = read.nextLine();

        Category category = null;
        boolean isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[1]+": ");
            input[1] = read.nextLine();
            try {
                category = Category.valueOf(input[1].toUpperCase());
            } catch (IllegalArgumentException exception){
                System.out.println("You have to choose between following categories: Relax, Bussiness, School, Job");
                isCorrect = false;
            }
        }

        LocalDateTime time = null;
        isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[2]+": ");
            input[2] = read.nextLine();
            try {
                time = LocalDateTime.parse(input[2]);
            } catch (DateTimeParseException exception){
                System.out.println("The time must be entered in following format: YYYY-MM-DDTHH:MM, where letter T must separate a date and an hour");
                isCorrect = false;
            }
        }

        System.out.println(fields[3]+" (you can leave this field empty): ");
        input[3] = read.nextLine();
        if (input[3].equals("")) input[3]="none";

        isCorrect = false;
        while(!isCorrect) {
            System.out.println(fields[4] + ": ");
            input[4] = read.nextLine();
            if (input[4].equals("")) System.out.println("You cannot leave this field empty.");
            else isCorrect = true;
        }

        isCorrect = false;
        while(!isCorrect) {
            System.out.println(fields[5] + ": ");
            input[5] = read.nextLine();
            if (input[5].equals("")) System.out.println("You cannot leave this field empty.");
            else isCorrect = true;
        }

        int room = -2;
        isCorrect = false;
        while(!isCorrect){
            System.out.println(fields[6]+" (you can leave this field empty): ");
            input[6] = read.nextLine();
            if (input[6].equals("")) input[6]="-1";
            try {
                room = Integer.parseInt(input[6]);
            } catch (NumberFormatException exception){
                System.out.println("Room number must be a number! (sic!)");
            }
            if (room < -1) System.out.println("A room number must equals at least 0 (or (-1) if not specified)");
            else isCorrect = true;
        }

        Location location = new Location(input[3], input[4], input[5], room);

        System.out.println(fields[7]+" (you can leave this field empty): ");
        input[7] = read.nextLine();
        if (input[7].equals("")) input[7]="none";


        int durationHours = -1;
        int durationMinutes = -1;
        boolean areCorrectBoth = false;
        while(!areCorrectBoth) {
            isCorrect = false;

            durationHours = -1;
            while (!isCorrect) {
                System.out.println(fields[8] + " (you can leave this field empty): ");
                input[8] = read.nextLine();
                if (input[8].equals("")) input[8] = "0";
                try {
                    durationHours = Integer.parseInt(input[8]);
                } catch (NumberFormatException exception) {
                    System.out.println("Time in hours must be a number! (sic!)");
                }
                if (durationHours < 0) System.out.println("Time in hours must be a positive number");
                else isCorrect = true;
            }

            durationMinutes = -1;
            isCorrect = false;
            while (!isCorrect) {
                System.out.println(fields[9] + " (you can leave this field empty): ");
                input[9] = read.nextLine();
                if (input[9].equals("")) input[9] = "0";
                try {
                    durationMinutes = Integer.parseInt(input[9]);
                } catch (NumberFormatException exception) {
                    System.out.println("Time in minutes must be a number! (sic!)");
                }
                if (durationMinutes < 0 || durationMinutes >= 60) System.out.println("Time in minutes must be between 0 and 59");
                else isCorrect = true;
            }

            if (durationHours+durationMinutes == 0) System.out.println("The duration time must be at least 1 minute!");
            else areCorrectBoth = true;
        }

        myTasks.put(time, new Outgoing(input[0], category, time, location, input[7], durationHours, durationMinutes));

        Writer write = new FileWriter(calendarPath, true);
        write.write("O\n");
        write.write(input[0]+"\n");
        write.write(input[1]+"\n");
        write.write(input[2]+"\n");
        write.write(input[3]+"\n");
        write.write(input[4]+"\n");
        write.write(input[5]+"\n");
        write.write(input[6]+"\n");
        write.write(input[7]+"\n");
        write.write(input[8]+"\n");
        write.write(input[9]+"\n");
        write.close();
    };
    public void addNewRequest() throws IOException {
        Scanner read = new Scanner(System.in);

        System.out.println("\nPlease fill in fields below:\n");

        String[] input = new String[3];
        String[] fields = new String[3];

        fields[0]="Request title";
        fields[1]="Category";
        fields[2]="Deadline";

        System.out.println(fields[0]+": ");
        input[0] = read.nextLine();

        Category category = null;
        boolean isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[1]+": ");
            input[1] = read.nextLine();
            try {
                category = Category.valueOf(input[1].toUpperCase());
            } catch (IllegalArgumentException exception){
                System.out.println("You have to choose between following categories: Relax, Bussiness, School, Job");
                isCorrect = false;
            }
        }

        LocalDateTime time = null;
        isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[2]+": ");
            input[2] = read.nextLine();
            try {
                time = LocalDateTime.parse(input[2]);
            } catch (DateTimeParseException exception){
                System.out.println("The time must be entered in following format: YYYY-MM-DDTHH:MM, where letter T must separate a date and an hour");
                isCorrect = false;
            }
        }

        myTasks.put(time, new Request(input[0], category, time));

        Writer write = new FileWriter(calendarPath, true);
        write.write("R\n");
        write.write(input[0]+"\n");
        write.write(input[1]+"\n");
        write.write(input[2]+"\n");
        write.close();

    };
    public void addNewTrip() throws IOException {
        Scanner read = new Scanner(System.in);

        System.out.println("\nPlease fill in fields below:\n");

        String[] input = new String[10];
        String[] fields = new String[10];

        fields[0]="Trip title";
        fields[1]="Category";
        fields[2]="Departure time";
        fields[3]="Destination country";
        fields[4]="Destination city";
        fields[5]="Destination address";
        fields[6]="Return time";

        System.out.println(fields[0]+": ");
        input[0] = read.nextLine();

        Category category = null;
        boolean isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[1]+": ");
            input[1] = read.nextLine();
            try {
                category = Category.valueOf(input[1].toUpperCase());
            } catch (IllegalArgumentException exception){
                System.out.println("You have to choose between following categories: Relax, Bussiness, School, Job");
                isCorrect = false;
            }
        }

        LocalDateTime time = null;
        isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[2]+": ");
            input[2] = read.nextLine();
            try {
                time = LocalDateTime.parse(input[2]);
            } catch (DateTimeParseException exception){
                System.out.println("The time must be entered in following format: YYYY-MM-DDTHH:MM, where letter T must separate a date and an hour");
                isCorrect = false;
            }
        }

        LocalDateTime returnTime = time;
        isCorrect = false;
        while(!isCorrect){
            isCorrect = true;
            System.out.println(fields[6]+": ");
            input[6] = read.nextLine();
            try {
                returnTime = LocalDateTime.parse(input[6]);
            } catch (DateTimeParseException exception){
                System.out.println("The time must be entered in following format: YYYY-MM-DDTHH:MM, where letter T must separate a date and an hour");
                isCorrect = false;
            }
            if (!returnTime.isAfter(time)) {
                System.out.println("Return time must be later than departure time!");
                isCorrect = false;
            }
        }

        System.out.println(fields[3]+" (you can leave this field empty): ");
        input[3] = read.nextLine();
        if (input[3].equals("")) input[3]="none";

        isCorrect = false;
        while(!isCorrect) {
            System.out.println(fields[4] + ": ");
            input[4] = read.nextLine();
            if (input[4].equals("")) System.out.println("You cannot leave this field empty.");
            else isCorrect = true;
        }

        System.out.println(fields[5]+" (you can leave this field empty): ");
        input[5] = read.nextLine();
        if (input[5].equals("")) input[5]="none";

        Location location = new Location(input[3], input[4], input[5], -1);

        myTasks.put(time, new Trip(input[0], category, time, returnTime, location));

        Writer write = new FileWriter(calendarPath, true);
        write.write("T\n");
        write.write(input[0]+"\n");
        write.write(input[1]+"\n");
        write.write(input[2]+"\n"); //departure time
        write.write(input[6]+"\n"); //return time
        write.write(input[3]+"\n");
        write.write(input[4]+"\n");
        write.write(input[5]+"\n");
        write.close();
    };


    public void showMyTasks() {
        if (myTasks.isEmpty()) {
            System.out.println("\n>>> You don't have any tasks <<< ;)");
        } else {
            System.out.println("\nYour coming tasks:\n");
            boolean nothingComing = true;
            for (Task task : myTasks.values()) {
                if (task.getTime().isAfter(LocalDateTime.now())) {
                    task.showInfo();
                    nothingComing = false;
                }
            }
            if (nothingComing) System.out.println("\nNothing's coming ;)");
        }
    }

    public void loadMyCalendar() throws FileNotFoundException{
        File myCalendar = new File(calendarPath);
        Scanner read = new Scanner(myCalendar);

        String taskType;

        while(read.hasNextLine()){
            taskType = read.nextLine();
            if (taskType.equals("M")){
                String title;
                Category category;
                LocalDateTime time;
                Location location = new Location();
                String withWho;
                int durationHours;
                int durationMinutes;

                title = read.nextLine();
                category = Category.valueOf(read.nextLine().toUpperCase());
                time = LocalDateTime.parse(read.nextLine());
                location.setCountry(read.nextLine());
                location.setCity(read.nextLine());
                location.setAddress(read.nextLine());
                location.setRoom(Integer.parseInt(read.nextLine()));
                withWho = read.nextLine();
                durationHours = Integer.parseInt(read.nextLine());
                durationMinutes = Integer.parseInt(read.nextLine());

                myTasks.put(time, new Meeting(title, category, time, location, withWho, durationHours, durationMinutes));
            }
            else if (taskType.equals("R")){
                String title;
                Category category;
                LocalDateTime time;

                title = read.nextLine();
                category = Category.valueOf(read.nextLine().toUpperCase());
                time = LocalDateTime.parse(read.nextLine());

                myTasks.put(time, new Request(title, category, time));
            }
            else if (taskType.equals("O")){
                String title;
                Category category;
                LocalDateTime time;
                Location location = new Location();
                String withWho;
                int durationHours;
                int durationMinutes;

                title = read.nextLine();
                category = Category.valueOf(read.nextLine().toUpperCase());
                time = LocalDateTime.parse(read.nextLine());
                location.setCountry(read.nextLine());
                location.setCity(read.nextLine());
                location.setAddress(read.nextLine());
                location.setRoom(Integer.parseInt(read.nextLine()));
                withWho = read.nextLine();
                durationHours = Integer.parseInt(read.nextLine());
                durationMinutes = Integer.parseInt(read.nextLine());

                myTasks.put(time, new Outgoing(title, category, time, location, withWho, durationHours, durationMinutes));
            }
            else if (taskType.equals("T")){
                String title;
                Category category;
                LocalDateTime time;
                LocalDateTime returnTime;
                Location location = new Location();

                title = read.nextLine();
                category = Category.valueOf(read.nextLine().toUpperCase());
                time = LocalDateTime.parse(read.nextLine());
                returnTime = LocalDateTime.parse(read.nextLine());
                location.setCountry(read.nextLine());
                location.setCity(read.nextLine());
                location.setAddress(read.nextLine());
                location.setRoom(-1);

                myTasks.put(time, new Trip(title, category, time, returnTime, location));
            }
        }
        read.close();
    }

}
