package com.ralphevmanzano.newspaging.di.modules;

import com.ralphevmanzano.newspaging.di.FragmentScope;
import com.ralphevmanzano.newspaging.ui.news.NewsAdapter;
import com.ralphevmanzano.newspaging.ui.news.NewsFragment;
import com.ralphevmanzano.newspaging.utils.NewsDiffUtilCallBack;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsFragmentModule {

    @FragmentScope
    @Provides
    NewsAdapter providesNewsAdapter(NewsDiffUtilCallBack newsDiffUtilCallBack, NewsFragment newsFragment) {
        return new NewsAdapter(newsDiffUtilCallBack, newsFragment);
    }

    @FragmentScope
    @Provides
    NewsDiffUtilCallBack providesNewsDiffUtilCallBack() {
        return new NewsDiffUtilCallBack();
    }
}
