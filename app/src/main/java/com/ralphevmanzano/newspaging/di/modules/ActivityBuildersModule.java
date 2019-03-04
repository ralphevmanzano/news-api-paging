package com.ralphevmanzano.newspaging.di.modules;

import com.ralphevmanzano.newspaging.MainActivity;
import com.ralphevmanzano.newspaging.di.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity bindMainActivity();
}
