package org.example.gui;

import org.example.logic.SelectionPolicy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private JFrame frame;
    private JTextField nField;
    private JTextField qField;
    private JTextField maxSimulationField;
    private JTextField minArrivalTimeField;
    private JTextField maxArrivalTimeField;
    private JTextField minServiceTimeField;
    private JTextField maxServiceTimeField;
    private JComboBox comboBox;
    private JButton btnStart;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField waitingField;
    private JTextField serviceField;
    private JTextField hourField;

    public SimulationFrame() {
        this.setTitle("Queues Management Application");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        nField = new JTextField();
        nField.setBounds(186, 91, 78, 26);
        this.getContentPane().add(nField);
        nField.setColumns(10);

        JLabel introduceLabel = new JLabel("Introduce the data:");
        introduceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        introduceLabel.setBounds(27, 44, 160, 19);
        this.getContentPane().add(introduceLabel);

        JLabel nLabel = new JLabel("number of clients = ");
        nLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nLabel.setBounds(39, 88, 137, 28);
        this.getContentPane().add(nLabel);

        JLabel qLabel = new JLabel("number of queues = ");
        qLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        qLabel.setBounds(39, 141, 151, 26);
        this.getContentPane().add(qLabel);

        qField = new JTextField();
        qField.setColumns(10);
        qField.setBounds(200, 143, 78, 26);
        this.getContentPane().add(qField);

        JLabel maxSimulationLabel = new JLabel("maximum simulation time = ");
        maxSimulationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        maxSimulationLabel.setBounds(39, 193, 193, 28);
        this.getContentPane().add(maxSimulationLabel);

        maxSimulationField = new JTextField();
        maxSimulationField.setColumns(10);
        maxSimulationField.setBounds(240, 195, 112, 28);
        this.getContentPane().add(maxSimulationField);

        JLabel minArrivalTimeLabel = new JLabel("minimum arrival time = ");
        minArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        minArrivalTimeLabel.setBounds(39, 248, 167, 26);
        this.getContentPane().add(minArrivalTimeLabel);

        JLabel maxArrivalTimeLabel = new JLabel("maximum arrival time = ");
        maxArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        maxArrivalTimeLabel.setBounds(39, 298, 167, 26);
        this.getContentPane().add(maxArrivalTimeLabel);

        minArrivalTimeField = new JTextField();
        minArrivalTimeField.setColumns(10);
        minArrivalTimeField.setBounds(216, 249, 112, 28);
        this.getContentPane().add(minArrivalTimeField);

        maxArrivalTimeField = new JTextField();
        maxArrivalTimeField.setColumns(10);
        maxArrivalTimeField.setBounds(216, 296, 112, 28);
        this.getContentPane().add(maxArrivalTimeField);

        JLabel minServiceTimeLabel = new JLabel("minimum service time = ");
        minServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        minServiceTimeLabel.setBounds(39, 339, 167, 26);
        this.getContentPane().add(minServiceTimeLabel);

        minServiceTimeField = new JTextField();
        minServiceTimeField.setColumns(10);
        minServiceTimeField.setBounds(216, 340, 112, 28);
        this.getContentPane().add(minServiceTimeField);

        JLabel maxServiceTimeLabel = new JLabel("maximum service time = ");
        maxServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        maxServiceTimeLabel.setBounds(39, 382, 171, 25);
        this.getContentPane().add(maxServiceTimeLabel);

        maxServiceTimeField = new JTextField();
        maxServiceTimeField.setColumns(10);
        maxServiceTimeField.setBounds(216, 382, 112, 28);
        this.getContentPane().add(maxServiceTimeField);

        JLabel chooseLabel = new JLabel("Choose the selection policy: ");
        chooseLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chooseLabel.setBounds(27, 436, 198, 19);
        this.getContentPane().add(chooseLabel);

        comboBox = new JComboBox<SelectionPolicy>();
        comboBox.addItem(SelectionPolicy.SHORTEST_QUEUE);
        comboBox.addItem(SelectionPolicy.SHORTEST_TIME);
        comboBox.setBounds(216, 434, 160, 26);
        this.getContentPane().add(comboBox);

        btnStart = new JButton("START");
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnStart.setBounds(147, 496, 111, 34);
        this.getContentPane().add(btnStart);

        textArea = new JTextArea();
        textArea.setBounds(453, 92, 304, 337);
        this.getContentPane().add(textArea);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(453, 92, 304, 337);
        this.getContentPane().add(scrollPane);

        JLabel seeLabel = new JLabel("The log of events:");
        seeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        seeLabel.setBounds(453, 44, 167, 19);
        this.getContentPane().add(seeLabel);

        JLabel lblWaiting = new JLabel("The average waiting time:");
        lblWaiting.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblWaiting.setBounds(453, 459, 181, 19);
        this.getContentPane().add(lblWaiting);

        JLabel lblService = new JLabel("The average service time:");
        lblService.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblService.setBounds(453, 493, 181, 19);
        this.getContentPane().add(lblService);

        JLabel lblHour = new JLabel("The peak hour:");
        lblHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHour.setBounds(453, 523, 120, 19);
        this.getContentPane().add(lblHour);

        waitingField = new JTextField();
        waitingField.setBounds(637, 459, 90, 19);
        this.getContentPane().add(waitingField);
        waitingField.setColumns(10);

        serviceField = new JTextField();
        serviceField.setColumns(10);
        serviceField.setBounds(637, 496, 90, 19);
        this.getContentPane().add(serviceField);

        hourField = new JTextField();
        hourField.setColumns(10);
        hourField.setBounds(562, 524, 62, 19);
        this.getContentPane().add(hourField);

        this.setVisible(true);
    }

    public int getNField() {
        return Integer.parseInt(nField.getText());
    }

    public void setNField(int nField) {
        this.nField.setText(String.valueOf(nField));
    }

    public int getQField() {
        return Integer.parseInt(qField.getText());
    }

    public void setQField(int qField) {
        this.qField.setText(String.valueOf(qField));
    }

    public int getMaxSimulationField() {
        return Integer.parseInt(maxSimulationField.getText());
    }

    public void setMaxSimulationField(int maxSimulationField) {
        this.maxSimulationField.setText(String.valueOf(maxSimulationField));
    }

    public int getMinArrivalTimeField() {
        return Integer.parseInt(minArrivalTimeField.getText());
    }

    public void setMinArrivalTimeField(int minArrivalTimeField) {
        this.minArrivalTimeField.setText(String.valueOf(minArrivalTimeField));
    }

    public int getMaxArrivalTimeField() {
        return Integer.parseInt(maxArrivalTimeField.getText());
    }

    public void setMaxArrivalTimeField(int maxArrivalTimeField) {
        this.maxArrivalTimeField.setText(String.valueOf(maxArrivalTimeField));
    }

    public int getMinServiceTimeField() {
        return Integer.parseInt(minServiceTimeField.getText());
    }

    public void setMinServiceTimeField(int minServiceTimeField) {
        this.minServiceTimeField.setText(String.valueOf(minServiceTimeField));
    }

    public int getMaxServiceTimeField() {
        return Integer.parseInt(maxServiceTimeField.getText());
    }

    public void setMaxServiceTimeField(int maxServiceTimeField) {
        this.maxServiceTimeField.setText(String.valueOf(maxServiceTimeField));
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public String getTextArea() {
        return textArea.getText();
    }

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public double getWaitingField() {
        return Double.parseDouble(waitingField.getText());
    }

    public void setWaitingField(double waitingField) {
        this.waitingField.setText(String.valueOf(waitingField));
    }

    public double getServiceField() {
        return Double.parseDouble(serviceField.getText());
    }

    public void setServiceField(double serviceField) {
        this.serviceField.setText(String.valueOf(serviceField));
    }

    public int getHourField() {
        return Integer.parseInt(hourField.getText());
    }

    public void setHourField(int hourField) {
        this.hourField.setText(String.valueOf(hourField));
    }

    public void addStartListener(ActionListener actionListener) {
        btnStart.addActionListener(actionListener);
    }
}
