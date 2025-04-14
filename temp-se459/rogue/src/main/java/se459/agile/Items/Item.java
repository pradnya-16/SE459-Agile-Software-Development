package se459.agile.Items;

public class Item {
    private String name;
    private String type;
    private int value; // Could be used for health, attack, etc.

    public Item(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getValue() { return value; }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
