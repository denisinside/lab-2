import javax.swing.*;
import java.awt.*;

public class GoodsMenu {

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenDimension = tk.getScreenSize();
    protected static void setGoodsMenu(){
        JFrame goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.setBounds(screenDimension.width/4,screenDimension.height/4,900,600);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goodsFrame.setVisible(true);


    }
}
