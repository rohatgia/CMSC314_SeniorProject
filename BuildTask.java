import java.util.Scanner;

public class BuildTask {
	public static LearnOptions learnOptions = new LearnOptions();
	public static EvaluationOptions evaluationOptions = new EvaluationOptions();
	
	BuildTask(){
		
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Learn Options \n" +
                           "2) Evaluation Options \n" +
                           "3) Configure Flow \n");
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
        }
    }
	
}
