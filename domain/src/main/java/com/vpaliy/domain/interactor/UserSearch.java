package com.vpaliy.domain.interactor;

import android.text.TextUtils;
import com.vpaliy.domain.executor.BaseSchedulerProvider;
import com.vpaliy.domain.model.User;
import com.vpaliy.domain.repository.SearchRepository;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class UserSearch extends SingleUseCase<List<User>,String>{

    private SearchRepository repository;

    @Inject
    public UserSearch(BaseSchedulerProvider schedulerProvider,
                          SearchRepository searchRepository){
        super(schedulerProvider);
        this.repository=searchRepository;
    }

    @Override
    public Single<List<User>> buildUseCase(String query) {
        if(query==null || TextUtils.isEmpty(query)) {
            return Single.error(new IllegalArgumentException("Query is null"));
        }
        return repository.searchUser(query);
    }
}