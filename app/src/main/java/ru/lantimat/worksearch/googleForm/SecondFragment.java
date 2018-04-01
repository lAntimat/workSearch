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

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;

import ru.lantimat.worksearch.R;

/**
 * Created by lAntimat on 22.02.2018.
 */

public class SecondFragment extends Fragment {

    private Calendar calendar = Calendar.getInstance();

        @SuppressWarnings("ConstantConditions")
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            MaterialSpinner spinner = (MaterialSpinner) getView().findViewById(R.id.spinner);
            spinner.setItems("Школьник", "Студент", "Соискатель");
            ((GoogleFormActivity)getActivity()).status = spinner.getItems().get(0).toString();
            spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    ((GoogleFormActivity)getActivity()).status = item;
                }
            });

            MaterialSpinner spinner2 = (MaterialSpinner) getView().findViewById(R.id.spinner2);
            spinner2.setItems("Высшее", "Среднее специальное", "Среднее");
            ((GoogleFormActivity)getActivity()).education = spinner2.getItems().get(0).toString();
            spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    ((GoogleFormActivity)getActivity()).education = item;
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
                    ((GoogleFormActivity)getActivity()).studyPlace = editable.toString();
                }
            });
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.second_fragment_layout, container, false);
        }
}
