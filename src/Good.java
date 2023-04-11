import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class Good implements Serializable {
    
    // Object fields
    String name;
    String description;
    String producer;
    int  price;
    int amount;
    short groupNum;
    String groupName;
    JLabel image;
   static JLabel defaultImg;

    public Good(String name, String description, String producer, int price, int amount, short groupNum, String groupName) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
        this.amount = amount;
        this.groupNum = groupNum;
        this.groupName = groupName;
        image = new JLabel(defaultImg.getIcon());
    }
    public void setImage(String link) throws MalformedURLException{
        URL url = new URL(link);
        Image img = MainMenu.tk.createImage(url);

        Image scaledImage = img.getScaledInstance(150, 120, Image.SCALE_SMOOTH);

        image = new JLabel(new ImageIcon(scaledImage));
    }
    static void setDefaultImage(String link) throws MalformedURLException {
        URL url = new URL(link);
        Image img = MainMenu.tk.createImage(url);

        Image scaledImage = img.getScaledInstance(150, 120, Image.SCALE_SMOOTH);

        defaultImg = new JLabel(new ImageIcon(scaledImage));
    }
    @Override
    public String toString() {
        return  name +
                "\n Опис: " + description +
                "\n Виробник: " + producer +
                "\n Ціна за одиницю: " + price +
                "\n Кількість на складі: " + amount;
    }
}
