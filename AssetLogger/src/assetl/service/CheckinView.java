/*
 * CheckinView.java
 *
 * Created on Mar 14, 2010, 3:43:17 PM
 */

package assetl.service;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import assetl.system.AssetView;
import assetl.system.AssetLControl;

/**
 * Provides an interface to check in a laptop
 *
 * @author Devin Doman
 */
public class CheckinView
        extends AssetView
{

    /**
     * Creates a user interface taking a controller
     * as a parameter.
     *
     * @param pControl The controller for this view
     */
    public CheckinView(AssetLControl pControl)
    {
        super(pControl, "Checkin");
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        ckInTxtFld = new javax.swing.JTextField();
        ckInBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ckInTxtFld.setText("42");
        ckInTxtFld.setName("ckInTxtFld"); // NOI18N

        ckInBtn.setText("Check In");
        ckInBtn.setName("ckInBtn"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(ckInTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(ckInBtn)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(ckInTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(ckInBtn)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>


    // Variables declaration - do not modify
    private javax.swing.JButton ckInBtn;
    private javax.swing.JTextField ckInTxtFld;
    // End of variables declaration


    /**
     * Updates the view's display of the model. This function will be
     * called every time the model's state changes.
     */
    public void updateData()
    {
        // TODO: get from the model the status of the checkin for the asset
    }

    /**
     * Grabs all pertainent data from the fields and sets them in
     * a DataPacket object
     */
    public void grabDataPacket()
    {
        //get the laptop data
        mData.setAsset(mControl.getAsset(ckInTxtFld.getText()));
    }

    /**
     * Enables functionality passed in for this view.
     */
    public void enable(String pFunction)
    {
        //enable the function for mCkInBtn
        enable(pFunction, ckInBtn);

        ckInBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                grabDataPacket();
                System.err.println("Check in Laptop: " + 
                                   mData.getAsset().getID() + " " +
                                   mData.getAsset().getType());
            }
        });
    }

    /**
     * The entry point for this view
     */
    public void run()
    {

    }
}

