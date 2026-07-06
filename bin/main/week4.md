Readings
Recall in last week's pre-tutorial materials the Soldier hierarchy from Starcraft:image.png

In this simplified example, a key constraint was that we could only create a Marine or Firebat soldier. But we saw that it was easy to break the constraint; all we needed to do was create a Soldier object directly:

Soldier superMarine = new Soldier(Weapon.MACHINE_GUN, 400, 40);
Soldier superFirebat = new Soldier(Weapon.FLAME_THROWER, 500, 40);
Here we've created a Marine and a Firebat that has 10 times the usual health points. We can of course be disciplined and avoid creating Soldier objects directly, but it's even better if the compiler can enforce this restriction. Java has a very neat solution to this problem.

Abstract classes
An abstract class is one that cannot be instantiated i.e. we cannot create an object of the given class. Such classes are typically used as a parent(base) class for other classes. To make a class abstract, we simply add the abstract keyword to the class declaration. Consider this simple example with dogs:

abstract class Dog {
    protected String name;
    protected Breed breed;

    Dog(String name, Breed breed) {
        this.name = name;
        this.breed = breed;
    }
}

class Poodle extends Dog {
    Poodle(String name) {
        super(name, Breed.POODLE);
    }
}

class GermanShepherd extends Dog {
    GermanShepherd(String name) {
        super(name, Breed.GERMAN_SHEPHERD);
    }
}

enum Breed {
    POODLE, GERMAN_SHEPHERD
}
Note how the abstract Dog class has a constructor. It can only be called by child classes through the use of the super keyword. In addition, abstract classes can contain attributes and regular methods.

 Concrete class
A concrete class is one that can be instantiated i.e. we can create objects of the class.

 

Exercise: Try to create a Dog object and read what error the Java compiler gives you.

Abstract methods
An abstract method is one that is declared without an implementation i.e. there is no { ... } block. Any concrete subclass of the abstract class must provide an implementation of this method. Let's add an abstract void bark() method to the Dog class:dog_add_abstract_method-1.gif

When we added the abstract method to Dog note that Java immediately reported 2 errors in both child classes. The problem is that the child classes have yet to implement the bark() method. Because of this we say that abstract methods define a 'contract' where concrete subclasses must implement the abstract method. This provides a number of benefits:

There's no need to write a generic and often useless implementation of the method in the abstract class.
We also have a guarantee that every concrete subclass has a specific implementation of this method, which improves the use of polymorphism. Recall from last week that a child class can override an existing method (call it m(), say) of the parent. This overriding is strictly optional; if a child doesn't override m(), calling the method on it defaults to the implementation from the parent class. This often results in inconsistent behaviour if some children classes override, and others don't. Abstract methods ensure consistency in this area.
Implementing an abstract method is the same as overriding a concrete method. Here's the implementations for both Poodle and GermanShepherd:

class Poodle extends Dog {
    Poodle(String name) {
        super(name, Breed.POODLE);
    }

    @Override
    void bark() {
        System.out.println(super.name + " the poodle, yaps.");
    }
}

class GermanShepherd extends Dog {
    GermanShepherd(String name) {
        super(name, Breed.GERMAN_SHEPHERD);
    }

    @Override
    void bark() {
        System.out.println(super.name +
                " the German Shephard, barks in a low register.");
    }
}
Abstract subclasses
Subclasses of an abstract class can also be abstract! Let's insert an abstract class BigDog between the Dog and GermanShepherd classes:

abstract class BigDog extends Dog {

    BigDog(String name, Breed breed) {
        super(name, breed);
    }

    abstract void guardProperty();
}

class GermanShepherd extends BigDog {
    GermanShepherd(String name) {
        super(name, Breed.GERMAN_SHEPHERD);
    }

    // Still need to implement bark() as specified in the `Dog`
    // class.
    void bark() {
        System.out.println(super.name +
                " the German Shephard, barks in a low register.");
    }

    void guardProperty() {
        System.out.println("Guarding the property");
    }
}
Note how GermanShepherd is a concrete class that implements all abstract methods declared within its ancestor classes. We now have the following class hierarchy:dog_hierarchy.jpg
On the previous page we learnt how abstract methods of an abstract class A provide a contract where concrete subclasses of A must provide implementations of the abstract methods. Java interfaces are effectively a collection of abstract methods and we say that classes can implement an interface. The interface is a contract that implementing classes agree to follow. Consider the following interface:

public interface HasArea {
    double computeArea();
}
We can write a Rectangle and Circle class which implements this interface (note the implements keyword; we do not use extends for interfaces).

class Rectangle implements HasArea {

    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double computeArea() {
        return this.width * this.height;
    }
}

class Circle implements HasArea {

    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double computeArea() {
        return Math.PI * this.radius * this.radius;
    }
}
Now we could definitely model this scenario with an abstract Shape class containing an abstract method double computeArea(), and Rectangle and Circle would be child classes of Shape. But what if other classes that wouldn't fit neatly into this hierarchy also needed to compute an area? For example, let's create a simple car entity that requires a rectangle and 2 circles to represent it:car_shape.jpg

So our car is composed of 3 shapes. We could write a class for it that implements the HasArea interface:

class Car implements HasArea {
    private Circle frontWheel, backWheel;
    private Rectangle body;

    Car(double width, double height, double wheelRadius) {
        this.frontWheel = new Circle(wheelRadius);
        this.backWheel = new Circle(wheelRadius);
        this.body = new Rectangle(width, height);
    }

    @Override
    public double computeArea() {
        return this.frontWheel.computeArea()
                + this.backWheel.computeArea()
                + this.body.computeArea();
    }

}
With interfaces, it's easy to extend the behaviour of various classes that might not be closely related.

Classes can implement multiple interfaces
Suppose we want to give the above classes the ability to compute their perimeter as well? We can create another interface HasPerimeter and get our classes to implement it in addition to the HasArea interface:

interface HasArea {
    double computeArea();
}

interface HasPerimeter {
    double computePerimeter();
}

class Rectangle implements HasArea, HasPerimeter {

    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double computeArea() {
        return this.width * this.height;
    }

    @Override
    public double computePerimeter() {
        return 2 * (this.width + this.height);
    }

}

class Circle implements HasArea, HasPerimeter {

    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double computeArea() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double computePerimeter() {
        return 2 * Math.PI * this.radius;
    }

}
Exercise: Add an implementation of HasPerimeter for the Car class.

Polymorphism with a single interface
Now that we know how to implement interfaces, how do we use them? Can we do polymorphism? Yes and yes; let's consider the simplest case of polymorphism with a single interface.

Suppose we want to create an ArrayList that holds objects that implement the HasArea interface. It works just like with regular inheritance. 

class Week4 {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(10.0, 5.0);
        Circle circ = new Circle(10.0);

        ArrayList<HasArea> items = new ArrayList<>();
        items.add(rect);
        items.add(circ);

        for (HasArea a : items) {
            System.out.println(a.computeArea());
        }
    }
}
 

Polymorphism with multiple interfaces
There are added complications when trying to extend the above example to handle types that implement multiple interfaces. Suppose we want to create an ArrayList that holds objects that implement the HasArea and HasPerimeter interfaces. It would be nice if we could modify the items ArrayList above to something like:

ArrayList<HasArea + HasPerimeter> items = new ArrayList<>(); // This does not work :(
The solution is to create a common interface that incorporates both interfaces. The syntax is:

interface AreaAndPerimeter extends HasArea, HasPerimeter {
}
Nothing is required between the curly brackets. The amended code is:

class Week4 {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(10.0, 5.0);
        Circle circ = new Circle(10.0);

        ArrayList<AreaAndPerimeter> items = new ArrayList<>();
        items.add(rect);
        items.add(circ);

        for (AreaAndPerimeter a : items) {
            System.out.println("Area: " + a.computeArea() + ", perimeter: " + a.computePerimeter());
        }
    }
}
Interfaces vs class inheritance
In general, you should prefer interfaces over inheritance when looking to apply polymorphism.

Interfaces are more flexible since they only dictate a contract for providing method implementations. As we've seen above, different classes that are not related via an inheritance hierarchy can implement interfaces.
It's often more troublesome to make changes with inheritance.
For example, suppose you needed to change the type of a protected attribute high up in a deep inheritance hierarchy. This will affect all the descendent classes that use this attribute, requiring more code modifications. You don't have this problem with interfaces because they don't specify how methods should be implemented, so modifying a particular class' implementation of an interface does not affect any other implementations (of course, if you need to modify the interface itself, you will need to modify all implementations of that interface). 
Classes can implement multiple interfaces, but can only inherit from one parent class.
So when should we use inheritance? There's no hard and fast rule, but a number of the following requirements should be met:

When there is a clear is-a relationship between classes e.g. a Car is-a Vehicle. Moreover, there are characteristics or properties of the parent class that the child class inherently has too.
When child classes need to reuse code (attributes and/or concrete methods) from the parent class.
When you want a child class to be able to use reuse a method of the parent yet still implement an overriding implementation.
