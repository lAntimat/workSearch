package ru.lantimat.worksearch.googleForm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.Calendar;
import java.util.HashMap;

import ru.lantimat.worksearch.R;

/**
 * Created by lAntimat on 22.02.2018.
 */

public class FourFragment extends Fragment {
    HashMap<String, String> map = new HashMap<>();

    private Calendar calendar = Calendar.getInstance();

    CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9;
        @SuppressWarnings("ConstantConditions")
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            checkBox = getView().findViewById(R.id.checkBox);
            checkBox2 = getView().findViewById(R.id.checkBox2);
            checkBox3 = getView().findViewById(R.id.checkBox3);
            checkBox4 = getView().findViewById(R.id.checkBox4);
            checkBox5 = getView().findViewById(R.id.checkBox5);
            checkBox6 = getView().findViewById(R.id.checkBox6);
            checkBox7 = getView().findViewById(R.id.checkBox7);
            checkBox8 = getView().findViewById(R.id.checkBox8);
            checkBox9 = getView().findViewById(R.id.checkBox9);

            CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    switch (compoundButton.getId()) {
                        case R.id.checkBox:
                            if(b) map.put("1", "промышленность, производство");
                            else map.remove("1");
                            break;
                        case R.id.checkBox2:
                            if(b)
                            map.put("2", "строительство");
                            else map.remove("2");
                            break;
                        case R.id.checkBox3:
                            if(b)
                            map.put("3", "оптовая и розничная торговля");
                            else map.remove("3");
                            break;
                        case R.id.checkBox4:
                            if(b)
                            map.put("4", "гостиницы и рестораны");
                            else map.remove("4");
                            break;
                        case R.id.checkBox5:
                            if(b)
                            map.put("5", "образование");
                            else map.remove("5");
                            break;
                        case R.id.checkBox6:
                            if(b)
                            map.put("6", "промышленность, производство");
                            else map.remove("6");
                            break;
                        case R.id.checkBox7:
                            if(b)
                            map.put("7", "промышленность, производство");
                            else map.remove("7");
                            break;
                        case R.id.checkBox8:
                            if(b)
                            map.put("8", "промышленность, производство");
                            else map.remove("8");
                            break;
                        case R.id.checkBox9:
                            if(b)
                            map.put("9", "промышленность, производство");
                            else map.remove("9");
                            break;
                    }
                    ((GoogleFormActivity)getActivity()).mapPreference = map;
                }
            };



            checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox2.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox3.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox4.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox5.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox6.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox7.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox8.setOnCheckedChangeListener(onCheckedChangeListener);
            checkBox9.setOnCheckedChangeListener(onCheckedChangeListener);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.four_fragment_layout, container, false);
        }
}
