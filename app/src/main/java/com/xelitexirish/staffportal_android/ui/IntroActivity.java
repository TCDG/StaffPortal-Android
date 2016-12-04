package com.xelitexirish.staffportal_android.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;
import com.github.paolorotolo.appintro.ISlidePolicy;
import com.xelitexirish.staffportal_android.MainActivity;
import com.xelitexirish.staffportal_android.R;

/**
 * Created by XeliteXirish on 03/12/2016 (www.xelitexirish.com)
 */

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Punishment List", "View the reason that you were punished on your favourite discord server", R.mipmap.ic_launcher, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("Submittion Tool", "A staff tool that allows you to submit punishments to the database!", R.mipmap.ic_launcher, Color.parseColor("#1976D2")));

        addSlide(SlideDetails.newInstance(R.layout.fragment_intro_details));

        setFadeAnimation();

        showSkipButton(false);
        setProgressButtonEnabled(true);

        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this, MainActivity.class));
    }

    public static class SlideDetails extends Fragment implements ISlidePolicy, ISlideBackgroundColorHolder {

        private static final String ARG_LAYOUT_RES_ID = "layoutResId";
        private int layoutResId;
        private EditText editTextHostname;

        public static SlideDetails newInstance(int layoutResId) {
            SlideDetails slideDetails = new SlideDetails();

            Bundle args = new Bundle();
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
            slideDetails.setArguments(args);

            return slideDetails;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
                layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(layoutResId, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            editTextHostname = (EditText) view.findViewById(R.id.editTextHostname);

        }

        @Override
        public boolean isPolicyRespected() {
            return (isUrl(editTextHostname.getText().toString()));
        }

        @Override
        public void onUserIllegallyRequestedNextPage() {
            Toast.makeText(getContext(), "Please enter a valid url!", Toast.LENGTH_SHORT).show();

        }

        @Override
        public int getDefaultBackgroundColor() {
            return Color.parseColor("#000000");
        }

        @Override
        public void setBackgroundColor(@ColorInt int backgroundColor) {

        }

        private boolean isUrl(String url) {
            return url.startsWith("http://") || (url.startsWith("https://"));
        }


    }
}
