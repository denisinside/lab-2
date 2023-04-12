import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGood {
    private JFrame addFrame;
    private JLabel nameLabel, descriptionLabel, producerLabel, priceLabel, groupLabel;
    private JComboBox<Object> groupList;
    private JButton OK;
    private JTextField nameField, producerField, priceField;
    private JTextArea descriptionArea;
    private boolean isCorrect;

    public AddGood() {
    }

    void setAddGood(){
        addFrame = new JFrame("Додати товар");
        addFrame.setLayout(new BorderLayout());
        addFrame.setBounds(MainMenu.screenDimension.width/3,MainMenu.screenDimension.height/4,500,600);
        addFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 70));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JLabel nameLabel = new JLabel("Ім'я:");
        nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Опис:");
        descriptionArea = new JTextArea();
        JLabel producerLabel = new JLabel("Виробник:");
        producerField = new JTextField();
        JLabel priceLabel = new JLabel("Ціна:");
        priceField = new JTextField();
        groupLabel = new JLabel("Група:");
        groupList = new JComboBox<>();
        for(Group gr: Shop.groupArray){
            groupList.addItem(gr.name);
        }

        OK = new JButton("OK");
        OK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                check();
                if(isCorrect){
                    JOptionPane.showMessageDialog(null, "Товар успішно додано!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    addFrame.dispose();
                    GoodsMenu.goodsFrame.dispose(); // close the previous GoodsMenu
                    GoodsMenu.setGoodsMenu(); // open the updated GoodsMenu
                }
                else{
                    nameField.setText("");
                    descriptionArea.setText("");
                    producerField.setText("");
                    priceField.setText("");
                }
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionArea);
        inputPanel.add(producerLabel);
        inputPanel.add(producerField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(groupLabel);
        inputPanel.add(groupList);
        buttonPanel.add(OK);

        addFrame.add(inputPanel, BorderLayout.CENTER);
        addFrame.add(buttonPanel, BorderLayout.SOUTH);
        addFrame.setVisible(true);
    }

    private void check(){
        for(char c: priceField.getText().toCharArray()){
            if(!Character.isDigit(c)){
                isCorrect = false;
                JOptionPane.showMessageDialog(null,"Дані введено неправильно", "Input error", JOptionPane.ERROR_MESSAGE);
                break;
            }
            else isCorrect = true;
        }
    }
}
