import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGood {
    private JFrame addFrame;
    private JComboBox<Object> groupList;
    private JTextField nameField, producerField, priceField;
    private JTextArea descriptionArea;
    private boolean isCorrect;

private String name;
private String description;
private String producer;
private String groupName;
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
        JScrollPane scroll = new JScrollPane(descriptionArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scroll.setVisible(true);
        JLabel producerLabel = new JLabel("Виробник:");
        producerField = new JTextField();
        JLabel priceLabel = new JLabel("Ціна:");
        priceField = new JTextField();
        JLabel groupLabel = new JLabel("Група:");
        groupList = new JComboBox<>();
        for(Group gr: Shop.groupArray){
            groupList.addItem(gr.name);
        }

        JButton OK = new JButton("OK");
        OK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Group gr = null;
                check();

                if(isCorrect){
                    groupName = (String) groupList.getSelectedItem();
                    for(Group g: Shop.groupArray){
                        if(g.name.equals(groupName)) gr = g;
                    }
                    short index = (short) Shop.groupArray.indexOf(gr);
                    int price = Integer.parseInt(priceField.getText());

                    Good g = new Good(name,description,producer,price,0, index,groupName);
                    Shop.goodsArray.add(g);
                    GoodsMenu.goodPanels.add(new GoodsMenu.GoodPanel(g));

                    JOptionPane.showMessageDialog(null, "Товар успішно додано!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    addFrame.dispose();
                    GoodsMenu.goodsFrame.dispose();
                    GoodsMenu.setGoodsMenu();
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

        name = nameField.getText();
        description = descriptionArea.getText();
        producer = producerField.getText();


        for(char c: priceField.getText().toCharArray()){
            if(!Character.isDigit(c)){
                isCorrect = false;
                JOptionPane.showMessageDialog(null,"Невірно введені дані!", "Input error", JOptionPane.ERROR_MESSAGE);
                break;
            }
            else isCorrect = true;
        }
        if(name.equals("") || description.equals("") || producer.equals("") && isCorrect){
            isCorrect = false;
            JOptionPane.showMessageDialog(null, "Невірно введені дані!", "Input error", JOptionPane.ERROR_MESSAGE);
            nameField.setText("");
            descriptionArea.setText("");
            producerField.setText("");
        }
    }
}
