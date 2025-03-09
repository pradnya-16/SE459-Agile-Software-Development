package se459.agile.UI;

import se459.agile.Player.Player;
import se459.agile.Items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryUI extends JFrame {
    private Player player;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;

    public InventoryUI(Player player) {
        this.player = player;

        setTitle("Inventory");
        setSize(300, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.BLACK);

        listModel = new DefaultListModel<>();
        updateItemList();

        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton dropButton = new JButton("Drop Item");
        JButton closeButton = new JButton("Close");

        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    player.dropItem(selectedIndex);
                    updateItemList();
                }
            }
        });

        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(dropButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateItemList() {
        listModel.clear();
        for (Item item : player.getPack().getItems()) {
            listModel.addElement(item.toString());
        }
    }
}
