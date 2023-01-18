/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Anima
 */
public class CustomTableHeader extends DefaultTableCellRenderer{
    
   @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row == -1) {
            label.setBackground(Color.decode("#383838"));
            label.setForeground(Color.WHITE);
        } else {
            label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            label.setBorder(new CompoundBorder(label.getBorder(), new EmptyBorder(10, 10, 10, 10)));

        }


        return label;
    } 
    
}
