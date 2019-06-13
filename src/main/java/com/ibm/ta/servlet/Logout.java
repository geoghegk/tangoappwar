package com.ibm.ta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
@ServletSecurity(
		value = @HttpConstraint(
				rolesAllowed = {
						"tangosecured"
				}),
				httpMethodConstraints = {
			@HttpMethodConstraint(value = "GET", rolesAllowed = {
					"tangosecured"
			})
		})
public class Logout extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/***** This Method Is Called By The Servlet Container To Process A 'GET' Request *****/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		 HttpSession session = request.getSession(false);
		 
		 if(session!=null) {
			 session.invalidate();
		 }
		 
//		 request.logout();
		 response.sendRedirect("./index.jsp");
	}
	
}