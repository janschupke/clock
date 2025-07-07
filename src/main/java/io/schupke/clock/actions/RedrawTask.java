package io.schupke.clock.actions;

import java.util.TimerTask;

import io.schupke.clock.gui.InfoBlock;

public class RedrawTask extends TimerTask {
    private InfoBlock infoBlock;

    public RedrawTask(InfoBlock infoBlock) {
        this.infoBlock = infoBlock;
    }

    public void run() {
        infoBlock.setValue();
        infoBlock.repaint();
    }

} 
