package SU2;

import java.util.*;

abstract class Worker {
    String name;
    double salary;
    abstract double TotalPay();
}

class Manager extends Worker {  

    Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    double TotalPay() {
        double bonus = 0.2 * salary;
        return salary + bonus;
    }
}

class Intern extends Worker {
    
    Intern(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    double TotalPay() {
        return salary;
    }
}

public class Main5 {
    public static void main(String[] args) {
        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(new Manager("Alice", 80000));
        workers.add(new Intern("Bob", 30000));

        for (Worker worker : workers) {
            System.out.println("Name: " + worker.name + ", Total Pay: " + worker.TotalPay());
        }
    }    
}
