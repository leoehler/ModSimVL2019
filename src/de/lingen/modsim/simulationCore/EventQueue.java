package de.lingen.modsim.simulationCore;

import java.util.ArrayList;
import java.util.Collections;

public class EventQueue extends ArrayList<Event>{
    private static final long serialVersionUID = 1L;
    private static final int MAX_EVENTS = 100000;

    private EventQueue(){
        super(MAX_EVENTS);
    }

    private static class Inner{
        private static EventQueue eventqueue = new EventQueue();
    }

    public static EventQueue getInstance(){
        return Inner.eventqueue;
    }

    public Event getNextEvent(int timeStep,
                              boolean past,
                              UniqueEventDescription description,
                              SimulationObject receiver,
                              Class<? extends SimulationObject> receiverClass){
        ArrayList<Event> subEvents = new ArrayList<Event>(this.size());

        for(Event e : this) {
            if ((past && timeStep >= e.getTimeStep() || !past && timeStep <= e.getTimeStep())
                    && (receiverClass == null || receiverClass == e.getReceivingClass())
                    && (receiver == null || receiver == e.getReceivingObject())
                    && (description == null || description == e.getDescription())
            ) {
                subEvents.add(e);
            }
        }

        if (subEvents.size() > 1)
            Collections.sort(subEvents);

        if (subEvents.size() >=1)
            return subEvents.get(0);
        // Zu hause implementieren
        // TODO
        // Eine der aufgaben zu hause
        return null;
    }
}
