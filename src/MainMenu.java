import javax.swing.*;
import javax.swing.border.BevelBorder;
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
        mainPanel.setBackground(Color.decode("#6358AD"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));

        mainFrame.setBounds(screenDimension.width/5,screenDimension.height/7,900,800);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("store.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.decode("#BBB1FA"));
        JLabel logo = new JLabel("S T O R E  S I M U L A T O R");
        logo.setForeground(Color.decode("#6358AD"));
        logoPanel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.CYAN, Color.BLACK));
        logo.setIcon(icon);
        logo.setIconTextGap(40);
        logo.setFont(new Font("Lucida Fax", Font.BOLD, 45));
        logoPanel.add(logo);

        // КНОПКИ ГОЛОВНОГО МЕНЮ //
        JButton goodsButton = new JButton("Товари");
        goodsButton.setFont(new Font("Monospaced", Font.BOLD, 25));
        goodsButton.setPreferredSize(new Dimension(mainFrame.getWidth()/2,mainFrame.getHeight()/8));

        JButton groupsButton = new JButton("Групи товарів");
        groupsButton.setFont(new Font("Monospaced", Font.BOLD, 25));
        groupsButton.setPreferredSize(new Dimension(mainFrame.getWidth()/2,mainFrame.getHeight()/8));


        // ДОДАВАННЯ КНОПОК ДО ПАНЕЛІ КНОПОК //
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel east = new JPanel();
        east.setBackground(Color.decode("#FAF0B2"));
        JPanel west = new JPanel();
        west.setBackground(Color.decode("#FAF0B2"));
        east.setBorder(new EmptyBorder(0,0,0,150));
        west.setBorder(new EmptyBorder(0,150,0,0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150,50,80,50));
        buttonPanel.setBackground(Color.decode("#FAF0B2"));
        buttonPanel.add(goodsButton, BorderLayout.NORTH);
        buttonPanel.add(groupsButton, BorderLayout.SOUTH);
        goodsButton.addActionListener(goToGoods);
        groupsButton.addActionListener(goToGroups);

        // ДОДАВАННЯ ОБ'ЄКТІВ ДО ГОЛОВНОЇ ПАНЕЛІ //
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(east, BorderLayout.EAST);
        mainPanel.add(west, BorderLayout.WEST);
        mainPanel.add(new JPanel(){{
            setBackground(Color.decode("#ADA46A"));
            JLabel jl = new JLabel("M a d e   b y   S h v a c h k a   &   M a t i c h y k  co.");
            jl.setFont(new Font("Lucida Fax", Font.BOLD, 25));
            add(jl);

            setBorder(new BevelBorder(BevelBorder.RAISED,Color.CYAN, Color.BLACK));
        }}, BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

    }

    private static final ActionListener goToGoods = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            mainFrame.setVisible(false);
            GoodsMenu.setGoodsMenu(new Rectangle(screenDimension.width/10,screenDimension.height/10, 1300,900));

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

