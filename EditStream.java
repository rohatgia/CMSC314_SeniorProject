import java.util.Scanner;
import moa.streams.ArffFileStream;

public class EditStream {
	private int batchSize=1;
	boolean exit= false;
	EditStream(){
		
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Rename Selected Stream \n" +
                           "2) Remove Attributes in Selected Stream \n"+
                           "3) Batch Size: "+ this.getBatchSize()+ "\n" + 
                           "4) Remove the Selected Stream \n" +
                           "101) Go back");
        
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//Rename selected Stream Button
        	/*
        	 * This feature may not be needed...probably just set the "name" of the stream as the file name
        	 */
        	break;
        case 2:
			//Remove Attributes Tool
        	break;
        case 3:
        	setBatchSize(userIn.nextInt());
        	break;
        case 4:
			// Remove selected stream Button
        	if(ConfigureStream.selectedStream!=null){
        		ConfigureStream.streamList.remove(ConfigureStream.selectedStream);
            	if(!ConfigureStream.streamList.isEmpty()){
            		ConfigureStream.selectedStream=ConfigureStream.streamList.get(0);
            	}
            	else{
            		ConfigureStream.selectedStream=null;
            	}
        	}
        	exit=true;
        	break;

        case 101:
        	exit=true;
        	break;
        }
        
        if(!exit){
        	displayMenu();
        }
    }
    
    /*
     * BatchSize Setter/Getter
     * Allows user to retrieve and set the batch size
     */
	public int getBatchSize() {
		return this.batchSize;
	}
	private void setBatchSize(int batchSize){
		this.batchSize=batchSize;
	}
}
