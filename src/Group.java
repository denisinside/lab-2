import java.io.Serializable;

public class Group implements Serializable {
    String name;
    String description;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }
    private int getGoodGroupValue(){
        int sum = 0;
        for (Good good : Shop.goodsArray) if(good.groupName.equals(name))sum += good.getProductTypeValue();
        return sum;
    }
    @Override
    public String toString() {
        return name + "  | " + description  + " | Загальна вартість: " + String.format("%,d", getGoodGroupValue()) + " грн";
    }
}