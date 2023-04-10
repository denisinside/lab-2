import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Shop {
    protected static ArrayList<Group> groupArray;

    protected static ArrayList<Good> goodsArray;

    public Shop() {
            setData();
        MainMenu.setMainMenu();

    }

    public static void setData()  {
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("goods.naukma")));
            goodsArray = (ArrayList<Good>) ois.readObject();
            ois.close();
            ObjectInputStream ois1 = new ObjectInputStream(Files.newInputStream(Paths.get("groups.naukma")));
            groupArray = (ArrayList<Group>) ois1.readObject();
            ois1.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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

