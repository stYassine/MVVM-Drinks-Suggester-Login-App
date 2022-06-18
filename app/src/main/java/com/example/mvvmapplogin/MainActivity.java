package com.example.mvvmapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mvvmapplogin.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView tvDrinkName;
    private ProgressBar progressBar;
    private Button bGetDrink;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDrinkName = (TextView) findViewById(R.id.tvDrinkName);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        bGetDrink = (Button) findViewById(R.id.bGetDrink);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getmProgressMutableData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });
        mainViewModel.getmDrinksMutableData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String drink) {
                tvDrinkName.setText(drink);
            }
        });


        bGetDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.suggestNewDrink();
            }
        });
    }

    /// Before MVVM
//    private void suggestNewDrink(){
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        /// Before Executing background Thread
//        progressBar.setVisibility(View.VISIBLE);
//
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                /// Background Work here
//                try {
//                    Thread.sleep(1000); /// mimic Server request / Long Execution
//
//                    int randomIndex = new Random().nextInt(drinksListRemote.length);
//                    final String drinkName = drinksListRemote[randomIndex];
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            tvDrinkName.setText(drinkName);
//                            progressBar.setVisibility(View.INVISIBLE);
//                        }
//                    });
//
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//    }


}
