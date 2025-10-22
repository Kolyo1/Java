package SU2;

import java.util.*;

interface Measurable {
    double measure();
    
}

class Temperature implements Measurable {
    double temperatute;
    public Temperature(double temperatute) {
        this.temperatute = temperatute;
    }

    @Override
    public double measure() {
        return temperatute;
    }
}

class SpeedSensor implements Measurable {
    double speed;
    public SpeedSensor(double speed) {
        this.speed = speed;
    }
    @Override
    public double measure() {
        return speed;
    }
}

public class Main2 {
    public static void main(String[] args) {
        List<Measurable> sensors = new ArrayList<>();
        sensors.add(new Temperature(36.5));
        sensors.add(new SpeedSensor(88.0));
        for (Measurable sensor : sensors) {
            System.out.println("Measurement: " + sensor.measure());
        }
    }
}
