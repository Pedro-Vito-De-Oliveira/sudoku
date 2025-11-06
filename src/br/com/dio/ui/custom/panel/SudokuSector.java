package br.com.dio.ui.custom.panel;

import br.com.dio.ui.custom.input.NumberText;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class SudokuSector extends JPanel {

    public SudokuSector(final List<NumberText> fields) {

        this.setLayout(new GridLayout(3, 3, 2, 2));


        this.setBackground(Color.WHITE);


        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        this.setBorder(border);


        for (NumberText field : fields) {

            field.setBackground(Color.WHITE);
            this.add(field);
        }


        this.setVisible(true);
    }
}
