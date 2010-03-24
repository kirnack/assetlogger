/*
 * LoginView.java
 *
 * Created on Mar 21, 2010, 6:52:40 PM
 */

package assetl.desktop;

import assetl.system.AssetLControl;
import assetl.system.User;

/**
 * Allows user to login to the application
 *
 * @author Devin Doman
 */
public class LoginView 
        extends AssetView
{

    /** 
     * Creates new form LoginView
     */
    public LoginView(AssetLControl pControl)
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

        mLoginLbl = new javax.swing.JLabel();
        mPicLbl = new javax.swing.JLabel();
        mUserLbl = new javax.swing.JLabel();
        mPwrdLbl = new javax.swing.JLabel();
        mUserTxtFld = new javax.swing.JTextField();
        mPwrdTxtFld = new javax.swing.JPasswordField();
        mLogInBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mLoginLbl.setText("Log In");
        mLoginLbl.setName("mLoginLbl"); // NOI18N

        mPicLbl.setText("A picture can go here");
        mPicLbl.setName("mPicLbl"); // NOI18N

        mUserLbl.setText("User:");
        mUserLbl.setName("mUserLbl"); // NOI18N

        mPwrdLbl.setText("Password:");
        mPwrdLbl.setName("mPwrdLbl"); // NOI18N

        mUserTxtFld.setName("mUserTxtFld"); // NOI18N

        mPwrdTxtFld.setName("mPwrdTxtFld"); // NOI18N

        mLogInBtn.setText("Log In");
        mLogInBtn.setName("mLogInBtn"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mPicLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(mPwrdLbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(mUserLbl)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mLoginLbl)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(mPwrdTxtFld, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mUserTxtFld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addComponent(mLogInBtn)))
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(mPicLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(mLoginLbl)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mUserLbl)
                            .addComponent(mUserTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mPwrdLbl)
                            .addComponent(mPwrdTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(mLogInBtn)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mLogInBtn;
    private javax.swing.JLabel mLoginLbl;
    private javax.swing.JLabel mPicLbl;
    private javax.swing.JLabel mPwrdLbl;
    private javax.swing.JPasswordField mPwrdTxtFld;
    private javax.swing.JLabel mUserLbl;
    private javax.swing.JTextField mUserTxtFld;
    // End of variables declaration//GEN-END:variables


    /**
     * Gets the necessary data from the text fields to log in
     */
    public void grabDataPacket()
    {
        System.out.println(mPwrdTxtFld.getPassword());
        // Grab the user data from the text fields
        mData.setUser(new User(mUserTxtFld.getText(),
                      new String(mPwrdTxtFld.getPassword()), false));
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
        enable(pFunction, mLogInBtn);
    }

    /**
     * The entry point for this view
     */
    public void run()
    {
        hideMenuBar();
        enable("LogIn");
    }

}