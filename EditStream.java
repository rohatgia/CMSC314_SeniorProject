import java.util.Scanner;
import moa.streams.ArffFileStream;

public class EditStream {
	private int batchSize=1;
	
	EditStream(){
		ArffFileStream currentStream = ConfigureStream.streamList.get(0);
	}
	
    public void displayMenu(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Select Stream \n" +
                           "2) Rename Selected Stream \n" +
                           "3) Remove Attributes in Selected Stream \n"+
                           "4) Batch Size: "+ this.getBatchSize()+ "\n" + 
                           "5) Remove the Selected Stream");
        
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	//Select Stream Button
        	break;
        case 2:
        	//Rename selected Stream Button
        	/*
        	 * This feature may not be needed...probably just set the "name" of the stream as the file name
        	 */
        	break;
        case 3:
			//Remove Attributes Tool
			//is this supposed to call the arraylist in CS314Menu? Which attributes are we editing, the number of iterations and such? or other arff stream attributes
        	break;
        case 4:
        	setBatchSize(userIn.nextInt());
        	break;
        case 5:
			// Remove selected stream Button
			for(int i = 0;i<ConfigureStream.streamList.size();i++){
				if currentStream.equals(ConfigureStream.streamList.get(i)){
					ConfigureStream.streamList.remove(i);
				}
			}
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

	public String[] getStreamDetails(ArffFileStream stream){
		String[] attributes = getAttributes(stream);
		String[] retArr = new String[1];
		return retArr;
		//placeholder method
	}
}
