package part1_task2;
import part1_task2.Tic_Tac_Toe.Tic_Tac_Toe;

import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    private static boolean flag=true;
    static  final int PORT=8000;
    public static void main(String[] args) {
        Tic_Tac_Toe tic_tac_toe=new Tic_Tac_Toe();
        try(ServerSocket serv= new ServerSocket(PORT)) {

            System.out.println("server started");
            while (flag)
                try(Socket socket = serv.accept();
                    BufferedWriter writer =
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream()));
                    BufferedReader reader =
                            new BufferedReader(
                                    new InputStreamReader(
                                            socket.getInputStream()));
                    //BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
                    )
                {
                    System.out.println("client connected");
                  //  writer.write("Hello world");


                 String line="";

                        boolean b;
                        boolean isCurrentX = false;
                        do {
                            isCurrentX = !isCurrentX;
                            writer.write(Tic_Tac_Toe.drawCanvas());
                            writer.flush();
                            System.out.println("mark " + (isCurrentX ? "X" : "O"));
                            writer.write("mark " + (isCurrentX ? "X" : "O"));
                            writer.flush();
                            int n = Tic_Tac_Toe.getNumber(reader.readLine());
                            Tic_Tac_Toe.canvas[n] = isCurrentX ? 1 : 2;
                            b = !Tic_Tac_Toe.isGameOver(n);
                            if (Tic_Tac_Toe.isDraw()){
                                System.out.println("Draw");
                                return;
                            }
                        } while (b);
                        Tic_Tac_Toe.drawCanvas();
                        System.out.println();

                        System.out.println("The winner is " + (isCurrentX ? "X" : "O") + "!");
                        writer.write("The winner is " + (isCurrentX ? "X" : "O") + "!");
                        writer.flush();
                        System.out.println(line);
                        //writer.write(reader1.readLine());
                        writer.flush();

                }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("server close");



//    try {
//        ServerSocket serverSocket=new ServerSocket(8000);
//        serverSocket.accept();
//    }
//    catch (Exception e){
//        System.out.println(e.getMessage());
//    }


    }


}
