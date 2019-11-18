package de.lingen.modsim.gameoflife;

public class Grid {

    private Cell[][] map;
    private int sizeX;
    private int sizeY;

    public Grid(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        map = new Cell[sizeX][sizeY];
    }

    public void populate(){
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                map[i][j] = new Cell(i, j);
            }
        }

    }


    public void showOnConsole(){
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                System.out.print(map[j][i].show());
            }
            System.out.println("");
        }
    }

}