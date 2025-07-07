package io.schupke.clock;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import io.schupke.clock.actions.RedrawTask;
import io.schupke.clock.gui.TimePanel;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private TimePanel timePanel;
    private Timer timer;
    private ClassLoader classLoader;
    private Properties properties;

    private String fileName, configFolder;

    private File f;
    private FileOutputStream out;

    // main window dimensions
    public final String SIZE_X = "size_x";
    public final String SIZE_Y = "size_y";
    public final String POSITION_X = "position_x";
    public final String POSITION_Y = "position_y";
    public final String TIMEZONE = "timezone";

    // main window dimensions
    public final int DEFAULT_SIZE_X = 270;
    public final int DEFAULT_SIZE_Y = 100;
    public final int DEFAULT_POSITION_X = 1;
    public final int DEFAULT_POSITION_Y = 1;
    public final String DEFAULT_TIMEZONE = "UTC";

    // -----------------------------------------------------------------
    // make basic window instances
    // -----------------------------------------------------------------
    public MainWindow() {
        classLoader = this.getClass().getClassLoader();
        properties = new Properties();

        handleFiles();
        loadSettings();

        timePanel = new TimePanel(this);
        timer = new Timer();

        setListeners();
    }

    // -----------------------------------------------------------------
    // rendering data
    // -----------------------------------------------------------------
    void render() throws Exception {
        // -------------------------------------------------------------
        // basic setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Const.APP_TITLE + " " + Const.APP_VERSION);
        setResizable(false);

        setIconImage(new ImageIcon(classLoader.getResource("icon.png")).getImage());

        setSizeAndLocation();

        // -------------------------------------------------------------
        // set panels
        setLayout(new BorderLayout());
        add(timePanel, BorderLayout.CENTER);

        // keep updating current time
        timer.schedule(new RedrawTask(timePanel.getInfoBlock()), 0, 500);

        this.setVisible(true);
    }

    private void handleFiles() {
        configFolder = System.getProperty("user.home") +
                System.getProperty("file.separator") +
                Const.MAIN_FOLDER +
                System.getProperty("file.separator") +
                Const.APP_FOLDER;

        fileName = configFolder +
                System.getProperty("file.separator") +
                "clock.conf";

        // prevent non-existing folder
        f = new File(configFolder);

        if(!f.exists()) {
            f.mkdirs();
        }

        f = new File(fileName);
    }

    private void setListeners() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // update window dimensions for future
                saveWindowDimensions(getWidth(), getHeight(), getLocation());

                // save configuration
                saveSettings();

                System.exit(0);
            }
        });
    }

    private void setSizeAndLocation() {
        // check if properties exist - if one exists, other exist as well
        if(properties.containsKey(POSITION_X)) {
            setLocation(Integer.parseInt(properties.getProperty(POSITION_X)),
                    Integer.parseInt(properties.getProperty(POSITION_Y)));
        } else {
            setLocation(DEFAULT_POSITION_X, DEFAULT_POSITION_Y);
        }

        // not variable
        setSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
    }

    // sets default values in case of missing file or initial setup
    public void setDefaultConfig() {
        // main window dimensions
        properties.setProperty(
                SIZE_X, new Integer(DEFAULT_SIZE_X).toString());
        properties.setProperty(
                SIZE_Y, new Integer(DEFAULT_POSITION_Y).toString());
        properties.setProperty(
                POSITION_X, new Integer(DEFAULT_SIZE_X).toString());
        properties.setProperty(
                POSITION_Y, new Integer(DEFAULT_POSITION_Y).toString());
    }

    public void saveWindowDimensions(Integer sizeX, Integer sizeY, Point location) {
        properties.setProperty(SIZE_X, sizeX.toString());
        properties.setProperty(SIZE_Y, sizeY.toString());
        properties.setProperty(POSITION_X, Integer.toString((int)location.getX()));
        properties.setProperty(POSITION_Y, Integer.toString((int)location.getY()));
    }

    // Tries to load values. Sets defaults if the file is not available.
    public void loadSettings() {
        try {
            if(f.exists()) {
                // load from existing
                properties.load(new FileInputStream(f));
            } else {
                // create a new config. file and save defaults
                out = new FileOutputStream(f);
                setDefaultConfig();
                properties.store(out, "");
                out.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // tries to save new values into the file
    public void saveSettings() {
        try {
            // save the config. file
            out = new FileOutputStream(f);
            properties.store(out, "");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }
} 
