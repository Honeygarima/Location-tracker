Location-tracker
================

An android application that provides location based services to the user like changing the phone mode etc.

Mostly the smart phone has a feature to GPS tracking, find location, road map to find the way easily .We are using this GPS feature in our application. With this, we are also providing some additional features like changing phone mode (Normal/Silent/Setting Reminder).Currently we are setting the profile according to the user’s current location .

PHASE 1:
We can provide a search box in map UI , so that user can search for his destination.
Now the profile will be saved according to the user’s destination location.

PHASE 2:
Save all the information (Location, Profile name, Reminder message etc.) in the database.
Now in this part, service will periodically check the user’s current location .When the user come in the range of his destination location which is saved in SQLite database as a profile then service will behave accordingly.
