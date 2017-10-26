import java.util.Scanner;

public class EvaluationOptions {
	boolean confusionMatrix=true;
	boolean instanceCorrect=true;
	boolean instancesIncorrect=true;
	
	EvaluationOptions(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Confusion Matrix:" + confusionMatrix + "\n"+
                           "2) Instance Correct:" + instanceCorrect + "\n"+
                           "3) Instance Incorrect:" + instancesIncorrect + "\n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	confusionMatrix = !confusionMatrix;
        	break;
        case 2:
        	instanceCorrect = !instanceCorrect;
        	break;
        case 3:
        	instancesIncorrect = !instancesIncorrect;
        	break;
        }
    }
}
