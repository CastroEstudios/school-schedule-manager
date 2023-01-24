/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package danielCastro.schoolschedule.gui;

import com.formdev.flatlaf.FlatLightLaf;
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
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anima
 */
public class PantallaCRUD extends javax.swing.JFrame {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
    DefaultTableModel dtm = new DefaultTableModel();
    List<Class> listaClases = new ArrayList();
    Class ultimaClase;
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
        //Applies the CustomTableHeader class to the Header and the rest of the table
        jTable.getTableHeader().setDefaultRenderer(new CustomTableHeader());
        jTable.setDefaultRenderer(Object.class, new CustomTableHeader());
        //Background colors
        jPanelMenu.setBackground(Color.decode("#dde5b6"));
        jPanelTable.setBackground(Color.decode("#dde5b6"));
    }

    //Method that stores all the classes in a list for its use.
    private void classesToArrayList() {
        // package name with all the classes
        String packageName = "danielCastro.schoolschedule.dao";
        // Get the package URL
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageURL = classLoader.getResource(packageName.replace(".", "/"));

        //Get all files in the package
        File packageDir = new File(packageURL.getFile());
        File[] files = packageDir.listFiles();
        //Goes over all the class files in the package
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                //Gets the class name, getting rid of the .class extension
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                try {
                    //Creates the class and adds it to the list
                    Class<?> clazz = Class.forName(className);
                    listaClases.add(clazz);
                    jComboTabla.addItem(clazz.getSimpleName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PantallaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //Method that creates a list for each class to store the DB info
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

    //Method that extracts all the info of the database and stores it into its
    //corresponding list.
    private void extractDBData() {
        //Selects everything for each class and stores it.
        for (Class clazz : listaClases) {
            EntityManager em = emf.createEntityManager();
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
    
    //Method that updates the info displaying on the table
    private void updateTable(Class clazz, Boolean guardar) {
        //Method that checks if we are changing the current table selected 
        //on the comboBox and saves the changes made to that table on its
        //corresponding list
        if (guardar) {
            guardarValores(ultimaClase);
        }
        //Clears the table
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        //Sets the header values depending on the class on the parametres
        Object[] listaAtt = getAttributeNames(clazz).toArray();
        dtm.setColumnIdentifiers(listaAtt);
        //Sets the table model to DefaultTableModel and gets the list that 
        //has the info of the class and its values.
        jTable.setModel(dtm);
        List listaClase = arrayLists.get(clazz);
        //Iterates over all the values in the list in order to display them
        //on the table
        for (Object object : listaClase) {
            //I grab all the attributes of the selected Class clazz so I can 
            //iterate through all of them and display them
            Field[] arrayAttObj = object.getClass().getDeclaredFields();
            Object[] values = new Object[arrayAttObj.length];
            int i = 0;
            //For each field, I get the value, store it in the 'values' array
            //and add them as a row in the table
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
        //I set the 'ultimaClase' variable to the class selected by the user
        ultimaClase = clazz;
    }

    //Method that updates the table when the comboBox value that has the 
    //database tables is changed
    private void comboListener() {
        jComboTabla.addActionListener(new ActionListener() {
            //Gets the selected item and compares it to the classes in 'listaClases'
            //until it finds the right one
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

    //Method that checks if we are changing the current table selected 
    //on the comboBox and saves the changes made to that table on its
    //corresponding list
    private void guardarValores(Class clazz) {
        // Get the list corresponding to the 'clazz' key and clears it
        List arrayList = arrayLists.get(clazz);
        arrayList.clear();
        // Get the number of rows in the table
        int rowCount = jTable.getRowCount();
        // Go over all the rows in the table
        for (int i = 0; i < rowCount; i++) {
            // Create a new instance of the ultimaClase class
            Object instance = null;
            try {
                //Creates a new constructor of the class 'clazz' and sets the
                //Object 'instance' to use that constructor
                Constructor constructor = clazz.getConstructor();
                instance = constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException 
                    | IllegalArgumentException | InvocationTargetException 
                    | NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(PantallaCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Get the number of columns in the table
            int columnCount = jTable.getColumnCount();
            // Go over all the columns in the table
            for (int j = 0; j < columnCount; j++) {
                String columnName = jTable.getColumnName(j);
                // Get the value of the cell in the current row and column
                Object value = jTable.getValueAt(i, j);
                try {
                    // Get the field of the class corresponding to the column name
                    Field field = clazz.getDeclaredField(columnName);
                    // Make the field accessible
                    field.setAccessible(true);
                    //Casts the value to the correct data type
                    if (field.getType() == int.class && value instanceof String) {
                        value = Integer.valueOf((String) value);
                    }
                    if (field.getType() == boolean.class && value instanceof String) {
                        value = Boolean.valueOf((String) value);
                    }
                    // Set the value of the field in the instance Object
                    field.set(instance, value);
                    field.setAccessible(false);
                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(PantallaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // Add the instance Object to the corresponding list in the arrayLists map
            arrayList.add(instance);
        }
    }

    //Gets the names of the attributes for the passed class
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
        botonDelete = new javax.swing.JButton();
        botonCreate = new javax.swing.JButton();
        botonCancel = new javax.swing.JButton();
        botonSave = new javax.swing.JButton();
        jComboTabla = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMenu.setOpaque(false);

        botonDelete.setText("Delete");
        botonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDeleteActionPerformed(evt);
            }
        });

        botonCreate.setText("Create");
        botonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCreateActionPerformed(evt);
            }
        });

        botonCancel.setText("CANCEL");
        botonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelActionPerformed(evt);
            }
        });

        botonSave.setText("SAVE");
        botonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSaveActionPerformed(evt);
            }
        });

        jTextField1.setToolTipText("Filtrar");

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(botonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void botonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelActionPerformed
        // TODO add your handling code here:
        PantallaLogIn pli = new PantallaLogIn();
        this.dispose();
        pli.setVisible(true);
    }//GEN-LAST:event_botonCancelActionPerformed

    private void botonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSaveActionPerformed
        // TODO add your handling code here:
        guardarValores(ultimaClase);
        
        for(Map.Entry<Class, List> entry: arrayLists.entrySet()) {
            EntityManager em = emf.createEntityManager();
            Class key = entry.getKey();
            List listaActual = entry.getValue();
            
            for (int i = 0; i < listaActual.size(); i++) {
                Object appObj = listaActual.get(i);
                Object idApp = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(appObj);
                Object objetoDB = em.find(key, idApp);
                em.getTransaction().begin();
                objetoDB = em.merge(appObj);
                em.flush();
                em.getTransaction().commit();
            }
            
            String nombreClase = key.getSimpleName();
            String query = "SELECT p FROM " + nombreClase + " p";
            TypedQuery tq = em.createQuery(query, key);
            List<Object> listaClase = null;
            try {
                listaClase = tq.getResultList();
            } catch (NoResultException nrex) {
                System.out.println("No se ha encontrado ningún resultado en la BD.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            for (Object object : listaClase) {
                boolean isInDB = false;
                Object idObject = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(object);
                for (Object objectActual : listaActual) {
                    Object idObjectActual = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(objectActual);
                    if(idObject == idObjectActual) {
                        isInDB = true;
                        break;
                    }
                }
                if (!isInDB) {
                    em.getTransaction().begin();
                    em.remove(object);
                    em.getTransaction().commit();
                }
            }
            em.close();
        }
    }//GEN-LAST:event_botonSaveActionPerformed

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

    private void botonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDeleteActionPerformed
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
    }//GEN-LAST:event_botonDeleteActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new PantallaCRUD().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancel;
    private javax.swing.JButton botonCreate;
    private javax.swing.JButton botonDelete;
    private javax.swing.JButton botonSave;
    private javax.swing.JComboBox<String> jComboTabla;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
