
import java.util.ArrayList;
import java.util.Random;

class Passenger {

    private String name, boardingLocation, disembarkLocation;
    boolean isSick;

    public Passenger(String name, String boardingLocation, String disembarkLocation) {
        this.name = name;
        this.boardingLocation = boardingLocation;
        this.disembarkLocation = disembarkLocation;
        this.isSick = false;
    }

    public String getName() {
        return this.name;
    }

    public String getBoardingLocation() {
        return this.boardingLocation;
    }

    public String getDisembarkLocation() {
        return this.disembarkLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoardingLocation(String boardingLocation) {
        this.boardingLocation = boardingLocation;
    }

    public void setDisembarkLocation(String disembarkLocation) {
        this.disembarkLocation = disembarkLocation;
    }

    public void getSick() {
        this.isSick = true;
    }

    public void recover() {
        this.isSick = false;
    }

    public String toString() {
        return "Name: " + this.name + " | Boarding: "
                + this.boardingLocation + " | Disembark: " + this.disembarkLocation;
    }

}

class CruiseShip {

    String shipName;
    int capacity;
    ArrayList<String> itinerary;
    String currentLocation;
    int stopNum;
    ArrayList<Passenger> passengers;

    public CruiseShip(String name, int capacity, String currentLocation) {
        this.shipName = name;
        this.capacity = capacity;
        this.currentLocation = currentLocation;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public CruiseShip(String name, int capacity, ArrayList<String> itinerary) {
        this.shipName = name;
        this.capacity = capacity;
        this.itinerary = itinerary;
        this.currentLocation = itinerary.get(0);
        this.stopNum = 1; // not in instructions
        this.passengers = new ArrayList<>();
    }

    public boolean boardPassenger(Passenger p) {
        if (!this.currentLocation.equals(p.getBoardingLocation())) {
            System.out.println("Incorrect boarding location");
            return false;
        }
        if (this.passengers.size() >= this.capacity) {
            System.out.println("Ship at max capacity");
            return false;
        }
        System.out.println("Welcome aboard " + p.getName());
        this.passengers.add(p);
        return true;
    }

    public void travelToNextStop() {
        if (stopNum < this.itinerary.size()) {
            this.currentLocation = this.itinerary.get(stopNum);
            System.out.println("Now at stop: " + this.currentLocation);
            stopNum++;
            // int i = 0;
            // int n = this.passengers.size();
            // // while (i < n) {
            // //     if (this.passengers.get(i).getDisembarkLocation().equals(this.currentLocation)) {
            // //         System.out.println(this.passengers.get(i).getName() + " disembarks");
            // //         this.passengers.remove(i);
            // //         i--;
            // //         n--;
            // //     }
            // //     i++;
            // // }
            for (int i = this.passengers.size() - 1; i >= 0; i--) {
                if (this.passengers.get(i).getDisembarkLocation().equals(this.currentLocation)) {
                    System.out.println(this.passengers.get(i).getName() + " disembarks");
                    this.passengers.remove(i);
                }
            }
        }
    }

    public void seedInitialCases(int n) {
        Random random = new Random();
        int numOfSick = n;
        int i = 0;
        while (n != 0) {
            if (i > this.passengers.size()) {
                i = 0;
            }
            if (!this.passengers.get(i).isSick) {
                int rand = random.nextInt(1);
                if (rand == 0) {
                    this.passengers.get(i).getSick();
                    n--;
                    i++;
                }
            }
        }
    }

    public void spreadBug() {
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).isSick) {
                Random random = new Random();
                int spreadChance = random.nextInt(10);
                if (spreadChance == 0) {
                    int spreadedPassenger = -1;

                    while (spreadedPassenger != i) {
                        spreadedPassenger = random.nextInt(this.passengers.size());
                    }
                    this.passengers.get(spreadedPassenger).getSick();
                }
            }
        }
    }
}

public class TestCruise {
    public static void main(String[] args) {
        ArrayList<String> itinerary = new ArrayList<>();
        itinerary.add("Sydney");
        itinerary.add("Brisbane");

        CruiseShip ship = new CruiseShip("Super Cruise", 10, itinerary);

        for (int i = 0; i < 100; i++) {
            Passenger p = new Passenger("P" + i, "Sydney", "Melbourne");
            ship.boardPassenger(p);
        }

        // Seed illness to 10 passengers
        ship.seedInitialCases(10);
        ship.spreadBug();
    }
}
