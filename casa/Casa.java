package casa;

import java.io.Serializable;

public class Casa implements Serializable {
    private int numHabitaciones;
    private int numBanos;
    private boolean tieneTrastero;

    public Casa(int numHabitaciones, int numBanos, boolean tieneTrastero) {
        this.numHabitaciones = numHabitaciones;
        this.numBanos = numBanos;
        this.tieneTrastero = tieneTrastero;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public int getNumBanos() {
        return numBanos;
    }

    public boolean tieneTrastero() {
        return tieneTrastero;
    }
}
