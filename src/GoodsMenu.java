import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GoodsMenu {

    public static JFrame goodsFrame;
    private static JMenuBar goodMenuBar;
    private static JButton add, edit, delete, search, back;
    private static JTextField searchField;
    private static ArrayList<GoodPanel> goodPanels;
    private static  JPanel goodListPanel;

    protected static void setGoodsMenu(){
        goodPanels = new ArrayList<>();
        // НАЛАШТУВАННЯ ВІКНА //
        goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.setLayout(new BorderLayout());
        goodsFrame.addWindowListener(new ShopWindowListener());
        goodsFrame.setBounds(MainMenu.screenDimension.width/4,MainMenu.screenDimension.height/4,1200,900);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ВЕРХНЯ ЧАСТИНА //
        JPanel frameTop = new JPanel(new GridLayout(2,0));
        frameTop.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8));

        // ТЕКСТ "НАУКМА МАГАЗ" //
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.cyan);
        JLabel logo = new JLabel("Вибір Товарів");
        logo.setForeground(Color.YELLOW);
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
        buttonsPanel.add(back);
        searchPanel.add(buttonsPanel);
        frameTop.add(searchPanel);

        // СЕРЕДНЯ ЧАСТИНА З ТОВАРАМИ //
        goodListPanel = new JPanel(new FlowLayout());
        int y = Shop.goodsArray.size()%4;
        y = y != 0 ? 1 : 0;
        y = (int) (goodsFrame.getHeight()/8*6*(Shop.goodsArray.size()+y)/8);
        goodListPanel.setPreferredSize(new Dimension(goodsFrame.getWidth(), y));
        goodListPanel.setBackground(new Color(252, 233, 174));
        for (Good good : Shop.goodsArray){
            GoodPanel gp = new GoodPanel(good);
            goodListPanel.add(gp);
            goodPanels.add(gp);
        }

        // НИЖНЯ ЧАСТИНА З КНОПКАМИ "ВИДАЛИТИ І ДОДАТИ" //
        JPanel frameBottom = new JPanel(new GridLayout(1,2));
        frameBottom.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/16));
        add = new JButton("Додати");
        delete = new JButton("Видалити");
        delete.addActionListener(deleteSelectedGoods);
        frameBottom.add(add);
        frameBottom.add(delete);

        // НЕ ПОМОГЛО.... //
        JScrollPane j = new JScrollPane(goodListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        j.setPreferredSize(new Dimension(goodsFrame.getWidth(), Integer.MAX_VALUE));


        // А ЧОМУ НЕ ХЛОПЦЯ( //
        goodsFrame.add(frameTop, BorderLayout.NORTH);
        goodsFrame.add(j, BorderLayout.CENTER);
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
                    goodListPanel.remove(gp);
                }
            }
            goodListPanel.revalidate();
            goodListPanel.repaint();

        }
    };


//    private static String[][] setParameters(){
//        String[][] goodParams = new String[Shop.goodsArray.size()][parameters.length];
//
//        String n = "";
//        String d = "";
//        String p = "";
//        String pr = "";
//        String q = "";
//        String g = "";
//        short j = 0;
//        for(int i=0; i<Shop.goodsArray.size(); i++){
//
//            if(j<parameters.length){
//                n = Shop.goodsArray.get(i).name;
//                d = Shop.goodsArray.get(i).description;
//                p = Shop.goodsArray.get(i).producer;
//                pr = String.valueOf(Shop.goodsArray.get(i).price);
//                q = String.valueOf(Shop.goodsArray.get(i).amount);
//                g = Shop.goodsArray.get(i).groupName;
//
//                goodParams[i][j] = Arrays.toString(new String[]{n + " " + d + " " + p + " " + pr + " " + q + " " + g});
//            }
//        }
//        return goodParams;
//    }
}
