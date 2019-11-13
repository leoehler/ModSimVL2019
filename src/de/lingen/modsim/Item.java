package de.lingen.modsim;

public class Item {

    private int number;
    private int value;
    private int volume;

    public static void main(String [] args){
        Item testItem = new Item(1,2,45);
        System.out.println(testItem.toString());
        Item testItem2 = new Item(2,9,5);
        System.out.println(testItem2.toString());
        Item testItem3 = new Item(3,4,30);
        System.out.println(testItem3.toString());
    }

    public Item(int number, int value, int volume){
        this.number = number;
        this.value = value;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Item: " + number
                + ", Value: " + value
                + ", Volume: " + volume;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
}
