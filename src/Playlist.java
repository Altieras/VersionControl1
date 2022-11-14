import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Playlist {

    /** The name of the playlist */
    private String name;

    /** A map of the songs (used to quickly check if a song already exists in the playlist) */
    private HashMap<Song,Integer> songsMap;

    /** The list of songs in the playlist in normal play order */
    private ArrayList<Song> songs;

    /** Loads a playlist from a playlist save file */
    public Playlist(File source){

    }

    /** Creates an empty playlist with the given name */
    public Playlist(String name){

    }

    /** Creates a new playlist with all the songs of the given playlist
     * @param p the playlist to copy the songs from
     */
    public Playlist(Playlist p){

    }

    /** Set the name of the playlist
     * @param name the new name of the playlist
     * @return false if another playlist already has that name
     */
    public boolean setName(String name){
        return false;
    }

    /** Adds a song to the playlist
     * @param s the song to add
     */
    public void add(Song s){

    }

    /** Removes a song from the playlist
     * @param s the song to remove
     */
    public void remove(Song s){

    }

    /** @return the name of the playlist */
    public String getName(){
        return null;
    }
    
    /** @return an arraylist of the songs in the playlist */
    public ArrayList<Song> getSongs(){
        return null;
    }

    /** Generates a save file for this playlist */
    public void save(){

    }
}
