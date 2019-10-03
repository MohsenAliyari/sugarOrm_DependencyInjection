package com.example.sugarorm_dagger2;

import android.os.Handler;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.example.sugarorm_dagger2.adapter.ChronometerAdapter;
import com.example.sugarorm_dagger2.di.component.DaggerDataComponent;
import com.example.sugarorm_dagger2.di.component.DataComponent;
import com.example.sugarorm_dagger2.di.model.Data;
import com.example.sugarorm_dagger2.di.module.DataModule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    Button startBtn, saveBtn, stopBtn;
    Handler handler = null;
    ImageView delete;
    RecyclerView recycler;
    ArrayList<Data> dataList = new ArrayList<>();
    DataComponent component = null;
    Data data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        startBtn = (Button) findViewById(R.id.btn_start);
        stopBtn = (Button) findViewById(R.id.btn_stop);
        saveBtn = (Button) findViewById(R.id.btn_saveItem);
        delete = (ImageView) findViewById(R.id.img_delete);
        handler = new Handler();
        saveBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == startBtn) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                }
            });
            startBtn.setVisibility(View.GONE);
            stopBtn.setVisibility(View.VISIBLE);
            saveBtn.setVisibility(View.VISIBLE);
        }
        if (v == saveBtn) {
            getValue();
        }
        if (v == stopBtn) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.stop();
            chronometer.clearComposingText();
            saveBtn.setVisibility(View.INVISIBLE);
            stopBtn.setVisibility(View.GONE);
            startBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * get current date and time to write in db
     */
    private void getValue() {

        /**
         * get date by simple format
         * */
        SimpleDateFormat df2 = new SimpleDateFormat(" hh:mm a ");
        Date getCurentdate = new Date();
        String time = df2.format(getCurentdate);


        Calendar calendar = java.util.Calendar.getInstance();

        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH) + 1;
        int day = calendar.get(calendar.DAY_OF_MONTH);

        String chron_time = (chronometer.getText().toString());
        chronometer.setBase(SystemClock.elapsedRealtime());


        /**
         * insert data in Sugar DB
         */
        try {
            component = DaggerDataComponent.builder().dataModule(new DataModule()).build();
            data = component.provideData();
            data.setYears(String.valueOf(year));
            data.setMonth(String.valueOf(month));
            data.setDay(String.valueOf(day));
            data.setTime(String.valueOf(time));
            data.setChronometer(String.valueOf(chron_time));

        } catch (Exception e) {
            e.printStackTrace();
            // you got a problem
        }
        dataList.add(data);
        if (dataList.size() > 0) {
            ChronometerAdapter adapter = new ChronometerAdapter(getApplicationContext(), dataList);
            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                    , RecyclerView.VERTICAL, false));
            recycler.setAdapter(adapter);
        }
    }


}

