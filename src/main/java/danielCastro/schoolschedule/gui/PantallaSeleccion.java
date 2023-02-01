/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package danielCastro.schoolschedule.gui;

import com.formdev.flatlaf.FlatLightLaf;
import danielCastro.schoolschedule.util.CustomTableHeader;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anima
 */
public class PantallaSeleccion extends javax.swing.JFrame {
    //QUITAR EL AUTO_INCREMENT
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
    DefaultTableModel dtm = new DefaultTableModel();
    List<String> listaTurnos = new ArrayList();
    LinkedHashMap<String, List> arrayLists = new LinkedHashMap<>();

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaSeleccion() {
        initComponents();
        this.setLocationRelativeTo(null);
        styles();
        //classesToArrayList();
        fixedClassesToArrayList();
        createArrayLists();
        comboListener();
    }

    private void styles() {
        //Applies the CustomTableHeader class to the Header and the rest of the table
        jTable.getTableHeader().setDefaultRenderer(new CustomTableHeader());
        jTable.setDefaultRenderer(Object.class, new CustomTableHeader());
        jTableSelect.getTableHeader().setDefaultRenderer(new CustomTableHeader());
        jTableSelect.setDefaultRenderer(Object.class, new CustomTableHeader());
        //Set background colors
        jPanelMenu.setBackground(Color.decode("#dde5b6"));
        jPanelTable.setBackground(Color.decode("#dde5b6"));
        //Makes the primary key rows uneditable
        //Sets the images
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\BG4.png"
                , PantallaLogIn.labelIntoJPanel(jPanel1));
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\saveButton.png"
                , botonSave);
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\cancelBoton.png"
                , botonCancel);
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\addBoton.png"
                , botonCreate);
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\deleteBoton.png"
                , botonDelete);
    }

    private Object objectInstance(Class clazz) {
        Object instance = null;
        try {
            //Creates a new constructor of the class 'clazz' and sets the
            //Object 'instance' to use that constructor
            Constructor constructor = clazz.getConstructor();
            instance = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(PantallaSeleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }
    
    private void fixedClassesToArrayList() {
        listaTurnos.add(0, "Manana");
        listaTurnos.add(1, "Tarde");
        listaTurnos.add(2, "Noche");
        jComboTabla.addItem("Manana");
        jComboTabla.addItem("Tarde");
        jComboTabla.addItem("Noche");
    }

    //Method that creates a list for each class to store the DB info
    private void createArrayLists() {
        //Fills the map with empty ArrayLists
        for (String str : listaTurnos) {
            List arrayList = new ArrayList();
            arrayLists.put(str, arrayList);
        }
        //Populates the ArrayLists with the data from the DB
        for (String str : listaTurnos) {
            arrayLists.get(str).addAll(extractDBData(str));
        }
        //Initializes the table to display a class info from the start of the app
        for (String str : listaTurnos) {
            if (str.equals(jComboTabla.getSelectedItem())) {
                updateTable(str);
                break;
            }
        }
    }

    //Method that extracts all the info of the database and stores it into its
    //corresponding list.
    private List extractDBData(String str) {
        //Selects everything for each class and stores it in listaClase.
        List<Object> listaClase = new ArrayList();
        EntityManager em = emf.createEntityManager();
        String query = """
                       SELECT 
                       m.nombre,
                       cu.idCurso,
                       cu.turno,
                       m.horasReales
                       FROM danielCastro.schoolschedule.dao.Curso cu
                       JOIN danielCastro.schoolschedule.dao.Modulo_Curso mc
                       ON cu.idCurso = mc.idCurso 
                       JOIN danielCastro.schoolschedule.dao.Modulo m
                       ON mc.idModulo = m.idModulo
                       WHERE cu.turno = :turnito
                       """;
        Query tq = em.createQuery(query);
        tq.setParameter("turnito", str);
        try {
            listaClase = tq.getResultList();
        } catch (NoResultException nrex) {
            System.out.println("No se ha encontrado ningún resultado en la BD.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return listaClase;
    }

    //Method that updates the info displaying on the table
    private void updateTable(String str) {
        //Clears the table
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        //Sets the header values depending on the class on the parametres
        Object[] listaAtt = new Object[] {"Módulo", "Curso", "Turno", "Horas"};
        dtm.setColumnIdentifiers(listaAtt);
        //Sets the table model to DefaultTableModel and gets the list that 
        //has the info of the class and its values.
        jTable.setModel(dtm);
        List listaClase = arrayLists.get(str);
        //Iterates over all the values in the list in order to display them
        //on the table
        for (Object object : listaClase) {
            Object[] listaObjetos = (Object []) object;
            Object[] listaAgnadirATabla = new Object[listaObjetos.length];
            int i = 0;
            for (Object objeto : listaObjetos) {
                listaAgnadirATabla[i] = objeto.toString();
                i++;
            }
            if(listaAgnadirATabla != null) {
                dtm.addRow(listaAgnadirATabla);
            }
        }
        //I set the 'ultimaClase' variable to the class selected by the user
    }

    //Method that updates the table when the comboBox value that has the 
    //database tables is changed
    private void comboListener() {
        jComboTabla.addActionListener((ActionEvent e) -> {
            Object selectedItem = jComboTabla.getSelectedItem();
            for (String str : listaTurnos) {
                if (str.equals(selectedItem)) {
                    updateTable(str);
                    break;
                }
            }
        } //Gets the selected item and compares it to the classes in 'listaClases'
        //until it finds the right one
        );
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
        botonCancel = new javax.swing.JButton();
        botonSave = new javax.swing.JButton();
        jComboTabla = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        botonDelete = new javax.swing.JButton();
        botonCreate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSelect = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMenu.setOpaque(false);

        botonCancel.setBorderPainted(false);
        botonCancel.setContentAreaFilled(false);
        botonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelActionPerformed(evt);
            }
        });

        botonSave.setBorderPainted(false);
        botonSave.setContentAreaFilled(false);
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
                .addGap(37, 37, 37)
                .addComponent(botonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jComboTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addGap(310, 310, 310)
                .addComponent(botonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboTabla)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMenuLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(botonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        botonDelete.setBorderPainted(false);
        botonDelete.setContentAreaFilled(false);
        botonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDeleteActionPerformed(evt);
            }
        });

        botonCreate.setBorderPainted(false);
        botonCreate.setContentAreaFilled(false);
        botonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCreateActionPerformed(evt);
            }
        });

        jTableSelect.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableSelect);

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(botonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(botonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        PantallaCRUD pcrud = new PantallaCRUD();
        this.dispose();
        pcrud.setVisible(true);
    }//GEN-LAST:event_botonCancelActionPerformed

    private void botonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSaveActionPerformed
        // TODO add your handling code here:
        //Saves the values on the current table in its corresponding arrayLists list
        PantallaSeleccion ps = new PantallaSeleccion();
        this.dispose();
        ps.setVisible(true);
    }//GEN-LAST:event_botonSaveActionPerformed

    private void botonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonDeleteActionPerformed

    private void botonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCreateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCreateActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaSeleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaSeleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaSeleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaSeleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PantallaSeleccion().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTableSelect;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
