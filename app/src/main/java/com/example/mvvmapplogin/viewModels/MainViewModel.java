package com.example.mvvmapplogin.viewModels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmapplogin.repositories.MainRepository;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    public MutableLiveData<String> mDrinksMutableData = new MutableLiveData<>();

    public MainRepository mainRepository;

    public MainViewModel() {
        mProgressMutableData.postValue(View.INVISIBLE);
        mDrinksMutableData.postValue("");
        mainRepository = new MainRepository();
    }

    public void suggestNewDrink(){
        mProgressMutableData.postValue(View.VISIBLE);

        mainRepository.suggestNewDrink(new MainRepository.IDrinkCallback() {
            @Override
            public void onDrinkSuggested(String drinkName) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mDrinksMutableData.postValue(drinkName);
            }

            @Override
            public void onErrorOccured() {

            }
        });
    }

    public MutableLiveData<Integer> getmProgressMutableData() {
        return mProgressMutableData;
    }

    public MutableLiveData<String> getmDrinksMutableData() {
        return mDrinksMutableData;
    }
}
