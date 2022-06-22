package com.s4team.java.musiachivem.ui.view.home.banner;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.s4team.java.musiachivem.R;

import java.util.ArrayList;
import java.util.List;

public class BannerSlider extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private int limitSlider = 3;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_slider);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItemList = new ArrayList<>();
        sliderItemList.add(new SliderItem(R.drawable.rooftop_image));
        sliderItemList.add(new SliderItem(R.drawable.rooftop_image));
        sliderItemList.add(new SliderItem(R.drawable.rooftop_image));
        sliderItemList.add(new SliderItem(R.drawable.rooftop_image));
        sliderItemList.add(new SliderItem(R.drawable.rooftop_image));
        sliderItemList.add(new SliderItem(R.drawable.rooftop_image));
        this.setupViewPager(viewPager2);

        viewPager2.setAdapter(new SliderAdapter(sliderItemList, viewPager2));
    }

    void setupViewPager(ViewPager2 viewPager) {
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(this.limitSlider);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager.setPageTransformer(compositePageTransformer);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(slierImage); // Slide duration 3 seconds
                handler.postDelayed(slierImage, 3000);
            }
        });
    }

    private Runnable slierImage = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
}
