public class Menu {
    void runMainMenu() {
        System.out.println("--- Main menu ---");
        while (true) {
            System.out.println("Select an option:");
            System.out.println("  1. Sub-menu A");
            System.out.println("  2. Sub-menu B");
            System.out.println("  3. Exit");
            int choice = In.nextInt();
            if (choice == 1) {
                runSubMenuA();
            } else if (choice == 2) {
                runSubMenuB();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Pick an option 1, 2, or 3");
            }
        }

        System.out.println("Exiting...");
    }

    void runSubMenuA() {
        System.out.println("--- Sub-menu A ---");
        while (true) {
            System.out.println("Select an option:");
            System.out.println("  1. Do A1");
            System.out.println("  2. Do A2");
            System.out.println("  3. Do A3");
            System.out.println("  4. Back to main menu");
            int choice = In.nextInt();
            if (choice == 1) {
                System.out.println("  A1!!!");
            } else if (choice == 2) {
                System.out.println("  A2!!!");
            } else if (choice == 3) {
                System.out.println("  A3!!!");
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Pick an option 1, 2, 3 or 4");
            }
        }
        System.out.println("Back to main menu...");
    }

    void runSubMenuB() {
        System.out.println("--- Sub-menu B ---");
        while (true) {
            System.out.println("Select an option:");
            System.out.println("  1. Do B1");
            System.out.println("  2. Do B2");
            System.out.println("  3. Back to main menu");
            int choice = In.nextInt();
            if (choice == 1) {
                System.out.println("  B1!!!");
            } else if (choice == 2) {
                System.out.println("  B2!!!");
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Pick an option 1, 2, or 3");
            }
        }
        System.out.println("Back to main menu...");
    }


    public static void main(String[] args) {
        Menu menu = new Menu();

        menu.runMainMenu();
    }
}
