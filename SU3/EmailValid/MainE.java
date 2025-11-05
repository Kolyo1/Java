package EmailValid;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}

public class MainE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете имейл адрес: ");
        String email = scanner.nextLine();
        
        try{
            validEmail(email);
            System.out.println("Имейл е приет : %s".formatted(email));
        } catch (InvalidEmailException e){
            System.out.println("Невалиден имейл: " + e.getMessage());
        }

        scanner.close();
    }

    public static void validEmail(String email) {
        String regex = "^[\\\\w._%+-]+@[\\\\w.-]+\\\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new InvalidEmailException("Имейлът не отговаря на изисквания формат.");
        }
    }
}

