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
    private String name;

    private boolean executeCommand(String input, Server_int stub, Client_int clientStub) {
        String[] arr = input.split(" ");
        String command = arr[0];
        switch(command) {
            case "register":
                if (name == null) {
                    try {
                        this.name = arr[1];
                        stub.registerName(arr[1], clientStub);
                        System.out.println("successfully registered "+this.name);
                    } catch(Exception e) {
                        System.out.println("registerName exception");
                    }
                } else {
                    System.out.println("could not register name: only one name per client.");
                }

                break;
            case "get":
                try {
                    System.out.println(stub.requestDirectory());
                } catch(Exception e) {
                    System.out.println("requestDirectory exception");
                }
                break;
            case "send":
                if (name == null) {
                    System.out.println("please register your name first");
                    break;
                }
                try {
//                    String[] messageComponents = rest.split(" ", 2);
                    String user = arr[1];
//                    System.out.println(user);
//                    for (String s: input.split(" ", 3)){
//                        System.out.println(s);
//                    }
                    String msg = name + ": " + input.split(" ", 3)[2];

                    stub.sendMessage(user, msg);
                } catch(Exception e) {
                    System.out.println("requestDirectory exception");
                    e.printStackTrace();
                }
                break;
            case "quit":
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        Client client = new Client();

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Server_int stub = (Server_int) registry.lookup("im_server");
            Client_int clientStub = (Client_int) UnicastRemoteObject.exportObject(client, 0);

            System.err.println("Client ready");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            while(client.executeCommand(input, stub, clientStub)) {
                input = sc.nextLine();
//                executeCommand(input, stub, clientStub);
            }


            System.out.println("shutting down client...");
            sc.close();
            UnicastRemoteObject.unexportObject(client, true);

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
