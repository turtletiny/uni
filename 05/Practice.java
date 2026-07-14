import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class Song {
  String title;
  String artist;
  int playCount;
  double rating;

  Song(String title, String artist, int playCount, double rating) {
    this.title = title;
    this.artist = artist;
    this.playCount = playCount;
    this.rating = rating;
  }

  public int getPlayCount() {
    return this.playCount;
  }

  public String getTitle() {
    return this.title;
  }

  public String getArtist() {
    return this.artist;
  }

  public double getRating() {
    return this.rating;
  }
}

class Playlist {
  List<Song> songs;
  static final Comparator<Song> BY_POPULARITY = Comparator.comparing(Song::getPlayCount).reversed()
      .thenComparing(Song::getTitle);
  static final Comparator<Song> BY_ARTIST_THEN_RATING = Comparator.comparing(Song::getArtist)
      .thenComparing(Song::getRating).reversed();

  Playlist() {
    this.songs = new ArrayList<>();
  }

  public void shufflePlayback() {
    Collections.shuffle(this.songs);
  }

  public void reverseOrder() {
    Collections.reverse(this.songs);
  }

  public void rankByPopularity() {
    for (Song s : Collections.sort(this.songs, BY_ARTIST_THEN_RATING) {

    }
  }
}

public class Practice {

  public static void printStats(int start) {

    Random rand = new Random();
    int curTotal = start;
    int lowest = start;
    int highest = start;

    System.out.println(start);
    for (int i = 1; i <= 100; i++) {
      start -= rand.nextInt(-300, 300);
      curTotal += start;
      double avg = (double) curTotal / (i + 1);
      lowest = Math.min(lowest, start);
      highest = Math.max(highest, start);

      System.out.println(start + ", " + lowest + ", " + highest + ", " + avg);
    }
  }
}
