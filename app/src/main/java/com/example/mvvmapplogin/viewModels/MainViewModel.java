package com.example.mvvmapplogin.viewModels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmapplogin.models.LoginBody;
import com.example.mvvmapplogin.models.LoginResponse;
import com.example.mvvmapplogin.repositories.MainRepository;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    public MutableLiveData<String> mDrinksMutableData = new MutableLiveData<>();
    public MutableLiveData<String> mLoginResultMutableData = new MutableLiveData<>();

    public MainRepository mainRepository;

    public MainViewModel() {
        mProgressMutableData.postValue(View.INVISIBLE);
        mDrinksMutableData.postValue("");
        mainRepository = new MainRepository();
    }

    public void login(String email, String password){
        mProgressMutableData.postValue(View.VISIBLE);
        mLoginResultMutableData.postValue("Checking");
        mainRepository.loginRemote(new LoginBody(email, password), new MainRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login Success");
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login Failure : "+ t.getLocalizedMessage());
            }
        });
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
                mProgressMutableData.postValue(View.INVISIBLE);
                // Show Toast with Error
            }
        });
    }

    public MutableLiveData<String> getmLoginResultMutableData() {
        return mLoginResultMutableData;
    }

    public MutableLiveData<Integer> getmProgressMutableData() {
        return mProgressMutableData;
    }

    public MutableLiveData<String> getmDrinksMutableData() {
        return mDrinksMutableData;
    }
}
