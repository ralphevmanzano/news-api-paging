package com.ralphevmanzano.newspaging.di.modules;

import com.ralphevmanzano.newspaging.di.FragmentScope;
import com.ralphevmanzano.newspaging.ui.news.NewsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = NewsFragmentModule.class)
    abstract NewsFragment contributeNewsFragment();
}
