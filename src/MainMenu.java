import javax.swing.*;
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
        mainPanel.setBackground(new Color(252, 233, 174));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));

        mainFrame.setBounds(screenDimension.width/5,screenDimension.height/7,1200,900);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.decode("#BBB1FA"));
        JLabel logo = new JLabel("S T O R E    S I M U L A T O R");
        logo.setForeground(Color.decode("#6358AD"));
        logo.setFont(new Font("Impact", Font.BOLD, 50));
        logoPanel.add(logo);

        // КНОПКИ ГОЛОВНОГО МЕНЮ //
        JButton goodsButton = new JButton("Товари");
        goodsButton.setFont(new Font("Arial Black", Font.BOLD, 25));
        goodsButton.setPreferredSize(new Dimension(mainFrame.getWidth()/2,mainFrame.getHeight()/8));

        JButton groupsButton = new JButton("Групи товарів");
        groupsButton.setFont(new Font("Arial Black", Font.BOLD, 25));
        groupsButton.setPreferredSize(new Dimension(mainFrame.getWidth()/2,mainFrame.getHeight()/8));


        // ДОДАВАННЯ КНОПОК ДО ПАНЕЛІ КНОПОК //
        JPanel buttonPanel = new JPanel(new GridLayout(2,0,0,70));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150,50,80,50));
        buttonPanel.setBackground(Color.decode("#FAF0B2"));
        buttonPanel.add(goodsButton, BorderLayout.NORTH);
        buttonPanel.add(groupsButton, BorderLayout.SOUTH);
        goodsButton.addActionListener(goToGoods);
        groupsButton.addActionListener(goToGroups);

        // ДОДАВАННЯ ОБ'ЄКТІВ ДО ГОЛОВНОЇ ПАНЕЛІ //
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(new JPanel(){{
            setBorder(BorderFactory.createEmptyBorder(30,30,80,30));
            setBackground(Color.decode("#ADA46A"));
        }}, BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

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

}

