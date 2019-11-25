package de.lingen.modsim.des.core;

public abstract class Simulation {

    protected abstract void printEveryStep();

    public int simulate(){
        EventQueue eventQueue = EventQueue.getInstance();
        SimulationObjects simulationObjects = SimulationObjects.getInstance();

        int numberOfSteps = 1;
        int timeStep = 0;
        Event e = null;

        do{
            System.out.println(numberOfSteps++ + "." + "<" + Time.stepsToString(timeStep) + "> " + eventQueue);

            printEveryStep();

            boolean oneSwitched;
            do{
                oneSwitched = false;
                for (SimulationObject so : simulationObjects){
                    if (so.simulate(timeStep)){
                        oneSwitched = true;
                        System.out.println(numberOfSteps++ + "." + "<" + Time.stepsToString(timeStep) + "> " + eventQueue);
                    }
                }
            } while (oneSwitched);

            System.out.println();
            timeStep++;

            e = eventQueue.getNextEvent(timeStep, false, null, null, null);
            if (e != null)
                timeStep = e.getTimeStep();
        } while (e != null);

        System.out.println("---------------------------------------------");
        return timeStep;
    }
}