package in.bloodsync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        String email = req.getParameter("email");
        String pass = req.getParameter("pass");

        ServletConfig sc = getServletConfig();
        String adminemail = sc.getInitParameter("adminemail");
        String adminpass = sc.getInitParameter("adminpass");

        if (adminemail.equalsIgnoreCase(email) && adminpass.equalsIgnoreCase(pass)) {
            resp.sendRedirect("html/Admin/Desktopadmin.html");
        } else {
            pw.println("<html><head><title>Login Failed</title></head><body>");
            pw.println("<script>alert('Please enter valid ID and Password');</script>");
            
            RequestDispatcher rd = req.getRequestDispatcher("/html/Admin/admin_login.html");
            rd.include(req, resp);

            pw.println("</body></html>");
        }
    }
}
