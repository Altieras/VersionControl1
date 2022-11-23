package PlayerGui;

import PlaylistEditor.PLEController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Player.*;


public class PlayerGuiController implements Initializable {
    @FXML
    private TextField addSongField;

    @FXML
    private ChoiceBox<String> playlistChoiceBox;

    private String activePlaylistName;


    /**
     * Add a song to the queue, specified by text in addSongField
     * @param a Event triggered when the user enters text in addSongField
     */
    public void addSong(ActionEvent a) {
        try {
            PlayerGUI.player.addToQueue(new Song(new File(addSongField.getText())));
            addSongField.clear();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /**
     * Create a new Playlist
     * @param a Event triggered when the user clicks newPlaylistButton
     */
    // TODO: COMPLETE THIS METHOD
    public void newPlaylist(ActionEvent a) {
        new PLEController().activate(a, new Playlist("New Playlist"));
    }

    /**
     * Create a new Playlist by copying an existing one
     * @param a Event triggered when the user clicks copyPlaylistButton
     */
    // TODO: COMPLETE THIS METHOD
    public void copyPlaylist(ActionEvent a) {
        Playlist original = new Playlist(new File("/data/" + activePlaylistName + ".playlist"));
        Playlist copy = new Playlist(original);
        copy.setName(copy.getName() + " - Copy");
        copy.save();
    }

    /**
     * Edit the contents of a Playlist
     * @param a Event triggered when the user clicks editPlaylistButton
     */
    // TODO: COMPLETE THIS METHOD
    public void editPlaylist(ActionEvent a) {
        Playlist p = new Playlist(new File("./data/" + activePlaylistName + ".playlist"));
        new PLEController().activate(a, p);
    }

    /**
     * Add all songs in a Playlist to the player queue
     * @param a Event triggered when the user clicks playPlaylistButton
     */
    // TODO: COMPLETE THIS METHOD
    public void playPlaylist(ActionEvent a) {
        System.out.println("play playlist");
    }

    /**
     * Delete a Playlist
     * @param a Event triggered when the user clicks deletePlaylistButton
     */
    public void deletePlaylist(ActionEvent a) {
        File playlistFile = new File(".\\data\\" + activePlaylistName + ".playlist");

        if (playlistFile.delete()) {
            System.out.println("Playlist deleted");
            updateExistingPlaylists();
        } else {
            System.out.println("Error");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateExistingPlaylists();
        playlistChoiceBox.setOnAction(this::updateActivePlaylist);
    }

    /**
     * Update the String active playlist when the user selects a different playlist.
     * @param event Event triggered by the user clicking an option in the playlist choice box.
     */
    public void updateActivePlaylist(ActionEvent event) {
        activePlaylistName = playlistChoiceBox.getValue();
    }

    /**
     * Update the playlist choice box with all playlists.
     * Invoked when a playlist is created or deleted.
     */
    public void updateExistingPlaylists() {
        File[] playlists = new File(".\\data").listFiles();
        playlistChoiceBox.getItems().clear();
        for (File f: playlists) {
            playlistChoiceBox.getItems().add(f.getName().substring(0, f.getName().indexOf('.')));
        }
    }

}
