# GameSpace

Project for the RIS (Razvoj Informacionih Sistema) course at the Faculty of Sciences. It's a web app where you can browse games, comment on them and save your favorites. You can also add new games and print a PDF report of all games from a studio. Admins can delete users. The UI is in Serbian.

Made with Spring Boot, Spring Security, JPA, JSP and JasperReports, with a MySQL database. The entities are in a separate Maven project (GameSpaceJPA) and the actual app is in GameSpaceWeb.

![Home page](docs/home.png)

## How to run

You need Java 17, Maven and MySQL. First import the database, it has some sample data in it:

```
mysql -u root -p < GameSpace/database/gamespace.sql
```

The DB login is set in `GameSpaceWeb/src/main/resources/application.properties` (root / 1234), so change it there if yours is different.

Then install the JPA project and start the web app. Run it from the GameSpaceWeb folder, otherwise the JSP pages won't load:

```
cd GameSpace/GameSpaceJPA && mvn install
cd ../GameSpaceWeb && mvn spring-boot:run
```

The app runs on http://localhost:8080/Gamespace. You can log in as admin / admin123 or demo / demo123.

![Game details](docs/details.png)
