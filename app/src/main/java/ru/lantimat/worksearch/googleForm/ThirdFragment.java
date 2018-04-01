package ru.lantimat.worksearch.googleForm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;

import ru.lantimat.worksearch.R;

/**
 * Created by lAntimat on 22.02.2018.
 */

public class ThirdFragment extends Fragment {

    private Calendar calendar = Calendar.getInstance();

        @SuppressWarnings("ConstantConditions")
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            RadioGroup radioGroup = getView().findViewById(R.id.group);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // find which radio button is selected
                    if (checkedId == R.id.radioButton1) {
                        ((GoogleFormActivity)getActivity()).computerStatus = "Не владею";
                    } else if (checkedId == R.id.radioButton2) {
                        ((GoogleFormActivity)getActivity()).computerStatus = "начинающий пользователь";
                    } else if (checkedId == R.id.radioButton3) {
                        ((GoogleFormActivity)getActivity()).computerStatus = "уверенный пользователь";
                    }
                }

            });

            RadioGroup radioGroup2 = getView().findViewById(R.id.group2);
            radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // find which radio button is selected
                    if (checkedId == R.id.radioButton1) {
                        ((GoogleFormActivity)getActivity()).workType = "постоянная";
                    } else if (checkedId == R.id.radioButton2) {
                        ((GoogleFormActivity)getActivity()).workType = "временная (летняя/сезонная/подработка)";
                    }
                }

            });

            EditText editText = getView().findViewById(R.id.editText);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    ((GoogleFormActivity)getActivity()).work = editable.toString();
                }
            });

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.third_fragment_layout, container, false);
        }
}
