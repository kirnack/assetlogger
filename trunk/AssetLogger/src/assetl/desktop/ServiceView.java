/*
 * ServiceView.java
 *
 * Created on Mar 21, 2010, 6:54:06 PM
 */

package assetl.desktop;

import assetl.system.DataPacket;
import assetl.system.DBPacket;

import assetl.system.AssetLControl;

/**
 *
 * @author Devin Doman
 */
public class ServiceView 
        extends AssetView
{

    /**
     * Constructor for the user interface
     *
     * @param pControl The controller for this view
     */
    public ServiceView(AssetLControl pControl)
    {
        super(pControl);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mNameLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mCheckoutList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        mRequestedList = new javax.swing.JList();
        mCheckoutLbl = new javax.swing.JLabel();
        mRequestedLbl = new javax.swing.JLabel();
        mReturnBtn = new javax.swing.JButton();
        mExtendBtn = new javax.swing.JButton();
        mScheduleBtn = new javax.swing.JButton();
        mCheckoutBtn = new javax.swing.JButton();
        mCancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mNameLbl.setText("Name");
        mNameLbl.setName("mNameLbl"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        mCheckoutList.setName("mCheckoutList"); // NOI18N
        jScrollPane1.setViewportView(mCheckoutList);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        mRequestedList.setName("mRequestedList"); // NOI18N
        jScrollPane2.setViewportView(mRequestedList);

        mCheckoutLbl.setText("Currently Checked out:");
        mCheckoutLbl.setName("mCheckoutLbl"); // NOI18N

        mRequestedLbl.setText("Currently Scheduled:");
        mRequestedLbl.setName("mRequestedLbl"); // NOI18N

        mReturnBtn.setText("Checkin");
        mReturnBtn.setName("mReturnBtn"); // NOI18N

        mExtendBtn.setText("Extend");
        mExtendBtn.setName("mExtendBtn"); // NOI18N

        mScheduleBtn.setText("+");
        mScheduleBtn.setName("mScheduleBtn"); // NOI18N

        mCheckoutBtn.setText("Checkout");
        mCheckoutBtn.setName("mCheckoutBtn"); // NOI18N

        mCancelBtn.setText("-");
        mCancelBtn.setName("mCancelBtn"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mScheduleBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mCancelBtn))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(mRequestedLbl)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mNameLbl)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mCheckoutLbl))))))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mCheckoutBtn)
                    .addComponent(mReturnBtn)
                    .addComponent(mExtendBtn))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(mNameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mCheckoutLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(mRequestedLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mScheduleBtn)
                    .addComponent(mCancelBtn))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(mExtendBtn)
                .addGap(26, 26, 26)
                .addComponent(mReturnBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(mCheckoutBtn)
                .addGap(117, 117, 117))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton mCancelBtn;
    private javax.swing.JButton mCheckoutBtn;
    private javax.swing.JLabel mCheckoutLbl;
    private javax.swing.JList mCheckoutList;
    private javax.swing.JButton mExtendBtn;
    private javax.swing.JLabel mNameLbl;
    private javax.swing.JLabel mRequestedLbl;
    private javax.swing.JList mRequestedList;
    private javax.swing.JButton mReturnBtn;
    private javax.swing.JButton mScheduleBtn;
    // End of variables declaration//GEN-END:variables

    /**
     * Updates the views display of the model
     */
    public void updateData()
    {

    }

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
        return new DBPacket();
    }

    /**
     * Enables functionality passed in for this view.
     */
    public void enable(String pFunction)
    {

    }

    /**
     * The entry point for this view
     */
    public void run()
    {

    }
}
