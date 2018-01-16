import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Instances;

import moa.classifiers.bayes.NaiveBayes;
import moa.streams.ArffFileStream;
import moa.tasks.EvaluateInterleavedChunks;

public class CS314Menu{
	public static ConfigureStream configureStreams = new ConfigureStream();
	public static BuildTask buildTask = new BuildTask();
	public static EvaluateInterleavedChunks tasks = new EvaluateInterleavedChunks();
	public static boolean modelTrained=false;
	
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException{
    	CS314Menu mainMenu= new CS314Menu();
    	mainMenu.initializeProgram();
    	mainMenu.displayMenu();
    }
    
    public void displayMenu() throws InstantiationException, IllegalAccessException, IOException{
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
    		//ArrayList<Instance> testChunk = makeChunks(this.configureStreams.testingStream);
    		
    		/*
    		 *FILTER 
    		 */
    		FilterModule filterM = new FilterModule();
    		trainChunk = filterM.filterChunk(trainChunk);
    		
    		try {
				temp_export_DataSet("0_1_Filtered", trainChunk);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		/*
    		 * TRAIN
    		 */
    		//TrainModule TrainM= new TrainModule(this.buildTask.learnOptions.currentSelected, trainChunk);
    		
    		
    		/*
    		 * TEST
    		 */
    		//TestModule TestM= new TestModule(this.buildTask.learnOptions.currentSelected, testChunk);
    		
    	}	
    }
    
	public void temp_export_DataSet(String outputFileName, ArrayList <Instance> output) throws IOException{ //TODO I can make this more generic using array of Instance instead
		String fileName="";
    	Scanner scan= new Scanner(System.in);
    	System.out.println("Please Enter File Name");
    	fileName= scan.nextLine();
    	
    	FileWriter writer = new FileWriter(new File(fileName));
		//write header
		writer.write(configureStreams.selectedStream.getHeader().toString());
		
		//write delimited data
		for(int i = 0; i < output.size(); i++){
			String s = output.get(i).toString();
			scan = new Scanner(s);
			scan.useDelimiter(",");
			while(scan.hasNext()){
				String n= scan.next() + ",";
				writer.write(n);
				writer.flush();
			}
			writer.write("\n");
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