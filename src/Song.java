import java.io.File;

public class Song {
    
    /** Loads a file to play audio from
     * @throws Exception if the file has an improper audio format
     */
    public Song(File f){
        
    }

    /** Plays the file at the designated starting time 
     * @param time the time (in seconds) at which to start playing the file at
    */
    public void play(int time){
        
    }

    /** Stops playing the file */
    public void pause(){
        
    }

    /** Checks if the audio file is currently being played
     * @return true if the file is being played
     */
    public boolean playing(){
        return true;
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
    public int getDuration(){
        return 0;
    }
}