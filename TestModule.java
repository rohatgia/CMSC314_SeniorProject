import java.util.ArrayList;
import java.util.Scanner;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.AbstractClassifier;
import moa.streams.ArffFileStream;

public class TestModule {
	boolean first=true;
	private ArffFileStream stream;
	private evalBinaryClass eval= new evalBinaryClass();
	
	public TestModule(AbstractClassifier classifier, ArffFileStream stream){
		this.stream=stream;
	}
	
	private void testChunk(AbstractClassifier classifier, ArrayList<Instance> chunk){
		for(int chunkInst=0; chunkInst < CS314Menu.tasks.chunkSizeOption.getValue(); chunkInst++){
			Instance newInstance = chunk.get(chunkInst); //grabs the next instance from the TRUE Stream
			//Tests the instance and run statistics
			if(!first){
				if(extractPrediction(classifier,newInstance) == newInstance.classValue()){
					eval.totalCorrect++;
					eval.totalPredicted++;
				}
				else{
					eval.totalPredicted++;
				}
				first=false;
			}
		}
	}
	
	private double extractPrediction(AbstractClassifier olr, Instance instance){ 
		double[] instanceConfidence= olr.getPredictionForInstance(instance).getVotes();
		double predictedLabel= 0;
		
		double highest = instanceConfidence[0];
		for(int i=0; i<instanceConfidence.length;i++){
			if(instanceConfidence[i]>highest){
				highest=instanceConfidence[i];
				predictedLabel= i;
			}
		}
		return predictedLabel;
	}
	

}

/*
 * need to be able to use confusion matrix meausures instead of total correct/incorrect 
 */
class evalBinaryClass{
	public double totalCorrect=0;
	public double totalPredicted=0;
	public double truePositive=0;
	public double trueNegative=0;
	public double falsePositive=0;
	public double falseNegative=0;
	
	public evalBinaryClass(){
		truePositive=0;
		trueNegative=0;
		falsePositive=0;
		falseNegative=0;
		totalCorrect=0;
	}
	
}