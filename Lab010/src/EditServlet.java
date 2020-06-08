import DAO.IDAO;
import DAO.MySQLDAO;
import DAO.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String country = request.getParameter("name");
        String price = request.getParameter("price");

        IDAO database = null;
        try {
            database = new MySQLDAO();
            database.UpdateTour(id,country,price);
            response.sendRedirect(request.getContextPath()+"/CurrentTime");
        }
        catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/CurrentTime");
        }
        catch (ClassNotFoundException e) {
            response.sendRedirect(request.getContextPath()+"/CurrentTime");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        IDAO database = null;
        Item item = null;
        try {
            database = new MySQLDAO();
            item = database.SelectOne(id);
        }
        catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/CurrentTime");
        }
        catch (ClassNotFoundException e) {
            response.sendRedirect(request.getContextPath()+"/CurrentTime");
        }

        if(item !=null) {
            request.setAttribute("item", item);
            getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
        }

    }
}
