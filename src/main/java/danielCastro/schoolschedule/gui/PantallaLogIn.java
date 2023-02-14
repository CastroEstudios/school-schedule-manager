/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package danielCastro.schoolschedule.gui;

import com.formdev.flatlaf.FlatLightLaf;
import danielCastro.schoolschedule.dao.Ciclo;
import danielCastro.schoolschedule.dao.Curso;
import danielCastro.schoolschedule.dao.Especialidad;
import danielCastro.schoolschedule.dao.Login;
import danielCastro.schoolschedule.dao.Modulo;
import danielCastro.schoolschedule.dao.Modulo_Curso;
import danielCastro.schoolschedule.dao.Profesor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
public class PantallaLogIn extends javax.swing.JFrame {
    List<String> imagePaths = new ArrayList<>();
    public static File fileDB = new File("cesarManrique.db");
    ODB odb = ODBFactory.open(fileDB.getName(), "root", "");
    /**
     * Creates new form PantallaLogIn
     */
    public PantallaLogIn() {
        initComponents();
        this.setLocationRelativeTo(null);
        styles();
        addBGImages();
        randomImg(labelIntoJPanel(jPanelImg));
        initBGImage(".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\BG15-2.png", labelIntoJPanel(jPanelImg));
        initBGImage(".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\BG18.png", labelIntoJPanel(jPanelMain));
        initBGImage(".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\userIcon.png", jLabelUser);
        initBGImage(".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\pswKey.png", jLabelPsw);
    }
    
    //It sets the styles of the JComponents
    private void styles() {
        jLabelSecondary1.setForeground(Color.decode("#6c757d"));
        jLabelSecondary2.setForeground(Color.decode("#6c757d"));
        
        jTextNIF.setBackground(Color.decode("#f7f7f2"));
        jTextNIF.setBorder(new CompoundBorder(new LineBorder(Color.decode("#6c757d"), 2), new EmptyBorder(5,5,5,5)));
        jTextPassword.setBackground(Color.decode("#f7f7f2"));
        jTextPassword.setBorder(new CompoundBorder(new LineBorder(Color.decode("#6c757d"), 2), new EmptyBorder(5,5,5,5)));
        
        jPanelImg.setBackground(Color.decode("#a2c7de"));
        jPanelMain.setBackground(Color.decode("#a2c7de"));
        
        jBotonLog.putClientProperty("JButton.buttonType","roundRect");
        botonDatosPrueba.putClientProperty("JButton.buttonType","roundRect");
        jBotonLog.setBackground(Color.decode("#6e8a89"));
        botonDatosPrueba.setBackground(Color.decode("#6e8a89"));
    }
    
    //This method picks up all the tenerifex.png images and puts them into a list.
    private void addBGImages() {
        int i = 1;
        String path = ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\tenerife" + i + ".png";
            while(Files.exists(Paths.get(path))){
                imagePaths.add(path);
                i++;
            path = ".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\tenerife" + i + ".png";
        }
    }
    
    //Grabs one random image on the list and calls initBGImage method to load the image.
    private void randomImg(JLabel label) {
        int randomInt = new Random().nextInt(0, imagePaths.size());
        initBGImage(imagePaths.get(randomInt), label);
    }
        
    //Loads the image into the JLabel created by labelIntoJPanel() method.
    public static void initBGImage(String url, JLabel label) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(),
        Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg); 
        label.setIcon(icon);
    }
    
    public static void initBGImage(String url, JButton boton) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(boton.getWidth(), boton.getHeight(),
        Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg); 
        boton.setIcon(icon);
    }
        
    //Creates a JLabel inside a JPanel in order to set the background image for that JPanel. 
    public static JLabel labelIntoJPanel(JPanel panel) {
        JLabel label = new JLabel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        label.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        return label;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jPanelHead = new javax.swing.JPanel();
        jLabelSecondary1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelSecondary2 = new javax.swing.JLabel();
        jPanelBody = new javax.swing.JPanel();
        jLabelNIF = new javax.swing.JLabel();
        jLabelContra = new javax.swing.JLabel();
        jTextNIF = new javax.swing.JTextField();
        jTextPassword = new javax.swing.JPasswordField();
        jLabelUser = new javax.swing.JLabel();
        jLabelPsw = new javax.swing.JLabel();
        jPanelImg = new javax.swing.JPanel();
        jPanelBotones = new javax.swing.JPanel();
        jBotonLog = new javax.swing.JButton();
        botonDatosPrueba = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHead.setOpaque(false);

        jLabelSecondary1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N
        jLabelSecondary1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSecondary1.setText("Por favor, rellene el formulario ");

        jLabel5.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Inicio de Sesión");

        jLabelSecondary2.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N
        jLabelSecondary2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSecondary2.setText("con sus correspondientes datos:");

        javax.swing.GroupLayout jPanelHeadLayout = new javax.swing.GroupLayout(jPanelHead);
        jPanelHead.setLayout(jPanelHeadLayout);
        jPanelHeadLayout.setHorizontalGroup(
            jPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelSecondary2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelHeadLayout.createSequentialGroup()
                        .addComponent(jLabelSecondary1)
                        .addGap(19, 19, 19)))
                .addGap(103, 103, 103))
        );
        jPanelHeadLayout.setVerticalGroup(
            jPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeadLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabelSecondary1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecondary2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanelBody.setOpaque(false);

        jLabelNIF.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N
        jLabelNIF.setText("NIF:");

        jLabelContra.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N
        jLabelContra.setText("Contraseña:");

        jTextNIF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jTextNIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNIFActionPerformed(evt);
            }
        });

        jTextPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jTextPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBodyLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPsw, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelContra)
                    .addComponent(jTextNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNIF))
                .addGap(54, 54, 54))
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNIF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBodyLayout.createSequentialGroup()
                        .addComponent(jTextNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabelContra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanelBodyLayout.createSequentialGroup()
                        .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPsw, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanelImgLayout = new javax.swing.GroupLayout(jPanelImg);
        jPanelImg.setLayout(jPanelImgLayout);
        jPanelImgLayout.setHorizontalGroup(
            jPanelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        jPanelImgLayout.setVerticalGroup(
            jPanelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelBotones.setOpaque(false);

        jBotonLog.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jBotonLog.setText("Iniciar Sesión");
        jBotonLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonLogActionPerformed(evt);
            }
        });

        botonDatosPrueba.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        botonDatosPrueba.setText("Cargar Datos de Prueba");
        botonDatosPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDatosPruebaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonDatosPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBotonLog, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jBotonLog, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(botonDatosPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelHead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNIFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNIFActionPerformed

    private void jTextPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPasswordActionPerformed

    //Validates the user credentials are in the database.
    private void jBotonLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonLogActionPerformed
        
        String nif = jTextNIF.getText();
        char[] contrasegna = jTextPassword.getPassword();
        String contrasegnaS = "";
        for (char c : contrasegna) {
            contrasegnaS += c;
        }

        IQuery query = new CriteriaQuery(Login.class, Where.and().add(Where.equal("profesor_nif", nif))
                                                           .add(Where.equal("clave", contrasegnaS)));
        Objects<Login> result = odb.getObjects(query);

        if (result.hasNext()) {
            Login login = result.next();
            odb.close();
            if(login.getPermisos().equals("SA")) {
                PantallaCRUD pp = new PantallaCRUD();
                this.dispose();
                pp.setVisible(true);
            } else if(login.getPermisos().equals("Profesor")) {
                PantallaSeleccion ps = new PantallaSeleccion(nif);
                this.dispose();
                ps.setVisible(true);
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos."
                    , "Error de credenciales", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBotonLogActionPerformed
    
    //Clears both the NIF and Password JTextFields.
    private void botonDatosPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDatosPruebaActionPerformed
        // TODO add your handling code here:
        if(fileDB.exists()) {
            
            fileDB.delete();
            
            // Insert into Especialidad
            odb.store(new Especialidad(368, "Informática"));
            odb.store(new Especialidad(468, "Aplicaciones"));

            // Insert into Profesor
            odb.store(new Profesor("11111111A", "Wilfredo", "Wilfredo", 1560, 30, 468));
            odb.store(new Profesor("11111111B", "Manuel", "Carrasco", 7350, 28, 368));
            odb.store(new Profesor("11111111C", "Abel", "Abel", 110, 40, 468));
            odb.store(new Profesor("11111111D", "Lucinio", "Johnson", 9034, 40, 368));
            odb.store(new Profesor("99999999A", "SA", "SA", 0, 0, 368));

            // Insert into Modulo
            odb.store(new Modulo(1, "ADE", 1, 1, "Administración de sistemas gestores de bases de datos", 368));
            odb.store(new Modulo(2, "ADE", 2, 4, "Administración de sistemas gestores de bases de datos", 368));
            odb.store(new Modulo(3, "AED", 3, 3, "Acceso a datos", 368));
            odb.store(new Modulo(4, "AED", 5, 5, "Acceso a datos", 368));
            odb.store(new Modulo(5, "APW", 3, 6, "Aplicaciones Web", 368));
            odb.store(new Modulo(6, "APW", 2, 2, "Aplicaciones Web", 368));
            odb.store(new Modulo(7, "BAE", 6, 12, "Bases de datos", 368));
            odb.store(new Modulo(8, "BAE", 4, 4, "Bases de datos", 368));
            odb.store(new Modulo(9, "DPL", 4, 8, "Despliegue de aplicaciones web", 368));
            odb.store(new Modulo(10, "DPL", 2, 2, "Despliegue de aplicaciones web", 368));
            odb.store(new Modulo(11, "DSW", 5, 10, "Desarrollo web en entorno servidor", 368));
            odb.store(new Modulo(12, "DSW", 3, 3, "Desarrollo web en entorno servidor", 368));
            odb.store(new Modulo(13, "ETS", 2, 2, "Entornos de desarrollo", 368));
            odb.store(new Modulo(14, "ETS", 1, 1, "Entornos de desarrollo", 368));
            odb.store(new Modulo(15, "ETS", 3, 6, "Entornos de desarrollo", 368));
            odb.store(new Modulo(16, "FCT", 4, 8, "Formación en centros de trabajo", 368));
            odb.store(new Modulo(17, "FCT", 4, 4, "Formación en centros de trabajo", 368));
            odb.store(new Modulo(18, "FCT", 3, 3, "Formación en centros de trabajo", 368));
            odb.store(new Modulo(19, "GTB", 5, 10, "Gestión de bases de datos", 368));
            odb.store(new Modulo(20, "GTB", 5, 5, "Gestión de bases de datos", 368));
            odb.store(new Modulo(21, "HAJ", 4, 4, "Hacking ético", 368));
            odb.store(new Modulo(22, "IHC", 4, 4, "Incidentes de ciberseguridad", 368));
            odb.store(new Modulo(23, "IMW", 2, 2, "Implantación de aplicaciones web", 368));
            odb.store(new Modulo(24, "IMW", 4, 8, "Implantación de aplicaciones web", 368));
            odb.store(new Modulo(25, "LND", 2, 2, "Lenguaje de marcas y gestión de la información", 368));
            odb.store(new Modulo(26, "LND", 1, 1, "Lenguaje de marcas y gestión de la información", 368));
            odb.store(new Modulo(27, "LND", 4, 8, "Lenguaje de marcas y gestión de la información", 368));
            odb.store(new Modulo(28, "NOB", 2, 2, "Normativa de ciberseguridad", 368));
            odb.store(new Modulo(29, "PGL", 3, 3, "Programación multimedia y dispositivos móviles", 368));
            odb.store(new Modulo(30, "PGL", 2, 2, "Programación multimedia y dispositivos móviles", 368));
            odb.store(new Modulo(31, "PGV", 3, 3, "Programación de servicios y procesos", 368));
            odb.store(new Modulo(32, "PGV", 1, 1, "Programación de servicios y procesos", 368));
            odb.store(new Modulo(33, "PMR", 2, 4, "Proyecto de administración de sistemas informáticos en red", 368));
            odb.store(new Modulo(34, "PMR", 1, 1, "Proyecto de administración de sistemas informáticos en red", 368));
            odb.store(new Modulo(35, "PNI", 5, 5, "Proyecto de administración de sistemas informáticos en red", 368));
            odb.store(new Modulo(36, "PNI", 1, 1, "Proyecto de administración de sistemas informáticos en red", 368));
            odb.store(new Modulo(37, "PNI", 5, 10, "Proyecto de administración de sistemas informáticos en red", 368));
            odb.store(new Modulo(38, "PPP", 2, 2, "Proyecto de desarrollo de aplicaciones multiplataforma", 368));
            odb.store(new Modulo(39, "PPP", 1, 1, "Proyecto de desarrollo de aplicaciones multiplataforma", 368));
            odb.store(new Modulo(40, "PRO", 5, 5, "Programación", 368));
            odb.store(new Modulo(41, "PRO", 2, 2, "Programación", 368));
            odb.store(new Modulo(42, "PRO", 7, 14, "Programación", 368));
            odb.store(new Modulo(43, "PRW", 2, 4, "Proyecto de desarrollo de aplicaciones web", 368));
            odb.store(new Modulo(44, "PRW", 1, 1, "Proyecto de desarrollo de aplicaciones web", 368));
            odb.store(new Modulo(45, "RDE", 7, 14, "Redes Locales", 368));
            odb.store(new Modulo(46, "RDE", 5, 5, "Redes Locales", 368));
            odb.store(new Modulo(47, "SGF", 4, 8, "Seguridad Informática", 368));
            odb.store(new Modulo(48, "SGF", 2, 2, "Seguridad Informática", 368));
            odb.store(new Modulo(49, "SGY", 4, 8, "Seguridad y alta disponibilidad", 368));
            odb.store(new Modulo(50, "SGY", 2, 2, "Seguridad y alta disponibilidad", 368));
            odb.store(new Modulo(51, "SRC", 5, 10, "Servicios en red", 368));
            odb.store(new Modulo(52, "SRC", 3, 3, "Servicios en red", 368));
            odb.store(new Modulo(53, "SRD", 4, 8, "Servicios en red", 368));
            odb.store(new Modulo(54, "SRD", 3, 3, "Servicios en red", 368));
            odb.store(new Modulo(55, "TUO", 1, 1, "Tutoría", 368));
            odb.store(new Modulo(56, "ADD", 4, 8, "Administración de sistemas operativos", 468));
            odb.store(new Modulo(57, "ADD", 3, 3, "Administración de sistemas operativos", 468));
            odb.store(new Modulo(58, "AIF", 8, 16, "Aplicaciones ofimáticas", 468));
            odb.store(new Modulo(59, "AIF", 5, 5, "Aplicaciones ofimáticas", 468));
            odb.store(new Modulo(60, "ANZ", 4, 4, "Análisis forense informático", 468));
            odb.store(new Modulo(61, "BAG", 6, 6, "Bastionado de redes y sistemas", 468));
            odb.store(new Modulo(62, "BLO", 1, 1, "Bloque de formación inical", 468));
            odb.store(new Modulo(63, "DAD", 5, 5, "Desarrollo de interfaces", 468));
            odb.store(new Modulo(64, "DAD", 3, 3, "Desarrollo de interfaces", 468));
            odb.store(new Modulo(65, "DEW", 5, 10, "Desarrollo web en entorno cliente", 468));
            odb.store(new Modulo(66, "DEW", 3, 3, "Desarrollo web en entorno cliente", 468));
            odb.store(new Modulo(67, "DOR", 4, 8, "Diseño de interfaces web", 468));
            odb.store(new Modulo(68, "DOR", 3, 3, "Diseño de interfaces web", 468));
            odb.store(new Modulo(69, "FCT", 4, 8, "Formación en centros de trabajo", 468));
            odb.store(new Modulo(70, "FCT", 3, 3, "Formación en centros de trabajo", 468));
            odb.store(new Modulo(71, "FUW", 3, 6, "Fundamentos de hardware", 468));
            odb.store(new Modulo(72, "FUW", 2, 2, "Fundamentos de hardware", 468));
            odb.store(new Modulo(73, "FUW", 1, 1, "Fundamentos de hardware", 468));
            odb.store(new Modulo(74, "IDP", 8, 16, "Implantación de sistemas operativos", 468));
            odb.store(new Modulo(75, "IDP", 5, 5, "Implantación de sistemas operativos", 468));
            odb.store(new Modulo(76, "IDP", 2, 2, "Implantación de sistemas operativos", 468));
            odb.store(new Modulo(77, "ITG", 2, 4, "Integración", 468));
            odb.store(new Modulo(78, "ITG", 1, 1, "Integración", 468));
            odb.store(new Modulo(79, "MJE", 7, 14, "Montaje y mantenimiento de equipo", 468));
            odb.store(new Modulo(80, "MJE", 5, 5, "Montaje y mantenimiento de equipo", 468));
            odb.store(new Modulo(81, "PUK", 4, 4, "Puesta en producción segura", 468));
            odb.store(new Modulo(82, "SSF", 5, 10, "Sistemas informáticos", 468));
            odb.store(new Modulo(83, "SSF", 4, 4, "Sistemas informáticos", 468));
            odb.store(new Modulo(84, "SSF", 1, 1, "Sistemas informáticos", 468));
            odb.store(new Modulo(85, "SSN", 5, 10, "Sistemas operativos en red", 468));
            odb.store(new Modulo(86, "SSN", 4, 4, "Sistemas operativos en red", 468));
            odb.store(new Modulo(87, "SSV", 5, 10, "Sistemas operativos monopuesto", 468));
            odb.store(new Modulo(88, "SSV", 3, 3, "Sistemas operativos monopuesto", 468));
            odb.store(new Modulo(89, "TUO", 1, 1, "Tutoría", 468));
            odb.store(new Modulo(90, "TUO", 2, 2, "Tutoría", 468));

            //insert Ciclo
            odb.store(new Ciclo(0, "1º CFGM Dist. Informática y Comunicaciones - Sistemas Microinformáticos y Redes (LOE)", "Medio", 368));
            odb.store(new Ciclo(1, "1º CFGM Informática y Comunicaciones - Sistemas Microinformáticos y Redes (LOE)", "Medio", 368));
            odb.store(new Ciclo(2, "1º CFGS Dist. Informática y Comunicaciones - Administración de Sistemas Informáticos en Red (LOE)", "Superior", 368));
            odb.store(new Ciclo(3, "1º CFGS Dist. Informática y Comunicaciones - Desarrollo de Aplicaciones Multiplataforma (LOE)", "Superior", 468));
            odb.store(new Ciclo(4, "1º CFGS Dist. Informática y Comunicaciones - Desarrollo de Aplicaciones Web (LOE)", "Superior", 468));
            odb.store(new Ciclo(5, "1º CFGS Informática y Comunicaciones - Administración de Sistemas Informáticos en Red (LOE)", "Superior", 368));
            odb.store(new Ciclo(6, "1º CFGS Informática y Comunicaciones - Desarrollo de Aplicaciones Multiplataforma (LOE)", "Superior", 468));
            odb.store(new Ciclo(7, "1º CFGS Informática y Comunicaciones - Desarrollo de Aplicaciones Web (LOE)", "Superior", 468));
            odb.store(new Ciclo(8, "2º CFGM Dist. Informática y Comunicaciones - Sistemas Microinformáticos y Redes (LOE)", "Medio", 368));
            odb.store(new Ciclo(9, "2º CFGS Dist. Informática y Comunicaciones - Administración de Sistemas Informáticos en Red (LOE)", "Superior", 368));
            odb.store(new Ciclo(10, "2º CFGS Dist. Informática y Comunicaciones - Desarrollo de Aplicaciones Multiplataforma (LOE)", "Superior", 468));
            odb.store(new Ciclo(11, "2º CFGS Dist. Informática y Comunicaciones - Desarrollo de Aplicaciones Web (LOE)", "Superior", 468));
            odb.store(new Ciclo(12, "2º CFGS Informática y Comunicaciones - Administración de Sistemas Informáticos en Red (LOE)", "Superior", 368));
            odb.store(new Ciclo(13, "2º CFGS Informática y Comunicaciones - Desarrollo de Aplicaciones Multiplataforma (LOE)", "Superior", 468));
            odb.store(new Ciclo(14, "2º CFGS Informática y Comunicaciones - Desarrollo de aplicaciones web (LOE)", "Superior", 468));
            odb.store(new Ciclo(15, "3º CFGM Dist. Informática y Comunicaciones - Sistemas Microinformáticos y Redes (LOE)", "Medio", 368));
            odb.store(new Ciclo(16, "3º CFGS Dist. Informática y Comunicaciones - Administración de Sistemas Informáticos en Red (LOE)", "Superior", 368));
            odb.store(new Ciclo(17, "3º CFGS Dist. Informática y Comunicaciones - Desarrollo de Aplicaciones Multiplataforma (LOE)", "Superior", 468));
            odb.store(new Ciclo(18, "3º CFGS Dist. Informática y Comunicaciones - Desarrollo de Aplicaciones Web (LOE)", "Superior", 368));
            odb.store(new Ciclo(19, "Ciberseguridad en Entornos de las Tecnologías de la Información", "Básico", 368));
            odb.store(new Ciclo(20, "2º CFGM Informática y Comunicaciones - Sistemas Microinformáticos y Redes (LOE)", "Medio", 368));
            odb.store(new Ciclo(21, "1º TÉCNICO SUPERIOR ANIMACIONES 3D, JUEGOS Y ENTORNOS INTERACTIVOS LOE", "Superior", 368));

            //Insert curso
            odb.store(new Curso("671NTB", "Tarde", 1, 6));
            odb.store(new Curso("671NTB", "Tarde", 1, 6));
            odb.store(new Curso("672NTA", "Tarde", 2, 13));
            odb.store(new Curso("671NTA", "Tarde", 1, 6));
            odb.store(new Curso("753NNS", "Noche", 3, 18));
            odb.store(new Curso("662NTB", "Tarde", 2, 20));
            odb.store(new Curso("782NNS", "Noche", 2, 9));
            odb.store(new Curso("7L1NNS", "Noche", 1, 0));
            odb.store(new Curso("7L2NNS", "Noche", 1, 8));
            odb.store(new Curso("733NNS", "Noche", 3, 17));
            odb.store(new Curso("651NMB", "Manana", 1, 7));
            odb.store(new Curso("5L1NMA", "Manana", 1, 21));
            odb.store(new Curso("681NMA", "Manana", 1, 5));
            odb.store(new Curso("751NNS", "Noche", 1, 4));
            odb.store(new Curso("661NMA", "Manana", 1, 1));
            odb.store(new Curso("661NTB", "Tarde", 1, 1));
            odb.store(new Curso("781NNS", "Noche", 1, 2));
            odb.store(new Curso("731NNS", "Noche", 1, 3));
            odb.store(new Curso("652NMA", "Manana", 2, 14));
            odb.store(new Curso("651NMA", "Manana", 1, 14));
            odb.store(new Curso("652NMB", "Manana", 2, 14));
            odb.store(new Curso("732NNS", "Noche", 2, 10));
            odb.store(new Curso("682NMA", "Manana", 2, 12));
            odb.store(new Curso("783NNS", "Noche", 3, 16));
            odb.store(new Curso("752NNS", "Noche", 2, 11));
            odb.store(new Curso("991NTA", "Manana", 1, 19));
            odb.store(new Curso("7L3NNS", "Noche", 3, 15));
            odb.store(new Curso("681NTB", "Tarde", 1, 5));
            odb.store(new Curso("682NTB", "Tarde", 2, 5));
            odb.store(new Curso("662NMA", "Manana", 2, 8));

            //insertar Modulo_Curso
            odb.store(new Modulo_Curso(45, "661NMA"));
            odb.store(new Modulo_Curso(45, "661NTB"));
            odb.store(new Modulo_Curso(35, "781NNS"));
            odb.store(new Modulo_Curso(36, "781NNS"));
            odb.store(new Modulo_Curso(13, "731NNS"));
            odb.store(new Modulo_Curso(14, "731NNS"));
            odb.store(new Modulo_Curso(25, "731NNS"));
            odb.store(new Modulo_Curso(26, "731NNS"));
            odb.store(new Modulo_Curso(40, "731NNS"));
            odb.store(new Modulo_Curso(41, "731NNS"));
            odb.store(new Modulo_Curso(13, "751NNS"));
            odb.store(new Modulo_Curso(19, "781NNS"));
            odb.store(new Modulo_Curso(27, "781NNS"));
            odb.store(new Modulo_Curso(37, "781NNS"));
            odb.store(new Modulo_Curso(7, "671NTB"));
            odb.store(new Modulo_Curso(7, "671NTA"));
            odb.store(new Modulo_Curso(15, "671NTB"));
            odb.store(new Modulo_Curso(15, "671NTA"));
            odb.store(new Modulo_Curso(27, "671NTB"));
            odb.store(new Modulo_Curso(27, "671NTA"));
            odb.store(new Modulo_Curso(42, "671NTB"));
            odb.store(new Modulo_Curso(42, "671NTA"));
            odb.store(new Modulo_Curso(55, "671NTB"));
            odb.store(new Modulo_Curso(55, "671NTA"));
            odb.store(new Modulo_Curso(7, "651NMB"));
            odb.store(new Modulo_Curso(15, "651NMB"));
            odb.store(new Modulo_Curso(27, "651NMB"));
            odb.store(new Modulo_Curso(42, "651NMB"));
            odb.store(new Modulo_Curso(55, "651NMB"));
            odb.store(new Modulo_Curso(46, "7L2NNS"));
            odb.store(new Modulo_Curso(46, "662NMA"));
            odb.store(new Modulo_Curso(5, "7L2NNS"));
            odb.store(new Modulo_Curso(5, "662NMA"));
            odb.store(new Modulo_Curso(47, "7L2NNS"));
            odb.store(new Modulo_Curso(47, "662NMA"));
            odb.store(new Modulo_Curso(51, "7L2NNS"));
            odb.store(new Modulo_Curso(51, "662NMA"));
            odb.store(new Modulo_Curso(1, "782NNS"));
            odb.store(new Modulo_Curso(20, "782NNS"));
            odb.store(new Modulo_Curso(23, "782NNS"));
            odb.store(new Modulo_Curso(25, "782NNS"));
            odb.store(new Modulo_Curso(3, "732NNS"));
            odb.store(new Modulo_Curso(8, "732NNS"));
            odb.store(new Modulo_Curso(55, "732NNS"));
            odb.store(new Modulo_Curso(8, "752NNS"));
            odb.store(new Modulo_Curso(55, "752NNS"));
            odb.store(new Modulo_Curso(2, "682NMA"));
            odb.store(new Modulo_Curso(16, "682NMA"));
            odb.store(new Modulo_Curso(24, "682NMA"));
            odb.store(new Modulo_Curso(33, "682NMA"));
            odb.store(new Modulo_Curso(49, "682NMA"));
            odb.store(new Modulo_Curso(53, "682NMA"));
            odb.store(new Modulo_Curso(4, "672NTA"));
            odb.store(new Modulo_Curso(17, "672NTA"));
            odb.store(new Modulo_Curso(29, "672NTA"));
            odb.store(new Modulo_Curso(31, "672NTA"));
            odb.store(new Modulo_Curso(38, "672NTA"));
            odb.store(new Modulo_Curso(9, "652NMA"));
            odb.store(new Modulo_Curso(9, "651NMA"));
            odb.store(new Modulo_Curso(9, "652NMB"));
            odb.store(new Modulo_Curso(11, "652NMA"));
            odb.store(new Modulo_Curso(11, "651NMA"));
            odb.store(new Modulo_Curso(11, "652NMB"));
            odb.store(new Modulo_Curso(16, "652NMA"));
            odb.store(new Modulo_Curso(16, "651NMA"));
            odb.store(new Modulo_Curso(16, "652NMB"));
            odb.store(new Modulo_Curso(43, "652NMA"));
            odb.store(new Modulo_Curso(43, "651NMA"));
            odb.store(new Modulo_Curso(43, "652NMB"));
            odb.store(new Modulo_Curso(6, "7L3NNS"));
            odb.store(new Modulo_Curso(48, "7L3NNS"));
            odb.store(new Modulo_Curso(52, "7L3NNS"));
            odb.store(new Modulo_Curso(18, "783NNS"));
            odb.store(new Modulo_Curso(34, "783NNS"));
            odb.store(new Modulo_Curso(50, "783NNS"));
            odb.store(new Modulo_Curso(54, "783NNS"));
            odb.store(new Modulo_Curso(18, "733NNS"));
            odb.store(new Modulo_Curso(30, "733NNS"));
            odb.store(new Modulo_Curso(32, "733NNS"));
            odb.store(new Modulo_Curso(39, "733NNS"));
            odb.store(new Modulo_Curso(10, "753NNS"));
            odb.store(new Modulo_Curso(12, "753NNS"));
            odb.store(new Modulo_Curso(18, "753NNS"));
            odb.store(new Modulo_Curso(44, "753NNS"));
            odb.store(new Modulo_Curso(21, "991NTA"));
            odb.store(new Modulo_Curso(22, "991NTA"));
            odb.store(new Modulo_Curso(28, "991NTA"));
            odb.store(new Modulo_Curso(59, "7L1NNS"));
            odb.store(new Modulo_Curso(62, "7L1NNS"));
            odb.store(new Modulo_Curso(80, "7L1NNS"));
            odb.store(new Modulo_Curso(88, "7L1NNS"));
            odb.store(new Modulo_Curso(89, "7L1NNS"));
            odb.store(new Modulo_Curso(58, "661NMA"));
            odb.store(new Modulo_Curso(58, "661NTB"));
            odb.store(new Modulo_Curso(79, "661NMA"));
            odb.store(new Modulo_Curso(79, "661NTB"));
            odb.store(new Modulo_Curso(87, "661NMA"));
            odb.store(new Modulo_Curso(87, "661NTB"));
            odb.store(new Modulo_Curso(89, "661NMA"));
            odb.store(new Modulo_Curso(89, "661NTB"));
            odb.store(new Modulo_Curso(62, "781NNS"));
            odb.store(new Modulo_Curso(72, "781NNS"));
            odb.store(new Modulo_Curso(73, "781NNS"));
            odb.store(new Modulo_Curso(75, "781NNS"));
            odb.store(new Modulo_Curso(76, "781NNS"));
            odb.store(new Modulo_Curso(89, "781NNS"));
            odb.store(new Modulo_Curso(62, "731NNS"));
            odb.store(new Modulo_Curso(83, "731NNS"));
            odb.store(new Modulo_Curso(84, "731NNS"));
            odb.store(new Modulo_Curso(89, "731NNS"));
            odb.store(new Modulo_Curso(62, "751NNS"));
            odb.store(new Modulo_Curso(83, "751NNS"));
            odb.store(new Modulo_Curso(84, "751NNS"));
            odb.store(new Modulo_Curso(89, "751NNS"));
            odb.store(new Modulo_Curso(71, "681NMA"));
            odb.store(new Modulo_Curso(71, "681NTB"));
            odb.store(new Modulo_Curso(71, "682NTB"));
            odb.store(new Modulo_Curso(74, "681NMA"));
            odb.store(new Modulo_Curso(74, "681NTB"));
            odb.store(new Modulo_Curso(74, "682NTB"));
            odb.store(new Modulo_Curso(89, "681NMA"));
            odb.store(new Modulo_Curso(89, "681NTB"));
            odb.store(new Modulo_Curso(89, "682NTB"));
            odb.store(new Modulo_Curso(82, "671NTB"));
            odb.store(new Modulo_Curso(82, "671NTA"));
            odb.store(new Modulo_Curso(89, "671NTB"));
            odb.store(new Modulo_Curso(89, "671NTA"));
            odb.store(new Modulo_Curso(82, "651NMB"));
            odb.store(new Modulo_Curso(89, "651NMB"));
            odb.store(new Modulo_Curso(83, "7L2NNS"));
            odb.store(new Modulo_Curso(83, "662NMA"));
            odb.store(new Modulo_Curso(89, "7L2NNS"));
            odb.store(new Modulo_Curso(89, "662NMA"));
            odb.store(new Modulo_Curso(69, "662NTB"));
            odb.store(new Modulo_Curso(77, "662NTB"));
            odb.store(new Modulo_Curso(85, "662NTB"));
            odb.store(new Modulo_Curso(57, "782NNS"));
            odb.store(new Modulo_Curso(89, "782NNS"));
            odb.store(new Modulo_Curso(64, "732NNS"));
            odb.store(new Modulo_Curso(66, "752NNS"));
            odb.store(new Modulo_Curso(68, "752NNS"));
            odb.store(new Modulo_Curso(56, "682NMA"));
            odb.store(new Modulo_Curso(63, "672NTA"));
            odb.store(new Modulo_Curso(65, "652NMA"));
            odb.store(new Modulo_Curso(65, "651NMA"));
            odb.store(new Modulo_Curso(65, "652NMB"));
            odb.store(new Modulo_Curso(67, "652NMA"));
            odb.store(new Modulo_Curso(67, "651NMA"));
            odb.store(new Modulo_Curso(67, "652NMB"));
            odb.store(new Modulo_Curso(70, "7L3NNS"));
            odb.store(new Modulo_Curso(78, "7L3NNS"));
            odb.store(new Modulo_Curso(60, "991NTA"));
            odb.store(new Modulo_Curso(61, "991NTA"));
            odb.store(new Modulo_Curso(81, "991NTA"));
            odb.store(new Modulo_Curso(90, "991NTA"));

            //insert login
            odb.store(new Login("11111111A", "Profesor", "1234"));
            odb.store(new Login("11111111B", "Profesor", "1234"));
            odb.store(new Login("11111111C", "Profesor", "1234"));
            odb.store(new Login("11111111D", "Profesor", "1234"));
            odb.store(new Login("99999999A", "SA", "1234"));
            
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Datos de prueba introducidos"
                    , "Datos cargados", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_botonDatosPruebaActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaLogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDatosPrueba;
    private javax.swing.JButton jBotonLog;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelContra;
    private javax.swing.JLabel jLabelNIF;
    private javax.swing.JLabel jLabelPsw;
    private javax.swing.JLabel jLabelSecondary1;
    private javax.swing.JLabel jLabelSecondary2;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelHead;
    private javax.swing.JPanel jPanelImg;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JTextField jTextNIF;
    private javax.swing.JPasswordField jTextPassword;
    // End of variables declaration//GEN-END:variables
}
