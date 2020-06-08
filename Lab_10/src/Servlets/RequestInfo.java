package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(name = "Servlets.RequestInfo", urlPatterns={"/Servlets.RequestInfo"})
public class RequestInfo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String html = "";
        String currentTime = GetCurrentTime();
        String userAddress = request.getRemoteAddr();
        String userName = request.getRemoteUser();
        String protocol = request.getProtocol();
        String method = request.getMethod();
        String url = request.getRequestURI();

        html += "<h1>"+currentTime+"</h1>";
        html += "<h3>"+userAddress+"</h3>";
        html += "<h3>"+userName+"</h3>";
        html += "<h3>"+protocol+"</h3>";
        html += "<h3>"+method+"</h3>";
        html += "<h3>"+url+"</h3>";
        html += "<ul>";

        Enumeration<String> headersList = request.getHeaderNames();
        while (headersList.hasMoreElements()) {
            String header = headersList.nextElement();
            html += "<li>" + header +" -> "+ request.getHeader(header);
        }
        html += "</ul>";

        response.setContentType("text/html");
        response.getWriter().print(html);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String GetCurrentTime(){
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(dateNow);
    }

}
