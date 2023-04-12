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
    private JSpinner buyField;
    private JSpinner sellField;
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

        buyField = new JSpinner();
        sellField = new JSpinner();

        OK = new JButton("OK");
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check()){
                    boolean right = calculate();
                    if(right){
                        int newAmount = total;
                        JOptionPane.showMessageDialog(null,"Чудово! Тепер одиниць на складі: "+newAmount, "Success operation", JOptionPane.INFORMATION_MESSAGE);
                        g.setAmount(newAmount);
                        tradeFrame.dispose(); // close the previous GoodsMenu
                        GoodsMenu.goodsFrame.dispose();
                        GoodsMenu.setGoodsMenu(GoodsMenu.goodsFrame.getBounds()); // open the updated GoodsMenu
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
        buyField.setValue(0);
        sellField.setValue(0);
    }

    private boolean calculate(){
        boolean isLegal = false;

        buy = (int) buyField.getValue();
        sell = (int) sellField.getValue();

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

        isCorrect = ((int) buyField.getValue()) >= 0;
        if(isCorrect){
            isCorrect = ((int) sellField.getValue()) >= 0;
        }
        return isCorrect;
    }

}
