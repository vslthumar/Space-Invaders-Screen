
import java.util.Scanner;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.stage.Stage;

/**
 * This is the javaFX class,which takes input from user, process and calculate
 * the output by using user input data, and draw the output of alien's
 * spaceships,houses of player, player's spaceship, and bullets, with score and
 * remaining lives at the top,using Graphics context. May 22,2018.
 *
 * @author Vishal Thumar
 */
public class Assignment1 extends Application {

    /**
     * Sets up the stage and starts the main thread. Your drawing code should
     * NOT go here.
     *
     * @param stage The first stage
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("Space Invaders Screen"); // window title here
        Canvas canvas = new Canvas(510, 650); // canvas size here
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        int rows, columns, score, lives, xPositionPlayerSpaceship, red, blue, green;
        int y1PositionofAlienSpaceship = 0, x1PositionofAlienSpaceship = 0, y2PositionofAlienSpaceship = 0, x2PositionofAlienSpaceship = 0;

        Scanner sc = new Scanner(System.in);

        //***input
        do {
            System.out.print("Enter rows of alien spaceships(between 1 to 10):");
            rows = sc.nextInt();
        } while (rows < 1 || rows > 10);

        do {
            System.out.print("Enter columns of alien spaceships(between 1 to 10):");
            columns = sc.nextInt();
        } while (columns < 1 || columns > 10);

        do {
            System.out.print("Enter score(between 0 to 999999):");
            score = sc.nextInt();
        } while (score < 0 || score > 999999);

        do {
            System.out.print("Enter remaining lives(between 0 to 3):");
            lives = sc.nextInt();
        } while (lives < 0 || lives > 3);

        do {
            System.out.print("Enter x-position of player's spaceship(between 0 to 480):");
            xPositionPlayerSpaceship = sc.nextInt();
        } while (xPositionPlayerSpaceship < 0 || xPositionPlayerSpaceship > 480);

        System.out.println("Enter RGB values for color of player's spaceship(between 0 to 255):");
        do {
            System.out.print("Red: ");
            red = sc.nextInt();
        } while (red < 0 || red > 255);
        do {
            System.out.print("Blue:");
            blue = sc.nextInt();
        } while (blue < 0 || blue > 255);
        do {
            System.out.print("Green:");
            green = sc.nextInt();
        } while (green < 0 || rows > 255);

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 510, 650);
        gc.setFill(Color.RED);

        //***shows score and remainining lives
        gc.setFont(Font.font("Calibri", 20));
        gc.fillText("Score: " + score, 2, 20);
        gc.fillText("LIVES:", 300, 20);

        for (int l = 0; l < lives; l++) {
            gc.setFill(Color.GREEN);
            gc.fillOval(360 + (l * 20), 10, 10, 10);
        }

        //***draws rows and columns of alien spaceships using user input.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                //***processing
                y1PositionofAlienSpaceship = 60 + i * 50;
                y2PositionofAlienSpaceship = 50 + i * 50;
                x1PositionofAlienSpaceship = 10 + j * 50;
                x2PositionofAlienSpaceship = 20 + j * 50;

                //***output
                gc.setFill(Color.WHITE);
                gc.setStroke(Color.RED);
                gc.setLineWidth(3);
                gc.strokeOval(x2PositionofAlienSpaceship, y2PositionofAlienSpaceship, 20, 20);
                gc.fillOval(x2PositionofAlienSpaceship, y2PositionofAlienSpaceship, 20, 20);
                gc.strokeOval(x1PositionofAlienSpaceship, y1PositionofAlienSpaceship, 40, 10);
                gc.fillOval(x1PositionofAlienSpaceship, y1PositionofAlienSpaceship, 40, 10);

            }
        }

        //***draws bullets, randomly placed between alien spaceship's bottom row and houses.
        int bullets = (int) (Math.random() * 3);

        for (int b = 1; b < 10 + bullets; b++) {
            int bulletsXPosition = (int) (Math.random() * x1PositionofAlienSpaceship);
            int bulletsYPosition = (int) (Math.random() * (555 - (y1PositionofAlienSpaceship + 20)));
            gc.setFill(Color.RED);
            gc.setStroke(Color.WHITE);
            gc.strokeOval(10 + bulletsXPosition, y2PositionofAlienSpaceship + 20 + bulletsYPosition, 3, 7);
            gc.fillOval(10 + bulletsXPosition, y2PositionofAlienSpaceship + 20 + bulletsYPosition, 3, 7);
        }

        int houseX1Position = 0, houseX2Position = 0;

        //***draws a row of the houses.
        for (int k = 0; k < 8; k++) {
            houseX1Position = 30 + k * 60;
            houseX2Position = 25 + k * 60;
            gc.setFill(Color.YELLOW);
            gc.fillRect(houseX1Position, 570, 20, 15);
            gc.setFill(Color.BLUE);
            gc.fillRect(houseX2Position, 565, 30, 5);
        }

        //***set the color of player's spaceship using user input.
        gc.setStroke(Color.WHITE);
        gc.setFill(Color.rgb(red, green, blue));

        //***draws and place the player's spaceship at fixed y position and x position given by the user. 
        gc.strokeRect(xPositionPlayerSpaceship + 14, 615, 3, 5);
        gc.strokeOval(xPositionPlayerSpaceship + 5, 620, 20, 10);
        gc.strokeRoundRect(xPositionPlayerSpaceship, 625, 30, 10, 10, 10);
        gc.fillRoundRect(xPositionPlayerSpaceship, 625, 30, 10, 10, 10);
        gc.fillOval(xPositionPlayerSpaceship + 5, 620, 20, 10);
        gc.fillRect(xPositionPlayerSpaceship + 14, 615, 3, 5);
        stage.show();

    }

    /**
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
