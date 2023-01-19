/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package danielCastro.schoolschedule.gui;

import com.formdev.flatlaf.FlatLightLaf;
import danielCastro.schoolschedule.dao.Especialidad;
import danielCastro.schoolschedule.util.CustomTableHeader;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anima
 */
public class PantallaCRUD extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    List<Class> listaClases = new ArrayList();
    Map<Class, List> arrayLists = new HashMap<>();

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaCRUD() {
        initComponents();
        this.setLocationRelativeTo(null);
        styles();
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\BG4.png",
                 PantallaLogIn.labelIntoJPanel(jPanel1));
        classesToArrayList();
        createArrayLists();
        comboListener();
    }

    private void styles() {
        jTable.getTableHeader().setDefaultRenderer(new CustomTableHeader());
        jTable.setDefaultRenderer(Object.class, new CustomTableHeader());
        jPanelMenu.setBackground(Color.decode("#dde5b6"));
        jPanelTable.setBackground(Color.decode("#dde5b6"));
    }

    private void classesToArrayList() {
        // package name
        String packageName = "danielCastro.schoolschedule.dao";

        // Get the package URL
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageURL = classLoader.getResource(packageName.replace(".", "/"));

        //Get all files in the package
        File packageDir = new File(packageURL.getFile());
        File[] files = packageDir.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> clazz = Class.forName(className);
                    listaClases.add(clazz);
                    jComboTabla.addItem(clazz.getSimpleName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PantallaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void createArrayLists() {
        for (Class clazz : listaClases) {
            List arrayList = new ArrayList();
            arrayLists.put(clazz, arrayList);
        }
        extractDBData();
        for (Class clazz : listaClases) {
            if (clazz.getSimpleName().equals(jComboTabla.getSelectedItem())) {
                updateTable(clazz, false);
                break;
            }
        }
    }

    private void extractDBData() {
        for (Class clazz : listaClases) {
            EntityManager em = PantallaLogIn.emf.createEntityManager();
            String nombreClase = clazz.getSimpleName();
            String query = "SELECT p FROM " + nombreClase + " p";
            TypedQuery tq = em.createQuery(query, clazz);
            List<Object> listaClase;
            try {
                listaClase = tq.getResultList();
                arrayLists.get(clazz).addAll(listaClase);
            } catch (NoResultException nrex) {
                System.out.println("No se ha encontrado ningún resultado en la BD.");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                em.close();
            }
        }
    }

    private void comboListener() {
        jComboTabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = jComboTabla.getSelectedItem();
                for (Class clazz : listaClases) {
                    if (clazz.getSimpleName().equals(selectedItem)) {
                        updateTable(clazz, true);
                        break;
                    }
                }
            }
        });
    }

    private void updateTable(Class clazz, Boolean guardar) {
        if (guardar) {
            guardarValores(clazz);
        }
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        Object[] listaAtt = getAttributeNames(clazz).toArray();
        dtm.setColumnIdentifiers(listaAtt);
        jTable.setModel(dtm);
        List listaClase = arrayLists.get(clazz);
        for (Object object : listaClase) {
            //Cogo los atributos de cada objeto en listaClase
            Field[] arrayAttObj = object.getClass().getDeclaredFields();
            Object[] values = new Object[arrayAttObj.length];
            int i = 0;
            //Por cada campo, accedo al valor, lo guardo en values y los añado
            //como fila en la tabla
            for (Field field : arrayAttObj) {
                field.setAccessible(true);
                try {
                    values[i] = field.get(object);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(PantallaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(PantallaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
                i++;
                field.setAccessible(false);
            }
            dtm.addRow(values);
        }
    }
    
    //Le estoy pasando la clase a la que cambio, no a la que quiero guardar
    //Además, estoy guardando los datos uno por uno, debería crear un objeto con los datos
    //Debería hacer un valor global ultimaClase que almacene la ultima clase
    //Y debería pasarle ese valor por parámetros a la función guardarValores.
    private void guardarValores(Class clazz) {
        for (int i = 0; i < jTable.getRowCount(); i++) {
            ArrayList<Object> rowData = new ArrayList<>();
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                rowData.add(jTable.getValueAt(i, j));
            }
            arrayLists.get(clazz).addAll(rowData);
        }
    }

    public static List<String> getAttributeNames(Class clazz) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        botonCreate = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboTabla = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMenu.setOpaque(false);

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        botonCreate.setText("Create");
        botonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCreateActionPerformed(evt);
            }
        });

        jButton5.setText("CANCEL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("SAVE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField1.setToolTipText("Filtrar");

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanelTable.setOpaque(false);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTableLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 550, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 100, Short.MAX_VALUE)
                    .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        PantallaLogIn pli = new PantallaLogIn();
        this.dispose();
        pli.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void botonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCreateActionPerformed
        // TODO add your handling code here:
        //Comprueba la clase seleccionada en el comboBox y la guarda
        Object selectedItem = jComboTabla.getSelectedItem();

        for (Class clazz : listaClases) {
            if (clazz.getSimpleName().equals(selectedItem))
                try {
                /*
                    It uses the getDeclaredConstructor() method of the Class object to 
                    obtain the default constructor of the class, and the newInstance() 
                    method of the Constructor object to create a new instance of the class
                 */
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object emptyRow = constructor.newInstance();
                /*
                    It gets all the fields of the class, and store them in an array of objects
                    in order to be able to create the empty object of the selected class.
                 */
                Field[] fields = clazz.getDeclaredFields();
                Object[] rowData = new Object[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    rowData[i] = fields[i].get(emptyRow);
                }
                dtm.addRow(rowData);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_botonCreateActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int filaSeleccion = jTable.getSelectedRow();
        if (filaSeleccion != -1) {
            dtm.removeRow(filaSeleccion);
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada."
                    + " Seleccione una fila para eliminarla.",
                     "Error de selección", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaCRUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCreate;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboTabla;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
