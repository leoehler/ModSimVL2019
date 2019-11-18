package de.lingen.modsim.gameoflife;

public class Cell {

    private boolean alive;
    private int x;
    private int y;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        alive = false;
    }

    public Cell(int x, int y, boolean alive){
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public String show(){
        if(alive)
            return "X";
        else
            return "O";
    }

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}