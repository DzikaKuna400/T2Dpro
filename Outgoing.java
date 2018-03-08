import java.time.LocalDateTime;

public class Outgoing extends Task{

    private String title;
    private Category category;
    private LocalDateTime time;
    private Location location;
    private String withWho;
    private int durationHours;
    private int durationMinutes;

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    public Outgoing(String title, Category category, LocalDateTime time, Location location, String withWho, int durationHours, int durationMinutes){
        this.title = title;
        this.category = category;
        this.time = time;
        this.location = location;
        this.withWho = withWho;
        this.durationHours = durationHours;
        this.durationMinutes = durationMinutes;
    }


    public void showInfo(){
        String correctDurationMinutes = Integer.toString(durationMinutes);
        if (correctDurationMinutes.equals("0")) correctDurationMinutes = "00";
        if (withWho.equals("none")) {
            System.out.println(time.toString() + " - \"" + title + "\": You have to go to "
                    + location.toString() + ". It will take: " + durationHours + ":" + correctDurationMinutes + ". Category: " + category);
        } else {
            System.out.println(time.toString() + " - \"" + title + "\": You have to go to "
                    + location.toString() + " with " + withWho + ". It will take: " + durationHours + ":" + correctDurationMinutes + ". Category: " + category);
        }
    }
}
