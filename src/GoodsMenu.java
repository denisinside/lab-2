import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GoodsMenu {

    private static JFrame goodsFrame;
    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension screenDimension = tk.getScreenSize();
    private static JMenuBar goodMenuBar;
    private static JButton add, edit, delete, search, back;
    private static JTextField searchField;
    private static JTextField searchGoodField;
    private static JLabel functionName;
    private static JTable goodsTable;
    private static String[] parameters;
    private static String[][] data;

    protected static void setGoodsMenu(){
        /*// НАЛАШТУВАННЯ ВІКНА //
        goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.setLayout(new GridLayout(4,0));
        goodsFrame.setBounds(screenDimension.width/4,screenDimension.height/4,1200,900);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goodsFrame.setVisible(true);

        // НАЛАШТУВАННЯ ВЕРХНЬОГО МЕНЮ //
        goodMenuBar = new JMenuBar();
        add = new JMenu("Додати");
        edit = new JMenu("Редагувати");
        delete = new JMenu("Видалити товар");
        goodMenuBar.add(add); goodMenuBar.add(edit); goodMenuBar.add(delete);


        // НАЛАШТУВАННЯ ОПИСУ//
        JPanel functionPanel = new JPanel();
        functionName = new JLabel("Назва функції над товарами"); // переназву...
        functionName.setHorizontalAlignment(SwingConstants.CENTER);
        functionPanel.add(functionName);

        // НАЛАШТУВАННЯ ПОШУКОВОГО ПОЛЯ //
        JPanel searchPanel = new JPanel(new GridLayout(1,2));
        JLabel explore = new JLabel("Пошук");
        searchGoodField = new JTextField();
        searchPanel.add(explore);
        searchPanel.add(searchGoodField);

        parameters = new String[]{"Ім'я", "Опис", "Виробник"};

        goodsTable = new JTable(data,parameters);
        JScrollPane js = new JScrollPane(goodsTable); // зробити, щоб таблиця не рухалась

        goodsFrame.setJMenuBar(goodMenuBar);
        goodsFrame.add(functionPanel);
            goodsFrame.add(searchPanel);
            goodsFrame.add(js); */

        goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.setLayout(new BorderLayout());
        goodsFrame.setBounds(screenDimension.width/4,screenDimension.height/4,1200,900);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel frameTop = new JPanel(new GridBagLayout());
        frameTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        frameTop.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8));
        frameTop.setMaximumSize(new Dimension(Integer.MAX_VALUE, goodsFrame.getHeight()/16));
        JPanel logo = new JPanel();
        logo.setPreferredSize(new Dimension((int)(goodsFrame.getWidth()*0.9), goodsFrame.getHeight()/16));
        logo.setMaximumSize(new Dimension(Integer.MAX_VALUE, goodsFrame.getHeight()/16));
        logo.setBackground(Color.cyan);
        logo.add(new JLabel("Наукма магаз"));

        frameTop.add(logo, getSetup(0,0,5,1));
        searchField = new JTextField("Пошук...");
        searchField.setPreferredSize(new Dimension((int)(goodsFrame.getWidth()/5*2.9), goodsFrame.getHeight()/16));
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, goodsFrame.getHeight()/16));
        frameTop.add(searchField, getSetup(0,1,3,1));
        search = new JButton("Шукати");
        search.setPreferredSize(new Dimension(goodsFrame.getWidth()/5, goodsFrame.getHeight()/16));
        search.setMaximumSize(new Dimension(Integer.MAX_VALUE, goodsFrame.getHeight()/16));
        frameTop.add(search, getSetup(3,1,1,1));
        back = new JButton("Назад");
        back.setPreferredSize(new Dimension(goodsFrame.getWidth()/5, goodsFrame.getHeight()/16));
        back.setMaximumSize(new Dimension(Integer.MAX_VALUE, goodsFrame.getHeight()/16));
        frameTop.add(back, getSetup(4, 1, 1, 1));


        JPanel frameMiddle = new JPanel(new FlowLayout());
        frameMiddle.setBounds(0,goodsFrame.getHeight()/6,goodsFrame.getWidth(), goodsFrame.getHeight()/8*3);
        frameMiddle.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8*3));

        JPanel frameBottom = new JPanel(new GridLayout(1,2));
        frameBottom.setPreferredSize(new Dimension(goodsFrame.getWidth(), goodsFrame.getHeight()/8));
        add = new JButton("Додати");
        delete = new JButton("Видалити");
        frameBottom.add(add);
        frameBottom.add(delete);

        goodsFrame.add(frameTop, BorderLayout.NORTH);
        goodsFrame.add(frameMiddle, BorderLayout.CENTER);
        goodsFrame.add(frameBottom, BorderLayout.SOUTH);
        goodsFrame.setVisible(true);

    }
    private static GridBagConstraints getSetup(int gridx, int gridy, int gridwidth, int gridheight){
        GridBagConstraints gbs = new GridBagConstraints();
        gbs.weightx = 0;
        gbs.weighty = 0;
        gbs.gridx = gridx;
        gbs.gridy = gridy;
        gbs.gridwidth = gridwidth;
        gbs.gridheight = gridheight;
        gbs.fill = GridBagConstraints.BOTH;
        return gbs;
    }
    private static String[][] setParameters(){
        String[][] goodParams = new String[Main.goodsArray.size()][parameters.length];

        String n = "";
        String d = "";
        String p = "";
        String pr = "";
        String q = "";
        String g = "";
        short j = 0;
        for(int i=0; i<Main.goodsArray.size(); i++){

            if(j<parameters.length){
                n = Main.goodsArray.get(i).name;
                d = Main.goodsArray.get(i).description;
                p = Main.goodsArray.get(i).producer;
                pr = String.valueOf(Main.goodsArray.get(i).price);
                q = String.valueOf(Main.goodsArray.get(i).amount);
                g = Main.goodsArray.get(i).groupName;

                goodParams[i][j] = Arrays.toString(new String[]{n + " " + d + " " + p + " " + pr + " " + q + " " + g});
            }
        }
        return goodParams;
    }
}
