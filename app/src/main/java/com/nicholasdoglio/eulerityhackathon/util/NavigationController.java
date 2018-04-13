package com.nicholasdoglio.eulerityhackathon.util;

import android.support.v4.app.FragmentManager;

import com.nicholasdoglio.eulerityhackathon.R;
import com.nicholasdoglio.eulerityhackathon.ui.MainActivity;
import com.nicholasdoglio.eulerityhackathon.ui.edit.EditFragment;
import com.nicholasdoglio.eulerityhackathon.ui.list.ImageListFragment;

import javax.inject.Inject;

/**
 * @author Nicholas Doglio
 * Abstraction of navigation to clean up UI Code
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.fragments_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    /* Opens up ImageListFragment */
    public void openListFragment() {
        ImageListFragment imageListFragment = new ImageListFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, imageListFragment)
                .commitAllowingStateLoss();
    }

    /* Opens up EditFragment */
    public void openEditFragment(String imageUrl) {
        EditFragment editFragment = EditFragment.create(imageUrl);
        fragmentManager.beginTransaction()
                .replace(containerId, editFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}