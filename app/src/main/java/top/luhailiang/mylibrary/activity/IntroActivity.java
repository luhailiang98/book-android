package top.luhailiang.mylibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;

import top.luhailiang.mylibrary.R;
import top.luhailiang.mylibrary.utils.MyApplication;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setFadeAnimation();
        //setZoomAnimation();
        setFlowAnimation();
        //setSlideOverAnimation();
        //setDepthAnimation();

        addSlide(AppIntro2Fragment.newInstance("MYLIBRARY学生端", "欢迎使用MYLIBRARY学生端", R.drawable.logo, getResources().getColor(R.color.colorAccent)));
        addSlide(AppIntro2Fragment.newInstance("丰富互动", "海量图书", R.drawable.logo, getResources().getColor(R.color.colorAccent)));
        addSlide(AppIntro2Fragment.newInstance("操作简便", "你值得拥有", R.drawable.logo, getResources().getColor(R.color.colorAccent)));
    }


    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(MyApplication.mContext, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(MyApplication.mContext, LoginActivity.class);
        startActivity(intent);
    }
}
