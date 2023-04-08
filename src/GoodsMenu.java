import javax.swing.*;

public class GoodsMenu {
    protected static void setGoodsMenu(){
        JFrame goodsFrame = new JFrame("Опції з товарами");
        goodsFrame.setSize(200,200);
        goodsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goodsFrame.setVisible(true);
    }
}
