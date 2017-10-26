import java.util.Scanner;

public class TestOptions {

	TestOptions(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Selected Stream for testing \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//Selected Stream Button
        	break;   
        }
    }
    
}
