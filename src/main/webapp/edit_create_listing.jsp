<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <title>Listing</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>

<body>
<div id = "navigation_bar" class="topnav">
    <a href="/index.html" padding=0 ><img id="logo" src="TAT_Logo.PNG" align="left"/></a>
    <a class="option" href="/browse">Browse</a>
    <a class="option active" href="/my_listings">Listings</a>
    <a class="option" href="/account_details.jsp">My Account</a>
</div>

<div align="middle">
    <% if(session.getAttribute("user") == null){ %>
        <meta http-equiv = "refresh" content = "0; url = /Login.html" />
    <% } else { %>
    <form action="/create_listing" method="post">
        <br>
        <h2>Enter Listing Details</h2>
        <br>
        <div>
            Listing Type <select name="listing_type">
                <option value="Wanted">Wanted</option>
                <option value="Offering">Offering</option>
            </select><br><br>
            Listing Name <input type="text" name="listing_name"><br><br>
            Category <select name="category">
                <option value="Household">Household</option>
                <option value="Hobbies">Hobbies</option>
                <option value="Toys">Toys</option>
                <option value="Electronics">Electronics</option>
                <option value="Garden">Garden</option>
                <option value="Collectable">Collectable</option>
                <option value="Other">Other</option>
            </select><br><br>
            Listed Until <input type="date" name="expiry_date"><br><br>
            Condition <select name="condition">
                <option value="Unused">New/Unused</option>
                <option value="Used">Used</option>
                <option value="Damaged">Damaged</option>
                <option value="Scrap">Scrap</option>
            </select><br><br>
            Description<br>
            <textarea rows = "5" cols = "45" name = "description"></textarea><br><br>
            <input class="button submit red" type="submit" value="Submit">
        </div>
    </form>
    <% } %>
</div>

</body>

</html>