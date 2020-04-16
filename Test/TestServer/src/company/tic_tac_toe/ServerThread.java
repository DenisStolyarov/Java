package company.tic_tac_toe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

class ServerThread extends Thread {
    private PrintStream os; // передача
    private BufferedReader is; // прием
    private InetAddress addr; // адрес клиента
    private String marker;

    public ServerThread(Socket s, String marker) throws IOException {
        os = new PrintStream(s.getOutputStream());
        is = new BufferedReader(new InputStreamReader(s.getInputStream()));
        addr = s.getInetAddress();
        this.marker = marker;
    }

    public void run() {
        String str;
        try {
            while ((str = is.readLine()) != null) {

                if (this.marker.equals(Main.CurrentMarker) && Main.users.size() == 2) {
                    int value;
                    try{
                        value = Integer.valueOf(str);
                    }
                    catch (NumberFormatException e){
                        os.println("\n\rWrong number!");
                        continue;
                    }
                    synchronized (Main.game) {
                        if (!Main.game.fillValue(value, this.marker)) continue;
                        Main.Notify("\n\r" + this.marker + ":\n\r" + Main.game.toString());
                        Main.ChangeMarker();
                        if (Main.game.isGameOver()) {
                            Main.Notify(this.marker+" win");
                            Main.isRun = false;
                        }
                    }
                }
            }
        } catch (IOException e) {
// если клиент не отвечает, соединение с ним разрывается
            System.err.println("Disconnect");
        } finally {
            disconnect(); // уничтожение потока
        }
    }

    public void disconnect() {
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            System.out.println(addr.getHostName() + " disconnecting");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }

    public void SendMessage(String message) {
        os.println(message);
    }
}

