
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Property {

    PropertyType type;
    double price;
    double landSize;
    int numOfRooms;
    int numOfBathrooms;
    public static Comparator<Property> comparator = Comparator.comparing(Property::getPrice)
            .reversed();

    Property(PropertyType type, double price, double landSize, int numOfRooms, int numOfBathrooms) {
        this.type = type;
        this.price = price;
        this.landSize = landSize;
        this.numOfRooms = numOfRooms;
        this.numOfBathrooms = numOfBathrooms;
    }

    public double getPrice() {
        return this.price;
    }

    public double getLandSize() {
        return this.landSize;
    }

    public int getNumOfRooms() {
        return this.numOfRooms;
    }

    public int getNumOfBathrooms() {
        return this.numOfBathrooms;
    }

    public String toString() {
        return this.type + " price: " + this.price + " rooms: " + this.numOfRooms;
    }

}

enum PropertyType {
    APARTMENT, HOUSE;
}

class AgentInventory {

    Map<Transaction, List<Property>> inventory;

    public AgentInventory() {
        this.inventory = new HashMap<>();
    }

    public void addForSale(List<Property> p) {
        this.inventory.put(Transaction.SALE, p);
    }

    public void addForRent(List<Property> p) {
        this.inventory.put(Transaction.RENT, p);
    }

    public void sortPropertiesForRent() {
        Comparator<Property> comparator = Comparator.comparing(Property::getPrice)
                .thenComparing(Property::getNumOfRooms);
                Collections.sort(this.inventory.get(Transaction.RENT), comparator);
    }

    public void sortPropertiesForSale() {
        Comparator<Property> comparator = Comparator.comparing(Property::getPrice).reversed()
                .thenComparing(Property::getLandSize).reversed()
                .thenComparing(Property::getNumOfBathrooms).reversed();
        Collections.sort(this.inventory.get(Transaction.SALE), comparator);
    }

}

enum Transaction {
    SALE, RENT;
}

class TestProperty {

    public static void main(String[] args) {
        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property(PropertyType.APARTMENT, 100, 30, 5, 1));
        properties.add(new Property(PropertyType.HOUSE, 2000, 100, 20, 5));
        properties.add(new Property(PropertyType.APARTMENT, 1, 1, 1, 1));
        properties.add(new Property(PropertyType.HOUSE, 69, 42, 12, 9));
        System.out.println(properties);
        Collections.sort(properties, Property.comparator);
        System.out.println("AFter SORTING: \n" + properties);
    }
}
