package se.iths.labb3osedummestephen;

import javafx.application.Platform;

import java.io.*;
import java.net.Socket;


public class Server {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public void connect(Model model) {

        try {
            socket = new Socket("localhost", 8000);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            var thread = new Thread(() -> {
                try {
                    while (true) {
                        model.serverConnectedProperty().setValue(false);
                        String line = reader.readLine();
                        if (line.endsWith("/>"))
                            Platform.runLater(() -> model.importSvgString(line));
                        else
                            Platform.runLater(() -> model.chatWindow.add(line));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String string, Model model) {
        try {
            writer.println(string);
        } catch (Exception e) {
            model.chatWindow.add(string);
        }

    }
}

