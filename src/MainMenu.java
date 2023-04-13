
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
        //mainFrame.addWindowListener(new ShopWindowListener());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(252, 233, 174));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));

        mainFrame.setBounds(screenDimension.width/5,screenDimension.height/10,1000,800);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.PINK);
        JLabel logo = new JLabel("STORE SIMULATOR");
        logo.setForeground(Color.BLACK);
        logo.setFont(new Font("Arial Black", Font.BOLD, 50));
        logoPanel.add(logo);

        // КНОПКИ ГОЛОВНОГО МЕНЮ //
        JButton goodsButton = new JButton("Товари");
        JButton groupsButton = new JButton("Групи товарів");
        goodsButton.setPreferredSize(new Dimension(mainFrame.getWidth()/2,mainFrame.getHeight()/8));
        groupsButton.setPreferredSize(new Dimension(mainFrame.getWidth()/2,mainFrame.getHeight()/8));

        // ДОДАВАННЯ КНОПОК ДО ПАНЕЛІ КНОПОК //
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150,50,80,50));
        buttonPanel.setBackground(new Color(252, 233, 174));
        buttonPanel.add(goodsButton, BorderLayout.NORTH);
        buttonPanel.add(new JPanel(){{
            setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
            setBackground(new Color(252, 233, 174));
        }}, BorderLayout.CENTER);
        buttonPanel.add(groupsButton, BorderLayout.SOUTH);
        goodsButton.addActionListener(goToGoods);
        groupsButton.addActionListener(goToGroups);

        // ДОДАВАННЯ ОБ'ЄКТІВ ДО ГОЛОВНОЇ ПАНЕЛІ //
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(new JPanel(){{
            setBorder(BorderFactory.createEmptyBorder(30,30,80,30));
            setBackground(Color.PINK);
        }}, BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

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
            mainFrame.setVisible(false);
            GroupsMenu.setGroupsMenu(mainFrame.getBounds());
        }
    };

}