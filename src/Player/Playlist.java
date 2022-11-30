package Player;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Playlist {

    /** The name of the playlist */
    private String name;

    /** A map of the songs (used to quickly check if a song already exists in the playlist) */
    private HashMap<Song,Integer> songsMap;

    /** The list of songs in the playlist in normal play order */
    private ArrayList<Song> songs;

    /** Loads a playlist from a playlist save file
     * @param source the save file of the playlist
     * @throws FileNotFoundException if {@code source} does not exist or cant be accessed for any reason
     * @throws Exception if {@code source} is improperly formatted and cannot be read
    */
    public Playlist(File source) throws FileNotFoundException, Exception {
        String fileName = source.getName();
        this.name = fileName.substring(0,fileName.lastIndexOf("."));
        this.songs = new ArrayList<>();
        try {
            Scanner s = new Scanner(source);
            while(s.hasNextLine()){
                add(new Song(s.nextLine()));
            }
            s.close();
            
        } catch (URISyntaxException e){
            throw new Exception("Malformed save file "+source.getAbsolutePath());
        }
    }

    /** Creates an empty playlist with the given name */
    public Playlist(String name){
        boolean set = false;
        int i = 0;
        while(!set) {
            if(setName(name + i)) {
                this.name = name + i;
                set = true;
            }
            else{
                i++;
            }
        }

        this.songs = new ArrayList<>();
    }

    /** Creates a new playlist with all the songs of the given playlist
     * @param p the playlist to copy the songs from
     */
    public Playlist(Playlist p){

        //setting name
        boolean set = false;
        int i = 0;
        while(!set) {
            if(setName(p.name + "Copy" + i)) {
                this.name = p.name + "Copy" + i;
                set = true;
            }
            else{
                i++;
            }
        }

        //setting songs
        this.songs = new ArrayList<>();
        this.songs.addAll(p.songs);

        //setting songsMap
        for(int j = 0; j<songs.size(); j++){
            songsMap.put(songs.get(j),j);
        }
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
        this.songs.add(s);
        this.songsMap.put(s,songsMap.size());
    }

    /** Removes a song from the playlist
     * @param s the song to remove
     */
    public void remove(Song s){
        this.songs.remove(s);
        this.songsMap.remove(s);
    }

    /** @return the name of the playlist */
    public String getName(){
        return this.name;
    }
    
    /** @return an arraylist of the songs in the playlist */
    public ArrayList<Song> getSongs(){
        return this.songs;
    }

    /** Generates a save file for this playlist */
    public void save(){
        try {
            //Ensure that the data folder exits
            new File("data").mkdir();


            FileWriter w = new FileWriter("data"+File.separator+name.toLowerCase()+".playlist");

            for (Song s : songs){
                w.append(s.getUri().toString());
            }
            w.close();
        } catch (IOException e){
            System.out.println("Error while saving playlist"+name);
        }
    }
}
