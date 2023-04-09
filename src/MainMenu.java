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
        JPanel buttonPanel = new JPanel(new GridLayout(6,0));

        mainFrame.setBounds(screenDimension.width/4,screenDimension.height/4,900,600);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel mainHint = new JLabel("STORE SIMULATOR");
        mainHint.setHorizontalAlignment(SwingConstants.CENTER);

        // КНОПКИ ГОЛОВНОГО МЕНЮ //
        JButton goodsButton = new JButton("Товари");
        JPanel a = new JPanel(new GridLayout(1,0));
        a.add(goodsButton);
        a.setBorder(new EmptyBorder(0,10,10,10));

        JButton groupsButton = new JButton("Групи товарів");
        JPanel b = new JPanel(new GridLayout(1,0));
        b.add(groupsButton);
        b.setBorder(new EmptyBorder(0,10,10,10));

        JButton searchGoodButton = new JButton("Пошук товарів");
        JPanel c = new JPanel(new GridLayout(1,0));
        c.add(searchGoodButton);
        c.setBorder(new EmptyBorder(0,10,10,10));

        JButton showDataButton = new JButton("Вивести дані");
        JPanel d = new JPanel(new GridLayout(1,0));
        d.add(showDataButton);
        d.setBorder(new EmptyBorder(0,10,10,10));

        // СТВОРЕННЯ ПАНЕЛЕЙ ДЛЯ ВІДМЕЖУВАННЯ КНОПОК ВІД СТІНОК ВІКНА //
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        // ДОДАВАННЯ КНОПОК ДО ПАНЕЛІ КНОПОК //
        buttonPanel.add(mainHint);
        buttonPanel.add(a);
        buttonPanel.add(b);
        buttonPanel.add(c);
        buttonPanel.add(d);

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



}
