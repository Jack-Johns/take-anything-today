<!DOCTYPE html>

<html>
<%@ page import="Models.User" %>
<head>
    <meta charset="utf-8">
    <title>Account</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>

<body>
<div id = "navigation_bar" class="topnav">
    <a href="/index.html" padding=0 ><img id="logo" src="TAT_Logo.PNG" align="left"/></a>
    <a class="option active" href="/browse">Browse</a>
    <a class="option" href="/my_listings">Listings</a>
    <a class="option" href="/account_details.jsp">My Account</a>
</div>

<div align="middle">
    <br>
    <% if(session.getAttribute("user") == null){ %>
        <meta http-equiv = "refresh" content = "0; url = /Login.html" />
    <% } else { %>
        <h2>Account View</h2>
        <br>
        <div>
            <% User user = (User) request.getAttribute("user_view"); %>
            First Name: <% out.print(user.getFirstName()); %><br><br>
            Last Name: <% out.print(user.getLastName()); %><br><br>
            Address Line 1: <% out.print(user.getAddress().getAddressLine1()); %><br><br>
            Address Line 2: <% out.print(user.getAddress().getAddressLine2()); %><br><br>
            City: <% out.print(user.getAddress().getCity()); %><br><br>
            Postcode: <% out.print(user.getAddress().getPostcode()); %><br><br><br>
        </div>
    <% } %>
</div>

</body>

</html>