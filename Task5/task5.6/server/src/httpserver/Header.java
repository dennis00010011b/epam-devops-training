package httpserver;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

public class Header {

  String name;
  String content;

  public Header(String name, String content) {
    this.name = name;
    this.content = content;
  }

  @Override
  public String toString() {
    return this.name+":"+this.content;
  }
}
