/*
 * idView.java
 *
 * Created on Mar 21, 2010, 6:53:41 PM
 */
package assetl.desktop;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import assetl.system.AssetLControl;
import assetl.system.DataPacket;
import assetl.system.StringPacket;

/**
 * Prompts for I-number or Laptop number (if they are an admin)
 *
 * @author Devin Doman
 */
public class IDView
   extends AssetView
{
   /**
    * Default Constructor
    */
   public IDView()
   {
      this(null);
   }

   /**
    * Creates new form idView
    */
   public IDView(AssetLControl pControl)
   {
      super(pControl);
      initComponents();
      mGroup = new ButtonGroup();
      mGroup.add(mScheduleRadio);
      mGroup.add(mCheckInRadio);

      // Add listeners

      mScheduleRadio.addActionListener(new ActionListener()
      {
         /**
          * Enable the ability to load a person in
          * the next view
          */
         public void actionPerformed(ActionEvent ev)
         {
            enable("LoadPerson");
         }
      });

      mCheckInRadio.addActionListener(new ActionListener()
      {
         /**
          * Enable the ability to checkin a laptop
          */
         public void actionPerformed(ActionEvent ev)
         {
            enable("Checkin");
         }
      });
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mTxtFld = new javax.swing.JTextField();
        mScheduleRadio = new javax.swing.JRadioButton();
        mCheckInRadio = new javax.swing.JRadioButton();
        mBtn = new javax.swing.JButton();
        mLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mTxtFld.setName("mTxtFld"); // NOI18N

        mScheduleRadio.setText("Schedule");
        mScheduleRadio.setName("mScheduleRadio"); // NOI18N

        mCheckInRadio.setText("Check In");
        mCheckInRadio.setName("mCheckInRadio"); // NOI18N

        mBtn.setText("Execute");
        mBtn.setName("mBtn"); // NOI18N

        mLabel.setText("Enter");
        mLabel.setName("mLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mScheduleRadio)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mCheckInRadio)
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mBtn)
                            .addComponent(mLabel))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(mLabel)
                        .addGap(31, 31, 31)
                        .addComponent(mTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(mBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(mScheduleRadio)
                        .addGap(18, 18, 18)
                        .addComponent(mCheckInRadio)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mBtn;
    private javax.swing.JRadioButton mCheckInRadio;
    private javax.swing.JLabel mLabel;
    private javax.swing.JRadioButton mScheduleRadio;
    private javax.swing.JTextField mTxtFld;
    // End of variables declaration//GEN-END:variables
   private ButtonGroup mGroup;

   /**
    * Grabs all pertainent data from the fields and sets them in
    * a DataPacket object. This DataPacket is then returned by the
    * method.
    *
    * @param pFunction The function that needs the DataPacket
    * @return The DataPacket that has been set
    */
   public DataPacket grabDataPacket(String pFunction)
   {
      //Send the string in a packet to the controller
      mPacket = new StringPacket(mTxtFld.getText());
      return mPacket;
   }

   /**
    * Gets any needed data from the model
    */
   public void updateData()
   {
      // There is nothing to update for this view
   }

   /**
    * Enables functionality passed in for this view.
    */
   public void enable(String pFunction)
   {
      enable(pFunction, mBtn);
   }

   /**
    * The entry point for this view
    */
   public void run()
   {
      mScheduleRadio.setSelected(true);
      enable("LoadPerson");
   }
}
