import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private Map map;                 // открываем
    private MyBotPlayer player;
   // private MyPlayer myPlayer;//  все
    private Food food;               // необходимое
    private Stage window;
    private Scene scene1,scene2;
    private ImageView imageView1;
    private Label scorelabel;
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        String file = "C:\\Users\\Мади\\IdeaProjects\\Main\\src\\imagine-dragons-believer-(mp3-you.net).mp3"; //открываем музыку
        Media media = new Media(new File(file).toURI().toString());
        MediaPlayer thunder = new MediaPlayer(media);
        thunder.play();  //запускаем

        StackPane layout = new StackPane();

        imageView1 = new ImageView(new Image("RQSU.gif"));  // гифка первого окна

        Button play = new Button("Play");     //создаем кнопку PLAY
        play.getStyleClass().add("start");         //добавляем css start
        play.setMinWidth(100);                     //размер кнопки  play ширина
        play.setMinHeight(50);                     //размер кнопки  play высота
        layout.getChildren().addAll(imageView1,play);   //кнопку и

        play.setOnAction(x ->{
            try {

                map = new Map("map0.txt");
                player=new MyBotPlayer(map);
                food=new Food(map,player);
                map.setOnKeyPressed(e->{
                            switch (e.getCode())
                            {
                                case DOWN:  player.movieDown();
                                    break;
                                case UP:    player.movieUp();
                                    break;
                                case RIGHT: player.movieRight();
                                    break;
                                case LEFT: player.movieLeft();
                                    break;
                                case Q:player.feed(food);player.eat();break;
                                case W:player.feed(food);player.traverse();break;
                            }
                        }

                );
                HBox score = new HBox(10);
                scorelabel = new Label();
                scorelabel.setText(food.getPoints()+"");
                score.getChildren().add(scorelabel);
                VBox vBox = new VBox();
                vBox.getChildren().addAll(map,score);
                scene1=new Scene(vBox);
                scene1.getStylesheets().add("style.css");
                primaryStage.setScene(scene1);

                map.requestFocus();
                System.out.println(map.getPosition().getX()+" "+map.getPosition().getY());

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        } );



        scene2 = new Scene(layout,500,450);
        scene2.getStylesheets().add("style.css");
        primaryStage.setScene(scene2);
        primaryStage.getIcons().add(new Image("giphy.gif"));
        primaryStage.setTitle("EATER");
        primaryStage.show();
    }
}
