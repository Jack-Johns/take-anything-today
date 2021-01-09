<!DOCTYPE html>

<html>

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
       <form action="/create_account" method="post">
           <br>
           <h2>Create An Account</h2>
           <br>
           <div>
               Email <input type="email" name="email"><br><br>
               Password <input type="password" name="password"><br><br>
               First Name <input type="text" name="first_name"><br><br>
               Last Name <input type="text" name="last_name"><br><br>
               Address Line 1 <input type="text" name="address_line_1"><br><br>
               Address Line 2 <input type="text" name="address_line_2"><br><br>
               City <input type="text" name="city"><br><br>
               Postcode <input type="text" name="postcode"><br><br><br>
               <input class="button submit" type="submit" value="Sign Up">
           </div>
       </form>
   </div>

</body>

</html>