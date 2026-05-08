# Project: Java Swing Diary Application with MySQL Integration

## Goal
To create a simple, aesthetically pleasing diary application using Java Swing, with data persistence managed by a MySQL database. The application will be structured for high cohesion, separating UI, data transfer objects (DTOs), and database interaction logic into distinct files.

## Technologies
- **Language**: Java
- **UI Framework**: Java Swing
- **Database**: MySQL
- **Database Driver**: MySQL Connector/J (JDBC driver)

## Project Structure
The project will follow a clear package structure for maintainability:

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── diary/
│   │               ├── ui/             # Swing UI components (DiaryUI.java)
│   │               ├── dto/            # Data Transfer Objects (DiaryEntry.java)
│   │               └── db/             # Database interaction logic (MySQLDiaryDAO.java)
│   └── resources/      # Configuration files, images, etc.
└── test/               # Unit and integration tests (To be added later)
```

## Core Components

### 1. Data Transfer Object (`com.example.diary.dto.DiaryEntry`)
- **Purpose**: A Plain Old Java Object (POJO) representing a single diary entry.
- **Fields**:
    - `id`: Unique identifier for the entry (e.g., `long`).
    - `entryDate`: Date and time of the entry (e.g., `java.sql.Timestamp`).
    - `title`: Optional title for the entry (e.g., `String`).
    - `content`: The main text of the diary entry (e.g., `String`).
- **Attributes**: Simple POJO with getters and setters.

### 2. Database Access Object (`com.example.diary.db.MySQLDiaryDAO`)
- **Purpose**: Handles all interactions with the MySQL database.
- **Methods**:
    - `connect()`: Establishes a connection to the MySQL database.
    - `disconnect()`: Closes the database connection.
    - `saveEntry(DiaryEntry entry)`: Inserts a new diary entry or updates an existing one.
    - `getEntryById(long id)`: Retrieves a specific entry by its ID.
    - `getEntriesByDate(Date date)`: Retrieves all entries for a specific date.
    - `getAllEntries()`: Retrieves all diary entries, ordered by date.
    - `deleteEntry(long id)`: Deletes an entry by its ID.
    - `createTableIfNotExists()`: Utility to create the `diary_entries` table if it doesn't exist.
- **Configuration**: Database connection details (URL, username, password, driver class) are defined as constants within the class. **IMPORTANT**: These must be updated with your specific MySQL credentials.
- **Dependencies**: Requires the MySQL Connector/J JDBC driver. Add it to your project's build configuration (e.g., Maven's `pom.xml` or Gradle's `build.gradle`).
    ```xml
    <!-- Example Maven Dependency -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.33</version> <!-- Use the latest version -->
    </dependency>
    ```

### 3. User Interface (`com.example.diary.ui.DiaryUI`)
- **Purpose**: Manages the graphical user interface using Java Swing.
- **Features**:
    - Main window with a text area for diary content and an optional title field.
    - Buttons for "Save Entry", "New Entry", "Delete Selected".
    - A table to display a list of existing diary entries (ID, Date, Title).
    - Selection in the table loads the corresponding entry into the text area.
    - Status bar for messages.
    - **Aesthetics**: Custom colors applied to UI elements (backgrounds, text, buttons).
    - **Image Integration**: A placeholder area on the right for displaying an image. To add your own image, place it in `src/main/resources/images/` and update the `ImageIcon` path in `DiaryUI.java`.
- **Logic**: Interacts with `MySQLDiaryDAO` for data operations and `DiaryEntry` DTOs.

## Database Schema (`diary_entries` table)

### SQL Setup Script
Run the following SQL in your MySQL client to set up the database:

```sql
-- 1. Create Database
CREATE DATABASE IF NOT EXISTS diary_db;
USE diary_db;

-- 2. Create Table
CREATE TABLE IF NOT EXISTS diary_entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);
```

### Table Column Details
-   `id`: INT AUTO_INCREMENT PRIMARY KEY
-   `title`: VARCHAR(255) NOT NULL
-   `content`: TEXT NOT NULL
-   `created_at`: TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-   `INDEX idx_created_at (created_at)`: An index for efficient date-based queries.

## Development Workflow
1.  **Setup**: Ensure MySQL is running and you have the MySQL Connector/J JDBC driver added to your project dependencies.
2.  **Create Files**: Generate `gemini.md`, `DiaryEntry.java`, `MySQLDiaryDAO.java`, and `DiaryUI.java` in their respective packages.
3.  **Configure Credentials**: Update `MySQLDiaryDAO.java` with your MySQL database URL, username, and password.
4.  **Run Application**: Compile and run the `DiaryUI` class.
5.  **Add Image**: Place an image file (e.g., `diary_background.png`) in `src/main/resources/images/` and modify `DiaryUI.java` to display it.

```
This `gemini.md` file has been created to outline the project.
```