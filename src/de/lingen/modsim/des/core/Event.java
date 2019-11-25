package de.lingen.modsim.des.core;

public class Event implements Comparable<Event>{

    private Integer timeStep = null;
    private SimulationObject objectAttached = null;
    private Class<? extends SimulationObject> receivingClass = null;
    private SimulationObject receivingObject = null;
    private UniqueEventDescription description = null;

    public Event(Integer timeStep,
                 SimulationObject objectAttached,
                 Class<? extends SimulationObject> receivingClass,
                 SimulationObject receivingObject,
                 UniqueEventDescription description) {
        super();
        this.timeStep = timeStep;
        this.objectAttached = objectAttached;
        this.receivingClass = receivingClass;
        this.receivingObject = receivingObject;
        this.description = description;
    }

    public Integer getTimeStep() {
        return timeStep;
    }

    public SimulationObject getObjectAttached() {
        return objectAttached;
    }

    public Class<? extends SimulationObject> getReceivingClass() {
        return receivingClass;
    }

    public SimulationObject getReceivingObject() {
        return receivingObject;
    }

    public UniqueEventDescription getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Event: " + " < " + Time.stepsToString(timeStep) + " > " + description;
    }

    public int compareTo(Event event){
        return timeStep.compareTo(event.timeStep);
    }
}
