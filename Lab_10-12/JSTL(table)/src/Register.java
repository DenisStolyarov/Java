import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
@WebServlet(name = "Register")
public class Register extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        if(name =="" && pass=="")
        {
            throw new RuntimeException();
        }
        try {

            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/servlet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                            "root","375447753309");

            PreparedStatement ps = con.prepareStatement
                    ("insert into account values(?,?)");

            ps.setString(1, name);
            ps.setString(2, pass);
            int i = ps.executeUpdate();

            if(i > 0) {
                out.println("You are sucessfully registered");
            }

        }
        catch(Exception se) {
            se.printStackTrace();
        }

    }
}