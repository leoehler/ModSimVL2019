package de.lingen.modsim.des.modelGravelShipping;

import de.lingen.modsim.des.core.Event;
import de.lingen.modsim.des.core.EventQueue;
import de.lingen.modsim.des.core.Simulation;

public class GravelShipping extends Simulation {

    public static Integer gravelToShip = 10000;
    public static Integer gravelShipped = 0;

    public static Integer successfullLoadings = 0;
    public static Integer successfullLoadingSizes = 0;

    public static Integer unsuccessfullLoadings = 0;
    public static Integer unsuccessfullLoadingSizes = 0;

    private static final int NUM_TRUCKS = 12;
    private static final int NUM_LOADING_DOCKS = 2;
    private static final int NUM_WEIGHING_STATIONS = 1;



    @Override
    protected void printEveryStep() {
        System.out.print(" - GravelToShip " + gravelToShip);
        System.out.print(" - GravelShipped " + gravelShipped);
    }

    public static void main(String[] args) {
        EventQueue eventQueue = EventQueue.getInstance();


        for (int i = 0; i < NUM_TRUCKS ; i++) {
            eventQueue.add(new Event(0, new Truck("T" + i), LoadingDock.class, null, GravelShippingEventTypes.Loading));
        }
        for (int i = 0; i < NUM_LOADING_DOCKS; i++) {
            new LoadingDock("LD" + i);
        }
        for (int i = 0; i < NUM_WEIGHING_STATIONS; i++) {
            new WeighingStation("WS" + i);
        }

        GravelShipping gs = new GravelShipping();
        int timeStep = gs.simulate();

        System.out.println("Gravel shipped\t\t:" + gravelShipped + " tons");
        System.out.println("Mean Time / Gravel Unit\t:" + ((double) timeStep / gravelShipped) + " min.");
        //System.out.println("Successfull loadings\t" + successfullLoadings + " mean size: " + ((double) (successfullLoadingSizes / successfullLoadings)) + " tons");
        //System.out.println("Unsuccessfull loadings\t" + unsuccessfullLoadings + " mean size: " + ((double) (unsuccessfullLoadingSizes / unsuccessfullLoadings)) + " tons");
    }
}
