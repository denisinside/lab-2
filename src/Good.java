public class Good {
    
    // Object fields
    String name;
    String description;
    String producer;
    int  price;
    int amount;
    short groupNum;
    String groupName;

    public Good(String name, String description, String producer, int price, int amount, short groupNum, String groupName) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
        this.amount = amount;
        this.groupNum = groupNum;
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Назва: " + name +
                ", Опис: " + description +
                ", Виробник: " + producer +
                ", Ціна за одиницю: " + price +
                ", Кількість на складі: " + amount;
    }
}
