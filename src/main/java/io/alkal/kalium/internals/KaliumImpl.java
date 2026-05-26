package io.alkal.kalium.internals;

import io.alkal.kalium.Kalium;
import io.alkal.kalium.annotations.On;
import io.alkal.kalium.exceptions.KaliumException;
import io.alkal.kalium.interfaces.KaliumQueueAdapter;
import io.alkal.kalium.internals.utils.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ziv Salzman
 * Created on 20-Jan-2019
 */
public class KaliumImpl implements Kalium, QueueListener {

    private static final Logger logger = Logger.getLogger(KaliumImpl.class.getName());

    private List<Object> reactions;

    private Map<String, Object> reactionIdToReactionMap = new HashMap<>();

    private Map<String, Map<Class, List<Method>>> reactionIdToObjectTypeToMethodMap = new HashMap<>();

    private KaliumQueueAdapter queueAdapter;

    @Override
    public void start() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void addReaction(Object reaction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void post(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setQueueAdapter(KaliumQueueAdapter queueAdapter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void onObjectReceived(String reactionId, Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Map<String, Collection<Class>> getReactionIdsToObjectTypesMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> void on(Class<T> tClass, Consumer<T> consumer) throws KaliumException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> void on(Class<T> tClass, Consumer<T> consumer, String reactionId) throws KaliumException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void validateOnInputs(Class tClass, Consumer consumer, String reactionId) throws KaliumException {
        KaliumException exception = null;
        if (tClass == null) {
            exception = new KaliumException(".on(...) cannot use null class!");
        } else if (consumer == null) {
            exception = new KaliumException(".on(...) cannot use null reaction lambda expression!");
        } else if (reactionId == null || reactionId.isEmpty()) {
            exception = new KaliumException(".on(...) cannot use null or empty reactionId!");
        }
        if (exception != null) {
            logger.log(Level.WARNING, exception.getMessage(), exception);
            throw exception;
        }
    }

    private void addReactionInternal(String reactionId, Object reaction) {
        Class reactionClass = reaction.getClass();
        Map<Class, List<Method>> objectTypeToHandlersMap = new HashMap<>();
        reactionIdToObjectTypeToMethodMap.put(reactionId, objectTypeToHandlersMap);
        reactionIdToReactionMap.put(reactionId, reaction);
        ReflectionUtils.getMethodsAnnotatedWithOn(reactionClass).forEach(method -> {
            assert method.getParameterCount() == 1;
            Class parameter = method.getParameterTypes()[0];
            List<Method> handlers = objectTypeToHandlersMap.get(parameter);
            if (handlers == null) {
                handlers = new LinkedList<>();
                objectTypeToHandlersMap.put(parameter, handlers);
            }
            handlers.add(method);
        });
    }
}
