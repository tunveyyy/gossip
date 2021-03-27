package message;

import java.io.Serializable;

public class ACK2 implements Serializable {

    public ACK2 generateACK2Object() {
        System.out.println("Generating an ACK2 object");
        return new ACK2();
    }
}
