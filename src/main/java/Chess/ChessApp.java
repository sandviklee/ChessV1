package Chess;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ChessApp extends Application {

  private void UpdateChessboard(Group g) throws FileNotFoundException {
    g.getChildren().clear();
    g.getChildren().add(Chess.chessboard.ChessboardView());

    g.getChildren().addAll(Chess.chessboard.MatrixToFXML());
    try {
      g.getChildren().add(FXMLLoader.load(ChessApp.class.getResource("Chessboard.fxml")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void start(Stage primaryStage) throws Exception { 
    primaryStage.setTitle("Chess");
    Group root = new Group();
    root.getChildren().add(Chess.chessboard.ChessboardView());
    root.getChildren().addAll(Chess.chessboard.MatrixToFXML());
    Scene scene = new Scene(root);

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        Runnable updater = new Runnable() {
          @Override
          public void run() {
            try {
              UpdateChessboard(root);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
        };
          while (true) {
            try {
              Thread.sleep(1500);
            } catch (InterruptedException e) {
              }
              Platform.runLater(updater);
              
          }
        }
      });
    
    thread.setDaemon(true);
    thread.start();
    
    
    root.getChildren().add(FXMLLoader.load(ChessApp.class.getResource("Chessboard.fxml")));
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static void main(String[] args) {
    ChessApp.launch(args);
  }
}