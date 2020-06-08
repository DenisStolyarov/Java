package company.tic_tac_toe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameMain {

    public static boolean isRun = true;
    public static TicTacToe game = new TicTacToe();
    public static ArrayList<ServerThread> users = new ArrayList<ServerThread>();
    public static String[] markers = {"X", "O"};
    public static String CurrentMarker = "O";

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(7070);
            System.out.println("initialized");
            while (isRun) {
// ожидание клиента
                if (users.size() < 2) {
                    Socket socket = server.accept();
                    System.out.println(socket.getInetAddress().getHostName()
                            + " connected");
                    /*
                     * создание отдельного потока для обмена данными
                     * с соединившимся клиентом
                     */
                    ServerThread thread = new ServerThread(socket, markers[users.size()]);
                    users.add(thread);
// запуск потока
                    thread.start();
                }
                Thread.sleep(1000);
            }
            Notify("\n\rGame end.");
            for (ServerThread item : GameMain.users) {
                item.disconnect();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
    }

    public static void ChangeMarker() {
        CurrentMarker = CurrentMarker.equals("X") ? "O" : "X";
    }

    public static void Notify(String message) {
        for (ServerThread item : GameMain.users) {
            item.SendMessage(message);
        }
    }
}
