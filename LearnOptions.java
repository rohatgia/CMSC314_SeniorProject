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


	//create ensemble options with default values
	private static Pair<String,String> option0 = new Pair("Enabled","true");
	private static Pair<String,String> option1 = new Pair("Chunk Size","300");
	private static Pair<String,String> option2 = new Pair("Window Size","10");
	private static Pair<String,String> option3 = new Pair("Ensemble Pool","20");
	private static Pair[] ensembleOptions = {option0,option1,option2,option3};

	LearnOptions(){
		classifiers.add(new moa.classifiers.bayes.NaiveBayes());
		classifiers.add(new moa.classifiers.trees.HoeffdingAdaptiveTree());
		classifiers.add(new moa.classifiers.functions.Perceptron());
		classifiers.add(new moa.classifiers.trees.HoeffdingAdaptiveTree());
		
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
        System.out.println("2) Add Algorithm to workspace");
        System.out.println("3) Edit Parameters");
        System.out.println("4) Edit Ensemble");
        if(currentSelected!=null){
            System.out.println("5) Remove selected algorithm");
        }
    	System.out.println("101) go back");
        
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
			editEnsemble(userIn, 0);
			break;
        case 5:
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

	public void editEnsemble(Scanner userIn, int selection) {
		boolean exit = false;
		int selectionIdx = selection;

		for (int i = 0; i < ensembleOptions.length; i++) {
			if (i == selectionIdx) {
				System.out.println(ensembleOptions[i].getKey() + "  :   " + ensembleOptions[i].getValue() + "  <<<");
			} else {
				System.out.println(ensembleOptions[i].getKey() + "  :   " + ensembleOptions[i].getValue());
			}
		}
		System.out.println("press 'e' to select or 'w'/'s' to scroll and \"exit\" to go back");
		String input = userIn.next();
		switch (input) {
			case "e":
				System.out.println("Please enter a value for  " + ensembleOptions[selectionIdx].getKey());
				switch (selectionIdx) {
					case 0:
						String temp = userIn.next();
						if (temp.equals("false") || temp.equals("true")){
							ensembleOptions[selectionIdx].setValue(temp);
						} else {
							System.out.println("\nError: Please type \"true\" or \"false\"");
						}
					break;
					case 1:
						ensembleOptions[selectionIdx].setValue(userIn.next());
					break;
					case 2:
						String window = userIn.next();
						String ensemblePool = ensembleOptions[3].getValue().toString();
						if (Integer.valueOf(window)<=Integer.valueOf(ensemblePool)){
							ensembleOptions[selectionIdx].setValue(userIn.next());
						} else {
							System.out.println("\nError: Please type a number less than or equal to ensemble pool");
						}
					break;
					case 3:
						String window2 = ensembleOptions[2].getValue().toString();
						String ensemblePool2 = userIn.next();
						if (Integer.valueOf(ensemblePool2)>=Integer.valueOf(window2)){
							ensembleOptions[selectionIdx].setValue(userIn.next());
						} else {
							System.out.println("\nError: Please type a number greater then or equal to window size");
						}
					break;
				}
			case "w":
				if (selectionIdx > 0) {
					selectionIdx--;
				}
				break;
			case "s":
				if (selectionIdx < ensembleOptions.length - 1) {
					selectionIdx++;
				}
				break;
			case "exit":
				exit = true;
				break;
		}
		System.out.println();

		if (!exit) {
			editEnsemble(userIn, selectionIdx);
		}
	}
	public static boolean getEnsembleStatus(){
		if (ensembleOptions[0].getValue().toString().equals("true")){
			return true;
		} else {
			return false;
		}
	}
	public static int[] getEnsembleParameters(){
		int[] parameters = {Integer.valueOf(ensembleOptions[1].getValue().toString()),
							Integer.valueOf(ensembleOptions[2].getValue().toString()),
							Integer.valueOf(ensembleOptions[3].getValue().toString())};
		return parameters;
	}
}

class Pair<K,V> {
	private K key;
	private V value;
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}
	public K getKey(){return key;}
	public V getValue(){return value;}
	public void setKey(K key){this.key = key;}
	public void setValue(V value){this.value = value;}
}