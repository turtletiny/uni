import java.util.ArrayList;

public interface HasArea {
    public static void main(String[] args) {
        Car car = new Car(10, 30, 5);
        System.out.println(car.computeArea());
        System.out.println(car.computePerimeter());
        Rectangle rect = new Rectangle(10, 5);
        Circle c = new Circle(10);
        ArrayList<AreaAndPerimeter> items = new ArrayList<>();
        items.add(rect);
        items.add(c);
    }

    double computeArea();
}

interface HasPerimeter {
    double computePerimeter();
}

interface AreaAndPerimeter extends HasArea, HasPerimeter {
}

class Rectangle implements AreaAndPerimeter  {
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

class Circle implements AreaAndPerimeter {
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

class Car implements HasArea, HasPerimeter {
    private Circle frontWheel, backWheel;
    private Rectangle body;

    Car(double width, double height, double wheelRadius) {
        this.frontWheel = new Circle(wheelRadius);
        this.backWheel = new Circle(wheelRadius);
        this.body = new Rectangle(width, height);
    }

    @Override
    public double computeArea() {
        return this.frontWheel.computeArea() + this.backWheel.computeArea() + this.body.computeArea();
    }

    @Override
    public double computePerimeter() {
        return this.frontWheel.computePerimeter() +
                this.backWheel.computePerimeter() +
                this.body.computePerimeter();
    }
}
