import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GoodsMenu {

    private static JFrame goodsFrame;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenDimension = tk.getScreenSize();
    private static JMenuBar goodMenuBar;
    private static JMenu add, edit, delete;
    private static JTextField searchGoodField;
    private static JLabel functionName;
    private static JTable goodsTable;
    private static String[] parameters;
    private static String[][] data;

    protected static void setGoodsMenu(){
        // НАЛАШТУВАННЯ ВІКНА //
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
        goodsFrame.add(js);
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
