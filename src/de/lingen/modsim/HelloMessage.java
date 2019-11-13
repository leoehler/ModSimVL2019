package de.lingen.modsim;

public class HelloMessage {

    private String message = null;

    public HelloMessage(String newmessage){
        this.message = newmessage;
    }

    public void show(){
        System.out.println(message);
    }
}
