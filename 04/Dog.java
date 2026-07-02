abstract class Dog {
    public static void main(String[] args) {
        Poodle poodle = new Poodle("alaina");
        Dog dog = new Dog("noob");
    }

    protected String name;
    protected Breed breed;

    Dog(String name, Breed breed) {
        this.name = name;
        this.breed = breed;
    }
}

class Poodle extends Dog {
    Poodle(String name) {
        super(NAME, Breed.GERMAN_SHEPHERD);
    }
}

enum Breed {
    POODLE, GERMAN_SHEPHERD;
}
