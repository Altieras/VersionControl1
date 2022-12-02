package Player;

import java.io.File;
import java.nio.file.Files;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Song {

    /** The URI of the source file */
    private URI source;
    private MediaPlayer player;

    /**
     * Searches through to find all the audio files stored in a directory
     * @param f the folder to search
     * @return a list of the songs found
     */
    public static List<Song> searchFolder(File dir){
        LinkedList<Song> songs = new LinkedList<>();
        if (!dir.isDirectory()){
            throw new IllegalArgumentException("File is not a directory!");
        }
        File[] files = dir.listFiles();
        for (File f : files){
            try {
                if (f.isDirectory()){
                    songs.addAll(searchFolder(f));
                }
                else {
                    String contentType = Files.probeContentType(f.toPath());
                    if (contentType.indexOf("audio") == 0){
                        songs.add(new Song(f));
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return songs;
    }
    
    /** Loads a file to play audio from
     * @throws Exception if the file has an improper audio format
     */
    public Song(File f) {
        source = f.toURI();
        Media hit = new Media(source.toString());
        player = new MediaPlayer(hit);
    }

    /** Loads a song from a file path string
     * @param path the string URI of the file
     * @throws URISyntaxException if {@code path} is an improperly formatted URI
    */
    public Song(String path) throws URISyntaxException {
        source = new URI(path);
        Media hit = new Media(source.toString());
        player = new MediaPlayer(hit);
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

    @Override
    public int hashCode() {
        return source.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Song)){
            return false;
        }

        Song o = (Song)obj;
        return source.equals(o.source);
    }
}