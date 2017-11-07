import java.util.ArrayList;
import java.util.Scanner;

import com.github.javacliparser.Option;

import moa.classifiers.AbstractClassifier;
import moa.classifiers.*;
import moa.options.AbstractOptionHandler;

public class LearnOptions {
	public AbstractClassifier currentSelected;
	public static ArrayList <AbstractClassifier> classifiers = new ArrayList<AbstractClassifier>(); //arraylist of classifiers currently supported by our program
	public static ArrayList <AbstractClassifier> userSelectedClassifiers= new ArrayList<AbstractClassifier>(); //arraylist of the user selected algorithms that they want in their workspace
	
	LearnOptions(){
		classifiers.add(new moa.classifiers.bayes.NaiveBayes());
		classifiers.add(new moa.classifiers.trees.HoeffdingAdaptiveTree());
		classifiers.add(new moa.classifiers.functions.Perceptron());
		
		try {userSelectedClassifiers.add(classifiers.get(0).getClass().newInstance());
		} catch (InstantiationException e) {e.printStackTrace(); System.out.println(e.getMessage());
		} catch (IllegalAccessException e) {e.printStackTrace(); System.out.println(e.getMessage());}
		currentSelected = userSelectedClassifiers.get(0);
	}
	
    public void displayMenu(){
    	boolean exit=false;
    	System.out.println();
    	
    	if(userSelectedClassifiers.isEmpty()){
    		System.out.println("No Algorithms currently added to the workspace");
    	}
    	else{
    		for(AbstractClassifier c : userSelectedClassifiers){
    			System.out.println(c.getClass().toString());
    		}
    		System.out.println();
    	}
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option:");
        if(currentSelected!=null){
        	System.out.println("1) Selected Algorithm: " + currentSelected.getClass().toString());
        }
        System.out.println("2) Add Algorithm to workspace \n" +
                           "3) Edit Parameters");
        if(currentSelected!=null){
            System.out.println("4) Remove selected algorithm");
        }
    	System.out.println("101) go back \n");
        
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	if(currentSelected!=null){
            	selectAlgorithm(userIn,0);
        	}
        	break;
        case 2:
        	addAlgorithm(userIn,0);
        	break;
        case 3:
        	editParameters(userIn,0);
        	break;
        case 4:
        	if(currentSelected!=null){
        		userSelectedClassifiers.remove(currentSelected);
            	if(!userSelectedClassifiers.isEmpty()){
            		currentSelected=userSelectedClassifiers.get(0);
            	}
            	else{
            		currentSelected=null;
            	}
        	}
        	break;
        case 101:
        	exit=true;
        	break;
        
        }
        if(!exit){
        	displayMenu();
        }
    }
    
    public void selectAlgorithm(Scanner userIn, int selection){
    	int selectionIdx=selection;
    	boolean exit=false;
    	
    	for(int i=0; i<userSelectedClassifiers.size();i++){
    		if(i==selectionIdx){
    			System.out.println(userSelectedClassifiers.get(i).getClass().toString() +"  <<<");
    		}
    		else{
    			System.out.println(userSelectedClassifiers.get(i).getClass().toString());
    		}
    	}
    	System.out.println("press 'e' to select or 'w'/'s' to scroll");
    	String input= userIn.next();
    	switch (input){
    	case "e":
    		currentSelected=userSelectedClassifiers.get(selectionIdx);
    		exit=true;
    		break;
    	case "w":
    		if(selectionIdx>0){
    			selectionIdx--;
    		}
    		break;
    	case "s":
       		if(selectionIdx<userSelectedClassifiers.size()-1){
    			selectionIdx++;
    		}
    		break;
    	}
    	if(!exit){
    		selectAlgorithm(userIn,selectionIdx);
    	}
    }
    
    public void addAlgorithm(Scanner userIn, int selection){
    	int selectionIdx=selection;
    	boolean exit=false;
    	
    	for(int i=0; i<classifiers.size();i++){
    		if(i==selectionIdx){
    			System.out.println(classifiers.get(i).getClass().toString() +"  <<<");
    		}
    		else{
    			System.out.println(classifiers.get(i).getClass().toString());
    		}
    	}
    	System.out.println("press 'e' to select or 'w'/'s' to scroll");
    	String input= userIn.next();
    	switch (input){
    	case "e":
    		try {
				userSelectedClassifiers.add(classifiers.get(selectionIdx).getClass().newInstance());} 
    		catch (InstantiationException e) {e.printStackTrace();System.out.println(e.getMessage());}
    		catch (IllegalAccessException e) {e.printStackTrace();System.out.println(e.getMessage());}
    		currentSelected=userSelectedClassifiers.get(userSelectedClassifiers.size()-1);
    		exit=true;
    		break;
    	case "w":
    		if(selectionIdx>0){
    			selectionIdx--;
    		}
    		break;
    	case "s":
    		if(selectionIdx<classifiers.size()-1){
    			selectionIdx++;
    		}
    		break;
    	}
    	if(!exit){
    		addAlgorithm(userIn,selectionIdx);
    	}
    }
    
    public void editParameters(Scanner userIn, int selection){
    	Option[] optionArr;
    	optionArr = currentSelected.getOptions().getOptionArray();
    	boolean exit = false;
    	int selectionIdx= selection;

    	for(int i=0; i<optionArr.length;i++){
    		if(i==selectionIdx){
    			System.out.println(optionArr[i].getName() + "  :   "+optionArr[i].getValueAsCLIString() +"  <<<");
    		}
    		else{
    			System.out.println(optionArr[i].getName() + "  :   "+optionArr[i].getValueAsCLIString());
    		}
    	}
    	
    	System.out.println("press 'e' to select or 'w'/'s' to scroll and \"exit\" to go back");
    	String input= userIn.next();
    	switch (input){
    	case "e":
    		System.out.println("Please enter a value for  "+ optionArr[selectionIdx].getName());
    		optionArr[selectionIdx].setValueViaCLIString(userIn.next());
    		break;
    	case "w":
    		if(selectionIdx>0){
    			selectionIdx--;
    		}
    		break;
    	case "s":
    		if(selectionIdx<optionArr.length-1){
    			selectionIdx++;
    		}
    		break;
    	case "exit":
    		exit=true;
    		break;
    	}
    	System.out.println();
    	
    	if(!exit){
    		editParameters(userIn,selectionIdx);
    	}
    }
}