This project implements a puzzle game inspired by "Drop the Number." It is developed within the Software Engineering course and utilizes object-oriented design principles and design patterns to create robust and reusable software.

## How the Game Was Made

The game was developed using the Java programming language, applying principles and design patterns to ensure a clear and modular structure. The development stages include:

- **Analysis:** Understanding the rules and mechanics of the original game "Drop the Number."
- **Design:** Creating the software architecture using design patterns.
- **Implementation:** Coding the game functionalities in Java.
- **Testing:** Verifying the correctness of functionalities using JUnit unit tests.

## Design Patterns Used

- **MVC (Model-View-Controller):** Separates the application's logic from the user interface.
  - **Model:** Manages the game's data and logic.
  - **View:** Displays the graphical interface of the game.
  - **Controller:** Manages user interactions and updates the Model and View.

- **Singleton:** Ensures that certain classes have a single instance (e.g., score manager).

- **Flyweight:** Optimizes memory usage by sharing common state between objects (e.g., game pieces).

- **State:** Allows changing the behavior of game pieces based on their internal state.

- **Factory Method:** Provides an interface for creating objects, allowing subclasses to decide which objects to create (e.g., creating game pieces).

## How to Play

- **Start:** The game begins by pressing the start button, causing the first piece with the number 2 to fall on a random column.
- **Control:** The player can choose the column where the next piece will fall using the mouse.
- **Combinations:** Pieces with the same value combine to create pieces with higher values (powers of 2).
- **Score:** The score increases when pieces of the same value combine. The score is double the value of the combined piece.
- **End of the Game:** The game ends when a column is completely filled, and no more moves are possible.
- **Pause and Reset:** The player can pause or reset the game at any time.
