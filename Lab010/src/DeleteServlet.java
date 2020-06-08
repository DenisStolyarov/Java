import DAO.IDAO;
import DAO.MySQLDAO;
import DAO.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        IDAO database = null;
        try {
            database = new MySQLDAO();
            database.DeleteTour(id);
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
        doPost(request,response);
    }
}
