package co.m1ke.basic.events;

import co.m1ke.basic.events.error.ListenerAlreadyRegisteredException;
import co.m1ke.basic.events.interfaces.Event;
import co.m1ke.basic.events.interfaces.EventListener;
import co.m1ke.basic.logger.Logger;
import co.m1ke.basic.utils.Lang;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EventExecutor {

    private static final int PRE = -1;
    private static final int ALL = 0;
    private static final int POST = 1;

    private final Map<Class<? extends Event>, Collection<EventHandler>> bindings;
    private final Set<EventListener> registeredListeners;

    private Logger logger;

    private boolean debug;

    public EventExecutor(boolean debug) {
        this.bindings = new HashMap<>();
        this.registeredListeners = new HashSet<>();
        this.debug = debug;
        this.logger = new Logger("Events");
        logger.info("Event Manager ready.");
    }

    public List<EventHandler> getListenersFor(Class<? extends Event> clazz) {
        if (!this.bindings.containsKey(clazz))
            return new ArrayList<>();
        return new ArrayList<>(this.bindings.get(clazz));
    }

    public <T extends Event> T emit(T event, int i) {
        Collection<EventHandler> handlers = this.bindings.get(event.getClass());
        if (handlers == null) {
            logger.debug(event.getClass().getSimpleName() + " was called but it has no handlers.", this.debug);
            return event;
        }
        for (EventHandler handler : handlers) {
            if (i == PRE && handler.getPriority() >= 0)
                continue;
            if (i == POST && handler.getPriority() < 0)
                continue;
            handler.execute(event);
        }
        return event;
    }
    public <T extends Event> T emit(T event) {
        return this.emit(event, ALL);
    }

    public void registerListener(final EventListener listener) {
        if (registeredListeners.contains(listener)) {
            throw new ListenerAlreadyRegisteredException();
        }
        Method[] methods = listener.getClass().getDeclaredMethods();
        this.registeredListeners.add(listener);
        for (final Method method : methods) {
            Event annotation = method.getAnnotation(Event.class);
            if (annotation == null)
                continue;

            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length != 1)
                continue;

            Class<?> param = parameters[0];

            if (!method.getReturnType().equals(void.class)) {
                logger.severe("Event Handler " + Lang.CYAN + listener.getClass().getSimpleName() + "." + method.getName() + "()" + Lang.RESET + " does not return void. (Returns " + method.getReturnType().getSimpleName() + ")");
                continue;
            }

            if (Event.class.isAssignableFrom(param)) {
                @SuppressWarnings("unchecked") Class<? extends Event> realParam = (Class<? extends Event>) param;
                if (!this.bindings.containsKey(realParam)) {
                    this.bindings.put(realParam, new TreeSet<>());
                }
                Collection<EventHandler> eventHandlersForEvent = this.bindings.get(realParam);
                eventHandlersForEvent.add(createEventHandler(listener, method, annotation));
                logger.info("Listener " + Lang.CYAN + listener.getClass().getSimpleName() + "." + method.getName() + "() " + Lang.RESET + "activated.", this.debug);
            }
        }
    }

    private EventHandler createEventHandler(final EventListener listener, final Method method, final Event annotation) {
        return new EventHandler(listener, method, annotation);
    }

    public void unregisterAll(EventListener listener) {
        for (Map.Entry<Class<? extends Event>, Collection<EventHandler>> ee : bindings.entrySet()) {
            ee.getValue().removeIf(curr -> curr.getListener() == listener);
        }
        this.registeredListeners.remove(listener);
    }

    public void unregisterAll() {
        this.bindings.clear();
        this.registeredListeners.clear();
    }

    public Map<Class<? extends Event>, Collection<EventHandler>> getBindings() {
        return new HashMap<>(bindings);
    }

    public Set<EventListener> getRegisteredListeners() {
        return new HashSet<>(registeredListeners);
    }

}
