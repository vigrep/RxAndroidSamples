package com.vigrep.rxandroidsamples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vigrep.rxandroidsamples.databinding.ActivityMainBinding;
import com.vigrep.rxandroidsamples.module.elementary.ElementaryFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private static final String[] PAGES = {"基础"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mBinding.viewPager.setAdapter(new FragmentStateAdapter(this) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return new ElementaryFragment();
            }

            @Override
            public int getItemCount() {
                return PAGES.length;
            }
        });

        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText(PAGES[position]);
                        break;
                    default:
                        break;
                }
            }
        }).attach();
    }
}