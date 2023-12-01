package sun.awt.X11;

public interface  XMSelectionListener {

   /*
    * This method is called when the owner changes
    */
   public void ownerChanged(int screen, XMSelection sel, long newOwner, long data, long timestamp);

   /*
    * This method is called when the owner dies
    */
   public void ownerDeath(int screen, XMSelection sel, long deadOwner);

   /*
    * This method is for selection change notification
    *
    * This method will only get called if you use the default constructor
    * or expilicitly specify PropertyChangeMask.
    */

   public void selectionChanged(int screen, XMSelection sel, long owner, XPropertyEvent event);

}
