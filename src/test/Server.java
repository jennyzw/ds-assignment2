package test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by duncan on 2/10/17.
 */
public class Server implements Server_int {



    public Server() {}

    @Override
    public boolean registerName(String name) throws RemoteException {
        return false;
    }

    @Override
    public boolean sendMessage(String user, String msg) throws RemoteException {
        return false;
    }

    @Override
    public String requestDirectory() throws RemoteException {
        return null;
    }

    @Override
    public void quit() throws RemoteException {

    }

    @Override
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
