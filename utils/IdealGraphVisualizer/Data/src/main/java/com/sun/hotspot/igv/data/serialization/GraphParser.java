package com.sun.hotspot.igv.data.serialization;

import com.sun.hotspot.igv.data.GraphDocument;
import java.io.IOException;

public interface GraphParser {
    GraphDocument parse() throws IOException;
}
