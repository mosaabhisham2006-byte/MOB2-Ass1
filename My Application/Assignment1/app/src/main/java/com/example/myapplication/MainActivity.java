package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragment2Listener, Fragment3.OnFragment3Listener {

    private ActivityMainBinding binding;
    private int step = 1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        fragment2 = Fragment2.newInstance();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment1)
                    .commit();
        }

        binding.buttonContinue.setOnClickListener(v -> {
            if (step == 1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
                step = 2;
            } else if (step == 2) {
                fragment2.submitData();
            } else if (step == 3) {
                Toast.makeText(this, "Onboarding Completed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDataSubmitted(String name) {
        Fragment3 fragment3 = Fragment3.newInstance(name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();

        step = 3;
        binding.buttonContinue.setEnabled(false);
        binding.buttonContinue.setText("Continue");
    }

    @Override
    public void onAgreeChanged(boolean isChecked) {
        binding.buttonContinue.setEnabled(isChecked);
        binding.buttonContinue.setText(isChecked ? "Finish" : "Continue");
    }
}
