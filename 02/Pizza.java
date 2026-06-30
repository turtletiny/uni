
import java.util.ArrayList;

public class Pizza {

  public static void main(String[] args) {
    Pizza cheesePizza = new Pizza(10, new Topping[] { Topping.MOZZARELLA_CHEESE });
    cheesePizza.addTopping(Topping.GROUND_BEEF);
    cheesePizza.addTopping(Topping.RASHER_BACON);
    System.out.println(cheesePizza.calculatePrice());
    Menu menu = new Menu();
    menu.printMenu();

  }

  final double BASE_PRICE;
  final Topping[] includedToppings;
  ArrayList<Topping> extraToppings;

  Pizza(double basePrice, Topping[] includedToppings) {
    this.BASE_PRICE = basePrice;
    this.includedToppings = includedToppings;
    this.extraToppings = new ArrayList<>();
  }

  void addTopping(Topping topping) {
    this.extraToppings.add(topping);
  }

  double calculatePrice() {
    double price = this.BASE_PRICE;
    for (Topping t : this.includedToppings) {
      price += t.price;
    }
    for (Topping t : this.extraToppings) {
      price += t.price;
    }
    return price;
  }

  double applyDiscount(double discountRate) {
    return this.calculatePrice() * (1 - discountRate);
  }

  double applyDiscount(int discount) {
    return this.calculatePrice() - discount;
  }

  public String toString(){
    String str = "";
    str += "$";
    str += this.BASE_PRICE;
    for (Topping t : this.includedToppings){
      str += t;
      str += ", ";
    }
    return str;
  }
}


enum Topping {
  ANCHOVIES(2.25), RASHER_BACON(2.25), GROUND_BEEF(2.25), SEASONED_CHICKEN(2.25),
  CAMEMBERT_CHEESE(3.25), CAPSICUM(1.75), MOZZARELLA_CHEESE(2.25), HAM(2.25), PINEAPPLE(2.25);

  double price;

  Topping(double price) {
    this.price = price;
  }
}

class Menu {

  ArrayList<Pizza> premadePizzas;

  Menu() {
    this.premadePizzas = new ArrayList<>();
    Pizza hawaianPizza = new Pizza(10, new Topping[] { Topping.HAM, Topping.MOZZARELLA_CHEESE, Topping.PINEAPPLE });
    Pizza meatloversPizza = new Pizza(10,
        new Topping[] { Topping.GROUND_BEEF, Topping.RASHER_BACON, Topping.MOZZARELLA_CHEESE });
    premadePizzas.add(hawaianPizza);
    premadePizzas.add(meatloversPizza);
  }

  public void printMenu() {
    System.out.println("-Menu-");
    for (Pizza p : this.premadePizzas) {
      System.out.println(p);
    }
  }
}
