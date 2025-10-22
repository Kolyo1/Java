package SU2;

import java.util.*;

abstract class Food{
    String name;

    void describe(){
        System.out.println("This is a food item.");
    }
}

interface Eatable {
    void eat();
}

class Apple extends Food implements Eatable {
    Apple() {
        this.name = "Apple";
    }

    @Override
    void describe() {
        System.out.println("A sweet red fruit.");
    }
    @Override
    public void eat() {
        System.out.println("You eat the apple.");
    }
}

class Bread extends Food implements Eatable {
    Bread() {
        this.name = "Bread";
    }

    @Override
    void describe() {
        System.out.println("Baked from flour");
    }
    @Override
    public void eat() {
        System.out.println("You eat the bread.");
    }
}

class Cheese extends Food implements Eatable {
    Cheese() {
        this.name = "Cheese";
    }

    @Override
    void describe() {
        System.out.println("Made from milk.");
    }
    @Override
    public void eat() {
        System.out.println("You eat the cheese.");
    }
}
public class Main3 {
    public static void main(String[] args) {
    ArrayList<Food> foodList = new ArrayList<>();
    foodList.add(new Apple());
    foodList.add(new Bread());
    foodList.add(new Cheese());

    for(Food food : foodList){
        if(food instanceof Eatable){
            food.describe();
            ((Eatable) food).eat();
        }
    }
}
}
