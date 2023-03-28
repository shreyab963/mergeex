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
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
                return true;
            }
            else if (item.getItemId() == R.id.alarm) {
                replaceFragment(new AlarmFragment());
                return true;
            }
            else if (item.getItemId() == R.id.calendar) {
                replaceFragment(new CalendarFragment());
                return true;
            }
            else if (item.getItemId() == R.id.cookbook) {
                replaceFragment(new CookbookFragment());
                return true;
            }
            else if (item.getItemId() == R.id.fitness) {
                replaceFragment(new FitnessFragment());
                return true;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}