package se459.agile.Player;

import se459.agile.Items.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pack {
    private final int capacity = 5; // Limit the number of items
    private List<Item> items;

    public Pack() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (items.size() < capacity) {
            items.add(item);
            return true;
        } else {
            System.out.println("Pack is full! Drop an item to pick up a new one.");
            return false;
        }
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public void rearrange(int oldIndex, int newIndex) {
        if (oldIndex < items.size() && newIndex < items.size()) {
            Collections.swap(items, oldIndex, newIndex);
        }
    }

    public void displayPack() {
        System.out.println("Inventory:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    public List<Item> getItems() {
        return items;
    }
}
