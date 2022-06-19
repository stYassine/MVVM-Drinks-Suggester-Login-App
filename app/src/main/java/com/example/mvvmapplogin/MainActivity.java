package com.example.mvvmapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mvvmapplogin.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView tvDrinkName, tvLoginResult;
    private EditText etEmail, etPassword;
    private ProgressBar progressBar;
    private Button bGetDrink, bLogin;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvDrinkName = (TextView) findViewById(R.id.tvDrinkName);
        tvLoginResult = (TextView) findViewById(R.id.tvLoginResult);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        bGetDrink = (Button) findViewById(R.id.bGetDrink);
        bLogin = (Button) findViewById(R.id.bLogin);

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
//                tvDrinkName.setText(drink);
            }
        });

        mainViewModel.getmLoginResultMutableData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvLoginResult.setText(s);
            }
        });

//        bGetDrink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mainViewModel.suggestNewDrink();
//            }
//        });

        /// Login
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                mainViewModel.login(email, password);
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
