package PlayerGui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import Player.Playlist;
import Player.Song;
import PlaylistEditor.PLEController;


public class PlayerGuiController implements Initializable {
    @FXML
    private TextField addSongField;

    @FXML
    private ChoiceBox<String> playlistChoiceBox;

    @FXML
    private CheckBox shuffleButton;

    @FXML
    private Label errorLabel, queueContents, songTitle;

    private String activePlaylistName;

    /**
     * Add a song to the queue, source specified by text in addSongField
     */
    public void addSong() {
        clearError();
        try {
            PlayerGUI.player.addToQueue(new Song(addSongField.getText()));
            addSongField.clear();
        } catch (Exception e) {
            displayError("Error adding song");
        }
        updateDisplay();
    }

    /**
     * Pause the currently playing song
     */
    public void play() {
        clearError();
        PlayerGUI.player.play();
        updateDisplay();
    }

    /**
     * Continue the current song
     */
    public void pause() {
        clearError();
        PlayerGUI.player.pause();
        updateDisplay();
    }

    /**
     * Skip to the next song
     */
    public void next() {
        clearError();
        PlayerGUI.player.playNext();
        updateDisplay();
    }

    /**
     * Return to the previous song
     */
    public void previous() {
        clearError();
        PlayerGUI.player.playPrevious();
        updateDisplay();
    }

    /**
     * Clear the player queue
     */
    public void clearQueue() {
        clearError();
        PlayerGUI.player.clearQueue();
        updateDisplay();
    }

    /**
     * Create a new Playlist
     * @param a Event triggered when the user clicks newPlaylistButton
     */
    public void newPlaylist(ActionEvent a) {
        new PLEController().activate(a, new Playlist("New Playlist"));
        updateExistingPlaylists();
    }

    /**
     * Create a new Playlist by copying an existing one
     */
    public void copyPlaylist() {
        clearError();
        try {
            Playlist original = new Playlist(new File("./data/" + activePlaylistName + ".playlist"));
            Playlist copy = new Playlist(original);
            copy.save();
            updateExistingPlaylists();
        } catch (Exception e) {
            displayError("Error copying playlist");
            e.printStackTrace();
        }
    }

    /**
     * Edit the contents of a Playlist
     * @param a Event triggered when the user clicks editPlaylistButton
     */
    public void editPlaylist(ActionEvent a) {
        clearError();
        try {
            Playlist p = new Playlist(new File("./data/" + activePlaylistName + ".playlist"));
            new PLEController().activate(a, p);
        } catch (Exception e) {
            //System.out.println("Error opening editor");
            displayError("Error opening editor");
        }
    }
    // This does not work. The playlist editor opens, but does not contain existing list data.

    /**
     * Add all songs in a Playlist to the player queue
     */
    public void playPlaylist() {
        clearError();
        Playlist p = new Playlist("./data/" + activePlaylistName + ".playlist");
        PlayerGUI.player.addToQueue(p, shuffleButton.isSelected());
    }

    /**
     * Delete a Playlist
     */
    public void deletePlaylist() {
        clearError();
        File playlistFile = new File("./data/" + activePlaylistName + ".playlist");

        if (playlistFile.delete()) {
            //System.out.println("Playlist deleted");
            updateExistingPlaylists();
        } else {
            //System.out.println("Error");
            displayError("Error deleting playlist");
        }
    }

    /**
     * Inherited method from Initializable interface.
     * Called when the GUI starts.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateExistingPlaylists();
        this.activePlaylistName = playlistChoiceBox.getValue();
        playlistChoiceBox.setOnAction(this::updateActivePlaylist);
        clearError();
        updateDisplay();
    }

    /**
     * Update the String active playlist when the user selects a different playlist.
     * @param a Event triggered by the user clicking an option in the playlist choice box.
     */
    public void updateActivePlaylist(ActionEvent a) {
        activePlaylistName = playlistChoiceBox.getValue();

    }

    /**
     * Update the playlist choice box with all playlists.
     * Invoked when a playlist is created or deleted.
     */
    public void updateExistingPlaylists() {
        File[] playlists = new File("./data").listFiles();
        playlistChoiceBox.getItems().clear();
        for (File f: playlists) {
            playlistChoiceBox.getItems().add(f.getName().substring(0, f.getName().lastIndexOf('.')));
        }
        playlistChoiceBox.setValue((playlists[0].getName()).substring(0,playlists[0].getName().lastIndexOf('.')));
    }

    /**
     * Display the player queue and the name of the currently playing song
     */
    public void updateDisplay() {
        StringBuilder queueString = new StringBuilder();
        LinkedList<Song> queue = PlayerGUI.player.getQueue();
        for (Song s : queue) {
            queueString.append(s.getName());
            queueString.append("\n");
        }
        queueContents.setText(queueString.toString());

        try {
            songTitle.setText(PlayerGUI.player.getSong().getName());
        } catch (Exception e) {
            songTitle.setText("Nothing");
        }
    }

    /**
     * Print an error message to the GUI
     * @param message the error message
     */
    public void displayError(String message) {
        errorLabel.setText(message);
    }

    /**
     * Clear the error output
     */
    public void clearError() {
        errorLabel.setText("");
    }
}
