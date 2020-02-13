package com.eb.picasso.screens.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.eb.movieapp.adapter.SearchAdapter;
import com.eb.movieapp.adapter.SliderPicAdapter;
import com.eb.picasso.R;
import com.eb.picasso.commons.daggers.ApiInterface;
import com.eb.picasso.commons.models.Picture;
import com.eb.picasso.commons.models.PictureDao;
import com.eb.picasso.commons.utils.Application;

import com.eb.picasso.databinding.ActivitySearchBinding;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.eb.picasso.commons.utils.ConstantsKt.API_KEY;

public class Search extends AppCompatActivity {

    @Inject
    ApiInterface apiInterface;
    ActivitySearchBinding binding;

    private Disposable _disposable,searchDisposal;
    SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_search);
        Application.Companion.getPicassoComponent().inject(this);
        adapter=new SearchAdapter(new ArrayList<>());
        binding.rv.setAdapter(adapter);

        _disposable =
                RxTextView.textChangeEvents(binding.searchEt)
                        .debounce(600, TimeUnit.MILLISECONDS) // default Scheduler is Computation
                        .filter(changes -> changes!=null && changes.toString().length()>0)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(_getSearchObserver());
    }


    private DisposableObserver<TextViewTextChangeEvent> _getSearchObserver() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onComplete() { }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                searchFor(onTextChangeEvent.text().toString());
            }
        };
    }

    private void searchFor(String keyword) {

        Log.e("search for ",keyword);
        //to cancel previous fired api
        if(searchDisposal!=null)searchDisposal.dispose();

        searchDisposal=
        apiInterface.searchPics(keyword,API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                        new DisposableObserver<PictureDao>() {
                            @Override
                            public void onNext(PictureDao pictureDao) {
                                adapter.refresh(pictureDao.getHits());
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.e("search","complete");
                            }
                        }
                );

    }

    @Override
    protected void onDestroy() {
        _disposable.dispose();
        if(searchDisposal!=null)searchDisposal.dispose();
        super.onDestroy();
    }


}
