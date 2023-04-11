public class Group {
    String name;
    String description;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + "   (" + description + ')';
    }
}
