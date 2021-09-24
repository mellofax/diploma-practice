package ForAPI.QaseIO;

import java.util.Locale;

public class Milestone {
    private String title;
    private String description;
    public Milestone(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title.toUpperCase(Locale.ROOT);
    }
    public String getDescription() {
        return description.toUpperCase(Locale.ROOT);
    }
}
