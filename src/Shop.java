import java.net.MalformedURLException;
import java.util.ArrayList;

public class Shop {
    protected static ArrayList<Group> groupArray;

    protected static ArrayList<Good> goodsArray;

    public Shop(){
        setData();
        MainMenu.setMainMenu();

    }

    public static void setData(){
        try {
            Good.setDefaultImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3eUqe0gzy74YsDqHf365i8SEldyqxCbmqO0O50o1G1rtzCqSpV3CxDaD6Cv3FQr6Fl9g&usqp=CAU");
        }catch (MalformedURLException ignored){};
        groupArray = new ArrayList<>();
        goodsArray = new ArrayList<>();

        Group electronics = new Group("Електроніка", "Електроприлади");
        Group furniture = new Group("Меблі", "Товари для дому");
        Group domestics = new Group("Побутова техніка", "Техніка для побутових справ");

        groupArray.add(electronics);
        groupArray.add(furniture);
        groupArray.add(domestics);

        Good monitors = new Good("Монітори", "", "MEIZU", 24599, 267, (short) 1 , electronics.name);
        Good powerbanks = new Good("Павербанки", "", "Hoco", 1599, 6510, (short) 1, electronics.name);
        Good earphones = new Good("Навушники", "", "Apple corp.", 759, 1578, (short) 1, electronics.name);

        Good cupboards = new Good("Шафи", "в", "Меблі Маяк", 16499, 766, (short) 2, furniture.name);
        Good sofas = new Good("Дивани", "", "Веста корп.", 19599, 108, (short)2, furniture.name);
        Good chairs = new Good("Табуретки", "", "Київсбкий стандарт", 699, 3565, (short)2, furniture.name);

        Good microwaves = new Good("Мікрохвильовки", "", "Samsung", 4799, 1477, (short)3, domestics.name);
        Good fridges = new Good("Холодильники", "", "Bosch", 17599, 227, (short)3, domestics.name);
        Good hoovers = new Good("Пилососи", "", "Siemens", 6899, 1222, (short)3, domestics.name);

        goodsArray.add(monitors);
        goodsArray.add(powerbanks);
        goodsArray.add(earphones);
        goodsArray.add(cupboards);
        goodsArray.add(sofas);
        goodsArray.add(chairs);
        goodsArray.add(microwaves);
        goodsArray.add(fridges);
        goodsArray.add(hoovers);
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short) 3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short) 3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
        goodsArray.add(new Good("asd", " ", "sdfsd", 234, 23411, (short)3,domestics.name));
    }
}
