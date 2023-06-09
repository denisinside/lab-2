import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Trader {
    private JFrame tradeFrame;
    private JTextField buyField;
    private JTextField sellField;
    private Good good;
    private int buy;
    private int sell;
    private int total;


    void setTradeMenu(Good g){
        good=g;
        tradeFrame = new JFrame();
        tradeFrame.setBounds(MainMenu.screenDimension.width/4,MainMenu.screenDimension.width/4,500,400);
        tradeFrame.setLayout(new BorderLayout());

        JPanel tradePanel = new JPanel(new GridLayout(3, 2, 20, 30));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel infoPanel = new JPanel(new FlowLayout());


        JLabel infoLabel = new JLabel("Кількість товару на складі: " + g.amount);
        JLabel buyLabel = new JLabel("Закупити товару: ");
        JLabel sellLabel = new JLabel("Спродати товару: ");

        buyField = new JTextField("");
        sellField = new JTextField("");

        JButton OK = new JButton("OK");
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
                        GoodsMenu.goodsFrame.dispose();
                        GoodsMenu.setGoodsMenu(GoodsMenu.goodsFrame.getBounds()); // open the updated GoodsMenu
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Операція неможлива: від'ємний результат!", "Result error", JOptionPane.ERROR_MESSAGE);
                        reset();
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
        infoPanel.setBackground(Color.decode("#FAF0B2"));
        tradePanel.setBackground(Color.decode("#FAF0B2"));
        buttonPanel.setBackground(Color.decode("#FAF0B2"));
        tradeFrame.add(infoPanel, BorderLayout.NORTH);
        tradeFrame.add(tradePanel, BorderLayout.CENTER);
        tradeFrame.add(buttonPanel, BorderLayout.SOUTH);
        tradeFrame.setIconImage(Shop.icon);
        tradeFrame.setVisible(true);
    }

    private void reset() {
        buyField.setText("");
        sellField.setText("");
    }

    private boolean calculate(){
        boolean isLegal;

    if(buyField.getText().isEmpty() || buyField.getText().equals("")) buy=0;
    else buy = Integer.parseInt(buyField.getText());
    if(sellField.getText().isEmpty() || sellField.getText().equals("")) sell=0;
    else sell = Integer.parseInt(sellField.getText());

        int amount = good.amount;
        int rest = buy - sell;
        total = amount + rest;

        isLegal = total >= 0;
        return isLegal;
    }
    private boolean check(){
        boolean isCorrect;
        boolean buyIsCor;
        boolean sellIsCor;

        if(buyField.getText().isEmpty() || buyField.getText().equals("")){
            isCorrect = cycle(sellField.getText());
        }
        else if(sellField.getText().isEmpty() || sellField.getText().equals("")){
            isCorrect = cycle(buyField.getText());
        }
        else if (sellField.getText().isEmpty() && buyField.getText().isEmpty())  return false;
        else{
          buyIsCor = cycle(buyField.getText());
          sellIsCor = cycle(sellField.getText());
          isCorrect = buyIsCor && sellIsCor;
        }

        return isCorrect;
    }

    private boolean cycle(String s){

        if(s.isEmpty() || s.equals("")) return false;
        for(char c: s.toCharArray()){
            if(!Character.isDigit(c) || c=='-') {
                return false;
            }
        }
        return true;
    }
}
