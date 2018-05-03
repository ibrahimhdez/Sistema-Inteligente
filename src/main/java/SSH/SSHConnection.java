package SSH;

import com.jcraft.jsch.JSchException;
import java.io.IOException;

public class SSHConnection {
    private static final String USERNAME = "pi";
    private static final String HOST = "192.168.1.177";
    private static final int PORT = 22;
    private static final String PASSWORD = "***********+";
 
    public static void main(String[] args) {
        try {
            SSHConnector sshConnector = new SSHConnector();    
             
            sshConnector.connect(USERNAME, PASSWORD, HOST, PORT);
            sshConnector.executeCommand("gpio -g write 26 0");
            sshConnector.executeCommand("gpio -g write 19 0");
            sshConnector.disconnect();
             
            System.out.println("Done");
        } catch (JSchException ex) {
            ex.printStackTrace();
             
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
             
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
             
            System.out.println(ex.getMessage());
        }
    }
}
