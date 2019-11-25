package de.lingen.modsim.des.modelGravelShipping;

import de.lingen.modsim.des.core.UniqueEventDescription;

public enum GravelShippingEventTypes implements UniqueEventDescription {

    Loading("Loading Truck"),
    LoadingDone("Loading Truck done"),
    Weighing("Weighing Truck"),
    Transport("Transporting Truck);

    String eventTypeAsString = null;

    private GravelShippingEventTypes(String value){
        this.eventTypeAsString = value;

    }

    public String get(){
        return eventTypeAsString;
    }

}