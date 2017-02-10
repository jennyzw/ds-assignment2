package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by duncan on 2/10/17.
 */
public interface Server_int extends Remote {
    boolean registerName(String name) throws RemoteException;
    boolean sendMessage(String user, String msg) throws RemoteException;
    String requestDirectory() throws RemoteException;
    void quit() throws RemoteException;

    String sayHello() throws RemoteException;
}
