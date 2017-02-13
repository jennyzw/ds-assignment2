package test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by duncan on 2/10/17.
 * java -classpath out/production/ds-assignment2/ -Djava.rmi.server.codebase=file:out/production/ds-assignment2/ test.Server &
 */
public class Server implements Server_int {

    private HashMap<String, Client_int> users;

    public Server() {
        users = new HashMap<String, Client_int>();
    }

    @Override
    public boolean registerName(String name, Client_int stub) throws RemoteException {
        if (users.containsKey(name)) {
            return false;
        } else {
            users.put(name, stub);
            return true;
        }
    }

    @Override
    public boolean sendMessage(String user, String msg) throws RemoteException {
        if (users.containsKey(user)) {
            users.get(user).pushMessage(msg);
            return true;
        } else {
            // user doesn't exist, let client know
            return false;
        }
    }

    @Override
    public String requestDirectory() throws RemoteException {
        return users.keySet().toString();
    }

    @Override
    public String sayHello() {
        return "welcome to our basic IM thing!";
    }

    private static boolean executeCommand(String input) {
        String arr[] = input.split(" ", 2);
        String command = arr[0];
        String rest = arr[1];

        switch (command) {
            case "quit":
                return false;
            default:
                System.out.println("unknown command");
        }
        return true;
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Server_int stub = (Server_int) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("im_server", stub);

            System.err.println("Server ready");

            Scanner sc = new Scanner(System.in);
            String input;
            while (true) {
                input = sc.next();
                if (!executeCommand(input)) break;
            }

            System.err.println("Server shutting down...");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}


