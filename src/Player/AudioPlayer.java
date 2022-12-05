package Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.sound.sampled.AudioSystem;
import static java.util.Collections.shuffle;


/** This class will be used as the primary interface for interacting with the program */
public class AudioPlayer {
    /** List of songs to be played after the current song */
    private LinkedList<Song> queue;
    
    /** The song that is currently playing */
    private Song currentSong;

    /** */
    private long savedTime;
    //changed to long to accomodate grabbing from clip
    
    /** List of songs that have been previously played */
    private LinkedList<Song> previousSongs;

    /** The maximum amount of songs allowed in the queue and previous songs */
    private static final int MAX_LENGTH = 50;

    //things added
    private String currentStatus;
    Clip clip;
    AudioInputStream aStr;

    /** Plays the current song starting at the saved timestamp */
    public void play(){
        try {
            aStr = AudioSystem.getAudioInputStream(new File(currentSong.getUriString()));
            clip.open(aStr);
            clip.start();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /** Pauses the current song */
    public void pause(){
        savedTime = clip.getMicrosecondPosition();
        clip.stop();
        currentStatus = "paused";
    }

    /** Gets the next song from the queue and starts playing it */
    public void playNext(){
        previousSongs.add(currentSong);
        currentSong = queue.pop();
        play();
    }

    /** Gets the last song played and starts playing it */
    public void playPrevious(){
        queue.add(currentSong);
        currentSong = previousSongs.pop();
    }

    /** Removes all the songs from the queue */
    public void clearQueue(){
        queue.clear();
    }

    /** Adds a song to the queue */
    public void addToQueue(Song s){
        System.out.println("hello this method was called correctly");
        queue.add(s);
    }

    /** Adds every song in a playlist to the queue */
    public void addToQueue(Playlist p, boolean shuffle){
        if(!shuffle){
            for(Song s : p.getSongs()){
                queue.add(s);
            }
        }

        if(shuffle){
            ArrayList<Song> songCopy = new ArrayList<>(p.getSongs());
            shuffle(songCopy);
            for(Song s : songCopy){
                queue.add(s);
            }
        }
    }

    /**
     * @return true if there is a song waiting in the queue
     */
    public boolean hasNext(){
        return (!queue.isEmpty());
    }

    /**
     * @return true if there is a song saved
     */
    public boolean hasPrevious(){
        return (!previousSongs.isEmpty());
    }

    /** Returns a list of all the songs currently in the queue */
    public LinkedList<Song> getQueue(){
        return queue;
    }

    /** Returns the current song that is playing */
    public Song getSong(){
        return currentSong;
    }

    /** Returns a list of the previous songs that were played */
    public LinkedList<Song> getPrevious(){
        return previousSongs;
    }
}
