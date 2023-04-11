import javax.swing.*;
import java.awt.*;

public class EditGood {
    private JFrame editFrame;
    private JPanel editPanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel descriptionLabel;
    private JTextArea descriptionArea;
    private JLabel producerLabel;
    private JTextField producerField;
    private JLabel priceLabel;
    private JTextField priceField;

    private JButton OK;
    private JButton reset;


    public JFrame setEditMenu(Good g){

        editFrame = new JFrame("Редагування характеристик товару");
        editFrame.setLayout(new BorderLayout());
        editFrame.setBounds(MainMenu.screenDimension.width/3,MainMenu.screenDimension.height/4,500,600);
        editFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        editPanel = new JPanel(new GridLayout(4,2,10,70));
        nameLabel = new JLabel("Ім'я:");
        nameField = new JTextField(g.name);
        descriptionLabel = new JLabel("Опис:");
        descriptionArea = new JTextArea(g.description);
        producerLabel = new JLabel("Виробник:");
        producerField = new JTextField(g.producer);
        priceLabel = new JLabel("Ціна:");
        priceField = new JTextField(String.valueOf(g.price));

        editPanel.add(nameLabel);
        editPanel.add(nameField);
        editPanel.add(descriptionLabel);
        editPanel.add(descriptionArea);
        editPanel.add(producerLabel);
        editPanel.add(producerField);
        editPanel.add(priceLabel);
        editPanel.add(priceField);



        JPanel bp = new JPanel(new GridLayout(1,2,30,0));
        OK = new JButton("OK");
        reset = new JButton("Скинути");
        bp.add(OK);
        bp.add(reset);

        editFrame.add(editPanel, BorderLayout.CENTER);
        editFrame.add(bp,BorderLayout.SOUTH);
        editFrame.setVisible(true);
        return editFrame;
    }

    public void editGood() {

    }
}
