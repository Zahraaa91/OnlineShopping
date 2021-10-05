package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class PagerActivity extends AppCompatActivity {

    OnboardingAdapter onboardingAdapter;
    LinearLayout layoutOnboardingIndicator;
    MaterialButton buttonOboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicator = findViewById(R.id.layoutOboardingIndicator);
        buttonOboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();
        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewpager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicator();

        setCurrentOboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOboardingIndicator(position);
            }
        });


        buttonOboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

    }

    private void setupOnboardingItems(){

        List<OnboardingItems> onboardingItems = new ArrayList<>();

        OnboardingItems foodItems = new OnboardingItems();
        foodItems.setTitle("List of food items");
        foodItems.setDescription("There are various food items in our application for food");
        foodItems.setImage(R.drawable.firsy);

        OnboardingItems foodDelivery = new OnboardingItems();
        foodDelivery.setTitle("We provide best services of delivery");
        foodDelivery.setDescription("Our biker are very good in all delivery food items in your place");
        foodDelivery.setImage(R.drawable.second);

        OnboardingItems foodPayment = new OnboardingItems();
        foodPayment.setTitle("Plz use online payment ");
        foodPayment.setDescription("In today life time is very imp so use online payment or mobile banking");
        foodPayment.setImage(R.drawable.third);

        onboardingItems.add(foodItems);
        onboardingItems.add(foodDelivery);
        onboardingItems.add(foodPayment);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicator() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i=0; i<indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));

            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentOboardingIndicator(int index){
        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i=0; i<childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );

            }
            else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.onboarding_indicator_inactive));
            }
        }


        if (index == onboardingAdapter.getItemCount() - 1) {
            buttonOboardingAction.setText("Start");
        }else {
            buttonOboardingAction.setText("Next");
        }

    }

}

