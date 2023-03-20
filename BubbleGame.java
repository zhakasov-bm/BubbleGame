import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.animation.*;
import java.util.ArrayList;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.scene.Group;
import java.lang.Math;

public class BubbleGame extends Application {
	private final int startScore = 0;
	private int count = startScore;
	private final int startTime = 30;
  	private int seconds = startTime;
    private static final double WIDTH = 600;
    private static final String GAME_PANE_COLOR = "#F0F8FF";
    private static final Font FONT_FOR_TEXT = Font.font("Courier", FontWeight.BOLD,FontPosture.ITALIC, 30);

    //all Scenes for programm 
    private Scene firstScene;
    private Scene gameScene;
    private Scene gameOverScene;
    private Scene playersScene;
    private Scene nameScene;

    //main menu Pane
    private GridPane firstMenuPane;
    private BorderPane root;

    //main menu Pane elements
    private Button playBT;
    private int score;

    //game Pane
    private Pane ballPane;
    private BorderPane gamePane;

    // game Pane elements
    private Label scoreLabel;
    private Label timeLabel;
    private Line divider;
    private Circle ballGame;
    

    //game over menu Pane
    private VBox gameOverMenuPane;
    
    //game over menu Pane elements
    private Text lastScoreText;
    private Circle circle;
    private Button playAgainBT;
    private Button exitBT;
    private Button resultBT;

    //Animation data fields
    private Timeline timerAnimation;

    //NickName Pane and elements
    private GridPane namePane;
    private Button startBT;
    private TextField textfield;
    private Label nickname;

    //Players List
    private GridPane playersPane;
    private Button backBT;
    private Label nickLabel;
    private ArrayList<String> players;
    private ArrayList<Integer> playersScore;

    private TextField tId;
  	private TextField tNick;
  	private TextField tScore;
 

    @Override
    public void start(Stage primaryStage) throws Exception {

        initLayout(primaryStage);
        initHandlers(primaryStage);
        //initAnimation(primaryStage);

        primaryStage.setTitle("Bubble game");
        primaryStage.setScene(firstScene);
        primaryStage.show();
        primaryStage.setResizable(false); // so that the size does not change
        
    }

    public void initLayout(Stage stage) {
        //-----------------------------------------StartPane---------------------------------------------
        firstMenuPane = new GridPane();
        firstMenuPane.setAlignment(Pos.CENTER);
        
        //setBackgroundImage
        ImageView img = new ImageView(new Image("images/bubble.gif"));
        img.fitWidthProperty().bind(stage.widthProperty()); 
        img.fitHeightProperty().bind(stage.heightProperty()); 
       	
       	root = new BorderPane();				// Pane for text and button
        root.setPadding(new Insets(100, 20, 0, 20));

        playBT = new Button("PLAY");
        playBT.setFont(FONT_FOR_TEXT);
        playBT.setStyle("-fx-text-fill: #38C3FE;");

        Text header = new Text("Bubble Game!");  //Create Text
        header.setFont(Font.font("Courier", FontWeight.BOLD,FontPosture.ITALIC, 70));
        header.setStyle("-fx-stroke: #38C3FE;");
        header.setFill(Color.WHITE);

        root.setCenter(playBT);
        root.setTop(header);

        root.setAlignment(header, Pos.TOP_CENTER);

        firstMenuPane.getChildren().addAll(img, root);

        //---------------------------------------NamePane----------------------------------------------- 
        namePane = new GridPane();
        namePane.setAlignment(Pos.CENTER);
        namePane.setHgap(5);
        namePane.setVgap(5);
        namePane.setStyle("-fx-background-color : " + GAME_PANE_COLOR);
		
        
        textfield = new TextField();
      

        startBT = new Button("START");
        nickname = new Label("NickName:");
        nickname.setFont(Font.font("Courier",FontWeight.BOLD, 20));

        namePane.add(nickname, 0, 0);
        namePane.add(textfield, 1, 0);
        namePane.add(startBT, 1, 1);
        

        //---------------------------------------GamePane----------------------------------------------- 
       
        ballPane = new Pane();
        ballPane.setMaxHeight(500);
        ballPane.setMaxWidth(550);

        ballGame = new Circle(180, 250, 30);
		ballPane.getChildren().add(ballGame);

        divider = new Line(0, 50, WIDTH, 50);
        divider.setStyle("-fx-stroke: #38C3FE; -fx-stroke-width: 1; ");

        scoreLabel = new Label();
        scoreLabel.setFont(FONT_FOR_TEXT);
        scoreLabel.setText("Score: " + String.valueOf(startScore));

        timeLabel = new Label();
        timeLabel.setFont(FONT_FOR_TEXT);

       	gamePane = new BorderPane();
       	gamePane.setPadding(new Insets(5, 10, 10, 10));
        gamePane.setStyle("-fx-background-color : " + GAME_PANE_COLOR);
        gamePane.setRight(timeLabel);
       	gamePane.setLeft(scoreLabel);
        gamePane.setCenter(ballPane);
        gamePane.getChildren().add(divider);

        //--------------------------------------GameOverPane--------------------------------------------

        gameOverMenuPane = new VBox(10);
        gameOverMenuPane.setAlignment(Pos.CENTER);
        gameOverMenuPane.setPadding(new Insets(10));
        gameOverMenuPane.setStyle("-fx-background-color : " + GAME_PANE_COLOR);

        StackPane result = new StackPane();

        circle = new Circle(300, 300, 100);
        circle.setStyle("-fx-stroke: #38C3FE;; -fx-fill: white; -fx-stroke-width: 10px");

        lastScoreText = new Text(); 
        lastScoreText.setFont(FONT_FOR_TEXT);
        lastScoreText.setStyle("-fx-fill: #38C3FE;");

        result.getChildren().addAll(circle, lastScoreText);

        playAgainBT = new Button("PLAY AGAIN");
        resultBT = new Button("RESULTS");
        exitBT = new Button("EXIT");
    
        gameOverMenuPane.getChildren().addAll(result, playAgainBT,resultBT, exitBT);

        //--------------------------------------------PlayerList----------------------------------------------

        players = new ArrayList<String>();
        playersScore = new ArrayList<Integer>();

        nickLabel = new Label();
        nickLabel.setFont(FONT_FOR_TEXT);
    	playersPane = new GridPane();
    	playersPane.setPadding(new Insets(20));
    	playersPane.setAlignment(Pos.CENTER);
    	playersPane.setVgap(5);
    	playersPane.setHgap(5);
    	
    	backBT = new Button("BACK");
    	
        ArrayList<Label> la = new ArrayList<Label>();
        la.add(new Label(" ID "));
        la.add(new Label("NickName"));
        la.add(new Label("Score"));
        for(int i=0; i<la.size();i++){
            la.get(i).setFont(Font.font("Courier", FontWeight.BOLD, 20));
        }
    	
        playersPane.add(la.get(0),0 ,0);
    	playersPane.add(la.get(1), 1, 0);
    	playersPane.add(la.get(2), 2, 0);

    	ScrollPane spVertical = new ScrollPane();      		//ScrollPane for players List
        spVertical.setContent(playersPane);
        spVertical.setStyle("-fx-background: #F0F8FF; -fx-border-color: #F0F8FF;");
        spVertical.setMinViewportWidth(300);

        VBox scorePane = new VBox(5);
        scorePane.setPadding(new Insets(10));
        scorePane.setStyle("-fx-background-color : " + GAME_PANE_COLOR);
        scorePane.getChildren().addAll(backBT ,spVertical);


        //--------------------------------------------SetScene----------------------------------------------
        
        firstScene = new Scene(firstMenuPane,WIDTH,WIDTH);
        nameScene = new Scene(namePane, WIDTH, WIDTH);
        gameScene = new Scene(gamePane, WIDTH, WIDTH);
        gameOverScene = new Scene(gameOverMenuPane, WIDTH, WIDTH);
        playersScene = new Scene(scorePane, WIDTH, WIDTH);
    }

    //--------------------------------------------initHandlers----------------------------------------------

    public void initHandlers(Stage primaryStage) {
        playBT.setOnAction(e -> primaryStage.setScene(nameScene));
        startBT.setOnAction(e -> {
            primaryStage.setScene(gameScene); 
            nickLabel.setText(textfield.getText());
            
            if(nickLabel.getText().equals("")){ // if it is empty, the default nickName is player(n)
        		players.add("player" + String.valueOf(players.size() + 1));
            }else{
            	players.add(nickLabel.getText()); // add nickNames to ArrayList
            }

        	textfield.setText(""); 
        	initAnimation(primaryStage);

            count = startScore; 
        	scoreLabel.setText("Score: " + String.valueOf(count));
            seconds = startTime;      	
        	timeLabel.setText("Time: " + String.valueOf(seconds));
        });
        
        ballGame.setOnMousePressed(e -> {
        	ballPane.getChildren().remove(ballGame);
           	game(ballGame, ballPane);

            scoreLabel.setText("Score: " + String.valueOf(count+1));
            count++;   
        });

        resultBT.setOnAction(e -> {     //showing results
      		primaryStage.setScene(playersScene);
      		playerList(playersPane, players); // method list of players
    	});
  
        exitBT.setOnAction(e -> System.exit(0));  //finish attempt and exit 
        backBT.setOnAction(e -> primaryStage.setScene(gameOverScene));
        playAgainBT.setOnAction(e -> {
        	primaryStage.setScene(nameScene);
        	textfield.setText("");
        	count = startScore;
        });
    }

    public void initAnimation(Stage primaryStage) {
    	
    	Timeline time = new Timeline();
    	time.setCycleCount(Timeline.INDEFINITE);

    	KeyFrame frame = new KeyFrame(Duration.seconds(1), e ->{
        
        	seconds--;

        	timeLabel.setText("Time: " + String.valueOf(seconds));
          	if(seconds <= 0){
          		time.stop();
          		score = count;
          		lastScoreText.setText("Score: " + String.valueOf(score));
       			playersScore.add(score); //add scores to ArrayList
            	primaryStage.setScene(gameOverScene);
          	}
    	});
    	time.getKeyFrames().add(frame);
    	time.playFromStart();    
    } 

    //game method
    public void game(Circle circle, Pane pane){
        double x = 0 + (Math.random() * (pane.getWidth() + 1)); //Random number from 0 to paneWidth 
        double y = 50 + (Math.random() * ((pane.getHeight() - 50) + 1)); //Random number from 50 to paneHeight
        double r = 7 + (Math.random() * 30); //Random number from 7 to 36;
       	
       	circle.setCenterX(x);
		circle.setCenterY(y);
		circle.setRadius(r);
		
       	circle.setFill(Color.color(Math.random(), Math.random(),Math.random()));
       	circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle); 
    }

    // method contains list and results of players
    public void playerList(GridPane pane, ArrayList<String> players){
    	
    	for(int i=0; i < players.size();i++){
        	tId = new TextField();
        	tId.setAlignment(Pos.CENTER);
            tId.setMaxWidth(30);
        	tId.setText(String.valueOf(i+1));
        	tId.setEditable(false);
        	pane.add(tId, 0, i+1);

        	tNick = new TextField();
        	tNick.setPrefColumnCount(6);
        	tNick.setText(players.get(i));
        	tNick.setEditable(false);
        	pane.add(tNick, 1, i+1);

        	tScore = new TextField();
        	tScore.setPrefColumnCount(1);
        	tScore.setText(String.valueOf(playersScore.get(i)));
        	tScore.setAlignment(Pos.CENTER);
        	tScore.setEditable(false);
        	pane.add(tScore, 2, i+1);
      	}
    }
}

