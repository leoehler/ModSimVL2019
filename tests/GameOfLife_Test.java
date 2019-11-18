package de.lingen.modsim.tester;
import de.lingen.modsim.gameoflife.Grid;

public class GameOfLife_Test {


    public static void main(String [] args){
        Grid test1 = new Grid(16,5);
        test1.populate();
        test1.showOnConsole();
    }
}