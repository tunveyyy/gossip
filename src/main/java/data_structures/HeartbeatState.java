package data_structures;

import java.io.Serializable;

public class HeartbeatState implements Serializable {
    private int generation; /* time when Node was launched */
    private int hbValue;    /* heartbeat value */

    public HeartbeatState(int generation) {
        this.generation = generation;
        this.hbValue = 0;
    }
    public HeartbeatState(int generation, int hbValue) {
        this.generation = generation;
        this.hbValue = hbValue;
    }

    public int getGeneration() {
        return this.generation;
    }

    public int getHeartbeatValue() {
        return this.hbValue;
    }

    public void updateHeartBeat() { 
        this.hbValue = VersionGenerator.getNextVersion(); 
    }

    /* newGeneration is used whenever node turns to NORMAL STATUS */
    public void newGeneration() {
        this.generation += 1;
    }

    public void setGeneration() {
        this.generation = (int)System.nanoTime();
    }

    public String toString() {
        return String.format("Heartbeat: generation = %d, hbValue = %d", this.generation, this.hbValue);
    }
}