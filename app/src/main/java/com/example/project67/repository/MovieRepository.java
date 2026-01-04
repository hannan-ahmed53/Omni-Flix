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
        movies.add(new Movie("Dune: Part Two", "https://image.tmdb.org/t/p/w500/d5NXSklXo0qyIYkgV94XAgMIckC.jpg", "https://www.youtube.com/watch?v=U2Qp5pL3ovA", "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.", "trending", "Sci-Fi"));
        movies.add(new Movie("Oppenheimer", "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg", "https://www.youtube.com/watch?v=uYPbbksJxIg", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", "trending", "Biography"));
        movies.add(new Movie("Barbie", "https://image.tmdb.org/t/p/w500/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg", "https://www.youtube.com/watch?v=pBk4NYhWNMM", "Barbie suffers a crisis that leads her to question her world and her existence.", "trending", "Comedy"));
        movies.add(new Movie("The Marvels", "https://image.tmdb.org/t/p/w500/9GBhzXMFjgcZ3FdR9w3bUmmNpsb.jpg", "https://www.youtube.com/watch?v=wS_qbDztgVY", "Carol Danvers gets her powers entangled with those of Kamala Khan and Monica Rambeau.", "trending", "Action"));
        movies.add(new Movie("Wonka", "https://image.tmdb.org/t/p/w500/qhb1qOilapb2xQOp17rLcJn7CVT.jpg", "https://www.youtube.com/watch?v=otNh9bTjXWg", "The story of how Willy Wonka became the world's greatest chocolate maker.", "trending", "Musical"));
        movies.add(new Movie("Aquaman and the Lost Kingdom", "https://image.tmdb.org/t/p/w500/7lTnXOy0iNtBAdRP3TZvaKJ77F6.jpg", "https://www.youtube.com/watch?v=Y8ey3wjf4sA", "Aquaman must protect Atlantis and the world from an ancient power.", "trending", "Action"));
        movies.add(new Movie("The Hunger Games: The Ballad of Songbirds & Snakes", "https://image.tmdb.org/t/p/w500/mBaXZ9R2I4fNDPm1pJ0wosL2aqc.jpg", "https://www.youtube.com/watch?v=qxZ1JhQj3cY", "Coriolanus Snow mentors and develops feelings for the female District 12 tribute during the 10th Hunger Games.", "trending", "Action"));
        movies.add(new Movie("Napoleon", "https://image.tmdb.org/t/p/w500/jE5o7y9K6pZtWNNMEw3IdpHuncR.jpg", "https://www.youtube.com/watch?v=OAZWXUOmMBE", "An epic that details the checkered rise and fall of French Emperor Napoleon Bonaparte.", "trending", "Biography"));
        movies.add(new Movie("Killers of the Flower Moon", "https://image.tmdb.org/t/p/w500/dB6Krk806zeqd0YNp2ngQ9zXteH.jpg", "https://www.youtube.com/watch?v=EP34Yoxs3FQ", "The real love story and murders at the heart of the Osage Nation killings in the 1920s.", "trending", "Crime"));
        movies.add(new Movie("The Creator", "https://image.tmdb.org/t/p/w500/vBZ0qvaRxqEhZwl6LWmruJqWE10.jpg", "https://www.youtube.com/watch?v=ex3C1-5Dhb8", "A war between humans and AI, a former soldier is recruited to hunt down and kill the Creator.", "trending", "Sci-Fi"));
        movies.add(new Movie("Fast X", "https://image.tmdb.org/t/p/w500/fiVW06jE7z9nO1pa1fIV1Vj0HxR.jpg", "https://www.youtube.com/watch?v=eoOaKN4qCKw", "Dom Toretto and his family are targeted by the vengeful son of drug kingpin Hernan Reyes.", "trending", "Action"));
        movies.add(new Movie("John Wick: Chapter 4", "https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg", "https://www.youtube.com/watch?v=qEVUtrk8_B4", "John Wick uncovers a path to defeating The High Table.", "trending", "Action"));
        movies.add(new Movie("Guardians of the Galaxy Vol. 3", "https://image.tmdb.org/t/p/w500/5YZbUmjbMa3ClvSW1W73GX9UN19.jpg", "https://www.youtube.com/watch?v=u3V5KDHRQvk", "Still reeling from the loss of Gamora, Peter Quill rallies his team to defend the universe.", "trending", "Action"));
        return movies;
    }

    public static List<Movie> getOriginalsMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Lupin", R.drawable.lupin, "https://www.youtube.com/watch?v=ga0iTWXCGa0", "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.", "originals", "Crime"));
        movies.add(new Movie("Avatar", R.drawable.avatar, "https://www.youtube.com/watch?v=5PSNL1qE6VY", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "originals", "Sci-Fi"));
        movies.add(new Movie("Stranger Things", "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg", "https://www.youtube.com/watch?v=b9EkMc79ZSU", "When a young boy vanishes, a small town uncovers a mystery involving secret experiments.", "originals", "Horror"));
        movies.add(new Movie("The Crown", "https://image.tmdb.org/t/p/w500/1M876KPjulVwppEpldhdc8V4o68.jpg", "https://www.youtube.com/watch?v=JWtnJjn6ng0", "Follows the political rivalries and romance of Queen Elizabeth II's reign.", "originals", "Drama"));
        movies.add(new Movie("The Witcher", "https://image.tmdb.org/t/p/w500/7vjaCdMw15FEbXyLQTVa04URsPm.jpg", "https://www.youtube.com/watch?v=ndl1W4ltcmg", "Geralt of Rivia, a mutated monster-hunter for hire, journeys toward his destiny.", "originals", "Fantasy"));
        movies.add(new Movie("Bridgerton", "https://image.tmdb.org/t/p/w500/htV8emTbYxJV5Pb0YJ6xy7z0iq8.jpg", "https://www.youtube.com/watch?v=gpv7ayf_tyE", "The eight close-knit siblings of the Bridgerton family look for love and happiness in London high society.", "originals", "Romance"));
        movies.add(new Movie("The Queen's Gambit", "https://image.tmdb.org/t/p/w500/zU0htwkhNvBQdVSIKB9s6xgVeid.jpg", "https://www.youtube.com/watch?v=CDrieqwSdgI", "Orphaned at the tender age of nine, prodigious introvert Beth Harmon discovers and masters the game of chess.", "originals", "Drama"));
        movies.add(new Movie("Squid Game", "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Puy93I6Wn0Ild4uSY.jpg", "https://www.youtube.com/watch?v=oqxAJKy0ii4", "Hundreds of cash-strapped players accept a strange invitation to compete in children's games.", "originals", "Thriller"));
        movies.add(new Movie("Wednesday", "https://image.tmdb.org/t/p/w500/jeGtaMwGxPmQN5xMAdKlWc8gqas.jpg", "https://www.youtube.com/watch?v=Di310WS8zLk", "Wednesday Addams attempts to master her emerging psychic ability, thwart a killing spree, and solve the mystery.", "originals", "Comedy"));
        movies.add(new Movie("The Last Kingdom", "https://image.tmdb.org/t/p/w500/7k9s7X3b11v8G3hVWZvZ9Q9kF2L.jpg", "https://www.youtube.com/watch?v=WxPApTGJwaE", "A Saxon warrior, Uhtred, battles to unite Britain against the invading Danes.", "originals", "Action"));
        movies.add(new Movie("Dark", "https://image.tmdb.org/t/p/w500/5Lo4WFYAHxYtvFmTwsbM2V4Z73.jpg", "https://www.youtube.com/watch?v=rrwycJ08PSA", "A missing child sets four families on a frantic hunt for answers as they unearth a mind-bending mystery.", "originals", "Sci-Fi"));
        movies.add(new Movie("Money Heist", "https://image.tmdb.org/t/p/w500/reEMJA1uzscCbkpeRJeTT2bjqUp.jpg", "https://www.youtube.com/watch?v=htqXL94Rze4", "An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history.", "originals", "Crime"));
        movies.add(new Movie("The Umbrella Academy", "https://image.tmdb.org/t/p/w500/scZlQQYnDVlnpxFTxaIv2ki0s5P.jpg", "https://www.youtube.com/watch?v=0DAmWHxeoKw", "A family of former child heroes, now grown apart, must reunite to continue to protect the world.", "originals", "Action"));
        movies.add(new Movie("House of Cards", "https://image.tmdb.org/t/p/w500/hKWxWjFwnM0sFz5XoB7v6jYzK7L.jpg", "https://www.youtube.com/watch?v=ULwUzF1q5w4", "A Congressman works with his equally conniving wife to exact revenge on the people who betrayed him.", "originals", "Drama"));
        movies.add(new Movie("Orange Is the New Black", "https://image.tmdb.org/t/p/w500/ekaa7YjGPTkFLcPhwW1ne6hGeQY.jpg", "https://www.youtube.com/watch?v=th8WT_pxGqg", "Piper Chapman's wild past comes back to haunt her, resulting in her arrest and detention in a federal penitentiary.", "originals", "Comedy"));
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
        movies.add(new Movie("Dangal", "https://image.tmdb.org/t/p/w500/4Y1HkbjN6kJCDLkT5iU6xvdVJ7F.jpg", "https://www.youtube.com/watch?v=x_7YlGv9u1g", "Former wrestler Mahavir Singh Phogat trains his daughters to become world-class wrestlers.", "bollywood", "Biography"));
        movies.add(new Movie("3 Idiots", "https://image.tmdb.org/t/p/w500/66aPEFvK9V7CVgB8k1r6QlbWw1E.jpg", "https://www.youtube.com/watch?v=K0eDlFX9GMc", "In the tradition of 'Old School' and 'Van Wilder' comes a new comedy about a group of friends.", "bollywood", "Comedy"));
        movies.add(new Movie("PK", "https://image.tmdb.org/t/p/w500/4B8kk8wjK8j3Q3oJfFMPOfmQ2ng.jpg", "https://www.youtube.com/watch?v=SOXWc32k4zA", "An alien on Earth loses the only device he can use to communicate with his spaceship.", "bollywood", "Comedy"));
        movies.add(new Movie("Bajrangi Bhaijaan", "https://image.tmdb.org/t/p/w500/3zXceNTtyj5FLjwQXuPvLYK5YYL.jpg", "https://www.youtube.com/watch?v=4nwAra0mz_Q", "An Indian man with a magnanimous heart takes a young mute Pakistani girl back to her homeland.", "bollywood", "Drama"));
        movies.add(new Movie("Lagaan", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hUYQf8SuGgT0PeX.jpg", "https://www.youtube.com/watch?v=osWLkfoypNk", "The people of a small village in Victorian India stake their future on a game of cricket.", "bollywood", "Drama"));
        movies.add(new Movie("Zindagi Na Milegi Dobara", "https://image.tmdb.org/t/p/w500/6J7fW3z8j3J3J3J3J3J3J3J3J3J.jpg", "https://www.youtube.com/watch?v=ifIBOKQfjkw", "Three friends who were inseparable in childhood have drifted apart as adults.", "bollywood", "Comedy"));
        movies.add(new Movie("Gully Boy", "https://image.tmdb.org/t/p/w500/1Lh1LER4xq3u3vPkr14yYHl3KjL.jpg", "https://www.youtube.com/watch?v=JfbxcD6biK4", "A coming-of-age story based on the lives of street rappers in Mumbai.", "bollywood", "Drama"));
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
        movies.add(new Movie("The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", "https://www.youtube.com/watch?v=EXeTwQWrcwY", "Batman sets out to dismantle the remaining criminal organizations that plague the streets.", "hollywood", "Action"));
        movies.add(new Movie("Inception", "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg", "https://www.youtube.com/watch?v=YoHD9XEInc0", "A skilled thief is given a chance at redemption if he can pull off an impossible heist.", "hollywood", "Sci-Fi"));
        movies.add(new Movie("Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", "https://www.youtube.com/watch?v=zSWdZVtXT7E", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.", "hollywood", "Sci-Fi"));
        return movies;
    }

    public static List<Movie> getKoreanMovies() {
        List<Movie> movies = new ArrayList<>();
        // Using URLs for movies without drawable resources
        movies.add(new Movie("Parasite", "https://i.postimg.cc/d1hB4m2z/parasite.jpg", "https://www.youtube.com/watch?v=5xH0HfJHsaY", "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.", "korean", "Thriller"));
        movies.add(new Movie("Train to Busan", "https://i.postimg.cc/mkjZ7B61/traintobusan.jpg", "https://www.youtube.com/watch?v=1ovgxN2VWNc", "While a zombie virus breaks out in South Korea, passengers struggle to survive on the train from Seoul to Busan.", "korean", "Horror"));
        movies.add(new Movie("Oldboy", "https://image.tmdb.org/t/p/w500/pWDtjs568ZfOTMbURQBYuT4wXKl.jpg", "https://www.youtube.com/watch?v=2HjjKzfzqyI", "After being kidnapped and imprisoned for 15 years, Oh Dae-Su is released, only to find that he must find his captor in 5 days.", "korean", "Thriller"));
        movies.add(new Movie("The Handmaiden", "https://image.tmdb.org/t/p/w500/7qZ0vbqXKdJ4d1r7jJqH6x9V8L.jpg", "https://www.youtube.com/watch?v=whldChqCsYk", "A woman is hired as a handmaiden to a Japanese heiress, but secretly she is involved in a plot to defraud her.", "korean", "Thriller"));
        movies.add(new Movie("Memories of Murder", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=Qx8E2bwJN8E", "In a small Korean province in 1986, two detectives struggle with the case of multiple young women being found raped and murdered.", "korean", "Crime"));
        movies.add(new Movie("The Wailing", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A stranger arrives in a little village and soon after a mysterious sickness starts spreading.", "korean", "Horror"));
        movies.add(new Movie("Burning", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "Jong-su bumps into a girl who used to live in the same neighborhood, who asks him to look after her cat.", "korean", "Mystery"));
        movies.add(new Movie("The Host", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A monster emerges from Seoul's Han River and focuses its attention on attacking people.", "korean", "Horror"));
        movies.add(new Movie("I Saw the Devil", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A secret agent exacts revenge on a serial killer through a series of captures and releases.", "korean", "Thriller"));
        movies.add(new Movie("A Taxi Driver", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A man accidentally becomes involved in the Gwangju Uprising of 1980.", "korean", "Drama"));
        movies.add(new Movie("The Man from Nowhere", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A quiet pawnshop keeper with a violent past takes on a drug-and-organ trafficking ring.", "korean", "Action"));
        movies.add(new Movie("The Chaser", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A disgraced ex-policeman who runs a small ring of prostitutes finds himself in a race against time.", "korean", "Thriller"));
        movies.add(new Movie("The Age of Shadows", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A Korean police captain in Japanese-controlled Shanghai must choose between his country and his duty.", "korean", "Action"));
        movies.add(new Movie("A Bittersweet Life", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A high-ranking mobster is asked to watch over his boss's mistress, but he falls in love with her.", "korean", "Action"));
        movies.add(new Movie("The Thieves", "https://image.tmdb.org/t/p/w500/5Z8qATuW8h7w6z3z3z3z3z3z3z3z.jpg", "https://www.youtube.com/watch?v=5Z8qATuW8h7w6z3z3z3z3z3z3z", "A group of Korean and Chinese thieves team up for a heist of a diamond worth $20 million.", "korean", "Action"));
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
        movies.add(new Movie("Demon Slayer", "https://image.tmdb.org/t/p/w500/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg", "https://www.youtube.com/watch?v=VQGCKyvzIM4", "A family is attacked by demons and only two members survive - Tanjiro and his sister Nezuko.", "anime", "Action"));
        movies.add(new Movie("Attack on Titan", "https://image.tmdb.org/t/p/w500/hTP1dtLGFamxq5hmMnejTK2V1wZ.jpg", "https://www.youtube.com/watch?v=M_U8D1f1Qzk", "Humanity fights for survival against the terrifying Titans.", "anime", "Action"));
        movies.add(new Movie("One Piece", "https://image.tmdb.org/t/p/w500/4Mt7WHox67uJ1yErwT1c1e1n6Fq.jpg", "https://www.youtube.com/watch?v=Ades3pQbeh8", "Monkey D. Luffy and his pirate crew explore the Grand Line to find the treasure One Piece.", "anime", "Adventure"));
        movies.add(new Movie("Naruto", "https://image.tmdb.org/t/p/w500/vauCEnR7Cw9ar3z7eIoUp8sKDzE.jpg", "https://www.youtube.com/watch?v=j2hiC9BmjRQ", "Naruto Uzumaki, a young ninja who seeks recognition from his peers and dreams of becoming the Hokage.", "anime", "Action"));
        movies.add(new Movie("Dragon Ball Z", "https://image.tmdb.org/t/p/w500/3wx3EAMtqnbSLhGG8NrqXriCUIQ.jpg", "https://www.youtube.com/watch?v=1ZkACacdh-M", "Goku and his friends defend Earth against villains including aliens, androids, and magical creatures.", "anime", "Action"));
        movies.add(new Movie("My Hero Academia", "https://image.tmdb.org/t/p/w500/phuYuzqWW9ru8EA3HVjE9W2T1x1.jpg", "https://www.youtube.com/watch?v=EPVkcwyLQQ8", "A boy born without superpowers in a world where they are the norm finds his own way to become a hero.", "anime", "Action"));
        movies.add(new Movie("Death Note", "https://image.tmdb.org/t/p/w500/iigTJJskR1PcjpQ4d1sBmmp6S4.jpg", "https://www.youtube.com/watch?v=tJZtOrm-WP4", "A high school student discovers a supernatural notebook that allows him to kill anyone.", "anime", "Thriller"));
        movies.add(new Movie("Fullmetal Alchemist", "https://image.tmdb.org/t/p/w500/2Z8yETmOZvzO9rYSNz6wfPhzVtl.jpg", "https://www.youtube.com/watch?v=kw4n1Y3ejAY", "Two brothers search for a Philosopher's Stone after an attempt to revive their deceased mother goes wrong.", "anime", "Fantasy"));
        movies.add(new Movie("Studio Ghibli Collection", "https://image.tmdb.org/t/p/w500/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg", "https://www.youtube.com/watch?v=4vPeTSRd580", "A collection of masterpieces from Studio Ghibli including Howl's Moving Castle, Princess Mononoke, and more.", "anime", "Fantasy"));
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
