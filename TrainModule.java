import moa.classifiers.AbstractClassifier;
import moa.streams.ArffFileStream;

public class TrainModule {
	
	public TrainModule(AbstractClassifier classifier, ArffFileStream stream) {
		while(stream.hasMoreInstances()){
			classifier.trainOnInstance(stream.nextInstance().instance);
		}
	}
}
