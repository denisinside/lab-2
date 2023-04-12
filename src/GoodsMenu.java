import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GoodsMenu {

    static JFrame goodsFrame;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenDimension = tk.getScreenSize();
    private static JButton add, delete, search, back;
    private static JTextField searchField;
    private static ArrayList<GoodPanel> goodPanels;
    private static JPanel middlePanel;
    private GoodsMenu goodsMenu;
    protected static void setGoodsMenu(){

        // НАЛАШТУВАННЯ ВІКНА //
        goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.setLayout(new BorderLayout());
        goodsFrame.setBounds((int)(MainMenu.screenDimension.width/5.5),MainMenu.screenDimension.height/10,1200,900);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ВЕРХНЯ ЧАСТИНА //
        JPanel frameTop = new JPanel(new GridLayout(2,0));
        frameTop.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8));

        // ТЕКСТ "НАУКМА МАГАЗ" //
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
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
        search = new JButton("Шукати");
        buttonsPanel.add(search);
        back = new JButton("Назад");
        search.addActionListener(startSearch);
        buttonsPanel.add(back);
        searchPanel.add(buttonsPanel);
        frameTop.add(searchPanel);

        // СЕРЕДНЯ ЧАСТИНА З ТОВАРАМИ //

        int goodPanelWidth = goodsFrame.getWidth() / 5;
        int numColumns = goodsFrame.getWidth() / (goodPanelWidth + 20); // Add 20 for spacing

        middlePanel = new JPanel(new GridLayout(0, numColumns, 20, 20));
        middlePanel.setBackground(new Color(252, 233, 174));

        goodPanels = new ArrayList<>();
        for (Good good : Shop.goodsArray){
            GoodPanel g = new GoodPanel(good);
            middlePanel.add(g);
            goodPanels.add(g);
        }

        JPanel frameMiddleWrapper = new JPanel(new BorderLayout());
        frameMiddleWrapper.add(middlePanel, BorderLayout.NORTH);

        JScrollPane frameMiddleScroll = new JScrollPane(frameMiddleWrapper);
        frameMiddleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frameMiddleScroll.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight() / 8 * 6));

        // НИЖНЯ ЧАСТИНА З КНОПКАМИ "ВИДАЛИТИ І ДОДАТИ" //
        JPanel frameBottom = new JPanel(new GridLayout(1,2));
        frameBottom.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/16));
        add = new JButton("Додати");
        delete = new JButton("Видалити");
        add.addActionListener(addGood);
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
        private static int panelCounter = 0;
        private int panelId;
        private Good good;
        private JButton edit;
        private JButton buySell;
        private JCheckBox toDelete;
        private int index=0;

        public GoodPanel(Good good) {
            setLayout(new BorderLayout());
            this.good = good;
            setBackground(Color.GRAY);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setPreferredSize(new Dimension(goodsFrame.getWidth()/5,goodsFrame.getHeight()/3));
            setBackground(new Color(252, 220, 120));
            add(good.image, BorderLayout.NORTH);

            JTextArea goodDescription = new JTextArea(good.toString());
            goodDescription.setEditable(false);
            goodDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            JScrollPane description = new JScrollPane(goodDescription);
            add(description, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel( new BorderLayout());
            buttonPanel.setBackground(new Color(252, 220, 120));
            JPanel buttons = new JPanel(new BorderLayout());
            edit = new JButton("Редагувати");

            edit.addActionListener(editSelected);
            buySell = new JButton("Купівля/Продаж");
            toDelete = new JCheckBox();

            buttonPanel.add(edit, BorderLayout.WEST);
            buttonPanel.add(buySell, BorderLayout.CENTER);
            buttonPanel.add(toDelete, BorderLayout.EAST);

            buttons.add(buttonPanel, BorderLayout.CENTER);
            add(buttons, BorderLayout.SOUTH);

            this.panelId = panelCounter++;
            edit.setActionCommand("edit_" + this.panelId);
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

    private static final ActionListener startSearch = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searched = searchField.getText();
            middlePanel.removeAll();
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

    private static final ActionListener addGood = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddGood ad = new AddGood();
            ad.setAddGood();
        }
    };

    private static final ActionListener editSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            String[] commandParts = actionCommand.split("_");
            int panelId = Integer.parseInt(commandParts[1]);

            for (GoodPanel goodPanel : goodPanels) {
                if (goodPanel.getPanelId() == panelId) {
                    EditGood ed = new EditGood();
                    ed.setEditMenu(goodPanel.getGood());
                    break;
                }
            }
        }
    };
}