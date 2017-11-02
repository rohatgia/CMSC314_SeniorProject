import java.util.Scanner;

public class LearnOptions {
	//We will need an arrayList of the algorithms the user added (this will likely be an arraylist using the "abstractclassifier" object from the MOA API

	LearnOptions(){
		
	}
	
    public void displayMenu(){
    	/*
    	 * List of the currently added algorithms will display here
    	 */
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Selected Algorithm \n" +
                           "2) Add Algorithm \n" +
                           "3) Edit Parameters \n" +
                           "4) Learning Stream \n" +
                           "5) Testing Stream \n");
        
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//execute Algorithm Selection Menu
        	break;
        case 2:
        	//add algorithm menu
        	break;
        case 3:
        	//edit parameters menu
        	break;
        case 4:
        	//cycles through loaded streams and sets it as the stream to learn from
        	break;
        case 5:
        	//cycles through loaded streams and sets it as the stream to test from
        	break;
    
        }
    }
}
