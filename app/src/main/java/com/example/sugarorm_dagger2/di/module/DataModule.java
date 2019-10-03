package com.example.sugarorm_dagger2.di.module;

import com.example.sugarorm_dagger2.di.model.Chronometer_model;
import com.example.sugarorm_dagger2.di.model.Data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aliyari on 1/18/2018.
 */

@Module
public class DataModule {


    @Singleton
    @Provides
    Chronometer_model providChronometer(){
        return new Chronometer_model();
    }


    @Provides
    @Singleton
    Data provideData(){
        return new Data(new Chronometer_model());
    }
}
