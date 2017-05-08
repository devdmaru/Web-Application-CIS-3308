<%-- 
    Document   : headToContent
    Created on : Feb 20, 2017, 10:59:20 AM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="titleNav">
                <div id="title">
                    Soccer Tribune
                </div>
                <div id="nav">
                    <a class="navLink" href="index.jsp">Home</a> | <a class="navLink" href="users.jsp">Users</a>
                    | <a class="navLink" href="other.jsp">Teams</a> | <a class="navLink" href="assoc.jsp">Comments</a>
                    | <a class="navLink" href="search.jsp">Search</a> | <a class="navLink" href="members.jsp">Members</a>
                    | <a class="navLink" href="Labs.jsp">Labs</a>
                    <%   
                    if(session.getAttribute("User_Email") != null){
                        String logoff = "\n | Hi, "+ session.getAttribute("Name") + " <a class=\"navLink\" href=\"logoff.jsp\">Log off</a>\n"; 
                       out.print(logoff);
                    } else{
                       String logon = "\n | <a class=\"navLink\" href=\"logon.jsp\">Log on</a>";
                       out.print(logon);
                    }      
                    %>
                </div>
                <span class="stopFloat"></span>
            </div>
