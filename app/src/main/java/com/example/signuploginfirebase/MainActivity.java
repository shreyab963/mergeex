package com.example.signuploginfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.signuploginfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        replacedFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {

                case R.id.home:
                    replacedFragment(new HomeFragment());
                    break;
                case R.id.alarm:
                    replacedFragment(new AlarmFragment());
                    break;
                case R.id.calendar:
                    replacedFragment(new CalendarFragment());
                    break;
                case R.id.cookbook:
                    replacedFragment(new CookbookFragment());
                    break;
                case R.id.fitness:
                    replacedFragment(new FitnessFragment());
                    break;
            }

            return true;
        });
    }

    private void replacedFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}