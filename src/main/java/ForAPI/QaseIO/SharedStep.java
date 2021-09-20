package ForAPI.QaseIO;

import ForAPI.Pages.ProjectPage;

import java.util.Locale;

public class SharedStep{
    private String title;


    public SharedStep(String title)
    {
        this.title = title;
    }

    public String getTitle() {
        return title.toUpperCase(Locale.ROOT);
    }
}
