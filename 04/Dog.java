abstract class Dog {
    public static void main(String[] args) {
        Poodle poodle = new Poodle("alaina");
    }

    protected String name;
    protected Breed breed;

    Dog(String name, Breed breed) {
        this.name = name;
        this.breed = breed;
    }

    abstract void bark();
}

abstract class BigDog extends Dog {

    BigDog(String name, Breed breed) {
        super(name, breed);
    }

    abstract void guardProperty();
}

class Poodle extends Dog {
    Poodle(String name) {
        super(name, Breed.GERMAN_SHEPHERD);
    }

    @Override
    void bark() {
        System.out.println(super.name + " the poodle, yaps");
    }
}

class GermanShepherd extends BigDog {
    GermanShepherd(String name) {
        super(name, Breed.GERMAN_SHEPHERD);
    }

    @Override
    void bark() {
        System.out.println(super.name + " the Germ, barks deeply");
    }

    void guardProperty() {
        System.out.println("guards property with with aura");
    }
}

enum Breed {
    POODLE, GERMAN_SHEPHERD;
}
