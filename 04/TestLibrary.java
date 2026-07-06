
import java.util.ArrayList;
import java.util.Random;

abstract class AudioTrack {

    int durationInSeconds;
    String name;
    ListeningDuration listeningDuration;
    Random rand;

    AudioTrack(String name, int durationInSeconds) {
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.listeningDuration = ListeningDuration.YET_TO_LISTEN;
        this.rand = new Random();
    }

    // Play the track for some random duration. Returns ListeningDuration
    // corresponding to how long the track was listened to.
    ListeningDuration play() {
        if (this.listeningDuration == ListeningDuration.YET_TO_LISTEN) {
            System.out.println("Haven't starting playing " + this.name);
        }

        if (this.listeningDuration == ListeningDuration.WHOLE_DURATION) {
            System.out.println(this.name + " is already over");
        }
        int choice = rand.nextInt(5);
        if (choice == 0) {
            this.listeningDuration = ListeningDuration.UNDER_1_MINUTE;
        } else if (choice == 1) {
            this.listeningDuration = ListeningDuration.ONE_QUARTER;
        } else if (choice == 2) {
            this.listeningDuration = ListeningDuration.HALF;
        } else if (choice == 3) {
            this.listeningDuration = ListeningDuration.THREE_QUARTERS;
        } else if (choice == 4) {
            this.listeningDuration = ListeningDuration.WHOLE_DURATION;
        } else {
            System.out.println("Unreachable code");
        }
        return this.listeningDuration;
    }

}

class Podcast extends AudioTrack {

    // Podcasts are created by 'hosts'
    String host;
    PodcastGenre genre;
    int episodeNumber;

    Podcast(String name, int durationInSeconds, String host, PodcastGenre genre, int episodeNumber) {
        super(name, durationInSeconds);
        this.host = host;
        this.genre = genre;
        this.episodeNumber = episodeNumber;
    }

    @Override
    ListeningDuration play() {
        System.out.println("Starting podcast '" + this.host + "', episide #" + this.episodeNumber);
        return super.play();
    }

    public String toString() {
        return this.name + "Episode no: " + this.episodeNumber
                + "Hosted by " + this.host + " - " + this.durationInSeconds;

    }
}

class Song extends AudioTrack {

    String artist;
    MusicGenre genre;

    Song(String name, int durationInSeconds, String artist, MusicGenre genre) {
        super(name, durationInSeconds);
        this.artist = artist;
        this.genre = genre;
    }

    @Override
    ListeningDuration play() {
        System.out.println("Starting song '" + this.artist + "Genre: " + this.genre);
        return super.play();
    }

    public String toString() {
        return this.artist + "-" + this.name + " - " + this.durationInSeconds + " seconds.";
    }
}

enum MusicGenre {
    METAL,
    POP,
    ROCK,
    CLASSICAL,
}

enum PodcastGenre {
    TECHNOLOGY,
    COMEDY,
    NEWS,
    BUSINESS,
    HISTORY,
}

class PlayList {

    ArrayList<Integer> tracks;
}

interface ListPodcasts {

    ArrayList<Podcast> listPodcasts();
}

interface ListSongs {

    // List all songs
    ArrayList<Song> listSongs();

    // List songs by artist
    ArrayList<Song> listSongs(String artist);
}

interface PlaySongs {

    void playSongs(ArrayList<Song> songs);
}

class MusicPlayer {

    AudioTrack[] library;
    PlayMode currentPlayMode;
}

enum PlayMode {
    // Plays tracks in order, first to last.
    LINEAR,
    // Plays tracks in order, repeating back to first after last track.
    REPEAT,
    // Plays tracks in random order.
    SHUFFLE,
}

enum ListeningDuration {
    YET_TO_LISTEN,
    // Listened for less than 1 minute
    UNDER_1_MINUTE,
    // Listened for more than a minute but up to a quarter of the track's duration
    ONE_QUARTER,
    // Listened for more than a quarter but up to half of the track's duration
    HALF,
    // Listened for more than a half but up to 3 quarters of the track's duration
    THREE_QUARTERS,
    // Listened for the whole duration of the track
    WHOLE_DURATION
}

public class TestLibrary {

    public static void main(String[] args) {
        AudioTrack[] library = {new Song("Gangnam Style", 219, "Psy", MusicGenre.POP),
            new Song("Enter Sandman", 331, "Metallica", MusicGenre.METAL),
            new Podcast("The Joe Rogan Experience: Robert Kennedy, Jr", 10800, "Joe Rogan", PodcastGenre.COMEDY,
            1999),
            new Song("Aces High", 271, "Iron Maiden", MusicGenre.METAL),
            new Podcast("The Joe Rogan Experience: Mike Tyson", 9000, "Joe Rogan", PodcastGenre.COMEDY,
            1532),
            new Song("Moonshield", 301, "In Flames", MusicGenre.METAL),
            new Song("Mesmeric Horror", 314, "Inferi ", MusicGenre.METAL),
            new Song("Eye of the Tiger", 245, "Survivor", MusicGenre.ROCK),
            new Song("Beat It", 258, "Michael Jackson", MusicGenre.POP),
            new Song("Ode To Joy", 660, "Ludwig van Beethoven", MusicGenre.CLASSICAL),
            new Podcast("Lex Fridman Podcast: Mark Zuckerberg", 3840, "Lex Fridman", PodcastGenre.TECHNOLOGY,
            398)};
    }
}
