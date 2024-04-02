// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton displayAllEntries = new JButton("Find all");
    private JButton lookUpAllByDate = new JButton("Look up all by date");
    private JRadioButton sprintSelector = new JRadioButton("Sprinter");
    private JRadioButton swimSelector = new JRadioButton("Swimmer");
    private JRadioButton cycleSelector = new JRadioButton("Cyclist");
    private ButtonGroup radioGroups = new ButtonGroup();
    private JTextField terrain = new JTextField(30);
    private JTextField tempo = new JTextField(30);
    private JTextField where = new JTextField(30);
    private JTextField repetitions = new JTextField(2);
    private JTextField recovery = new JTextField(2);
    private JLabel terLabel = new JLabel(" Terrain:");
    private JLabel tempoLabel = new JLabel(" Tempo:");
    private JLabel whereLabel = new JLabel(" Where:");
    private JLabel repetitionLabel = new JLabel(" Repetitions:");
    private JLabel recoveryLabel = new JLabel(" Recovery:");
    private JButton deleteRecord = new JButton("Delete Record");




    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(outputArea);
        displayAllEntries.addActionListener(this);
        add(displayAllEntries);
        lookUpAllByDate.addActionListener(this);
        add(lookUpAllByDate);
        deleteRecord.addActionListener(this);
        add(deleteRecord);

        radioGroups.add(sprintSelector);
        radioGroups.add(swimSelector);
        radioGroups.add(cycleSelector);

        cycleSelector.addActionListener(this);
        sprintSelector.addActionListener(this);
        swimSelector.addActionListener(this);
        add(cycleSelector);
        add(swimSelector);
        add(sprintSelector);

        add(tempoLabel);
        add(tempo);
        tempo.setEditable(true);
        tempo.setVisible(false);
        tempoLabel.setVisible(false);
        add(terLabel);
        add(terrain);
        terrain.setEditable(true);
        terrain.setVisible(false);
        terLabel.setVisible(false);
        add(recoveryLabel);
        add(recovery);
        recovery.setEditable(true);
        recovery.setVisible(false);
        recoveryLabel.setVisible(false);
        add(repetitionLabel);
        add(repetitions);
        repetitions.setEditable(true);
        repetitions.setVisible(false);
        repetitionLabel.setVisible(false);
        add(whereLabel);
        add(where);
        where.setEditable(true);
        where.setVisible(false);
        whereLabel.setVisible(false);


        outputArea.setEditable(false);
        setSize(1100, 450);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        // clear text field initially
        String message = "";
        outputArea.setText(message);
        boolean typeSelected = false;

        // just to ensure elements dont clear if nothing happens, for example if a type is selected with data input.
        // this could potentially be linked to type selected, and invert the if condition, although could cause errors
        // I dont want a headache right now necessarily.
        boolean shouldClear = true;

        if (swimSelector.isSelected() || sprintSelector.isSelected() || cycleSelector.isSelected()) {
            typeSelected = true;
            shouldClear = false;
        }

        if (event.getSource() == deleteRecord) {
            message = removeEntry();
        }


        if (event.getSource() == lookUpByDate || event.getSource() == lookUpAllByDate) {
            message = lookupEntry();
        }

        if (event.getSource() == displayAllEntries) {

            // could be moved into its own method to print all arrays.
            // this could be a set block of text rather than an append?
            String[] messageArr = displayAllEntries();

            if (messageArr.length <= 1) {
                outputArea.setText(messageArr[0]);
            }
            else {
                for (String s : messageArr) {
                    outputArea.append(s);
                }
            }
        }
        // not sure there's a way to break out of the action listener without stopping everything.
        selectorShort(swimSelector, where, whereLabel);
        selector(sprintSelector, repetitions, repetitionLabel, recovery, recoveryLabel);
        selector(cycleSelector, terrain, terLabel, tempo, tempoLabel);

        if (event.getSource() == addR && !typeSelected) {
            message = addEntry("generic");
        } else if (event.getSource() == addR && typeSelected) {
            if (sprintSelector.isSelected()) {
                message = addEntry("sprinter");
                shouldClear = true;
            }
            if (swimSelector.isSelected()) {
                message = addEntry("swimmer");
                shouldClear = true;
            }
            if (cycleSelector.isSelected()) {
                message = addEntry("cyclist");
                shouldClear = true;
            }
        }

        if (!message.isEmpty()) {
            outputArea.setText(message);
        }
        if (shouldClear) {
            blankDisplay();
        }
        repaint();

    } // actionPerformed

    private void selector(JRadioButton selectedElement,
                  JTextField textField1,
                  JLabel label1,
                  JTextField textField2,
                  JLabel label2)
    {
        if (selectedElement.isSelected()) {
            textField1.setVisible(true);
            label1.setVisible(true);
            textField2.setVisible(true);
            label2.setVisible(true);
        } else if (!selectedElement.isSelected()) {
            textField1.setVisible(false);
            label1.setVisible(false);
            textField2.setVisible(false);
            label2.setVisible(false);
        }
    }
    // null checking and passing null would allow one method for both, but I want easy solution rather than elegance.
    private void selectorShort(JRadioButton selectedElement,
                           JTextField textField1,
                           JLabel label1)
    {
        if (selectedElement.isSelected()) {
            textField1.setVisible(true);
            label1.setVisible(true);
        } else if (!selectedElement.isSelected()) {
            textField1.setVisible(false);
            label1.setVisible(false);
        }
    }

    public String addEntry(String what) {
        String message = "";
        System.out.println("Attempting to add "+what+" entry to the records");
        try {
            String n;
            if (!name.getText().isEmpty()) {
                n = name.getText();
            } else {
                return "Please insert a valid name.";
            }

            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            float km = java.lang.Float.parseFloat(dist.getText());
            int h = Integer.parseInt(hours.getText());
            int mm = Integer.parseInt(mins.getText());
            int s = Integer.parseInt(secs.getText());

            // initial entry.
            Entry e = null;

            if (what.equals("generic")) {
                e = new Entry(n, d, m, y, h, mm, s, km);
            }
            if (what.equals("cyclist")) {
                // I am opting to ensure that fields are typed in for the required elements.
                String terrain = this.terrain.getText();
                String tempo = this.tempo.getText();
                e = new CycleEntry(n,d,m,y,h,mm,s,km,terrain,tempo);
            }
            if (what.equals("swimmer")) {
                String where = this.where.getText();
                e = new SwimEntry(n,d,m,y,h,mm,s,km,where);
            }
            if (what.equals("sprinter")) {
                int reps = Integer.parseInt(this.repetitions.getText());
                int recovery = Integer.parseInt(this.recovery.getText());
                e = new SprintEntry(n,d,m,y,h,mm,s,km,reps,recovery);
            }

            // no need to null check afaik because we're guaranteeing values are filled in try-catch.
            message = myAthletes.addEntry(e);;
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input provided. Please enter only appropriate values in fields.");
        }

        return message;
    }

    public String removeEntry() {
        try {
            String n = "";
            if (!name.getText().isEmpty()) {
                n = name.getText();
            } else {
                return "Please insert a valid name.";
            }

            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());

            return myAthletes.deleteEntry(d,m,y,n);

        } catch (NumberFormatException e) {
            return "Invalid input provided. Please enter only appropriate values in fields.";
        }
    }
    
    public String lookupEntry() {
        String message = "";
        if (month.getText().isEmpty() || day.getText().isEmpty() || year.getText().isEmpty()) {
            outputArea.setText("Invalid Date provided, please ensure all fields are not empty.");
        }

        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("looking up record ...");
            message = myAthletes.lookupEntry(d, m, y);
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input provided. Please enter only appropriate values in fields.");
        }

        return message;
    }

    public String[] displayAllEntries() {

        if (myAthletes.displayAllElements() == null) {
            return new String[] {"No entries found"};
        }

        return myAthletes.displayAllElements();
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        tempo.setText("");
        terrain.setText("");
        where.setText("");
        repetitions.setText("");
        recovery.setText("");
    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

