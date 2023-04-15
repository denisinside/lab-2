import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class EditOrAddGood {
    private JFrame editFrame;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JComboBox<String> groupList;
    private JTextField producerField;
    private JTextField priceField;
    private JTextField iconField;
    private Good good;


    public void setEditMenu(Good g, String operation){
        good=g;
       if (operation.equals("Редагування")) editFrame = new JFrame("Редагування характеристик товару " + good.name);
       else editFrame = new JFrame("Додавання нового товару");
       editFrame.setLayout(new BorderLayout());
        editFrame.setBounds(MainMenu.screenDimension.width/5,MainMenu.screenDimension.height/6,800,800);
        editFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel editPanel = new JPanel(new GridLayout(6, 2, 10, 30));
        editPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        Font font = new Font("Arial Black", Font.BOLD,20);
        Font font1 = new Font("Arial Black", Font.BOLD,12);
        JLabel nameLabel = new JLabel("Ім'я:");
        nameLabel.setFont(font);
        nameField = new JTextField(g.name);
        nameField.setFont(font);

        JLabel descriptionLabel = new JLabel("Опис:");
        descriptionLabel.setFont(font);
        descriptionArea = new JTextArea(g.description);
        descriptionArea.setFont(font1);

        JLabel producerLabel = new JLabel("Виробник:");
        producerLabel.setFont(font);
        producerField = new JTextField(g.producer);
        producerField.setFont(font);

        JLabel priceLabel = new JLabel("Ціна:");
        priceLabel.setFont(font);
        priceField = new JTextField(String.valueOf(g.price));
        priceField.setFont(font);

        JLabel groupLabel = new JLabel("Група:");
        groupLabel.setFont(font);
        groupList = new JComboBox<>();
        for(Group gr: Shop.groupArray){
            groupList.addItem(gr.name);
        }
        if (!good.groupName.equals("")) groupLabel.setForeground(Color.BLUE);
        JLabel iconLabel = new JLabel("Іконка:");
        iconLabel.setFont(font);
        iconField = new JTextField(g.link1);
        nameField.setFont(new Font("Arial Black", Font.BOLD,12));

        editPanel.add(nameLabel);
        editPanel.add(nameField);
        editPanel.add(descriptionLabel);
        editPanel.add(new JScrollPane(descriptionArea));
        editPanel.add(producerLabel);
        editPanel.add(producerField);
        editPanel.add(priceLabel);
        editPanel.add(priceField);
        editPanel.add(groupLabel);
        editPanel.add(groupList);
        editPanel.add(iconLabel);
        editPanel.add(iconField);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2,30,0));
        JButton approveButton = new JButton("Підтвердити");
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCorrectInput()) {
                    editGood(operation);
                    editFrame.dispose();
                    if (GoodsMenu.goodsFrame != null && GoodsMenu.goodsFrame.isVisible()) {
                        GoodsMenu.goodsFrame.dispose();
                        GoodsMenu.setGoodsMenu(GoodsMenu.goodsFrame.getBounds());
                    }
                }
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
        buttonPanel.add(approveButton);
        buttonPanel.add(reset);

        editFrame.add(editPanel, BorderLayout.CENTER);
        editFrame.add(buttonPanel,BorderLayout.SOUTH);
        editFrame.setVisible(true);
    }
    private boolean isCorrectInput(){
        for(char c: priceField.getText().toCharArray()){
            if(!Character.isDigit(c)){
                JOptionPane.showMessageDialog(null,"Ціна введена неправильно", "Input error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (nameField.getText().equals("") || producerField.getText().equals("") || priceField.getText().equals("") || nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Дані не введено повністю", "Input error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public void editGoodIcon(){
        try {
             good.setImage(iconField.getText());
        }catch (MalformedURLException e){
            JOptionPane.showMessageDialog(GoodsMenu.goodsFrame,"Посилання не підходить. Встановлено картинку за замовчуванням.");
            good.image = new JLabel(Good.defaultImg.getIcon());
        }
    }
    public void editGood(String operation) {

        String newGoodName = nameField.getText().trim();
        if (newGoodName.equalsIgnoreCase(good.name) || Shop.goodsArray.stream().noneMatch(g -> g.name.equalsIgnoreCase(newGoodName))) {
            good.setName(newGoodName);
            good.setDescription(descriptionArea.getText());
            good.setProducer(producerField.getText());
            good.setPrice(Integer.parseInt(priceField.getText()));
            good.setGroupName((String) groupList.getSelectedItem());
            editGoodIcon();

            if (operation.equals("Редагування")){
                JOptionPane.showMessageDialog(null, "Товар успішно змінено!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Товар успішно додано!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Shop.goodsArray.add(good);
            }
        }else
            JOptionPane.showMessageDialog(null,"Товар з таким ім'ям вже є!", "Input error", JOptionPane.ERROR_MESSAGE);
    }
}
