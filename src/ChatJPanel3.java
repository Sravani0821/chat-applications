/**
 *
/**
 * @author Rohit Ahuja
 * rohitahuja@email.com
 * 9893075987
 */
package ChatClient;

import javax.swing.*;
import java.util.*;
import java.net.*;

public class ChatJPanel3 extends javax.swing.JPanel implements Runnable {

    public ChatJPanel3() {
        initComponents();
    }

    public ChatJPanel3(String un, DatagramSocket ds) {
        this.un = un;
        initComponents();
        personals = new Vector();
        communicator = new Thread(this);
        try {
            this.ds = ds;
            communicator.start();
            byte b[] = "USERS".getBytes();
            InetAddress ia = InetAddress.getByName(MainConstants.SERVER_IP);
            DatagramPacket dp = new DatagramPacket(b, b.length, ia, MainConstants.COMMUNICATION_PORT);
            ds.send(dp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to List to Port");
        }

    }

    public void run() {

        while (true) {
            byte b[] = new byte[256];
            DatagramPacket dp = new DatagramPacket(b, b.length);
            try {
                ds.receive(dp);
                String str = new String(dp.getData(), 0, dp.getLength());
                str = str.trim();
                System.out.println(str);
                if (str.startsWith("USERS")) {
                    String s1 = str.substring(str.indexOf(":") + 1);
                    if (!dm.contains(s1)) {
                        dm.addElement(s1);
                    }
                }
                if (str.startsWith("REMOVE")) {
                    String username = str.substring(str.indexOf(":") + 1);
                    dm.removeElement(username);
                }
                if (str.startsWith("SENDALL")) {
                    String text = str.substring(str.indexOf(":") + 1);
                    jTextArea1.append(text + "\n");
                    jTextArea1.setCaretPosition(jTextArea1.getText().length());
                }
                if (str.startsWith("PERSONAL")) {
                    char flag = 'n';
                    int aa = str.indexOf(":");
                    int bb = str.indexOf(":", aa + 1);
                    int cc = str.indexOf(":", bb + 1);
                    String from = str.substring(aa + 1, bb);
                    String to = str.substring(bb + 1, cc);
                    String msg = str.substring(cc + 1);
                    for (int i = 0; i < personals.size(); i++) {
                        PersonalUsers p = (PersonalUsers) personals.elementAt(i);
                        String un = p.getUsername();
                        JFrame f = p.getFrame();
                        if (un.equals(from)) {
                            ChatJPanel4 c4 = (ChatJPanel4) f.getContentPane();
                            c4.showMessage(msg);
                            f.setFocusable(true);
                            flag = 'y';
                        }
                    }
                    if (flag == 'n') {
                        JFrame f = new JFrame("Chat : " + from);
                        ChatJPanel4 c4 = new ChatJPanel4(un, from, ds);
                        f.setContentPane(c4);
                        c4.showMessage(msg);
                        PersonalUsers p = new PersonalUsers();
                        p.setUsername(from);
                        p.setFrame(f);
                        personals.add(p);
                        f.setBounds(10, 10, 400, 200);
                        f.setVisible(true);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Unable to Communicate" + e.toString());
            }
        }
    }

    class PersonalUsers {

        private String username;
        public JFrame frame;

        public PersonalUsers() {
        }

        public PersonalUsers(String username, JFrame frame) {
            this.username = username;
            this.frame = frame;
        }

        public JFrame getFrame() {
            return frame;
        }

        public String getUsername() {
            return username;
        }

        public void setFrame(JFrame frame) {
            this.frame = frame;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        dm = new DefaultListModel();
        jList1 = new javax.swing.JList(dm);
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(227, 225, 225));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Client", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        setLayout(null);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Send To All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(200, 350, 150, 30);

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(360, 350, 150, 30);

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setText("Personal Chat");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(40, 350, 150, 30);

        jPanel1.setBackground(new java.awt.Color(163, 165, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1);
        jPanel1.setBounds(10, 40, 220, 300);

        jPanel2.setBackground(new java.awt.Color(163, 165, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Massage", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2);
        jPanel2.setBounds(240, 40, 310, 300);
        add(jTextField1);
        jTextField1.setBounds(60, 390, 430, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String ss = "REMOVE:" + un;
            byte b[] = ss.getBytes();
            InetAddress ia = InetAddress.getByName(MainConstants.SERVER_IP);
            DatagramPacket dp = new DatagramPacket(b, b.length, ia, MainConstants.COMMUNICATION_PORT);
            ds.send(dp);

            JOptionPane.showMessageDialog(this, "You are Disconnected");
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to List to Port");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String ss = "SENDALL:" + un + ":" + jTextField1.getText();
            byte b[] = ss.getBytes();
            InetAddress ia = InetAddress.getByName(MainConstants.SERVER_IP);
            DatagramPacket dp = new DatagramPacket(b, b.length, ia, MainConstants.COMMUNICATION_PORT);
            ds.send(dp);
            jTextField1.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to List to Port");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int index = jList1.getSelectedIndex();
        String seluser = (String) dm.elementAt(index);
        if (!un.equals(seluser)) {
            for (int i = 0; i < personals.size(); i++) {
                PersonalUsers p = (PersonalUsers) personals.elementAt(i);
                String un = p.getUsername();
                JFrame f = p.getFrame();
                if (un.equals(seluser)) {
                    f.setFocusable(true);
                    return;
                }
            }
            JFrame f = new JFrame("Chat : " + seluser);
            f.setContentPane(new ChatJPanel4(un, seluser, ds));
            PersonalUsers p = new PersonalUsers();
            p.setUsername(seluser);
            p.setFrame(f);
            personals.add(p);
            f.setBounds(10, 10, 400, 200);
            f.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setContentPane(new ChatJPanel3());
        f.setBounds(10, 10, 580, 480);
        f.setVisible(true);
    }
    Thread communicator;
    DatagramSocket ds;
    DefaultListModel dm;
    String un;
    Vector personals;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
/**
* Vedisoft : Java - Module 1 "Desktop Technologies"
*
*
*   Vedisoft Software & Education Services Pvt. Ltd.
*   Plot No. 275, Zone II, M.P. Nagar,
*   Bhopal.
*   Phone : 0755 - 4076207, 4076208
*   Email : contact@vedisoft.com
*   Web : www.vedisoft.com
*/