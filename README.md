# Clock

A Java-based digital clock utility that displays current time and date in multiple time zones. The application remembers screen position and user configuration preferences.

## Features

- **Multi-timezone Support**: Display time in PST, EST, UTC, CET, and KST
- **Date Display**: Shows current date alongside the time
- **Persistent Configuration**: Remembers window position and selected timezone

## Requirements

- Java 8 or higher
- Maven (for building from source)

## Installation

### From Source

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd clock
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

3. Run the application:
   ```bash
   java -jar target/clock-1.0.jar
   ```

### Direct Execution

If you have the JAR file, simply run:
```bash
java -jar clock-1.0.jar
```

## Supported Timezones

- **PST** (Pacific Standard Time): America/Los_Angeles
- **EST** (Eastern Standard Time): America/New_York  
- **UTC** (Coordinated Universal Time): GMT
- **CET** (Central European Time): Europe/Paris
- **KST** (Korea Standard Time): Asia/Seoul

## Configuration

The application stores configuration in:
```
~/.schupke/clock/clock.conf
```

This file contains:
- Window position (X, Y coordinates)
- Window size
- Selected timezone

## Technical Details

- **Package**: `io.schupke.clock`
- **Main Class**: `io.schupke.clock.Clock`
- **GUI Framework**: Java Swing
- **Update Frequency**: 500ms
- **Configuration**: Java Properties file

## Project Structure

```
src/main/java/io/schupke/clock/
├── Clock.java              # Main application entry point
├── MainWindow.java         # Main window and configuration management
├── Const.java              # Application constants
├── TimeZones.java          # Timezone enum definitions
├── actions/
│   └── RedrawTask.java     # Timer task for UI updates
└── gui/
    ├── TimePanel.java      # Main time display panel
    ├── InfoBlock.java      # Time and date display component
    └── SetupBlock.java     # Timezone selection component
```

## Building

To build the project:
```bash
mvn clean compile
```

To create a runnable JAR:
```bash
mvn clean package
```
