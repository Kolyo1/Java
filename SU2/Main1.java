class Instrument {
    String name;

    public Instrument(String name) {
        this.name = name;
    }

    void play() {
        System.out.println("Generic instrument sound.");
    }
}

class Guitar extends Instrument {
    public Guitar(String name) {
        super(name);
    }

    @Override
    void play() {
        System.out.println("Strum!");
    }
}

class Piano extends Instrument {
    public Piano(String name) {
        super(name);
    }

    @Override
    void play() {
        System.out.println("Plink plonk!");
    }
}

public class Main1 {
    public static void main(String[] args) {
        Instrument[] instruments = {
            new Instrument("Generic Instrument"),
            new Guitar("Guitar"),
            new Piano("Piano")
        };

        for (Instrument instrument : instruments) {
            System.out.print(instrument.name + ": ");
            instrument.play();
        }
    }
}
