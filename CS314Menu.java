import java.util.ArrayList;
import java.util.Scanner;
import moa.tasks.EvaluateInterleavedChunks;

public class CS314Menu{
	public static ConfigureStream cs = new ConfigureStream();
	public static BuildTask bt = new BuildTask();
	public static ArrayList <EvaluateInterleavedChunks> tasks;
	
    public static void main(String[] args){
    	CS314Menu mainMenu= new CS314Menu();
    	mainMenu.displayMenu();
    }
    
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Configure Stream \n" +
                           "2) Build Task \n" +
                           "3) Execute Task \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	cs = new ConfigureStream();
        	break;
        case 2:
        	bt = new BuildTask();
        	break;
        case 3:
        	initializeStreams();
        	//execute flow
        	break;
    
        }
    }
    
    private void initializeStreams(){
		for( EvaluateInterleavedChunks t: this.tasks){
			t.prepareForUse();
		}
    }
}
