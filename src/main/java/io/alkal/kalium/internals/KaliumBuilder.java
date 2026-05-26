package io.alkal.kalium.internals;

import io.alkal.kalium.Kalium;
import io.alkal.kalium.exceptions.KaliumBuilderException;
import io.alkal.kalium.interfaces.KaliumQueueAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ziv Salzman
 * Created on 20-Jan-2019
 */
public class KaliumBuilder {

    private static final Logger logger = Logger.getLogger(KaliumBuilder.class.getName());

    private KaliumQueueAdapter queueAdapter = null;

    public KaliumBuilder setQueueAdapter(KaliumQueueAdapter queueAdapter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Kalium build() throws KaliumBuilderException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
