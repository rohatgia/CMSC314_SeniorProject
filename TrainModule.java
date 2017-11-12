import java.util.ArrayList;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.AbstractClassifier;
import moa.streams.ArffFileStream;

public class TrainModule {
	
	public TrainModule(AbstractClassifier classifier, ArrayList<Instance> chunk) {
		for(int i=0; i<chunk.size(); i++){
			classifier.trainOnInstance(chunk.get(i));
		}
	}
}
