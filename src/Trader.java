import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Trader {
    private JFrame tradeFrame;
    private JPanel tradePanel;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JLabel infoLabel;
    private JLabel buyLabel;
    private JLabel sellLabel;
    private JTextField buyField;
    private JTextField sellField;
    private JButton OK;
    private Good good;
    int buy, sell, rest, amount, total;

    void setTradeMenu(Good g){
        good=g;
        tradeFrame = new JFrame();
        tradeFrame.setBounds(MainMenu.screenDimension.width/4,MainMenu.screenDimension.width/4,500,400);
        tradeFrame.setLayout(new BorderLayout());

        tradePanel = new JPanel(new GridLayout(3,2,20,30));
        buttonPanel = new JPanel(new FlowLayout());
        infoPanel = new JPanel(new FlowLayout());


        infoLabel = new JLabel("Кількість товару на складі: "+ g.amount);
        buyLabel = new JLabel("Закупити товару: ");
        sellLabel = new JLabel("Спродати товару: ");

        buyField = new JTextField("Купівля");
        sellField = new JTextField("Продаж");

        OK = new JButton("OK");
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = check();
                if(b){
                    boolean right = calculate();
                    if(right){
                        int newAmount = total;
                        JOptionPane.showMessageDialog(null,"Чудово! Тепер одиниць на складі: "+newAmount, "Success operation", JOptionPane.INFORMATION_MESSAGE);
                        g.setAmount(newAmount);
                        tradeFrame.dispose(); // close the previous GoodsMenu
                        GoodsMenu.setGoodsMenu(); // open the updated GoodsMenu
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Невірно введені дані! Спробуйте ще раз.", "Input error", JOptionPane.ERROR_MESSAGE);
                    reset();
                }
            }
        });

        tradePanel.add(buyLabel);
        tradePanel.add(buyField);
        tradePanel.add(sellLabel);
        tradePanel.add(sellField);
        buttonPanel.add(OK);
        infoPanel.add(infoLabel);
        tradeFrame.add(infoPanel, BorderLayout.NORTH);
        tradeFrame.add(tradePanel, BorderLayout.CENTER);
        tradeFrame.add(buttonPanel, BorderLayout.SOUTH);
        tradeFrame.setVisible(true);
    }

    private void reset() {
        buyField.setText("Купівля...");
        sellField.setText("Продаж...");
    }

    private boolean calculate(){
        boolean isLegal = false;

        buy = Integer.parseInt(buyField.getText());
        sell = Integer.parseInt(sellField.getText());

        amount = good.amount;
        rest = buy-sell;
        total = amount+rest;

        if(total<0){
            JOptionPane.showMessageDialog(null,"Операція неможлива: від'ємний результат!", "Result error", JOptionPane.ERROR_MESSAGE);
            reset();
        }
        else return true;
        return isLegal;
    }
    private boolean check(){
       boolean isCorrect;

            isCorrect = cycle(buyField.getText());
            if(isCorrect){
                isCorrect = cycle(sellField.getText());
            }
       return isCorrect;
    }

    private boolean cycle(String s){

        if(s.equals("") || s.equals(" ")) return false;
        for(char c: s.toCharArray()){
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
