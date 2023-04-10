import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GoodsMenu {

    private static JFrame goodsFrame;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenDimension = tk.getScreenSize();
    private static JMenuBar goodMenuBar;
    private static JButton add, edit, delete, search, back;
    private static JTextField searchField;
    private static JTextField searchGoodField;
    private static JLabel functionName;
    private static JTable goodsTable;
    private static String[] parameters;
    private static String[][] data;

    protected static void setGoodsMenu(){

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
        JPanel frameMiddle = new JPanel(new FlowLayout());
        frameMiddle.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8*6*Shop.goodsArray.size()/10));
        frameMiddle.setBackground(new Color(252, 233, 174));
        for (Good good : Shop.goodsArray) frameMiddle.add(goodPanel(good));

        // НИЖНЯ ЧАСТИНА З КНОПКАМИ "ВИДАЛИТИ І ДОДАТИ" //
        JPanel frameBottom = new JPanel(new GridLayout(1,2));
        frameBottom.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/16));
        add = new JButton("Додати");
        delete = new JButton("Видалити");
        frameBottom.add(add);
        frameBottom.add(delete);

        // НЕ ПОМОГЛО.... //
        JScrollPane j = new JScrollPane(frameMiddle, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        j.setPreferredSize(new Dimension(goodsFrame.getWidth(), Integer.MAX_VALUE));


        // А ЧОМУ НЕ ХЛОПЦЯ( //
        goodsFrame.add(frameTop, BorderLayout.NORTH);
        goodsFrame.add(j, BorderLayout.CENTER);
        goodsFrame.add(frameBottom, BorderLayout.SOUTH);
        goodsFrame.setVisible(true);

    }
    private static JPanel goodPanel(Good good){
        JPanel goodPanel = new JPanel(new BorderLayout());
        goodPanel.setBackground(Color.GRAY);
        goodPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        goodPanel.setPreferredSize(new Dimension(goodsFrame.getWidth()/5,goodsFrame.getHeight()/3));
        goodPanel.add(good.image, BorderLayout.NORTH);

        JTextArea goodDescription = new JTextArea(good.toString());
        goodDescription.setEditable(false);
        goodDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        JScrollPane description = new JScrollPane(goodDescription);
        goodPanel.add(description, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(1,2));
        edit = new JButton("Редагувати");
        buttons.add(edit);
        JCheckBox toDelete = new JCheckBox();
        buttons.add(toDelete);
        goodPanel.add(buttons, BorderLayout.SOUTH);

        return goodPanel;
    }
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
