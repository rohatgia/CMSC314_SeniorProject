import java.util.Scanner;

public class BuildTask {
	public static LearnOptions learnOptions = new LearnOptions();
	public static TestOptions testOptions = new TestOptions();
	public static EvaluationOptions evaluationOptions = new EvaluationOptions();
	private int batchSize=1;
	
	BuildTask(){
		
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Learn Options \n" +
                           "2) Test Options \n" +
                           "3) Evaluation Options \n" +
                           "4) Batch Size: "+ getBatchSize() +"\n" +
                           "5) Configure Flow \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	learnOptions.displayMenu();
        	break;
        case 2:
        	testOptions.displayMenu();
        	break;
        case 3:
        	evaluationOptions.displayMenu();
        	break;
        case 4:
        	setBatchSize(userIn.nextInt());
        	break;
        case 5:
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
