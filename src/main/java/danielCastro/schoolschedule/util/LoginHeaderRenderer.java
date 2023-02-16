package danielCastro.schoolschedule.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;

public class LoginHeaderRenderer extends CustomHeaderRenderer {

    private final JButton button;

    public LoginHeaderRenderer() {
        super();
        button = new JButton("Button");
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setBackground(Color.decode("#F5F5F5"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.getColumnName(column).equals("profesor_nif")) {
            panel.add(button);
        }

        return panel;
    }
}
