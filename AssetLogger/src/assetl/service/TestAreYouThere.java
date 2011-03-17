/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TestAreYouThere.java
 *
 * Created on Jan 22, 2011, 3:40:28 PM
 */
package assetl.service;

import java.net.InetAddress;
import javax.swing.DefaultListModel;


/**
 *
 * @author brogers3
 */
public class TestAreYouThere extends javax.swing.JFrame
{
   Client mClient;
   DefaultListModel mList = new DefaultListModel();

   class findHost extends Thread
   {
      private CheckHost mClient;
      private String mHost;

      public findHost(String pHost, CheckHost pClient)
      {
         mHost = pHost;
         mClient = pClient;
      }

      @Override
      public void run()
      {
 //        String[] last = mHost.split(".");
         System.err.print(mHost);

               String response = mClient.postData("areYouThere", "",
                  mHost);

               if (response != null)
               {
                  mList.addElement(mHost + " = " + response);
                  
                  response = "";
               }
               jProgressBar1.setValue(jProgressBar1.getValue() + 1);
      }

   }

   class findHosts extends Thread
   {
      @Override
      public void run()
      {
         mList = new DefaultListModel();
         jList1.setModel(mList);
         try
         {
            jProgressBar1.setMaximum(254);
            jProgressBar1.setValue(1);
            CheckHost client = new CheckHost();
            String localhost = InetAddress.getLocalHost().toString().split("/")[1];
            String host = localhost.substring(0, localhost.lastIndexOf("."));
            for (int x = 1; x < 255; x++)
            {
               System.err.println(x);
               new Thread(new findHost(host + "." + x, client)).start();
               
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   /** Creates new form TestAreYouThere */
   public TestAreYouThere()
   {
      initComponents();
      mClient = Client.getInstance();
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      jButton1 = new javax.swing.JButton();
      jProgressBar1 = new javax.swing.JProgressBar();
      jScrollPane2 = new javax.swing.JScrollPane();
      jList1 = new javax.swing.JList();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      jLabel1.setText("Response");
      jLabel1.setName("jLabel1"); // NOI18N

      jButton1.setText("Test Server");
      jButton1.setName("mTest"); // NOI18N
      jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton1MouseClicked(evt);
         }
      });

      jProgressBar1.setName("jProgressBar1"); // NOI18N

      jScrollPane2.setName("jScrollPane2"); // NOI18N

      jList1.setModel(mList);
      jList1.setName("jList1"); // NOI18N
      jScrollPane2.setViewportView(jList1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(92, 92, 92)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jLabel1)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
               .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
            .addGap(111, 111, 111))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(33, 33, 33)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(64, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton1MouseClicked
    {//GEN-HEADEREND:event_jButton1MouseClicked
       new findHosts().start();
    }//GEN-LAST:event_jButton1MouseClicked

   /**
    * @param args the command line arguments
    */
   public static void main(String args[])
   {
      java.awt.EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            new TestAreYouThere().setVisible(true);
         }
      });
   }
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jButton1;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JList jList1;
   private javax.swing.JProgressBar jProgressBar1;
   private javax.swing.JScrollPane jScrollPane2;
   // End of variables declaration//GEN-END:variables
}
