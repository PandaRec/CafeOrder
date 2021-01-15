package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrder extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewSubs;

    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLemon;
    private CheckBox checkBoxSugar;


    private String name;
    private String password;
    private String drink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if(intent.hasExtra("name") && intent.hasExtra("password")){
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        textViewHello = findViewById(R.id.textViewHello);
        textViewSubs = findViewById(R.id.textViewChooseSubs);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        spinnerTea = findViewById(R.id.spinnerTea);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);

        String hello = String.format(getString(R.string.hello),name);
        textViewHello.setText(hello);

        drink = getString(R.string.tea);
        String subs = String.format(getString(R.string.subs),drink);
        textViewSubs.setText(subs);
    }

    public void onClickDrinkChanged(View view) {
        RadioButton radioButton = (RadioButton) view;
        int id = radioButton.getId();
        if(id == R.id.radioButtonTea){
            drink = getString(R.string.tea);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            spinnerTea.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);

        }else if(id == R.id.radioButtonCoffee){
            drink = getString(R.string.coffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);

        }
        String subs = String.format(getString(R.string.subs),drink);
        textViewSubs.setText(subs);

    }

    public void onClickSendOrder(View view) {
        /*
        имя
        пароль
        вид напитка
        добавки
        тип напитка
         */
        StringBuilder builderSubs = new StringBuilder();
        String typeOfDrink;
        if(checkBoxMilk.isChecked())
            builderSubs.append(getString(R.string.milk));
        if(checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea)))
            builderSubs.append(getString(R.string.lemon));
        if(checkBoxSugar.isChecked())
            builderSubs.append(getString(R.string.sugar));

        if(drink.equals(getString(R.string.tea)))
            typeOfDrink = spinnerTea.getSelectedItem().toString();
        else
            typeOfDrink = spinnerCoffee.getSelectedItem().toString();





        String order = String.format(getString(R.string.order),name,password,drink,builderSubs.toString(),typeOfDrink);
        Intent intent = new Intent(this,OrderDetailsActivity.class);
        intent.putExtra("order",order);
        startActivity(intent);
    }
}