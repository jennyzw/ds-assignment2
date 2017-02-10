package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by duncan on 2/10/17.
 */
public class Client {
    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Server_int stub = (Server_int) registry.lookup("im_server");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
