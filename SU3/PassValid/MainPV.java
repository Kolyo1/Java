package PassValid;

import java.util.Scanner;
import java.util.regex.Pattern;

class ShortPasswordException extends RuntimeException {
    public ShortPasswordException(String message) {
        super(message);
    }
}

class NoUppercaseException extends RuntimeException {
    public NoUppercaseException(String message) {
        super(message);
    }
}

class NoDigitException extends RuntimeException {
    public NoDigitException(String message) {
        super(message);
    }
}

class NoSpecialCharacterException extends RuntimeException {
    public NoSpecialCharacterException(String message) {
        super(message);
    }
}

public class MainPV {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете парола: ");
        String password = scanner.nextLine();

        try {
            validPassword(password);
            System.out.println("✅ Паролата е приета!");
        } catch (ShortPasswordException |
                 NoUppercaseException |
                 NoDigitException |
                 NoSpecialCharacterException e) {
            System.out.println("❌ Грешка: " + e.getMessage());
        }

        scanner.close();
    }

    public static void validPassword(String password) {
        if (password.length() < 8) {
            throw new ShortPasswordException("Паролата трябва да е поне 8 символа.");
        }
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            throw new NoUppercaseException("Паролата трябва да съдържа поне една главна буква (A-Z).");
        }
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            throw new NoDigitException("Паролата трябва да съдържа поне една цифра (0-9).");
        }
        if (!Pattern.compile("[!@#$%^&*]").matcher(password).find()) {
            throw new NoSpecialCharacterException("Паролата трябва да съдържа поне един специален символ (!@#$%^&*).");
        }
    }
}
