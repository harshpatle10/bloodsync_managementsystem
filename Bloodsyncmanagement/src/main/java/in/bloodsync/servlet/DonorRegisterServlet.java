package in.bloodsync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import in.bloodsync.dao.BloodDonorDao;
import in.bloodsync.pojo.BloodDonorPojo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DonorRegisterServlet extends HttpServlet {
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter pw = resp.getWriter();
	
	String name = req.getParameter("name");
	String bloodType = req.getParameter("blood_type");
	String city = req.getParameter("city");
	String contactNumber = req.getParameter("contact");
	
	BloodDonorPojo donor = new BloodDonorPojo();
	donor.setName(name);
	donor.setBloodType(bloodType);
	donor.setCity(city);
	donor.setContact(contactNumber);
	RequestDispatcher rd = req.getRequestDispatcher("html/response_handling.html");
	try {
		boolean ans = BloodDonorDao.addDonor(donor);
		if(ans) {
			pw.println("<title>Registration Successful!</title>");
			pw.println("<div class='wrapper'>");
			pw.println("<div class='container'>");
			pw.println("<h1 class='green'>Registration Successful!</h1>");
			pw.println("<p>Thank you for deciding to donate blood. Your contribution can save life! ♥ </p>");
			pw.println("<p>You are now a part of our noble cause.</p>");
			pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
			pw.println("</div></div>");
		}else {
			pw.println("<title>Registration Fail!</title>");
			pw.println("<div class='wrapper'>");
			pw.println("<div class='container'>");
			pw.println("<h1 class='red'>Registration Fail!</h1>");
			pw.println("<p>Something went wrong. Please try later. </p>");
			pw.println("<p>If the problem persists, contact support.</p>");
			pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
			pw.println("</div></div>");
		}
	}catch(SQLException ex) {
		pw.println("<title>Server Error!</title>");
		pw.println("<div class='wrapper'>");
		pw.println("<div class='container'>");
		pw.println("<h1 class='red'>Server Error!</h1>");
		pw.println("<p>"+ex.getMessage()+"</p>");
		pw.println("<a href='html/donor_register.html' class='btn'>Please try again!</a>");
	}finally {
		pw.flush();
		rd.include(req, resp);
	}
}
}
