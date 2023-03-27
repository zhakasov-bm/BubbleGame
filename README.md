# BubbleGame
Bubble Game is a simple game created using JavaFX. The objective of the game is to click on the moving bubble within the time limit to increase the score. If the player fails to click on the bubble within the time limit, the game ends.


## How to Play
1. Launch the application.
2. Click on the "PLAY" button on the main menu.
3. Enter your nickname in the text field and click the "START" button.
4. Click on the bubble that appears on the screen to increase your score.
5. Repeat step 4 until the time limit runs out.
6. The game will end, and the player's score will be displayed on the game over screen.
7. On the game over screen, the player can either play again, exit the game or view the list of high scores.

## Data Fields
### Scenes
* firstScene: The main menu scene.
* gameScene: The game scene.
* gameOverScene: The game over scene.
* playersScene: The high scores scene.
* nameScene: The nickname entry scene.

### Panes
* firstMenuPane: The main menu pane.
* root: The pane for the "PLAY" button and the header text.
* namePane: The nickname entry pane.
* ballPane: The game pane.
* gamePane: The game over pane.
* gameOverMenuPane: The pane for the game over menu.
* playersPane: The pane for the high scores list.

### Buttons
* playBT: The "PLAY" button on the main menu.
* startBT: The "START" button on the nickname entry pane.
* playAgainBT: The "PLAY AGAIN" button on the game over menu.
* exitBT: The "EXIT" button on the game over menu.
* resultBT: The "VIEW HIGH SCORES" button on the game over menu.
* backBT: The "BACK" button on the high scores list.

### Labels
* scoreLabel: The label for the player's score.
* timeLabel: The label for the time remaining in the game.
* lastScoreText: The label for the player's score on the game over menu.
* nickLabel: The label for the player's nickname on the high scores list.
* nickname: The label for the nickname entry.

### Text Fields
* textfield: The text field for entering the player's nickname.
* tId: The text field for displaying the player's rank on the high scores list.
* tNick: The text field for displaying the player's nickname on the high scores list.
* tScore: The text field for displaying the player's score on the high scores list.

### Shapes
* divider: The line that divides the game pane.
* ballGame: The moving bubble on the game

## Preview
https://user-images.githubusercontent.com/126884891/226696749-dc442371-361c-4f2e-a268-3dddadae13eb.mov
