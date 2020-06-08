import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet("/Servlet")
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter printWriter = response.getWriter();

        String row = request.getParameter("row");
        String column = request.getParameter("column");

        //printWriter.println(row + " " + col);
        request.setAttribute("row", row);
        request.setAttribute("column", column);

        getServletContext().getRequestDispatcher("/newPage.jsp").forward(request, response);
    }
}
