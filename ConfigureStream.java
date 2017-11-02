import java.util.Scanner;

public class ConfigureStream {
	public static EditStream editStream= new EditStream();
	public static ExportStream exportStream = new ExportStream();
	//we will need an arraylist of streams that is static here
	//we will need a stream variable that represents the current selected stream, this variable will be useful/accesible in many menus
	
	ConfigureStream(){
		
	}
	
    public void displayMenu(){
    	/*
    	 * Display the contents of the Stream arraylist menu here
    	 */
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
        	editStream.displayMenu();
        	break;
        case 3:
        	exportStream.displayMenu();
        	break;
    
        }
    }
}
