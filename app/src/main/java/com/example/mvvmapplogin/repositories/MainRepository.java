package com.example.mvvmapplogin.repositories;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository {

    String[] drinksListRemote = {
            "Spiking Coffee", "Sweet Bananas", "Tomato Tang", "Apple Berry Smoothie", "Cold Water"
    };

    public MainRepository() {
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

    public interface IDrinkCallback{
        void onDrinkSuggested(String drinkName);
        void onErrorOccured();
    }

}
