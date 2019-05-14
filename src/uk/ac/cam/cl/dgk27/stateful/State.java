package uk.ac.cam.cl.dgk27.stateful;

import java.util.HashMap;
import java.util.Map;

public abstract class State {
    String name;
    boolean enabled = false;
    /**
     * Stores all created states
     */
    private static Map<String, State> states = new HashMap<>();

    private static boolean addToMap(State s) {
        if (states.containsKey(s.name))
            return false;
        states.put(s.name, s);
        return true;
    }

    public static boolean enable(String name) {
        try {
            return states.get(name).enable();
        } catch (Exception e) {
            return false;
        }
    }

    public State(String name) {
        this.name = name;
        addToMap(this);
    }

    /**
     * @return Returns false if state was already enabled
     */
    public boolean enable() {
        if (enabled)
            return false;
        enabled();
        return (enabled = true);
    }

    /**
     * @return Returns false if state was already disabled
     */
    public boolean disable() {
        if (!enabled)
            return false;
        disabled();
        return !(enabled = false);
    }

    /**
     * Is called when the state is initially created
     */
    public abstract void initialise();

    /**
     * Is called when the state is enabled (but not when state is already enabled)
     */
    public abstract void enabled();

    /**
     * Is called when the state is disabled (but not when state is already disabled)
     */
    public abstract void disabled();

    /**
     * Is called when state is updated
     */
    public abstract void update();
}
