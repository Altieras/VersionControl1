package Player;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Song {

    /** The URI of the source file */
    private URI source;
    private MediaPlayer player;
    
    /** Loads a file to play audio from
     * @throws Exception if the file has an improper audio format
     */
    public Song(File f) {
        source = f.toURI();
        Media hit = new Media(source.toString());
        player = new MediaPlayer(hit);
        player.play();
    }

    /** Loads a song from a file path string
     * @param path the string URI of the file
     * @throws URISyntaxException if {@code path} is an improperly formatted URI
    */
    public Song(String path) throws URISyntaxException {
        source = new URI(path);
        Media hit = new Media(source.toString());
        player = new MediaPlayer(hit);
        player.play();
    }

    /**
     * Plays a song from its beginning
     */
    public void play(){
        play(0);
    }

    /** Plays the file at the designated starting time 
     * @param time the time (in seconds) at which to start playing the file at
    */
    public void play(int time){
        player.seek(new Duration(time*1000));
        player.play();
    }

    /** Stops playing the file */
    public void pause(){
        player.pause();
    }

    /** Checks if the audio file is currently being played
     * @return true if the file is being played
     */
    public boolean playing(){
        return player.getStatus() == MediaPlayer.Status.PLAYING;
    }

    /**
     * @return the name of the song
     */
    public String getName(){
        return null;
    }

    /**
     * @return the length of the song in seconds
     */
    public double getDuration(){
        return player.getTotalDuration().toSeconds();
    }

    public URI getUri(){
        return source;
    }
}