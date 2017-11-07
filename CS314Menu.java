import java.util.ArrayList;
import java.util.Scanner;

import moa.classifiers.bayes.NaiveBayes;
import moa.tasks.EvaluateInterleavedChunks;

public class CS314Menu{
	public static ConfigureStream configureStreams = new ConfigureStream();
	public static BuildTask buildTask = new BuildTask();
	public static EvaluateInterleavedChunks tasks = new EvaluateInterleavedChunks();
	
    public static void main(String[] args){
    	CS314Menu mainMenu= new CS314Menu();
    	mainMenu.initializeProgram();
    	mainMenu.displayMenu();
    }
    
    public void displayMenu(){
    	boolean exit=false;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Configure Stream \n" +
                           "2) Build Task \n" +
                           "3) Execute Task \n"+
                           "101) EXIT PROGRAM");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	configureStreams.displayMenu();
        	break;
        case 2:
        	buildTask.displayMenu();
        	break;
        case 3:
        	initializeStreams();
        	tempDoTask();
        	System.out.println("Task Complete");
        	break;
        case 101:
        	exit=true;
        	break;
        }
        
        if(!exit){
        	this.displayMenu();
        }
        
    }
    
    private void initializeStreams(){
			tasks.prepareForUse();
    }
    
    private void initializeProgram(){
    	tasks.chunkSizeOption.setValue(1);
    	tasks.learnerOption.setCurrentObject(buildTask.learnOptions.classifiers.get(0));
    }
    
    private void tempDoTask(){
    	TestModule TestM= new TestModule(this.buildTask.learnOptions.currentSelected, this.configureStreams.testingStream);
    	TrainModule TrainM= new TrainModule(this.buildTask.learnOptions.currentSelected, this.configureStreams.learningStream);
    }
}