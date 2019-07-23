package httpserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

  public static void main(String[] args) {

    System.out.println(Headers.USER_AGENT);
    try (ServerSocket serverSocket = new ServerSocket(8014)) {
      System.out.println("Server started!");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        try (BufferedReader input =
            new BufferedReader(new InputStreamReader(
                socket.getInputStream(),
                StandardCharsets.UTF_8));
            PrintWriter output =
                new PrintWriter(socket.getOutputStream())) {
          while (!input.ready()) {
            ;
          }

          System.out.println("REQUEST FROM CLIENT: ");
          Request request = Request.parseRequest(input);
          request.print();

          System.out.println("RESPOND FROM SERVER: ");
          Response responseOk = new Response();

          responseOk.headers = new ArrayList<>();
          responseOk.headers.add(new Header(Headers.CONTENT_TYPE, "text/html; charset=utf-8"));
          responseOk.httppVersion = "HTTP/1.1";
          responseOk.status = ResponseStatus.OK;
          responseOk.body = "<p>Server fordsdd Task5.6 <br> localhost:8014</p>";
          responseOk.send(output);

          System.out.println("Client disconnected!");
          System.out.println();
          input.close();
          output.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
