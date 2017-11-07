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
	public ArrayList<ArffFileStream> streamList;
	//we will need an arraylist of streams that is static here
	//we will need a stream variable that represents the current selected stream, this variable will be useful/accesible in many menus
	
	ConfigureStream(){
		
	}
	
    public void displayMenu(){
    	/*
    	 * Display the contents of the Stream arraylist menu here
    	 */
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Add Stream \n" +
                           "2) Edit Stream \n" +
                           "3) Export Stream \n");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
			//execute Add Stream Menu

			System.out.println("Please enter the name of the file to configure a stream for: ");
			String fileName = userIn.nextLine();
			ArffFileStream fileStream = new ArffFileStream(fileName, -1);

			streamList.add(fileStream);

        	break;
        case 2:
        	editStream.displayMenu();
        	break;
        case 3:
			//export stream Tool
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

			System.out.println(retObj);
        	break;
        }
    }
}
