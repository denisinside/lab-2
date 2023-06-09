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
    String groupName;
    JLabel image;
    static JLabel defaultImg;
    String link1;

    public Good(String name, String description, String producer, int price, int amount, String groupName) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
        this.amount = amount;
        this.groupName = groupName;
        image = new JLabel(defaultImg.getIcon());
    }
    public void setImage(String link) throws MalformedURLException{
        URL url = new URL(link);
        Image img = MainMenu.tk.createImage(url);
        link1 = url.toString();
        Image scaledImage = img.getScaledInstance(150, 120, Image.SCALE_SMOOTH);

        image = new JLabel(new ImageIcon(scaledImage));
    }


    static void setDefaultImage(String link) throws MalformedURLException {
        URL url = new URL(link);
        Image img = MainMenu.tk.createImage(url);
        link=String.valueOf(url);
        Image scaledImage = img.getScaledInstance(150, 120, Image.SCALE_SMOOTH);

        defaultImg = new JLabel(new ImageIcon(scaledImage));
    }
    public int getProductTypeValue(){
        return amount*price;
    }
    @Override
    public String toString() {
        return  name +
                " | " + producer +
                " | " + price +
                "грн | К-cть: " + amount +
                " | Загальна вартість: " + String.format("%,d",getProductTypeValue()) + " грн";
    }
    public String toStringPanel(){
        return  name +
                "\n\nОпис: \n" + description +
                "\n\nВиробник: " + producer +
                "\nЦіна за одиницю: " + price + " грн" +
                "\nКількість на складі: " + amount +
                "\nЗагальна вартість: " + String.format("%,d",getProductTypeValue()) + " грн";

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}