import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class GoodsMenu {

    public static JFrame goodsFrame;
    private static JButton filterButton;
    private static JTextField searchField;
    private static JPopupMenu filterPopup;
    private static String currentFilter = "дата";
    private static ArrayList<GoodPanel> goodPanels, goodsPanelUsingFilter;
    private static JPanel middlePanel;
    protected static void setGoodsMenu(Rectangle bounds){

        // НАЛАШТУВАННЯ ВІКНА //
        goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.addWindowListener(new ShopWindowListener());
        goodsFrame.setLayout(new BorderLayout());
        goodsFrame.setBounds(bounds);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goodsFrame.setIconImage(Shop.icon);

        // ВЕРХНЯ ЧАСТИНА //
        JPanel panelTop = new JPanel(new GridLayout(2,0));
        panelTop.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8));
        panelTop.setBorder(new LineBorder(Color.CYAN, 4));

        // ЗАГОЛОВК МЕНЮ //
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.decode("#BBB1FA"));
        JLabel logo = new JLabel("Вибір Товарів");
        logo.setForeground(Color.decode("#6358AD"));
        logo.setFont(new Font("Georgia", Font.BOLD, 40));

        logoPanel.add(logo);
        panelTop.add(logoPanel);


        // ПАНЕЛЬ З ПОШУКОМ І КНОПКАМИ //
        JPanel searchPanel = new JPanel(new GridLayout(1,2));
        JPanel explorePanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new GridLayout(1,3));
        JPanel aPanel = new JPanel(new FlowLayout());

        ImageIcon i = new ImageIcon("search.jpg");
        Image im = i.getImage();
        im = im.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        i = new ImageIcon(im);

        JLabel l = new JLabel();
        l.setSize(50,50);
        l.setIcon(i);

        searchField = new JTextField("Пошук...");
        aPanel.add(l);
        explorePanel.add(aPanel,BorderLayout.WEST);
        explorePanel.add(searchField, BorderLayout.CENTER);

        JButton searchButton = new JButton("Шукати");
        searchButton.addActionListener(startSearch);
        buttonsPanel.add(searchButton);
        JButton back = new JButton("Назад");

        filterButton = new JButton("Фільтр");
        buttonsPanel.add(filterButton);
        filterButton.addActionListener(e -> {
            Point buttonLocationOnScreen = filterButton.getLocationOnScreen();
            filterPopup.setLocation(buttonLocationOnScreen.x, buttonLocationOnScreen.y);
            filterPopup.setVisible(true);
        });
        filterPopup = new JPopupMenu();
        JMenuItem dateFilter = new JMenuItem("За часом додавання");
        dateFilter.addActionListener(e -> {
            currentFilter = "дата";
            useFilter();
            filterPopup.setVisible(false);
        });
        JMenuItem alphabetFilter = new JMenuItem("За алфавітом");
        alphabetFilter.addActionListener(e -> {
            currentFilter = "алфавіт";
            useFilter();
            filterPopup.setVisible(false);
        });
        JMenuItem costFilter = new JMenuItem("За ціною");
        costFilter.addActionListener(e -> {
            currentFilter = "ціна";
            useFilter();
            filterPopup.setVisible(false);
        });
        JMenuItem valueFilter = new JMenuItem("За заг. вартістю");
        valueFilter.addActionListener(e -> {
            currentFilter = "вартість";
            useFilter();
            filterPopup.setVisible(false);
        });

        filterPopup.add(dateFilter);
        filterPopup.add(alphabetFilter);
        filterPopup.add(costFilter);
        filterPopup.add(valueFilter);

        searchButton.addActionListener(startSearch);

        back.addActionListener(goToMenu);
        buttonsPanel.add(back);
        searchPanel.add(explorePanel);
        searchPanel.add(buttonsPanel);
        panelTop.add(searchPanel);

        // СЕРЕДНЯ ЧАСТИНА З ТОВАРАМИ //
        int goodPanelWidth = goodsFrame.getWidth() / 5;
        int numColumns = goodsFrame.getWidth() / (goodPanelWidth + 20); // Add 20 for spacing

        middlePanel = new JPanel(new GridLayout(0, numColumns, 20, 20));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        middlePanel.setBackground(Color.decode("#FAF0B2"));

        goodPanels = new ArrayList<>();
        for (Good good : Shop.goodsArray){
            GoodPanel g = new GoodPanel(good);
            middlePanel.add(g);
            goodPanels.add(g);
        }

        JPanel panelMiddleWrapper = new JPanel(new BorderLayout());
        panelMiddleWrapper.add(middlePanel, BorderLayout.NORTH);
        panelMiddleWrapper.setBackground(Color.decode("#FAF0B2"));

        // ДОДАВАННЯ СКРОЛУ ДЛЯ СЕРЕДНЬОЇ ПАНЕЛІ //
        JScrollPane frameMiddleScroll = new JScrollPane(panelMiddleWrapper);
        frameMiddleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frameMiddleScroll.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight() / 8 * 6));

        // НИЖНЯ ЧАСТИНА З КНОПКАМИ "ВИДАЛИТИ І ДОДАТИ" //
        JPanel frameBottom = new JPanel(new GridLayout(1,2));
        frameBottom.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/16));
        frameBottom.setBorder(new LineBorder(Color.black, 4));
        JButton add = new JButton("Додати");
        add.addActionListener(e -> {
            if (Shop.groupArray.size() != 0) {
                EditOrAddGood editOrAddGood = new EditOrAddGood();
                editOrAddGood.setEditMenu(new Good("", "", "", 0, 0, ""), "Додавання");
            }
        });
        JButton delete = new JButton("Видалити");
        delete.addActionListener(deleteSelectedGoods);
        frameBottom.add(add);
        frameBottom.add(delete);

        // ДОДАВАННЯ ПАНЕЛЕЙ ДО ВІКНА //
        goodsFrame.add(panelTop, BorderLayout.NORTH);
        goodsFrame.add(frameMiddleScroll, BorderLayout.CENTER);
        goodsFrame.add(frameBottom, BorderLayout.SOUTH);
        goodsFrame.setVisible(true);
        goodsPanelUsingFilter = new ArrayList<>(goodPanels);
    }

    private static void useFilter(){
        if (currentFilter.equals("дата"))goodsPanelUsingFilter = new ArrayList<>(goodPanels);
        if (currentFilter.equals("алфавіт")) goodsPanelUsingFilter.sort((s1, s2) -> {
            Collator collator = Collator.getInstance(new Locale("uk"));
            return collator.compare(s1.good.name, s2.good.name);
        });
        if (currentFilter.equals("ціна")) goodsPanelUsingFilter.sort((e1,e2) -> Integer.compare(e2.good.price,e1.good.price));
        if (currentFilter.equals("вартість")) goodsPanelUsingFilter.sort((e1,e2) -> Integer.compare(e2.good.getProductTypeValue(),e1.good.getProductTypeValue()));
        Iterator<GoodPanel> goodPanelIterator = goodsPanelUsingFilter.iterator();
        middlePanel.removeAll();
        while (goodPanelIterator.hasNext()){
                middlePanel.add(goodPanelIterator.next());
        }
        middlePanel.revalidate();
        middlePanel.repaint();
    }

//************************ ВНУТРІШНІЙ КЛАС ПАНЕЛІ ТОВАРІВ ************************* //

    static class GoodPanel extends JPanel {
        private static int panelCounter = 0;
        private final int panelId;
        private final Good good;
        private final JCheckBox toDelete;

        public GoodPanel(Good good) {
            setLayout(new BorderLayout());
            this.good = good;
            setBackground(Color.GRAY);
            setBorder(new LineBorder(Color.ORANGE,3));
            setPreferredSize(new Dimension(goodsFrame.getWidth()/5,goodsFrame.getHeight()/3));
            setBackground(Color.decode("#ADA46A"));
            add(good.image, BorderLayout.NORTH);

            JTextArea goodDescription = new JTextArea(good.toStringPanel());
            goodDescription.setEditable(false);
            goodDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            JScrollPane description = new JScrollPane(goodDescription);
            add(description, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel( new BorderLayout());
            buttonPanel.setBackground(Color.decode("#ADA46A"));
            JPanel buttons = new JPanel(new BorderLayout());
            JButton edit = new JButton("Редагувати");
            edit.addActionListener(e -> {
                EditOrAddGood editOrAddGood = new EditOrAddGood();
                editOrAddGood.setEditMenu(good,"Редагування");
            });

            JButton buySell = new JButton("Купівля/Продаж");
            buySell.addActionListener(tradeSelected);

            toDelete = new JCheckBox();

            buttonPanel.add(edit, BorderLayout.WEST);
            buttonPanel.add(buySell, BorderLayout.CENTER);
            buttonPanel.add(toDelete, BorderLayout.EAST);

            buttons.add(buttonPanel, BorderLayout.CENTER);
            add(buttons, BorderLayout.SOUTH);

            this.panelId = panelCounter++;
            edit.setActionCommand("edit_" + this.panelId);
            buySell.setActionCommand("trade_"+this.panelId);
        }
        public Good getGood() {
            return good;
        }
        public int getPanelId() {
            return panelId;
        }
        public boolean isToDeleteSelected() {
            return toDelete.isSelected();
        }
    }


// ********************************************************************************* //

    private static final ActionListener startSearch = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searched = searchField.getText();
            if (searched.equals("")) goodsPanelUsingFilter = new ArrayList<>(goodPanels);
            Iterator<GoodPanel> goodPanelIterator = goodsPanelUsingFilter.iterator();
            middlePanel.removeAll();
            while (goodPanelIterator.hasNext()){
                GoodPanel gp = goodPanelIterator.next();
                if (gp.good.name.toLowerCase().contains(searched.toLowerCase())){
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
            Iterator<GoodPanel> goodPanelIterator = goodsPanelUsingFilter.iterator();
            while (goodPanelIterator.hasNext()){
                GoodPanel gp = goodPanelIterator.next();
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
    private static final ActionListener tradeSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String act = e.getActionCommand();
            String[] commandParts = act.split("_");
            int panelId = Integer.parseInt(commandParts[1]);

            for (GoodPanel goodPanel : goodPanels) {
                if (goodPanel.getPanelId() == panelId) {
                    Trader tr = new Trader();
                    tr.setTradeMenu(goodPanel.getGood());
                    break;
                }
            }
        }
    };
}