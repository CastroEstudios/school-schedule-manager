/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package danielCastro.schoolschedule.gui;

import com.formdev.flatlaf.FlatLightLaf;
import danielCastro.schoolschedule.dao.Curso;
import danielCastro.schoolschedule.dao.Modulo;
import danielCastro.schoolschedule.dao.Modulo_Curso;
import danielCastro.schoolschedule.dao.Modulo_Profesor;
import danielCastro.schoolschedule.util.CustomTableHeader;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author Anima
 */
public class PantallaSeleccion extends javax.swing.JFrame {
    ODB odb = ODBFactory.open(PantallaLogIn.fileDB.getName(), "root", "");
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel ptm = new DefaultTableModel();
    List<String> listaTurnos = new ArrayList();
    LinkedHashMap<String, List> arrayLists = new LinkedHashMap<>();
    List listaCamposTablaSelect = new ArrayList();
    int horasProfesor = 0;
    static String nifProfesor;

    /**
     * Creates new form PantallaPrincipal
     *
     * @param nif
     */
    public PantallaSeleccion(String nif) {
        initComponents();
        this.setLocationRelativeTo(null);
        nifProfesor = nif;
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
        jLabelHoras.setText("Horas seleccionadas: " + horasProfesor);
        //Set background colors
        jPanelMenu.setBackground(Color.decode("#dde5b6"));
        jPanelTable.setBackground(Color.decode("#dde5b6"));
        //Makes the primary key rows uneditable
        //Sets the images
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\BG8.png",
                 PantallaLogIn.labelIntoJPanel(jPanel1));
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\saveButton.png",
                 botonSave);
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\cancelBoton.png",
                 botonCancel);
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\addBoton.png",
                 botonCreate);
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\deleteBoton.png",
                 botonDelete);
        PantallaLogIn.initBGImage(
                ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\profesor.png",
                 botonModuloProfesor);
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
            arrayLists.get(str).addAll(mainData(str));
        }
        listaCamposTablaSelect.addAll(turnTableData());
        //Initializes the table to display a class info from the start of the app
        for (String str : listaTurnos) {
            if (str.equals(jComboTabla.getSelectedItem())) {
                updateTable(str);
                break;
            }
        }
        updateTableSelect();
    }

    //Method that extracts all the info of the database and stores it into its
    //corresponding list.
    private List mainData(String str) {
        //Selects everything for each class and stores it in listaClase.
        List<Object> listaTabla = new ArrayList();
        IQuery query = new CriteriaQuery(Curso.class, Where.equal("turno"
                , jComboTabla.getSelectedItem().toString()));
        Objects result = odb.getObjects(query);
        while (result.hasNext()) {
            Curso cu = (Curso) result.next();
            query = new CriteriaQuery(Modulo_Curso.class,
                        Where.equal("idCurso", cu.getIdCurso()));
            Modulo_Curso mc = (Modulo_Curso) odb.getObjects(query);
            query = new CriteriaQuery(Modulo.class,
                        Where.not(Where.equal("idModulo", mc.getIdModulo())));
            Objects<Modulo> listaModulos = odb.getObjects(query);
            for (Modulo m : listaModulos) {
                Object[] row = new Object[]{
                    m.getNombre(),
                    cu.getIdCurso(),
                    cu.getTurno(),
                    m.getHorasReales()
                };
                listaTabla.add(row);
            }
        }
        return listaTabla;
    }

    private List turnTableData() {
        //Selects everything for each class and stores it in listaClase.
        String query2 = """
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
                       JOIN danielCastro.schoolschedule.dao.Modulo_Profesor mp
                       ON cu.idCurso = mp.idCurso
                       WHERE mp.profesor_nif = :nifProfesor
                       """;
        List<Object> listaTabla = new ArrayList();
        IQuery query = new CriteriaQuery(Modulo_Profesor.class, 
                Where.equal("profesor_nif", nifProfesor));
        Objects result = odb.getObjects(query);
        while (result.hasNext()) {
            Modulo_Profesor mp = (Modulo_Profesor) result.next();
            query = new CriteriaQuery(Curso.class,
                        Where.equal("idCurso", mp.getIdCurso()));
            Curso cu = (Curso) odb.getObjects(query);
            query = new CriteriaQuery(Modulo_Profesor.class,
                        Where.equal("idCurso", cu.getIdCurso()));
            Modulo_Curso mc = (Modulo_Curso) odb.getObjects(query);
            query = new CriteriaQuery(Modulo.class,
                        Where.equal("idModulo", mc.getIdModulo()));
            Modulo m = (Modulo) odb.getObjects(query);
            Object[] row = new Object[]{
                m.getNombre(),
                cu.getIdCurso(),
                cu.getTurno(),
                m.getHorasReales()
            };
            listaTabla.add(row);
        }
        return listaTabla;
    }

    //Method that updates the info displaying on the table
    private void updateTable(String str) {
        //Clears the table
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        //Sets the header values depending on the class on the parametres
        Object[] listaAtt = new Object[]{"Módulo", "Curso", "Turno", "Horas"};
        dtm.setColumnIdentifiers(listaAtt);
        //Sets the table model to DefaultTableModel and gets the list that 
        //has the info of the class and its values.
        jTable.setModel(dtm);
        List listaClase = arrayLists.get(str);
        //Iterates over all the values in the list in order to display them
        //on the table
        for (Object object : listaClase) {
            Object[] listaObjetos = (Object[]) object;
            Object[] listaAgnadirATabla = new Object[listaObjetos.length];
            int i = 0;
            for (Object objeto : listaObjetos) {
                listaAgnadirATabla[i] = objeto.toString();
                i++;
            }
            if (listaAgnadirATabla != null) {
                dtm.addRow(listaAgnadirATabla);
            }
        }
        //I set the 'ultimaClase' variable to the class selected by the user
    }

    private void updateTableSelect() {
        //Clears the table
        ptm.setRowCount(0);
        ptm.setColumnCount(0);
        //Sets the header values depending on the class on the parametres
        Object[] listaAtt = new Object[]{"Módulo", "Curso", "Turno", "Horas"};
        ptm.setColumnIdentifiers(listaAtt);
        //Sets the table model to DefaultTableModel and gets the list that 
        //has the info of the class and its values.
        jTableSelect.setModel(ptm);
        //Iterates over all the values in the list in order to display them
        //on the table
        for (Object object : listaCamposTablaSelect) {
            Object[] listaObjetos = (Object[]) object;
            Object[] listaAgnadirATabla = new Object[listaObjetos.length];
            int i = 0;
            for (Object objeto : listaObjetos) {
                listaAgnadirATabla[i] = objeto.toString();
                i++;
            }
            System.out.println(Integer.valueOf(listaAgnadirATabla[listaObjetos.length - 1].toString()));
            horasProfesor += Integer.valueOf(listaAgnadirATabla[listaObjetos.length - 1].toString());
            if (listaAgnadirATabla != null) {
                jLabelHoras.setText("Horas seleccionadas: " + horasProfesor);
                ptm.addRow(listaAgnadirATabla);
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
        botonModuloProfesor = new javax.swing.JButton();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        botonDelete = new javax.swing.JButton();
        botonCreate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSelect = new javax.swing.JTable();
        jLabelHoras = new javax.swing.JLabel();

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

        botonModuloProfesor.setBorderPainted(false);
        botonModuloProfesor.setContentAreaFilled(false);
        botonModuloProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModuloProfesorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(botonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jComboTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(botonModuloProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(botonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboTabla))
                        .addGap(24, 24, 24))
                    .addComponent(botonModuloProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(botonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
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

        jLabelHoras.setFont(new java.awt.Font("Lucida Console", 0, 16)); // NOI18N
        jLabelHoras.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHoras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabelHoras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(botonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(botonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelTableLayout.createSequentialGroup()
                        .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2)))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 550, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 84, Short.MAX_VALUE)
                    .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelActionPerformed
        // TODO add your handling code here:
        //Cambios en BD

        PantallaLogIn plog = new PantallaLogIn();
        this.dispose();
        plog.setVisible(true);
    }//GEN-LAST:event_botonCancelActionPerformed

    private void botonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSaveActionPerformed
        // TODO add your handling code here:
        //Cambios en BD
        List<Object[]> listaModulosSeleccionados = new ArrayList();
        for (int i = 0; i < jTableSelect.getRowCount(); i++) {
            Object[] listaModuloColumnas = new Object[jTableSelect.getColumnCount()];
            for (int j = 0; j < jTableSelect.getColumnCount(); j++) {
                listaModuloColumnas[j] = jTableSelect.getValueAt(i, j);
            }
            listaModulosSeleccionados.add(listaModuloColumnas);
        }

        if (!listaModulosSeleccionados.isEmpty()) {
            String nombreModulo;
            String idCurso;
            for (Object[] modulo : listaModulosSeleccionados) {
                nombreModulo = modulo[0].toString();
                idCurso = modulo[1].toString();

                EntityManager em = emf.createEntityManager();
                String query = """
                               SELECT 
                               m.idModulo
                               FROM danielCastro.schoolschedule.dao.Modulo m
                               WHERE m.nombre = :nombreModulo
                               """;
                Query tq = em.createQuery(query);
                tq.setParameter("nombreModulo", nombreModulo);
                int idModulo = 0;
                try {
                    idModulo = Integer.parseInt(tq.getSingleResult().toString());
                    Modulo_Profesor moduloProfesor
                            = new Modulo_Profesor(nifProfesor, idModulo, idCurso);
                    emf.createEntityManager();
                    em.getTransaction().begin();
                    em.persist(em.merge(moduloProfesor));
                    em.getTransaction().commit();
                } catch (NoResultException nrex) {
                    System.out.println("No se ha encontrado ningún resultado en la BD.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    em.close();
                }
            }
        }

        EntityManager em = emf.createEntityManager();
        String query = """
                       SELECT 
                       m.idModulo
                       FROM danielCastro.schoolschedule.dao.Modulo_Profesor m
                       """;
        Query tq = em.createQuery(query);
        List<Integer> dbModulosList = null;
        try {
            dbModulosList = tq.getResultList();
        } catch (NoResultException nrex) {
            System.out.println("No se ha encontrado ningún resultado en la BD.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        for (int idModuloDB : dbModulosList) {
            int idModuloTabla = 0;
            boolean borrar = true;
            for (Object[] moduloTabla : listaModulosSeleccionados) {
                em = emf.createEntityManager();
                query = """
                               SELECT 
                               m.idModulo
                               FROM danielCastro.schoolschedule.dao.Modulo m
                               WHERE m.nombre = :nombreModulo
                               """;
                tq = em.createQuery(query);
                tq.setParameter("nombreModulo", moduloTabla[0].toString());
                idModuloTabla = 0;
                idModuloTabla = Integer.parseInt(tq.getSingleResult().toString());
                if (idModuloDB == idModuloTabla) {
                    borrar = false;
                    break;
                }
            }
            em.close();
            em = emf.createEntityManager();
            if (borrar) {
                try {
                    em.getTransaction().begin();
                    em.remove(em.merge(em.find(Modulo_Profesor.class, idModuloDB)));
                    em.getTransaction().commit();
                } catch (Exception e) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Está intentando borrar una columna"
                            + " referenciado en otra tabla. No se puede realizar esa operación.",
                            "Error de borrado de columna.", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    em.close();
                }
            }

        }
        PantallaSeleccion ps = new PantallaSeleccion(nifProfesor);
        this.dispose();
        ps.setVisible(true);
    }//GEN-LAST:event_botonSaveActionPerformed

    private void botonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDeleteActionPerformed
        // TODO add your handling code here:
        int selectedIndex = jTableSelect.getSelectedRow();
        Object[] objetoSeleccionado = new Object[jTableSelect.getColumnCount()];
        for (int i = 0; i < jTableSelect.getColumnCount(); i++) {
            objetoSeleccionado[i] = ptm.getValueAt(selectedIndex, i);
        }
        ptm.removeRow(jTableSelect.getSelectedRow());
        horasProfesor -= Integer.parseInt(objetoSeleccionado[3].toString());
        System.out.println(horasProfesor);
        jLabelHoras.setText("Horas seleccionadas: " + horasProfesor);
    }//GEN-LAST:event_botonDeleteActionPerformed

    private void botonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCreateActionPerformed
        // TODO add your handling code here:
        int selectedIndex = jTable.getSelectedRow();
        Object[] objetoSeleccionado = new Object[jTable.getColumnCount()];
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            objetoSeleccionado[i] = dtm.getValueAt(selectedIndex, i);
        }
        horasProfesor += Integer.parseInt(objetoSeleccionado[3].toString());
        if(horasProfesor <= 100) {
            ptm.addRow(objetoSeleccionado);
            jLabelHoras.setText("Horas seleccionadas: " + horasProfesor);
        }else {
            horasProfesor -= Integer.parseInt(objetoSeleccionado[3].toString());
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "No se sobreexplote mi rey.",
                    "Error de extenuación.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonCreateActionPerformed

    private void botonModuloProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModuloProfesorActionPerformed
        // TODO add your handling code here:
        PantallaModuloProfesor pmp = new PantallaModuloProfesor(this, true);
        this.setVisible(false);
        pmp.setVisible(true);
    }//GEN-LAST:event_botonModuloProfesorActionPerformed

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
            new PantallaSeleccion(nifProfesor).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancel;
    private javax.swing.JButton botonCreate;
    private javax.swing.JButton botonDelete;
    private javax.swing.JButton botonModuloProfesor;
    private javax.swing.JButton botonSave;
    private javax.swing.JComboBox<String> jComboTabla;
    private javax.swing.JLabel jLabelHoras;
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
