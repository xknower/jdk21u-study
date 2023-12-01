package sun.print;

import java.awt.print.PrinterJob;

public class PlatformPrinterJobProxy {

   public static PrinterJob getPrinterJob() {
       return new sun.print.PSPrinterJob();
   }
}


