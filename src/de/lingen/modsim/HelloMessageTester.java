package de.lingen.modsim;

public class HelloMessageTester {

    public static void main (String [] args){
        HelloMessage hm = new HelloMessage("Hello World");
        hm.show();

        HelloMessage hm2 = new HelloMessage("Hallo Welt");
        hm2.show();
    }
}
