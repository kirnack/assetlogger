/*
 * FindAssetView.java
 *
 * Created on Mar 21, 2010, 6:57:14 PM
 */

package assetl.desktop;

import assetl.system.AssetLControl;

/**
 * Allows the user to find an asset they'd like to checkout or schedule
 *
 * @author Devin Doman
 */
public class FindAssetView 
        extends AssetView
{

    /**
     * Constructor for the user interface
     *
     * @param pControl The controller for this view
     */
    public FindAssetView(AssetLControl pControl)
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

        mStartMonTxtFld = new javax.swing.JTextField();
        mEndMonTxtFld = new javax.swing.JTextField();
        mStartLbl = new javax.swing.JLabel();
        mEndLbl = new javax.swing.JLabel();
        mMonthLbl = new javax.swing.JLabel();
        mStartDayTxtFld = new javax.swing.JTextField();
        mEndDayTxtFld = new javax.swing.JTextField();
        mStartYearTxtFld = new javax.swing.JTextField();
        mEndYearTxtFld = new javax.swing.JTextField();
        mDayLbl = new javax.swing.JLabel();
        mYearLbl = new javax.swing.JLabel();
        mFindBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mAssetList = new javax.swing.JList();
        mSelectBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mStartMonTxtFld.setName("mStartMonTxtFld"); // NOI18N

        mEndMonTxtFld.setName("mEndMonTxtFld"); // NOI18N

        mStartLbl.setText("Start Date");
        mStartLbl.setName("mStartLbl"); // NOI18N

        mEndLbl.setText("End Date");
        mEndLbl.setName("mEndLbl"); // NOI18N

        mMonthLbl.setText("Month");
        mMonthLbl.setName("mMonthLbl"); // NOI18N

        mStartDayTxtFld.setName("mStartDayTxtFld"); // NOI18N

        mEndDayTxtFld.setName("mEndDayTxtFld"); // NOI18N

        mStartYearTxtFld.setName("mStartYearTxtFld"); // NOI18N

        mEndYearTxtFld.setName("mEndYearTxtFld"); // NOI18N

        mDayLbl.setText("Day");
        mDayLbl.setName("mDayLbl"); // NOI18N

        mYearLbl.setText("Year");
        mYearLbl.setName("mYearLbl"); // NOI18N

        mFindBtn.setText("Find");
        mFindBtn.setName("mFindBtn"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        mAssetList.setName("mAssetList"); // NOI18N
        jScrollPane1.setViewportView(mAssetList);

        mSelectBtn.setText("Select");
        mSelectBtn.setName("mSelectBtn"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mSelectBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mStartLbl)
                                    .addComponent(mEndLbl))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(mEndMonTxtFld, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mStartMonTxtFld, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                    .addComponent(mMonthLbl, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mDayLbl)
                                    .addComponent(mStartDayTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                    .addComponent(mEndDayTxtFld))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mYearLbl)
                                    .addComponent(mStartYearTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addComponent(mEndYearTxtFld)))
                            .addComponent(mFindBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mMonthLbl)
                            .addComponent(mDayLbl)
                            .addComponent(mYearLbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mStartMonTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mStartDayTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mStartYearTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mStartLbl))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mEndMonTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mEndDayTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mEndYearTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mEndLbl))
                        .addGap(32, 32, 32)
                        .addComponent(mFindBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(mSelectBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList mAssetList;
    private javax.swing.JLabel mDayLbl;
    private javax.swing.JTextField mEndDayTxtFld;
    private javax.swing.JLabel mEndLbl;
    private javax.swing.JTextField mEndMonTxtFld;
    private javax.swing.JTextField mEndYearTxtFld;
    private javax.swing.JButton mFindBtn;
    private javax.swing.JLabel mMonthLbl;
    private javax.swing.JButton mSelectBtn;
    private javax.swing.JTextField mStartDayTxtFld;
    private javax.swing.JLabel mStartLbl;
    private javax.swing.JTextField mStartMonTxtFld;
    private javax.swing.JTextField mStartYearTxtFld;
    private javax.swing.JLabel mYearLbl;
    // End of variables declaration//GEN-END:variables

    /**
     * Updates the views display of the model
     */
    public void updateData()
    {

    }

    /**
     * Grabs all pertainent data from the fields and sets them in
     * a DataPacket object
     */
    public void grabDataPacket()
    {

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
