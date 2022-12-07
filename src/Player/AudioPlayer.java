package Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

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

    public AudioPlayer(){
        queue = new LinkedList<>();
        previousSongs = new LinkedList<>();
    }

    /** Plays the current song starting at the saved timestamp */
    public void play(){
        currentSong.play();
    }

    /** Pauses the current song */
    public void pause(){
        currentSong.stop();
    }

    /** Gets the next song from the queue and starts playing it */
    public void playNext(){
        if (!hasNext()){ 
            return;
        }

        if (currentSong != null){
            currentSong.stop();
            previousSongs.addFirst(currentSong);

            if(previousSongs.size()>MAX_LENGTH){
                previousSongs.removeLast();
            }
        }
        currentSong = queue.pop();
        play();
    }

    /** Gets the last song played and starts playing it */
    public void playPrevious(){
        if (!hasPrevious()){ 
            return;
        }

        if (currentSong != null){
            currentSong.stop();
            queue.addFirst(currentSong);
            //so current song will be the next song

            if(queue.size()>MAX_LENGTH){
                queue.removeLast();
                //keeping within max size
            }
        }
        currentSong = previousSongs.pop();
        play();
    }

    /** Removes all the songs from the queue */
    public void clearQueue(){
        queue.clear();
    }

    /** Adds a song to the queue */
    public void addToQueue(Song s){
        System.out.println("hello this method was called correctly");
        queue.add(s);
        //how to handle size? remove the next up song? (since we are adding to the end of queue)
        if(queue.size()>MAX_LENGTH){
            queue.removeFirst();
        }
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
        //same thing with size
        for(int i = queue.size()-MAX_LENGTH; i>0; i--){
            queue.removeFirst();
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
