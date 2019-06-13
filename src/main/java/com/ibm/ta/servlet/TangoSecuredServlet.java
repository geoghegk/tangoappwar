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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@WebServlet("/tangosecured")
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
public class TangoSecuredServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/***** This Method Is Called By The Servlet Container To Process A 'GET' Request *****/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		HttpSession session = request.getSession(false);
		if (session == null) {
		   request.setAttribute("Error", "Session has ended.  Please login.");
		   response.sendRedirect("./login.jsp");
		}
	
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		/***** Set Response Content Type *****/
		response.setContentType("text/html");

		String data = getData();
		
		/***** Print The Response *****/
		PrintWriter out = response.getWriter();
		String title = "Tango Private";		
		String docType = "<!DOCTYPE html>\n";
		out.println(docType 
				+ "<html>\n" + "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>" + title + "</title></head>\n" + "<body>");

		out.println("<h2>Tango Private: This is a secured page</h2>");
		
		out.println("<h2>Data:</h2>");
		out.println(data);
		
		out.println("<h2>Get me out:</h2>");
		out.println("<a href=\"./logout\">Logout</a>");
		
		out.close();
	}
	
	private String getData() {
		String data = "";
		
		try {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TangoDB");

			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select name, owner from cats");

			while(rs.next())
			{
				data += "<p>";
				data += rs.getString("name") + " ";
				data += rs.getString("owner");
				data += "</p>";
			}

		} catch (Exception e) {
			System.out.println("!!! EXCEPTION !!!" + e.getMessage());
		}
		
		return data;
	}
}