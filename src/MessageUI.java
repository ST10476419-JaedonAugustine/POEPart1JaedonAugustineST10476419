
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author jaedon
 */
public class MessageUI extends javax.swing.JFrame
{

    //Creates new form MessageUI
     
    public MessageUI()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        SignOut = new javax.swing.JButton();
        SentMessage = new javax.swing.JButton();
        Send = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(500, 300, 400, 350));
        setMinimumSize(new java.awt.Dimension(400, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SignOut.setText("Sign out");
        SignOut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SignOutActionPerformed(evt);
            }
        });
        getContentPane().add(SignOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 110, 40));

        SentMessage.setText("View sent messages");
        SentMessage.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SentMessageActionPerformed(evt);
            }
        });
        getContentPane().add(SentMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, 50));

        Send.setText("Send message");
        Send.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SendActionPerformed(evt);
            }
        });
        getContentPane().add(Send, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, 50));

        jLabel1.setText("What would you like to do today?");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, 50));

        jLabel2.setText("Welcome to Quickchat");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 17, 190, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Global objects to access login status and return to the start page
    LogInManager LMobj = new LogInManager();
    StartPage Spobj = new StartPage();

    // Global object to access view message function.
    ViewMessage VMobj = new ViewMessage();

    // takes user back to the StartPage
    private void SignOutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SignOutActionPerformed
    {//GEN-HEADEREND:event_SignOutActionPerformed
        Spobj.setVisible(true);
    }//GEN-LAST:event_SignOutActionPerformed

    private void SentMessageActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SentMessageActionPerformed
    {//GEN-HEADEREND:event_SentMessageActionPerformed
        VMobj.setVisible(true);
    }//GEN-LAST:event_SentMessageActionPerformed

    private void SendActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SendActionPerformed
    {//GEN-HEADEREND:event_SendActionPerformed
        // ask user for how many messages to send
        String input = JOptionPane.showInputDialog("How many messages would you like to send?");
        if (input == null)
        {
            return;
        }

        int numMessages;
        try
        {
            numMessages = Integer.parseInt(input);
        } catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            return;
        }

        int sentCount = 0;

        for (int i = 1; i <= numMessages; i++)
        {
            String recipient = JOptionPane.showInputDialog(this, "Enter recipient number (e.g. +27712345678):");
            if (recipient == null)
            {
                break;
            }

            String messageText = JOptionPane.showInputDialog(this, "Enter message (max 250 characters):");
            if (messageText == null)
            {
                break;
            }

            // Create message manager object
            MessageManager manager = new MessageManager(i, recipient, messageText);

            if (!manager.checkRecipientCell())
            {
                JOptionPane.showMessageDialog(this, "Invalid recipient number. Must start with '+' and max 15 chars.");
                i--;
                continue;
            }

            if (!manager.checkMessageLength())
            {
                JOptionPane.showMessageDialog(this, "Message too long. Max 250 characters.");
                i--;
                continue;
            }

            String[] options =
            {
                "Send", "Discard", "Store"
            };
            int action = JOptionPane.showOptionDialog(this,
                    "Choose what to do with this message:",
                    "Action",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (action == 0)
            { // Send
                JOptionPane.showMessageDialog(this, manager.printMessage());
                sentCount++;
            } else if (action == 2)
            {
                manager.storeMessage();
                sentCount++;
            } else
            {
                JOptionPane.showMessageDialog(this, "Message discarded.");
            }
        }

        JOptionPane.showMessageDialog(this, "Total messages sent/stored: " + sentCount);

    }//GEN-LAST:event_SendActionPerformed

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
            java.util.logging.Logger.getLogger(MessageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MessageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MessageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MessageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MessageUI().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Send;
    private javax.swing.JButton SentMessage;
    private javax.swing.JButton SignOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
