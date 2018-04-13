package com.nicholasdoglio.eulerityhackathon.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nicholasdoglio.eulerityhackathon.R;
import com.nicholasdoglio.eulerityhackathon.data.model.Image;
import com.nicholasdoglio.eulerityhackathon.util.ImageLoader;
import com.nicholasdoglio.eulerityhackathon.util.NavigationController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Nicholas Doglio
 */
public class ImageListAdapter extends ListAdapter<Image, ImageListAdapter.ImageViewHolder> {
    private static final DiffUtil.ItemCallback<Image> DIFF_CALLBACK = new DiffUtil.ItemCallback<Image>() {
        @Override
        public boolean areItemsTheSame(@NonNull Image oldImage, @NonNull Image newImage) {
            return oldImage.getUrl().equals(newImage.getUrl());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Image oldImage, @NonNull Image newImage) {
            return oldImage.equals(newImage);
        }
    };
    private Context imageListContext;
    private NavigationController navigationController;

    ImageListAdapter(Context imageListContext, NavigationController navigationController) {
        super(DIFF_CALLBACK);
        this.imageListContext = imageListContext;
        this.navigationController = navigationController;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_item)
        ImageView imageView;

        private Image image;

        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(Image image) {
            this.image = image;
            ImageLoader.loadImageNoEffect(imageListContext, image.getUrl(), imageView);
        }

        @OnClick(R.id.image_item)
        public void imageClicked() {
            navigationController.openEditFragment(image.getUrl());
        }
    }
}