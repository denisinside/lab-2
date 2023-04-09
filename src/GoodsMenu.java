import javax.swing.*;
import java.awt.*;

public class GoodsMenu {

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenDimension = tk.getScreenSize();
    private static JMenuBar goodMenuBar;
    private static JMenu add, edit, delete;
    JTextField searchGoodField;
    JTextArea goodsArea;
    protected static void setGoodsMenu(){
        JFrame goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.setBounds(screenDimension.width/4,screenDimension.height/4,1200,900);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goodsFrame.setVisible(true);

        goodMenuBar = new JMenuBar();
        add = new JMenu("Додати");
        edit = new JMenu("Редагувати");
        delete = new JMenu("Видалити товар");
    }
}
