import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class Practice {
  public static void main(String[] args) {
   Playlist playlist = new Playlist(); 
    Song song = new Song("Vanilla Sky", "Bladee", 100, 5);
    playlist.songs.add(song);
    playlist.rankByPopularity();
  }
}

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

  public String toString() {
    return this.artist + " - " + this.title;
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
    Collections.sort(this.songs, BY_ARTIST_THEN_RATING);
    for (Song s : this.songs) {
      System.out.println(s);
    }
  }
}

 class Data {

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


class Flight {
  String flightNumber;
  int departureTime;
  String gateNumber;

  Flight(String flightNumber, int departureTime, String gateNumber) {
    this.flightNumber = flightNumber;
    this.departureTime = departureTime;
    this.gateNumber = gateNumber;
  }

  public int getDepartureTime() {
    return this.departureTime;
  }

  public String getFlightNumber() {
    return this.flightNumber;
  }


}

class AirportSchedule {
  List<Flight> schedule;
  static Comparator<Flight> comparator = Comparator.comparing(Flight::getDepartureTime)
  .thenComparing(Flight::getFlightNumber);

  AirportSchedule() {
    this.schedule = new ArrayList<>();
  }

  public int findFlightIndex(Flight targetFlight) {
    Collections.sort(this.schedule, comparator);
    return Collections.binarySearch(this.schedule, targetFlight, comparator);
  }


}









