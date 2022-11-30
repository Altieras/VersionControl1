package PlaylistEditor;

import Player.Playlist;
import PlayerGui.PlayerGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PLEController implements Initializable {
    @FXML
    private TextField nameField;

    @FXML
    private Button exitSave;


    public void activate(ActionEvent a, Playlist p) {
        try {
            Stage stage = (Stage)((Node) a.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/PlaylistEditor/ple.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/PlaylistEditor/ple.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
            PlayerGUI.editablePlaylist = p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("activated");
        System.out.println(p);
        System.out.println(PlayerGUI.editablePlaylist);
    }

    public void exit(ActionEvent a) {
        if (a.getSource() == exitSave) {
            // Gather information from text fields
            Playlist playlist = new Playlist(nameField.getText());

            playlist.save();
        }

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
        System.out.println(PlayerGUI.editablePlaylist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialized");
    }
}
