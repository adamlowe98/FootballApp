# Assigment

Introduction

For the Mobile App Development module in semester 6 where I am required to create an app that implements CRUD (Create, Read, Update and Delete) for data entered by the user into the app which is then stored in a JSON file.
The app that I created is a Football Player App which allows a user to save information about their favourite players which they could use to save players they plan on buying in FIFA so they have all the information they need on the player before buying them for their team.
The user can enter the Full name of the player, the player’s age, the league the player plays in, the team the player plays for, the position the player plays in for the team, the cost of the player in the transfer market, an image of the player and finally the location of the player.
The data entered and accessed by the user is saved in the JSON file called players.json. When the user creates a player and deletes a player it will be removed from the JSON file.
My motivation to create this app was from myself playing FIFA and having to jump back and forth between google looking up players to buy and I decided that it would be a lot easier to have an app where I could save all the information in one go and then not have to jump back and forth between google and FIFA.
I am also a follower of football as an Arsenal FC supporter and Waterford FC supporter where I attend matches from time to time and have a lot of interest in player information as I occasionally dabble in Stock trading with football players which I also thought the app would be perfect for which I have
tested it with and it is perfect for the job.

Architectural Concerns

The original plan that I had for my app was for it to be a transfer app where a user can detail a player and show what club the player went from and the current club they have signed for but I decided that this would require too many pictures and details that wouldn't fit on the phone screen properly.
For my app I decided to stick with the practical layouts as it fit the app I envisioned perfectly but I added my own custom icons to the app for certain options. I was also going to add a home page for the app that links to the main app but decided that this would be redundant.
In my app I have a top bar navigation which holds the options to add a player, cancel a process, delete a player when a player is selected and the location of all players in the world. When a player is added each player is displayed in a recycler view on the main screen of the app.
The player and the information will be sent to the recycler view in descending order depending on when they were created. Each individual player is assigned its own unique ID in an array which always starts at zero.
When creating the app, I attempted to add all data into firebase which stores all of the data in the cloud but after creating an account in firebase it wouldn’t connect correctly and the database wasn’t displaying correctly in firebase.
In my app I also included a search option which allows the user to enter the name of the player and search for them to edit or delete. The search option requires the user the title of the player but only the title to find the player.
After looking through the app store I couldn’t find an app similar to what I have created but I had originally tried to model my app similarly to the futhead app as I wanted the player information to resemble it when the user entered a player.
For my roadmap for future development I want to add 3 tabs in a top navigation bar type layout with one of them being the add player section, the next showing new articles for the related players and for the last tab showing the recent form and results for the player’s teams.
I would also like to add a history section for each player to show their past accomplishments and Transfers. I have pushed all the code for my Football player app to GitHub in a repository which can be accessed using the following link:

https://github.com/adamlowe98/FootballApp.git


