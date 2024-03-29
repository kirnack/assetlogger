/*
 * AddAssetView.java
 *
 * Created on Mar 27, 2010, 4:41:00 PM
 */
package assetl.desktop;

import assetl.system.AssetLControl;
import assetl.system.DataPacket;
import assetl.system.AssetPacket;

/**
 * Allows an admin to add Assets to the model
 *
 * @author Devin Doman
 */
public class AddAssetView
   extends AssetView
{
   /**
    * Constructor for the user interface
    *
    * @param pControl The controller for this view
    */
   public AddAssetView(AssetLControl pControl)
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

      mAddBtn = new javax.swing.JButton();
      mBarCodeTxtFld = new javax.swing.JTextField();
      mBarCodeLbl = new javax.swing.JLabel();
      mLaptopNumTxtFld = new javax.swing.JTextField();
      mLaptopNumLbl = new javax.swing.JLabel();
      mMakeLbl = new javax.swing.JLabel();
      mSerialNumTxtFld = new javax.swing.JTextField();
      mSerialLbl = new javax.swing.JLabel();
      mMaintainChkBox = new javax.swing.JCheckBox();
      mModelTxtFld = new javax.swing.JTextField();
      mModelLbl = new javax.swing.JLabel();
      mMakeTxtFld = new javax.swing.JTextField();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      mAddBtn.setText("Add Asset");
      mAddBtn.setName("mAddBtn"); // NOI18N

      mBarCodeTxtFld.setName("mBarCodeTxtFld"); // NOI18N

      mBarCodeLbl.setText("Barcode:");
      mBarCodeLbl.setName("mBarCodeLbl"); // NOI18N

      mLaptopNumTxtFld.setName("mLaptopNumTxtFld"); // NOI18N

      mLaptopNumLbl.setText("Laptop Number:");
      mLaptopNumLbl.setName("mLaptopNumLbl"); // NOI18N

      mMakeLbl.setText("Make:");
      mMakeLbl.setName("mMakeLbl"); // NOI18N

      mSerialNumTxtFld.setName("mSerialNumTxtFld"); // NOI18N

      mSerialLbl.setText("Serial #:");
      mSerialLbl.setName("mSerialLbl"); // NOI18N

      mMaintainChkBox.setText("In Maintenance");
      mMaintainChkBox.setName("mMaintainChkBox"); // NOI18N

      mModelTxtFld.setName("mModelTxtFld"); // NOI18N

      mModelLbl.setText("Model:");
      mModelLbl.setName("mModelLbl"); // NOI18N

      mMakeTxtFld.setName("mMakeTxtFld"); // NOI18N

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(46, 46, 46)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(mBarCodeTxtFld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(mBarCodeLbl, javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(mMakeLbl)
                     .addComponent(mSerialLbl)
                     .addComponent(mModelLbl))
                  .addGap(33, 33, 33)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                     .addComponent(mMakeTxtFld, javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(mModelTxtFld, javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(mSerialNumTxtFld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                     .addComponent(mMaintainChkBox, javax.swing.GroupLayout.Alignment.LEADING))
                  .addGap(4, 4, 4)))
            .addGap(72, 72, 72)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(mLaptopNumTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(mLaptopNumLbl)
               .addComponent(mAddBtn))
            .addContainerGap(66, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(mBarCodeLbl)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(mBarCodeTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(mLaptopNumLbl)
                  .addGap(28, 28, 28)
                  .addComponent(mLaptopNumTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(mMakeLbl)
               .addComponent(mMakeTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(mModelTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(mModelLbl))
            .addGap(26, 26, 26)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(mSerialLbl)
               .addComponent(mSerialNumTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(mAddBtn))
            .addGap(18, 18, 18)
            .addComponent(mMaintainChkBox)
            .addGap(37, 37, 37))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents
   // Variables declaration - do not modify//GEN-BEGIN:variables
   /**
   * Button to add a laptop with
   */
   private javax.swing.JButton mAddBtn;
   /**
   * The label for the bar code
   */
   private javax.swing.JLabel mBarCodeLbl;
   /**
   * The text field for the bar code
   */
   private javax.swing.JTextField mBarCodeTxtFld;
   /**
   * The label for the laptop number
   */
   private javax.swing.JLabel mLaptopNumLbl;
   /**
   * The text field for the laptop number
   */
   private javax.swing.JTextField mLaptopNumTxtFld;
   /**
   * The check box to indicate if the laptop is in maintenance
   */
   private javax.swing.JCheckBox mMaintainChkBox;
   /**
   * The label for the make
   */
   private javax.swing.JLabel mMakeLbl;
   /**
   * The text field for the make
   */
   private javax.swing.JTextField mMakeTxtFld;
   /**
   * The label for the model
   */
   private javax.swing.JLabel mModelLbl;
   /**
   * The text field for the model
   */
   private javax.swing.JTextField mModelTxtFld;
   /**
   * The label for the serial number
   */
   private javax.swing.JLabel mSerialLbl;
   /**
   * The text field for the serial number
   */
   private javax.swing.JTextField mSerialNumTxtFld;
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
      String barCode = mBarCodeTxtFld.getText();
      String laptopNum = mLaptopNumTxtFld.getText();
      String make = mMakeTxtFld.getText();
      String model = mModelTxtFld.getText();
      String serialNum = mSerialNumTxtFld.getText();
      boolean inMaintain = mMaintainChkBox.isSelected();

      //Generate a laptop object
      Laptop laptop = ModelObjectGenerator.generateLaptop(barCode, laptopNum,
         make, model, serialNum, inMaintain);

      return new AssetPacket(laptop.getAsset());
   }

   /**
    * Enables functionality passed in for this view.
    */
   public void enable(String pFunction)
   {
      //Enable a FunctionListener for the button
      enable(pFunction, mAddBtn, "Function");
   }

   /**
    * The entry point for this view
    */
   public void run()
   {
      enable("AddAsset");
   }
}
