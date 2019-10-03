package com.example.sugarorm_dagger2.di.component;

import com.example.sugarorm_dagger2.di.model.Chronometer_model;
import com.example.sugarorm_dagger2.di.model.Data;
import com.example.sugarorm_dagger2.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aliyari on 1/18/2018.
 */

@Singleton
@Component(modules = DataModule.class)
public interface DataComponent {

    Data provideData();
    Chronometer_model providChronometer();
}
