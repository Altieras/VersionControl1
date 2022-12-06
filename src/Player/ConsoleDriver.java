package Player;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ConsoleDriver {
    public static void main(String[] args) {
        //bulk of logic is elsewhere
        //this should only have I/O logic
        //meaning talk to user, get user input, show changes to user

        //eg
        //create an object of type Whatever
        //Whatever w = new Whatever();
        //maybe while(!w.hasWon())
        //ask user for input
        //call w.displayStatus() ------- or displayBoard()

        //can add a gui with minimal if any changes to game logic
        AudioPlayer player = new AudioPlayer();
        Playlist playlist = new Playlist("Main");

        File songDir = new File("songs");
        if (!songDir.exists()){
            songDir.mkdir();
        }
        List<Song> songs = Song.searchFolder(songDir);
        if (songs.size() == 0){
            System.out.println("No audio files found in "+songDir.getAbsolutePath());
            System.exit(0);
        }

        for (Song s : songs){
            playlist.add(s);
        }
        player.addToQueue(playlist, true);
        player.playNext();
        Scanner s = new Scanner(System.in);

        while (true){
            System.out.println("Now playing: "+player.getSong().getName());
            System.out.println("1. Previous");
            System.out.println("2. Next");
            System.out.println("3. Exit");
            String option = s.next();

            if (option.equals("1")){
                player.playPrevious();
            }
            else if (option.equals("2")){
                player.playNext();
            }
            else if (option.equals("3")){
                System.exit(0);
            }
        }

    }
}
