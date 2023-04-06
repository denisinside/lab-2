import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenu {
    protected static void setMainFrame(){
        JFrame mainFrame = new JFrame();
        JPanel buttonPanel = new JPanel(new GridLayout(6,0));

        mainFrame.setSize(500,500);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel mainHint = new JLabel("STORE SIMULATOR");
        mainHint.setBorder(new EmptyBorder(0,190,0,0));

        JButton goodsButton = new JButton("Товари");
        JButton groupsButton = new JButton("Групи товарів");
        JButton searchGoodButton = new JButton("Пошук товарів");
        JButton showDataButton = new JButton("Вивести дані");

        mainFrame.add(buttonPanel);
        buttonPanel.add(mainHint);
        buttonPanel.add(goodsButton);
        buttonPanel.add(groupsButton);
        buttonPanel.add(searchGoodButton);
        buttonPanel.add(showDataButton);

        mainFrame.setVisible(true);
        buttonPanel.setVisible(true);
    }
}
