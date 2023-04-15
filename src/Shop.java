import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Shop {
    protected static Image icon;
    protected static ArrayList<Group> groupArray;

    protected static ArrayList<Good> goodsArray;

    public Shop() throws MalformedURLException {
        setData();
        MainMenu.setMainMenu();

    }

    public static void setData()  {
        GroupsMenu.setIcons();

        icon = new ImageIcon("store.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);

        try {
            Good.setDefaultImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3eUqe0gzy74YsDqHf365i8SEldyqxCbmqO0O50o1G1rtzCqSpV3CxDaD6Cv3FQr6Fl9g&usqp=CAU");
        }catch (MalformedURLException ignored){}
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("goods.naukma")));
            goodsArray = (ArrayList<Good>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            try {
                Files.createFile(Paths.get("goods.naukma"));
                goodsArray = new ArrayList<>();

            } catch (IOException ignored) {}
        }
        try {
            ObjectInputStream ois1 = new ObjectInputStream(Files.newInputStream(Paths.get("groups.naukma")));
            groupArray = (ArrayList<Group>) ois1.readObject();
            ois1.close();
        } catch (IOException | ClassNotFoundException e) {
            try {
                Files.createFile(Paths.get("groups.naukma"));
                groupArray = new ArrayList<>();
            } catch (IOException ignored) {}
        }
    }


}
class ShopWindowListener implements WindowListener {
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("goods.naukma")));
            oos.writeObject(Shop.goodsArray);
            oos.close();
        } catch (IOException ignored) {}
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("groups.naukma")));
            oos.writeObject(Shop.groupArray);
            oos.close();
        } catch (IOException ignored) {}

    }
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
