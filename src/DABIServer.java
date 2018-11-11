/**
 * Created by shobel on 11/4/18.
 */

import shared.Request;

import java.net.*;
import java.io.*;
import java.util.Random;

public class DABIServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void start(int port) {
        try {
            System.out.println("Starting socket");
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            Request firstRequest = (Request) in.readObject();
            Request response = new Request(firstRequest.getId(), "Acknowledged");
            out.writeObject(response);


            int counter = 0;
            while (true) {
                Request request = new Request(generateId(), "test" + counter);
                out.writeObject(request);
                counter++;
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println("Stopping socket");
            stop();
            //e.printStackTrace();
        }
    }

    public Long generateId(){
        return new Random().nextLong();
    }

    public void stop() {
        try {
            in.close();
        } catch (Exception e) {
        }
        try {
            out.close();
        } catch (Exception e) {
        }
        try {
            clientSocket.close();
        } catch (Exception e) {
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) throws IOException {
        DABIServer server = new DABIServer();
        while (true) {
            server.start(9000);
        }
    }
}
