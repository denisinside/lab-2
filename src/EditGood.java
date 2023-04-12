import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGood {
    private JFrame editFrame;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField producerField;
    private JTextField priceField;
    private Good good;

    public void setEditMenu(Good g){
        good=g;
        editFrame = new JFrame("Редагування характеристик товару");
        editFrame.setLayout(new BorderLayout());
        editFrame.setBounds(MainMenu.screenDimension.width/3,MainMenu.screenDimension.height/4,500,600);
        editFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 70));
        JLabel nameLabel = new JLabel("Ім'я:");
        nameField = new JTextField(g.name);
        JLabel descriptionLabel = new JLabel("Опис:");
        descriptionArea = new JTextArea(g.description);
        JLabel producerLabel = new JLabel("Виробник:");
        producerField = new JTextField(g.producer);
        JLabel priceLabel = new JLabel("Ціна:");
        priceField = new JTextField(String.valueOf(g.price));

        editPanel.add(nameLabel);
        editPanel.add(nameField);
        editPanel.add(descriptionLabel);
        editPanel.add(descriptionArea);
        editPanel.add(producerLabel);
        editPanel.add(producerField);
        editPanel.add(priceLabel);
        editPanel.add(priceField);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2,30,0));
        JButton OK = new JButton("OK");
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Товар успішно змінено!", "Success", JOptionPane.INFORMATION_MESSAGE);
                editGood();
                editFrame.dispose();
                GoodsMenu.goodsFrame.dispose(); // close the previous GoodsMenu
                GoodsMenu.setGoodsMenu(); // open the updated GoodsMenu
            }
        });
        JButton reset = new JButton("Скинути");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText(g.name);
                descriptionArea.setText(g.description);
                producerField.setText(g.producer);
                priceField.setText(String.valueOf(g.price));
            }
        });
        buttonPanel.add(OK);
        buttonPanel.add(reset);

        editFrame.add(editPanel, BorderLayout.CENTER);
        editFrame.add(buttonPanel,BorderLayout.SOUTH);
        editFrame.setVisible(true);
    }

    public void editGood() {
        String newName = nameField.getText();
        String newDescription = descriptionArea.getText();
        String newProducer = producerField.getText();
        String newPrice = priceField.getText();

        good.setName(newName);
        good.setDescription(newDescription);
        good.setProducer(newProducer);
        good.setPrice(Integer.parseInt(newPrice));
    }
}
