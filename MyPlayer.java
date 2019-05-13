import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;


public class MyPlayer implements Player,Runnable {
    private Map map;
    private Position position;
    private ImageView imageView;
    int n;

    MyPlayer(Map map){
        imageView = new ImageView(new Image("qk6pl37rqv6xf629k08y.gif"));
        this.map=map;

        this.position=this.map.getPosition();
        System.out.println(this.position.getX()+" "+this.position.getY());
        imageView.setLayoutX(this.position.getX()*map.getUnit()+5); //bastapky orny
        imageView.setLayoutY(this.position.getY()*map.getUnit()+5); //bastapky orny
        imageView.setFitHeight(35);
        imageView.setFitWidth(35);
        this.map.getChildren().add(imageView);
    }

    @Override
    public void movieRight() {
                if (this.position.getX()+1<map.getSize())
                if (map.getMap()[this.position.getX()+1][this.position.getY()]==0)
                {  position.setX(this.position.getX()+1);
                n=this.position.getX()*map.getUnit()+5;
            this.imageView.setLayoutX(n);}

    }

    @Override
    public void movieLeft() {
        if (this.position.getX()-1>=0)
            if (map.getMap()[this.position.getX()-1][this.position.getY()]==0){
        position.setX(this.position.getX()-1);
        n=(int)(this.position.getX()*map.getUnit()+5);
        this.imageView.setLayoutX(n);
        }

    }

    @Override
    public void movieUp() {
        if (this.position.getY()-1>=0)
            if (map.getMap()[this.position.getX()][this.position.getY()-1]==0) {
                position.setY(this.position.getY() - 1);
               n=(int)(this.position.getY() * map.getUnit()+5);
                this.imageView.setLayoutY(n);
            }

    }

    @Override
    public void movieDown() {
        if (this.position.getY()+1<map.getSize())
            if (map.getMap()[this.position.getX()][this.position.getY()+1]==0)
            {  position.setY(this.position.getY()+1);
               n=(int)(this.position.getY() * map.getUnit()+5);
        this.imageView.setLayoutY(n);}
    }

    @Override
    public Position getPosition() {
        return map.getPosition();
    }

    @Override
    public void run() {

    }
}
