<!DOCTYPE html>

<html>
<%@ page import="Models.Listings.Listing, Models.Listings.WantedListing, Models.Listings.OfferedListing, java.util.ArrayList, Models.User, Models.Question, Models.Response" %>
<head>
    <meta charset="utf-8">
    <title>Listing</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>

<body>
<div id = "navigation_bar" class="topnav">
    <a href="/index.html" padding=0 ><img id="logo" src="TAT_Logo.PNG" align="left"/></a>
    <a class="option active" href="/browse">Browse</a>
    <a class="option" href="/my_listings">Listings</a>
    <a class="option" href="/account_details.jsp">My Account</a>
</div>

<div id="listing">
    <% Listing genericListing = (Listing) request.getAttribute("listing"); %>
    <% User user = new User(); %>
    <% if(session.getAttribute("user") != null){ %>
        <% user = (User) session.getAttribute("user"); %>
    <% } %>

    <% System.out.println(genericListing.getListingTypeId()); %>
    <% if(genericListing.getListingTypeId() == 1){ %>
        <% WantedListing listing = (WantedListing) genericListing; %>

        <form action="/view_account" method="post">
            <input type="hidden" value=<% out.print(listing.getSubmitterId()); %> name="submitter">
            <input class="button submit" type="submit" value="View Poster's Profile">
        </form><br>
        <form action="/edit_listing" method="post">
            <h2> <% if(listing.getSubmitterId() == user.getId()){ %>
                <select name="listing_type">
                    <option value=<% out.print(Listing.getListingType(listing.getListingTypeId())); %>>Current Value</option>
                    <option value="Wanted">Wanted</option>
                    <option value="Offering">Offering</option>
                </select> : <input type="text" value=<% out.print(listing.getName()); %> name="listing_name">
            <% } else { %>
                <% out.print(Listing.getListingType(listing.getListingTypeId())); %> : <% out.print(listing.getName()); %>
            <% } %></h2><br>

            <div>
                Category:
                <% if(listing.getSubmitterId() == user.getId()){ %>
                    <select name="category">
                        <option value=<% out.print(listing.getCategory()); %>>Current Value</option>
                        <option value="Household">Household</option>
                        <option value="Hobbies">Hobbies</option>
                        <option value="Toys">Toys</option>
                        <option value="Electronics">Electronics</option>
                        <option value="Garden">Garden</option>
                        <option value="Collectable">Collectable</option>
                        <option value="Other">Other</option>
                    </select>
                <% } else { %>
                    <% out.print(listing.getCategory()); %>
                <% } %><br><br>

                Listed Until:
                <% if(listing.getSubmitterId() == user.getId()){ %>
                    <input type="date" value=<% out.print(listing.getExpiryDate()); %> name="expiry_date">
                <% } else { %>
                    <% out.print(listing.getExpiryDate()); %>
                <% } %><br><br>

                Description: <br>
                <% if(listing.getSubmitterId() == user.getId()){ %>
                    <textarea rows = "5" cols = "45" name = "description"><% out.print(listing.getDescription()); %></textarea>
                <% } else { %>
                    <% out.print(listing.getDescription()); %>
                <% } %><br><br>

            </div>
            <% if(listing.getSubmitterId() == user.getId()){ %>
                <input class="button submit red" type="submit" value="Submit Changes">
            <% } %>
        </form><br>
        <% if(listing.getSubmitterId() == user.getId()){ %>
            <form action="/delete_listing" method="post">
                <input type="hidden" value=<% out.print(listing.getId()); %> name="listingId">
                <input class="button submit red" type="submit" value="Remove Listing">
            </form><br>
        <% } %>
    <% } else { %>
        <% OfferedListing listing = (OfferedListing) genericListing; %>

        <form action="/view_account" method="post">
            <input type="hidden" value=<% listing.getSubmitterId(); %> name="userId">
            <input class="button submit" type="submit" value="View Poster's Profile">
        </form><br>
        <form action="/edit_listing" method="post">
            <h2> <% if(listing.getSubmitterId() == user.getId()){ %>
                <select name="listing_type">
                    <option value=<% out.print(Listing.getListingType(listing.getListingTypeId())); %>>Current Value</option>
                    <option value="Wanted">Wanted</option>
                    <option value="Offering">Offering</option>
                </select> : <input type="text" value=<% out.print(listing.getName()); %> name="listing_name">
            <% } else { %>
                <% out.print(Listing.getListingType(listing.getListingTypeId())); %> : <% out.print(listing.getName()); %>
            <% } %></h2><br>

            <div>
                Category:
                <% if(listing.getSubmitterId() == user.getId()){ %>
                    <select name="category">
                        <option value=<% out.print(listing.getCategory()); %>>Current Value</option>
                        <option value="Household">Household</option>
                        <option value="Hobbies">Hobbies</option>
                        <option value="Toys">Toys</option>
                        <option value="Electronics">Electronics</option>
                        <option value="Garden">Garden</option>
                        <option value="Collectable">Collectable</option>
                        <option value="Other">Other</option>
                    </select>
                <% } else { %>
                    <% out.print(listing.getCategory()); %>
                <% } %><br><br>

                Listed Until:
                <% if(listing.getSubmitterId() == user.getId()){ %>
                    <input type="date" value=<% out.print(listing.getExpiryDate()); %> name="expiry_date">
                <% } else { %>
                    <% out.print(listing.getExpiryDate()); %>
                <% } %><br><br>

                Condition:
                <% if(listing.getSubmitterId() == user.getId()){ %>
                    <select name="condition">
                        <option value=<% out.print(listing.getCondition()); %>>Current Value</option>
                        <option value="Unused">New/Unused</option>
                        <option value="Used">Used</option>
                        <option value="Damaged">Damaged</option>
                        <option value="Scrap">Scrap</option>
                    </select>
                <% } else { %>
                    <% out.print(listing.getCondition()); %>
                <% } %><br><br>

                Description: <br>
                <% if(listing.getSubmitterId() == user.getId()){ %>
                    <textarea rows = "5" cols = "45" name = "description"><% out.print(listing.getDescription()); %></textarea>
                <% } else { %>
                    <% out.print(listing.getDescription()); %>
                <% } %><br><br>

            </div>
            <% if(listing.getSubmitterId() == user.getId()){ %>
                <input class="button submit red" type="submit" value="Submit Changes">
            <% } %>
        </form><br>
        <% if(listing.getSubmitterId() == user.getId()){ %>
            <form action="/delete_listing" method="post">
                <input type="hidden" value=<% out.print(listing.getId()); %> name="listingId">
                <input class="button submit red" type="submit" value="Remove Listing">
            </form><br>
        <% } %>
    <% } %>
</div>

<div id="questions">
    <h2 id="body questions_header">Questions </h2>
    <% ArrayList<Question> questionList = (ArrayList<Question>) request.getAttribute("questionList"); %>
    <% ArrayList<Response> responseList = (ArrayList<Response>) request.getAttribute("responseList"); %>
    <% for(Question question:questionList){ %>
        <div>
            Question: <br>
            <% out.print(question.getQuestion()); %><br>
            <form action="/view_account" method="post">
                <input type="hidden" value=<% out.print(question.getSubmitterId()); %> name="submitter">
                <input class="button submit" type="submit" value="View Poster's Profile">
            </form><br>
            <% if(genericListing.getSubmitterId() == user.getId()){ %>
                <form action="/submit_answer" method="post">
                    <input type="text" placeholder="Respond to this question" name="newResponse">
                    <input type="hidden" value=<% out.print(question.getSubmitterId()); %> name="userId">
                    <input type="hidden" value=<% out.print(question.getId()); %> name="questionId">
                    <input class="button submit" type="submit" value="Submit">
                </form><br>
            <% } %>
            <% for(Response resp:responseList){ %>
                <% if(resp.getQuestionId() == question.getId()){ %>
                    Answer: <br>
                    <% out.print(resp.getResponse()); %><br>
                <% } %>
            <% } %>
        </div>
    <% } %>
    <% if(genericListing.getSubmitterId() != user.getId()){ %>
        <form action="/submit_question" method="post">
            <input type="text" placeholder="Create a new question" name="newQuestion">
            <input type="hidden" value=<% out.print(user.getId()); %> name="userId">
            <input type="hidden" value=<% out.print(genericListing.getId()); %> name="listingId">
            <input class="button submit" type="submit" value="Submit">
        </form><br>
    <% } %>
</div>

</body>

</html>