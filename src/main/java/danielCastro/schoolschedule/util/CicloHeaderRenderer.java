/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author 2dama
 */
public class CicloHeaderRenderer extends CustomHeaderRenderer {

    private final JButton button;

    public CicloHeaderRenderer() {
        super();
        button = new JButton("Button");
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setBackground(Color.decode("#F5F5F5"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.getColumnName(column).equals("nifProfesor")) {
            panel.add(button);
            panel.add(Box.createHorizontalGlue()); // push the button to the right
        }

        return panel;
    }
}
