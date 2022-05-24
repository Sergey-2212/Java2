package Lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimpleSingleThreadConsoleTCPServer {
    private static final int PORT = 8189;

    Set<Thread> threadList = new HashSet<>();
    ArrayList<Socket> socketList = new ArrayList<>();
    //HashMap<Integer, Socket> socketMap = new HashMap<>();

    public static void main(String[] args) {
        new SimpleSingleThreadConsoleTCPServer().start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
            waitConnection(serverSocket);
            System.out.println("Server started");}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void waitConnection (ServerSocket serverSocket) {
        System.out.println("Waiting for connection....");
        try {
            Socket socket = serverSocket.accept();
            socketList.add(socket);
            threadList.add(new ClientSession(socket));
            System.out.println("Client connected");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class ClientSession extends Thread {
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private Thread serverThread;
        private int clientNumber;

        public ClientSession (Socket socket) {
           try  {
                this.socket = socket;
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                clientNumber = getClientNumber();
                start();
                } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
        startConsoleInput();

            try {
                while (true) {
                    String income = in.readUTF();
                    if (income.startsWith("/end")) {
                        shutdown();
                        break;
                    }
                    System.out.println("Received: " + income);
                    for (int i = 0; i < socketList.size(); i++) {
                        if(!socketList.get(i).equals(this.socket)){
                         var out_temp =  new DataOutputStream(socketList.get(i).getOutputStream());
                         out_temp.writeUTF("User " + (clientNumber +1) + " sent: " + income);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    shutdown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            private int getClientNumber() {
            for (int i = 0; i < socketList.size(); i++) {
                if (socketList.get(i).equals(this.socket)) return i;
                }
            return 0;
                }

        private void startConsoleInput() {
            serverThread = new Thread(() -> {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                    //System.out.print("Enter your message >>>>> ");
                    while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                        if (br.ready()) {
                            String outcome = br.readLine();
                            out.writeUTF(outcome);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
        }
        private void shutdown() throws IOException {
            if (serverThread != null && serverThread.isAlive()) {
                serverThread.interrupt();
            }

            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            System.out.println("Session stopped");
        }
    }
}

