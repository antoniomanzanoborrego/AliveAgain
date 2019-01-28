/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliveagain.AliveAgain;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Antonio Manzano Borrego
 */
public class Main extends Application {
    
//--------------------Variables usadas al inicio del juego, situadas fuera del start porque el reinicio necesita cambiarlas-----------------
        int imagenViewWallA = 10;
        int imagenViewWallA2 = -600;
        int groupMuñecoX = 512;
        int velocidad = 0;   
        float velocidadCity = 0;
        double fondoCityY = 0;
        float groupCityX = 0;   
        float groupFantasmaY1 = 700;
        float groupFantasmaY2 = 1200;
        float groupFantasmaY3 = 1700;
        float groupFantasmaY4 = 2200;
        float groupFantasmaY5 = 2700;
        float groupFantasmaY6 = 3200;
        float groupFantasmaY7 = 3700;
        float groupFantasmaY8 = 4200;
        int groupMuñecoY = 100;
        float dificultad = 3;
        int numVidas = 2;
        int marcador;
        int marcadorAnterior;
        int groupFantasmaX1;
        int groupFantasmaX2;
        int groupFantasmaX3;
        int groupFantasmaX4;
        int groupFantasmaX5;
        int groupFantasmaX6;
        int groupFantasmaX7;
        int groupFantasmaX8;
        
    @Override
    public void start(Stage primaryStage) {
        
        //-----------------Variables ajenas al reinicio del juego--------------------------------------------------------------------
        Fantasma fantasma1 = new Fantasma ();                 
        Fantasma fantasma2 = new Fantasma ();                 
        Fantasma fantasma3 = new Fantasma ();                 
        Fantasma fantasma4 = new Fantasma ();                 
        Fantasma fantasma5 = new Fantasma ();                 
        Fantasma fantasma6 = new Fantasma ();                 
        Fantasma fantasma7 = new Fantasma ();
        Fantasma fantasma8 = new Fantasma ();
        Random randomEnemigosFantasma = new Random();    
        Rectangle rectangleVida = new Rectangle(0, 0, 200, 75);
        
        //-----------------Rectángulos para la colisión del jugador con los laterales-------------------------------------------------------------------
        Rectangle rectangleDerecha = new Rectangle (200, 600);
        Rectangle rectangleIzquierda = new Rectangle (824, 0, 200, 600);
        
        //-----------------Rectángulo del jugador y visibilidad---------------------------------------------------------------------------------
        Rectangle rectangleBoy = new Rectangle (32, 111);
        rectangleBoy.setVisible(false);
        
        //-----------------Adición de imágenes--------------------------------------------------------------------------------------
        Image imagenBoy = new Image(getClass().getResourceAsStream("Imagen/boy.png"));
        Image imagenWallA = new Image(getClass().getResourceAsStream("Imagen/wall_A.jpg"));
        Image imagenCity = new Image(getClass().getResourceAsStream("Imagen/city.jpg"));
        Image imagenVida = new Image(getClass().getResourceAsStream("Imagen/vida.jpg"));
        
        //-----------------Creación de ImageViews------------------------------------------------------------------------------------
        ImageView imagenViewBoy = new ImageView(imagenBoy); 
        ImageView imagenViewWallA_derecha = new ImageView(imagenWallA);
        ImageView imagenViewWallA_izquierda = new ImageView(imagenWallA);
        ImageView imagenViewWallA_derecha2 = new ImageView(imagenWallA);
        ImageView imagenViewWallA_izquierda2 = new ImageView(imagenWallA);
        ImageView imagenViewCity = new ImageView(imagenCity);
        ImageView imagenViewVida1 = new ImageView(imagenVida);
        ImageView imagenViewVida2 = new ImageView(imagenVida);
        
        //-----------------ImagenViews: Posición y tamaño-------------------------------------------------------------------------
        imagenViewCity.setX(0);
        imagenViewCity.setY(0);
        imagenViewCity.setFitWidth(1024);
        imagenViewCity.setFitHeight(2400);
        imagenViewWallA_izquierda.setX(0);
        imagenViewWallA_izquierda.setY(0);
        imagenViewWallA_derecha.setX(824);
        imagenViewWallA_derecha.setY(0);
        imagenViewWallA_izquierda2.setX(0);
        imagenViewWallA_izquierda2.setY(0);
        imagenViewWallA_derecha2.setX(824);
        imagenViewWallA_derecha2.setY(0);
        
        //-----------------Creación y posición de las vidas en un HBox---------------------------------------------------------------------------------------
        HBox cajaVidas = new HBox();
        cajaVidas.getChildren().addAll(imagenViewVida1, imagenViewVida2);
        
        //-----------------Creación y posición de grupoMuñeco---------------------------------------------------------------------------------------
        Group groupMuñeco = new Group ();
            groupMuñeco.getChildren().addAll(rectangleBoy, imagenViewBoy);
        groupMuñeco.setLayoutX(groupMuñecoX);
        groupMuñeco.setLayoutY(groupMuñecoY);
        
        //-----------------Creación de los grupoFantasmaX---------------------------------------------------------------------------------------
        Group groupFantasma1 = new Group ();
            groupFantasma1.getChildren().addAll(fantasma1);
            
        Group groupFantasma2 = new Group ();
            groupFantasma2.getChildren().addAll(fantasma2);
            
        Group groupFantasma3 = new Group ();
            groupFantasma3.getChildren().addAll(fantasma3);
            
        Group groupFantasma4 = new Group ();
            groupFantasma4.getChildren().addAll(fantasma4);
            
        Group groupFantasma5 = new Group ();
            groupFantasma5.getChildren().addAll(fantasma5);
            
        Group groupFantasma6 = new Group ();
            groupFantasma6.getChildren().addAll(fantasma6);
            
        Group groupFantasma7 = new Group ();
            groupFantasma7.getChildren().addAll(fantasma7);
            
        Group groupFantasma8 = new Group ();
            groupFantasma8.getChildren().addAll(fantasma8);
            
        //-----------------Posición de los grupoFantasmaX-------------------------------------------------------------------------
        groupFantasma1.setLayoutY(groupFantasmaY1);
        groupFantasmaX1 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma1.setLayoutX(groupFantasmaX1);
        
        groupFantasma2.setLayoutY(groupFantasmaY2);
        groupFantasmaX2 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma2.setLayoutX(groupFantasmaX2);
        
        groupFantasma3.setLayoutY(groupFantasmaY3);
        groupFantasmaX3 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma3.setLayoutX(groupFantasmaX3);
        
        groupFantasma4.setLayoutY(groupFantasmaY4);
        groupFantasmaX4 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma4.setLayoutX(groupFantasmaX4);
        
        groupFantasma5.setLayoutY(groupFantasmaY5);
        groupFantasmaX5 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma5.setLayoutX(groupFantasmaX5);
        
        groupFantasma6.setLayoutY(groupFantasmaY6);
        groupFantasmaX6 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma6.setLayoutX(groupFantasmaX6);
        
        groupFantasma7.setLayoutY(groupFantasmaY7);
        groupFantasmaX7 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma7.setLayoutX(groupFantasmaX7);
        
        groupFantasma8.setLayoutY(groupFantasmaY8);
        groupFantasmaX8 = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma8.setLayoutX(groupFantasmaX8);
        
        //-----------------Animación Fantasmas---------------------------------------------------------------------------------------
        AnimationTimer animationFantasma = new AnimationTimer (){
        
            @Override
            public void handle (long now) {
                groupFantasmaY1 = groupFantasmaY1 - dificultad;
                groupFantasma1.setLayoutY(groupFantasmaY1);
                groupFantasmaY2 = groupFantasmaY2 - dificultad;
                groupFantasma2.setLayoutY(groupFantasmaY2);
                groupFantasmaY3 = groupFantasmaY3 - dificultad;
                groupFantasma3.setLayoutY(groupFantasmaY3);
                groupFantasmaY4 = groupFantasmaY4 - dificultad;
                groupFantasma4.setLayoutY(groupFantasmaY4);
                groupFantasmaY5 = groupFantasmaY5 - dificultad;
                groupFantasma5.setLayoutY(groupFantasmaY5);
                groupFantasmaY6 = groupFantasmaY6 - dificultad;
                groupFantasma6.setLayoutY(groupFantasmaY6);
                groupFantasmaY7 = groupFantasmaY7 - dificultad;
                groupFantasma7.setLayoutY(groupFantasmaY7);
                groupFantasmaY8 = groupFantasmaY8 - dificultad;
                groupFantasma8.setLayoutY(groupFantasmaY8);
                if (groupFantasmaY1<-30) {
                    groupFantasmaY1 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX1 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma1.setLayoutX(groupFantasmaX1);
                    marcador = marcador + 10;
                }
                if (groupFantasmaY2<-30) {
                    groupFantasmaY2 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX2 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma2.setLayoutX(groupFantasmaX2);
                    marcador = marcador + 10;
                }
                if (groupFantasmaY3<-30) {
                    groupFantasmaY3 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX3 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma3.setLayoutX(groupFantasmaX3);
                    marcador = marcador + 10;
                }
                if (groupFantasmaY4<-30) {
                    groupFantasmaY4 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX4 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma4.setLayoutX(groupFantasmaX4);
                    marcador = marcador + 10;
                }
                if (groupFantasmaY5<-30) {
                    groupFantasmaY5 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX5 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma5.setLayoutX(groupFantasmaX5);
                    marcador = marcador + 10;
                }
                if (groupFantasmaY6<-30) {
                    groupFantasmaY6 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX6 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma6.setLayoutX(groupFantasmaX6);
                    marcador = marcador + 10;
                }
                if (groupFantasmaY7<-30) {
                    groupFantasmaY7 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX7 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma7.setLayoutX(groupFantasmaX7);
                    marcador = marcador + 10;
                }
                if (groupFantasmaY8<-30) {
                    groupFantasmaY8 = randomEnemigosFantasma.nextInt(400) + 1000;
                    groupFantasmaX8 = randomEnemigosFantasma.nextInt(594) + 200;
                    groupFantasma8.setLayoutX(groupFantasmaX8);
                    marcador = marcador + 10;
                }
            };
        };
        
        //-----------------Animación Ciudad---------------------------------------------------------------------------------------
        AnimationTimer animationCity = new AnimationTimer (){
            
            @Override
            @SuppressWarnings("empty-statement")
            public void handle (long now) {
                if (fondoCityY>-1440){
                    imagenViewCity.setY(fondoCityY);
                    fondoCityY = fondoCityY - 0.5;
                };
            };
        };
        
        //-----------------Animación Muñeco---------------------------------------------------------------------------------------
        AnimationTimer animationMuñeco = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                groupMuñecoX = velocidad + groupMuñecoX;
                groupMuñeco.setLayoutX(groupMuñecoX);
                velocidadCity = velocidad/3;      
                groupCityX = velocidadCity + groupCityX;             
                imagenViewCity.setX(groupCityX);
            };
        };
        
        //-----------------Animación Muros---------------------------------------------------------------------------------------
        AnimationTimer animationWall = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                if (imagenViewWallA_izquierda.getY()<-599){
                    imagenViewWallA = 610;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);                    
                }
                else {
                    imagenViewWallA = imagenViewWallA - 5;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);
                };
                if (imagenViewWallA_izquierda2.getY()<-599){
                    imagenViewWallA2 = 610;
                    imagenViewWallA_izquierda2.setY(imagenViewWallA2);
                    imagenViewWallA_derecha2.setY(imagenViewWallA2); 
                }
                else {
                    imagenViewWallA2 = imagenViewWallA2 - 5;
                    imagenViewWallA_izquierda2.setY(imagenViewWallA2);
                    imagenViewWallA_derecha2.setY(imagenViewWallA2);
                };
            };
        };        
        
        //-----------------Animación Dificultad incrementa---------------------------------------------------------------------------------------
        AnimationTimer animationDificultad = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                if (dificultad<12){
                    dificultad = (float) (dificultad + 0.005);
                }
            };
        };
        
        //-----------------Texto Derrota: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text derrota = new Text ("Has perdido");
        derrota.setFont(Font.font(150));
        derrota.setX(50);
        derrota.setY(200);
        derrota.setFill(Color.ORANGE);
        
        //-----------------Texto Puntuación: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text score = new Text ("Puntuación:");
        score.setFont(Font.font(25));
        score.setX(824);
        score.setY(25);
        score.setFill(Color.ORANGE);
        Rectangle rectangleScore = new Rectangle(824, 0, 200, 75);
        
        //-----------------Texto Puntuación: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text marcadorText = new Text ("");
        marcadorText.setFont(Font.font(25));
        marcadorText.setX(824);
        marcadorText.setY(60);
        marcadorText.setFill(Color.ORANGE);
          
        //-----------------Escenario (Pane)---------------------------------------------------------------------------------------
        Pane root = new Pane();
            root.getChildren().addAll(imagenViewCity, imagenViewWallA_derecha, 
                    imagenViewWallA_izquierda, imagenViewWallA_derecha2, 
                    imagenViewWallA_izquierda2, rectangleVida, cajaVidas, groupMuñeco, 
                    groupFantasma1, groupFantasma2, groupFantasma3, groupFantasma4,
                    groupFantasma5, groupFantasma6, groupFantasma7, groupFantasma8,
                    rectangleScore, score, marcadorText);

        //-----------------Animación puntuación---------------------------------------------------------------------------------------
        AnimationTimer animationMarcador = new AnimationTimer (){
            @Override
            public void handle (long now) {
                if (marcador==marcadorAnterior){
                }
                else{
                    System.out.print(marcadorAnterior);
                    marcador = marcadorAnterior;
                    marcadorText.setText(String.valueOf(marcador));
                    System.out.println(marcador);
                }
            }
        };
        
//-----------------Animación choque---------------------------------------------------------------------------------------
        AnimationTimer animationChoque = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                Shape shapeCollision1 = Shape.intersect(rectangleBoy, rectangleDerecha);
                boolean colisionShape1 = shapeCollision1.getBoundsInLocal().isEmpty();
                if (colisionShape1 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                          
                Shape shapeCollision2 = Shape.intersect(rectangleBoy, rectangleIzquierda);
                boolean colisionShape2 = shapeCollision2.getBoundsInLocal().isEmpty();
                if (colisionShape2 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                         
                Shape shapeCollision3 = Shape.intersect(rectangleBoy, fantasma1.circleFantasma);
                boolean colisionShape3 = shapeCollision3.getBoundsInLocal().isEmpty();
                if (colisionShape3 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                
                Shape shapeCollision4 = Shape.intersect(rectangleBoy, fantasma2.circleFantasma);
                boolean colisionShape4 = shapeCollision4.getBoundsInLocal().isEmpty();
                if (colisionShape4 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                
                Shape shapeCollision5 = Shape.intersect(rectangleBoy, fantasma3.circleFantasma);
                boolean colisionShape5 = shapeCollision5.getBoundsInLocal().isEmpty();
                if (colisionShape5 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                
                Shape shapeCollision6 = Shape.intersect(rectangleBoy, fantasma4.circleFantasma);
                boolean colisionShape6 = shapeCollision6.getBoundsInLocal().isEmpty();
                if (colisionShape6 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                
                Shape shapeCollision7 = Shape.intersect(rectangleBoy, fantasma5.circleFantasma);
                boolean colisionShape7 = shapeCollision7.getBoundsInLocal().isEmpty();
                if (colisionShape7 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                
                Shape shapeCollision8 = Shape.intersect(rectangleBoy, fantasma6.circleFantasma);
                boolean colisionShape8 = shapeCollision8.getBoundsInLocal().isEmpty();
                if (colisionShape8 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                
                Shape shapeCollision9 = Shape.intersect(rectangleBoy, fantasma7.circleFantasma);
                boolean colisionShape9 = shapeCollision9.getBoundsInLocal().isEmpty();
                if (colisionShape9 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
                
                Shape shapeCollision10 = Shape.intersect(rectangleBoy, fantasma8.circleFantasma);
                boolean colisionShape10 = shapeCollision10.getBoundsInLocal().isEmpty();
                if (colisionShape10 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationMuñeco.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                    }                
                };
            };
        };
        
        //-----------------Inicio de animaciones---------------------------------------------------------------------------------------
        animationDificultad.start();
        animationChoque.start();
        animationWall.start();
        animationCity.start();
        animationMuñeco.start();
        animationFantasma.start();
        animationMarcador.start();
        
        //-----------------Inicio Escena---------------------------------------------------------------------------------------
        Scene scene = new Scene(root, 1024, 600);
        
        //-----------------Animación Fantasmas---------------------------------------------------------------------------------------
        scene.setOnKeyReleased((KeyEvent teclasoltada) -> {
            velocidad = 0;            
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent teclapulsada) {
                switch(teclapulsada.getCode()) {
                    
                    case LEFT:
                        velocidad = -5;
                        break;
                    case RIGHT:
                        velocidad = 5;
                        break;
                }
            }
        });
        
        primaryStage.setTitle("Alive Again");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void reinicio (){        
        groupMuñecoY = 100;
        groupMuñecoX = 512;
        velocidad = 0;
        dificultad = 3;
        
        groupFantasmaY1 = 700;
        groupFantasmaY2 = 1200;
        groupFantasmaY3 = 1700;
        groupFantasmaY4 = 2200;
        groupFantasmaY5 = 2700;
        groupFantasmaY6 = 3200;
        groupFantasmaY7 = 3700;
        groupFantasmaY8 = 4200;
    }
}