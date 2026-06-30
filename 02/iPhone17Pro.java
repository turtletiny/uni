
public class iPhone17Pro {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Model model = menu.runMenuA();
        Colour colour = menu.runMenuB();
        Storage storage = menu.runMenuC(model);
        iPhone17Pro phone = new iPhone17Pro(model, colour, storage);
        System.out.println("Your new iPhone 17 Pro. Just the way you want it.");
        System.out.println(phone);
        System.out.println("Total Price: A$" + phone.calculatePrice());
    }

    Model model;
    Colour colour;
    Storage storage;

    iPhone17Pro(Model model, Colour colour, Storage storage) {
        this.model = model;
        this.colour = colour;
        this.storage = storage;
    }

    public double calculatePrice() {
        double totalPrice = 0;
        if (this.model == Model.PRO) {
            totalPrice = 1999;
        } else {
            totalPrice = 2199;
        }
        if (this.storage == Storage.GB512) {
            totalPrice += 400;
        } else if (this.storage == Storage.TB1) {
            totalPrice += 800;
        } else if (this.storage == Storage.TB2) {
            totalPrice += 1600;
        }
        return totalPrice;
    }

    public String toString() {
        return "iPhone 17 " + this.model + " " + this.storage + " " + this.colour;
    }
}

enum Model {
    PRO("Pro"), PRO_MAX("Pro Max");

    String name;

    Model(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

enum Colour {
    SILVER("Silver"), COSMIC_ORANGE("Cosmic Orange"), DEEP_BLUE("Deep Blue");

    String name;

    Colour(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

enum Storage {
    GB256("256 GB"), GB512("512 GB"), TB1("1 TB"), TB2("2 TB");

    String name;

    Storage(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class Menu {

    void runMenuA() {
        while (true) {
            System.out.println("Buy iPhone 17 Pro");
            System.out.println("\nModel. Which is best for you?");
            System.out.println("[1] iPhone 17 Pro - From A$1,999");
            System.out.println("[2] iPhone 17 Pro Max - From A$2,199");
            System.out.println("[3] Exit");
            int modelChoice = In.nextInt();
            Model model;
            if (modelChoice == 1) {
                model = Model.PRO;
                runMenuB();
            } else if (modelChoice == 2) {
                model = Model.PRO_MAX;
                runMenuB();
            } else if (modelChoice == 3) {
                break;
            } else {
                System.out.println("Pick an option 1 or 2");
            }

        }
    }

    Colour runMenuB() {
        while (true) {
            System.out.println("Finish. Pick your favourite.");
            System.out.println("[1] Colour - Silver");
            System.out.println("[2] Colour - Cosmic Orange");
            System.out.println("[3] Colour - Deep Blue");
            int colourChoice = In.nextInt();
            if (colourChoice == 1) {
                return Colour.SILVER;
            } else if (colourChoice == 2) {
                return Colour.COSMIC_ORANGE;
            } else if (colourChoice == 3) {
                return Colour.DEEP_BLUE;
            } else {
                System.out.println("Pick an option 1, 2 or 3");
            }
        }
    }

    Storage runMenuC(Model model) {
        while (true) {
            System.out.println("Storage. How much space do you need?");
            System.out.println("[1] 256GB");
            System.out.println("[2] 512GB");
            System.out.println("[3] 1TB");
            if (model == Model.PRO_MAX) {
                System.out.println("[4] 2TB");
            }
            int storageChoice = In.nextInt();
            if (storageChoice == 1) {
                return Storage.GB256;
            } else if (storageChoice == 2) {
                return Storage.GB512;
            } else if (storageChoice == 3) {
                return Storage.TB1;
            } else if (storageChoice == 4 && model == Model.PRO_MAX) {
                return Storage.TB2;
            } else {
                System.out.println("Invalid option");
            }

        }
    }
}
