package com.ralphevmanzano.newspaging.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.ralphevmanzano.newspaging.R;
import com.ralphevmanzano.newspaging.databinding.NewsFragmentBinding;
import com.ralphevmanzano.newspaging.ui.BaseFragment;
import com.ralphevmanzano.newspaging.utils.Pref;
import com.ralphevmanzano.newspaging.viewmodel.NewsViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;

public class NewsFragment extends BaseFragment<NewsViewModel, NewsFragmentBinding> implements OnNewsClickListener {

    private static final String DEFAULT_SEARCH = "Philippines";

    @Inject
    NewsAdapter newsAdapter;

    @Inject
    Pref pref;

    @Override
    public Class<NewsViewModel> getViewModel() {
        return NewsViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.news_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSearch(pref.getSearch().isEmpty() ? DEFAULT_SEARCH : pref.getSearch());
    }

    @Override
    public void onDestroy() {
        pref.setSearch(binding.etSearch.getText().toString().trim());
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setupUi();
        return view;
    }

    private void initSearch(String search) {
        viewModel.searchNews(search);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.news.observe(getViewLifecycleOwner(),
                news -> newsAdapter.submitList(news));
        viewModel.networkErrors.observe(getViewLifecycleOwner(),
                error -> Toast.makeText(getContext(), "\uD83D\uDE28 Wooops " + error, Toast.LENGTH_LONG).show());
    }

    private void setupUi() {
        if (getContext() != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                    layoutManager.getOrientation());

            binding.recyclerView.addItemDecoration(dividerItemDecoration);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerView.setAdapter(newsAdapter);

            binding.etSearch.setText(pref.getSearch().isEmpty() ? DEFAULT_SEARCH : pref.getSearch());
            binding.etSearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    Timber.d("searching news");
                    searchNews();
                    return true;
                } else {
                    return false;
                }
            });

            binding.etSearch.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Timber.d("searching news");
                    searchNews();
                    return true;
                } else {
                    return false;
                }
            });
        }
    }

    private void searchNews() {
        String query = binding.etSearch.getText().toString().trim();
        Timber.d( "searchNews: %s", query);
        if (!query.isEmpty()) {
            binding.recyclerView.scrollToPosition(0);
            viewModel.searchNews(query);
            newsAdapter.submitList(null);
        }
    }

    @Override
    public void onNewsClick(String url) {
        if (!url.startsWith("https://") && !url.startsWith("http://")){
            url = "http://" + url;
        }
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openUrlIntent);
    }
}
