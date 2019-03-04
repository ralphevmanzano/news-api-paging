package com.ralphevmanzano.newspaging.di;

import com.ralphevmanzano.newspaging.NewsApp;
import com.ralphevmanzano.newspaging.di.modules.ActivityBuildersModule;
import com.ralphevmanzano.newspaging.di.modules.AppModule;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {AppModule.class, AndroidSupportInjectionModule.class, ActivityBuildersModule.class})
public interface AppComponent extends AndroidInjector<NewsApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsApp>{}
}

