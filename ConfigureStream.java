import java.util.ArrayList;
import java.util.Scanner;
import moa.tasks.*;
import moa.streams.ArffFileStream;

//Added Functionality on 11/6/2017: Ayush Rohatgi
//Added functionality to the add stream and export stream options
//These are not complete, and non-functional right now.
//Discussion needed in Tuesday meeting to determine storeage location

public class ConfigureStream {
	public static EditStream editStream= new EditStream();
	public static ArrayList<ArffFileStream> streamList = new ArrayList<ArffFileStream>();
	public static ArffFileStream selectedStream = new ArffFileStream();
	public static ArffFileStream learningStream = new ArffFileStream();
	public static ArffFileStream testingStream = new ArffFileStream();
	//we will need a stream variable that represents the current selected stream, this variable will be useful/accesible in many menus
	
	ConfigureStream(){
		
	}
	
    public void displayMenu(){
    	/*
    	 * Display the contents of the Stream arraylist menu here
    	 */
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Select Stream \n" +
                           "2) Add Stream to workspace \n" +
                           "3) Edit Selected Stream \n" +
                           "4) Export Selected Stream \n" +
                           "5) Set Learning Stream \n" +
                           "6) Set Testing Stream");
        int userChoice = userIn.nextInt();
        switch (userChoice){
        case 1:
        	selectedStream = setStream(userIn, 0);
        	break;
        case 2:
        	//execute Add Stream Menu
        	addStream(userIn);
        	break;
        case 3:
        	editStream.displayMenu();
        	break;
        case 4:
        	//export stream Tool
        	exportStream();
        	break;
        case 5:
        	learningStream = setStream(userIn, 0);
        	break;
        case 6:
        	testingStream = setStream(userIn, 0);
        	break;
        
        }
    }
    public ArffFileStream setStream(Scanner userIn, int selection){
    	int selectionIdx=selection;
    	boolean exit=false;
    	
    	for(int i=0; i<streamList.size();i++){
    		if(i==selectionIdx){
    			System.out.println(streamList.get(i).arffFileOption.getName().toString() +"  <<<");
    		}
    		else{
    			System.out.println(streamList.get(i).arffFileOption.getName().toString());
    		}
    	}
    	System.out.println("press 'e' to select or 'w'/'s' to scroll");
    	String input= userIn.next();
    	switch (input){
    	case "e":
    		return streamList.get(selectionIdx);
    	case "w":
    		if(selectionIdx>0){
    			selectionIdx--;
    		}
    		break;
    	case "s":
       		if(selectionIdx<streamList.size()-1){
    			selectionIdx++;
    		}
    		break;
    	}
    	if(!exit){
    		setStream(userIn,selectionIdx);
    	}
    	
		return null;
    }
    
    public void exportStream(){
    	
		WriteStreamToARFFFile export = new WriteStreamToARFFFile();
		export.streamOption.setCurrentObject(selectedStream);
		export.outputFileOption.setValue(""); //FILENAME GOES HERE
		export.doTask();
		
/*			
		System.out.println("Select Stream to export: ");
		int count = 0;
		for(ArffFileStream i : streamList){
			System.out.println(count++ + i.toString());
		}

		String[] streamDetails = editStream.getStreamDetails(streamList(userIn.nextInt())); //gets current selected stream, gets the streams name as a string, gets the number of iterations from the...
		//alternatively get all this information from the tasks arraylist in the menu
		WriteStreamToARFFFFile.streamOption = new ClassOption("stream", 's', "Stream to write", InstaceStream.class, streamDetails[0]);
		WriteStreamToARFFFFile.arffFileOption = new FileOption("arffFile", 'f', "Destination ARFF file.", streamDetails[1], "arff", true);
		WriteStreamToARFFFFile.maxInstancesOption = new IntOption("maxInstances", 'm', "Maximum number of instances to write to file.", Integer.parseInt(streamDetails[2]), 0, Integer.MAX_VALUE);

		ObjectRepository repo = new ObjectRepository(); //check parameters
		TaskMonitor tm = new TaskMonitor(); //check parameter
		Object retObj = WriteStreamToARFFFFile.doMainTask(tm, repo);

		System.out.println(retObj);*/
    }
    
    public void addStream(Scanner userIn){

		System.out.println("Please enter the name of the file to configure a stream for: ");
		String fileName = userIn.nextLine();
		System.out.println("Please enter the class index");
		int cIdx = userIn.nextInt();
		ArffFileStream fileStream = new ArffFileStream(fileName, cIdx);
		streamList.add(fileStream);
    }
}
