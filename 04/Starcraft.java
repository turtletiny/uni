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
}

interface GroundUnit {

}

interface AirbourneUnit {

}
weapon
interface UseStimPack {
    void activateStimpack();
}weapon

interface UseCloak {
    void activateCloak();
}

class Marine implements UseStimPack, AirbourneUnit{

    private final Weapon WEAPON;
    private final int MAX_HEALTH;
    private int currentHealth;
    private int speed;
    boolean stimpackActive;

    Marine() {
        this.stimpackActive = false;
        this.MAX_HEALTH = 40;
        this.currentHealth = 40;
        this.speed = 4;
        this.WEAPON = Weapon.MACHINE_GUN;
    }

    @Override
    public void activateStimpack() {
        this.stimpackActive = true;
    }
}

class Wraith implements AirbourneUnit {

    private final int MAX_HEALTH;
    private final Weapon WEAPON;
    private int currentHealth;
    private int speed;
    
    Wraith() {
        this.WEAPON = Weapon.C_10_RIFLE;
        this.MAX_HEALTH = 50;
        this.currentHealth = 50;
        this.speed = 7;
    }

    int getGroundDamage() {
        return 8;
}

    int getAirDamage() {
        return 20;
    }
}

class Firebat implements UseStimPack {
    boolean stimpackActive;
    private final Weapon WEAPON;
    private final int MAX_HEALTH;
    private int currentHealth;
    private int speed;
    
    
}
