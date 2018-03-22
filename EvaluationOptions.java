import java.util.ArrayList;
import java.util.Scanner;

import com.yahoo.labs.samoa.instances.Instance;

public class EvaluationOptions {
	public static double TP=0; //true positive
	public static double TN=0; //true negative
	public static double FP=0; //false positive
	public static double FN=0; //false negative
	public static double ACC=0; //accuracy
	public static double ERR=0; //error rate
	
	public static boolean confusionMatrixOption=true;
	public static boolean accuracyOption=true;
	
	EvaluationOptions(){
		
	}
	public double extractAccuracy(){
		ACC=((TP+TN)/(TP+TN+FP+FN));
		return ACC;
	}
	
    public void displayMenu(){
		boolean exit=false;
		Scanner userIn = new Scanner(System.in);
        System.out.println("Please enter a menu option: \n" +
                           "1) Confusion Matrix:" + confusionMatrixOption + "\n"+
                           "2) Accuracy:" + accuracyOption + "\n"+
				           "101) Go back");
        int userChoice = userIn.nextInt();
        
        switch (userChoice){
        case 1:
        	confusionMatrixOption = !confusionMatrixOption;
        	break;
        case 2:
        	accuracyOption = !accuracyOption;
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
     * k-Disagreeing Neighbors (kDN)
     * kDN measures the local overlap of an instance in the original task space in relation to its nearest neighbors
     * The kDN of an instance is the percentage of the k nearest neighbors (using Euclidean distance) for an instance that do not share its target class value.
     */
    public double kDN(Instance inst, int k, int limit){
    	double[] votes;
    	int disagreeing = 0;
    	moa.classifiers.lazy.kNN kNN= new moa.classifiers.lazy.kNN();
    	kNN.kOption.setValue(k);
    	kNN.limitOption.setValue(limit);
    	votes = kNN.getVotesForInstance(inst);
    	for(int i=0; i<votes.length; i++){
    		if(inst.classValue()!=i){
    			disagreeing += k*votes[i]; //number of instances that are not the target class
    		}
    	}
    	return disagreeing/k;
    }
    
    /*
     * Disjunct Size (DS)
     * DS measures how tightly a learning algorithm has to divide the task space to correctly classify an instance and the complexity of the decision boundary
     * Some learning algorithms, such as decision trees and rule-based learning algorithms, can express the learned concept as a disjunctive description.
     * Thus, the DS of an instance is the number of instances in a disjunct divided by the number of instances covered by the largest disjunct in a dataset.
     */
    public void dS(Instance inst, ArrayList<Instance> currentRecievedData){
    	moa.classifiers.trees.HoeffdingAdaptiveTree hat = new moa.classifiers.trees.HoeffdingAdaptiveTree();
    	//hat.leafpredictionOption.
    }
    
    /*
     * DCP measures the overlap of an instance on a subset of features. using a pruned C4.5 tree.
     * The DCP of an instance is the number of instances in a disjunct belonging to tits class divided by the total number of instances in the disjunct
     * 
     */
    public void dCP(){
    	
    }
    
}
