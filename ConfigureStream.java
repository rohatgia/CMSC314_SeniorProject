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
	public static ArffFileStream selectedStream;
	public static ArffFileStream learningStream = new ArffFileStream();
	public static ArffFileStream testingStream = new ArffFileStream();
	public static int totalInstances=0;
	//we will need a stream variable that represents the current selected stream, this variable will be useful/accesible in many menus
	
	ConfigureStream(){
		
	}
	
    public void displayMenu(){
    	boolean exit=false;
    	/*
    	 * Display the contents of the Stream arraylist menu here
    	 */
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n");
        if(selectedStream!=null){
            System.out.println("1) Selected Stream "+ selectedStream.arffFileOption.getFile().getName());
        }
        System.out.println(
                           "2) Add Stream to workspace \n" +
                           "3) Edit Selected Stream \n" +
                           "4) Export Selected Stream \n" +
                           "5) Set Learning Stream "+ this.learningStream.arffFileOption.getFile().getName()+ " \n" +
                           "6) Set Testing Stream "+ this.learningStream.arffFileOption.getFile().getName()+ " \n" +
                           "7) Set total Instances to stream " + this.totalInstances + "\n" +
                           "101) Go Back");
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
        case 7:
        	totalInstances = userIn.nextInt();
        	break;
        case 101:
        	exit=true;
        }
        
        if(!exit){
        	this.displayMenu();
        }
        
    }
    public ArffFileStream setStream(Scanner userIn, int selection){
    	int selectionIdx=selection;
    	boolean exit=false;
    	
    	for(int i=0; i<streamList.size();i++){
    		if(i==selectionIdx){
    			System.out.println(streamList.get(i).arffFileOption.getFile().getName() +"  <<<");
    		}
    		else{
    			System.out.println(streamList.get(i).arffFileOption.getFile().getName());
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
    }
    
    public void addStream(Scanner userIn){

		System.out.println("Please enter the name of the file to configure a stream for: ");
		String fileName = userIn.next();
		System.out.println("Please enter the class index");
		int cIdx = userIn.nextInt();
		ArffFileStream fileStream = new ArffFileStream(fileName, cIdx);
		streamList.add(fileStream);
		
		if(selectedStream==null){
			selectedStream=streamList.get(0);
		}
    }
}
