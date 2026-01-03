package com.example.project67.repository;

import com.example.project67.R;
import com.example.project67.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    public static List<Movie> getTrendingMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Wicked for Good", R.drawable.wicked_for_good, "https://www.youtube.com/watch?v=F1dvX92n_2E", "The story of how a green-skinned woman framed by the Wizard of Oz becomes the Wicked Witch of the West.", "trending", "Fantasy"));
        movies.add(new Movie("Zootopia 2", R.drawable.zootopia_2_movie_poster_md, "https://www.youtube.com/watch?v=videoseries", "A sequel to the popular Disney movie Zootopia.", "trending", "Animation"));
        return movies;
    }

    public static List<Movie> getOriginalsMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Lupin", R.drawable.lupin, "https://www.youtube.com/watch?v=ga0iTWXCGa0", "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.", "originals", "Crime"));
        movies.add(new Movie("Avatar", R.drawable.avatar, "https://www.youtube.com/watch?v=5PSNL1qE6VY", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "originals", "Sci-Fi"));
        return movies;
    }

    public static List<Movie> getBollywoodMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Pathan", R.drawable.pathan, "https://www.youtube.com/watch?v=YxWla0CAcsI", "A spy thriller featuring Shah Rukh Khan.", "bollywood", "Action"));
        movies.add(new Movie("KGF Chapter 3", R.drawable.kgfch3, "https://www.youtube.com/watch?v=kgfch3", "The epic conclusion of the KGF saga.", "bollywood", "Action"));
        movies.add(new Movie("Pushpa", R.drawable.puspha, "https://www.youtube.com/watch?v=Q1NKMph3cwQ", "A story of a red sandalwood smuggler.", "bollywood", "Action"));
        movies.add(new Movie("Ved", R.drawable.ved, "https://www.youtube.com/watch?v=ved", "A romantic drama.", "bollywood", "Romance"));
        movies.add(new Movie("War 3", R.drawable.war3, "https://www.youtube.com/watch?v=war3", "An action-packed thriller.", "bollywood", "Action"));
        movies.add(new Movie("Bhag Milkha Bhag", R.drawable.bhagmilkabhag, "https://www.youtube.com/watch?v=bmbb", "The inspiring story of Milkha Singh.", "bollywood", "Biography"));
        movies.add(new Movie("Fan", R.drawable.fan, "https://www.youtube.com/watch?v=fan", "A psychological thriller.", "bollywood", "Thriller"));
        movies.add(new Movie("Saiyara", R.drawable.saiyara, "https://www.youtube.com/watch?v=saiyara", "A romantic drama.", "bollywood", "Romance"));
        return movies;
    }

    public static List<Movie> getHollywoodMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Conjuring", R.drawable.theconjuring, "https://www.youtube.com/watch?v=k10ETZ41q5o", "Paranormal investigators help a family terrorized by a dark presence.", "hollywood", "Horror"));
        movies.add(new Movie("The Conjuring 2", R.drawable.theconjuring2, "https://www.youtube.com/watch?v=VFsmuRPClr4", "The Warrens travel to London to help a single mother.", "hollywood", "Horror"));
        movies.add(new Movie("The Nun", R.drawable.thenun, "https://www.youtube.com/watch?v=pzD9zGcUNrw", "A priest and a nun confront an unholy force.", "hollywood", "Horror"));
        movies.add(new Movie("Annabelle", R.drawable.annabelle, "https://www.youtube.com/watch?v=paFgQNPGlsg", "A couple's home becomes haunted by a possessed doll.", "hollywood", "Horror"));
        movies.add(new Movie("It Chapter 2", R.drawable.it2, "https://www.youtube.com/watch?v=xhJ5P7Up3jA", "The Losers Club reunites to defeat Pennywise.", "hollywood", "Horror"));
        movies.add(new Movie("Freddy", R.drawable.freddy, "https://www.youtube.com/watch?v=freddy", "A psychological horror film.", "hollywood", "Horror"));
        movies.add(new Movie("Game of Thrones", R.drawable.gameofthrones, "https://www.youtube.com/watch?v=gameofthrones", "The epic fantasy series.", "hollywood", "Fantasy"));
        movies.add(new Movie("The Witcher", R.drawable.thewitcher, "https://www.youtube.com/watch?v=thewitcher", "Geralt of Rivia's adventures.", "hollywood", "Fantasy"));
        movies.add(new Movie("Reacher", R.drawable.reacher, "https://www.youtube.com/watch?v=reacher", "A former military police officer investigates crimes.", "hollywood", "Action"));
        movies.add(new Movie("Green Book", R.drawable.green_book, "https://www.youtube.com/watch?v=greenbook", "A working-class Italian-American bouncer becomes the driver for an African-American pianist.", "hollywood", "Drama"));
        movies.add(new Movie("Broken", R.drawable.broken, "https://www.youtube.com/watch?v=broken", "A psychological thriller.", "hollywood", "Thriller"));
        movies.add(new Movie("In Your Dreams", R.drawable.in_you_dreams, "https://www.youtube.com/watch?v=inyourdreams", "A romantic fantasy.", "hollywood", "Romance"));
        return movies;
    }

    public static List<Movie> getKoreanMovies() {
        List<Movie> movies = new ArrayList<>();
        // Using URLs for movies without drawable resources
        movies.add(new Movie("Parasite", "https://i.postimg.cc/d1hB4m2z/parasite.jpg", "https://www.youtube.com/watch?v=5xH0HfJHsaY", "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.", "korean", "Thriller"));
        movies.add(new Movie("Train to Busan", "https://i.postimg.cc/mkjZ7B61/traintobusan.jpg", "https://www.youtube.com/watch?v=1ovgxN2VWNc", "While a zombie virus breaks out in South Korea, passengers struggle to survive on the train from Seoul to Busan.", "korean", "Horror"));
        return movies;
    }

    public static List<Movie> getAnimeMovies() {
        List<Movie> movies = new ArrayList<>();
        // Using URLs for movies without drawable resources
        movies.add(new Movie("Spirited Away", "https://i.postimg.cc/PqgGdz97/spiritedaway.jpg", "https://www.youtube.com/watch?v=ByXuk9QqQkk", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits.", "anime", "Fantasy"));
        movies.add(new Movie("Your Name", "https://i.postimg.cc/0jBSrq43/yourname.jpg", "https://www.youtube.com/watch?v=xU47dYJAt_c", "Two strangers find themselves linked in a bizarre way. When a connection forms, will distance be the only thing to keep them apart?", "anime", "Romance"));
        movies.add(new Movie("Bleach", R.drawable.bleach, "https://www.youtube.com/watch?v=bleach", "A teenager with the ability to see ghosts becomes a Soul Reaper.", "anime", "Action"));
        movies.add(new Movie("Jujutsu Kaisen", R.drawable.jujtsukaisen, "https://www.youtube.com/watch?v=jujutsukaisen", "A high school student enters the world of Cursed Spirits.", "anime", "Supernatural"));
        movies.add(new Movie("Fire Force", R.drawable.fire_force, "https://www.youtube.com/watch?v=fireforce", "Firefighters fight against spontaneous human combustion.", "anime", "Action"));
        movies.add(new Movie("Sweet Home", R.drawable.sweet_home, "https://www.youtube.com/watch?v=sweethome", "Residents of an apartment building fight monsters.", "anime", "Horror"));
        movies.add(new Movie("Kaiju No. 8", R.drawable.kiaju_no_8, "https://www.youtube.com/watch?v=kaijuno8", "A man gains the power to transform into a kaiju.", "anime", "Action"));
        return movies;
    }

    public static List<Movie> getAllMovies() {
        List<Movie> allMovies = new ArrayList<>();
        allMovies.addAll(getTrendingMovies());
        allMovies.addAll(getOriginalsMovies());
        allMovies.addAll(getBollywoodMovies());
        allMovies.addAll(getHollywoodMovies());
        allMovies.addAll(getKoreanMovies());
        allMovies.addAll(getAnimeMovies());
        return allMovies;
    }
}
