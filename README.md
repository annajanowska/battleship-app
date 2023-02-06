# Battleship game <img src="https://user-images.githubusercontent.com/56201394/217060807-95e196af-410a-435e-a0ac-c08d53f3e4cc.png" width="30" height="30">
The project implements the traditional rules of the battleship game. You can create your account and choose the game mode - real player or AI bot with different difficulty levels. Additionally, you can view stats and game replays.

![image](https://user-images.githubusercontent.com/56201394/217059350-db311e6c-0873-4996-8eb4-7c23d11fce8c.png)
![image](https://user-images.githubusercontent.com/56201394/217059415-3ded2ddc-6b63-4ace-9907-02894518a5ed.png)
![image](https://user-images.githubusercontent.com/56201394/217059494-66b54930-2eaa-41ad-a479-0c0213ab5adb.png)
![image](https://user-images.githubusercontent.com/56201394/217059536-fed3695a-2607-42fe-b856-74c2b3e4a2fd.png)
![image](https://user-images.githubusercontent.com/56201394/217059574-50f84445-5397-4e0e-b160-13e6f799e5e3.png)
![image](https://user-images.githubusercontent.com/56201394/217059618-054e84ba-c272-493e-b582-24a0d7de718c.png)
![image](https://user-images.githubusercontent.com/56201394/217059663-347a381c-7b3a-4af1-9743-e5816b3e998d.png)





## How to launch game
- open the project in IntelliJ
- open XAMPP
- press START next to the MySQL module
- open HeidiSQL
- click on the 'New' button
- give the name and the rest of the settings as in the picture:
   ![](./screenshots/screenshot1.jpg)
- click on the 'Open' button
- select the 'ships-game' base as in the picture:

   ![](./screenshots/screenshot2.jpg)
- click on the 'Query' tab
- select 'New query tab'
- open the file 'database-ships-app.txt' which is in the project folder
- copy the content of the file and paste it into the previously created new query tab in HeidiSQL
- press F9
- open class *'StartApplication'* in IntelliJ (src\main\java\com\example\ships\StartApplication.java)
- right click and select 'Run 'StartApplication.main()''
