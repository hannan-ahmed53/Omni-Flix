package com.example.project67.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.project67.R;
import com.example.project67.model.Profile;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private final List<Profile> profiles;
    private final OnProfileClickListener onProfileClickListener;
    private final OnProfileDeleteListener onProfileDeleteListener;

    public interface OnProfileClickListener {
        void onProfileClick(Profile profile);
    }
    
    public interface OnProfileDeleteListener {
        void onProfileDelete(Profile profile);
    }

    public ProfileAdapter(List<Profile> profiles, OnProfileClickListener listener, OnProfileDeleteListener deleteListener) {
        this.profiles = profiles;
        this.onProfileClickListener = listener;
        this.onProfileDeleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile profile = profiles.get(position);
        holder.bind(profile, onProfileClickListener, onProfileDeleteListener);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    static class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView profileImageView;
        ImageButton deleteButton;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.profile_name);
            profileImageView = itemView.findViewById(R.id.profile_image);
            deleteButton = itemView.findViewById(R.id.delete_profile_button);
        }

        public void bind(final Profile profile, final OnProfileClickListener listener, final OnProfileDeleteListener deleteListener) {
            nameTextView.setText(profile.name);
            
            // Load profile image
            String imageName = profile.profileImage != null ? profile.profileImage : "guts";
            int imageResource = itemView.getContext().getResources().getIdentifier(
                    imageName, "drawable", itemView.getContext().getPackageName());
            
            if (imageResource != 0) {
                Glide.with(itemView.getContext())
                        .load(imageResource)
                        .into(profileImageView);
            } else {
                // Fallback to guts image if not found
                int gutsResource = itemView.getContext().getResources().getIdentifier(
                        "guts", "drawable", itemView.getContext().getPackageName());
                if (gutsResource != 0) {
                    Glide.with(itemView.getContext())
                            .load(gutsResource)
                            .into(profileImageView);
                }
            }
            
            itemView.setOnClickListener(v -> listener.onProfileClick(profile));
            
            if (deleteButton != null) {
                deleteButton.setOnClickListener(v -> {
                    if (deleteListener != null) {
                        deleteListener.onProfileDelete(profile);
                    }
                });
            }
        }
    }
}
