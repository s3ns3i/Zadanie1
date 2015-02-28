package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FirstApp extends Activity implements View.OnClickListener {

    private Button button
            , button2;
    private TextView textView;
    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_app);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setClickable(false);
        button.setTextColor(ColorStateList.valueOf(Color.GRAY));
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = editText.getText().toString();
                String text2 = editText2.getText().toString();
                button.setClickable(true);
                button.setTextColor(ColorStateList.valueOf(Color.BLACK));
                try{
                    Integer.parseInt(text1);
                    Integer.parseInt(text2);
                } catch(NumberFormatException e){
                    button.setClickable(false);
                    button.setTextColor(ColorStateList.valueOf(Color.GRAY));
                }
            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = editText.getText().toString();
                String text2 = editText2.getText().toString();
                button.setClickable(true);
                button.setTextColor(ColorStateList.valueOf(Color.BLACK));
                try{
                    Integer.parseInt(text1);
                    Integer.parseInt(text2);
                } catch(NumberFormatException e){
                    button.setClickable(false);
                    button.setTextColor(ColorStateList.valueOf(Color.GRAY));
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                if(editText.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int number1 = Integer.parseInt(editText.getText().toString());
                int number2 = Integer.parseInt(editText2.getText().toString());
                textView.setText(String.valueOf(number1 + number2));
                break;
            case R.id.button2:
                String text1 = editText.getText().toString();
                String text2 = editText2.getText().toString();
                if(editText.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setText(text1 + text2);
                break;
        }
    }
}
