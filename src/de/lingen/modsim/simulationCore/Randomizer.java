package de.lingen.modsim.simulationCore;

import java.util.ArrayList;
import java.util.Random;

public class Randomizer {
    private static final boolean USE_GAUSSIAN = true;

    private static final double MIN_PROB = 0.0;
    private static final double MAX_PROB = 1.0;

    private static Random random = new Random();
    private ArrayList<Probability2Value<Integer>> prob2Int = new ArrayList<>();

    public void addProbInt(double to, int value){
        if(to >= MIN_PROB && to <= MAX_PROB)
            prob2Int.add(new Probability2Value<Integer>(to, value));
    }

    public int nextInt(){
        double r = USE_GAUSSIAN ? random.nextGaussian() : random.nextDouble();

        for (Probability2Value<Integer> pI : prob2Int){
            if(r <= pI.probabilityUpperLimit)
                return pI.value;
        }
        return 0;
    }

}