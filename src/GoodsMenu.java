import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GoodsMenu {

    public static JFrame goodsFrame;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenDimension = tk.getScreenSize();
    private static JButton add, edit, delete, searchButton, back;
    private static JTextField searchField;
    private static ArrayList<GoodPanel> goodPanels;
    private static JPanel middlePanel;
    protected static void setGoodsMenu(Rectangle bounds){

        // НАЛАШТУВАННЯ ВІКНА //
        goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.addWindowListener(new ShopWindowListener());
        goodsFrame.setLayout(new BorderLayout());
        goodsFrame.setBounds(bounds);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ВЕРХНЯ ЧАСТИНА //
        JPanel frameTop = new JPanel(new GridLayout(2,0));
        frameTop.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8));

        // ТЕКСТ "НАУКМА МАГАЗ" //
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.cyan);
        JLabel logo = new JLabel("Вибір Товарів");
        logo.setForeground(Color.BLACK);
        logo.setFont(new Font("Arial Black", Font.BOLD, 32));
        logoPanel.add(logo);
        frameTop.add(logoPanel);

        // НИЖНЯ ПАНЕЛЬ З ПОШУКОМ І КНОПКАМИ //
        JPanel searchPanel = new JPanel(new GridLayout(1,2));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        searchField = new JTextField("Пошук...");
        searchPanel.add(searchField);
        searchButton = new JButton("Шукати");
        searchButton.addActionListener(startSearch);
        buttonsPanel.add(searchButton);
        back = new JButton("Назад");
        back.addActionListener(goToMenu);
        buttonsPanel.add(back);
        searchPanel.add(buttonsPanel);
        frameTop.add(searchPanel);

        // СЕРЕДНЯ ЧАСТИНА З ТОВАРАМИ //

        int goodPanelWidth = goodsFrame.getWidth() / 5;
        int numColumns = goodsFrame.getWidth() / (goodPanelWidth + 20); // Add 20 for spacing

        middlePanel = new JPanel(new GridLayout(0, numColumns, 20, 20));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        goodPanels = new ArrayList<>();
        for (Good good : Shop.goodsArray){
            GoodPanel g = new GoodPanel(good);
            middlePanel.add(g);
            goodPanels.add(g);
        }

        JPanel frameMiddleWrapper = new JPanel(new BorderLayout());
        frameMiddleWrapper.add(middlePanel, BorderLayout.NORTH);
        middlePanel.setBackground(new Color(252, 233, 174));
        frameMiddleWrapper.setBackground(new Color(252, 233, 174));

        JScrollPane frameMiddleScroll = new JScrollPane(frameMiddleWrapper);
        frameMiddleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frameMiddleScroll.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight() / 8 * 6));

        // НИЖНЯ ЧАСТИНА З КНОПКАМИ "ВИДАЛИТИ І ДОДАТИ" //
        JPanel frameBottom = new JPanel(new GridLayout(1,2));
        frameBottom.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/16));
        add = new JButton("Додати");
        delete = new JButton("Видалити");
        delete.addActionListener(deleteSelectedGoods);
        frameBottom.add(add);
        frameBottom.add(delete);

        // А ЧОМУ НЕ ХЛОПЦЯ( //
        goodsFrame.add(frameTop, BorderLayout.NORTH);
        goodsFrame.add(frameMiddleScroll, BorderLayout.CENTER);
        goodsFrame.add(frameBottom, BorderLayout.SOUTH);
        goodsFrame.setVisible(true);

    }
    private static class GoodPanel extends JPanel {
        private Good good;
        private JButton edit;
        private JCheckBox toDelete;

        public GoodPanel(Good good) {
            setLayout(new BorderLayout());
            this.good = good;
            setBackground(Color.GRAY);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setPreferredSize(new Dimension(goodsFrame.getWidth()/5,goodsFrame.getHeight()/3));
            add(good.image, BorderLayout.NORTH);

            JTextArea goodDescription = new JTextArea(good.toString());
            goodDescription.setEditable(false);
            goodDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            JScrollPane description = new JScrollPane(goodDescription);
            add(description, BorderLayout.CENTER);

            JPanel buttons = new JPanel(new GridLayout(1,2));
            edit = new JButton("Редагувати");
            buttons.add(edit);
            toDelete = new JCheckBox();
            buttons.add(toDelete);
            add(buttons, BorderLayout.SOUTH);

        }
        public Good getGood() {
            return good;
        }
        public boolean isToDeleteSelected() {
            return toDelete.isSelected();
        }
    }
    private static final ActionListener startSearch = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searched = searchField.getText();
            Iterator<GoodPanel> goodPanelIterator = goodPanels.iterator();
            middlePanel.removeAll();
            while (goodPanelIterator.hasNext()){
                GoodPanel gp = goodPanelIterator.next();
                if (gp.good.name.toLowerCase().startsWith(searched.toLowerCase())){
                    middlePanel.add(gp);
                }
            }
            middlePanel.revalidate();
            middlePanel.repaint();

        }
    };
    private static final ActionListener goToMenu = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            goodsFrame.setVisible(false);
            MainMenu.mainFrame.setVisible(true);
            MainMenu.mainFrame.setBounds(goodsFrame.getBounds());
        }
    };
    private static final ActionListener deleteSelectedGoods = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Iterator<GoodPanel> goodPanelIterator = goodPanels.iterator();
            while (goodPanelIterator.hasNext()){
                GoodPanel gp = goodPanelIterator.next();
                System.out.println(gp.isToDeleteSelected());
                if (gp.isToDeleteSelected()){
                    goodPanelIterator.remove();
                    Shop.goodsArray.remove(gp.getGood());
                    middlePanel.remove(gp);
                }
            }
            middlePanel.revalidate();
            middlePanel.repaint();

        }
    };
}