Scheduler App For Android

Features:
1. Add client info (Name, Address, Company, Designation, Mobile number)
2. Edit and Delete client info
3. Add meeting schedule (Date, Time, Place, Client Name)
4. Edit and Delete meeting schedule
5. Push Notification before 30 mins of meeting time.
6. Show all client
7. show all meeting schedule
8. Filter meeting schedules using Date and Client.
9. After 24 hours of meeting schedule time that meeting schedule will turn isActive = false and will not be visible in All Meeting Schedule list.

UI Description:
Main activity will have 4 buttons.
	1. Add Client. (Opens up a form which contains fields like name, address, company and designation. I have done this part)
	2. View Clients. (Views all clients name in a list. After clicking on a name his/her info will open in another activity. 
			There will be 2 buttons. one is delete another is edit. Clicking the Delete button will Delete that client info. 
			Edit button will open a form containing clients info. User can edit and save from here)
	3. Add Meeting Schedule. (Opens up a form containing Date, Time, Client Name, Place of meeting. The client name will be a drop down list containing added client names and user will select a client)
	4. View Meeting Schedule. (Shows all added meeting schedules in a list. User can filter user client name and/or date. Clicking on a schedule will open up on a new activity containing Client name, 
			date time, place of meeting. There will be 2 buttons Edit and Delete. Edit button will open a new form filled up with meeting schedule info. After editing there will be a 
			save button which will save the edited meeting info. Delete button will delete the meeting schedule)