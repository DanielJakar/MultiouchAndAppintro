package danandroid.course.multitouchandappintro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class PaoloIntroActivity extends com.github.paolorotolo.appintro.AppIntro {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addSlide(com.github.paolorotolo.appintro.AppIntroFragment.newInstance("This is the Title", "Description", R.drawable.page_1, 0xeeeeee));
        addSlide(danandroid.course.multitouchandappintro.appintro.AppIntroFragment.newInstance(0));
        addSlide(danandroid.course.multitouchandappintro.appintro.AppIntroFragment.newInstance(1));
        addSlide(danandroid.course.multitouchandappintro.appintro.AppIntroFragment.newInstance(2));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        EndIntro();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        EndIntro();

    }

    private void EndIntro() {
        SharedPreferences prefs = getSharedPreferences("appIntroduction", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("shouldShowIntro", false);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
