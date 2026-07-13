import java.util.Random;
import java.util.ArrayList;
import java.util.List;


abstract class AudioTrack {
    int durationInSeconds;
    String name;
    Random rand;

    AudioTrack(String name, int durationInSeconds) {
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.rand = new Random();
    }

    // Play the track for some random duration. Returns ListeningDuration
    // corresponding to how long the track was listened to.
    ListeningDuration play() {
        ListeningDuration listeningDuration = ListeningDuration.YET_TO_LISTEN;
        int choice = rand.nextInt(5);
        if (choice == 0) {
            listeningDuration = ListeningDuration.UNDER_1_MINUTE;
        } else if (choice == 1) {
            listeningDuration = ListeningDuration.ONE_QUARTER;
        } else if (choice == 2) {
            listeningDuration = ListeningDuration.HALF;
        } else if (choice == 3) {
            listeningDuration = ListeningDuration.THREE_QUARTERS;
        } else if (choice == 4) {
            listeningDuration = ListeningDuration.WHOLE_DURATION;
        } else {
            System.out.println("Unreachable code");
        }
        return listeningDuration;
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
        String playString = "Starting podcast '" + this.toString();
        ListeningDuration playDuration = super.play();
        if (playDuration == ListeningDuration.UNDER_1_MINUTE) {
            playString += ". Listened for < 1 minute";
        } else if (playDuration == ListeningDuration.ONE_QUARTER) {
            playString += ". Listened to a quarter of the podcast";
        } else if (playDuration == ListeningDuration.HALF) {
            playString += ". Listened to half of the podcast";
        } else if (playDuration == ListeningDuration.THREE_QUARTERS) {
            playString += ". Listened to 3 quarters of the podcast";
        } else if (playDuration == ListeningDuration.WHOLE_DURATION) {
            playString += ". Listened to the entire podcast";
        }
        System.out.println(playString);
        return playDuration;
    }

    @Override
    public String toString() {
        return super.name + ", by " + this.host + ", episode #" + this.episodeNumber;
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
        String playString = "Starting song '" + this.toString();
        ListeningDuration playDuration = super.play();
        if (playDuration == ListeningDuration.UNDER_1_MINUTE) {
            playString += ". Listened for < 1 minute";
        } else if (playDuration == ListeningDuration.ONE_QUARTER) {
            playString += ". Listened to a quarter of the song";
        } else if (playDuration == ListeningDuration.HALF) {
            playString += ". Listened to half of the song";
        } else if (playDuration == ListeningDuration.THREE_QUARTERS) {
            playString += ". Listened to 3 quarters of the song";
        } else if (playDuration == ListeningDuration.WHOLE_DURATION) {
            playString += ". Listened to the entire song";
        }
        System.out.println(playString);
        return playDuration;

    }

    @Override
    public String toString() {
        return super.name + ", by " + this.artist;
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
    List<Integer> list;
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

class MusicPlayer implements ListPodcasts, ListSongs, PlaySongs {
    AudioTrack[] library;
    PlayMode currentPlayMode;

    MusicPlayer(AudioTrack[] library, PlayMode playmode) {
        this.library = library;
        this.currentPlayMode = playmode;
    }

    @Override
    public ArrayList<Podcast> listPodcasts() {
        ArrayList<Podcast> podcasts = new ArrayList<>();
        for (AudioTrack t : this.library) {
            if (t instanceof Podcast) {
                podcasts.add((Podcast) t);
            }
        }
        return podcasts;
    }

    @Override
    public ArrayList<Song> listSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        for (AudioTrack t : this.library) {
            if (t instanceof Song) {
                songs.add((Song) t);
            }
        }
        return songs;
    }

    @Override
    public ArrayList<Song> listSongs(String artist) {
        ArrayList<Song> songs = new ArrayList<>();
        for (AudioTrack t : this.library) {
            if (t instanceof Song) {
                // Need to access 'artist' attribute, so a type-cast is needed
                Song song = (Song) t;
                if (song.artist.equals(artist)) {
                    songs.add((Song) t);
                }
            }
        }
        return songs;
    }

    @Override
    public void playSongs(ArrayList<Song> songs) {
        if (this.currentPlayMode == PlayMode.LINEAR) {
            for (Song s : songs) {
                s.play();
            }
        } else if (this.currentPlayMode == PlayMode.REPEAT) {
            while (true) {
                for (Song s : songs) {
                    s.play();
                }
                System.out.println("Would you like to continue (y/n)?");
                if (In.nextChar() == 'n') {
                    break;
                }
            }
        } else {
            // Randomised playback. There are a couple of ways to do this.
            // We'll randomly pick a song out of the ArrayList, then remove it.
            while (!songs.isEmpty()) {
                Random rand = new Random();
                int choice = rand.nextInt(songs.size());
                songs.get(choice).play();
                songs.remove(choice);
            }
        }
    }

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

class TestLibrary {
    public static void main(String[] args) {
        AudioTrack[] library = { new Song("Gangnam Style", 219, "Psy", MusicGenre.POP),
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
                        398) };

        MusicPlayer mp = new MusicPlayer(library, PlayMode.SHUFFLE);

        ArrayList<Song> songs = new ArrayList<>();
        songs.add((Song) library[3]);
        songs.add((Song) library[5]);
        songs.add((Song) library[6]);
        mp.playSongs(songs);
    }
}