import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;

@WebServlet("/ServletAccount")
public class ServletAccount extends javax.servlet.http.HttpServlet {

    private static Connection connection;
    private static Statement stm;
    private static ResultSet res;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/servlet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_NAME = "root";
    private static final String DB_PASSWORD = "375447753309";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        List<Account> basket = new ArrayList();
        PrintWriter pw = response.getWriter();
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();;
            connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        PreparedStatement preparedStatement = null;
        String zap = "SELECT * FROM account";
//        int x = 0;
//        int y = 8 / x;
        String query = "select count(*) from account";
        boolean check = false;
        try {
            stm = connection.createStatement();
            res = stm.executeQuery(zap);
            while(res.next())
            {
                Account acc = new Account();

                acc.login = res.getString("name");
                acc.pass = res.getString("password");

                if(login.equals(acc.login) && password.equals(acc.pass))
                {
                    response.sendRedirect("Welcome.jsp");
                    //response.sendRedirect("https://metanit.com/");
                    check = true;
//                    Date date = new Date();
//                    Cookie cookie = new Cookie(acc.login, date.toString());
//                    response.addCookie(cookie);
//
//
//                    pw.println("<html> <head>\n" +
//                            "  <meta charset=\"utf-8\">\n" +
//                            "  <title>Тег META, атрибут charset</title>\n" +
//                            " </head>");
//                    pw.println("<h1>hello</h1>");
//                    pw.println("<h3>");
//                    pw.println("method " + request.getMethod());
//                    pw.println("</h3>");
//
//                    pw.println("<h3>");
//                    pw.println("URL "+ request.getRequestURL());
//                    pw.println("</h3>");
//
//                    pw.println("<h3>");
//                    pw.println("IP " + request.getRemoteAddr());
//                    pw.println("</h3>");
//
//                    pw.println("<h3>");
//                    pw.println("Port " + request.getRemotePort());
//                    pw.println("</h3>");
//
//                    pw.println("<h3>");
//                    pw.println("Http-string " + request.getQueryString());
//                    pw.println("</h3>");
//
//                    Cookie[] cookies = request.getCookies();
//                    String cookieName = acc.login;
//                    Cookie cookie2 = null;
//                    if(cookies !=null) {
//                        for(Cookie c: cookies) {
//                            pw.println(c.getName()+"<br>");
//                        }
//                    }
//
//                    pw.print("<h5>");
//                    pw.print(cookie2);
//                    pw.print("</h5>");
//
//                    Enumeration< String > e = request.getHeaderNames();
//                    while (e.hasMoreElements()) {
//                        String name = e.nextElement();
//                        String value = request.getHeader(name);
//                        pw.println(name + " = " + value);
//                    }
//                    pw.println("</html");


                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!check)
        {
            request.getRequestDispatcher("Error.jsp").forward(request, response);
            //response.sendRedirect("Error.jsp");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }
}
