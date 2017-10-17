import java.util.Scanner;

public class CS314Menu{
    public static void main(String[] args){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Load File \n" +
                           "2) Build Flow \n" +
                           "3) Edit Flow \n" +
                           "4) Edit Parameters \n");
        int userChoice = userIn.nextInt();
        System.out.println("Debug - User Choice: " + userChoice);
    }
}