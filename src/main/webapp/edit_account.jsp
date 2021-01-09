<!DOCTYPE html>

<html>
<%@ page import="Models.User" %>
<head>
    <meta charset="utf-8">
    <title>Create Account</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>

<body>
<div id = "navigation_bar" class="topnav">
    <a href="/index.html" padding=0 ><img id="logo" src="TAT_Logo.PNG" align="left"/></a>
    <a class="option" href="/browse">Browse</a>
    <a class="option" href="/my_listings">Listings</a>
    <a class="option active" href="/account_details.jsp">My Account</a>
</div>

<div align="middle">
    <% if(session.getAttribute("user") == null){ %>
        <meta http-equiv = "refresh" content = "0; url = /Login.html" />
    <% } else { %>
    <form action="/edit_account" method="post">
        <br>
        <h2>Edit Your Account</h2>
        <br>
        <div>
            <% User user = (User) session.getAttribute("user"); %>
            Email <input type="email" value = "<% out.print(user.getLogin().getEmail()); %>" name="email"><br><br>
            Password <input type="password" value = "<% out.print(user.getLogin().getPassword()); %>" name="password"><br><br>
            Confirm Password <input type="password" value = "<% out.print(user.getLogin().getPassword()); %>" name="confirm_password"><br><br>
            First Name <input type="text" value = "<% out.print(user.getFirstName()); %>" name="first_name"><br><br>
            Last Name <input type="text" value = "<% out.print(user.getLastName()); %>" name="last_name"><br><br>
            Address Line 1 <input type="text" value = "<% out.print(user.getAddress().getAddressLine1()); %>" name="address_line_1"><br><br>
            Address Line 2 <input type="text" value = "<% out.print(user.getAddress().getAddressLine2()); %>" name="address_line_2" ><br><br>
            City <input type="text" value = "<% out.print(user.getAddress().getCity()); %>" name="city" ><br><br>
            Postcode <input type="text" value = "<% out.print(user.getAddress().getPostcode()); %>" name="postcode"><br><br><br>
            <input class="button red submit" type="reset">
            <input class="button submit" type="submit" value="Save Changes">
        </div>
    </form>
    <% } %>
</div>

</body>

</html>