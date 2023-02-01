/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package danielCastro.schoolschedule.gui;

import com.formdev.flatlaf.FlatLightLaf;
import danielCastro.schoolschedule.dao.Login;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Anima
 */
public class PantallaLogIn extends javax.swing.JFrame {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
    List<String> imagePaths = new ArrayList<>();
    /**
     * Creates new form PantallaLogIn
     */
    public PantallaLogIn() {
        initComponents();
        this.setLocationRelativeTo(null);
        styles();
        addBGImages();
        randomImg(labelIntoJPanel(jPanelImg));
        initBGImage(".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\BG15.png", labelIntoJPanel(jPanelImg));
        initBGImage(".\\src\\main\\java\\danielCastro\\schoolschedule\\img\\BG8.png", labelIntoJPanel(jPanelMain));
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
        jBotonClear.putClientProperty("JButton.buttonType","roundRect");
        jBotonLog.setBackground(Color.decode("#6e8a89"));
        jBotonClear.setBackground(Color.decode("#6e8a89"));
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
        jBotonClear = new javax.swing.JButton();

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

        jBotonClear.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jBotonClear.setText("Limpiar Credenciales");
        jBotonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBotonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBotonLog, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jBotonLog, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jBotonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // TODO add your handling code here:
        String nif = jTextNIF.getText();
        char[] contrasegna = jTextPassword.getPassword();
        String contrasegnaS = "";
        for (char c : contrasegna) {
            contrasegnaS += c;
        }
        EntityManager em = emf.createEntityManager();
        String query = "FROM Login WHERE profesor_nif = :nif"
                + " AND clave = :clave";
        TypedQuery<Login> tq = em.createQuery(query, Login.class);
        tq.setParameter("nif", nif);
        tq.setParameter("clave", contrasegnaS);
        Login login;
        try {
            login = tq.getSingleResult();
            if(login.getPermisos().equals("SA")) {
                PantallaCRUD pp = new PantallaCRUD();
                this.dispose();
                pp.setVisible(true);
            }else if(login.getPermisos().equals("Profesor")) {
                PantallaSeleccion ps = new PantallaSeleccion();
                this.dispose();
                ps.setVisible(true);
            }
        }catch (NoResultException nrex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos."
                    , "Error de credenciales", JOptionPane.ERROR_MESSAGE);
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }//GEN-LAST:event_jBotonLogActionPerformed

    //Clears both the NIF and Password JTextFields.
    private void jBotonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonClearActionPerformed
        // TODO add your handling code here:
        jTextNIF.setText("");
        jTextPassword.setText("");
    }//GEN-LAST:event_jBotonClearActionPerformed

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
    private javax.swing.JButton jBotonClear;
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
