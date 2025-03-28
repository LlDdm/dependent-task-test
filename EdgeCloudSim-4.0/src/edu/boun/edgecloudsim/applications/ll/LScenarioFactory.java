/*
 * Title:        EdgeCloudSim - Scenario Factory
 *
 * Description:  Sample scenario factory providing the default
 *               instances of required abstract classes
 *
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 * Copyright (c) 2017, Bogazici University, Istanbul, Turkey
 */

package edu.boun.edgecloudsim.applications.ll;

import edu.boun.edgecloudsim.cloud_server.CloudServerManager;
import edu.boun.edgecloudsim.cloud_server.DefaultCloudServerManager;
import edu.boun.edgecloudsim.core.ScenarioFactory;
import edu.boun.edgecloudsim.edge_orchestrator.EdgeOrchestrator;
import edu.boun.edgecloudsim.edge_server.DefaultEdgeServerManager;
import edu.boun.edgecloudsim.edge_server.EdgeServerManager;
import edu.boun.edgecloudsim.edge_client.MobileDeviceManager;
import edu.boun.edgecloudsim.edge_client.mobile_processing_unit.DefaultMobileServerManager;
import edu.boun.edgecloudsim.edge_client.mobile_processing_unit.MobileServerManager;
import edu.boun.edgecloudsim.mobility.MobilityModel;
import edu.boun.edgecloudsim.mobility.NomadicMobility;
import edu.boun.edgecloudsim.task_generator.IdleActiveLoadGenerator;
import edu.boun.edgecloudsim.task_generator.LoadGeneratorModel;
import edu.boun.edgecloudsim.network.NetworkModel;

public class LScenarioFactory implements ScenarioFactory {
    private int numOfMobileDevice;
    private double simulationTime;
    private String orchestratorPolicy;
    private String simScenario;

    LScenarioFactory(int _numOfMobileDevice,
                          double _simulationTime,
                          String _orchestratorPolicy,
                          String _simScenario){
        orchestratorPolicy = _orchestratorPolicy;
        numOfMobileDevice = _numOfMobileDevice;
        simulationTime = _simulationTime;
        simScenario = _simScenario;
    }

    @Override
    public LoadGeneratorModel getLoadGeneratorModel() {
        return new LOfflineLoadGenerator(numOfMobileDevice, simulationTime, simScenario);
    }

    @Override
    public EdgeOrchestrator getEdgeOrchestrator() {
        return new LEdgeOrchestrator(orchestratorPolicy, simScenario);
    }

    @Override
    public MobilityModel getMobilityModel() { return new LMobilityModel(numOfMobileDevice,simulationTime);}

    @Override
    public NetworkModel getNetworkModel() {
        return new LNetworkModel(numOfMobileDevice, simScenario);
    }

    @Override
    public EdgeServerManager getEdgeServerManager() {
        return new DefaultEdgeServerManager();
    }

    @Override
    public CloudServerManager getCloudServerManager() {
        return new DefaultCloudServerManager();
    }

    @Override
    public MobileDeviceManager getMobileDeviceManager() throws Exception {
        return new LMobileDeviceManager();
    }

    @Override
    public MobileServerManager getMobileServerManager() {
        return new DefaultMobileServerManager();
    }
}

