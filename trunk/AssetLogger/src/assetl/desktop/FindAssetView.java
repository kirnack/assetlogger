package assetl.desktop;

import java.util.Date;
import java.util.ArrayList;
import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import firstthree.desktop.BlockLayout;
import firstthree.desktop.BlockPanel;
import javax.swing.WindowConstants;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import assetl.system.AssetLControl;
import assetl.system.DataPacket;
import assetl.system.RequestPacket;
import assetl.system.StringPacket;
import assetl.system.Asset;
import assetl.system.Request;

/**
 * Provides the ability to find and asset using JCalendar to set the dates
 * @author Devin Doman
 */
public class FindAssetView
   extends AssetView
{
   /**
    * The list model holding assets
    */
   private DefaultListModel mAssetListModel;
   /**
    * The JCalendar to select start dates with
    */
   private JCalendar mStartCal;
   /**
    * The JCalendar to select end dates with
    */
   private JCalendar mEndCal;
   /**
    * Indicates whether the start fields are hidden or not
    */
   private boolean mStartShown;
   /**
    * A block panel used for layout.
    */
   private BlockPanel mPanel;
   /**
    * A JPanel to put the start calendar in
    */
   private JPanel mStartPanel;
   /**
    * A JPanel to put the end calendar in
    */
   private JPanel mEndPanel;
   /**
    * East panel for layout.
    */
   private BlockPanel mEastPanel;
   /**
    * West panel for layout.
    */
   private BlockPanel mWestPanel;
   /**
    * The scroll pane
    */
   private JScrollPane mScrollPane;
   /**
    * Holds the list of assets
    */
   private JList mAssetList;
   /**
    * The button to find assets with
    */
   private JButton mFindBtn;
   /**
    * The button to select assets with
    */
   private JButton mSelectBtn;
   /**
    * Labels the end calendar
    */
   private JLabel mEndLbl;
   /**
    * Labels the start calendar
    */
   private JLabel mStartLbl;

   /**
    * Default constructor expecting the controller to add itself
    */
   public FindAssetView()
   {
      this(null);
   }

   /**
    * Constructor for the user interface
    *
    * @param pControl The controller for this view
    */
   public FindAssetView(AssetLControl pControl)
   {
      super(pControl);
      initComponents();
      addCompToPane();
   }

   /**
    * Initializes all the swing components for the gui
    */
   public void initComponents()
   {
      mPanel = new BlockPanel(10, true);
      mWestPanel = new BlockPanel(10, true);
      mEastPanel = new BlockPanel(10, true);

      mStartPanel = new JPanel();
      mEndPanel = new JPanel();
      mStartLbl = new JLabel();
      mEndLbl = new JLabel();
      mStartCal = new JCalendar();
      mEndCal = new JCalendar();
      mScrollPane = new JScrollPane();
      mAssetListModel = new DefaultListModel();
      mAssetList = new JList();
      mFindBtn = new JButton();
      mSelectBtn = new JButton();

      mStartLbl.setText("Start Date");
      mEndLbl.setText("End Date");
      mFindBtn.setText("Find");
      mSelectBtn.setText("Select");
      mAssetList.setModel(mAssetListModel);
      mScrollPane.setViewportView(mAssetList);
   }

   /**
    * Adds the components created to the content pane
    */
   public void addCompToPane()
   {
      mStartPanel.setLayout(new BoxLayout(mStartPanel, BoxLayout.PAGE_AXIS));
      mStartPanel.add(mStartLbl);
      mStartPanel.add(mStartCal);
      mEndPanel.setLayout(new BoxLayout(mEndPanel, BoxLayout.PAGE_AXIS));
      mEndPanel.add(mEndLbl);
      mEndPanel.add(mEndCal);
      mWestPanel.add(mStartPanel, "NW:N");
      mWestPanel.add(mFindBtn, "SE:S");
      mWestPanel.add(mEndPanel, "SW:S");
      mScrollPane.setPreferredSize(new Dimension(250, 300));
      mEastPanel.add(mScrollPane, "NE:N");
      mEastPanel.add(mSelectBtn, "SE:S");
      mPanel.add(mWestPanel, "W");
      mPanel.add(mEastPanel, "E");

      setContentPane(mPanel);

      setSize(640, 480);
      setMinimumSize(new Dimension(640, 480));
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      // add action listeners
      mFindBtn.addActionListener(new ActionListener()
      {
         /**
          * Updates the JList with assets
          */
         public void actionPerformed(ActionEvent ev)
         {
            updateData();
         }
      });
   }

   /**
    * Changes what components are shown in the view based on functionality.
    * The components will be shown if the boolean passed in is true.
    *
    * @param pShow The components will be shown if true
    */
   public void setStart(boolean pShow)
   {
      mStartLbl.setVisible(pShow);
      mStartCal.setVisible(pShow);
      mStartShown = pShow;
   }

   /**
    * Updates the views display of the model
    */
   public void updateData()
   {
      // reset the JList
      mAssetListModel.removeAllElements();

      Date start = grabStart();
      Date end = grabEnd();

      if (start != null && end != null)
      {
         ArrayList<Asset> available;
         available = (ArrayList<Asset>) mControl.getAvailableAssets(start, end);

         for (Asset asset : available)
         {
            Laptop laptop = new Laptop(asset);
            mAssetListModel.addElement(laptop);
         }
      }
   }

   /**
    * Gets the current start date
    *
    * @return The start date
    */
   public Date grabStart()
   {
      Date start = new Date();
      if (mStartShown)
      {
         start = mStartCal.getDate();
      }
      return start;
   }

   /**
    * Gets the current end date
    *
    * @return The end date
    */
   public Date grabEnd()
   {
      Date end = mEndCal.getDate();
      return end;
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
      Request req;
      ArrayList<Asset> assets = new ArrayList<Asset>();
      //Get the person id sent from ServiceView
      StringPacket tempPacket = (StringPacket) mPacket;

      //Get the selected assets
      Object[] selected = mAssetList.getSelectedValues();
      for (Object obj : selected)
      {
         Asset asset = (Asset) obj;
         assets.add(asset);
      }

      //Generate a new Request
      req = ModelObjectGenerator.generateRequest(tempPacket.getString(), assets,
         grabStart(), grabEnd());

      return new RequestPacket(req);
   }

   /**
    * Enables functionality passed in for this view.
    */
   public void enable(String pFunction)
   {
      //Enable a FunctionListener for the button
      enable(pFunction, mSelectBtn, "Function");
      setLabel(pFunction, mSelectBtn);
      setStart("Schedule".equals(pFunction));
   }

   /**
    * The entry point for this view
    */
   public void run()
   {
   }

   public static void main(String[] args)
   {
      new FindAssetView().setVisible(true);
   }
}
