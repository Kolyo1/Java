package SU2;

import java.util.*;

interface Drawable {
    void draw();
}

class Triangle implements Drawable {
    double base;
    double height;
    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    @Override
    public void draw() {
        System.out.println("Drawing a triangle");
    }
}

class Square implements Drawable {
    double side;
    Square(double side) {
        this.side = side;
    }
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }
}

public class Main6 {
    public static void main(String[] args) {
        List<Drawable> shapes = new ArrayList<>();
        shapes.add(new Triangle(3, 4));
        shapes.add(new Square(5));

        for (Drawable shape : shapes) {
            if (shape instanceof Triangle) {
                Triangle t = (Triangle) shape;
                System.out.println("Triangle: base = " + t.base + ", height = " + t.height);
            }       
            else if (shape instanceof Square) {
                Square s = (Square) shape;
                System.out.println("Square: side = " + s.side);
            }
            shape.draw();
        }
    }
}
