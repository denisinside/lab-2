import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Shop {
    protected static ArrayList<Group> groupArray;

    protected static ArrayList<Good> goodsArray;

    public Shop() throws MalformedURLException {
        setData();
        MainMenu.setMainMenu();

    }
    public static void setData1() throws MalformedURLException {
        Good.setDefaultImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3eUqe0gzy74YsDqHf365i8SEldyqxCbmqO0O50o1G1rtzCqSpV3CxDaD6Cv3FQr6Fl9g&usqp=CAU");
        GroupsMenu.setIcons();

        groupArray = new ArrayList<>();
        goodsArray = new ArrayList<>();
        Group electronics = new Group("Електроніка", "Електроприлади");
        Group furniture = new Group("Меблі", "Товари для дому");
        Group domestics = new Group("Побутова техніка", "Товари для побутових справ");

        groupArray.add(electronics);
        groupArray.add(furniture);
        groupArray.add(domestics);

        Good monitors = new Good("Монітори", "Екрани для персональних комп'ютерів.\n"+
                "Роздільна здатність: 3840x2160\n"
                +"Частота оновлення: 144 Гц\n"+
                "Максимальна роздільна здатність: 3840х2160 (4K Ultra HD)", "Samsung", 20999, 267,  electronics.name);
        monitors.setImage("https://cdn.comfy.ua/media/catalog/product/c/2/c27f390_001_front_black_1.jpg");

        Good powerbanks = new Good("Павербанки", "Ємність батареї мА*год: 20000\n" +
                "Вихідні інтерфейси: 2 x USB Type \n" +
                "Вхідні інтерфейси: Lightning, Micro-USB, USB Type C\n" +
                "Вихідна напруга: 5В", "Hoco", 1199, 6510, electronics.name);
        powerbanks.setImage("https://technomarket.biz.ua/8674-thickbox_default/power-bank-hoco-j84-10000mah-.jpg");

        Good earphones = new Good("Навушники", "Тип навушників: TWS (2 окремо)\n" +
                "Тип підключення: Бездротові\n" +
                "Інтерфейс під'єднання: Bluetooth", "Apple corp.", 759, 1578,  electronics.name);
        earphones.setImage("https://c.ua/image/cache/catalog/BlackFriday/111/12345entaaornpl-800x800.jpg");

        Good cupboards = new Good("Шафи", "Гарантійний термін: 5 років\n"+
                "Матеріал виробу: ДСП\n" +
                "Колір каркаса: дуб крафт золотий", "Меблі Маяк", 16499, 766, furniture.name);
        cupboards.setImage("https://cdn.images.fecom-media.com/FE00018317/images/HE1849041_1431004-GLS-CLF-P01.jpg");

        Good sofas = new Good("Дивани", "Гарантія: 18 міс.\n"+
                "Додаткові характеристики: тканина Kilo 6\n"+
                "Матеріали каркаса: фанера, ДВП, ДСП, брус сосновий зрощений", "Веста корп.", 15599, 108, furniture.name);
        sofas.setImage("https://cdn-images.article.com/products/SKU2128/2890x1500/image88982.jpg?fit=max&w=1200&q=100");

        Good chairs = new Good("Табуретки", "Матеріал каркаса: масив дерева\n"+
                "Оббивка: ДСП без оббивки\n"+
                "Транспортувальний стан: зібраний", "Київсбкий стандарт", 699, 3565,  furniture.name);
        chairs.setImage("https://www.ikea.com/us/en/images/products/lerhamn-chair-black-brown-vittaryd-beige__0728160_pe736117_s5.jpg?f=s");

        Good microwaves = new Good("Мікрохвильовки", "Об`єм: 23 л\n"+
                "Потужність СВЧ: 800 Вт\n"+
                "Внутрішнє покриття: Біо-кераміка", "LG corp.", 4799, 1477,  domestics.name);
        microwaves.setImage("https://i5.walmartimages.com/asr/1a6a610e-05cd-496f-a949-9cf9e25e6f45.b9addebb598d81c477605182e969000e.jpeg");

        Good fridges = new Good("Холодильники", "Охолодження холодильної камери: No Frost / Frost free\n"+
                "Тип компресора: Звичайний"+
                "Розморожування морозильної камери: No Frost (Frost Free)", "Bosch", 17599, 227,  domestics.name);
        fridges.setImage("https://cdn.thewirecutter.com/wp-content/media/2021/09/refrigerators-2048px-frigidaire-FFTR2021TS-topfreezer.jpg");

        Good hoovers = new Good("Пилососи", "Тип прибирання: Сухе прибирання\n" +
                "Потужність споживана: 550 Вт\n"+
                "Об'єм пилозбірника: 2 л", "Siemens", 4399, 1222,  domestics.name);
        hoovers.setImage("https://media.hoover.com/i/ttifloorcare/FH14000_ATF_1_Hero?w=1000");

        goodsArray.add(monitors);
        goodsArray.add(powerbanks);
        goodsArray.add(earphones);
        goodsArray.add(cupboards);
        goodsArray.add(sofas);
        goodsArray.add(chairs);
        goodsArray.add(microwaves);
        goodsArray.add(fridges);
        goodsArray.add(hoovers);

    }

    public static void setData()  {
        GroupsMenu.setIcons();
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
