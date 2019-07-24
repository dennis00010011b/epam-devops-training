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
public static final String HOST="localhost";
  public static final int PORT=8014;
  public static void main(String[] args) {
    //Create responses
    // 100 Continue  ******************************
    Response response100 = new Response();
    response100.headers = new ArrayList<>();
    response100.headers.add(new Header(Headers.CONTENT_TYPE, "text/html; charset=utf-8"));
    response100.headers.add(new Header(Headers.SERVER, "DennisTikhomirov server"));
    response100.httppVersion = "HTTP/1.1";
    response100.status = ResponseStatus.CONTINUE;
    response100.body = "<p>You are in right way! Just continue, baby</p>";
    // 200 Ok ******************************
    Response response200 = new Response();
    response200.headers = new ArrayList<>();
    response200.headers.add(new Header(Headers.CONTENT_TYPE, "text/html; charset=utf-8"));
    response200.headers.add(new Header(Headers.SERVER, "DennisTikhomirov server"));
    response200.httppVersion = "HTTP/1.1";
    response200.status = ResponseStatus.OK;
    response200.body = "<p>Server for Task5.6 <br> localhost:8014</p>";
    // 404 Not found ******************************
    Response response404 = new Response();
    response404.headers = new ArrayList<>();
    response404.headers.add(new Header(Headers.CONTENT_TYPE, "text/html; charset=utf-8"));
    response404.headers.add(new Header(Headers.SERVER, "DennisTikhomirov server"));
    response404.httppVersion = "HTTP/1.1";
    response404.status = ResponseStatus.NOT_FOUND;
    response404.body = "Page NOT FOUND (";
    // 301 Moved permanently ******************************
    String moved = ""+ HOST+":"+PORT+"/moved/";
    Response response301 = new Response();
    response301.headers = new ArrayList<>();
    response301.headers.add(new Header(Headers.CONTENT_TYPE, "text/html; charset=utf-8"));
    response301.headers.add(new Header(Headers.LOCATION, HOST+":"+PORT+"/moved/"));
    response301.headers.add(new Header(Headers.SERVER, "DennisTikhomirov server"));
    response301.httppVersion = "HTTP/1.1";
    response301.status = ResponseStatus.MOVED_PERMANENTLY;
    // 500 Internal server error ***************************
    Response response500 = new Response();
    response500.headers = new ArrayList<>();
    response500.headers.add(new Header(Headers.CONTENT_TYPE, "text/html; charset=utf-8"));
    response500.headers.add(new Header(Headers.SERVER, "DennisTikhomirov server"));
    response500.httppVersion = "HTTP/1.1";
    response500.status = ResponseStatus.INTERNAL_SERVER_ERROR;
    response500.body = "Sorry! Server is broken!!!";

    System.out.println(Headers.USER_AGENT);
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
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

          response200.send(output);

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
