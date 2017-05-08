<%-- 
    Document   : Labs
    Created on : Jan 31, 2017, 1:21:43 PM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Labs</title>
        <link href="index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div  id="container">
            <jsp:include page="headToContent.jsp"/>

            <div id="content">
                <h2>Lab 1: Web App Proposal</h2>
                <p> In this lab I made a proposal for my web application. I used MySQL Workbench to work with the
                    tables in the database.</p>
                <h2>Lab 2: Data Model and Database</h2>
                <p>
                    This lab had us filling our tables that were contained in the database. Click 
                    <a class="labLinks" href="DataModel.pdf">Here</a> to see the model.      
                </p>
                <h2>Lab 3: Home page and Labs page</h2>
                <p>In this Lab I created HTML pages for my home page and Labs page respectively. I used CSS
                    to style the page to what it is right now. Click <a class="labLinks" href="index.jsp">here</a> 
                    to see the Home page</p>
                <h2>Lab 4: Data Display</h2>
                <p>In this Lab I created HTML pages for my other, assoc, and user tables. I learned to 
                    used java to communicate with the tables in the database, and use that data to 
                    display it on a HTML page.</br>
                    Click <a class="labLinks" href="users.jsp">here</a> 
                    to see the Users page. </br>
                    Click <a class="labLinks" href="other.jsp">here</a> 
                    to see the Teams page. </br>
                    Click <a class="labLinks" href="assoc.jsp">here</a> 
                    to see the comments page.
                </p>
                <h2>Lab 5: Logon, Logoff, Members</h2>
                <p> In this lab I made three new jsp pages. The log on page logs in a valid user. The logoff
                    page logs off a user and redirects them to the index.jsp page. The members area can only be 
                    accessed if the user is logged in. I also added two more jsp pages called top.jsp and bottom.jsp
                    which are used as &lt jsp:include page="" / &gt directives. This helps me to interchange the login
                    and log off links in the header respectively.
                </p>
                <h2>Lab 6: Search</h2>
                <p> In this lab I made two pick lists, the first list is that of the user's names, and the second
                    one is that of the the team's name. I learned how to persist data after the submit button is 
                    clicked. After the data is persists, I use the values to display a filtered results as per the
                    user's choices. If no value is picked the search displays everything.
                </p>
                <h2>Lab 7: Insert</h2>
                <p> In this lab I made three pages for inserting into the user table, the other table and the associative
                    table. The lab was fairly straightforward once I got the insertUser.jsp page to work.
                </p>
                <h2>Lab 8: Update</h2>
                <p> In this lab I made three pages for updating into the user table, the other table and the associative
                    table. The lab was fairly straightforward once I got the updateUser.jsp page to work.
                </p>
                <h2>Lab 9: AJAX Search</h2>
                <p> In this lab I made the search page with the table being built on the client side. I used AJAX to pull
                    data from a JSON tree structure. The table is made on the client side. <a class="labLinks" href="search.html">Link to the AJAX
                    search.</a> 
                </p>
                </p>
                <h2>Lab 10: Delete</h2>
                <p> In this lab I was able to delete records from the user, other, and association tables.
                    This lab was fairly easy to implement. Deleting records that have no connection to the 
                    association table is possible, and if there is a connection then a user friendly error message
                    shown on the screen.
                </p>
                <h2>Challenge: Microsoft .NET</h2>
                <p> In this lab I was able to use Microsoft's .NET framework to display my other table. The challenge was
                    fairly easy as most of the sample code was given already. I learned about different ways to do the same
                    thing we have been doing all semester. Here is a link to the page I made.<a class="labLinks" href="http://cis-iis2.temple.edu/Spring2017/CIS3308_tuf37373/index.aspx"> Link to the .NET page.</a> 
                </p>
                
            </div>
            <jsp:include page ="bottom.jsp" />
        </div> <!-- container -->
    </body>
</html>


