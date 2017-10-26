import java.util.Scanner;

public class LearnOptions {
	LearnOptions(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Selected Algorithm \n" +
                           "2) Learning Stream \n" +
                           "3) Testing Stream \n");
        /*
         * TODO need to scan selected algorithm and list out parameters
         */
        
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//execute Algorithm Selection Menu
        	break;
        case 2:
        	//cycles through loaded streams and sets it as the stream to learn from
        	break;
        case 3:
        	//cycles through loaded streams and sets it as the stream to test from
        	break;
    
        }
    }
}
