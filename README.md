# JavaFX Tower Defense Game

A desktop tower-defense game developed as the final project for the Advanced Programming course at the University of Isfahan. The application uses JavaFX and FXML for its interface and follows a model/controller structure for players, maps, towers, raiders, spells, and game progression.

## Features visible in the implementation

- Sign-up and login screens
- Multiple playable maps
- Archer, wizard, artillery, and air-defense towers
- Several raider types and attack waves
- Shop and spell system
- Coins, health, upgrades, settings, music, and sound effects
- Local SQL-backed player data components

## Technology

- Java 21
- JavaFX 21 and FXML
- Maven Wrapper
- JDBC-based persistence components

## Run

From the repository root:

```powershell
cd GameProject
.\mvnw.cmd clean javafx:run
```

The interface can run through Maven, but database-dependent features require a local database and an appropriate JDBC driver/configuration.

## Structure

```text
GameProject/
|-- pom.xml
|-- src/main/java/       # Models, controllers, and JavaFX entry point
`-- src/main/resources/  # FXML views, images, music, and sound effects
```

## Academic context

Created through GitHub Classroom for the Spring 2024 Advanced Programming course. The repository is retained as an educational project and a record of object-oriented desktop application development.
