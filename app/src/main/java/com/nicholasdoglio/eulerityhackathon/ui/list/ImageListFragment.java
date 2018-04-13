package com.nicholasdoglio.eulerityhackathon.ui.list;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nicholasdoglio.eulerityhackathon.R;
import com.nicholasdoglio.eulerityhackathon.data.model.Image;
import com.nicholasdoglio.eulerityhackathon.util.NavigationController;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author Nicholas Doglio
 */
public class ImageListFragment extends Fragment implements ImageListContract.View {

    @Inject
    ImageListContract.Presenter imageListPresenter;

    @Inject
    NavigationController navigationController;

    @BindView(R.id.image_list_toolbar)
    Toolbar imageListToolbar;

    @BindView(R.id.image_list_swipe_refresh_layout)
    SwipeRefreshLayout imageListSwipeRefreshLayout;

    @BindView(R.id.image_List_recyclerView)
    RecyclerView imageListRecyclerView;

    @BindString(R.string.network_error)
    String networkError;

    @BindString(R.string.storage_permission_request)
    String permissionRequest;

    private Unbinder imageListFragmentUnbinder;
    private ImageListAdapter imageListAdapter;

    public ImageListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onActivityCreated(savedInstanceState);

        imageListAdapter = new ImageListAdapter(getContext(), navigationController);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        imageListRecyclerView.setLayoutManager(linearLayoutManager);
        imageListRecyclerView.setAdapter(imageListAdapter);
        imageListRecyclerView.setHasFixedSize(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);

        imageListFragmentUnbinder = ButterKnife.bind(this, view);

        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        if (!EasyPermissions.hasPermissions(Objects.requireNonNull(getContext()), permission)) {
            EasyPermissions.requestPermissions(this,
                    permissionRequest,
                    1,
                    permission);
        }

        imageListSwipeRefreshLayout.setOnRefreshListener(() -> imageListPresenter.loadNewImages());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        imageListPresenter.attach(this);
        imageListPresenter.getImages();
    }

    @Override
    public void onStop() {
        super.onStop();
        imageListPresenter.clearDisposables();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imageListPresenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imageListFragmentUnbinder.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void showLoadingBar() {
        imageListSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingBar() {
        imageListSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setList(List<Image> imageList) {
        imageListAdapter.submitList(imageList);
    }

    @Override
    public void showNetworkIssue() {
        Toast.makeText(getContext(), networkError, Toast.LENGTH_SHORT).show();
    }
}
