package uk.ac.cam.cl.dgk27.stateful;

import javafx.scene.Scene;

public abstract class State {
    String name;
    protected Scene scene;
    private boolean enabled = false;

    public State(String name) {
        this.name = name;
        initialise();
        StateManager.addToMap(this);
    }

    /**
     * Is called when the state is initially created
     */
    abstract void initialise();

    /**
     * Is called when the state is enabled (but not when state is already enabled)
     */
    abstract void enable();

    /**
     * Is called when the state is disabled (but not when state is already disabled)
     */
    abstract void disable();

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
