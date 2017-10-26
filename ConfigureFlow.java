import java.util.Scanner;

public class ConfigureFlow {

	ConfigureFlow(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Current selected flow module \n"+
                           "2) Add new module to flow \n"+
                           "3) Remove selected Module from flow \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//cycles through "flow modules" each flow module is numbered in an ordinal way so you can get an idea of flow of control
        	break;
        case 2:
        	//add new flow menu
        	break;
        	
        case 3:
        	//Remove selected module from flow
        	break;
        }
    }
    
}
