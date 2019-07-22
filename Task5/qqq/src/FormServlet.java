import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FormServlet", urlPatterns = {"/"})
public class FormServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("It was POST request");
    response.setContentType("text/html");

    try {
      request.getRequestDispatcher("/post.jsp").include(request, response);
    } catch (ServletException e) {
      e.printStackTrace();
    }
    PrintWriter out = response.getWriter();

    out.println(this.getGreeting(request));
    out.flush();
    System.out.println(request.getParameter("username"));
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("It was GET request");
    response.setContentType("text/html");

    try {
      request.getRequestDispatcher("/get.jsp").include(request, response);
    } catch (ServletException e) {
      e.printStackTrace();
    }
    PrintWriter out = response.getWriter();

    out.println(this.getGreeting(request));
    out.flush();
    System.out.println(request.getParameter("username"));

  }

  private String getGreeting(HttpServletRequest request) {
    String username =
        request.getParameter("username") == null ? "BRO" : request.getParameter("username");
    return "<h2>Hello " + username.toUpperCase() + " !</h2> " +
        "\n" + "Username was transfered with " + request.getMethod() + " method";
  }

  protected void doPut(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("It was PUT request");
  }
}
