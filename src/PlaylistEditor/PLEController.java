package PlaylistEditor;

import Player.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PLEController {
    @FXML
    private TextField nameField;


    public void activate(ActionEvent a, Playlist p) {
        try {
            Stage stage = (Stage)((Node) a.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/PlaylistEditor/ple.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/PlaylistEditor/ple.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent a) {
        // Gather information from text fields
        Playlist playlist = new Playlist(nameField.getText());

        playlist.save();

        // Exit scene
        try {
            Stage stage = (Stage)((Node) a.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/PlayerGui/gui.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/PlayerGui/gui.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
