package PlayerGui;

import Player.AudioPlayer;
import PlaylistEditor.PLEController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class PlayerGUI extends Application {
    public static AudioPlayer player;

    public static void main(String[] args) {
        player = new AudioPlayer();
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/PlayerGui/gui.fxml"));
        stage.setTitle("Groovier Music");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/PlayerGui/icon.png"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/PlayerGui/gui.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}
