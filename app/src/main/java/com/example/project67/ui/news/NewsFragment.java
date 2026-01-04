package com.example.project67.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project67.R;
import com.example.project67.adapter.NewsAdapter;
import com.example.project67.model.NewsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsFragment extends Fragment {

    private RecyclerView newsRecyclerView;
    private ProgressBar progressBar;
    private TextView errorText;
    private NewsAdapter adapter;
    private List<NewsItem> newsItems;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final String TMDB_API_KEY = "YOUR_TMDB_API_KEY"; // Replace with actual API key
    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/upcoming";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        progressBar = view.findViewById(R.id.news_progress_bar);
        errorText = view.findViewById(R.id.news_error_text);

        newsItems = new ArrayList<>();
        adapter = new NewsAdapter(newsItems, this::onNewsItemClick);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        newsRecyclerView.setAdapter(adapter);

        loadNews();

        return view;
    }

    private void loadNews() {
        progressBar.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.GONE);

        executor.execute(() -> {
            try {
                // For now, use mock data since we need API key
                // In production, replace with actual TMDB API call
                List<NewsItem> items = getMockNewsData();
                
                requireActivity().runOnUiThread(() -> {
                    newsItems.clear();
                    newsItems.addAll(items);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                });
            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    errorText.setVisibility(View.VISIBLE);
                    errorText.setText("Failed to load news. Please check your connection.");
                    Toast.makeText(requireContext(), "Error loading news", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private List<NewsItem> getMockNewsData() {
        List<NewsItem> items = new ArrayList<>();
        
        // Mock news data - Replace with actual TMDB API call
        items.add(new NewsItem("Dune: Part Two Release", "Dune: Part Two is set to release on March 1, 2024. The sequel continues Paul Atreides' journey.", "2024-03-01", "https://www.youtube.com/watch?v=U2Qp5pL3ovA"));
        items.add(new NewsItem("Oppenheimer Wins Best Picture", "Christopher Nolan's Oppenheimer wins the Academy Award for Best Picture at the 2024 Oscars.", "2024-03-10", "https://www.youtube.com/watch?v=uYPbbksJxIg"));
        items.add(new NewsItem("New Marvel Phase 5 Announcement", "Marvel Studios announces new projects for Phase 5, including new Avengers lineup.", "2024-03-15", "https://www.youtube.com/watch?v=wS_qbDztgVY"));
        items.add(new NewsItem("Barbie Sequel Confirmed", "Greta Gerwig confirms work on a Barbie sequel with Margot Robbie returning.", "2024-03-20", "https://www.youtube.com/watch?v=pBk4NYhWNMM"));
        items.add(new NewsItem("Avatar 3 Production Update", "James Cameron provides update on Avatar 3 production, expected release in 2025.", "2024-03-25", "https://www.youtube.com/watch?v=5PSNL1qE6VY"));
        items.add(new NewsItem("New Star Wars Series Announced", "Disney announces new Star Wars series focusing on the Old Republic era.", "2024-04-01", "https://www.youtube.com/watch?v=example"));
        items.add(new NewsItem("The Batman 2 Casting News", "Robert Pattinson returns as Batman in the sequel, with new villains announced.", "2024-04-05", "https://www.youtube.com/watch?v=example"));
        items.add(new NewsItem("Stranger Things Final Season", "Netflix announces the final season of Stranger Things will premiere in 2025.", "2024-04-10", "https://www.youtube.com/watch?v=b9EkMc79ZSU"));
        
        return items;
    }

    private void onNewsItemClick(NewsItem newsItem) {
        if (newsItem.getUrl() != null && !newsItem.getUrl().isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.getUrl()));
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Could not open link", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        executor.shutdown();
    }
}

