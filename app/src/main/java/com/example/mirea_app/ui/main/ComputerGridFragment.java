package com.example.mirea_app.ui.main;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mirea_app.R;

import java.util.Calendar;

public class ComputerGridFragment extends Fragment {

    private int computer = 0;
    private View view;
    private int mColumnCount = 5;
    TextView timeTextView;
    TextView dateTextView;
    Calendar dateAndTime= Calendar.getInstance();
    int time;
    AlertDialog.Builder builder;
    AlertDialog dialog;

    public ComputerGridFragment() {
    }


    public static ComputerGridFragment newInstance() {
        ComputerGridFragment fragment = new ComputerGridFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_computer_list, container, false);

        timeTextView = view.findViewById(R.id.selected_time);
        dateTextView = view.findViewById(R.id.selected_date);
        //setInitialDateTime();

        view.findViewById(R.id.date_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });

        view.findViewById(R.id.time_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v);
            }
        });

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title);
        builder.setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();


        view.findViewById(R.id.finish_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res =
                        DateUtils.formatDateTime(getActivity(),
                                dateAndTime.getTimeInMillis(),
                                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR)
                                + " "
                        + DateUtils.formatDateTime(getActivity(),
                        dateAndTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_TIME);
                switch (time){
                    case 0:
                        res += " на час";
                        break;
                    case 1:
                        res += " на полтора часа";
                        break;
                    case 2:
                        res += " на два часа";
                        break;
                }
                dialog.setMessage(res);
                dialog.show();
            }
        });

        Spinner spinner = view.findViewById(R.id.time_spinner);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                time = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_computer_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        recyclerView.setAdapter(new ComputerGridRecyclerViewAdapter(this));

        return view;
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(getActivity(), d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(getActivity(), t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }


    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute>30?30:0);

            timeTextView.setText(DateUtils.formatDateTime(getActivity(),
                    dateAndTime.getTimeInMillis(),
                    DateUtils.FORMAT_SHOW_TIME));
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            dateTextView.setText(DateUtils.formatDateTime(getActivity(),
                    dateAndTime.getTimeInMillis(),
                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        }
    };

    public void setSelectedComp(int pos){
        computer = pos;
        ((TextView)view.findViewById(R.id.selected_comp)).setText("компьютер №" + Integer.toString(pos));
    }
}