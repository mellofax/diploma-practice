package ForAPI.Pages;

public class ProjectGenerator {
    public static String GenerateName(int length)
    {
        String str = "";
        for (int i = 0; i < length; i++) {
            int key = (int)(Math.random() * ((122 - 97) + 1)) + 97;
            str+=(char)key;
        }
        return str;
    }
}
