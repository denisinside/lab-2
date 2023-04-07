import java.util.ArrayList;

public class Main {
    protected static ArrayList<Group> groupArray;

    protected static ArrayList<Good> goodsArray;
    public static void main(String[] args) {
        setData();
        MainMenu.setMainMenu();
    }

    public static void setData(){

        groupArray = new ArrayList<>();
        goodsArray = new ArrayList<>();

        Group electronics = new Group("Електроніка", "Електроприлади");
        Group furniture = new Group("Меблі", "Товари для дому");
        Group domestics = new Group("Побутова техніка", "Техніка для побутових справ");

        groupArray.add(electronics);
        groupArray.add(furniture);
        groupArray.add(domestics);

        Good monitors = new Good("Монітори", "", "MEIZU", 24599, 267, (short) 1);
        Good powerbanks = new Good("Павербанки", "", "Hoco", 1599, 6510, (short) 1);
        Good earphones = new Good("Навушники", "", "Apple corp.", 759, 1578, (short) 1);

        Good cupboards = new Good("Шафи", "в", "Меблі Маяк", 16499, 766, (short) 2);
        Good sofas = new Good("Дивани", "", "Веста корп.", 19599, 108, (short)2);
        Good chairs = new Good("Табуретки", "", "Київсбкий стандарт", 699, 3565, (short)2);

        Good microwaves = new Good("Мікрохвильовки", "", "Samsung", 4799, 1477, (short)3);
        Good fridges = new Good("Холодильники", "", "Bosch", 17599, 227, (short)3);
        Good hoovers = new Good("Пилососи", "", "Siemens", 6899, 1222, (short)3);

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
}