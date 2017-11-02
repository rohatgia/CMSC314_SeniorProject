import java.util.Scanner;

public class EvaluationOptions {
	public static double TP=0; //true positive
	public static double TN=0; //true negative
	public static double FP=0; //false positive
	public static double FN=0; //false negative
	public static double ACC=0; //accuracy
	public static double ERR=0; //error rate
	
	public static boolean confusionMatrixOption=true;
	public static boolean accuracyOption=true;
	
	EvaluationOptions(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Confusion Matrix:" + confusionMatrixOption + "\n"+
                           "2) Accuracy:" + accuracyOption + "\n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	confusionMatrixOption = !confusionMatrixOption;
        	break;
        case 2:
        	accuracyOption = !accuracyOption;
        	break;
        	}
    }
}
