package Utils;

import message.ACK;

public class GenerateACK {
    public ACK generateACKObject(){
        System.out.println("Generating ACK Object....");
        return new ACK();
    }
}
