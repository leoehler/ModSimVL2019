package de.lingen.modsim.des.modelGravelShipping;

import de.lingen.modsim.des.core.*;

public class LoadingDock implements SimulationObject {

    private String name = null;
    private Truck truckCurrentlyLoaded = null;

    private static EventQueue eventQueue = null;

    private static Randomizer loadingWeight = null;
    private static Randomizer loadingTime = null;
    private static Randomizer drivingToWeightStation = null;

    public LoadingDock(String name){
        this.name = name;
        eventQueue = EventQueue.getInstance();
        SimulationObjects.getInstance().add(this);

        loadingWeight = new Randomizer();
        loadingWeight.addProbInt(0.3, 34);
        loadingWeight.addProbInt(0.6, 38);
        loadingWeight.addProbInt(1.0, 41);

        loadingTime = new Randomizer();
        loadingTime.addProbInt(0.3,60);
        loadingTime.addProbInt(0.8,120);
        loadingTime.addProbInt(1.0,180);

        drivingToWeightStation = new Randomizer();
        drivingToWeightStation.addProbInt(0.5,30);
        drivingToWeightStation.addProbInt(0.78,45);
        drivingToWeightStation.addProbInt(1.0,60);
    }

    @Override
    public String toString() {
        return "Loading Dock:" + name + " Truck:" + (truckCurrentlyLoaded != null ? truckCurrentlyLoaded : "---");
    }

    public boolean simulate(int timeStep){
        if(truckCurrentlyLoaded == null
            && GravelShipping.gravelToShip > 0){
            Event event = eventQueue.getNextEvent(timeStep, true, GravelShippingEventTypes.Loading, null, this.getClass());

            if(event != null
                && event.getObjectAttached() != null
                && event.getObjectAttached().getClass() == Truck.class){

                eventQueue.remove(event);

                truckCurrentlyLoaded = (Truck) event.getObjectAttached();
                truckCurrentlyLoaded.load(Math.min(loadingWeight.nextInt(), GravelShipping.gravelToShip));
                GravelShipping.gravelToShip -= truckCurrentlyLoaded.getLoad();

                eventQueue.add(new Event(timeStep + loadingTime.nextInt(), truckCurrentlyLoaded, null, this, GravelShippingEventTypes.LoadingDone));

                return true;
            }
            else{
                event = eventQueue.getNextEvent(timeStep, true, GravelShippingEventTypes.LoadingDone, this, null);
                if(event != null
                    && event.getObjectAttached() != null
                    && event.getObjectAttached().getClass() == Truck.class){

                    eventQueue.remove(event);
                    eventQueue.add(new Event(timeStep + drivingToWeightStation.nextInt(), truckCurrentlyLoaded,
                            WeighingStation.class, null, GravelShippingEventTypes.Weighing));
                }
            }
        }
    }



}
