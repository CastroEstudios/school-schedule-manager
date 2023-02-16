package danielCastro.schoolschedule.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

    protected JPanel panel;
    protected JLabel label;

    public CustomHeaderRenderer() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        panel.setBackground(Color.BLACK);

        label = new JLabel();
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(100, 30)); // set a larger preferred size
        label.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // set a larger maximum size
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        panel.add(label);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label.setText(value.toString());
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        return panel;
    }
}
