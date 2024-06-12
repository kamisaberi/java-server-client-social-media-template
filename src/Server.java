import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 8080;
    private static List<User> users = new ArrayList<>();
    private static Map<String, List<String>> posts = new HashMap<>();
    private static Map<String, Set<String>> followers = new HashMap<>();
    private static Map<String, Set<String>> following = new HashMap<>();


    static {
        users.add(new User("ali", "1234"));
        users.add(new User("reza", "1234"));
        users.add(new User("ahmad", "1234"));
    }


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started, listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                // Handle client commands here
                String command = in.readLine();
                processCommand(command, out);
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void processCommand(String command, PrintWriter out) {
            // Implement the logic to process different commands (e.g., register, login, post, follow, etc.)
            String[] parts = command.trim().split(" ");
            if (parts[0].equals("login")) {
                out.println("your username is " + parts[1] + " and your password is " + parts[2]);
            }
            System.out.println("Received command: " + command);
            out.println("Server received: " + command);
        }
    }
}
