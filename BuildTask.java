import java.util.Scanner;

public class BuildTask {
	public static LearnOptions learnOptions = new LearnOptions();
	public static EvaluationOptions evaluationOptions = new EvaluationOptions();
	private int batchSize=1;
	
	BuildTask(){
		
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Learn Options \n" +
                           "2) Evaluation Options \n" +
                           "3) Batch Size: "+ getBatchSize() +"\n" +
                           "4) Configure Flow \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	learnOptions.displayMenu();
        	break;
        case 2:
        	evaluationOptions.displayMenu();
        	break;
        case 3:
        	setBatchSize(userIn.nextInt());
        	break;
        case 4:
        	//execute Configure Flow Menu
        	break;
        }
    }

    /*
     * BatchSize Setter/Getter
     * Allows user to retrieve and set the batch size
     */
	private int getBatchSize() {
		return this.batchSize;
	}
	public void setBatchSize(int batchSize){
		this.batchSize=batchSize;
	}
	
}
