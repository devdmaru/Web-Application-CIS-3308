<%@page language="java" import="java.sql.*"%>
<%@page language="java" import="dbUtils.DbConn" %>


        <%
            session.invalidate();
            response.sendRedirect("logon.jsp");
        %>
