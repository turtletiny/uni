import java.util.Random;

public class Exercises {
    public static void main(String[] args) {
        // String[] prices = { "Keyboard - $50", "Laptop - $1500", "Phone - $1000" };
        // for (int i = 0; i < prices.length; i++) {
        // System.out.println((i + 1) + ". " + prices[i]);
        // }
        // int choice = In.nextInt();
        // System.out.println("How many?");
        // int count = In.nextInt();
        // int price;
        // if (choice == 1) {
        // price = 50;
        // } else if (choice == 2) {
        // price = 1500;
        // } else if (choice == 3) {
        // price = 1000;
        // } else {
        // price = 0;
        // }
        // System.out.println("Price: $" + price * count);

        // double[] widths = {1.2, 3.7, 5.8, 9.3, 12.6};
        // for (double width : widths){
        // System.out.println(width * width);
        // }

        // for (int i = 20; i > 3; i -= 2 ){
        // System.out.println(i);
        // }
        //
        Random random = new Random();

        int health1 = 100;
        int health2 = 100;
        int count = 1;

        while (health1 > 0 && health2 > 0) {
            // Generate a random integer within a specified range (e.g., 1 to 100)
            int damageTaken = random.nextInt(20) + 1;
            if (count % 2 != 0) {
                health1 -= damageTaken;
                System.out.println("Damage taken: " + damageTaken + ". Current health1: " + health1);
            } else {
                health2 -= damageTaken;
                System.out.println("Damage taken: " + damageTaken + ". Current health2: " + health2);
            }
            count++;
        }
        String winner;
        if (health1 > health2){
            winner = "Player 1";
        } else {
            winner = "Player 2";
        }
        System.out.println("Winner: " + winner);
    }

}
