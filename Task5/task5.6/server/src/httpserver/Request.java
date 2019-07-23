package httpserver;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Request {

  ArrayList<Header> headers;
  String resource;
  String method;
  String body;
  String httpVersion;

  public Request() {
  }


  public static Request parseRequest(BufferedReader input) {
    Request request = new Request();
    ArrayList<Header> head = new ArrayList<>();
    try {
      while (input.ready()) {
        String[] split = input.readLine().split(" ");
        request.method = split[0];
        request.resource = split[1];
        request.httpVersion = split[2];
        String reader;

        while (!(reader = input.readLine()).equals("")) {
          split = reader.split(":", 2);
          if (split.length!=2) split[1]="";
          Header header = new Header(split[0].trim().toUpperCase(), split[1].trim());
          head.add(header);
        }
        request.headers = head;
        request.body = (input.ready()) ? input.readLine() : "";

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return request;

  }

  public void print() {
    System.out.println(this.method + " " + this.resource + " " + this.httpVersion);
    for (Header header : headers
    ) {
      System.out.println(header);
    }
    System.out.println(this.body);
  }
}
