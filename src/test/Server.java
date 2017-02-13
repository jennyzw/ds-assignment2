package test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by duncan on 2/10/17.
 * java -classpath out/production/ds-assignment2/ -Djava.rmi.server.codebase=file:out/production/ds-assignment2/ test.Server &
 */
public class Server implements Server_int {



    public Server() {}

    public boolean registerName(String name) throws RemoteException {
        return false;
    }

    public boolean sendMessage(String user, String msg) throws RemoteException {
        return false;
    }

    public String requestDirectory() throws RemoteException {
        return null;
    }

    public void quit() throws RemoteException {

    }

    public String sayHello() {
        return "hello";
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Server_int stub = (Server_int) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("im_server", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }


}
