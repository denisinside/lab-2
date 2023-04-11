import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    protected static JFrame mainFrame;
    protected static final Toolkit tk = Toolkit.getDefaultToolkit();
    protected static final Dimension screenDimension = tk.getScreenSize();
    protected static void setMainMenu(){

        mainFrame = new JFrame("Головне меню");
        mainFrame.addWindowListener(new ShopWindowListener());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        JPanel buttonPanel = new JPanel(new GridLayout(6,0,0,20));

        mainFrame.setBounds(screenDimension.width/5,screenDimension.height/7,1200,900);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel mainHint = new JLabel("STORE SIMULATOR");
        mainHint.setHorizontalAlignment(SwingConstants.CENTER);

        // КНОПКИ ГОЛОВНОГО МЕНЮ //
        JButton goodsButton = new JButton("Товари");
        JButton groupsButton = new JButton("Групи товарів");
        JButton searchGoodButton = new JButton("Пошук товарів");
        JButton showDataButton = new JButton("Вивести дані");


        // ДОДАВАННЯ КНОПОК ДО ПАНЕЛІ КНОПОК //
        buttonPanel.add(mainHint);
        buttonPanel.add(goodsButton);
        buttonPanel.add(groupsButton);
        buttonPanel.add(searchGoodButton);
        buttonPanel.add(showDataButton);

        goodsButton.addActionListener(goToGoods);
        groupsButton.addActionListener(goToGroups);
        showDataButton.addActionListener(goToShow);

        // ДОДАВАННЯ ОБ'ЄКТІВ ДО ГОЛОВНОЇ ПАНЕЛІ //
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainPanel.setVisible(true);
        buttonPanel.setVisible(true);

    }

        private static final ActionListener goToGoods = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                GoodsMenu.setGoodsMenu(mainFrame.getBounds());
            }
        };

        private static final ActionListener goToGroups = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                GroupsMenu.setGroupsMenu(mainFrame.getBounds());
            }
        };

    // ШОУ... НЄ БЛЯТЬ ШАПІТО ЙОБАНЕ
    // цирк
        private static final ActionListener goToShow = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMenu sm = new ShowMenu();
                sm.fuck();
            }
        };
}
