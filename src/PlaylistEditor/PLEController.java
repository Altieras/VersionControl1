package PlaylistEditor;

import Player.Playlist;
import Player.Song;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PLEController implements Initializable {
    @FXML
    private TextField nameField;

    @FXML
    private Button exitSave;

    @FXML
    private GridPane col1, col2, col3;

    /**
     * Loads the editor GUI and sets the Main.editablePlaylist Playlist to a Playlist passed in.
     * @param a Event triggered by clicking the "New" or "Edit" buttons in the main GUI scene.
     * @param p Playlist assigned to Main.editablePlaylist.
     */
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
    }

    /**
     * Handles collection of data from editor text fields and returns window to main GUI scene.
     * @param a Event triggered by clicking either the "Save" or "Cancel" buttons.
     */
    public void exit(ActionEvent a) {
        if (a.getSource() == exitSave) {
            Playlist playlist = new Playlist(nameField.getText());

            int numInvalid = 0;
            for (Node n : col1.getChildren()) {
                if (n instanceof TextField) {
                    try {
                        String text = ((TextField) n).getText();
                        if (text.length() > 0) {
                            playlist.add(new Song(text));
                        }
                    } catch (Exception e) {
                        numInvalid += 1;
                    }
                }
            }

            for (Node n : col2.getChildren()) {
                if (n instanceof TextField) {
                    try {
                        String text = ((TextField) n).getText();
                        if (text.length() > 0) {
                            playlist.add(new Song(text));
                        }
                    } catch (Exception e) {
                        numInvalid += 1;
                    }
                }
            }

            for (Node n : col3.getChildren()) {
                if (n instanceof TextField) {
                    try {
                        String text = ((TextField) n).getText();
                        if (text.length() > 0) {
                            playlist.add(new Song(text));
                        }
                    } catch (Exception e) {
                        numInvalid += 1;
                    }
                }
            }

            playlist.save();

            System.out.println(numInvalid + " failed songs");
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
    }

    /**
     * Inherited method from Initializable interface.
     * Called when the GUI starts.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println("initialized");
    }
}
