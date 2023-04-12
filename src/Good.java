import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Good {

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

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
                "\nОпис: " + description +
                "\nВиробник: " + producer +
                "\nЦіна за одиницю: " + price +
                "\nКількість на складі: " + amount +
                "\nГрупа товару: "+Shop.groupArray.get(groupNum-1);
    }
}
