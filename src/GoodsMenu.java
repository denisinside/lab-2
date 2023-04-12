import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

public class GoodsMenu {

    public static JFrame goodsFrame;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenDimension = tk.getScreenSize();
    private static JButton add, edit, delete, searchButton, back, filterButton;
    private static JTextField searchField;
    private static JPopupMenu filterPopup;
    private static JMenuItem dateFilter, alphabetFilter, costFilter, valueFilter;
    private static String currentFilter = "дата";
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
        logoPanel.setBackground(Color.decode("#BBB1FA"));
        JLabel logo = new JLabel("Вибір Товарів");
        logo.setForeground(Color.decode("#6358AD"));
        logo.setFont(new Font("Impact", Font.BOLD, 40));
        logoPanel.add(logo);
        frameTop.add(logoPanel);


        // НИЖНЯ ПАНЕЛЬ З ПОШУКОМ І КНОПКАМИ //
        JPanel searchPanel = new JPanel(new GridLayout(1,1));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,3));
        searchField = new JTextField("Пошук...");
        searchPanel.add(searchField);
        searchButton = new JButton("Шукати");
        searchButton.addActionListener(startSearch);
        buttonsPanel.add(searchButton);
        back = new JButton("Назад");

        filterButton = new JButton("Фільтер");
        buttonsPanel.add(filterButton);
        filterButton.addActionListener(e -> {
            Point buttonLocationOnScreen = filterButton.getLocationOnScreen();
            filterPopup.setLocation(buttonLocationOnScreen.x, buttonLocationOnScreen.y);
            filterPopup.setVisible(true);
        });
        filterPopup = new JPopupMenu();
        dateFilter = new JMenuItem("За часом додавання");
        dateFilter.addActionListener(e -> {
            currentFilter = "дата";
            useFilter();
            filterPopup.setVisible(false);
        });
        alphabetFilter = new JMenuItem("За алфавітом");
        alphabetFilter.addActionListener(e -> {
            currentFilter = "алфавіт";
            useFilter();
            filterPopup.setVisible(false);
        });
        costFilter = new JMenuItem("За ціною");
        costFilter.addActionListener(e -> {
            currentFilter = "ціна";
            useFilter();
            filterPopup.setVisible(false);
        });
        valueFilter = new JMenuItem("За заг. вартістю");
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
        middlePanel.setBackground(Color.decode("#FAF0B2"));
        frameMiddleWrapper.setBackground(Color.decode("#FAF0B2"));

        JScrollPane frameMiddleScroll = new JScrollPane(frameMiddleWrapper);
        frameMiddleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frameMiddleScroll.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight() / 8 * 6));

        // НИЖНЯ ЧАСТИНА З КНОПКАМИ "ВИДАЛИТИ І ДОДАТИ" //
        JPanel frameBottom = new JPanel(new GridLayout(1,2));
        frameBottom.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/16));
        add = new JButton("Додати");
        add.addActionListener(e -> {
            EditOrAddGood editOrAddGood = new EditOrAddGood();
            editOrAddGood.setEditMenu(new Good("","","",0,0,""),"Додавання");
        });
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

    private static void useFilter(){
        ArrayList<GoodPanel> usingFilter = new ArrayList<>(goodPanels);
        if (currentFilter.equals("алфавіт")) usingFilter.sort((s1, s2) -> {
            Collator collator = Collator.getInstance(new Locale("uk"));
            return collator.compare(s1.good.name, s2.good.name);
        });
        if (currentFilter.equals("ціна")) usingFilter.sort((e1,e2) -> Integer.compare(e2.good.price,e1.good.price));
        if (currentFilter.equals("вартість")) usingFilter.sort((e1,e2) -> Integer.compare(e2.good.getProductTypeValue(),e1.good.getProductTypeValue()));
        Iterator<GoodPanel> goodPanelIterator = usingFilter.iterator();
        middlePanel.removeAll();
        while (goodPanelIterator.hasNext()){
                middlePanel.add(goodPanelIterator.next());
        }
        middlePanel.revalidate();
        middlePanel.repaint();
    }

//************************ ВНУТРІШНІЙ КЛАС ПАНЕЛІ ТОВАРІВ ************************* //

    private static class GoodPanel extends JPanel {
        private Good good;
        private JButton edit;
        private JCheckBox toDelete;
        private JButton buySell;

        public GoodPanel(Good good) {
            setLayout(new BorderLayout());
            this.good = good;
            setBackground(Color.decode("#ADA46A"));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setPreferredSize(new Dimension(goodsFrame.getWidth()/5,goodsFrame.getHeight()/3));
            add(good.image, BorderLayout.NORTH);

            JTextArea goodDescription = new JTextArea(good.toStringPanel());
            goodDescription.setEditable(false);
            goodDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            JScrollPane description = new JScrollPane(goodDescription);
            add(description, BorderLayout.CENTER);

            JPanel buttons = new JPanel(new BorderLayout());
            edit = new JButton("Редагувати");

            JPanel buttonPanel = new JPanel( new BorderLayout());
            buttonPanel.setBackground(Color.decode("#FAF0B2"));

            edit = new JButton("Редагувати");
            edit.addActionListener(e -> {
                EditOrAddGood editOrAddGood = new EditOrAddGood();
                editOrAddGood.setEditMenu(good,"Редагування");
            });
            buySell = new JButton("Купівля/Продаж");
            toDelete = new JCheckBox();

            buttonPanel.add(edit, BorderLayout.WEST);
            buttonPanel.add(buySell, BorderLayout.CENTER);
            buttonPanel.add(toDelete, BorderLayout.EAST);

            buttons.add(buttonPanel, BorderLayout.CENTER);
            add(buttons, BorderLayout.SOUTH);
            add(buttons, BorderLayout.SOUTH);

        }
        public Good getGood() {
            return good;
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