package br.com.dio.ui.custom.panel;

import javax.swing.JPanel;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(final Dimension dimension){
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
    }

}