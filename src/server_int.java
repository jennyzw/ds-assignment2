import java.rmi.Remote;

/**
 * Created by duncan on 2/10/17.
 */
public interface server_int extends Remote {
    boolean registerName(String name);
    boolean sendMessage(String user, String msg);
    String requestDirectory();
    void quit();
}
