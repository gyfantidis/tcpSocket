package tcpSock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class TcpSockServer {
    public static void main(String[] args) throws IOException {

        //The 2^20 hashtable
        Hashtable<Integer, Integer> table1 = new Hashtable<>(2, 20);


        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(10007);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10007.");
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("Waiting for connection.....");

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println("Connection successful");
        System.out.println("Waiting for input.....");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        int key;
        int value;
        int delKey;
        int searchKey;
        int searchValue;



        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("0")) {
                break;
            }

            else if (inputLine.equals("1")) {
                try{
                key = Integer.parseInt(inputLine = in.readLine());
                value = Integer.parseInt(inputLine = in.readLine());
                table1.put(key, value);
                out.println("1");
                }
                catch (NumberFormatException nfe){
                    out.println("0");
                }

            }

            else if (inputLine.equals("2")) {
                try {
                    delKey = Integer.parseInt(inputLine = in.readLine());
                    if(table1.containsKey(delKey)) {
                        table1.remove(delKey);
                        out.println("1");
                    }
                    else {
                        out.println("0");
                    }
                }
                catch (NumberFormatException nfe){
                    out.println("0");
                }
            }

            else if (inputLine.equals("3")) {
                try {
                    searchKey = Integer.parseInt(inputLine = in.readLine());
                    if (table1.containsKey(searchKey)) {
                        searchValue = table1.get(searchKey);
                        out.println(searchValue);
                    } else {
                        out.println("0");
                    }
                }
                catch (NumberFormatException nfe){
                    out.println("0");
                }
            }


            System.out.println("The hashtable : " +table1);



        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }



}