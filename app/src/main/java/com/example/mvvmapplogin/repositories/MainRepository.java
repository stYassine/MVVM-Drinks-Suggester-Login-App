package com.example.mvvmapplogin.repositories;

import com.example.mvvmapplogin.models.LoginBody;
import com.example.mvvmapplogin.models.LoginResponse;
import com.example.mvvmapplogin.services.ILoginService;
import com.example.mvvmapplogin.utils.RetrofitClientInstancee;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    String[] drinksListRemote = {
            "Spiking Coffee", "Sweet Bananas", "Tomato Tang", "Apple Berry Smoothie", "Cold Water"
    };

    public MainRepository() {
    }

    public void loginRemote(LoginBody loginBody, final ILoginResponse loginResponse){
        ILoginService loginService = RetrofitClientInstancee.getRetrofit().create(ILoginService.class);

        Call<LoginResponse> initiateLogin = loginService.login(loginBody);

        initiateLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponse.onResponse(response.body());
                }else{
                    loginResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponse.onFailure(t);
            }
        });

    }

    public void suggestNewDrink(final IDrinkCallback drinkCallback){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /// Before Executing background Thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                /// Background Work here
                try {
                    Thread.sleep(1000); /// mimic Server request / Long Execution

                    int randomIndex = new Random().nextInt(drinksListRemote.length);
                    String drinkName = drinksListRemote[randomIndex];
                    drinkCallback.onDrinkSuggested(drinkName);

                }catch (InterruptedException e){
                    e.printStackTrace();
                    drinkCallback.onErrorOccured();
                }catch (Exception e){
                    e.printStackTrace();
                    drinkCallback.onErrorOccured();
                }
            }
        });
    }

    public interface ILoginResponse{
        void onResponse(LoginResponse loginResponse);
        void onFailure(Throwable t);
    }

    public interface IDrinkCallback{
        void onDrinkSuggested(String drinkName);
        void onErrorOccured();
    }

}
