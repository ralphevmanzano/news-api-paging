package com.ralphevmanzano.newspaging.di.modules;

import com.ralphevmanzano.newspaging.NewsApp;
import com.ralphevmanzano.newspaging.api.NewsService;
import com.ralphevmanzano.newspaging.db.NewsDao;
import com.ralphevmanzano.newspaging.db.NewsDatabase;
import com.ralphevmanzano.newspaging.di.AppScope;
import com.ralphevmanzano.newspaging.utils.Pref;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = {AndroidInjectionModule.class, NetworkModule.class, ViewModelModule.class})
public class AppModule {

    @Provides
    @AppScope
    NewsService provideNewsService(OkHttpClient okHttpClient, Moshi moshi) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/everything/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(NewsService.class);
    }

    @Provides
    @AppScope
    Picasso picasso(NewsApp app, OkHttp3Downloader okHttp3Downloader) {
        Picasso picasso = new Picasso.Builder(app.getApplicationContext())
                .downloader(okHttp3Downloader)
                .indicatorsEnabled(true)
                .build();
        Picasso.setSingletonInstance(picasso);
        return picasso;
    }

    @Provides
    @AppScope
    NewsDatabase provideNewsDatabase(NewsApp app) {
        return Room.databaseBuilder(app.getApplicationContext(), NewsDatabase.class, "news.db")
                   .fallbackToDestructiveMigration()
                   .allowMainThreadQueries()
                   .build();
    }

    @Provides
    @AppScope
    NewsDao provideNewsDao(NewsDatabase newsDatabase) {
        return newsDatabase.newsDao();
    }

    @Provides
    @AppScope
    Pref providePref(NewsApp app) {
        return Pref.getInstance(app.getApplicationContext());
    }
}
