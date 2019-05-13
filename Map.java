import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map extends Pane  {
    String line;
    private int unit;
    private int size;
    private int[][] map;
    private Position position;
    Map(String txt) throws FileNotFoundException {
        File file = new File("C:\\Users\\Мади\\IdeaProjects\\Main\\src\\map0.txt");
            Scanner output = new Scanner(file);
            size = Integer.parseInt(output.nextLine());
            Rectangle rec;
            unit = 40;
            int j=0;
            map=new int[size][size];
            while (output.hasNextLine()){
                line=output.nextLine();
                String[] kletka=line.split(" ");
                for (int i=0;i<kletka.length;i++){
                    rec=new Rectangle(unit,unit);
                    if (kletka[i].equals("1")){
                        rec.setFill(Color.RED);
                        map[i][j] = 1;
                    }
                    else if(kletka[i].equals("0")){
                        rec.setFill(Color.BLACK);
                        map[i][j] =0;
                    }
                    else if(kletka[i].equals("2")){
                        rec.setFill(Color.BLACK);
                        map[i][j] =0;
                        position = new Position(i,j);
                    }
                    rec.setStroke(Color.WHITE);
                    rec.setX(i*40);
                    rec.setY(j*40);
                    getChildren().add(rec);
                }
                j++;
            }
    }
    public int getUnit(){
        return this.unit;
    }

    public int getSize() {
        return this.size;
    }

    public int[][] getMap() {
        return this.map;
    }

    public Position getPosition() {
        return this.position;
    }
}
