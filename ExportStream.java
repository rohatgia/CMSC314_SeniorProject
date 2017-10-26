import java.util.Scanner;

public class ExportStream {

	ExportStream(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Selected Stream \n" +
                           "2) Export Stream To ARFF \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//Selected Stream Button
        	break;
        case 2:
        	//export stream to arff button
        	break;    
        }
    }
}
