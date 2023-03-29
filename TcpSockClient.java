package tcpSock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpSockClient {
    public static void main(String[] args) throws IOException {
        String serverHostname = "127.0.0.1";
        if (args.length > 0) {
            serverHostname = args[0];
        }
        System.out.println("Attempting to connect to host " + serverHostname + " on port 10007.");

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket(serverHostname, 10007);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + serverHostname);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInputA="9";
        String userInputB="9";
        String userInputC="9";

        System.out.println("Select \"0\" to quit, \"1\" to insert, \"2\" to delete, \"3\" to search :");
        while ((userInputA = stdIn.readLine()) != null) {
            if (userInputA.equals("0")) {
                System.out.println("are you sure? \"0\" to quit, everything else return to start menu : ");
                userInputB = stdIn.readLine();
                if (userInputB.equals("0"))
                    break;
                System.out.println("");
                userInputA = "9";
            }
            else if (userInputA.equals("1")) {
                System.out.println("Give the key : ");
                userInputB = stdIn.readLine();
                System.out.println("Give the value : ");
                userInputC = stdIn.readLine();
                out.println(userInputA);
                out.println(userInputB);
                out.println(userInputC);
                System.out.println("Servers message : " +in.readLine());
                System.out.println("");

            }
            else if (userInputA.equals("2")) {
                System.out.println("Delete the key : ");
                userInputB = stdIn.readLine();
                out.println(userInputA);
                out.println(userInputB);
                System.out.println("");
                System.out.println("Servers message : " +in.readLine());
                System.out.println("");
                }

            else if (userInputA.equals("3")) {
                System.out.println("Search for the key : ");
                userInputB = stdIn.readLine();
                out.println(userInputA);
                out.println(userInputB);
                System.out.println("");
                System.out.println("Keys Value (0 if keys not exist) : " +in.readLine());
                System.out.println("");
            }
            else {
                System.out.println("Please select from 0 to 3");

            }



            System.out.println("Select \"0\" to quit, \"1\" to insert, \"2\" to delete, \"3\" to search:");

        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
