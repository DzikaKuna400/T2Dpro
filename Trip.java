import java.time.LocalDateTime;

public class Trip extends Task {
    private String title;
    private Category category;
    private LocalDateTime time;
    private LocalDateTime returnTime;
    private Location location;

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    public Trip(String title, Category category, LocalDateTime time, LocalDateTime returnTime, Location location){
        this.title = title;
        this.category = category;
        this.time = time;
        this.returnTime = returnTime;
        this.location = location;
    }

    public void showInfo(){
        System.out.println(time.toString() + " - Trip \"" + title + "\": You are traveling to "
                + location.toString() + ". You will return " + returnTime.toString() + ". Category: " + category);
    }
}
