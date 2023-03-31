package com.example.localetextstarter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.LocaleListCompat;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.os.LocaleList;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menginisiasi tombol fab dan mengatur fungsi onClick nya
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp();
            }
        });

        Date myDate = new Date();
        long expirationDate = myDate.getTime() +
                TimeUnit.DAYS.toMillis(3);
        myDate.setTime(expirationDate);

        String formatDate = DateFormat.getDateInstance().format(myDate);
        TextView expiredTextView = findViewById(R.id.date);
        expiredTextView.setText(formatDate);

        String codeLanguage = String.valueOf(LocaleListCompat.getAdjustedDefault());
        TextView code = findViewById(R.id.code);
        code.setText(codeLanguage);

        Button convert = findViewById(R.id.convert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.price_item);
                double priceItem = Double.parseDouble(input.getText().toString());
                TextView priceitemTextView = findViewById(R.id.price);
                TextView price100 = findViewById(R.id.price100);
                if (codeLanguage.equals("[en_US]")) {
                    double price = priceItem / 15024.60;
                    double price100pax = price * 100;
                    price100.setText(NumberFormat.getCurrencyInstance(Locale.US).format(price100pax));
                    priceitemTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(price));
                }
                else if(codeLanguage.equals("[ko_KR]")){
                    double price = priceItem / 11.58;
                    double price100pax = price * 100;
                    price100.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(price100pax));
                    priceitemTextView.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(price));
                }
                else if(codeLanguage.equals("[ja_JP]")){
                    double price = priceItem / 113.29;
                    double price100pax = price * 100;
                    price100.setText(NumberFormat.getCurrencyInstance(Locale.JAPAN).format(price100pax));
                    priceitemTextView.setText(NumberFormat.getCurrencyInstance(Locale.JAPAN).format(price));
                }
                else if (codeLanguage.equals("[de_DE]")){
                    double price = priceItem / 8470.21;
                    double price100pax = price * 100;
                    price100.setText(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(price100pax));
                    priceitemTextView.setText(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(price));
                }
                else if (codeLanguage.equals("[ar_EG]")){
                    Locale localeAR = new Locale("ar", "EG");
                    double price = priceItem / 4006.90;
                    double price100pax = price * 100;
                    price100.setText(NumberFormat.getCurrencyInstance(localeAR).format(price100pax));
                    priceitemTextView.setText(NumberFormat.getCurrencyInstance(localeAR).format(price));
                }
                else {
                    Locale localeID = new Locale("in", "ID");
                    double price100pax = priceItem * 100;
                    price100.setText(NumberFormat.getCurrencyInstance(localeID).format(price100pax));
                    priceitemTextView.setText(NumberFormat.getCurrencyInstance(localeID).format(priceItem));
                }
            }
        });


    }

    /**
     * Shows the Help screen.
     */
    private void showHelp() {
        // Create the intent.
        Intent helpIntent = new Intent(this, HelpActivity.class);
        // Start the HelpActivity.
        startActivity(helpIntent);
    }

    /**
     * Creates the options menu and returns true.
     *
     * @param menu       Options menu
     * @return boolean   True after creating options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles options menu item clicks.
     *
     * @param item      Menu item
     * @return boolean  True if menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle options menu item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_change_language_indo){
            Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(languageIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}