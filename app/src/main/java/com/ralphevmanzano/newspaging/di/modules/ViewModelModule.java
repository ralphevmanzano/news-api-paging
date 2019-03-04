package com.ralphevmanzano.newspaging.di.modules;

import com.ralphevmanzano.newspaging.di.ViewModelKey;
import com.ralphevmanzano.newspaging.viewmodel.NewsViewModel;
import com.ralphevmanzano.newspaging.viewmodel.NewsViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    abstract ViewModel bindsNewsViewModel(NewsViewModel newsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(NewsViewModelFactory newsViewModelFactory);
}
