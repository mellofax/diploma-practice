package ForAPI.QaseIO;

import java.util.Locale;

public class Project {
    private String title;
    private String code;

    public Project(String title, String code) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code.toUpperCase(Locale.ROOT);
    }
    public String getTitle() {
        return title.toUpperCase(Locale.ROOT);
    }
}
