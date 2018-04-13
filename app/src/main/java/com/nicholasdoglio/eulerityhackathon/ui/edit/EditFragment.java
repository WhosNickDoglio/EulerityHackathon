package com.nicholasdoglio.eulerityhackathon.ui.edit;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nicholasdoglio.eulerityhackathon.R;
import com.nicholasdoglio.eulerityhackathon.util.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * @author Nicholas Doglio
 */
public class EditFragment extends Fragment implements EditContract.View {
    private static final String IMAGE_KEY = "IMAGE_URL";
    @Inject
    EditContract.Presenter editPresenter;
    @BindView(R.id.edit_image)
    ImageView editImage;
    @BindView(R.id.effect_spinner)
    Spinner editEffectSpinner;
    @BindView(R.id.add_text)
    Button addTextButton;
    @BindView(R.id.upload_text)
    Button uploadImageButton;
    @BindView(R.id.edit_image_progress_bar)
    ContentLoadingProgressBar editImageProgressbar;
    @BindView(R.id.edit_fragment)
    ConstraintLayout constraintLayout;
    @BindString(R.string.network_error)
    String networkError;
    @BindString(R.string.upload_complete)
    String uploadComplete;
    @BindColor(android.R.color.white)
    int textColor;
    private Unbinder editImageUnbinder;
    private String imageUrl;

    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment create(String url) {
        EditFragment editFragment = new EditFragment();

        Bundle arguments = new Bundle();
        arguments.putString(IMAGE_KEY, url);
        editFragment.setArguments(arguments);

        return editFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(IMAGE_KEY)) {
            imageUrl = getArguments().getString(IMAGE_KEY);
            ImageLoader.loadImageNoEffect(getContext(), imageUrl, editImage);

        } else {
            Toast.makeText(getContext(), networkError, Toast.LENGTH_SHORT).show();
        }

        editPresenter.getUploadLink();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        editImageUnbinder = ButterKnife.bind(this, view);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.
                createFromResource(Objects.requireNonNull(getContext()),
                        R.array.effects, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editEffectSpinner.setAdapter(spinnerAdapter);

        addTextButton.setOnClickListener(view1 -> showAddTextViewDialog());

        uploadImageButton.setOnClickListener(view12 -> {
            String root = Environment.getExternalStorageDirectory().toString();
            editImage.setDrawingCacheEnabled(true);
            BitmapDrawable imageDrawable = (BitmapDrawable) editImage.getDrawable();
            Bitmap bitmap = imageDrawable.getBitmap();
            saveBitmap(bitmap, root);
            editPresenter.uploadImage(imageUrl, root);
        });

        editEffectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageLoader.loadImageWithEffect(getContext(), imageUrl,
                        editImage, adapterView.getItemAtPosition(i).toString(), editImageProgressbar);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        editPresenter.attach(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        editPresenter.clearDisposables();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        editPresenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        editImageUnbinder.unbind();
    }

    @Override
    public void uploadComplete() {
        Toast.makeText(getContext(), uploadComplete, Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();

    }

    /**
     * Presents a dialog for the user to enter a text value
     */
    private void showAddTextViewDialog() {
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));

        builder
                .setView(inflater.inflate(R.layout.dialog_text, null))
                .setPositiveButton(R.string.dialog_text_positive, (dialogInterface, i) -> {
                    Dialog dialog = (Dialog) dialogInterface;
                    EditText dialogEditText = dialog.findViewById(R.id.dialog_text_edit_text);
                    createTextView(dialogEditText.getText().toString());
                })
                .setNegativeButton(R.string.dialog_text_negative,
                        (dialogInterface, i) -> dialogInterface.dismiss());

        builder.create().show();
    }

    /**
     * Creates a textView from give input and centers it within the image
     *
     * @param userInput
     */
    private void createTextView(String userInput) {
        ConstraintSet constraintSet = new ConstraintSet();
        TextView userCreatedTextView = new TextView(getContext());
        userCreatedTextView.setId(R.id.userCreatedTextView);
        userCreatedTextView.setText(userInput);
        userCreatedTextView.setTextColor(textColor);
        userCreatedTextView.setTextSize(32);

        constraintLayout.addView(userCreatedTextView);
        constraintSet.clone(constraintLayout);

        constraintSet.connect(userCreatedTextView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        constraintSet.connect(userCreatedTextView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        constraintSet.connect(userCreatedTextView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        constraintSet.connect(userCreatedTextView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

        constraintSet.applyTo(constraintLayout);
    }

    /**
     * Saves the image to the file system
     *
     * @param bitmap
     * @param root
     */
    private void saveBitmap(Bitmap bitmap, String root) {

        File myDir = new File(root + "/Download");
        myDir.mkdirs();
        Random generator = new Random();
        String fname = "Image.jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}