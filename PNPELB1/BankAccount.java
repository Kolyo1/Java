public class BankAccount {
    public static String OwnerName;
    public static double Balance;
    public static double INTEREST_RATE = 0.02;
    public static double calculateInterest(double amount){
        return amount * INTEREST_RATE;
    }
}