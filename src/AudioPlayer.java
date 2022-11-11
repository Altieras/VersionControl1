import java.util.LinkedList;
import java.util.Queue;

/** This class will be used as the primary interface for interacting with the program */
public abstract class AudioPlayer {
    /** List of songs to be played after the current song */
    private Queue<Song> queue;
    /** The song that is currently playing */
    private Song currentSong;

    private int savedTime;
    /** List of songs that have been previously played */
    private LinkedList<Song> previousSongs;

    /** The maximum amount of songs allowed in the queue and previous songs */
    private final int MAX_LENGTH = 50;


    /** Plays the current song starting at the saved timestamp */
    public void play(){

    }

    /** Pauses the current song */
    public void pause(){
        
    }

    /** Gets the next song from the queue and starts playing it */
    public void playNext(){

    }

    /** Gets the last song played and starts playing it */
    public void playPrevious(){
        
    }

    /** Removes all the songs from the queue */
    public void clearQueue(){

    }

    /** Adds a song to the queue */
    public void addToQueue(Song s){

    }

    /** Adds every song in a playlist to the queue */
    public void addToQueue(Playlist p, boolean shuffle){

    }

    /** Returns a list of all the songs currently in the queue */
    public LinkedList<Song> getQueue(){
        return null;
    }

    /** Returns the current song that is playing */
    public Song getSong(){
        return null;
    }

    /** Returns a list of the previous songs that were played */
    public LinkedList<Song> getPrevious(){
        return null;
    }
}
