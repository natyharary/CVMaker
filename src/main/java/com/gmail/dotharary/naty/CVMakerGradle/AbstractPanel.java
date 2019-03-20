package com.gmail.dotharary.naty.CVMakerGradle;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {


    private static final long serialVersionUID = 8221256658243821951L;
    protected static final Color COLOR1 = new Color(55, 170, 200);
    protected static final Color COLOR2 = new Color(200, 80, 75);

    public AbstractPanel() {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.WHITE);
    }

    public abstract String getTitle();


    public abstract String getDescription();


    protected JFrame showInFrame() {
        JFrame frame = new JFrame(this.getTitle());
        frame.getContentPane().add(this, "Center");
        frame.setDefaultCloseOperation(3);
        frame.setSize(this.getPreferredSize());
        frame.setVisible(true);
        return frame;
    }

    public String toString() {
        return this.getTitle();
    }
}
