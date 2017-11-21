import java.util.ArrayList;
import java.util.Scanner;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.bayes.NaiveBayes;
import moa.streams.ArffFileStream;
import moa.tasks.EvaluateInterleavedChunks;

public class CS314Menu{
	public static ConfigureStream configureStreams = new ConfigureStream();
	public static BuildTask buildTask = new BuildTask();
	public static EvaluateInterleavedChunks tasks = new EvaluateInterleavedChunks();
	public static boolean modelTrained=false;
	
    public static void main(String[] args) throws InstantiationException, IllegalAccessException{
    	CS314Menu mainMenu= new CS314Menu();
    	mainMenu.initializeProgram();
    	mainMenu.displayMenu();
    }
    
    public void displayMenu() throws InstantiationException, IllegalAccessException{
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
    
    private void tempDoTask() throws InstantiationException, IllegalAccessException{
    	buildTask.learnOptions.currentSelected.prepareForUse();
    	
    	for(int i=0; i < configureStreams.totalInstances / configureStreams.editStream.getBatchSize(); i++){
    		ArrayList<Instance> trainChunk = makeChunks(this.configureStreams.learningStream);
    		ArrayList<Instance> testChunk = makeChunks(this.configureStreams.testingStream);
    		/*
    		 *FILTER 
    		 */
    		/*
    		FilterModule filterM = new FilterModule();
    		trainChunk = filterM.filterChunk(trainChunk);
    		*/
    		
    		/*
    		 * TRAIN
    		 */
    		TrainModule TrainM= new TrainModule(this.buildTask.learnOptions.currentSelected, trainChunk);
    		
    		/*
    		 * TEST
    		 */
    		TestModule TestM= new TestModule(this.buildTask.learnOptions.currentSelected, testChunk);
    	}	
    }
    
    private ArrayList<Instance> makeChunks(ArffFileStream stream){
    	ArrayList<Instance> newChunk= new ArrayList<Instance>();
    	for(int i=0; i<configureStreams.editStream.getBatchSize(); i++){
    		newChunk.add(stream.nextInstance().instance);
    	}
		return newChunk;
    }
    
    public boolean getTrained(){
    	return this.modelTrained;
    }
}