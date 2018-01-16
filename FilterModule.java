import java.util.ArrayList;
import java.util.Random;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.AbstractClassifier;

public class FilterModule {
	int sizeOfEnsemble=2;
	Random theClaw = new Random();
	ArrayList <AbstractClassifier> ensemble = new ArrayList<AbstractClassifier>();
	
	public ArrayList<Instance> filterChunk(ArrayList <Instance> chunk) throws InstantiationException, IllegalAccessException{
		ArrayList <Instance> trainedChunk = new ArrayList<Instance>();
		ArrayList <Instance> filteredChunk = new ArrayList<Instance>();
		
		//populates the ensemble
		//if(first){
			ensemble = createEnsemble();
		//} 
		//trains the ensemble using random partitions built from the incoming chunk of data
		trainedChunk=trainEnsemble(ensemble, chunk);
		
		//the ensembles filters the chunk through voting
		for(int i=0; i<trainedChunk.size(); i++){
			double[] tally = new double[CS314Menu.configureStreams.learningStream.getHeader().numClasses()];
			int r = theClaw.nextInt(trainedChunk.size());
			Instance nextInst = trainedChunk.get(r);
			trainedChunk.remove(r);
			double prediction=0;
			
			//the ensemble casts its votes
			for(int j=0; j<ensemble.size();j++){			
				tally[(int) extractPrediction(ensemble.get(j), nextInst) ]++;
			}
			
			//extract the highest voted class
			double highest=0;
			for(int j=0; j<tally.length;j++){
				if(tally[j]>highest){
					highest=tally[j];
					prediction=j;
				}
			}
			nextInst.setClassValue(prediction); //Correct the class value
			filteredChunk.add(nextInst);
		}
		
		return filteredChunk;
	}
	
	private double extractPrediction(AbstractClassifier olr, Instance instance){ 
		double[] instanceConfidence = olr.getPredictionForInstance(instance).getVotes();
		String instanceConfidence2 = olr.getPredictionForInstance(instance).toString();
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
	
	private ArrayList <Instance> trainEnsemble(ArrayList<AbstractClassifier> ensemble, ArrayList<Instance> chunk){
		ArrayList<Instance> trainedChunk = new ArrayList<Instance>();
		for(int i=0; i<sizeOfEnsemble; i++){
			ArrayList <Instance> partition=new ArrayList<Instance>();
			
			//build the partition
			for(int j=0; j<chunk.size()/sizeOfEnsemble; j++){
				//randomly remove instances from the chunk so that each partition is random/shuffled instances of the chunk
				int r = theClaw.nextInt(chunk.size()); //choose next instance based on size of the list of instances that havnt been chosen.
				partition.add(chunk.get(r)); //add the Instance to the partition
				chunk.remove(r);//remove the instance from the chunk
			}
			
			//train one of the ensemble classifiers with the partition
			while(!partition.isEmpty()){
				Instance toTrain = partition.get(0);
				partition.remove(partition.get(0));
				ensemble.get(i).trainOnInstance(toTrain);
				trainedChunk.add(toTrain);
			}
		}
		return trainedChunk;
	}
	
	private ArrayList<AbstractClassifier> createEnsemble() throws InstantiationException, IllegalAccessException{
		ArrayList<AbstractClassifier> ensemble = new ArrayList<AbstractClassifier>();
		
		for(int i=0; i<sizeOfEnsemble; i++){
			AbstractClassifier AC = CS314Menu.buildTask.learnOptions.currentSelected.getClass().newInstance();
			AC.prepareForUse();
			ensemble.add(AC);
		}
		return ensemble;
	}
	
	
}
