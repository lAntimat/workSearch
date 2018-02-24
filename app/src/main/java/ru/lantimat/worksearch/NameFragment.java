package ru.lantimat.worksearch;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lAntimat on 22.02.2018.
 */

public class NameFragment extends Fragment {

    private Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d;

    EditText editText2;

    @SuppressWarnings("ConstantConditions")
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

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
                   ((GoogleFormActivity)getActivity()).fullName = editable.toString();
               }
           });

           editText2 = getView().findViewById(R.id.editText2);
           editTextClickListener();
           initDateTimePickers();
        }

    private void editTextClickListener() {
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), d,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
    }

    private void initDateTimePickers() {
        // установка обработчика выбора даты
        d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                Date date = new Date(calendar.getTimeInMillis());
                //mapPresenter.loadTrack(date);
                //mapPresenter.getTrack("", dateAndTime.getTimeInMillis(), new Date().getTimestamp());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru", "RU"));
                String formattedDate = simpleDateFormat.format(date);
                editText2.setText(formattedDate);
                ((GoogleFormActivity)getActivity()).bornDate = calendar;

                ((GoogleFormActivity)getActivity()).age = getAge(i, i1, i2);
                Log.d("age", getAge(i, i1, i2));

            }
        };
    }

    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.name_fragment_layout, container, false);
        }
}
