import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class BinaryStats {

    public static void main(String[] args) {
        String inputFile = "numbers.dat";
        String outputFile = "stats.txt";

        if (args.length >= 1) inputFile = args[0];
        if (args.length >= 2) outputFile = args[1];

        long count = 0;
        long sum = 0;
        Integer min = null;
        Integer max = null;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputFile))) {
            while (true) {
                try {
                    int number = dis.readInt();
                    count++;
                    sum += number;
                    if (min == null || number < min) min = number;
                    if (max == null || number > max) max = number;
                } catch (EOFException eof) {
                    // reached end of file
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + inputFile);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O error while reading input file: " + e.getMessage());
            System.exit(2);
        }

        double avg = (count == 0) ? 0.0 : ((double) sum) / count;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Count: " + count);
            bw.newLine();
            bw.write("Sum: " + sum);
            bw.newLine();
            bw.write("Avg: " + avg);
            bw.newLine();
            bw.write("Min: " + (min != null ? min : "N/A"));
            bw.newLine();
            bw.write("Max: " + (max != null ? max : "N/A"));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
            System.exit(3);
        }
    }
}


