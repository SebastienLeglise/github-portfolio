import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortScanner {

    private static final int DEFAULT_TIMEOUT = 200;
    private static final int DEFAULT_START_PORT = 1;
    private static final int DEFAULT_END_PORT = 65535;
    private static final String DEFAULT_TARGET = "localhost";

    private final int startPort, endPort, socketTimeout;
    private final String targetHost;

    private PortScanner(String targetHost, int startPort, int endPort, int timeout) {
        this.targetHost = targetHost;
        this.startPort = startPort;
        this.endPort = endPort;
        this.socketTimeout = timeout;
    }


    private static  PortScanner initializeScanner(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("rentrer host (default: localhost): ");
            String host = scanner.nextLine();
            if (host.isEmpty()) 
                host = PortScanner.DEFAULT_TARGET;
            
            System.out.print("Rentrer port de départ (default: 1): ");
            String startPortInput = scanner.nextLine();
            int start = startPortInput.isEmpty() ? PortScanner.DEFAULT_START_PORT : Integer.parseInt(startPortInput);

            System.out.print("Rentrer port de fin (default: 65535): ");
            String endPortInput = scanner.nextLine();
            int end = endPortInput.isEmpty() ? PortScanner.DEFAULT_END_PORT : Integer.parseInt(endPortInput);

            System.out.print("Rentrer timeout (default: 200): ");
            String timeoutInput = scanner.nextLine();
            int timeout = timeoutInput.isEmpty() ? PortScanner.DEFAULT_TIMEOUT : Integer.parseInt(timeoutInput);

            return new PortScanner(host, start, end, timeout); 
        }
    }

    private ArrayList<Integer> scanPorts() {
        ArrayList<Integer> openPorts = new ArrayList<>();
        
        for (int port = startPort; port <= endPort; port++) {
            try {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(targetHost, port), socketTimeout);
                }
                openPorts.add(port);
                System.out.println("Port " + port + " is open.");
            } catch (IOException e) {
                System.out.println("Port " + port + " is closed or filtered.");
            }
        }
        
        return openPorts;
    }


    private static void affichePortsOuverts(List<Integer> openPorts){
        if (openPorts.isEmpty()) {
            System.out.println("Aucun port ouvert");
        } else {
            System.out.println("Ports ouverts:");
            for (int port : openPorts) {
                System.out.println(port);
            }
        }
    }

    public static void lanceScanner(){
        ArrayList<Integer> openPorts = (initializeScanner()).scanPorts();
        affichePortsOuverts(openPorts);

    }

    public static void main(String[] args) {
        PortScanner.lanceScanner();
    }
}
