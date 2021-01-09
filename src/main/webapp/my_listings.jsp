<!DOCTYPE html>

<html>
<%@ page import="Models.Listings.Listing, Models.Listings.WantedListing, Models.Listings.OfferedListing, java.util.ArrayList" %>
<head>
    <meta charset="utf-8">
    <title>Take Anything Today</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>

<body>
<div id = "navigation_bar" class="topnav">
    <a href="/index.html" padding=0 ><img id="logo" src="TAT_Logo.PNG" align="left"/></a>
    <a class="option active" href="/browse">Browse</a>
    <a class="option" href="/my_listings">Listings</a>
    <a class="option" href="/account_details.jsp">My Account</a>
</div>

<% ArrayList<Listing> listingsList = (ArrayList<Listing>) request.getAttribute("listingsList"); %>
<% if(listingsList.size() > 0){ %>
    <% for(Listing listing:listingsList){ %>
        <div class="body listing">
            <form action="/listing_view" method="post">
                <% out.print(listing.getCategory()); %> <br>
                <% out.print(Listing.getListingType(listing.getListingTypeId())); %> : <% out.print(listing.getName()); %>
                <input type="hidden" default=<% listing.getId(); %> name="listingId">
                <% if(Listing.isExpired(listing.getExpiryDate()){ %>
                    Expired
                <% } %>
                <input class="button listing" type="submit" value="View Listing">
            </form>
        </div>
<% }}else{ %>
    <h2> No Listings Found </h2>
<% } %>
</body>

</html>