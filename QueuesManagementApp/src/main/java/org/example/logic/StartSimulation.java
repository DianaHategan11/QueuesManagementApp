package org.example.logic;

import org.example.gui.SimulationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartSimulation {
    private SimulationFrame frame;

    public StartSimulation(SimulationFrame simulationFrame) {
        this.frame = simulationFrame;
        this.frame.addStartListener(new StartListener());
    }

    public class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int nbOfClients = frame.getNField();
            int nbOfQueues = frame.getQField();
            int maxSimulationTime = frame.getMaxSimulationField();
            int minArrivalTime = frame.getMinArrivalTimeField();
            int maxArrivalTime = frame.getMaxArrivalTimeField();
            int minServiceTime = frame.getMinServiceTimeField();
            int maxServiceTime = frame.getMaxServiceTimeField();
            SelectionPolicy selectionPolicy = (SelectionPolicy) frame.getComboBox().getSelectedItem();
            SimulationManager gen = new SimulationManager(nbOfClients, nbOfQueues, maxSimulationTime,
                    minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, selectionPolicy,
                    frame, StartSimulation.this);
            Thread t = new Thread(gen);
            t.start();
        }
    }
}
