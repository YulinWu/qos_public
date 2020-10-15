package qc.qos.testclient;

import qc.qos.rpcapi.qpx1701.QOSServerApi;
import qc.qos.rpc.Constants;

/**
 * Copyright (c) 2019 onward, Yulin Wu. All rights reserved.
 * <p>
 * mail4ywu@gmail.com/mail4ywu@icloud.com
 * University of Science and Technology of China
 */
public class Service {
    private static void callService(String options) {
        QOSServerApi serverApi = null;
        try {
            serverApi = new QOSServerApi(Config.experimentUserName, Config.experimentUserPassword, Config.qosServerHost,
                    Constants.DEFAULT_PORT);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        switch (options){
            case "start":
            case "boot up":
                System.out.println("======== Start boot up ========");
                serverApi.bootup(Config.settingsStoreUserName, Config.settingsStorePassword, Config.configServerName,
                        Config.labradManagerHost, Config.labradManagerPort, Config.labradPassword,
                        Config.shiroIniFile, Config.systemId, Config.sampleId);
                break;
            case "shut down":
            case "stop":
                serverApi.shutdown("tokenNotNeededForNow");
                break;
            case "getQPUInfo": // tested ok
                serverApi.getQPUInfo();
                break;
            case "restartHardware": // tested ok
                serverApi.restartHardware(new String[]{"DAF013"}); // empty array to restart all
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
