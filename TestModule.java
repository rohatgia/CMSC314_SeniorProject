import java.util.ArrayList;
import java.util.Scanner;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.AbstractClassifier;
import moa.streams.ArffFileStream;

public class TestModule {
	private ArrayList <Instance> chunk;
	EvaluationOptions eval=null;
	
	public TestModule(AbstractClassifier classifier, ArrayList<Instance> chunk, EvaluationOptions eval){
		this.chunk=chunk;
		testChunk(classifier, chunk);
		this.eval=eval;
	}
	
	private void testChunk(AbstractClassifier classifier, ArrayList<Instance> chunk){
		for(int chunkInst=0; chunkInst < chunk.size(); chunkInst++){
			Instance newInstance = chunk.get(chunkInst); //grabs the next instance from the TRUE Stream
			//Tests the instance and run statistics
			if(!CS314Menu.modelTrained){
				
				//TODO THESE EVALUATION OPTIONS ARE WRONG BUT ARE PLACEHOLDERS FOR NOW
				double prediction = extractPrediction(classifier, newInstance);
				if(prediction == newInstance.classValue()){
					if(newInstance.classValue()==0.0){
						eval.TP++;
					}
					else{
						eval.TN++;
					}
				}
				else{
					if(prediction==0){
						eval.FP++;
					}
					else{
						eval.FN++;
					}
				}
				CS314Menu.modelTrained = false;
			}
			CS314Menu.modelTrained = false;
		}
	}
	
	private double extractPrediction(AbstractClassifier olr, Instance instance){ 
		double[] instanceConfidence = olr.getPredictionForInstance(instance).getVotes();
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