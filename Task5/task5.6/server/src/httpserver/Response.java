package httpserver;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Response {

  String httppVersion;
  String status;
  ArrayList<Header> headers;
  String body;

  public Response(String httppVersion, String status, ArrayList<Header> headers,
      String body) {
    this.httppVersion = httppVersion;
    this.status = status;
    this.headers = headers;
    this.body = body;
  }

  public Response() {
  }

  public void send(PrintWriter output) {
    output.println(this.httppVersion + " "+this.status);
    for (int i = 0; i < this.headers.size(); i++) {
      output.println(this.headers.get(i).name+": "+this.headers.get(i).content); //headers
    }
    output.println();                                         // empty line
    output.println(this.body);             //body
    output.flush();
  }


}
