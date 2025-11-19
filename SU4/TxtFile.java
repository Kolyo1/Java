import java.io.*;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class TxtFile {
    public static void main(String[] args) {
        String inputPath = "input.txt";
        String outputPath = "output.txt";

        // DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String header = "Филтриран файл, генериран на: " + LocalDateTime.now().format(fmt);

        try (BufferedReader br = new BufferedReader(new FileReader(inputPath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {

            //bw.write(header);
            // bw.newLine();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (line.trim().startsWith("#")) {
                    continue;
                }
                bw.write(line);
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Входният файл '" + inputPath + "' не е намерен.");
            System.err.println("Уверете се, че файлът съществува в работната директория и опитайте отново.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Грешка при четене или запис: " + e.getMessage());
            System.exit(2);
        }
    }
}
