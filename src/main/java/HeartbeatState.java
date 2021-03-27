public class HeartbeatState {
    private int generation; /* time when Node was launched */
    private int hbValue;    /* heartbeat value */

    HeartbeatState(int generation) {
        this.generation = generation;
        this.hbValue = 0;
    }
    HeartbeatState(int generation, int hbValue) {
        this.generation = generation;
        this.hbValue = hbValue;
    }

    int getGeneration() {
        return this.generation;
    }

    int getHeartbeatValue() {
        return this.hbValue;
    }

    void updateHeartbeat() {
        this.hbValue = this.hbValue + 1;
    }

    /* newGeneration is used whenever node turns to NORMAL STATUS */
    void newGeneration() {
        this.generation += 1;
    }

    // TODO: setgeneration()
    /*set generation*/
    public String toString() {
        return String.format("Heartbeat: generation = %d, hbValue = %d", this.generation, this.hbValue);
    }
}
