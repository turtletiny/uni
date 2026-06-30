
import java.util.ArrayList;
import java.util.HashMap;

public class Soldier extends TerranUnit {
  public static void main(String[] args) {
    Squad squad = new Squad();
    Firebat firebat = new Firebat();
    Marine marine = new Marine();
    TerranUnit ghost1 = new Ghost();
    TerranUnit ghost2 = new Ghost();
    TerranUnit wraith = new Wraith();
    Wraith wraith2 = new Wraith();

    squad.addTerranUnit(firebat);
    squad.addTerranUnit(marine);
    squad.addTerranUnit(ghost1);
    squad.addTerranUnit(ghost2);
    squad.addTerranUnit(wraith);
    squad.addTerranUnit(wraith2);
    squad.activateStimpackForMarines();
    squad.printSquadComposition();
  }

  Soldier(Weapon weapon, int maxHealth, int speed) {
    super(weapon, maxHealth, speed);
  }
}

class TerranUnit {

  private final int MAX_HEALTH;
  private final Weapon WEAPON;

  private int currentHealth;
  // Measures how fast the soldier can move
  private int speed;

  TerranUnit(Weapon weapon, int maxHealth, int speed) {
    this.MAX_HEALTH = maxHealth;
    this.WEAPON = weapon;
    this.currentHealth = maxHealth;
    this.speed = speed;
  }

  int getAirDamage() {
    return this.WEAPON.getAirDamage();
  }

  int getGroundDamage() {
    return this.WEAPON.getGroundDamage();
  }
}

class Wraith extends TerranUnit {
  UnitType unitType;

  Wraith() {
    super(Weapon.C_10_RIFLE, 50, 7);
    this.unitType = UnitType.AIRBOURNE;
  }
}

class Marine extends Soldier {
  boolean stimpackActive;

  Marine() {
    super(Weapon.MACHINE_GUN, 40, 4);
    this.stimpackActive = false;
  }

  void activateStimpack() {
    this.stimpackActive = true;
  }

  @Override
  int getAirDamage() {
    int originalDamage = super.getAirDamage();
    if (this.stimpackActive) {
      // If stimpack is active, increase damage by 50%.
      return originalDamage + originalDamage / 2;
    } else {
      return originalDamage;
    }
  }

}

class Firebat extends Soldier {

  boolean stimpackActive;

  Firebat() {
    super(Weapon.FLAME_THROWER, 50, 4);
    this.stimpackActive = false;
  }

  @Override
  int getGroundDamage() {
    int originalDamage = super.getGroundDamage();
    if (this.stimpackActive) {
      return originalDamage + originalDamage / 2;
    } else {
      return originalDamage;
    }
  }

  void activateStimpack() {
    this.stimpackActive = true;
  }
}

class Ghost extends Soldier {
  private boolean cloakActive;

  Ghost() {
    super(Weapon.C_10_RIFLE, 45, 4);
    this.cloakActive = false;
  }

  public void activateCloak() {
    this.cloakActive = true;
  }

}

enum UnitType {
  GROUND, AIRBOURNE;
}

enum Weapon {
  MACHINE_GUN(6, 6, 4),
  FLAME_THROWER(8, 0, 2),
  C_10_RIFLE(10, 10, 7);

  // Amount of damage that can be inflicted on ground-base enemies
  private int groundDamage;
  // Amount of damage that can be inflicted on airbourne enemies
  private int airDamage;
  // The maximum distance that the weapon can inflict damage
  private int attackRange;

  Weapon(int groundDamage, int airDamage, int attackRange) {
    this.groundDamage = groundDamage;
    this.airDamage = airDamage;
    this.attackRange = attackRange;
  }

  int getGroundDamage() {
    return this.groundDamage;
  }

  int getAirDamage() {
    return this.airDamage;
  }

  int getAttackRange() {
    return this.attackRange;
  }

  public String toString() {
    return this.name() + "\n" +
        " - Ground damage: " + this.groundDamage + "\n" +
        " - Air damage: " + this.airDamage + "\n" +
        " - Attack range: " + this.attackRange;
  }
}

class Squad {
  private ArrayList<TerranUnit> members;

  Squad() {
    this.members = new ArrayList<>();
  }

  void addTerranUnit(TerranUnit terranUnit) {
    this.members.add(terranUnit);
  }

  int totalGroundAttackPower() {
    int total = 0;
    for (TerranUnit s : this.members) {
      total += s.getGroundDamage();
    }
    return total;
  }

  void activateStimpackForMarines() {
    for (TerranUnit s : this.members) {
      // Here is where we check for `Marine` type
      if (s instanceof Marine) {
        // We need to cast `s` to the `Marine` type.
        Marine m = (Marine) s;
        m.activateStimpack();
      }
    }
  }

  void cloakAllGhosts() {
    for (TerranUnit s : this.members) {
      if (s instanceof Ghost) {
        Ghost m = (Ghost) s;
        m.activateCloak();
      }
    }
  }

  void printSquadComposition() {
    HashMap<String, Integer> terraUnitCount = new HashMap<>();
    for (TerranUnit s : this.members) {
      String className = s.getClass().getSimpleName();
      terraUnitCount.put(className, terraUnitCount.getOrDefault(className, 0) + 1);
    }
    for (String s : terraUnitCount.keySet()) {
      System.out.println(s + "s: " + terraUnitCount.get(s));
    }
  }
  // int firebatCount = 0;
  // int marineCount = 0;
  // int ghostCount = 0;
  // for (Soldier s : this.members) {
  // if (s instanceof Marine) {
  // marineCount++;
  // } else if (s instanceof Firebat) {
  // firebatCount++;
  // } else if (s instanceof Ghost) {
  // ghostCount++;
  // }
  // }
  // System.out.println("Firebats: " + firebatCount);
  // System.out.println("Marines: " + marineCount);
  // System.out.println("Ghosts: " + ghostCount);

}
