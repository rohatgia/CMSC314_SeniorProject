import java.util.Scanner;

public class ConfigureStream {

	ConfigureStream(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Add Stream \n" +
                           "2) Edit Stream \n" +
                           "3) Export Stream \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//execute Add Stream Menu
        	break;
        case 2:
        	//execute Edit Stream Menu
        	break;
        case 3:
        	//execute Export Stream Menu
        	break;
    
        }
    }
}
