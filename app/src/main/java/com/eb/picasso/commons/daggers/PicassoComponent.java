package com.eb.picasso.commons.daggers;

import com.eb.picasso.screens.search.Search;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface PicassoComponent {
    void inject(Search clazz);
}
