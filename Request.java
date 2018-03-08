import java.time.LocalDateTime;

public class Request extends Task {
    private String title;
    private Category category;
    private LocalDateTime time;

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    public Request(String title, Category category, LocalDateTime time){
        this.title = title;
        this.category = category;
        this.time = time;
    }

    public void showInfo(){
        System.out.println(time.toString() + " - You have to do: \"" + title + "\". Category: " + category);
    }
}
