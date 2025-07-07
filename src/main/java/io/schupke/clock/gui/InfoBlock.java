package io.schupke.clock.gui;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoBlock extends JPanel {
    private static final long serialVersionUID = 1L;

    private TimePanel timePanel;

    private JLabel mainLabel;
    private JLabel mainTime;
    private JLabel mainDate;

    public InfoBlock(TimePanel timePanel) {
        this.timePanel = timePanel;

        mainLabel = new JLabel();
        mainLabel.setFont(new Font("Serif", Font.BOLD, 14));

        mainTime = new JLabel();
        mainTime.setFont(new Font("Monospaced", Font.BOLD, 32));

        mainDate = new JLabel();
        mainDate.setFont(new Font("Monospaced", Font.BOLD, 15));

        setPositions();
        addFields();
    }

    private void setPositions() {
        mainLabel.setBounds(new Rectangle(5, 5, 199, 17));
        mainTime.setBounds(new Rectangle(5, 20, 199, 32));
        mainDate.setBounds(new Rectangle(66, 56, 120, 12));
    }

    private void addFields() {
        setLayout(null);
        add(mainLabel, null);
        add(mainTime, null);
        add(mainDate, null);
    }

    // get current time
    public String[] timeNow(String zone) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(zone));

        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String[] now = new String[2];

        now[0] = zero(hrs) + ":" + zero(min) + ":" + zero(sec);
        now[1] = year + "-" + zero(month) + "-" + zero(day);

        return now;
    }

    public String zero(int num) {
        String number = (num < 10) ? ("0" + num) : ("" + num);
        return number; // Add leading zero if needed
    }

    public void setValue() {
        mainLabel.setText(timePanel.getSelectedTimeZone() + ":");
        mainTime.setText(timeNow(timePanel.getSelectedTimeZone())[0]);
        mainDate.setText(timeNow(timePanel.getSelectedTimeZone())[1]);
    }

} 
