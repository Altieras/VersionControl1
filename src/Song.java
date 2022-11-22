import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class Song {

    /** The URI of the source file */
    private URI source;
    
    /** Loads a file to play audio from
     * @throws Exception if the file has an improper audio format
     */
    public Song(File f){

    }

    /** Loads a song from a file path string */
    public Song(String path){
        try {
            source = new URI(path);
        } catch (URISyntaxException e){
            System.out.println("Unable to parse "+path+" to valid URI");
        }
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

    public URI getUri(){
        return source;
    }
}