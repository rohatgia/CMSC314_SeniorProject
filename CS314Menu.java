import java.util.ArrayList;
import java.util.Scanner;

import moa.classifiers.bayes.NaiveBayes;
import moa.tasks.EvaluateInterleavedChunks;

public class CS314Menu{
	public static ConfigureStream configureStreams = new ConfigureStream();
	public static BuildTask buildTask = new BuildTask();
	public static ArrayList <EvaluateInterleavedChunks> tasks;
	
    public static void main(String[] args){
    	CS314Menu mainMenu= new CS314Menu();
    	mainMenu.initializeProgram();
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
        	configureStreams.displayMenu();
        	break;
        case 2:
        	buildTask.displayMenu();
        	break;
        case 3:
        	initializeStreams();
        	tasks.get(0).doTask(); //for now we are only worried about exectuing a single task at a time
        	break;
    
        }
    }
    
    private void initializeStreams(){
		for( EvaluateInterleavedChunks t: this.tasks){
			t.prepareForUse();
		}
    }
    
    private void initializeProgram(){
    	tasks.add(new EvaluateInterleavedChunks());
    	tasks.get(0).chunkSizeOption.setValue(1);
    	buildTask.learnOptions.classifiers.add(new NaiveBayes());
    	tasks.get(0).learnerOption.setCurrentObject(buildTask.learnOptions.classifiers.get(0));
    }
}
