package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by duncan on 2/10/17.
 */
public interface Client_int extends Remote{
    void pushMessage(String msg) throws RemoteException;
}
