package br.com.dio.ui.custom.button;

import javax.swing.*;
import java.awt.*;

public class ScorePoint extends JPanel {

    private int score = 0;
    private final JLabel scoreLabel;

    public ScorePoint() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.WHITE);
        scoreLabel = new JLabel("Pontos: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);
    }

    public void addPoint() {
        score++;
        updateLabel();
    }

    public void resetPoints() {
        score = 0;
        updateLabel();
    }

    private void updateLabel() {
        scoreLabel.setText("Pontos: " + score);
    }

    public int getScore() {
        return score;
    }
}
