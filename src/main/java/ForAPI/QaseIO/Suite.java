package ForAPI.QaseIO;

import java.util.Locale;

public class Suite {
    private String title;
    private int parent_id;
    private String description;
    private String preconditions;

    public Suite(String title, int paretn_id, String description, String preconditions) {
        this.title = title;
        this.parent_id = paretn_id;
        this.description = description;
        this.preconditions = preconditions;
    }

    public String getTitle() {
        return title.toUpperCase(Locale.ROOT);
    }
    public int getParent_id() {
        return parent_id;
    }
    public String getDescription() {
        return description.toUpperCase(Locale.ROOT);
    }
    public String getPreconditions() {
        return preconditions.toUpperCase(Locale.ROOT);
    }
}
