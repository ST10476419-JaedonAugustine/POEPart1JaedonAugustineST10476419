/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author jaedon
 */
public class SignUp extends javax.swing.JFrame
{

    /**
     * Creates new form SignUp
     */
    public SignUp()
    {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        Phone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PasswordCheck = new javax.swing.JPasswordField();
        Password = new javax.swing.JPasswordField();
        SignUpB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ErrorDisplay = new javax.swing.JTextArea();
        Back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(500, 300, 400, 350));
        setLocation(new java.awt.Point(500, 300));
        setMinimumSize(new java.awt.Dimension(400, 350));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setSize(new java.awt.Dimension(400, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Please enter phone number");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        Phone.setText("E.g.+27829111829");
        Phone.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                PhoneMouseClicked(evt);
            }
        });
        getContentPane().add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jLabel2.setText("Please enter username");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        Username.setText("E.g.JaedonA");
        Username.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                UsernameMouseClicked(evt);
            }
        });
        getContentPane().add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        jLabel3.setText("Please enter password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        PasswordCheck.setText("jPasswordField1");
        PasswordCheck.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                PasswordCheckMouseClicked(evt);
            }
        });
        getContentPane().add(PasswordCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));

        Password.setText("jPasswordField1");
        Password.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                PasswordMouseClicked(evt);
            }
        });
        getContentPane().add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        SignUpB.setText("SignUp");
        SignUpB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SignUpBActionPerformed(evt);
            }
        });
        getContentPane().add(SignUpB, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        ErrorDisplay.setColumns(20);
        ErrorDisplay.setRows(5);
        jScrollPane1.setViewportView(ErrorDisplay);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 390, 60));

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BackActionPerformed(evt);
            }
        });
        getContentPane().add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Creating global objects
    LogInManager LMobj = new LogInManager();
    StartPage Spobj = new StartPage();
    
    // Global variables to store user input
    private static String UsernameOut;
    private static String PhoneOut;
    private static String PasswordOut;
    private static String PasswordCheckOut;

    // Event methods for clearing placeholder text
    private void PhoneMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_PhoneMouseClicked
    {//GEN-HEADEREND:event_PhoneMouseClicked
        Phone.setText("");
    }//GEN-LAST:event_PhoneMouseClicked

    private void UsernameMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_UsernameMouseClicked
    {//GEN-HEADEREND:event_UsernameMouseClicked
        Username.setText("");
        ErrorDisplay.setVisible(false);     // Hide error messages on new input
    }//GEN-LAST:event_UsernameMouseClicked

    private void PasswordCheckMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_PasswordCheckMouseClicked
    {//GEN-HEADEREND:event_PasswordCheckMouseClicked
        PasswordCheck.setText("");
    }//GEN-LAST:event_PasswordCheckMouseClicked

    private void PasswordMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_PasswordMouseClicked
    {//GEN-HEADEREND:event_PasswordMouseClicked
        Password.setText("");
    }//GEN-LAST:event_PasswordMouseClicked

    // SignUp button logic
    private void SignUpBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SignUpBActionPerformed
    {//GEN-HEADEREND:event_SignUpBActionPerformed
        // Collect values from fields into global variables
        PhoneOut = Phone.getText();
        UsernameOut = Username.getText();
        PasswordOut = Password.getText();
        PasswordCheckOut = PasswordCheck.getText();

        LMobj.Newuser();        // Register user logic in manager
        
        // Display result message
        ErrorDisplay.setText(LMobj.reisterUser());      
    }//GEN-LAST:event_SignUpBActionPerformed

    // Back button logic to return to start page
    private void BackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BackActionPerformed
    {//GEN-HEADEREND:event_BackActionPerformed
        Spobj.setVisible(true);
    }//GEN-LAST:event_BackActionPerformed

    // Getters for use in LogInManager
    public String getUsernameOut()
    {
        return UsernameOut;
    }

    public String getPhoneOut()
    {
        return PhoneOut;
    }

    public String getPasswordOut()
    {
        return PasswordOut;
    }

    public String getPasswordCheckOut()
    {
        return PasswordCheckOut;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JTextArea ErrorDisplay;
    private javax.swing.JPasswordField Password;
    private javax.swing.JPasswordField PasswordCheck;
    private javax.swing.JTextField Phone;
    private javax.swing.JButton SignUpB;
    private javax.swing.JTextField Username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
