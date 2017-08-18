package danandroid.course.multitouchandappintro.appintro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import danandroid.course.multitouchandappintro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppIntroFragment extends Fragment {


    //newInstance -> ctrl+space:
    //also added args.putInt(...)

    public static AppIntroFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        AppIntroFragment fragment = new AppIntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView ivAppIntro;
    int[] imageResources = {R.drawable.page_1, R.drawable.page_2, R.drawable.page_3};

//    public static int getImageSize(){
//        return imageResources.length;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_app_intro, container, false);
        ivAppIntro = v.findViewById(R.id.ivIntro);

        //in which position are we in? (viewpager position argument)
        int position = getArguments().getInt("position");
        //get the image by position
        int imgResource = imageResources[position];

        ivAppIntro.setImageResource(imgResource);

        return v;
    }

}
