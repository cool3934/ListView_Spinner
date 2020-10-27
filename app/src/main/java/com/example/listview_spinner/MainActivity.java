package com.example.listview_spinner;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayList<Double> currencyExchanges;
    Spinner spinner1, spinner2;
    EditText editText;
    TextView textView;

    int state1 = 0;
    int state2 = 10;
    Double amount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText1);
        textView = findViewById(R.id.empty);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        items = new ArrayList<String>();
        currencyExchanges = new ArrayList<Double>();

        items.add("VND"); currencyExchanges.add(1.0);
        items.add("USD"); currencyExchanges.add(23213.28);
        items.add("GBP"); currencyExchanges.add(30384.83);
        items.add("CNY"); currencyExchanges.add(3462.26);
        items.add("RUB"); currencyExchanges.add(301.242);
        items.add("KRW"); currencyExchanges.add(20.5692);
        items.add("CAD"); currencyExchanges.add(17653.05);
        items.add("JPY"); currencyExchanges.add(222.246);
        items.add("EUR"); currencyExchanges.add(27447.20);
        items.add("AUD"); currencyExchanges.add(16577.92);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable + "";
                if(str.equals("")) {
                    amount = null;
                    textView.setText("");
                } else {
                    amount = Double.parseDouble(editable + "");
                    textView.setText("" + convert(amount));
                }
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                state1 = i;
                if(amount != null)
                    textView.setText(convert(amount) + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                state2 = i;
                if(amount != null)
                    textView.setText(convert(amount) + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public Double convert(Double value) {
        return (Math.round(value * currencyExchanges.get(state1)/currencyExchanges.get(state2) * 1000))/1000d;
    }
}
