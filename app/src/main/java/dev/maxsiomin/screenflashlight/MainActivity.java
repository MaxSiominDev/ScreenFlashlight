package dev.maxsiomin.screenflashlight;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class MainActivity extends AppCompatActivity {

    // Root of R.layout.activity_main
    private LinearLayoutCompat layout;

    private TextView textView;

    private boolean flashlightEnabled;

    private float brightnessBeforeChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main_activity_root);
        layout.setOnClickListener(v -> {
            if (!flashlightEnabled) {
                enableFlashlight();
            } else {
                disableFlashlight();
            }
        });

        textView = findViewById(R.id.textview);
    }

    private void enableFlashlight() {
        LayoutParams params = getWindow().getAttributes();

        // Save brightness to restore it later
        brightnessBeforeChanged = params.screenBrightness;

        // Set screen brightness to maximum
        params.screenBrightness = 1F;
        getWindow().setAttributes(params);

        textView.setVisibility(View.INVISIBLE);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        flashlightEnabled = true;
    }

    private void disableFlashlight() {
        LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = brightnessBeforeChanged;
        getWindow().setAttributes(layout);

        textView.setVisibility(View.VISIBLE);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.show();

        flashlightEnabled = false;
    }
}
