import DAO.IDAO;
import DAO.MySQLDAO;
import DAO.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IDAO database = null;
        ArrayList<User> users = null;
        String log = "";
        try {
            log = request.getParameter("login");
            String pass = request.getParameter("password");
            database = new MySQLDAO();
            database.InsertUser(log,pass);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.addCookie(new Cookie("Login", log));
        getServletContext().getRequestDispatcher("/TourList.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
