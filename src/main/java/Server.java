import handlers.NodeHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    int portNumber;
    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public static void main(String[] args) {
        int portNumber = 5000;
      //   don't need to specify a hostname, it will be the current machine
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ServerSocket awaiting connections...");
        while(true) {

            try {
                assert serverSocket != null;
                Socket socket = serverSocket.accept(); // blocking call, this will wait until a connection is attempted on this port.
                System.out.println("Connection from " + socket + "!");

                // get the input stream from the connected socket
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                Runnable runnable = new NodeHandler(socket,objectInputStream,objectOutputStream);
                Thread thread = new Thread(runnable);
                thread.start();

//                while (!socket.isClosed()) {
//                    // read the list of messages from the socket
//                    List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
//                    System.out.println("Received message of size " + listOfMessages.size());
//                    System.out.println("The message is:");
//                    listOfMessages.forEach((msg) -> System.out.println(msg.getText()));
//                }
            } catch (NullPointerException e) {
                System.out.println("Server socket cannot accept connections");
            }
            catch(Exception e) {
                System.out.println();
            }
        }

    }
}