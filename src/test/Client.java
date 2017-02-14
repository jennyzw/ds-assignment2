package test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Created by duncan on 2/10/17.
 */
public class Client implements Client_int{
    private Client() {}

    private static boolean executeCommand(String input, Server_int stub, Client_int clientStub) {
        String[] arr = input.split(" ", 2);
        String command = arr[0];
        String rest = arr[1];
        switch(command) {
            case "register":
                try {
                    stub.registerName(rest, clientStub);
                } catch(Exception e) {
                    System.out.println("registerName exception");
                }
                break;
            case "get":
                try {
                    stub.requestDirectory();
                } catch(Exception e) {
                    System.out.println("requestDirectory exception");
                }
                break;
            case "send":
                try {
                    String[] messageComponents = rest.split(" ", 2);
                    String user = messageComponents[0];
                    String msg = messageComponents[1];
                    stub.sendMessage(user, msg);
                } catch(Exception e) {
                    System.out.println("requestDirectory exception");
                }
                break;
            case "quit":
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        Client obj = new Client();

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Server_int stub = (Server_int) registry.lookup("im_server");
            Client_int clientStub = (Client_int) UnicastRemoteObject.exportObject(obj, 0);

            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            while(executeCommand(input, stub, clientStub)) {
                executeCommand(input, stub, clientStub);
            }

            /*String response = stub.sayHello();
            System.out.println("response: " + response);*/
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }


    public void pushMessage(String msg) throws RemoteException {
        System.out.println(msg);
    }
}
