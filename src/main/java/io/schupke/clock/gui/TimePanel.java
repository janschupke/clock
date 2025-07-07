package io.schupke.clock.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import io.schupke.clock.MainWindow;
import io.schupke.clock.TimeZones;

public class TimePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private MainWindow mainWindow;

    private InfoBlock infoBlock;
    private SetupBlock setupBlock;

    private String selectedTimeZone;

    public TimePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        // default
        setSelectedTimeZone(TimeZones.UTC);

        infoBlock = new InfoBlock(this);
        setupBlock = new SetupBlock(this);

        setLayout(new BorderLayout());
        add(infoBlock, BorderLayout.CENTER);
        add(setupBlock, BorderLayout.EAST);
    }

    public String getSelectedTimeZone() {
        return selectedTimeZone;
    }
    public void setSelectedTimeZone(TimeZones timeZones) {
        selectedTimeZone = timeZones.toString();
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public InfoBlock getInfoBlock() {
        return infoBlock;
    }
} 
