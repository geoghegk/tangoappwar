<%@ page session="false" %>
<%
	String user = "NOT LOGGED IN";
	if (request.getSession(false) != null) {
		user = "SUCESSFULLY LOGGED IN";
	}
%>
<html>
<body>
<h2>You know when you've been tango'ed! (<%= user %>)</h2>
<ul>
	<li><a href="./tangopublic">Public Area (All welcome)</a></li>
	<li><a href="./tangosecured">Restricted Area (Password required)</a></li>
</ul>
</body>
</html>
