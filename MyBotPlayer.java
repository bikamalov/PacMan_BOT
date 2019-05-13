import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyBotPlayer implements Player,Runnable { //Открываем класс который реализует интерфейс Player , Runnable
    private Map map;
    private Position position;
    private ImageView imageView;
    int n;
    private Food food;
    private Circle ball;

    MyBotPlayer(Map map) {
        imageView = new ImageView(new Image("qk6pl37rqv6xf629k08y.gif"));
        this.map = map;
        this.position = getPosition();
        ball = new Circle(this.map.getUnit()/2);
        System.out.println(this.position.getX() + " " + this.position.getY());
//        imageView.setLayoutX(this.position.getX()*map.getUnit()+5); //bastapky orny
//        imageView.setLayoutY(this.position.getY()*map.getUnit()+5); //bastapky orny
//        imageView.setFitHeight(35);
//        imageView.setFitWidth(35);
//        this.map.getChildren().add(imageView);
        ball.setCenterY(position.getY()* this.map.getUnit() + this.map.getUnit()/2);  //начальная позция плейера
        ball.setCenterX(position.getX()* this.map.getUnit() + this.map.getUnit()/2);  //начальная позция плейера
        ball.setFill(Color.RED);   //цвет плейера
         this.map.getChildren().add(ball);  //добавляем ball на карту
    }

    public void feed(Food f){
        this.food = f;
    }
    public void eat() {  //метод второго бота
        Thread threadeat = new Thread(new Runnable() {   //создаем поток с Runnable в котором есть метод run
            @Override
            public void run() {
                if(getPosition().getX()<food.getPosition().getX()){   //если икс плейера меньше икса фуда
                    for(int i=getPosition().getX();i<=food.getPosition().getX();i++){  //то мы стартуем от позиций плейера,i++ пока не дойдем до фуда
                        ball.setCenterX(i*map.getUnit()+map.getUnit()/2);
                       // position.setX(this.position.getX()+1);
                        //n=this.position.getX()*map.getUnit()+5;//шаг плейера
                        getPosition().setX(i); //изменяет координату ball
                        try {
                            Thread.sleep(100); //остонавливает исполнение текущего потока
                        }
                        catch (InterruptedException e){ //InterruptedException сигнализирует о том, что поток просит завершить его работу.
                            e.printStackTrace();
                        }
                    }

                }
                if(getPosition().getX()>food.getPosition().getX()){  //если позиция икса плейера больше чем позиция фуда
                    for(int i=getPosition().getX();i>=food.getPosition().getX();i--){  //то мы стартуем от позиций плейера,i-- пока не дойдем до фуда
                        ball.setCenterX(i*map.getUnit()+map.getUnit()/2);  //шаг плейера
                        getPosition().setX(i);   ////изменяет координату ball
                        try {
                            Thread.sleep(100);  //остонавливает исполнение текущего потока
                        }
                        catch (InterruptedException e){  //InterruptedException сигнализирует о том, что поток просит завершить его работу.
                            e.printStackTrace();
                        }
                    }

                }
                if(getPosition().getY()<food.getPosition().getY()){   //если позиций уай плейера меньше позиций уай фуда
                    for(int i=position.getY();i<=food.getPosition().getY();i++){ //то i++ пока не дойдем до фуда
                        ball.setCenterY(i*map.getUnit()+map.getUnit()/2); //шаг плейера
                        getPosition().setY(i);  ////изменяет координату ball
                        try {
                            Thread.sleep(100);   //остонавливает исполнение текущего потока
                        }
                        catch (InterruptedException e){   //InterruptedException сигнализирует о том, что поток просит завершить его работу.
                            e.printStackTrace();
                        }
                    }

                }
                if(getPosition().getY()>food.getPosition().getY()){   //если позиций уай плейера больше позиций уай фуда
                    for(int i=position.getY();i>=food.getPosition().getY();i--){    //то i-- пока не дойдем до фуда
                        ball.setCenterY(i*map.getUnit()+map.getUnit()/2);   //шаг плейера
                        getPosition().setY(i);     ////изменяет координату ball
                        try {
                            Thread.sleep(100);   //остонавливает исполнение текущего потока
                        }
                        catch (InterruptedException e){    //InterruptedException сигнализирует о том, что поток просит завершить его работу.
                            e.printStackTrace();
                        }
                    }

                }

            }
        });
        threadeat.start(); //старт поток
       // this.eat();
    }

    public void traverse() {  //метод снейк бота
        Thread threadsnake = new Thread(new Runnable() {   //создаем поток с Runnable в котором есть метод run
            @Override
            public void run() {
                //System.out.println(foodpos.getX() + " " + foodpos.getY());
                boolean s = true;  //если t равен true  то плейер двигается,в противном случае плейер останавливается
                for (int i = 0; i < map.getSize(); i++) { //двигаемся от i до map size
                    if (!s) break;   //если t равен false плейер останавливается
                    if (i % 2 == 0) {  //если i четное число
                        for (int j = 0; j < map.getSize(); j++) {    //то плейер двигается слева направо

                            ball.setCenterX(j * map.getUnit() + map.getUnit() / 2);   //шаг плейера
                            ball.setCenterY(i * map.getUnit() + map.getUnit() / 2);    //шаг плейера
                            getPosition().setX(j);   ////изменяет координату ball
                            getPosition().setX(i);   ////изменяет координату ball
                            if (getPosition().equals(food.getPosition())) {   //если позиция плейера равен позиции фуда
                                s = false;  //то t равен false и плейер больше не двигается
                                break;
                            }
                            try {
                                Thread.sleep(100);    //остонавливает исполнение текущего потока
                            } catch (InterruptedException ex) {   //InterruptedException сигнализирует о том, что поток просит завершить его работу.
                                Logger.getLogger(MyBotPlayer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } else {   //если i нечетное число то
                        for (int j = map.getSize() - 1; j >= 0; j--) {  //плейер двигается справа налево
                            ball.setCenterX(j * map.getUnit() + map.getUnit() / 2);  //шаг плейера
                            ball.setCenterY(i * map.getUnit() + map.getUnit() / 2);  //шаг плейера
                            getPosition().setX(j);   ////изменяет координату ball
                            getPosition().setY(i);   ////изменяет координату ball
                            if (getPosition().equals(food.getPosition())) {   //если позиция плейера равен позиции фуда
                                s = false;   //то t равен false и плейер больше не двигается
                                break;
                            }
                            try {
                                Thread.sleep(200);    //остонавливает исполнение текущего потока
                            } catch (InterruptedException ex) {    //InterruptedException сигнализирует о том, что поток просит завершить его работу.
                                Logger.getLogger(MyBotPlayer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        });
        threadsnake.start();
    }

    @Override
    public void movieRight() {
        if (this.position.getX() + 1 < map.getSize())
            if (map.getMap()[this.position.getX() + 1][this.position.getY()] == 0) {
                position.setX(this.position.getX() +1);
                n = this.position.getX() * map.getUnit()+19;
                this.ball.setCenterX(n);
            }

    }

    @Override
    public void movieLeft() {
        if (this.position.getX() - 1 >= 0)
            if (map.getMap()[this.position.getX() - 1][this.position.getY()] == 0) {
                position.setX(this.position.getX() - 1);
                n =this.position.getX() * map.getUnit()+19;
                this.ball.setCenterX(n);
            }

    }

    @Override
    public void movieUp() {
        if (this.position.getY() - 1 >= 0)
            if (map.getMap()[this.position.getX()][this.position.getY() - 1] == 0) {
                position.setY(this.position.getY() - 1);
                n = this.position.getY() * map.getUnit()+19;
                this.ball.setCenterY(n);
            }

    }

    @Override
    public void movieDown() {
        if (this.position.getY() + 1 < map.getSize())
            if (map.getMap()[this.position.getX()][this.position.getY() + 1] == 0) {
                position.setY(this.position.getY() + 1);
                n =this.position.getY() * map.getUnit()+19;
                this.ball.setCenterY(n);
            }
    }

    @Override
    public Position getPosition() {
        return map.getPosition();
    }

    @Override
    public void run() {

    }

//    @Override
//    public void run() {
//
//    }
//@Override
//public void movieRight() {
//    if (this.position.getX()+1<map.getSize())
//        if (map.getMap()[this.position.getX()+1][this.position.getY()]==0)
//        {  position.setX(this.position.getX()+1);
//            n=this.position.getX()*map.getUnit()+5;
//            this.imageView.setLayoutX(n);}
//
//}
//
//    @Override
//    public void movieLeft() {
//        if (this.position.getX()-1>=0)
//            if (map.getMap()[this.position.getX()-1][this.position.getY()]==0){
//                position.setX(this.position.getX()-1);
//                n=(int)(this.position.getX()*map.getUnit()+5);
//                this.imageView.setLayoutX(n);
//            }
//
//    }
//
//    @Override
//    public void movieUp() {
//        if (this.position.getY()-1>=0)
//            if (map.getMap()[this.position.getX()][this.position.getY()-1]==0) {
//                position.setY(this.position.getY() - 1);
//                n=(int)(this.position.getY() * map.getUnit()+5);
//                this.imageView.setLayoutY(n);
//            }
//
//    }
//
//    @Override
//    public void movieDown() {
//        if (this.position.getY()+1<map.getSize())
//            if (map.getMap()[this.position.getX()][this.position.getY()+1]==0)
//            {  position.setY(this.position.getY()+1);
//                n=(int)(this.position.getY() * map.getUnit()+5);
//                this.imageView.setLayoutY(n);}
//    }
//
//    @Override
//    public Position getPosition() {
//        return map.getPosition();
//    }
}