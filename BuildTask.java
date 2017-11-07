import java.util.Scanner;

public class BuildTask {
	public static LearnOptions learnOptions = new LearnOptions();
	public static EvaluationOptions evaluationOptions = new EvaluationOptions();
	boolean exit=false;
	BuildTask(){
		
		
	}
	
    public void displayMenu(){
    	
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Learn Options \n" +
                           "2) Evaluation Options \n" +
                           "3) Configure Flow \n" +
                           "101) Go back");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	learnOptions.displayMenu();
        	break;
        case 2:
        	evaluationOptions.displayMenu();
        	break;
        case 3:
        	//execute Configure Flow Menu
        	break;
        case 101:
        	exit=true;
        }
        
        if(!exit){
        	this.displayMenu();
        }
    }
	
}
