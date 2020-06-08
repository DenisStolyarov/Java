import DAO.IDAO;
import DAO.MySQLDAO;
import DAO.Item;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CurrentTimeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String log = "";

        for (Cookie cookie: request.getCookies()) {
            if(cookie.getName().equals("Login")){
                log = cookie.getValue();
            }
        }

        IDAO database = null;
        ArrayList<Item> item = null;
        try {
            database = new MySQLDAO();
            item = database.SelectTour(log);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("items", item);
        getServletContext().getRequestDispatcher("/TourList.jsp").forward(request, response);





    }
}
