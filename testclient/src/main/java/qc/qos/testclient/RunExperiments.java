package qc.qos.testclient;

import org.apache.log4j.BasicConfigurator;
import qc.qos.rpcapi.qpx1701.QOSServerApi;
import qc.qos.rpc.Constants;
import qc.qos.util.ArrayHelper;
import qc.qos.util.calibration.F01ToCoherenceMapper;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (c) 2019 onward, Yulin Wu. All rights reserved.
 * <p>
 * mail4ywu@gmail.com/mail4ywu@icloud.com
 * University of Science and Technology of China
 */
public final class RunExperiments {
    private static void callService(String options) {
        BasicConfigurator.configure();

        String controlGraphName = Config.controlGraphName;
        // String controlGraphName = null;
        QOSServerApi serverApi;
        try {
            serverApi = new QOSServerApi(Config.experimentUserName, Config.experimentUserPassword,
                    Config.qosServerHost, Constants.DEFAULT_PORT);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        serverApi.login(Config.experimentUserPassword, Config.userAgents, Config.settingsStoreUserName,
                Config.settingsStorePassword, Config.session, Config.dataPath, controlGraphName);
        switch (options){
            case "reload":
                serverApi.reloadSettings(Config.experimentUserPassword);
                break;
            case "logout":
                serverApi.logout(Config.experimentUserPassword);
                break;
            case "ReadoutIQ0": // tested OK
                serverApi.runReadoutIQ(new String[]{"R01","R02","R03","R04","R05","R06","R07","R08","R09","R10","R11"},
                        ArrayHelper.newArray(11, 3.2e4f), // readout amplitudes
                        ArrayHelper.newArray(11, -0.4e9f), // sidebandFrequencies
                        true, -30f,
                        new float[][]{
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),
                                ArrayHelper.arange(4e9f, 7.5e9f, 5e6f),

                        },
                        false, true, 2000, "");
                break;
            case "ReadoutIQ1": // tested OK
                float[] frequency = ArrayHelper.arange(6.591e9f-1.5e6f, 6.591e9f+1.5e6f, 0.5e9f); //6.525e9f, 6.535e9f, 0.5e6f

                // float[] frequency = ArrayHelper.arange(6.63102e9f-10e6f, 6.63102e9f-10e6f, 0.6e6f); //6.525e9f, 6.535e9f, 0.5e6f
                serverApi.runReadoutIQ(new String[]{"Q1"},
                        new String[]{"Q1"}, new String[]{"I"}, new String[]{"Q1"},
                        false, false, false, false,
                        new float[][]{frequency},
                        new float[][]{{9000}}, // readoutAmplitudes
                        new float[][]{ArrayHelper.linspace(-3.2e4f, 3.2e4f, 5)}, // bias amplitudes
                        true,true,1500, "test");
                /*
                // multiple qubits, tested
                float[] frequencyQ6 = ArrayHelper.arange(6.6354e9f-3e6f, 6.6354e9f+3e6f, 0.2e6f);
                serverApi.runReadoutIQ(new String[]{"Q2","Q1"},
                        new String[]{}, new String[]{}, new String[]{"Q2"}, false,
                        false, true,
                        new float[][]{frequencyQ6,
                                ArrayHelper.linspace(6.7036e9f-3e6f, 6.7036e9f+3e6f, frequencyQ6.length)},
                        new float[][]{ArrayHelper.logspace(2e4f, 3e4f, 1),
                                ArrayHelper.logspace(1e3f, 3e3f, 1)},
                        new float[][]{{0f}},
                        true,true,1500, "test");
                */
                /*
                // multiple qubits, tune amplitude, tested
                float[] frequencyQ6 = ArrayHelper.arange(6.6358e9f - 1e6f, 6.6358e9f + 0.4e6f, 0.05e6f);
                serverApi.runReadoutIQ(new String[]{"Q2", "Q1"},
                        new String[]{}, new String[]{}, new String[]{"Q2"}, false,
                        false, true,
                        new float[][]{frequencyQ6,
                                ArrayHelper.linspace(6.7036e9f - 1e6f, 6.7036e9f + 0.4e6f, frequencyQ6.length)},
                        new float[][]{ArrayHelper.logspace(1e3f, 3e4f, 50),
                                ArrayHelper.logspace(1e3f, 3e4f, 50)},
                        new float[][]{{0f}},
                        true, true, 1500, "test");

                 */
                /*
                // multiple qubits, bias zpa, tested
                float[] frequencyQ6 = ArrayHelper.arange(6.6358e9f - 0.8e6f, 6.6358e9f + 0.4e6f, 0.03e6f);
                float[] fastbiasAmplitudes = ArrayHelper.linspace(-3e4f, 3e4f, 50);
                serverApi.runReadoutIQ(new String[]{"Q2", "Q1"},
                        new String[]{}, new String[]{}, new String[]{"Q2", "Q1"}, false,
                        false, true,
                        new float[][]{frequencyQ6,
                                ArrayHelper.linspace(6.7036e9f - 0.8e6f, 6.7036e9f + 0.4e6f, frequencyQ6.length)},
                        new float[][]{ArrayHelper.logspace(5e3f, 3e4f, 1),
                                ArrayHelper.logspace(5e3f, 3e4f, 1)},
                        new float[][]{fastbiasAmplitudes,fastbiasAmplitudes},
                        true, true, 1500, "test");
                 */
                /*
                // multiple qubits, bias zdc, tested
                float[] frequencyQ6 = ArrayHelper.arange(6.6358e9f - 0.8e6f, 6.6358e9f + 0.4e6f, 0.03e6f);
                float[] dcbiasAmplitudes = ArrayHelper.linspace(-50e4f, 50e4f, 50);
                serverApi.runReadoutIQ(new String[]{"Q2", "Q1"},
                        new String[]{}, new String[]{}, new String[]{"Q2", "Q1"}, true,
                        false, true,
                        new float[][]{frequencyQ6,
                                ArrayHelper.linspace(6.7036e9f - 0.8e6f, 6.7036e9f + 0.4e6f, frequencyQ6.length)},
                        new float[][]{ArrayHelper.logspace(5e3f, 3e4f, 1),
                                ArrayHelper.logspace(5e3f, 3e4f, 1)},
                        new float[][]{dcbiasAmplitudes,dcbiasAmplitudes},
                        true, true, 1500, "test");
                 */
                break;
            case "IMPABringup0": //
                serverApi.runIMPABringup0("A1", "R1",
                        ArrayHelper.arange(6000f, 6000f, 1e9f), // signalAmplitude,
                        ArrayHelper.arange(6.55e+9f, 6.55e+9f, 20e9f), // signalFrequency,
                        400e6f, // signalSidebandFrequency,
                        ArrayHelper.arange(-4.5e5f, 4.5e5f, 1e4f), // biasAmplitude,
                        ArrayHelper.arange(0f, 0e4f, 0.5e9f), // pumpAmplitude,
                        // ArrayHelper.arange(12.62e9f, 12.62e9f, 500e6f), // pumpLoFrequency,
                        ArrayHelper.arange(3e9f, 3e9f, 200e6f), // pumpLoFrequency,
                        ArrayHelper.arange(0f, 0f, 5e6f), // pumpSidebandFrequencies,
                        false,true,1500, "test run");
                break;
            case "IMPABringup1": //
                serverApi.runIMPABringup1(new String[]{"A1"}, new String[]{"R1"}, true,
                        new float[][]{ArrayHelper.arange(0f, 0f, 1e4f)}, // biasAmplitudes,
                        new float[][]{ArrayHelper.arange(100f, 3.2e4f, 1e3f)}, // pumpAmplitudes,
                        new float[][]{ArrayHelper.arange(12.62e9f, 12.62e9f, 500e6f)}, // pumpLoFrequencies,
                        new float[][]{ArrayHelper.arange(0f, 0f, 5e6f)}, // pumpSidebandFrequencies,
                        false,true,1500, "test run");
                break;
            case "IMPAGainOptimizerSimplexTriImpl0": //
                serverApi.runIMPAGainBaseOptimizerSimplexTriImpl0(new String[]{"A1"}, new String[]{"R1"},
                        true, -30, 200,
                        new float[]{-5e5f}, new float[]{5e5f},
                        new float[]{0}, new float[]{3.2e4f},
                        new float[]{10e9f}, new float[]{15e9f},
                        new float[]{5e4f}, new float[]{5e3f}, new float[]{100e6f}, 10f, 20f, 30f, 2e9f,
                        false,true,1500);
                break;
            case "SpectroscopyZDCLo": //
                serverApi.runSpectroscopyZDCLo("Q2", 8000, 8000,
                        200e6f, "Q2", "Q2", "IQ",
                        ArrayHelper.arange(-0.5e4f,0.1f,2e3f),
                        ArrayHelper.arange(5e9f, 5.05e9f, 5e6f), false,
                        true, 1500, "test run");
                break;
            case "SpectroscopyZPALo": // tested OK
                serverApi.runSpectroscopyZPALo("Q1", 5000, 500, -400e6f,
                        "Q1", "Q1",
                        new String[]{},new float[]{},  // detuneAgents, detuneAmplitudes,
                        "P1",
                        ArrayHelper.arange(0e4f, 2e4f, 1E9f), //biasAmplitudes
                        ArrayHelper.arange(4.977e9f-30e6f, 4.977e9f+30e6f, 0.3e6f), //driveFrequencies
                        // ArrayHelper.arange(4.757e9f-15e6f, 4.757e9f+15e6f, 0.5e6f), //driveFrequencies
                        false,true,2000, "test run");
                break;
            case "SpectroscopyZPALoAFR": //
                serverApi.runSpectroscopyZPALoAFR("Q2", 4000,
                        1000, 0.1F, 200e6f,
                        20e6f, 2e6f,
                        new String[]{},new float[]{},  // detuneAgents, detuneAmplitudes,
                        ArrayHelper.arange(-3e4f,0f, 0.25e4f),
                        "XMON_F01_FIXED_AH", -225e6f, 5e6f, 1/3e4f,
                        false,true,1500, "test run");
                break;
            case "Anharmonicity": // tested OK
                serverApi.runAnharmonicity(new String[]{"Q2"},500, new float[]{4e3f},
                        new float[][]{
                                ArrayHelper.arange(-300e6f, -150e6f, 0.5e6f),
                        },
                        false, true,1500, "test run");
                break;
            case "SpectroscopyZPASB": //
                serverApi.runSpectroscopyZPASB(new String[]{"Q2"}, 6000, new float[]{500f}, // driveAmplitudes
                        4.7e9f, new String[]{"G12"},  // biasAgents,
                        false, new String[]{"Q1"}, // detuneAgents
                        new float[]{-2e4f},  // detuneAmplitudes,
                        "P1",
                        new float[][]{
                                ArrayHelper.arange(-3e4f, 1e4f, 1000f)
                        }, //biasAmplitudes
                        new float[][]{
                                ArrayHelper.arange(4.86e9f-35e6f, 4.86e9f+25e6f, 0.3e6f)
                        }, //driveFrequencies
                        false,true,1500, "Q2: compensation on");
                /*
                serverApi.runSpectroscopyZPASB(new String[]{"Q2"}, 6000, new float[]{500f}, // driveAmplitudes
                        4.7e9f, new String[]{"G12"},  // biasAgents,
                        true, new String[]{"Q1"}, // detuneAgents
                        new float[]{-2e4f},  // detuneAmplitudes,
                        "P1",
                        new float[][]{
                                ArrayHelper.arange(-3e4f, 1e4f, 1000f)
                        }, //biasAmplitudes
                        new float[][]{
                                ArrayHelper.arange(4.86e9f-35e6f, 4.86e9f+25e6f, 0.3e6f)
                        }, //driveFrequencies
                        false,true,1500, "Q2: compensation off");
                serverApi.runSpectroscopyZPASB(new String[]{"Q1"}, 6000, new float[]{500f}, // driveAmplitudes
                        4.7e9f, new String[]{"G12"},  // biasAgents,
                        false, new String[]{"Q2"}, // detuneAgents
                        new float[]{-2e4f},  // detuneAmplitudes,
                        "P1",
                        new float[][]{
                                ArrayHelper.arange(-3e4f, 1e4f, 1000f)
                        }, //biasAmplitudes
                        new float[][]{
                                ArrayHelper.arange(4.8e9f-35e6f, 4.8e9f+25e6f, 0.3e6f)
                        }, //driveFrequencies
                        false,true,1500, "Q1: compensation on");
                serverApi.runSpectroscopyZPASB(new String[]{"Q1"}, 6000, new float[]{500f}, // driveAmplitudes
                        4.7e9f, new String[]{"G12"},  // biasAgents,
                        true, new String[]{"Q2"}, // detuneAgents
                        new float[]{-2e4f},  // detuneAmplitudes,
                        "P1",
                        new float[][]{
                                ArrayHelper.arange(-3e4f, 1e4f, 1000f)
                        }, //biasAmplitudes
                        new float[][]{
                                ArrayHelper.arange(4.8e9f-35e6f, 4.8e9f+25e6f, 0.3e6f)
                        }, //driveFrequencies
                        false,true,1500, "Q1: compensation off");

                 */
                break;
            case "RabiAmp": // tested OK
                serverApi.runRabiAmpDetune(new String[]{"Q1"},new String[]{"Q1"}, "X2P", 2, "IQ",
                        ArrayHelper.arange(0e4f, 3.2e4f, 10), //xyAmplitudes
                        ArrayHelper.arange(-50e6f, 50e6f, 5e9f), // detunings
                        false,true,1500, "test run");
                break;
            case "RabiAmpParallel": // tested OK
                serverApi.runRabiAmpDetune(new String[]{"Q63"}, new String[]{}, new float[]{-0e4f},
                        new String[]{"X2P"}, new int[]{4}, // num drive gate
                        "IQ",
                        new float[][]{ArrayHelper.arange(0f, 3.2e4f, 5e2f)}, //xyAmplitudes
                        // new float[][]{{1e4f, 1.5e4f, 2e4f, 2.5e4f, 3e4f}}, //xyAmplitudes
                        new float[][]{ArrayHelper.arange(-15e6f, 15e6f, 3e6f)}, // detunings
                        false,true,2000, "test run");
                break;
            case "T1": // tested OK
                serverApi.runT1("Q1","P1", false,
                        ArrayHelper.arange(0f, 0e4f, 1e3f), //detuneAmplitudes
                        ArrayHelper.arange(0, (int) 2e3, (int) 1e3), // delays
                        false,true,1500, "test run");
                break;
            case "T1Paralle": // tested OK
                // @NonNull String[] qubits,
                //            @NonNull String dataType, boolean measureReference, boolean detuneInFrequency,
                //            @NonNull float[][] detuneAmplitudes, @NonNull int[][] delays,
                //            boolean continuousExecution, boolean enableParallelMode,int numShots, @NonNull String notes
                serverApi.runT1(new String[]{"Q63"},"P1", true, false,
                        new float[][]{ArrayHelper.arange(-3e4f, 3e4f, 2e3f)}, //detuneAmplitudes
                        new int[][]{ArrayHelper.arange(0, (int) 4.5e4, (int) 1e3)}, // delays
                        false,true,1500, "");
                break;
            case "Ramsey": // tested OK
                serverApi.runRamsey("Q2", 0, "P", false,
                        ArrayHelper.arange(0, 200, 20), //delays
                        // ArrayHelper.arange(0e3f, 30e3f, 5e3f), // detunings
                        ArrayHelper.arange(5e6f, 250e6f, 5e9f), // detunings
                        false,true,1500, "test run");
                break;
            case "RamseyParallel": // tested OK
                serverApi.runRamsey(new String[]{"Q1"},
                        new String[]{}, new float[]{}, // workBiasQubits, workBiasAmplitudes
                        new int[]{0}, "PHASE", true,
                        new int[][]{ArrayHelper.arange(0, 6000, 50)}, //delays
                        new float[][]{ArrayHelper.arange(0e6f, 10e6f, 2e9f)}, // detunings
                        false,true,1000, "test run");
                break;
            case "RamseyEnvelop": // tested OK
                serverApi.runRamseyEnvelop("Q1", 0, new float[]{0f},
                        ArrayHelper.arange(0, 100, 10), //delays
                        false,true,1500, "test run");
                break;
            case "RamseyEnvelopParallel": // tested OK
                serverApi.runRamseyEnvelop(new String[]{"Q1"}, 0, new float[][]{{0f}},
                        new int[][]{ArrayHelper.arange(0, 200, 20)}, //delays
                        false,true,1500, "test run");
                break;
            case "DRAGAlphaAPE": // tested OK
                serverApi.runDRAGAlphaAPE(new String[]{"Q1"},
                        new float[][]{ArrayHelper.arange(-3f,3f, 0.05f)}, new int[]{15},
                        false,true,1500, "test run");
                break;
            case "XYGateDetuning":
                serverApi.runXYGateDetuning(new String[]{"Q1"}, new int[]{23}, new float[]{0.5f},
                        new float[][]{ArrayHelper.arange(-50e6f,50e6f, 1e6f)},
                        false,true,1500, "test run");
                break;
            case "ZDistortion": // tested OK
                serverApi.runZDistortion(new String[]{"Q2"}, new float[]{-3e4f},800,
                        ArrayHelper.linlogspace(0,3000, 100),
                        "FIXED_MEASURE", 800,
                        false,true,2000, "test run");
                break;
            case "ReadoutPhotonNumber": // tested, data not checked
                serverApi.runReadoutPhotonNumber(new String[]{"R1"}, new String[]{"Q1"},
                        new int[]{1000}, new int[]{30}, new float[]{2.2e4f},"P", // readoutDriveLengths, qubitDriveLengths, qubitDriveAmplitudes,dataType
                        new float[][]{ArrayHelper.arange(4.858e9f - 100e6f,4.858e9f - 20e6f, 4e6f)}, // qubitDriveFrequencies
                        new int[][]{ArrayHelper.arange(-100, 2000, 20)}, // qubitDriveDelays
                        new float[][]{ArrayHelper.arange(6000f,15000f, 5e9f)}, // readoutDriveAmplitudes,
                        new float[][]{ArrayHelper.arange(6.59198e9f,6.6e9f, 2e9f)}, // readoutDriveFrequencies
                        false,true,1500, "test run");
                break;
            case "QQSwap": //
                serverApi.runQQSwap("Q1", "Q2", new String[]{"Q3","Q4"}, // sweepDetuneQubit, fixedDetuneQubit, otherDetuneQubits
                        5000f, new float[]{-2000f, 1500f}, "Y2P", "X", "p",
                        ArrayHelper.arange(0, 3e4f, 1e3f), ArrayHelper.arange(0, 500, 5),
                        false,true,1500, "test run");
                break;
            case "XYZTiming": // tested OK
                serverApi.runDriveBiasTiming(new String[]{"Q1"}, new String[]{"Q1"}, new int[]{5}, new float[]{3e4f}, // xyDriveQubits, zBiasAgents, zBiasLengths, zBiasAmplitudes
                        true, new int[][]{ArrayHelper.arange(-80,80,1)},
                        false,true,1500, "test run");
                break;
            case "QGTiming": //
                serverApi.runQGTimingBiasXTalkImpl(new String[]{"Q2"}, new String[]{"G12"}, new int[]{15}, new float[]{3e4f}, // qubits, couplers, zBiasLengths, zBiasAmplitudes
                        new int[][]{ArrayHelper.arange(-40,40,1)},
                        false,true,1500, "test run");
                break;
            case "ACZLengthAmplitude": //
                serverApi.runACZLengthAmplitude( "G12", "X",
                        "I", "P",
                        ArrayHelper.arange(30, 100, 2), // aczLength,
                        ArrayHelper.arange(0.5e4f, 2e4f, 2500f), // aczAmplitude,
                        false,true,1500, "test run");
                break;
            case "RamseyErrorFilter": //
                serverApi.runRamseyErrorFilter("G12",
                        ArrayHelper.arange(6, (int)100, 1), //delays,
                        false,true,1500, "test run");
                break;
            case "SimpleStateTomography": //
                serverApi.runSimpleStateTomography(new String[]{"Q1"}, // new String[][]{},
                        false, false,true,1500, "test run");
                break;
            case "SingleQStateTomography": // tested OK
                serverApi.runSingleQStateTomography(new String[]{"Q1"}, new String[]{"Y2P"}, // Y2P XPY2P XMY2M,
                        false,true,5000, "test run");
                break;
            case "SingleQAXYStateTomography": //
                serverApi.runSingleQAXYStateTomography(new String[]{"Q1"},
                        new float[][]{ArrayHelper.arange(1.470206738e+4f, 1.470206738e+4f, 1e9f)}, // gateAmplitudes
                        new float[][]{ArrayHelper.arange(0e6f, 10e6f, 1e9f)}, // detunings
                        new float[][]{{0.6521728039f}}, // alphas
                        new int[][]{{50}}, // gate lengths
                        new float[][]{ArrayHelper.linspace(0f, (float) (2f*Math.PI), 50)}, // azimuths
                        false,true,5000, "test run");
                break;
            case "SimpleProcessTomography1Q": // tested OK
                serverApi.runSimpleProcessTomography1Q("Q2", // "XY2P Q2 0.785398163397448"
                        new String[]{"XY2M Q2 -0.785398163397448"}, // process: instruction string
                    false,true,3000, "test run");
            break;
            case "SimpleProcessTomography2Q": //
                serverApi.runSimpleProcessTomography2Q("G12",
                        new String[]{"FSIM G12"}, // process: instruction string
                        false,true,1500, "test run");
                break;
            case "RandomizedBenchmarking1Q": // tested OK
                int[] numCycles0 = ArrayHelper.linlogspace(1, 300, 25);
                int[] numSequeces0 = new int[numCycles0.length];
                Arrays.fill(numSequeces0, 50);
                serverApi.runRandomizedBenchmarking1Q("Q2","I", 20, false,
                        true, false,
                        numCycles0, numSequeces0,
                        //new int[]{50,50,50,50,50},
                        // new int[]{2, 4, 6, 8, 10,15,20,50,100, 150, 200},
                        // new int[]{30,30,30,30,30,30,30,30,30,  30,  30},
                        false,true,1000, "test run");
                break;
            case "RBClifordXYImpl1Q": // tested OK
                int[] numCycles1 = ArrayHelper.linlogspace(1, 300, 25);
                int[] numSequeces1 = new int[numCycles1.length];
                Arrays.fill(numSequeces1, 50);
                serverApi.runRandomizedBenchmarking1Q(new String[]{"Q2"},"I", 20, false,
                        true,
                        numCycles1,
                        numSequeces1,
                        false,true,1000, "test run");
                break;
            case "RandomizedBenchmarking2Q": //
                serverApi.runRandomizedBenchmarking2Q(
                        "G12", "CZ", 10, true, true,
                        new int[]{1,20,100},
                        new int[]{20,20,20},
                        false,true,1500, "test run");
                break;
            case "RBXEBImpl1Q": // tested OK
                int[] numCycles2 = ArrayHelper.linlogspace(1, 1000, 75);
                // int[] numCycles2 = new int[]{1};
                int[] numSequeces2 = new int[numCycles2.length];
                Arrays.fill(numSequeces2, 150);
                serverApi.runRBXEBImpl1Q(new String[]{"Q2"},
                        new String[]{
                                "I Q2 0"
                        },
                        new double[][]{{1,0,0,0},
                                {0,0,1,0}},
                        /*
                        new double[][]{{0.707106781186548,0,0,-0.707106781186547},
                                {0,-0.707106781186547,0.707106781186547,0}},
                         */
                        false,
                        numCycles2, numSequeces2,
                        false,true,2000, "test run");
                break;
            case "OptimalReadoutFrequencyCal0": // tested OK
                serverApi.runOptimalReadoutFrequencyCal(new String[]{"Q1"}, new String[]{"Q2"}, new float[]{-1.5e4f},
                        new double[]{1.5e6f}, 50, 2.0f,
                        false, true, true, 1500);
                break;
            case "OptimalReadoutFrequencyCal1": //
                serverApi.runOptimalReadoutFrequencyCal(new String[]{"Q1"}, new String[]{"Q2"}, new float[]{-1.5e4f},
                        false, false, new double[]{1.5e6f}, 50, 2.0f,
                        true, true, 1500);
                break;
            case "IQ2Probability": // tested OK
                serverApi.runIQ2Probability(new String[]{"Q63"}, new String[]{}, // bias away qubit
                        new float[]{},
                        false, false,
                        Float.MAX_VALUE, 1f, 1e6f,
                        true, true, 10000);
                break;
            case "IQ2ProbabilityQ2": // tested OK
                serverApi.runIQ2Probability(new String[]{"Q2"}, new String[]{"Q1"}, new float[]{-0e4f},
                        false, false,
                        Float.MAX_VALUE, 2f, 1e6f,
                        true, true, 10000);
                break;
            case "IQ2Probability012": // tested OK
                serverApi.runIQ2Probability(new String[]{"Q2"}, null, null,
                        true, false, 1.0f, 0.5f, 0.25f ,
                        true, false, 10000);
                break;
            case "ErrorVsReadoutAmplitude": // tested OK
                serverApi.runErrorVsReadoutAmplitude(new String[]{"Q1"},
                        new float[][]{ArrayHelper.arange(2000f, 20000f, 1000f)}, false,
                        false, true, 10000, "test run");
                break;
            case "ReadoutFrequencyFinder": //
                serverApi.runReadoutFrequencyFinder(new String[]{"R1"}, new float[]{3.2e4f},
                        new float[]{4e3f}, new float[]{400e6f},
                1e6f, 3e6f, 0.1e6f, 5, 100e6f,
                20e6f, 5e6f,
                        false, true, true, 1000);
                break;
            case "ReadoutFrequencyPowerDependency": //
                serverApi.runReadoutFrequencyPowerDependency(new String[]{"Q1"},
                        1000f, 3e4f, 25,
                        0.7e6f, 0.8e6f,
                        0.1e6f, 5f,
                        true, false, true, true, 1000);
                break;
            case "ReadoutFrequencyNAmplitudeOptimizer": //
                serverApi.runReadoutFrequencyNAmplitudeOptimizer(new String[]{"Q1"}, null, null,
                        new float[]{0.7e6f}, false, 60, 0.5e6f, 0.05f,
                        true, true, 1000);
                break;
            case "CorrectF01": // tested ok
                serverApi.runCorrectF01ByPhaseTomo(new String[]{"Q2"}, null, null, new int[]{0},
                new int[][]{ArrayHelper.arange(0, 500, 20)}, 20e6f, 0.5f,
                        false,true, false, 2000);
                System.out.println("CorrectF01");
                break;
            case "F01DriftVsTime": //
                serverApi.runF01DriftVsTime(new String[]{"Q1", "Q2"}, null, null, new int[]{0,0},
                        new int[][]{ArrayHelper.arange(0, 3000, 50),
                                ArrayHelper.arange(0, 3000, 50)},
                        0, 3600*13,
                        false, true, 3000, "test f01 drift"); // enableParallelMode, numShots
                break;
            case "XYGateAmplitudeRabiCal": // Tested ok
                serverApi.runXYGateAmplitudeRabiCal(new String[]{"Q2"}, null, null, 40, // numDataPoints
                new int[]{3}, "X12", 0.15f, // numPis, gate, dacMaxAmplitude, fitErrorUB
                0.5f, 0.1f, false, true, false, 2000); // enableParallelMode, numShots
                break;
            case "XYGateHPiRBClifordOptimizer0": //
                serverApi.runXYGateHPiRBClifordOptimizer0(new String[]{"Q2"}, 100, 200, 30,
                        false, true, false, 1000); // enableParallelMode, numShots
                break;
            case "XYGateRBClifordOptimizer0": //
                serverApi.runXYGateRBClifordOptimizer0(new String[]{"Q2"}, 100, 200, 30,
                        false, true, false,1000); // enableParallelMode, numShots
                break;
            case "XYGateRBIQSBOptimizer": //
                serverApi.runXYGateRBIQSBOptimizer(new String[]{"Q1"}, 100, 150, 30,
                        -0.05f, 0.05f, 0.2f, 0.2f,
                        false, true, false, 1500); // enableParallelMode, numShots
                break;
            case "XYGateAmplitudeIniCal": //
                serverApi.runXYGateAmplitudeIniCal(new String[]{"Q1"}, null, null,
                        100, "X2P", 5f,
                        false, 0.3f, 0.95f,
                        false, true,
                        false, 1500);
                break;
            case "ZDistortionCalExpDecay": //
                serverApi.runZDistortionCalExpDecay(new String[]{"Q2"}, 800, new float[]{-3e4f},
                        ArrayHelper.linlogspace(0,3000,100),
                        new float[]{-0.03f}, new float[]{0.03f}, new int[]{2000}, // alphaLowerBound, alphaUpperBound, decayTimeUpperBound,
                        0.8f, 0.15f,
                        "FIXED_MEASURE", 600,
                        0.1f, false, true, false, 2500); // enableParallelMode, numShots
                break;
            case "DRAGAlphaCal": // tested OK
                serverApi.runDRAGAlphaCal(new String[]{"Q1"}, new float[]{0.5f}, new int[][]{{11, 17, 25}}, // 1, 3, 5, 13 //11, 17, 25
                        40, 0.1f,
                        false, true, 1500, 0.5f); // enableParallelMode, numShots
                break;
            case "ReadoutIQLoCalibrator": // tested OK, data looks OK
                serverApi.runReadoutIQLoCalibrator(new String[]{"R1"},
                        new float[][]{ArrayHelper.arange(6.21e9f, 6.4e9f, 5e9f)},-800, 800, 100,
                        false, true, 3000, true); // last: verbose
                break;
            case "ReadoutIQSBCalibrator": // tested OK, data looks OK
                serverApi.runReadoutIQSBCalibrator(new String[]{"R1"}, new float[]{20e3f},
                        new float[][]{ArrayHelper.arange(6.4e9f, 6.42e9f, 5e6f)},
                        new float[][]{ArrayHelper.arange(-150.0e6f, -100e6f, 5e9f)},
                        -0.15f, 0.15f, -0.5f, 0.5f, 100,
                        false, true, 3000, true); // last: verbose
                break;
            case "ReadoutIQGShift": // tested OK
                serverApi.runReadoutIQGShift(new String[]{"G12"},
                        new boolean[]{false}, new String[]{"Q1"}, // readoutQubits, detuneQubits
                        new float[]{0e4f}, // detuneAmplitudes
                        new float[][]{ArrayHelper.arange(-3.2e4f, 3.2e4f, 500f)}, // coupler biasAmplitudes
                        false, false,
                        false, true, 2000, "test run");
                break;
            case "QQSwapG": //
                serverApi.runQQSwapG(new String[]{"G12"},
                        new float[]{-87e6f}, new float[]{0e6f}, false, new String[]{"X"}, new String[]{"I"}, "P",
                        new float[][]{ArrayHelper.arange(1.35e4f, 2.0e4f, 500f)}, // coupler bias amplitudes
                        new int[][]{ArrayHelper.arange(0, 250, 5)}, // swap times
                        false, true, 3000, "test run");
                break;
            case "CPhaseG": //
                serverApi.runCPhaseG(new String[]{"G12"}, new boolean[]{true},
                        new float[]{0f}, new float[]{0}, false, // q1 detune, q2 detune
                        new float[][]{ArrayHelper.arange(-3.2e4f, 3.2e4f, 200f)}, // coupler bias amplitudes
                        new int[][]{ArrayHelper.arange(500, 500, 10)}, // swap times
                        false, true, 3000, "test run");
                break;
            case "FSim": //
                serverApi.runFSim(new String[]{"G12"}, new int[]{0}, 50,"P",
                        new String[]{"X"}, new String[]{"X"},
                        new float[][]{{35}}, new int[][]{{3}}, new int[][]{{6}}, // fSimActiveLengths, fSimDetuneEdgeWidths, fSimGEdgeWidths
                        new float[][]{ArrayHelper.arange(-200e6f, 100e6f, 0.5e6f)}, // fSimDetunes
                        new boolean[]{true},
                        new float[][]{ArrayHelper.arange(-30e6f, 9e6f, 0.5e9f)}, // fSimGs
                        false, true, 1500, "swap");
                break;


            /*
             * control work node
             */
            case "ReadoutFrequencyFinderNodeImpl0": // tested OK, again
                serverApi.runReadoutFrequencyFinderNodeImpl0(new String[]{"R01"},
                        new float[]{3.2e4f}, new float[]{4e3f}, new float[]{-400e6f}, // readout amplitude, side band frequency, number of readout qubits
                        1e6f, 0.10e6f, 3e6f, 20e6f,
                        10e6f, 15e6f, -30f,
                        false, true, true, 1500);
                break;
            case "ReadoutFrequencyPowerDependencyNodeImpl0": // tested OK, again
                serverApi.runReadoutFrequencyPowerDependencyNodeImpl0(
                        new String[]{"Q1"}, 1000f, 3e4f, 30,
                        0.5e6f, 0.15e6f, 0.4e6f,
                        0.05e6f, 2f, 2f,
                        true, false, true, true, 1500);
                break;
            case "ReadoutFrequencyBiasDependencyNodeImpl0": // tested OK, both DC and Pulse, again
                serverApi.runReadoutFrequencyBiasDependencyNodeImpl0(
                        new String[]{"Q1"}, new float[]{-50e4f}, new float[]{50e4f}, 41, true,
                        0.5e6f, 0.3e6f, // chi = - g^2/Delta^2*anharmonicity
                        0.1e6f, 2f, 2f, 0.15e6f,
                        false, true, true, 1500);
                break;
            case "F01FinderNodeImpl0": // tested OK, again
                serverApi.runF01FinderNodeImpl0(
                        new String[]{"Q01", "Q04", "Q03", "Q05", "Q06", "Q08", "Q07", "Q10"}, 5000, new float[]{0.1e4f},
                        650e6f, 25e6f, new float[]{1.5e4f}, // backgroundBiasAmplitudes
                        200e6f, 0.2e6f,
                        new String[]{"Q02", "Q09"}, new float[]{-3e4f}, 7, 4.1e9f, 5.9e9f, 5f,
                        false, true, true, 1500);
                break;
                /*
            case "XYGateAmplitudeIniCalNodeImpl0": // tested OK, again
                serverApi.runXYGateAmplitudeIniCalNodeImpl0(
                        new String[]{"Q2"}, 100,
                        "X", 3.2e4f,
                        0.333f, 2.0f, false,
                        false, true, 2000);
                break;
                 */
            case "XYGateAmplitudeIniCalNodeWithBackupImpl0": // tested OK, again
                serverApi.runXYGateAmplitudeIniCalNodeWithBackupImpl0(
                        new String[]{"Q1"}, new String[]{}, new float[]{}, 100, "X2P",
                        5f, false, 0.3f, 0.95f,
                        false, true, true, 2000);
                break;
            case "OptimalReadoutFrequencyNodeImpl0": // tested OK, again
                serverApi.runOptimalReadoutFrequencyCalNode(
                        new String[]{"Q1"}, new String[]{"Q2"}, new float[]{-1.5e4f}, new float[]{100e6f}, // frequencyRanges
                        1000, 1f, 2f, // minSNR is the minimum of |0>, |1> iq separation snr
                        1e6f, false, true, true, 2000);
                break;
            case "OptimalReadoutFrequencyCalNode": //
                // @NonNull String[] qubits, @NonNull String[] biasAwayQubits,
                //            @NonNull float[] biasAmplitudes, boolean measureState2, boolean forParamSet012, @NonNull float[] frequencyRanges,
                //            int numSamples, float minSNR, float minDataSNR, // minSNR is the minimum of |0>, |1> iq separation snr
                //            float frequencyChangeUB, boolean enableParallelMode, boolean saveRawData, int numShots
                serverApi.runOptimalReadoutFrequencyCalNode(
                        new String[]{"Q1"}, new String[]{"Q2"}, new float[]{-1.5e4f}, false, false,
                        new float[]{100e6f}, // frequencyRanges
                        1000, 1f, 2f, // minSNR is the minimum of |0>, |1> iq separation snr
                        1e6f, true, true, 2000);
                break;
            case "ReadoutFrequencyNAmplitudeOptimizerNodeImpl0": // tested ok, again, needs to be checked again
                serverApi.runReadoutFrequencyNAmplitudeOptimizerNodeImpl0(
                        new String[]{"Q1"}, null, null, new float[]{0.7e6f},
                        0.5f, 0.1f, 0.5e6f, // minSNR is the minimum of |0>, |1> iq separation snr
                        2f, false, 100, true,
                        false, 2000);
                break;
            case "IQ2ProbabilityCompareToHistoryImpl0Node": // tested OK, again
                serverApi.runIQ2ProbabilityNodeCompareToHistoryImpl0(
                        new String[]{"Q1"}, new String[]{"Q2"}, new float[]{-1.5e4f},
                        false, false,
                        1f, 0.25f, 0.15f, 0.5f,
                        false, true, 10000);
                break;
            case "IQ2ProbabilityNodeMultiRunImpl0": // tested OK, again
                serverApi.runIQ2ProbabilityNodeMultiRunImpl0(new String[]{"Q1"},  new String[]{"Q2"}, new float[]{-1.5e4f},
                        false, false, 7, 5, 1.0f,
                        0.15f, 1.0f, true, true, 10000);
                break;
            case "IQ2ProbabilityNodeWithBackupImpl0": // tested OK, again
                serverApi.runIQ2ProbabilityNodeWithBackupImpl0(new String[]{"Q1"}, new String[]{}, new float[]{},
                        false, false, 0.75f, 0.25f, 0.15f,
                        0.5f, 2, 7, 5, 0.5f,
                        true, false, 10000);
                break;
            case "XYGateAmplitudeRabiCalNodeImpl0": // tested OK, again
                serverApi.runXYGateAmplitudeRabiCalNodeImpl0(
                        new String[]{"Q1"}, new String[]{}, new float[]{}, 100, new int[]{3}, "X2P",
                        0.333f, 2.0f, 0.1f,false,
                        false, true, 1500);
                break;
            case "DRAGAlphaCalNodeWithBackupImpl0": // tested OK, again
                serverApi.runDRAGAlphaCalNodeWithBackupImpl0(
                        new String[]{"Q2"}, new float[]{1}, new float[]{5},
                        new int[][]{{11, 17, 25}}, new int[][]{{1, 3, 5}}, 50,
                        0.1f, -3f, 3f, 5f,
                        false, false, true, 3000);
                break;
            case "F01BiasDependencyNodeImpl0": // tested OK
                serverApi.runF01BiasDependencyNodeImpl0(
                        new String[]{"Q2"}, false, 5000, new float[]{500}, 0.15f,
                4.5e9f, 30e6f, 0.5e6f,
                new String[]{"Q1"}, new float[]{-3e4f},
                true, -242e6f, 10e6f, 4e9f, 1f/(4f*3e4f),
                3, 5, new float[]{-3e4f}, new float[]{3e4f}, new float[]{1000},
                        false,true,true, 1500,true);
                /*
                serverApi.runF01BiasDependencyNodeImpl0(
                        new String[]{"Q1"}, true, 5000, new float[]{500}, 0.15f,
                        4.5e9f, 40e6f, 0.5e6f,
                        new String[]{"Q2"}, new float[]{-3e4f},
                        true, -242e6f, 10e6f, 4e9f, 1f/(4f*50e5f),
                        3, new float[]{-50e4f}, new float[]{50e4f}, new float[]{10000},
                        false,true,true, 1500,true);

                 */
                break;
            case "CorrectF01NodePhaseTomoImpl0": // tested OK, again
                serverApi.runCorrectF01NodePhaseTomoImpl0(
                        new String[]{"Q2"},  new String[]{}, new float[]{}, 0, 2000,
                        50, 20e6f, 0.5f,
                5f, 3e6f, false, true, true, 2000);
                break;
            case "CorrectF01NodeWithBackupPhaseTomoImpl0": // tested OK, again
                serverApi.runCorrectF01NodeWithBackupPhaseTomoImpl0(
                        new String[]{"Q1"}, new String[]{}, new float[]{}, 0, 2000,
                        50, 10, 20e6f, 0.5f,
                        5f, 3e6f, false, true, true, 2000);
                break;
            case "runCorrectF01NodeRabiAmpImpl0": //
                serverApi.runCorrectF01NodeRabiDetuneImpl0(
                        new String[]{"Q2"}, new String[]{}, new float[]{}, 70, 50e6f, 0.2f,
                        true, false, true, true, 2000);
                break;
            case "IMPAGainOptimizationNodeSimplexTriImpl0": // tested OK
                serverApi.runIMPAGainOptimizationNodeSimplexTriImpl0(
                        new String[]{"A1"}, new String[]{"R1"},
                        true, -30, 200,
                        new float[]{-5e5f}, new float[]{5e5f},
                        new float[]{1f}, new float[]{3.2e4f},
                        new float[]{10e9f}, new float[]{15e9f},
                        new float[]{2.5e4f}, new float[]{2000}, new float[]{100e6f},
                        10f, 20f, 30f, 2e9f,
                5, 30,
                        false,true,true, 1500);
                break;
            case "IMPAGainOptimizationNodeDETriImpl0": //
                serverApi.runIMPAGainOptimizationNodeDETriImpl0(
                        new String[]{"A1"}, new String[]{"R1"},
                        true, -30, 200,
                        new float[]{-3e5f}, new float[]{1.5e5f}, // dc bias
                        new float[]{1000f}, new float[]{3.2e4f}, // pump amplitudes
                        new float[]{11e9f}, new float[]{14e9f}, // lo frequency
                        50, true, 0.05f, 3,
                        15,
                        10f, 20f, 30f, 2e9f,
                        5, 30,
                        false,true,true, 1500);
                break;
            case "T1VsF01CalNodeLatestValueImpl": // tested OK
                float[][] DEWP0 = F01ToCoherenceMapper.getDefaultErrorWeightParameters();
                serverApi.runT1VsF01CalNodeLatestValueImpl(
                        new String[]{"Q60", "Q61", "Q62", "Q63", "Q64"}, true,
                        new float[]{4.6e+9f}, new float[]{5.6e9f},
                        20e6f, new int[]{40000},  // maxDelay
                        40, (int) (2e6/ F01ToCoherenceMapper.F01_GRID_SIZE), 3600*24*3/1000,
                        DEWP0[0], DEWP0[1], 0.2f,
                        false,true,true, 2000);
                break;
            case "T1VsF01CalNodeTimeExpDecayWeightImpl": // tested OK
                float[][] DEWP1 = F01ToCoherenceMapper.getDefaultErrorWeightParameters();
                serverApi.runT1VsF01CalNodeTimeExpDecayWeightImpl(
                        new String[]{"Q2"}, true, new float[]{4.889629696e+9f - 50e6f}, new float[]{4.915e+9f},
                        10e6f, new int[]{50000},  // maxDelay
                        40, (int) (2e6/ F01ToCoherenceMapper.F01_GRID_SIZE), 3600*24*3/1000,
                        DEWP1[0], DEWP1[1], 432, 0.2f,
                        false,true,true, 1500);
                break;
            case "ZXTalkCalNodeImpl0": // tested OK, again, needs to be checked again
                serverApi.runZXTalkCalNodeImpl0(
                        "Q1", "Q2", 1000, 1, 100,
                1000f, -20e3f,
                0.2e6f, // in target qubit frequency, Hz
                0.05f, 0.5f, // in phase rad
                false,true,true, 2000);
                break;
            case "DriveBiasTimingCalBaseNodeImpl0": // tested OK, again
                serverApi.runDriveBiasTimingCalBaseNodeImpl0(
                        new String[]{"Q1"}, new String[]{"Q1"}, new String[]{"XMON"}, new int[]{20}, new float[]{3e4f},
                        false, -40, 40, 20, 3f, 0.1f,
                        0.5f,
                        false,true,true, 2500);
                break;
            case "ZDistortionCalNodeExpDecayImpl0": // tested OK, again
                serverApi.runZDistortionCalNodeExpDecayImpl0(
                        new String[]{"Q1"}, 800, new float[]{-3e4f}, 10, 3000,
                        100, new float[]{-0.03f}, new float[]{0.03f}, new int[]{2000},
                        0.13f, "FIXED_MEASURE", 600,
                        0.15f, 3f, 0.3f, 0.3f,
                        false,true,true, 2500);
                break;
            case "IQVsXTGBiasCalNodeImpl0": // tested OK
                serverApi.runIQVsXTGBiasCalNodeImpl0(
                        new String[]{"G12"}, new boolean[]{true},
                        new String[]{"Q2"}, new float[]{-3e4f}, false,
                        new float[]{-5e5f}, new float[]{5e5f}, 70, 2, 1e4f,
                        false,true,true, 3000);
                break;
            case "XTGZeroPointCoarseCalNodePhaseImpl0": // tested OK
                serverApi.runXTGZeroPointCoarseCalNodePhaseImpl0(
                        new String[]{"G12"}, new boolean[]{true}, 200,
                        new int[]{1000}, false,
                        3e4f, (float) Math.PI/5, 4f,
                        false,true,true, 3000);
                break;
            case "F01XTGBiasDependencyCalNodeImpl0": // tested OK
                serverApi.runF01XTGBiasDependencyCalNodeImpl0(
                        new String[]{"G12"}, new boolean[]{true},
                        5000, new float[]{500}, 0.15f,
                        600e6f, 20e6f, 0.3e6f,
                        4e9f, 7,
                        new float[]{-3e4f}, new float[]{2.3e4f}, // biasLBs,  biasUBs
                        new float[]{400f}, 2e6f,
                        10e6f, false,true,true,
                        3000, true);
                break;

            /********************************
             * control graph
             */

            case "CreateControlGraph":
                serverApi.createControlGraph(controlGraphName, true);
                break;
            case "DeleteControlGraph": // not working
                serverApi.deleteControlGraph(controlGraphName);
                break;
            case "ReloadControlNode": //
                serverApi.reloadControlNode(controlGraphName, "T1VsF010G1Temp"); // RFreqVsBias F01Finder0G1  T1VsF010G1Temp
                break;
            case "SetControlNodeOutOfSpec":
                serverApi.setControlNodeOutOfSpec(controlGraphName, new String[]{});
                break;
            case "MaintainControlGraph": //
                serverApi.maintainControlGraph(controlGraphName, "Exit", Config.maintainCalGroups, true);
                break;
            case "ControlNodeRunCal": //
                // IMPAGainOptimization0 ReadoutFreqFinder // T1VsF010G1, RFreqVsPwrG2 IQ2P0G1, IQ2P0G2 T1VsF010G1Temp
                // FSimThetaVsGCalFine RFreqVsBias IMPAGainOptim0
                // FSimThetaVsGCalCoarse FSimThetaVsDetuneCal0 X12GateAmplitudeCalQ1 IQ2Probability012Q2 OptimalReadoutFrequencyQ2012
                // BenchMark1QGatesQ2 DriveBiasTimingCalBaseQ2G12 F01XTGBiasDependencyCalQ2 XTGGVsBias IMPAGainOptimization0 BenchMark1Q
                // X2PGateAmplitudeIniCalQ2 XTGZeroPointCalCoarse X2PGateAmplitudeIniCalQ1 T1VsF01Cal0Q2 CorrectF010Q1 IQ2Probability2Q2 DriveBiasTimingCalBaseQ1G12
                serverApi.controlNodeRunCal(controlGraphName, "T1VsF010G1", true); // XYGateAmplitudeRabiCal0Q2 T2VsF01Cal0Q2 ZDistortionCalQ2
                break;
            case "SetNodeAllOutOfSpec": //
                serverApi.controlNodeTestOp(controlGraphName, new String[]{
                        // "SetReadoutLoFreq",
                        // "RFreqVsPwrG1", "RFreqVsPwrG2", "RFreqVsPwrG3", "RFreqVsPwrG4", "RFreqVsPwrG5", "RFreqVsPwrG6",
                        "OptRFreqG1", "OptRFreqG2", "IQ2P1G1", "IQ2P1G2"
                }, 0); // F01Finder0G1
                break;
            case "SetNodeAllInSpec": //
                serverApi.controlNodeTestOp(controlGraphName, new String[]{
                        "ReadoutFreqFinder",
                        "SetReadoutLoFreq",
                        "RFreqVsPwrG1", "RFreqVsPwrG2", "RFreqVsPwrG3", "RFreqVsPwrG4", "RFreqVsPwrG5", "RFreqVsPwrG6",
                        "IMPAModulationCal", "RFreqVsBias","IMPAGainOptim0","IMPAGainOptim1",
                        "IQVsXTGBiasCal1", "IQVsXTGBiasCal2", "IQVsXTGBiasCal3", "IQVsXTGBiasCal4",
                        "F01Finder0G1", "F01Finder0G2",
                        "F01Finder1G1", "F01Finder1G2",
                        "X2PGateAmpIniCal0G1", "X2PGateAmpIniCal0G2",
                        // "OptRFreqG1", "OptRFreqG2",
                        "RFreqNAmpOptimG1", "RFreqNAmpOptimG2",
                        "F01RamseyCheckG1", "F01RamseyCheckG2",
                        "SetWorkingPoint0",
                        "F01VsBias0G1", "F01VsBias0G2", "XYAmpRabiCal0G1", "XYAmpRabiCal0G2",
                        "XTGZeroCoarseG1", "XTGZeroCoarseG2", "XTGZeroCoarseG3", "XTGZeroCoarseG4",
                        "T2VsF010G1", "T2VsF010G2"

                }, 1); // , RFreqVsBias IMPAGainOptim0
                break;













            case "runSetQubitWorkingPointTest0":
                serverApi.runSetQubitWorkingPointTest0(new String[]{"Q1"}, 30e6f, 100e6f,
                        30e6f,
                        new float[]{4.80e9f}, new float[]{5.0e+9f});
                break;
            case "runTemporary":
                // serverApi.runTemperoary();
                break;
            default:
                throw new RuntimeException("Unsupported case: "+options);
        }
        serverApi.disconnect();
    }

    public static void main(String[] args){
        if (args == null || args.length == 0) {
            callService("");
        } else {
            callService(args[0]);
        }
    }
}
