package danielCastro.schoolschedule.util;

import javax.swing.JTable;

public class LoginTableRenderer extends CustomCellRenderer {

    private final JTable jTable;
    
    public LoginTableRenderer(JTable jTable) {
        super();
        this.jTable = jTable;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        
        return !jTable.getColumnName(column).equals("profesor_nif"); 
    }
}
