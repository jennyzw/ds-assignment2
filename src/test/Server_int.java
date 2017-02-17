package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by duncan on 2/10/17.
 */
public interface Server_int extends Remote {
    boolean registerName(String name, Client_int stub) throws RemoteException;
    boolean unregisterName(String name, Client_int stub) throws RemoteException;
    boolean sendMessage(String user, String msg) throws RemoteException;
    String requestDirectory() throws RemoteException;
}
