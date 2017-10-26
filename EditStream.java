import java.util.Scanner;

public class EditStream {
	
	EditStream(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Select Stream \n" +
                           "2) Rename Selected Stream \n" +
                           "3) Remove Attributes in Selected Stream \n"+
                           "4) Remove the Selected Stream");
        
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//Select Stream Menu
        	break;
        case 2:
        	//Rename selected Stream Button
        	break;
        case 3:
        	//Remove Attributes Menu
        	break;
        	
        case 4:
        	// Remove selected stream Button
        	break;   
        }
    }
}
