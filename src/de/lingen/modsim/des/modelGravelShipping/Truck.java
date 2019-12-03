package de.lingen.modsim.des.modelGravelShipping;

import de.lingen.modsim.des.core.SimulationObject;
import de.lingen.modsim.des.core.SimulationObjects;

public class Truck implements SimulationObject {
    private String name = null;
    private Integer loadedWithTons = null;

    public Truck(String name){
        this.name = name;
        SimulationObjects.getInstance().add(this);
    }

    public void load(int weight){
        loadedWithTons = weight;
    }

    public void unload(){
        loadedWithTons = null;
    }

    public Integer getLoad(){
        return loadedWithTons;
    }

    @Override
    public String toString() {
        return name + (loadedWithTons != null ? "(tons: " + loadedWithTons + ")" : "");
    }

    @Override
    public boolean simulate(int timeStep) {
        return false;
    }
}