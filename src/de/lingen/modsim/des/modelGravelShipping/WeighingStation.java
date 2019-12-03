package de.lingen.modsim.des.modelGravelShipping;

import de.lingen.modsim.des.core.Event;
import de.lingen.modsim.des.core.EventQueue;
import de.lingen.modsim.des.core.Randomizer;
import de.lingen.modsim.des.core.SimulationObject;

public class WeighingStation implements SimulationObject {

    private static final int MAXLOAD = 40;
    private String name = null;
    private Truck truck = null;

    private static Randomizer drivingToUnloadDock = null;
    private static Randomizer drivingToLoadingDock = null;
    private static EventQueue eventQueue = null;

    public WeighingStation(String name){
        this.name = name;
        eventQueue = EventQueue.getInstance();

        drivingToUnloadDock = new Randomizer();
        drivingToUnloadDock.addProbInt(0.5, 120);
        drivingToUnloadDock.addProbInt(0.8, 150);
        drivingToUnloadDock.addProbInt(1.0, 180);

        drivingToLoadingDock = new Randomizer();
        drivingToLoadingDock.addProbInt(0.5, 30);
        drivingToLoadingDock.addProbInt(1.0, 45);

    }


    @Override
    public String toString() {
        return "Weighing Station : " + name + " " + (truck != null ? truck : "---" );
    }

    @Override
    public boolean simulate(int timeStep) {
        Event event = eventQueue.getNextEvent(timeStep, true, GravelShippingEventTypes.Weighing, null, this.getClass());

        if (event != null && event.getObjectAttached() != null && event.getObjectAttached().getClass() == Truck.class){
            eventQueue.remove(event);
            truck = (Truck) event.getObjectAttached();
            final Integer load = truck.getLoad();

            if(load != null) {
                if (load > MAXLOAD) {
                    GravelShipping.gravelToShip += load;
                    GravelShipping.unsuccessfullLoadingSizes += load;
                    GravelShipping.unsuccessfullLoadings++;

                    eventQueue.add(new Event(timeStep + drivingToLoadingDock.nextInt(), truck,
                            LoadingDock.class, null, GravelShippingEventTypes.Loading));
                }
                else {
                    GravelShipping.gravelToShip += load;
                    GravelShipping.successfullLoadingSizes += load;
                    GravelShipping.successfullLoadings++;
                    truck.unload();

                    eventQueue.add(new Event(timeStep + drivingToUnloadDock.nextInt(), truck,
                            LoadingDock.class, null, GravelShippingEventTypes.Loading));
                }
                truck.unload();
            }
            return true;
        }
        return false;
    }
}
