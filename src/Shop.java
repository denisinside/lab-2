import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Shop {
    protected static ArrayList<Group> groupArray;

    protected static ArrayList<Good> goodsArray;

    public Shop(){
        try {
        setData();
    }catch (MalformedURLException ignored){};
        MainMenu.setMainMenu();

    }

    public static void setData() throws MalformedURLException {
            Good.setDefaultImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3eUqe0gzy74YsDqHf365i8SEldyqxCbmqO0O50o1G1rtzCqSpV3CxDaD6Cv3FQr6Fl9g&usqp=CAU");

        groupArray = new ArrayList<>();
        goodsArray = new ArrayList<>();

        Group electronics = new Group("Електроніка", "Електроприлади");
        Group furniture = new Group("Меблі", "Товари для дому");
        Group domestics = new Group("Побутова техніка", "Техніка для побутових справ");

        groupArray.add(electronics);
        groupArray.add(furniture);
        groupArray.add(domestics);

        Good monitors = new Good("Монітори", "", "MEIZU", 24599, 267, (short) 1 , electronics.name);
        monitors.setImage("https://cdn.comfy.ua/media/catalog/product/c/2/c27f390_001_front_black_1.jpg");
        Good powerbanks = new Good("Павербанки", "", "Hoco", 1599, 6510, (short) 1, electronics.name);
       powerbanks.setImage("https://technomarket.biz.ua/8674-thickbox_default/power-bank-hoco-j84-10000mah-.jpg");
        Good earphones = new Good("Навушники", "", "Apple corp.", 759, 1578, (short) 1, electronics.name);
        earphones.setImage("https://c.ua/image/cache/catalog/BlackFriday/111/12345entaaornpl-800x800.jpg");

        Good cupboards = new Good("Шафи", "в", "Меблі Маяк", 16499, 766, (short) 2, furniture.name);
        cupboards.setImage("https://cdn.images.fecom-media.com/FE00018317/images/HE1849041_1431004-GLS-CLF-P01.jpg");
        Good sofas = new Good("Дивани", "", "Веста корп.", 19599, 108, (short)2, furniture.name);
        sofas.setImage("https://cdn-images.article.com/products/SKU2128/2890x1500/image88982.jpg?fit=max&w=1200&q=100");
        Good chairs = new Good("Табуретки", "", "Київсбкий стандарт", 699, 3565, (short)2, furniture.name);
        chairs.setImage("https://www.ikea.com/us/en/images/products/lerhamn-chair-black-brown-vittaryd-beige__0728160_pe736117_s5.jpg?f=s");

        Good microwaves = new Good("Мікрохвильовки", "", "Samsung", 4799, 1477, (short)3, domestics.name);
        microwaves.setImage("https://i5.walmartimages.com/asr/1a6a610e-05cd-496f-a949-9cf9e25e6f45.b9addebb598d81c477605182e969000e.jpeg");
        Good fridges = new Good("Холодильники", "", "Bosch", 17599, 227, (short)3, domestics.name);
        fridges.setImage("https://cdn.thewirecutter.com/wp-content/media/2021/09/refrigerators-2048px-frigidaire-FFTR2021TS-topfreezer.jpg");
        Good hoovers = new Good("Пилососи", "", "Siemens", 6899, 1222, (short)3, domestics.name);
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
}
