package uk.ac.cam.cl.dgk27.stateful;

import javafx.scene.Scene;

public abstract class State {
    String name;
    protected Scene scene;
    protected boolean enabled = false;

    public State(String name) {
        this.name = name;
        initialise();
        StateManager.addToMap(this);
    }

    /**
     * Is called when the state is initially created
     */
    protected abstract void initialise();

    /**
     * Is called when the state is enabled (but not when state is already enabled)
     */
    protected abstract void enable();

    /**
     * Is called when the state is disabled (but not when state is already disabled)
     */
    protected abstract void disable();

    /**
     * Is called when state is updated
     */
    public abstract void update();

    /**
     * @return Returns the scene object
     */
    public Scene getScene(){
        return scene;
    }
}
