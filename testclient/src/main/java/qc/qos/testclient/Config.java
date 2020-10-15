package qc.qos.testclient;

import qc.qos.util.ArrayHelper;

/**
 * Copyright (c) 2019 onward, Yulin Wu. All rights reserved.
 * <p>
 * mail4ywu@gmail.com/mail4ywu@icloud.com
 * University of Science and Technology of China
 */
public final class Config {

    static final String systemId = "Changsha";
    // static final String systemId = "Shenzhen";


    // System Shenzhen
    static final String settingsStoreUserName;
    static final String settingsStorePassword;
    static volatile String configServerName;
    static volatile String labradManagerHost;
    static final int labradManagerPort;
    static final String labradPassword;
    static final String session;

    static volatile String qosServerHost;
    static final String sampleId;
    static final String experimentUserName;
    static final String experimentUserPassword;

    static final int numQubits;
    static String[] qubits;
    static final String[] couplers;
    static final int numCouplers;
    static final String[] readouts;
    static final int numReadoutAgents;
    static String[] amplifiers;

    static final String[] userAgents;
    static final String shiroIniFile;
    static final String dataPath;
    static final String controlGraphName;

    static String[][] maintainCalGroups;

    static {
        switch (systemId){
            case "Shenzhen":
                settingsStoreUserName = "admin";
                settingsStorePassword = "admin";
                configServerName = "sz_config_server"; // "config_server";
                labradManagerHost = "172.16.20.212"; // "localhost";
                labradManagerPort = 7682;
                labradPassword = "";
                session = "wyl_test_session_200930";
                qosServerHost = "172.16.20.212"; // changsha
                // systemId = "Shenzhen";
                sampleId = "QSL10";
                experimentUserName = "admin"; // "admin"
                experimentUserPassword = "123456";

                numQubits = 10;
                qubits = new String[numQubits];
                for (int i = 0; i < numQubits; i++) {
                    qubits[i] = "Q" + String.format("%02d", i+1);
                }
                couplers = new String[]{
                        "G0201", "G0203", "G0204", "G0205",
                        "G0906", "G0907", "G0908", "G0910"
                };
                numCouplers = couplers.length;

                readouts = new String[]{
                        "R1", "R2", "R3"
                };
                numReadoutAgents = readouts.length;

                amplifiers = new String[]{
                        "A1", "A2", "A3"
                };
                userAgents = ArrayHelper.concatenate(
                        qubits, couplers, readouts, amplifiers
                );

                shiroIniFile = "~\\shiro.ini";
                dataPath = "C:\\wyl\\data";
                controlGraphName = "qsl10bitGraph1";


                String[] qubitGroup1 = new String[]{
                        "Q01","Q04",
                        "Q03", "Q05",
                        "Q06", "Q08",
                        "Q07", "Q10"
                };
                String[] qubitGroup2 = new String[]{
                        "Q02","Q09"
                };
                String[] readoutAgents = new String[]{"R1", "R2", "R3"};
                String[] amplifiers = new String[]{"A1", "A2", "A3"};
                String[] couplerG1 = new String[]{
                        "G0201", "G0906"
                };
                String[] couplerG2 = new String[]{
                        "G0204", "G0908"
                };
                String[] couplerG3 = new String[]{
                        "G0203", "G0907"
                };
                String[] couplerG4 = new String[]{
                        "G0205", "G0910"
                };
                String[] maintainAgentsSZ = ArrayHelper.concatenate(qubitGroup1, qubitGroup2, readoutAgents, amplifiers,
                        couplerG1, couplerG2, couplerG3, couplerG4);
                maintainCalGroups = new String[maintainAgentsSZ.length][];
                for (int i = 0; i < maintainAgentsSZ.length; i++){
                    maintainCalGroups[i] = new String[]{maintainAgentsSZ[i]};
                }


                break;
            case "Changsha":
                settingsStoreUserName = "admin";
                settingsStorePassword = "admin";
                configServerName = "cs_config_server"; // "config_server";
                labradManagerHost = "172.16.20.234"; // "localhost";
                labradManagerPort = 7682;
                labradPassword = "";
                session = "wyl_test_session_200911"; // wyl_test_session_200829 // TestSession200608
                qosServerHost = "172.16.20.234"; // changsha
                sampleId = "QS66G1";
                experimentUserName = "admin"; // "admin"
                experimentUserPassword = "123456";

                numQubits = 66;
                qubits = new String[numQubits];
                for (int i = 0; i < numQubits; i++) {
                    qubits[i] = "Q" + String.format("%02d", i);
                }

                couplers = new String[]{
                        "G0600", "G0700", "G0701", "G0801", "G0802", "G0902", "G0903", "G1003", "G1004", "G1104", "G1105",
                        "G1206", "G1207", "G1307", "G1308", "G1408", "G1409", "G1509", "G1510", "G1610", "G1611", "G1711",
                        "G1812", "G1912", "G1913", "G2013", "G2014", "G2114", "G2115", "G2215", "G2216", "G2316", "G2317",
                        "G2418", "G2419", "G2519", "G2520", "G2620", "G2621", "G2721", "G2722", "G2822", "G2823", "G2923",
                        "G3024", "G3124", "G3125", "G3225", "G3226", "G3326", "G3327", "G3427", "G3428", "G3528", "G3529",
                        "G3630", "G3631", "G3731", "G3732", "G3832", "G3833", "G3933", "G3934", "G4034", "G4035", "G4135",
                        "G4236", "G4336", "G4337", "G4437", "G4438", "G4538", "G4539", "G4639", "G4640", "G4740", "G4741",
                        "G4842", "G4843", "G4943", "G4944", "G5044", "G5045", "G5145", "G5146", "G5246", "G5247", "G5347",
                        "G5448", "G5548", "G5549", "G5649", "G5650", "G5750", "G5751", "G5851", "G5852", "G5952", "G5953",
                        "G6054", "G6055", "G6155", "G6156", "G6256", "G6257", "G6357", "G6358", "G6458", "G6459", "G6559"
                };
                numCouplers = couplers.length;

                readouts = new String[]{
                        "R01", "R02", "R03", "R04", "R05", "R06", "R07", "R08", "R09", "R10", "R11"
                };
                numReadoutAgents = readouts.length;
                amplifiers = new String[]{
                        "A01", "A02", "A03", "A04", "A05", "A06", "A07", "A08", "A09", "A10", "A11"
                };
                userAgents = ArrayHelper.concatenate(
                        qubits, couplers, readouts, amplifiers
                );

                shiroIniFile = "~\\shiro.ini";
                dataPath = "E:\\wyl\\data";
                controlGraphName = "qs66MinGraph4T1"; // qs66MinGraph4T1 qs66Graph1

                String[] maintainAgentsCS = ArrayHelper.concatenate(qubits, readouts, amplifiers, couplers);
                maintainCalGroups = new String[maintainAgentsCS.length][];
                for (int i = 0; i < maintainAgentsCS.length; i++){
                    maintainCalGroups[i] = new String[]{maintainAgentsCS[i]};
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown system["+systemId+"]");
        }
    }


}
