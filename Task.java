import java.time.LocalDateTime;

public abstract class Task {
    private String title;
    private Category category;
    private LocalDateTime time;
    private Location location;

    public LocalDateTime getTime() {
        return time;
    }

    public abstract void showInfo();
}
