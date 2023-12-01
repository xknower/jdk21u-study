package sun.text.resources;

import java.util.ResourceBundle;
import sun.util.resources.BreakIteratorResourceBundle;

public class BreakIteratorResources extends BreakIteratorResourceBundle {
    @Override
    protected ResourceBundle getBreakIteratorInfo() {
        return new BreakIteratorInfo();
    }
}
