import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import com.github.javacliparser.FloatOption;
import com.github.javacliparser.IntOption;
import moa.core.FastVector;
import moa.core.InstanceExample;
import moa.core.ObjectRepository;
import moa.options.AbstractOptionHandler;
import moa.streams.InstanceStream;
import moa.tasks.TaskMonitor;
import com.yahoo.labs.samoa.instances.Attribute;
import com.yahoo.labs.samoa.instances.DenseInstance;
import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Instances;
import com.yahoo.labs.samoa.instances.InstancesHeader;

public class DmitriyStartMethod {
    //example generated stream with 100 000 instances and the following class
//imbalance ratios: 1:10 / 1:50 / 1:100 (so three stream files in total).
    public static void DmitriyMain(String[] args) {

        CustomHyperplaneGenerator stream = new CustomHyperplaneGenerator();
        stream.classBiasOption.setValue(1/10);
        //stream.prepareForUse();
        moa.tasks.WriteStreamToARFFFile myTask = new moa.tasks.WriteStreamToARFFFile();
        myTask.arffFileOption.setValue("OneToTenStream");
        myTask.maxInstancesOption.setValue(100000);
        myTask.streamOption.setCurrentObject(stream);
        myTask.prepareForUse();
        myTask.doTask();
    }
}
