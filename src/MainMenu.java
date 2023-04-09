import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    protected static JFrame mainFrame;
    protected static Toolkit tk = Toolkit.getDefaultToolkit();
    protected static Dimension screenDimension = tk.getScreenSize();
    protected static void setMainMenu(){

        mainFrame = new JFrame("Головне меню");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(6,0,0,20));

        mainFrame.setBounds(screenDimension.width/4,screenDimension.height/4,900,600);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel mainHint = new JLabel("STORE SIMULATOR");
        mainHint.setHorizontalAlignment(SwingConstants.CENTER);

        // КНОПКИ ГОЛОВНОГО МЕНЮ //
        JButton goodsButton = new JButton("Товари");
        JButton groupsButton = new JButton("Групи товарів");
        JButton searchGoodButton = new JButton("Пошук товарів");
        JButton showDataButton = new JButton("Вивести дані");

        // СТВОРЕННЯ ПАНЕЛЕЙ ДЛЯ ВІДМЕЖУВАННЯ КНОПОК ВІД СТІНОК ВІКНА //
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        // ДОДАВАННЯ КНОПОК ДО ПАНЕЛІ КНОПОК //
        buttonPanel.add(mainHint);
        buttonPanel.add(goodsButton);
        buttonPanel.add(groupsButton);
        buttonPanel.add(searchGoodButton);
        buttonPanel.add(showDataButton);

        goodsButton.addActionListener(goToGoods);

        // ДОДАВАННЯ ОБ'ЄКТІВ ДО ГОЛОВНОЇ ПАНЕЛІ //
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainPanel.setVisible(true);
        buttonPanel.setVisible(true);

    }

        private static final ActionListener goToGoods = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                GoodsMenu.setGoodsMenu();
            }
        };

        private static final ActionListener goToGroups = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        private static final ActionListener goToShow = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
}
