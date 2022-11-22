import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;

public class PlayerGuiController {
    @FXML
    private TextField addSongField;

    /*
    @FXML
    private Button newPlaylistButton;

    @FXML
    private Button copyPlaylistButton;

    @FXML
    private Button editPlaylistButton;

    @FXML
    private Button playPlaylistButton;

    @FXML
    private Button deletePlaylistButton;

     */

    public AudioPlayer player = new AudioPlayer();

    /**
     * Add a song to the queue, specified by text in addSongField
     * @param a Event triggered when the user enters text in addSongField
     */
    public void addSong(ActionEvent a) {
        try {
            player.addToQueue(new Song(new File(addSongField.getText())));
            addSongField.clear();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /**
     * Create a new Playlist
     * @param a Event triggered when the user clicks newPlaylistButton
     */
    public void newPlaylist(ActionEvent a) {
        System.out.println("new playlist");
    }

    /**
     * Create a new Playlist by copying an existing one
     * @param a Event triggered when the user clicks copyPlaylistButton
     */
    public void copyPlaylist(ActionEvent a) {
        System.out.println("copy playlist");
    }

    /**
     * Edit the contents of a Playlist
     * @param a Event triggered when the user clicks editPlaylistButton
     */
    public void editPlaylist(ActionEvent a) {
        System.out.println("edit playlist");
    }

    /**
     * Add all songs in a Playlist to the player queue
     * @param a Event triggered when the user clicks playPlaylistButton
     */
    public void playPlaylist(ActionEvent a) {
        System.out.println("play playlist");
    }

    /**
     * Delete a Playlist
     * @param a Event triggered when the user clicks deletePlaylistButton
     */
    public void deletePlaylist(ActionEvent a) {
        System.out.println("delete playlist");
    }
}
