package io.schupke.clock.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import io.schupke.clock.TimeZones;

public class SetupBlock extends JPanel {
    private static final long serialVersionUID = 1L;

    private TimePanel timePanel;
    private JComboBox<String> zoneSwitch;
    private String[] availableZones = {"PST", "EST", "UTC", "CET", "KST"};

    public SetupBlock(TimePanel timePanel) {
        this.timePanel = timePanel;

        zoneSwitch = new JComboBox<String>(availableZones);

        if(timePanel.getMainWindow().getProperties().containsKey(timePanel.getMainWindow().TIMEZONE)) {
            zoneSwitch.setSelectedItem(timePanel.getMainWindow().getProperties().getProperty(timePanel.getMainWindow().TIMEZONE));
        } else {
            zoneSwitch.setSelectedItem(timePanel.getMainWindow().DEFAULT_TIMEZONE);
        }

        setLayout(new BorderLayout());
        add(zoneSwitch, BorderLayout.CENTER);

        addListeners();
    }

    void addListeners() {
        zoneSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timePanel.setSelectedTimeZone(TimeZones.valueOf((String)zoneSwitch.getSelectedItem()));

                timePanel.getMainWindow().getProperties().setProperty(
                        timePanel.getMainWindow().TIMEZONE, (String)zoneSwitch.getSelectedItem());
            }
        });
    }

} 
