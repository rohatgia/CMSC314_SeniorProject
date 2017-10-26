import java.util.Scanner;

public class BuildTask {

	BuildTask(){
		displayMenu();
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Learn Options \n" +
                           "2) Test Options \n" +
                           "3) Evaluation Options \n" +
                           "4) Batch Size \n" +
                           "5) Configure Flow \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//execute Learn Options Menu
        	break;
        case 2:
        	//execute Test Options Menu
        	break;
        case 3:
        	//execute Evaluation Options Menu
        	break;
        case 4:
        	//execute Batch size Option
        	break;
        case 5:
        	//execute Configure Flow Menu
        	break;
    
        }
    }
}
