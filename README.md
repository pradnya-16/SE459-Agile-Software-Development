README: Rogue Game
 This project is a simple Java-based 2D Rogue-style game implemented using Java Swing. The game involves a player navigating rooms, avoiding a monster, and reaching a staircase to proceed to the next level. 
🎮 Features
 - Player movement using arrow keys. - Two rooms with room-specific movement logic. - Monster that follows and attacks the player. - Player health and score tracking. - Staircase for level transition. - Random player name assignment. 
📁 Project Structure
 src/ │ ├── main/ │   └── java/ │       └── se459/ │           └── agile/ │               ├── Main.java │               ├── Levels/ │               │   └── Level1.java │               ├── Player/ │               │   ├── Player.java │               │   └── Monster.java │               └── UI/ │                   └── Window.java │ ├── out/ (compiled class files) ├── test/     └── java/         └── se459/             └── agile/                 └── AppTest.java 
🚀 How to Run
 1. Compile the Java files using your IDE or command line. 2. Run the Main.java file. 3. Use arrow keys to move the player. 4. Avoid the monster and reach the staircase to proceed. 
📌 Controls
 - Arrow Keys: Move the player 
🧠 Game Logic
 - The monster follows the player and reduces player health on collision. - If player health reaches 0, the game ends. - The player gains score with every movement. 
👩‍💻 Author
Pradnya Kadam
