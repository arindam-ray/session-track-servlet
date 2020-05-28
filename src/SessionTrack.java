import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class SessionTrack extends HttpServlet {

   public void service(HttpServletRequest request, HttpServletResponse response) 
   throws ServletException, IOException {

      // Create a session object if it is already not created.
      HttpSession session = request.getSession(true);

      // Get session creation time.
     Date createTime = new Date(session.getCreationTime());

      // Get last access time of this web page.
      Date lastAccessTime = new Date(session.getLastAccessedTime());

      String title = "Welcome Back to my Website";
      Integer visitCount = 1;
      String visitCountKey = "visitCount";
      String userIDKey = "userID";
      String userID = "Admin";
      String docType = "<!doctype HTML>";
      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();


      // Check if this is new comer on your web page.
      if (session.isNew()) {
         title = "Welcome to my Website First Time";   
         session.setAttribute(userIDKey, userID);
      } else {
         visitCount = (Integer) session.getAttribute(visitCountKey);
         visitCount = visitCount + 1;
         userID = (String) session.getAttribute(userIDKey);

      }
      session.setAttribute(visitCountKey, visitCount);



       out.println(docType + "<html><head><title>" + title + "</title></head>"
            + "<link rel='icon' href='images/logo.png'> <link rel='stylesheet' href='style.css'>" + "<body>\n"
            + "<h1>" + title + "</h1> <h2>Session Infomation</h2> "
            + "<table border = 1 align = 'center'>" +
            "<tr> <th>Session Info</th><th>value</th></tr>" +
            "<tr> <td>Session ID</td><td>" + session.getId() + "</td> </tr>" +
            "<tr> <td>Session Duration (Seconds)</td><td>" + session.getMaxInactiveInterval()  + "</td> </tr>" +
            "<tr> <td>First Access Time</td> <td>" + createTime + "  </td> </tr>" +
            "<tr> <td>Last Access Time </td>" + "  <td>" + lastAccessTime + "  </td> </tr>" +
            "<tr> <td>User ID</td> <td>" + userID + "  </td> </tr>" +
            "<tr> <td>Number of visits</td>" + "  <td>" + visitCount + "</td> </tr>" + "</table>"
            + "</body></html>");
   }
}