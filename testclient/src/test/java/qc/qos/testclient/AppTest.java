package qc.qos.testclient;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Copyright (c) 2019 onward, Yulin Wu. All rights reserved.
 * <p>
 * mail4ywu@gmail.com/mail4ywu@icloud.com
 * University of Science and Technology of China
 */
public class AppTest {
    @Test
    public void start(){
        Service.main(new String[]{"start"});
        RunExperiments.main(new String[]{"CreateControlGraph"});
        // RunExperiments.main(new String[]{"MaintainControlGraph"});
    }

    @Test
    public void stop(){ Service.main(new String[]{"stop"}); } // "stop" "restartHardware"

    @Test
    public void service(){Service.main(new String[]{"getQPUInfo"});} // restartHardware getQPUInfo

    @Test
    public void maintenance(){RunExperiments.main(new String[]{"ReloadControlNode"});}

    @Test
    public void runIQ2Probability(){ // SingleQAXYStateTomography
        RunExperiments.main(new String[]{"IQ2Probability"}); // SpectroscopyZPASB
    }

    @Test
    public void runExperiment0(){ // SingleQAXYStateTomography

        RunExperiments.main(new String[]{"RabiAmpParallel"});
        // RunExperiments.main(new String[]{"IQ2Probability"});
        // RabiAmpParallel SpectroscopyZPALo XYZTiming RamseyParallel RabiAmpParallel IQ2ProbabilityQ1 SpectroscopyZPASB SingleQStateTomography
        // RunExperiments.main(new String[]{"RabiAmpParallel"}); // SpectroscopyZPASB Ramsey RabiAmpParallel FSim
        // RunExperiments.main(new String[]{"IMPAGainOptimizerSimplexTriImpl0"}); // SpectroscopyZPASB Ramsey RabiAmpParallel FSim
        // RunExperiments.main(new String[]{"ReadoutIQ0"}); // SpectroscopyZPASB Ramsey RabiAmpParallel FSim
        // RunExperiments.main(new String[]{"IMPABringup0"}); // SpectroscopyZPASB Ramsey RabiAmpParallel FSim
        // RunExperiments.main(new String[]{"T1Paralle"}); // SpectroscopyZPASB
        // RunExperiments.main(new String[]{"IQ2ProbabilityQ2"}); // SpectroscopyZPASB
        // XTGZeroPointCalNodePhaseImpl0 runCorrectF01NodeRabiAmpImpl0 // ControlNodeRunCal MaintainControlGraph CPhaseG ReadoutIQGShift
        //        // IMPAGainOptimizationNodeDETriImpl0 MaintainControlGraph
    }

    // control graph related

    @Test
    public void createGraph() {
        RunExperiments.main(new String[]{"CreateControlGraph"});
    }

    @Test
    public void deleteGraph() {
        RunExperiments.main(new String[]{"DeleteControlGraph"});
    }

    @Test
    public void runNodeCal() {
        RunExperiments.main(new String[]{"ControlNodeRunCal"});  //
    }

    @Test
    public void maintainGraph() {
        RunExperiments.main(new String[]{"MaintainControlGraph"});  // SpectroscopyZPASB
    }

    @Test
    public void setNodeAllOutOfSpec() {
        RunExperiments.main(new String[]{"SetNodeAllOutOfSpec"});
    }

    @Test
    public void setNodeAllInSpec() {
        RunExperiments.main(new String[]{"SetNodeAllInSpec"});
    }


    /*

    @Test
    public void runCorrectF01(){
        RunExperiments.main(new String[]{"CorrectF01NodePhaseTomoImpl0"});
    }
    @Test
    public void reload(){
        RunExperiments.main(new String[]{"reload"});
    }

     */
    

}
