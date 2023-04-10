import java.io.Serializable;

public class Group implements Serializable {
    String name;
    String description;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
