package qc.qos.rpcapi.qpx1701;

import lombok.NonNull;
import qc.qos.rpc.ServerApi;
import qc.qos.rpc.data.Data;
import qc.qos.rpc.data.Record;
import qc.qos.rpc.exception.RPCException;
import qc.qos.util.ArrayHelper;

import javax.annotation.Nonnegative;
import java.util.concurrent.ExecutionException;

/**
 * Copyright (c) 2019 onward, Yulin Wu. All rights reserved.
 * <p>
 * mail4ywu@gmail.com/mail4ywu@icloud.com
 * University of Science and Technology of China.
 */
public class QOSServerApi extends ServerApi {

    public QOSServerApi(@NonNull String userName, String password, @NonNull String host, int port)
            throws ExecutionException, InterruptedException {
        super(userName, password, host, port, false, null, null);
    }

    public QOSServerApi(@NonNull String userName, String password, @NonNull String host, int port,
                        boolean useSSL, String keyFile, char[] keyStorePassword) throws ExecutionException, InterruptedException {
        super(userName, password, host, port, useSSL, keyFile, keyStorePassword);
    }

    public void bootup(@NonNull String processorConfFile, @NonNull String shiroIniFile,
                       @NonNull String systemId, @NonNull String sampleId) {
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1,
                    Data.clusterOf(
                            Data.valueOf(processorConfFile),
                            Data.valueOf(shiroIniFile),
                            Data.valueOf(systemId),
                            Data.valueOf(sampleId))))
                    .get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void bootup(@NonNull String settingsStoreUserName, @NonNull String settingsStorePassword,
                       @NonNull String configServerName, @NonNull String labradManagerHost,
                       @NonNull int labradManagerPort, @NonNull String labradPassword,
                       @NonNull String qHWSession,
                       @NonNull String shiroIniFile,
                       @NonNull String systemId, @NonNull String sampleId) {
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1,
                    Data.clusterOf(
                            Data.valueOf(settingsStoreUserName),
                            Data.valueOf(settingsStorePassword),
                            Data.valueOf(configServerName),
                            Data.valueOf(labradManagerHost),
                            Data.valueOf(labradManagerPort),
                            Data.valueOf(labradPassword),
                            Data.valueOf(qHWSession),
                            Data.valueOf(shiroIniFile),
                            Data.valueOf(systemId),
                            Data.valueOf(sampleId))))
                    .get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void shutdown(@NonNull String shutdownAuthorizationCode) {
        getClient().sendMessage(getContextId(),new Record(2, Data.valueOf(shutdownAuthorizationCode)));
    }

    public void login(@NonNull String password, @NonNull String[] userAgents,
                       @NonNull String user1AgentSoftPropsFolder, @NonNull String dataPath, String controlGraphName) {
        if (controlGraphName == null) controlGraphName = "";
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(3,
                    Data.clusterOf(Data.valueOf(password), Data.valueOf(userAgents),
                            Data.valueOf(user1AgentSoftPropsFolder), Data.valueOf(dataPath),
                            Data.valueOf(controlGraphName)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void login(@NonNull String password, @NonNull String[] userAgents, @NonNull String settingsStoreUserName,
                      @NonNull String settingsStorePassword, @NonNull String userSession, @NonNull String dataPath,
                      String controlGraphName) {
        if (controlGraphName == null) controlGraphName = "";
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(3,
                    Data.clusterOf(Data.valueOf(password), Data.valueOf(userAgents),
                            Data.valueOf(settingsStoreUserName), Data.valueOf(settingsStorePassword),
                            Data.valueOf(userSession), Data.valueOf(dataPath), Data.valueOf(controlGraphName)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void logout(@NonNull String password) {
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4, Data.valueOf(password))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void reloadSettings(@NonNull String password) {
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5, Data.valueOf(password))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void getQPUInfo() {
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(6, Data.valueOf("_"))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void restartHardware(@NonNull String[] hardwareSetsToBeRestarted) {
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(7,
                    Data.valueOf(hardwareSetsToBeRestarted))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutIQ(@NonNull String[] readoutAgents,
                             @NonNull float[] readoutAmplitudes, @NonNull float[] sidebandFrequencies,
                             boolean amplifierOff, float amplifierOffLoPower,
                             @NonNull float[][] frequencies,
                              boolean continuousExecution, boolean enableParallelMode,
                              int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1000,
                    Data.clusterOf(Data.valueOf(readoutAgents),
                            Data.valueOf(readoutAmplitudes),
                            Data.valueOf(sidebandFrequencies),
                            Data.valueOf(amplifierOff), Data.valueOf(amplifierOffLoPower),
                            Data.valueOf(frequencies),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutIQ(@NonNull String[] readoutQubits,
                             @NonNull String[] driveQubits, @NonNull String[] driveGates,
                             @NonNull String[] biasQubits, boolean isDCBias, boolean biasDriveGateOnly,
                             boolean saveIQRaw, boolean doFit,
                             @NonNull float[][] frequencies, @NonNull float[][] amplitudes, @NonNull float[][] biasAmplitudes,
                             boolean continuousExecution, boolean enableParallelMode,
                             int numShots, @NonNull String notes){
        try {
            Data ret = getClient().sendRequest(getContextId(),new Record(1000, // overloaded method
                    Data.clusterOf(Data.valueOf(readoutQubits), Data.valueOf(driveQubits),Data.valueOf(driveGates),
                            Data.valueOf(biasQubits), Data.valueOf(isDCBias), Data.valueOf(biasDriveGateOnly),
                            Data.valueOf(saveIQRaw), Data.valueOf(doFit),
                            Data.valueOf(frequencies), Data.valueOf(amplitudes), Data.valueOf(biasAmplitudes),
                            Data.valueOf(continuousExecution),
                            Data.valueOf(enableParallelMode), Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIMPABringup0(@NonNull String jpaAgent, @NonNull String readoutAgent,
                              @NonNull float[] signalAmplitude, @NonNull float[] signalFrequency,
                              @NonNull float signalSidebandFrequency, @NonNull float[] biasAmplitude, @NonNull float[] pumpAmplitude,
                              @NonNull float[] pumpLoFrequency, @NonNull float[] pumpSidebandFrequencies,
                              boolean continuousExecution, boolean enableParallelMode,
                              int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1002,
                    Data.clusterOf(Data.valueOf(jpaAgent), Data.valueOf(readoutAgent),
                            Data.valueOf(signalAmplitude), Data.valueOf(signalFrequency), Data.valueOf(signalSidebandFrequency),
                            Data.valueOf(biasAmplitude), Data.valueOf(pumpAmplitude), Data.valueOf(pumpLoFrequency),
                            Data.valueOf(pumpSidebandFrequencies),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIMPABringup1(@NonNull String[] jpaAgents, @NonNull String[] readoutAgents, boolean pumpWithMixer,
                                @NonNull float[][] biasAmplitudes, @NonNull float[][] pumpAmplitudes,
                                @NonNull float[][] pumpLoFrequencies, @NonNull float[][] pumpSidebandFrequencies,
                                boolean continuousExecution, boolean enableParallelMode,
                                int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1002, // overloaded method
                    Data.clusterOf(Data.valueOf(jpaAgents), Data.valueOf(readoutAgents), Data.valueOf(pumpWithMixer),
                            Data.valueOf(biasAmplitudes), Data.valueOf(pumpAmplitudes), Data.valueOf(pumpLoFrequencies),
                            Data.valueOf(pumpSidebandFrequencies),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runIMPAGainBaseOptimizerSimplexTriImpl0(
            @NonNull String[] jpaAgents, @NonNull String[] readoutAgents, boolean pumpWithMixer, float jpaOffLoPower,
            int maxEval,
            @NonNull float[] biasAmplitudeLBs, @NonNull float[] biasAmplitudeUBs,
            @NonNull float[] pumpAmplitudeLBs, @NonNull float[] pumpAmplitudeUBs,
            @NonNull float[] pumpLoFrequencyLBs, @NonNull float[] pumpLoFrequencyUBs,
            @NonNull float[] biasAmplitudeDeltas, @NonNull float[] pumpAmplitudeDeltas,
            @NonNull float[] pumpLoFrequenciesDeltas,
            float g0, float g1, float g2, float loMinFrequency,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1003,
                    Data.clusterOf(Data.valueOf(jpaAgents), Data.valueOf(readoutAgents),
                            Data.valueOf(pumpWithMixer), Data.valueOf(jpaOffLoPower), Data.valueOf(maxEval),
                            Data.valueOf(biasAmplitudeLBs), Data.valueOf(biasAmplitudeUBs),
                            Data.valueOf(pumpAmplitudeLBs), Data.valueOf(pumpAmplitudeUBs),
                            Data.valueOf(pumpLoFrequencyLBs), Data.valueOf(pumpLoFrequencyUBs),
                            Data.valueOf(biasAmplitudeDeltas), Data.valueOf(pumpAmplitudeDeltas), Data.valueOf(pumpLoFrequenciesDeltas),
                            Data.valueOf(g0), Data.valueOf(g1), Data.valueOf(g2), Data.valueOf(loMinFrequency),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSpectroscopyZDCLo(@NonNull String driveQubit, int driveLength, float driveAmplitude, float driveSBFrequency,
                                   @NonNull String biasQubit, @NonNull String readoutQubit, @NonNull String dataType,
                                   @NonNull float[] biasDCLevel, @NonNull float[] driveFrequency,
                                   boolean continuousExecution, boolean enableParallelMode,
                                   int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1100,
                    Data.clusterOf(Data.valueOf(driveQubit), Data.valueOf(driveLength),
                            Data.valueOf(driveAmplitude), Data.valueOf(driveSBFrequency), Data.valueOf(biasQubit),
                            Data.valueOf(readoutQubit),Data.valueOf(dataType),Data.valueOf(biasDCLevel),Data.valueOf(driveFrequency),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSpectroscopyZPALo(@NonNull String driveQubit, int driveLength, float driveAmplitude, float driveSBFrequency,
                                   @NonNull String biasQubit, @NonNull String readoutQubit,
                                   @NonNull String[] detuneAgents, @NonNull float[] detuneAmplitudes,
                                   @NonNull String dataType,
                                   @NonNull float[] biasAmplitudes, @NonNull float[] driveFrequencies,
                                   boolean continuousExecution, boolean enableParallelMode,
                                   int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1102,
                    Data.clusterOf(Data.valueOf(driveQubit), Data.valueOf(driveLength),
                            Data.valueOf(driveAmplitude), Data.valueOf(driveSBFrequency), Data.valueOf(biasQubit),
                            Data.valueOf(readoutQubit), Data.valueOf(detuneAgents), Data.valueOf(detuneAmplitudes),
                            Data.valueOf(dataType),Data.valueOf(biasAmplitudes),Data.valueOf(driveFrequencies),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSpectroscopyZPALoAFR(@NonNull String qubit, int driveLength,
                                            float driveAmplitude, float peakAmplitudeLB, float driveSBFrequency,
                                            float frequencyRange, float frequencyStep,
                                            @NonNull String[] detuneAgents, @NonNull float[] detuneAmplitudes,
                                            @NonNull float[] biasAmplitudes,
                                            @NonNull String fitterType, float fah, float fitErrorUB, float kfEstimation,
                                            boolean continuousExecution, boolean enableParallelMode,
                                            int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1103,
                    Data.clusterOf(Data.valueOf(qubit),  Data.valueOf(driveLength),  Data.valueOf(driveAmplitude),
                            Data.valueOf(peakAmplitudeLB), Data.valueOf(driveSBFrequency), Data.valueOf(frequencyRange),
                            Data.valueOf(frequencyStep),
                            Data.valueOf(detuneAgents), Data.valueOf(detuneAmplitudes),
                            Data.valueOf(biasAmplitudes), Data.valueOf(fitterType),
                            Data.valueOf(fah), Data.valueOf(fitErrorUB), Data.valueOf(kfEstimation),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runAnharmonicity(@NonNull String[] qubits, int driveLength, @NonNull float[] driveAmplitudes,
                                 @NonNull float[][] frequencies,
                                 boolean continuousExecution, boolean enableParallelMode, int numShots, @NonNull String notes){
        try {
            Data ret = getClient().sendRequest(getContextId(),new Record(1104,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(driveLength),
                            Data.valueOf(driveAmplitudes), Data.valueOf(frequencies),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSpectroscopyZPASB(@NonNull String[] driveNReadoutQubits, int driveLength, @NonNull float[] driveAmplitudes,
                                     float loFrequency, @NonNull String[] biasAgents,
                                     boolean biasAgentsAreXTGsNTurnOffXtalkCompensation,
                                     @NonNull String[] detuneAgents, @NonNull float[] detuneAmplitudes,
                                     @NonNull String dataType,
                                     @NonNull float[][] biasAmplitudes, @NonNull float[][] driveFrequencies,
                                     boolean continuousExecution, boolean enableParallelMode,
                                     int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1105,
                    Data.clusterOf(Data.valueOf(driveNReadoutQubits), Data.valueOf(driveLength),
                            Data.valueOf(driveAmplitudes), Data.valueOf(loFrequency), Data.valueOf(biasAgents),
                            Data.valueOf(biasAgentsAreXTGsNTurnOffXtalkCompensation),
                            Data.valueOf(detuneAgents), Data.valueOf(detuneAmplitudes),
                            Data.valueOf(dataType),Data.valueOf(biasAmplitudes),Data.valueOf(driveFrequencies),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runFixedFrequencySpectroscopy(@NonNull String[] qubits, @NonNull int[] driveLengths, int biasLength,
                                              float loFrequency, @NonNull float[] driveFrequencies,
                                              @NonNull String dataType,
                                              @NonNull float[][] biasAmplitudes, @NonNull float[][] driveAmplitudes,
                                              boolean continuousExecution, boolean enableParallelMode,
                                              int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1106,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(driveLengths),
                            Data.valueOf(biasLength), Data.valueOf(loFrequency), Data.valueOf(driveFrequencies),
                            Data.valueOf(dataType),Data.valueOf(biasAmplitudes),Data.valueOf(driveAmplitudes),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRabiAmpDetune(@NonNull String[] driveQubits,
                           @NonNull String[] readoutQubits, @NonNull String driveGate, int numDriveGates, @NonNull String dataType,
                           @NonNull float[] xyAmplitude, @NonNull float[] f01s,
                           boolean continuousExecution, boolean enableParallelMode,
                           int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1200,
                    Data.clusterOf(Data.valueOf(driveQubits), Data.valueOf(readoutQubits),
                            Data.valueOf(driveGate), Data.valueOf(numDriveGates), Data.valueOf(dataType),
                            Data.valueOf(xyAmplitude),Data.valueOf(f01s),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRabiAmpDetune(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes,
            @NonNull String[] driveGates, @NonNull int[] numDriveGates, @NonNull String dataType,
            @NonNull float[][] xyAmplitudes, @NonNull float[][] detunings,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1200,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(driveGates),
                            Data.valueOf(numDriveGates), Data.valueOf(dataType),
                            Data.valueOf(xyAmplitudes),Data.valueOf(detunings),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runT1(
            @NonNull String qubit,
            @NonNull String dataType, boolean measureReference, @NonNull float[] detuneAmplitude, @NonNull int[] delay,
            boolean continuousExecution, boolean enableParallelMode,int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1202,
                    Data.clusterOf(Data.valueOf(qubit),  Data.valueOf(dataType),  Data.valueOf(measureReference),
                            Data.valueOf(detuneAmplitude), Data.valueOf(delay),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runT1(
            @NonNull String[] qubits,
            @NonNull String dataType, boolean measureReference, boolean detuneInFrequency,
            @NonNull float[][] detuneAmplitudes, @NonNull int[][] delays,
            boolean continuousExecution, boolean enableParallelMode,int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1202,
                    Data.clusterOf(Data.valueOf(qubits),  Data.valueOf(dataType),  Data.valueOf(measureReference),
                            Data.valueOf(detuneInFrequency),
                            Data.valueOf(detuneAmplitudes), Data.valueOf(delays),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRamsey(
            @NonNull String qubit, int numDDs,
            @NonNull String dataType, boolean detuneMode, @NonNull int[] delays, @NonNull float[] detunings,
            boolean continuousExecution, boolean enableParallelMode, int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1203,
                    Data.clusterOf(Data.valueOf(qubit),  Data.valueOf(numDDs),  Data.valueOf(dataType),
                            Data.valueOf(detuneMode), Data.valueOf(delays),Data.valueOf(detunings),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRamsey(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes,
            @NonNull int[] numDDs, @NonNull String dataType,
            boolean detuneMode, @NonNull int[][] delays, @NonNull float[][] detunings,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1203,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(numDDs),  Data.valueOf(dataType),
                            Data.valueOf(detuneMode), Data.valueOf(delays),Data.valueOf(detunings),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRamseyEnvelop(@NonNull String qubit,
                                 int numDDs, @NonNull float[] detunings, @NonNull int[] delays,
                                 boolean continuousExecution, boolean enableParallelMode,
                                 int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1204,
                    Data.clusterOf(Data.valueOf(qubit),  Data.valueOf(numDDs),  Data.valueOf(detunings), Data.valueOf(delays),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRamseyEnvelop(@NonNull String[] qubits,
                                 int numDDs, @NonNull float[][] detunings, @NonNull int[][] delays,
                                 boolean continuousExecution, boolean enableParallelMode,
                                 int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1204,
                    Data.clusterOf(Data.valueOf(qubits),  Data.valueOf(numDDs),  Data.valueOf(detunings), Data.valueOf(delays),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runDRAGAlphaAPE(@NonNull String[] qubits,
                                 @NonNull float[][] dragAlphas, @NonNull int[] numIs,
                                 boolean continuousExecution, boolean enableParallelMode,
                                 int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1205,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(dragAlphas),
                            Data.valueOf(numIs),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runXYGateDetuning(@NonNull String[] qubits,
                                  @NonNull int[] numIs, @NonNull float[] dragAlphas, @NonNull float[][] detunings,
                                  boolean continuousExecution, boolean enableParallelMode,
                                  int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1206,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(numIs), Data.valueOf(dragAlphas),
                            Data.valueOf(detunings),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runZDistortion(@NonNull String[] qubits, @NonNull float[] zBiasAmplitudes, @NonNull int zBiasLength,
                               @NonNull int[] delays, @NonNull String phaseMode, int slidingWindowWidth,
                               boolean continuousExecution, boolean enableParallelMode,
                               int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1400,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(zBiasLength), Data.valueOf(zBiasAmplitudes),
                            Data.valueOf(delays), Data.valueOf(phaseMode), Data.valueOf(slidingWindowWidth),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutPhotonNumber(
            @NonNull String[] readoutAgents, @NonNull String[] qubits,
            @NonNull int[] readoutDriveLengths, @NonNull int[] qubitDriveLengths,
            @NonNull float[] qubitDriveAmplitudes,
            @NonNull String dataType,
            @NonNull float[][] qubitDriveFrequencies, @NonNull int[][] qubitDriveDelays,
            @NonNull float[][] readoutDriveAmplitudes, @NonNull float[][] readoutDriveFrequencies,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(1450,
                    Data.clusterOf(Data.valueOf(readoutAgents), Data.valueOf(qubits),
                            Data.valueOf(readoutDriveLengths), Data.valueOf(qubitDriveLengths),
                            Data.valueOf(qubitDriveAmplitudes), Data.valueOf(dataType),
                            Data.valueOf(qubitDriveFrequencies), Data.valueOf(qubitDriveDelays),
                            Data.valueOf(readoutDriveAmplitudes), Data.valueOf(readoutDriveFrequencies),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runQQSwap(
            @NonNull String sweepDetuneQubit, @NonNull String fixedDetuneQubit,
            @NonNull String[] otherDetuneQubits, float fixedDetuneAmplitude, @NonNull float[] otherDetuneAmplitudes,
            @NonNull String driveGate1, @NonNull String driveGate2,
            @NonNull String dataType, @NonNull float[] amplitudes, @NonNull int[] swapTimes,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(2000,
                    Data.clusterOf(Data.valueOf(sweepDetuneQubit),  Data.valueOf(fixedDetuneQubit),  Data.valueOf(otherDetuneQubits),
                            Data.valueOf(fixedDetuneAmplitude),
                            Data.valueOf(otherDetuneAmplitudes),Data.valueOf(driveGate1),
                            Data.valueOf(driveGate2),Data.valueOf(dataType),
                            Data.valueOf(amplitudes),Data.valueOf(swapTimes),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runDriveBiasTiming(
            @NonNull String[] xyDriveQubits, @NonNull String[] zBiasAgents,
            @NonNull int[] zBiasLengths, @NonNull float[] zBiasAmplitudes, boolean netZeroZBias, @NonNull int[][] delays,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(2001,
                    Data.clusterOf(Data.valueOf(xyDriveQubits),  Data.valueOf(zBiasAgents),  Data.valueOf(zBiasLengths),
                            Data.valueOf(zBiasAmplitudes), Data.valueOf(netZeroZBias), Data.valueOf(delays),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runQGTimingBiasXTalkImpl(@NonNull String[] qubits, @NonNull String[] couplers,
                                         @NonNull int[] zBiasLengths, @NonNull float[] zBiasAmplitudes, @NonNull int[][] delays,
                                         boolean continuousExecution, boolean enableParallelMode,
                                         int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(2003,
                    Data.clusterOf(Data.valueOf(qubits),  Data.valueOf(couplers),  Data.valueOf(zBiasLengths),
                            Data.valueOf(zBiasAmplitudes), Data.valueOf(delays),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runZXTalk(@NonNull String sourceAgent, @NonNull String targetQubit, int pulseLength,
            int numPulses, float[] pulseAmplitudes, boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(2004,
                    Data.clusterOf(Data.valueOf(sourceAgent),  Data.valueOf(targetQubit),  Data.valueOf(pulseLength),
                            Data.valueOf(numPulses), Data.valueOf(pulseAmplitudes),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runACZLengthAmplitude(@NonNull String aczAgent, @NonNull String qubit1DriveGate,
                                      @NonNull String qubit2DriveGate, @NonNull String dataType,
                                      @NonNull int[] aczLength, @NonNull float[] aczAmplitude,
                                      boolean continuousExecution, boolean enableParallelMode, int numShots, @NonNull String notes){
        try {
            Data ret = getClient().sendRequest(getContextId(),new Record(3000,
                    Data.clusterOf(Data.valueOf(aczAgent), Data.valueOf(qubit1DriveGate),
                            Data.valueOf(qubit2DriveGate), Data.valueOf(dataType), Data.valueOf(aczLength), Data.valueOf(aczAmplitude),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRamseyErrorFilter(@NonNull String aczAgent,@NonNull int[] delays,
                                     boolean continuousExecution, boolean enableParallelMode,
                                     int numShots, @NonNull String notes){
        try {
            Data ret = getClient().sendRequest(getContextId(),new Record(3001,
                    Data.clusterOf(Data.valueOf(aczAgent), Data.valueOf(delays),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSimpleStateTomography(
            @NonNull String[] qubits, // @NonNull String[][] extraTuneFields, // there is a bug to fix in rpc in handling String[][]
            boolean jointMode, boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4000,
                    Data.clusterOf(Data.valueOf(qubits), // Data.valueOf(extraTuneFields),
                            Data.valueOf(jointMode),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSingleQStateTomography(
            @NonNull String[] qubits,  @NonNull String[] initializationGates, // @NonNull String[][] extraTuneFields, // there is a bug to fix in rpc in handling String[][]
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4001,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(initializationGates), // Data.valueOf(extraTuneFields),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSimpleProcessTomography1Q(@NonNull String qubit, @NonNull String[] process, // process: instruction string
                                             boolean continuousExecution, boolean enableParallelMode,
                                             int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4002,
                    Data.clusterOf(Data.valueOf(qubit), Data.valueOf(process),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSimpleProcessTomography2Q(@NonNull String coupler,
                                          @NonNull String[] process, // process: instruction string
                                          boolean continuousExecution, boolean enableParallelMode,
                                          int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4003,
                    Data.clusterOf(
                            Data.valueOf(coupler), Data.valueOf(process),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runSingleQAXYStateTomography( @NonNull String[] qubits,
            @NonNull float[][] gateAmplitudes, @NonNull float[][] detunings, @NonNull float[][] alphas,
            @NonNull int[][] lengths, @NonNull float[][] azimuths,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(4004,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(gateAmplitudes), Data.valueOf(detunings),
                            Data.valueOf(alphas), Data.valueOf(lengths), Data.valueOf(azimuths),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRandomizedBenchmarking1Q(
            @NonNull String qubit,
            String benchMarkGate, int ILength,
            boolean measureReference, boolean measureInterleaved, boolean measureState2,
            @NonNull int[] numClifords, @NonNull int[] numRandomSequences,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4100,
                    Data.clusterOf(Data.valueOf(qubit), Data.valueOf(benchMarkGate),  Data.valueOf(ILength), Data.valueOf(measureReference),
                            Data.valueOf(measureInterleaved), Data.valueOf(measureState2),
                            Data.valueOf(numClifords), Data.valueOf(numRandomSequences),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRandomizedBenchmarking1Q(@NonNull String[] qubits,
                                            String benchMarkGate, int ILength,
                                            boolean measureReference, boolean measureInterleaved,
                                            @NonNull int[] numClifords, @NonNull int[] numRandomSequences,
                                            boolean continuousExecution, boolean enableParallelMode,
                                            int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4100,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(benchMarkGate),  Data.valueOf(ILength), Data.valueOf(measureReference),
                            Data.valueOf(measureInterleaved), Data.valueOf(numClifords), Data.valueOf(numRandomSequences),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRandomizedBenchmarking2Q(@NonNull String aczAgent, String benchMarkGate, int ILength,
                                            boolean measureReference, boolean measureInterleaved,
                                            @NonNull int[] numClifords, @NonNull int[] numRandomSequences,
                                            boolean continuousExecution, boolean enableParallelMode,
                                            int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4101,
                    Data.clusterOf(Data.valueOf(aczAgent),
                            Data.valueOf(benchMarkGate), Data.valueOf(ILength), Data.valueOf(measureReference),
                            Data.valueOf(measureInterleaved), Data.valueOf(numClifords), Data.valueOf(numRandomSequences),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRBXEBImpl1Q(@NonNull String[] qubits,
                               @NonNull String[] interleavingQCircuit, // @NonNull String[][] interleavingQCircuit, // there is a bug to fix in rpc in handling String[][]
                               @NonNull double[][] interleavingCircuitMatrix, boolean measureState2,
                               @NonNull int[] numCycles, @NonNull int[] numRandomSequences,
                               boolean continuousExecution, boolean enableParallelMode,
                               int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4103,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(interleavingQCircuit),
                            Data.valueOf(interleavingCircuitMatrix), Data.valueOf(measureState2),
                            Data.valueOf(numCycles),
                            Data.valueOf(numRandomSequences),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runRBXTGFSimXEBImpl(@NonNull String[] couplers,
                                    @NonNull int[] fSimIndexes, boolean measureState2,
                                    @NonNull int[] numCycles, @NonNull int[] numRandomSequences,
                                    boolean continuousExecution, boolean enableParallelMode,
                                    int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(4104,
                    Data.clusterOf(Data.valueOf(couplers),
                            Data.valueOf(fSimIndexes), Data.valueOf(measureState2), Data.valueOf(numCycles),
                            Data.valueOf(numRandomSequences),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runOptimalReadoutFrequencyCal(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits,  @NonNull float[] biasAmplitudes,
            @NonNull double[] frequencyRanges, int numSamples, float minSNR,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5000,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(frequencyRanges), Data.valueOf(numSamples), Data.valueOf(minSNR),
                            Data.valueOf(continuousExecution),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runOptimalReadoutFrequencyCal(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits,  @NonNull float[] biasAmplitudes,
            boolean measureState2, boolean forParamSet012,
            @NonNull double[] frequencyRanges, int numSamples, float minSNR,
            boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5000,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(measureState2), Data.valueOf(forParamSet012),
                            Data.valueOf(frequencyRanges), Data.valueOf(numSamples), Data.valueOf(minSNR),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runIQ2Probability(@NonNull String[] qubits, @NonNull String[] biasAwayQubits, @NonNull float[] biasAmplitudes,
                                  boolean measureState2, boolean forParamSet012, double maxDrift, double minSNR, double maxSNRDrop,
                                   boolean enableParallelMode, boolean saveRawData, int numShots){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5001,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(measureState2), Data.valueOf(forParamSet012), Data.valueOf(maxDrift), Data.valueOf(minSNR), Data.valueOf(maxSNRDrop),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runErrorVsReadoutAmplitude(@NonNull String[] qubits,
                                            @NonNull float[][] amplitudes, boolean measureState2,
                                            boolean continuousExecution, boolean enableParallelMode, int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5002,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(amplitudes), Data.valueOf(measureState2),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runReadoutFrequencyFinder(@NonNull String[] readoutAgents,
                                          @NonNull float[] readoutAmplitudesH, @NonNull float[] readoutAmplitudesL,
                                          @NonNull float[] sidebandFrequencies,
                                          float dipWidthEstimation, float dispersiveShiftEstimation,
                                          float frequencyStep, float minDipAmplitude, float maxOverallFrequencyShift,
                                          float maxIndividualFrequencyShift, float minSeparation,
                                          boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5003,
                    Data.clusterOf(Data.valueOf(readoutAgents), Data.valueOf(readoutAmplitudesH),
                            Data.valueOf(readoutAmplitudesL),
                            Data.valueOf(sidebandFrequencies),
                            Data.valueOf(dipWidthEstimation), Data.valueOf(dispersiveShiftEstimation),
                            Data.valueOf(frequencyStep), Data.valueOf(minDipAmplitude), Data.valueOf(maxOverallFrequencyShift),
                            Data.valueOf(maxIndividualFrequencyShift), Data.valueOf(minSeparation),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runReadoutFrequencyPowerDependency(
            @NonNull String[] qubits,
            float readoutAmplitude0, float readoutAmplitude1, int numReadoutAmplitudes,
            float dipWidthEstimation, float chiEstimation, // chi = - g^2/Delta^2*anharmonicity
            float frequencyStep, float minDipAmplitude, boolean alwaysUpdate,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5004,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(readoutAmplitude0),
                            Data.valueOf(readoutAmplitude1),Data.valueOf(numReadoutAmplitudes),
                            Data.valueOf(dipWidthEstimation), Data.valueOf(chiEstimation),
                            Data.valueOf(frequencyStep), Data.valueOf(minDipAmplitude), Data.valueOf(alwaysUpdate),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runReadoutFrequencyNAmplitudeOptimizer(
            @NonNull String[] qubits, String[] biasAwayQubits, float[] biasAmplitudes, @NonNull float[] dipWidths,
            boolean measureState2, int maxIteration, float minSNR, float maxOutlierRatio,
            boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5005,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(dipWidths),
                            Data.valueOf(measureState2), Data.valueOf(maxIteration),
                            Data.valueOf(minSNR), Data.valueOf(maxOutlierRatio),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runCorrectF01ByPhaseTomo(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, @NonNull int[] numDDs,
            @NonNull int[][] delays, float f01DriftLimit, float fitErrorUB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5100,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits),
                            Data.valueOf(workBiasAmplitudes), Data.valueOf(numDDs), Data.valueOf(delays),
                            Data.valueOf(f01DriftLimit), Data.valueOf(fitErrorUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runF01DriftVsTime(@NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes,
                                  @NonNull int[] numDDs, @NonNull int[][] delays,
                                  int takeDelayInSeconds, int totalTimeDurationInSeconds,
                                  boolean continuousExecution, boolean enableParallelMode,
                                  int numShots, String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5101,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits),
                            Data.valueOf(workBiasAmplitudes), Data.valueOf(numDDs), Data.valueOf(delays),
                            Data.valueOf(takeDelayInSeconds), Data.valueOf(totalTimeDurationInSeconds),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runXYGateAmplitudeRabiCal(@NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes,
                                          int numDataPoints,
                                          @NonNull int[] numPis, @NonNull String gate,
                                          float fitErrorUB, float minVisibility, float maxDrift,
                                          boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5102,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits),
                            Data.valueOf(workBiasAmplitudes), Data.valueOf(numDataPoints), Data.valueOf(numPis),
                            Data.valueOf(gate), Data.valueOf(fitErrorUB),
                            Data.valueOf(minVisibility), Data.valueOf(maxDrift),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runXYGateHPiRBClifordOptimizer0(@NonNull String[] qubits, int maxEval,
                                     int numClifords, int numRandomSequences,
                                     boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5103,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(maxEval), Data.valueOf(numClifords),
                            Data.valueOf(numRandomSequences),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runXYGateRBClifordOptimizer0(@NonNull String[] qubits, int maxEval,
                                                int numClifords, int numRandomSequences,
                                                boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5104,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(maxEval), Data.valueOf(numClifords),
                            Data.valueOf(numRandomSequences),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runXYGateRBIQSBOptimizer(@NonNull String[] qubits, int maxEval,
                                     int numClifords, int numRandomSequences,
                                         float epsilonLB, float epsilonUB, float deltaLB, float deltaUB,
                                     boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5200,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(maxEval), Data.valueOf(numClifords),
                            Data.valueOf(numRandomSequences),
                            Data.valueOf(epsilonLB), Data.valueOf(epsilonUB),Data.valueOf(deltaLB),Data.valueOf(deltaUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runXYGateAmplitudeIniCal(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, int numDataPoints,
            @NonNull String gate, float minSNR,
            boolean adjustGateLengthIfOutOfBounds,
            float gateAmplitudeLB, float gateAmplitudeUB, // relative to gateAmplitudeDACLimit
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5201,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(numDataPoints), Data.valueOf(gate), Data.valueOf(minSNR),
                            Data.valueOf(adjustGateLengthIfOutOfBounds),
                            Data.valueOf(gateAmplitudeLB), Data.valueOf(gateAmplitudeUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runZDistortionCalExpDecay(
            @NonNull String[] qubits, int zBiasLength, @NonNull float[] zBiasAmplitudes,
            @NonNull int[] delays,
            @NonNull float[] alphaLowerBound, @NonNull float[] alphaUpperBound, @NonNull int[] decayTimeUpperBound,
            float fitErrorUpperBound, float lowpassFilterBandWidth,
            @NonNull String phaseMode, int slidingWindowWidth, float maxResidualRatio,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5800,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(zBiasLength), Data.valueOf(zBiasAmplitudes),
                            Data.valueOf(delays),
                            Data.valueOf(alphaLowerBound), Data.valueOf(alphaUpperBound),Data.valueOf(decayTimeUpperBound),
                            Data.valueOf(fitErrorUpperBound), Data.valueOf(lowpassFilterBandWidth),
                            Data.valueOf(phaseMode), Data.valueOf(slidingWindowWidth), Data.valueOf(maxResidualRatio),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runDRAGAlphaCal(
            @NonNull String[] qubits, @NonNull float[] alphaRanges,
            @NonNull int[][] numIs, int numDataPoints, float visibilityLB,
            boolean continuousExecution, boolean enableParallelMode, int numShots,
            float changeBound
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(5801,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(alphaRanges), Data.valueOf(numIs),
                            Data.valueOf(numDataPoints), Data.valueOf(visibilityLB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(numShots),
                            Data.valueOf(changeBound)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runReadoutIQLoCalibrator(
            @NonNull String[] readoutAgents, @NonNull float[][] loFrequencies,
            float iqZerosLB, float iqZerosUB, int maxEval,
            boolean continuousExecution, boolean enableParallelMode, int numShots, boolean verbose
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(6000,
                    Data.clusterOf(Data.valueOf(readoutAgents), Data.valueOf(loFrequencies),
                            Data.valueOf(iqZerosLB), Data.valueOf(iqZerosUB), Data.valueOf(maxEval),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(numShots),
                            Data.valueOf(verbose)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    @Deprecated
    public void runReadoutIQSBCalibrator(
            @NonNull String[] readoutAgents,
            @NonNull float[] readoutAmplitudes, @NonNull float[][] loFrequencies,
            @NonNull float[][] sidebandFrequencies,
            double epsilonLB, float epsilonUB, float deltaLB, float deltaUB, int maxEval,
            boolean continuousExecution,
            boolean enableParallelMode, int numShots, boolean verbose
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(6001,
                    Data.clusterOf(Data.valueOf(readoutAgents), Data.valueOf(readoutAmplitudes), Data.valueOf(loFrequencies),
                            Data.valueOf(sidebandFrequencies),
                            Data.valueOf(epsilonLB), Data.valueOf(epsilonUB),Data.valueOf(deltaLB),Data.valueOf(deltaUB),
                            Data.valueOf(maxEval),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(numShots),
                            Data.valueOf(verbose)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutIQGShift(
            @NonNull String[] couplers, @NonNull boolean[] measureQ0s,
            @NonNull String[] detuneQubits,
            @NonNull float[] detuneAmplitudes, @NonNull float[][] biasAmplitudes,
            boolean isDCBias, boolean isDCDetune,
            boolean continuousExecution, boolean enableParallelMode,
            int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(7000,
                    Data.clusterOf(Data.valueOf(couplers), Data.valueOf(measureQ0s), Data.valueOf(detuneQubits),
                            Data.valueOf(detuneAmplitudes),
                            Data.valueOf(biasAmplitudes), Data.valueOf(isDCBias),Data.valueOf(isDCDetune),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runQQSwapG(@NonNull String[] couplers,
                           @NonNull float[] detuneAmplitude0s, @NonNull float[] detuneAmplitude1s, boolean isDCBias,
                           @NonNull String[] driveGate0s, @NonNull String[] driveGate1s,
                           @NonNull String dataType,
                           @NonNull float[][] couplerBiasAmplitudes, @NonNull int[][] swapTimes,
                           boolean continuousExecution, boolean enableParallelMode, int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(7001,
                    Data.clusterOf(Data.valueOf(couplers),
                            Data.valueOf(detuneAmplitude0s), Data.valueOf(detuneAmplitude1s), Data.valueOf(isDCBias),
                            Data.valueOf(driveGate0s),Data.valueOf(driveGate1s),
                            Data.valueOf(dataType), Data.valueOf(couplerBiasAmplitudes), Data.valueOf(swapTimes),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runCPhaseG(
            @NonNull String[] couplers, @NonNull boolean[] measureQ0s,
            @NonNull float[] detuneAmplitude0s, @NonNull float[] detuneAmplitude1s, boolean isDCBias,
            @NonNull float[][] couplerBiasAmplitudes, @NonNull int[][] swapTimes,
            boolean continuousExecution, boolean enableParallelMode, int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(),new Record(7002,
                    Data.clusterOf(Data.valueOf(couplers), Data.valueOf(measureQ0s),
                            Data.valueOf(detuneAmplitude0s), Data.valueOf(detuneAmplitude1s), Data.valueOf(isDCBias),
                            Data.valueOf(couplerBiasAmplitudes), Data.valueOf(swapTimes),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runFSim(@NonNull String[] couplers,
                        @NonNull int[] fSimIndexes, int numGates, @NonNull String dataType,
                        @NonNull String[] driveGate0s, @NonNull String[] driveGate1s,
                        @NonNull float[][] activeLengths, @NonNull int[][] fSimDetuneEdgeWidths, @NonNull int[][] fSimGEdgeWidths,
                        @NonNull float[][] fSimDetunes, @NonNull boolean[] isDetune1stQubits, @NonNull float[][] fSimGs,
                        boolean continuousExecution, boolean enableParallelMode,
                        int numShots, @NonNull String notes){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(7003,
                    Data.clusterOf(Data.valueOf(couplers),
                            Data.valueOf(fSimIndexes), Data.valueOf(numGates),
                            Data.valueOf(dataType), Data.valueOf(driveGate0s), Data.valueOf(driveGate1s),
                            Data.valueOf(activeLengths), Data.valueOf(fSimDetuneEdgeWidths), Data.valueOf(fSimGEdgeWidths),
                            Data.valueOf(fSimDetunes), Data.valueOf(isDetune1stQubits), Data.valueOf(fSimGs),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runFSimActiveLengthVsLeakage( @NonNull String[] couplers, @NonNull int[] fSimIndexes,
            int numCycles, int numRandomSequences, float[][] activeLengths,
            boolean continuousExecution, boolean enableParallelMode, int numShots, @NonNull String notes
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(7004,
                    Data.clusterOf(Data.valueOf(couplers),
                            Data.valueOf(numCycles), Data.valueOf(numRandomSequences), Data.valueOf(activeLengths),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(numShots), Data.valueOf(notes)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    /*
     * control work nodes
     */

    public void runIQ2ProbabilityNodeCompareToHistoryImpl0(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits, @NonNull float[] biasAmplitudes,
            boolean measureState2, boolean forParamSet012, float minSNR, float maxSNRDrop, float maxOutlierRatio, float maxDrift,
            boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001000,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(measureState2), Data.valueOf(forParamSet012), Data.valueOf(minSNR), Data.valueOf(maxSNRDrop),
                            Data.valueOf(maxOutlierRatio), Data.valueOf(maxDrift),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIQ2ProbabilityNodeMultiRunImpl0(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits, @NonNull float[] biasAmplitudes,
            boolean measureState2, boolean forParamSet012, int numRuns, int successThreshold,
            float minSNR, float maxOutlierRatio, float clusterDistance,
            boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001001,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(measureState2), Data.valueOf(forParamSet012),
                            Data.valueOf(numRuns), Data.valueOf(successThreshold),
                            Data.valueOf(minSNR), Data.valueOf(maxOutlierRatio), Data.valueOf(clusterDistance),
                            Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIQ2ProbabilityNodeWithBackupImpl0(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits, @NonNull float[] biasAmplitudes,
            boolean measureState2, boolean forParamSet012,
            float minSNR, float maxSNRDrop, float maxOutlierRatio, float maxDrift, int numRepeatOfMainCal,
            int numRuns, int successThreshold, float clusterDistance,
            boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001002,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(measureState2), Data.valueOf(forParamSet012),
                            Data.valueOf(minSNR), Data.valueOf(maxSNRDrop),
                            Data.valueOf(maxOutlierRatio), Data.valueOf(maxDrift),
                            Data.valueOf(numRepeatOfMainCal), Data.valueOf(numRuns), Data.valueOf(successThreshold),
                            Data.valueOf(clusterDistance),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutFrequencyFinderNodeImpl0(
            @NonNull String[] readoutAgents,
            @NonNull float[] readoutAmplitudesH, @NonNull float[] readoutAmplitudesL, @NonNull float[] sidebandFrequencies,
            float dipWidthEstimation, float dispersiveShiftEstimation, float frequencyStep, float maxOverallFrequencyShift,
            float maxIndividualFrequencyShift, float minSeparation, float amplifierOffLoPower,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001100,
                    Data.clusterOf(Data.valueOf(readoutAgents), Data.valueOf(readoutAmplitudesH),
                            Data.valueOf(readoutAmplitudesL), Data.valueOf(sidebandFrequencies),
                            Data.valueOf(dipWidthEstimation), Data.valueOf(dispersiveShiftEstimation),
                            Data.valueOf(frequencyStep), Data.valueOf(maxOverallFrequencyShift),
                            Data.valueOf(maxIndividualFrequencyShift), Data.valueOf(minSeparation),
                            Data.valueOf(amplifierOffLoPower),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutFrequencyPowerDependencyNodeImpl0(
            @NonNull String[] qubits,
            float readoutAmplitude0, float readoutAmplitude1, @NonNull int numReadoutAmplitudes,
            float dipWidthEstimation, float shiftLB, float shiftEstimation, // shift = - g^2/Delta
            float frequencyStep, float minDipAmplitude, float minSNR, boolean alwaysUpdate,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001200,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(readoutAmplitude0), Data.valueOf(readoutAmplitude1),
                            Data.valueOf(numReadoutAmplitudes), Data.valueOf(dipWidthEstimation), Data.valueOf(shiftLB),
                            Data.valueOf(shiftEstimation), Data.valueOf(frequencyStep), Data.valueOf(minDipAmplitude),
                            Data.valueOf(minSNR), Data.valueOf(alwaysUpdate),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutFrequencyBiasDependencyNodeImpl0(
            @NonNull String[] qubits, @NonNull float[] bias0s, @NonNull float[] bias1s, @NonNull int numBiases,
            boolean isDCBias, float dipWidth, float chiEstimation, // chi = - g^2/Delta^2*anharmonicity
            float frequencyStep, float minDipAmplitude, float minSNR, float minTuneRange,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001250,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(bias0s),
                            Data.valueOf(bias1s), Data.valueOf(numBiases), Data.valueOf(isDCBias),
                            Data.valueOf(dipWidth), Data.valueOf(chiEstimation), Data.valueOf(frequencyStep),
                            Data.valueOf(minDipAmplitude), Data.valueOf(minSNR), Data.valueOf(minTuneRange),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runXYGateAmplitudeIniCalNodeImpl0(
            @NonNull String[] qubits,  @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, int numDataPoints,
            @NonNull String gate, float minSNR,
            boolean adjustGateLengthIfOutOfBounds,
            float gateAmplitudeLB, float gateAmplitudeUB, // relative to gateAmplitudeDACLimit
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001300,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(numDataPoints), Data.valueOf(gate), Data.valueOf(minSNR),
                            Data.valueOf(adjustGateLengthIfOutOfBounds), Data.valueOf(gateAmplitudeLB), Data.valueOf(gateAmplitudeUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runXYGateAmplitudeIniCalNodeWithBackupImpl0(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, int numDataPoints,
            @NonNull String gate, float minSNR,
            boolean adjustGateLengthIfOutOfBounds,
            float gateAmplitudeLB, float gateAmplitudeUB, // relative to gateAmplitudeDACLimit
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001301,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(numDataPoints), Data.valueOf(gate), Data.valueOf(minSNR),
                            Data.valueOf(adjustGateLengthIfOutOfBounds), Data.valueOf(gateAmplitudeLB), Data.valueOf(gateAmplitudeUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runCorrectF01NodePhaseTomoImpl0(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, @Nonnegative int numDDs,
            int maxDelay, int delayStep, float f01DriftLimit,
            float absoluteErrorUB, float snrLB, float snrCheckF01DriftLB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001400,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(numDDs), Data.valueOf(maxDelay),
                            Data.valueOf(delayStep), Data.valueOf(f01DriftLimit), Data.valueOf(absoluteErrorUB),
                            Data.valueOf(snrLB), Data.valueOf(snrCheckF01DriftLB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runCorrectF01NodeWithBackupPhaseTomoImpl0(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, @Nonnegative int numDDs,
            int maxDelay, int delayStep, int shrinkFactor, float f01DriftLimit,
            float absoluteErrorUB, float snrLB, float snrCheckF01DriftLB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001401,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(numDDs), Data.valueOf(maxDelay),
                            Data.valueOf(delayStep),Data.valueOf(shrinkFactor), Data.valueOf(f01DriftLimit),
                            Data.valueOf(absoluteErrorUB), Data.valueOf(snrLB), Data.valueOf(snrCheckF01DriftLB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runCorrectF01NodeRabiDetuneImpl0(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, @Nonnegative int numDataPoints,
            float f01DriftLimit, float fitErrorUB, boolean updateF01,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001410,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes),
                            Data.valueOf(numDataPoints), Data.valueOf(f01DriftLimit),
                            Data.valueOf(fitErrorUB), Data.valueOf(updateF01),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runDRAGAlphaCalNodeImpl0(
            @NonNull String[] qubits, @NonNull float[] alphaRanges,
            @NonNull int[][] numIs, int numDataPoints,
            float visibilityLB, float alphaLB, float alphaUB, float alphaChangeUB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001500,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(alphaRanges), Data.valueOf(numIs),
                            Data.valueOf(numDataPoints),Data.valueOf(visibilityLB), Data.valueOf(alphaLB), Data.valueOf(alphaUB),
                            Data.valueOf(alphaChangeUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runDRAGAlphaCalNodeWithBackupImpl0(
            @NonNull String[] qubits, @NonNull float[] alphaRangesFine, @NonNull float[] alphaRangesCoarse,
            @NonNull int[][] numIsFine, @NonNull int[][] numIsCoarse, int numDataPoints,
            float visibilityLB, float alphaLB, float alphaUB, float alphaChangeUB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001501,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(alphaRangesFine), Data.valueOf(alphaRangesCoarse),
                            Data.valueOf(numIsFine), Data.valueOf(numIsCoarse),
                            Data.valueOf(numDataPoints),Data.valueOf(visibilityLB), Data.valueOf(alphaLB), Data.valueOf(alphaUB),
                            Data.valueOf(alphaChangeUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIMPAGainOptimizationNodeSimplexTriImpl0(
            @NonNull String[] jpaAgents, @NonNull String[] readoutAgents,
            boolean pumpWithMixer, float jpaOffLoPower, int maxIteration,
            @NonNull float[] biasAmplitudeLBs, @NonNull float[] biasAmplitudeUBs,
            @NonNull float[] pumpAmplitudeLBs, @NonNull float[] pumpAmplitudeUBs,
            @NonNull float[] pumpLoFrequencyLBs, @NonNull float[] pumpLoFrequencyUBs,
            @NonNull float[] biasAmplitudeDeltas, @NonNull float[] pumpAmplitudeDeltas,
            @NonNull float[] pumpLoFrequenciesDeltas, float g0, float g1, float g2, float loMinFrequency,
            @Nonnegative float minGain, @Nonnegative float maxGain,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001600,
                    Data.clusterOf(Data.valueOf(jpaAgents), Data.valueOf(readoutAgents),
                            Data.valueOf(pumpWithMixer), Data.valueOf(jpaOffLoPower),
                            Data.valueOf(maxIteration),
                            Data.valueOf(biasAmplitudeLBs), Data.valueOf(biasAmplitudeUBs),
                            Data.valueOf(pumpAmplitudeLBs), Data.valueOf(pumpAmplitudeUBs),
                            Data.valueOf(pumpLoFrequencyLBs), Data.valueOf(pumpLoFrequencyUBs),
                            Data.valueOf(biasAmplitudeDeltas), Data.valueOf(pumpAmplitudeDeltas),
                            Data.valueOf(pumpLoFrequenciesDeltas),
                            Data.valueOf(g0), Data.valueOf(g1), Data.valueOf(g2),
                            Data.valueOf(loMinFrequency),Data.valueOf(minGain), Data.valueOf(maxGain),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIMPAGainOptimizationNodeSimplexBiImpl0(
            @NonNull String[] jpaAgents, @NonNull String[] readoutAgents,
            boolean pumpWithMixer, float jpaOffLoPower, int maxIteration,
            @NonNull float[] biasAmplitudes,
            @NonNull float[] pumpAmplitudeLBs, @NonNull float[] pumpAmplitudeUBs,
            @NonNull float[] pumpLoFrequencyLBs, @NonNull float[] pumpLoFrequencyUBs,
            @NonNull float[] pumpAmplitudeDeltas,
            @NonNull float[] pumpLoFrequenciesDeltas, float g0, float g1, float g2, float loMinFrequency,
            @Nonnegative float minGain, @Nonnegative float maxGain,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001601,
                    Data.clusterOf(Data.valueOf(jpaAgents), Data.valueOf(readoutAgents),
                            Data.valueOf(pumpWithMixer), Data.valueOf(jpaOffLoPower), Data.valueOf(maxIteration),
                            Data.valueOf(biasAmplitudes),
                            Data.valueOf(pumpAmplitudeLBs), Data.valueOf(pumpAmplitudeUBs),
                            Data.valueOf(pumpLoFrequencyLBs), Data.valueOf(pumpLoFrequencyUBs),
                            Data.valueOf(pumpAmplitudeDeltas),
                            Data.valueOf(pumpLoFrequenciesDeltas),
                            Data.valueOf(g0), Data.valueOf(g1), Data.valueOf(g2),
                            Data.valueOf(loMinFrequency),Data.valueOf(minGain), Data.valueOf(maxGain),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIMPAGainOptimizationNodeDETriImpl0(
            @NonNull String[] jpaAgents, @NonNull String[] readoutAgents,
            boolean pumpWithMixer, float jpaOffLoPower, int maxIteration,
            @NonNull float[] biasAmplitudeLBs, @NonNull float[] biasAmplitudeUBs,
            @NonNull float[] pumpAmplitudeLBs, @NonNull float[] pumpAmplitudeUBs,
            @NonNull float[] pumpLoFrequencyLBs, @NonNull float[] pumpLoFrequencyUBs,
            int populationSize, boolean adaptivePopulationSize,
            float populationShrinkRatio, int initialIterations, int minimumPopulationSize,
            float g0, float g1, float g2, float loMinFrequency,
            @Nonnegative float minGain, @Nonnegative float maxGain,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001602,
                    Data.clusterOf(Data.valueOf(jpaAgents), Data.valueOf(readoutAgents),
                            Data.valueOf(pumpWithMixer), Data.valueOf(jpaOffLoPower), Data.valueOf(maxIteration),
                            Data.valueOf(biasAmplitudeLBs), Data.valueOf(biasAmplitudeUBs),
                            Data.valueOf(pumpAmplitudeLBs), Data.valueOf(pumpAmplitudeUBs),
                            Data.valueOf(pumpLoFrequencyLBs), Data.valueOf(pumpLoFrequencyUBs),
                            Data.valueOf(populationSize),
                            Data.valueOf(adaptivePopulationSize), Data.valueOf(populationShrinkRatio),
                            Data.valueOf(initialIterations),Data.valueOf(minimumPopulationSize),
                            Data.valueOf(g0), Data.valueOf(g1), Data.valueOf(g2),
                            Data.valueOf(loMinFrequency),Data.valueOf(minGain), Data.valueOf(maxGain),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIMPAGainOptimizationNodeDEBiImpl0(
            @NonNull String[] jpaAgents, @NonNull String[] readoutAgents,
            boolean pumpWithMixer, float jpaOffLoPower, int maxIteration,
            @NonNull float[] biasAmplitudes,
            @NonNull float[] pumpAmplitudeLBs, @NonNull float[] pumpAmplitudeUBs,
            @NonNull float[] pumpLoFrequencyLBs, @NonNull float[] pumpLoFrequencyUBs,
            int populationSize, boolean adaptivePopulationSize,
            float populationShrinkRatio, int initialIterations, int minimumPopulationSize,
            float g0, float g1, float g2, float loMinFrequency,
            @Nonnegative float minGain, @Nonnegative float maxGain,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001603,
                    Data.clusterOf(Data.valueOf(jpaAgents), Data.valueOf(readoutAgents),
                            Data.valueOf(pumpWithMixer), Data.valueOf(jpaOffLoPower), Data.valueOf(maxIteration),
                            Data.valueOf(biasAmplitudes),
                            Data.valueOf(pumpAmplitudeLBs), Data.valueOf(pumpAmplitudeUBs),
                            Data.valueOf(pumpLoFrequencyLBs), Data.valueOf(pumpLoFrequencyUBs),
                            Data.valueOf(populationSize),
                            Data.valueOf(adaptivePopulationSize), Data.valueOf(populationShrinkRatio),
                            Data.valueOf(initialIterations),Data.valueOf(minimumPopulationSize),
                            Data.valueOf(g0), Data.valueOf(g1), Data.valueOf(g2),
                            Data.valueOf(loMinFrequency),Data.valueOf(minGain), Data.valueOf(maxGain),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runOptimalReadoutFrequencyCalNode(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits,
            @NonNull float[] biasAmplitudes, @NonNull float[] frequencyRanges,
            int numSamples, float minSNR, float minDataSNR, // minSNR is the minimum of |0>, |1> iq separation snr
            float frequencyChangeUB,
            boolean continuousExecution,boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001700,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(frequencyRanges), Data.valueOf(numSamples),
                            Data.valueOf(minSNR), Data.valueOf(minDataSNR),
                            Data.valueOf(frequencyChangeUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runOptimalReadoutFrequencyCalNode(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits,
            @NonNull float[] biasAmplitudes, boolean measureState2, boolean forParamSet012, @NonNull float[] frequencyRanges,
            int numSamples, float minSNR, float minDataSNR, // minSNR is the minimum of |0>, |1> iq separation snr
            float frequencyChangeUB, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001700,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(measureState2), Data.valueOf(forParamSet012), Data.valueOf(biasAmplitudes),
                            Data.valueOf(frequencyRanges), Data.valueOf(numSamples),
                            Data.valueOf(minSNR), Data.valueOf(minDataSNR),
                            Data.valueOf(frequencyChangeUB),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runReadoutFrequencyNAmplitudeOptimizerNodeImpl0(
            @NonNull String[] qubits, @NonNull String[] biasAwayQubits,
            @NonNull float[] biasAmplitudes, @NonNull float[] dipWidths,
            float snrLB, float outlierRatioUB, float frequencyChangeUB, float amplitudeChangeRatioUB,
            boolean measureState2, int maxIteration, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1001800,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(biasAwayQubits), Data.valueOf(biasAmplitudes),
                            Data.valueOf(dipWidths), Data.valueOf(snrLB),
                            Data.valueOf(outlierRatioUB), Data.valueOf(frequencyChangeUB),
                            Data.valueOf(amplitudeChangeRatioUB), Data.valueOf(measureState2), Data.valueOf(maxIteration),
                            Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runXYGateAmplitudeRabiCalNodeImpl0(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, int numDataPoints,
            @NonNull int[] numPis, @NonNull String gate,
            float fitErrorRatioUB, float minVisibility, float maxDrift,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002000,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits),
                            Data.valueOf(workBiasAmplitudes), Data.valueOf(numDataPoints), Data.valueOf(numPis),
                            Data.valueOf(gate),
                            Data.valueOf(fitErrorRatioUB), Data.valueOf(minVisibility), Data.valueOf(maxDrift),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runXYGateAmplitudeRabiCalNodeWithBackupImpl0(
            @NonNull String[] qubits, @NonNull String[] workBiasQubits, @NonNull float[] workBiasAmplitudes, int numDataPoints,
            @NonNull int[] numPisFine, @NonNull int[] numPisCoarse, @NonNull String gate,
            float fitErrorRatioUB, float minVisibility, float maxDrift,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002001,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(workBiasQubits), Data.valueOf(workBiasAmplitudes), Data.valueOf(numDataPoints),
                            Data.valueOf(numDataPoints), Data.valueOf(numPisFine),
                            Data.valueOf(numPisCoarse), Data.valueOf(gate),
                            Data.valueOf(fitErrorRatioUB), Data.valueOf(minVisibility), Data.valueOf(maxDrift),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runZDistortionCalNodeExpDecayImpl0(
            @NonNull String[] qubits, int zBiasLength, @NonNull float[] zBiasAmplitudes,
            int delay0, int delay1, int numDataPoints,
            @NonNull float[] alphaLowerBound, @NonNull float[] alphaUpperBound,
            @NonNull int[] decayTimeUpperBound, float lowpassFilterBandWidth,
            @NonNull String phaseMode, int slidingWindowWidth,
            float maxResidualRatio, float snrLB, float fitErrorUB, float phaseAmplitudeLB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002200,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(zBiasLength), Data.valueOf(zBiasAmplitudes),
                            Data.valueOf(delay0), Data.valueOf(delay1), Data.valueOf(numDataPoints),
                            Data.valueOf(alphaLowerBound), Data.valueOf(alphaUpperBound), Data.valueOf(decayTimeUpperBound),
                            Data.valueOf(lowpassFilterBandWidth), Data.valueOf(phaseMode), Data.valueOf(slidingWindowWidth),
                            Data.valueOf(maxResidualRatio), Data.valueOf(snrLB), Data.valueOf(fitErrorUB), Data.valueOf(phaseAmplitudeLB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runF01FinderNodeImpl0(
            @NonNull String[] qubits, int driveLength, @NonNull float[] driveAmplitudes,
            float maxSBFrequency, float minSBFrequency, @NonNull float[] backgroundBiasAmplitudes,
            float frequencyRange, float frequencyStep, @NonNull String[] detuneAgents,
            @NonNull float[] detuneAmplitudes, int smoothSpan, float f01LB, float f01UB, float snrLB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002300,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(driveLength), Data.valueOf(driveAmplitudes),
                            Data.valueOf(maxSBFrequency), Data.valueOf(minSBFrequency), Data.valueOf(backgroundBiasAmplitudes),
                            Data.valueOf(frequencyRange), Data.valueOf(frequencyStep), Data.valueOf(detuneAgents),
                            Data.valueOf(detuneAmplitudes), Data.valueOf(smoothSpan), Data.valueOf(f01LB),
                            Data.valueOf(f01UB), Data.valueOf(snrLB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runF01BiasDependencyNodeImpl0(
            @NonNull String[] qubits, boolean isDCBias, int driveLength, @NonNull float[] driveAmplitudes, float peakAmplitudeLB,
            float loFrequency, float frequencyRange, float frequencyStep,
            @NonNull String[] detuneAgents, @NonNull float[] detuneAmplitudes,
            boolean fixedFahFit, float fah, float fitErrorUB, float frequencyLB, float minTuneRange, float kfEstimation,
            int smoothSpan, @NonNull float[] biasLBs, @NonNull float[] biasUBs, @NonNull float[] biasSteps,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData,
            int numShots, boolean verbose
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002400,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(isDCBias), Data.valueOf(driveLength),
                            Data.valueOf(driveAmplitudes), Data.valueOf(peakAmplitudeLB),
                            Data.valueOf(minTuneRange), Data.valueOf(loFrequency),
                            Data.valueOf(frequencyRange), Data.valueOf(frequencyStep), Data.valueOf(detuneAgents), Data.valueOf(detuneAmplitudes),
                            Data.valueOf(fixedFahFit), Data.valueOf(fah), Data.valueOf(fitErrorUB),
                            Data.valueOf(frequencyLB), Data.valueOf(kfEstimation), Data.valueOf(smoothSpan),
                            Data.valueOf(biasLBs), Data.valueOf(biasUBs), Data.valueOf(biasSteps),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots), Data.valueOf(verbose)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runT1VsF01CalNodeLatestValueImpl(
            @NonNull String[] qubits, boolean measureReference, @NonNull float[] f0s,
            @NonNull float[] f1s, float f01Step, @NonNull int[] maxDelays, int numSamples,
            int defaultF01WindowWidth, @NonNull float defaultExpireTime,
            @NonNull float[] defaultErrorValues, @NonNull float[] defaultErrorWeights,
            float fitErrorUB, boolean continuousExecution, boolean enableParallelMode,
            boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002500,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(measureReference), Data.valueOf(f0s),
                            Data.valueOf(f1s), Data.valueOf(f01Step),
                            Data.valueOf(maxDelays), Data.valueOf(numSamples), Data.valueOf(defaultF01WindowWidth),
                            Data.valueOf(defaultExpireTime), Data.valueOf(defaultErrorValues),
                            Data.valueOf(defaultErrorWeights), Data.valueOf(fitErrorUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runT1VsF01CalNodeMeanValueImpl(
            @NonNull String[] qubits, boolean measureReference, @NonNull float[] f0s,
            @NonNull float[] f1s, float f01Step, @NonNull int[] maxDelays, int numSamples,
            int defaultF01WindowWidth, @NonNull float defaultExpireTime,
            @NonNull float[] defaultErrorValues, @NonNull float[] defaultErrorWeights,
            float fitErrorUB, boolean continuousExecution, boolean enableParallelMode,
            boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002501,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(measureReference), Data.valueOf(f0s),
                            Data.valueOf(f1s), Data.valueOf(f01Step),
                            Data.valueOf(maxDelays), Data.valueOf(numSamples), Data.valueOf(defaultF01WindowWidth),
                            Data.valueOf(defaultExpireTime), Data.valueOf(defaultErrorValues),
                            Data.valueOf(defaultErrorWeights), Data.valueOf(fitErrorUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runT1VsF01CalNodeTimeExpDecayWeightImpl(
            @NonNull String[] qubits, boolean measureReference, @NonNull float[] f0s,
            @NonNull float[] f1s, float f01Step, @NonNull int[] maxDelays, int numSamples,
            int defaultF01WindowWidth, @NonNull float defaultExpireTime,
            @NonNull float[] defaultErrorValues, @NonNull float[] defaultErrorWeights,
            float decayTimeConstant, float fitErrorUB, boolean continuousExecution, boolean enableParallelMode,
            boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002502,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(measureReference), Data.valueOf(f0s),
                            Data.valueOf(f1s), Data.valueOf(f01Step),
                            Data.valueOf(maxDelays), Data.valueOf(numSamples), Data.valueOf(defaultF01WindowWidth),
                            Data.valueOf(defaultExpireTime), Data.valueOf(defaultErrorValues),
                            Data.valueOf(defaultErrorWeights), Data.valueOf(decayTimeConstant), Data.valueOf(fitErrorUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode),
                            Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runZXTalkCalNodeImpl0(
            @NonNull String sourceQubit, @NonNull String targetQubit, int pulseLength,
            int numPulses, int numDataPoints, float maxReferenceBiasAmplitude, float maxXtalkBiasAmplitude,
            float correctionTol, // in target qubit frequency, Hz
            float xTalkFactorUB, float fitErrorUB, // in phase rad
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002800,
                    Data.clusterOf(Data.valueOf(sourceQubit), Data.valueOf(targetQubit), Data.valueOf(pulseLength),
                            Data.valueOf(numPulses), Data.valueOf(numDataPoints), Data.valueOf(maxReferenceBiasAmplitude),
                            Data.valueOf(maxXtalkBiasAmplitude), Data.valueOf(correctionTol), Data.valueOf(xTalkFactorUB),
                            Data.valueOf(fitErrorUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runDriveBiasTimingCalBaseNodeImpl0(
            @NonNull String[] driveQubits,
            @NonNull String[] biasAgents, @NonNull String[] biasAgentTypes,
            @NonNull int[] zBiasLengths, @NonNull float[] zBiasAmplitudes, boolean netZeroBias, int startDelay, int stopDelay,
            float delayUB, float snrLB, float dipAmplitudeLB, float backgroundDifferenceUB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1002900,
                    Data.clusterOf(Data.valueOf(driveQubits), Data.valueOf(biasAgents), Data.valueOf(biasAgentTypes),
                            Data.valueOf(zBiasLengths), Data.valueOf(zBiasAmplitudes), Data.valueOf(netZeroBias),
                            Data.valueOf(startDelay), Data.valueOf(stopDelay), Data.valueOf(delayUB), Data.valueOf(snrLB),
                            Data.valueOf(dipAmplitudeLB), Data.valueOf(backgroundDifferenceUB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runFSimThetaVsGCalNodeImpl0(
            @NonNull String[] couplers,
            @NonNull int[] fSimIndexes, int numGates,
            boolean isFineCal, int numDataPoints,
            float maxGChange, float minSwapAmplitude, float minSNR,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData,
            int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1005000,
                    Data.clusterOf(Data.valueOf(couplers),
                            Data.valueOf(fSimIndexes), Data.valueOf(numGates),
                            Data.valueOf(isFineCal), Data.valueOf(numDataPoints), Data.valueOf(maxGChange),
                            Data.valueOf(minSwapAmplitude), Data.valueOf(minSNR),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runFSimThetaVsDetuneCalNodeImpl0(
            @NonNull String[] couplers,
            @NonNull int[] fSimIndexes, int numGates, @NonNull boolean[] isTune1stQubit,
            @NonNull float[] detuneRanges, int numDataPoints, float minSwapAmplitude,
            float checkRange, float maxDetune, float minSNR,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1005001,
                    Data.clusterOf(Data.valueOf(couplers),
                            Data.valueOf(fSimIndexes), Data.valueOf(numGates), Data.valueOf(isTune1stQubit),
                            Data.valueOf(detuneRanges), Data.valueOf(numDataPoints), Data.valueOf(minSwapAmplitude),
                            Data.valueOf(checkRange), Data.valueOf(maxDetune), Data.valueOf(minSNR),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runIQVsXTGBiasCalNodeImpl0(
            @NonNull String[] couplers, @NonNull boolean[] measureQ0s,
            @NonNull String[] detuneQubits, @NonNull float[] detuneAmplitudes,
            boolean isDCDetune, @NonNull float[] bias0s, @NonNull float[] bias1s, int numBiases, float snrLB, float minPeakSeparation,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1005010,
                    Data.clusterOf(Data.valueOf(couplers), Data.valueOf(measureQ0s),
                            Data.valueOf(detuneQubits), Data.valueOf(detuneAmplitudes), Data.valueOf(isDCDetune),
                            Data.valueOf(bias0s), Data.valueOf(bias1s), Data.valueOf(numBiases),
                            Data.valueOf(snrLB), Data.valueOf(minPeakSeparation),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData), Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runXTGZeroPointCoarseCalNodePhaseImpl0(@NonNull String[] couplers, @NonNull boolean[] measureQ0s,
            int numDataPoints,
            @NonNull int[] interactionDurations, boolean detuneQubits,
            float changeUB, float minPhaseUB, float snrLB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1005020,
                    Data.clusterOf(Data.valueOf(couplers), Data.valueOf(measureQ0s), Data.valueOf(numDataPoints),
                            Data.valueOf(interactionDurations),
                            Data.valueOf(detuneQubits), Data.valueOf(changeUB), Data.valueOf(minPhaseUB), Data.valueOf(snrLB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runXTGZeroPointCalNodeSwapImpl0(@NonNull String[] couplers,
            int numDataPoints, @NonNull float[] detuneAmplitude0s, @NonNull float[] detuneAmplitude1s, // detune in frequency
            @NonNull float[] biasAmplitude0s, @NonNull float[] biasAmplitude1s,
            @NonNull int[] interactionDurations, boolean fineTune,
            float changeUB, float minPhaseUB, float snrLB,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1005021,
                    Data.clusterOf(Data.valueOf(couplers), Data.valueOf(numDataPoints),
                            Data.valueOf(detuneAmplitude0s), Data.valueOf(detuneAmplitude1s), Data.valueOf(biasAmplitude0s),
                            Data.valueOf(biasAmplitude1s), Data.valueOf(interactionDurations),
                            Data.valueOf(fineTune), Data.valueOf(changeUB), Data.valueOf(minPhaseUB), Data.valueOf(snrLB),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void runF01XTGBiasDependencyCalNodeImpl0(
            @NonNull String[] couplers, @NonNull boolean[] isQubit0s,
            int driveLength, @NonNull float[] driveAmplitudes, float peakAmplitudeLB, float maxSBFrequency,
            float frequencyRange, float frequencyStep, float frequencyLB,
            int smoothSpan, @NonNull float[] biasLBs, @NonNull float[] biasUBs, @NonNull float[] biasSteps,
            float f01ShiftPositiveMin, float f01ShiftNegativeMin,
            boolean continuousExecution, boolean enableParallelMode, boolean saveRawData, int numShots, boolean verbose
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1005030,
                    Data.clusterOf(Data.valueOf(couplers), Data.valueOf(isQubit0s),
                            Data.valueOf(driveLength), Data.valueOf(driveAmplitudes), Data.valueOf(peakAmplitudeLB),
                            Data.valueOf(maxSBFrequency), Data.valueOf(frequencyRange), Data.valueOf(frequencyStep),
                            Data.valueOf(frequencyLB), Data.valueOf(smoothSpan),
                            Data.valueOf(biasLBs), Data.valueOf(biasUBs), Data.valueOf(biasSteps),
                            Data.valueOf(f01ShiftPositiveMin), Data.valueOf(f01ShiftNegativeMin),
                            Data.valueOf(continuousExecution), Data.valueOf(enableParallelMode), Data.valueOf(saveRawData),
                            Data.valueOf(numShots), Data.valueOf(verbose)
                    ))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    /********************************
     * control graph
     */

    public void createControlGraph(@NonNull String controlGraphName, // unique per user
                                   boolean deleteIfAlreadyCreate){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(2_000_000,
                    Data.clusterOf(Data.valueOf(controlGraphName), Data.valueOf(deleteIfAlreadyCreate)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void deleteControlGraph(@NonNull String controlGraphName){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(2_000_001,
                    Data.valueOf(controlGraphName))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void reloadControlNode(@NonNull String controlGraphName, @NonNull String nodeName){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(2_000_002,
                    Data.clusterOf(Data.valueOf(controlGraphName), Data.valueOf(nodeName)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void setControlNodeOutOfSpec(@NonNull String controlGraphName, @NonNull String[] nodesToSet){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(2_000_003,
                    Data.clusterOf(Data.valueOf(controlGraphName), Data.valueOf(nodesToSet)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void maintainControlGraph(@NonNull String controlGraphName, @NonNull String nodeName,
                                     @NonNull String[][] calAgentGroups, boolean ignoreFailure){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(2_000_010,
                    Data.clusterOf(Data.valueOf(controlGraphName), Data.valueOf(nodeName),
                            Data.valueOf(ArrayHelper.stringArray2DTo1D(calAgentGroups)),
                            Data.valueOf(ignoreFailure)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void controlNodeRunCal(@NonNull String controlGraphName, @NonNull String nodeName, boolean update){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(2_000_011,
                    Data.clusterOf(Data.valueOf(controlGraphName), Data.valueOf(nodeName), Data.valueOf(update)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }

    public void controlNodeTestOp(@NonNull String controlGraphName, @NonNull String[] nodeNames, int op){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(2_000_100,
                    Data.clusterOf(Data.valueOf(controlGraphName), Data.valueOf(nodeNames), Data.valueOf(op)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }


    public void runSetQubitWorkingPointTest0(@NonNull String[] qubits,
            float reservedDynamicRangeP, float reservedDynamicRangeN,
            float f01FinderFrequencyRange,
            @NonNull float[] targetF01s, @NonNull float[] loFrequencies
    ){
        try {
            Data ret =  getClient().sendRequest(getContextId(), new Record(1_000_000_000,
                    Data.clusterOf(Data.valueOf(qubits), Data.valueOf(reservedDynamicRangeP),
                            Data.valueOf(reservedDynamicRangeN), Data.valueOf(f01FinderFrequencyRange),
                            Data.valueOf(targetF01s),
                            Data.valueOf(loFrequencies)))).get();
            if (ret.isError()) {
                throw new RuntimeException(ret.getErrorMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RPCException("Remote procedure call failed", e);
        }
    }


    // how to call api with zero arguments:
    /*
    public void runTemperoary(){
        getClient().sendMessage(getContextId(),new Record(1_000_000_000, Data.valueOf("_")));
    }

     */

}
