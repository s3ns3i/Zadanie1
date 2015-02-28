package com.example.s3ns3i.zadanie1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
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


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Double firstVariable
            , secondVariable;
    private Button button
            , button2
            , zeroButton
            , oneButton
            , twoButton
            , threeButton
            , fourButton
            , fiveButton
            , sixButton
            , sevenButton
            , eightButton
            , nineButton
            , addButton
            , substractButton
            , multiplyButton
            , divideButton
            , clearEverythingButton
            , clearButton
            , eraseButton
            , equalsButton
            , dotButton;
    private TextView textView
            , displayTextView
            , display2TextView
            , operationTextView;
    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstVariable = 0.0;
        secondVariable = 0.0;
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(this);
        oneButton = (Button) findViewById(R.id.oneButton);
        oneButton.setOnClickListener(this);
        twoButton = (Button) findViewById(R.id.twoButton);
        twoButton.setOnClickListener(this);
        threeButton = (Button) findViewById(R.id.threeButton);
        threeButton.setOnClickListener(this);
        fourButton = (Button) findViewById(R.id.fourButton);
        fourButton.setOnClickListener(this);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(this);
        sixButton = (Button) findViewById(R.id.sixButton);
        sixButton.setOnClickListener(this);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        sevenButton.setOnClickListener(this);
        eightButton = (Button) findViewById(R.id.eightButton);
        eightButton.setOnClickListener(this);
        nineButton = (Button) findViewById(R.id.nineButton);
        nineButton.setOnClickListener(this);
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        substractButton = (Button) findViewById(R.id.substractButton);
        substractButton.setOnClickListener(this);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(this);
        divideButton = (Button) findViewById(R.id.divideButton);
        divideButton.setOnClickListener(this);
        clearEverythingButton = (Button) findViewById(R.id.clearEverythingButton);
        clearEverythingButton.setOnClickListener(this);
        clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);
        eraseButton = (Button) findViewById(R.id.eraseButton);
        eraseButton.setOnClickListener(this);
        equalsButton = (Button) findViewById(R.id.equalsButton);
        equalsButton.setOnClickListener(this);
        dotButton = (Button) findViewById(R.id.dotButton);
        dotButton.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);
        displayTextView = (TextView) findViewById(R.id.displayTextView);
        display2TextView = (TextView) findViewById(R.id.display2TextView);
        operationTextView = (TextView) findViewById(R.id.operationTextView);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        button.setClickable(false);
        button.setTextColor(ColorStateList.valueOf(Color.GRAY));

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setClickable(true);
                button.setTextColor(ColorStateList.valueOf(Color.BLACK));
                if(editText.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
                    button.setClickable(false);
                    button.setTextColor(ColorStateList.valueOf(Color.GRAY));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = editText.getText().toString();
                try{
                    Integer.parseInt(text1);
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
                button.setClickable(true);
                button.setTextColor(ColorStateList.valueOf(Color.BLACK));
                if(editText.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
                    button.setClickable(false);
                    button.setTextColor(ColorStateList.valueOf(Color.GRAY));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text2 = editText2.getText().toString();
                try{
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View view) {
        String number = displayTextView.getText().toString();
        switch(view.getId()){
            case R.id.zeroButton:
                displayTextView.setText(number + String.valueOf(0));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.oneButton:
                displayTextView.setText(number + String.valueOf(1));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.twoButton:
                displayTextView.setText(number + String.valueOf(2));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.threeButton:
                displayTextView.setText(number + String.valueOf(3));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.fourButton:
                displayTextView.setText(number + String.valueOf(4));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.fiveButton:
                displayTextView.setText(number + String.valueOf(5));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.sixButton:
                displayTextView.setText(number + String.valueOf(6));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.sevenButton:
                displayTextView.setText(number + String.valueOf(7));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.eightButton:
                displayTextView.setText(number + String.valueOf(8));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.nineButton:
                displayTextView.setText(number + String.valueOf(9));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.dotButton:
                displayTextView.setText(number + String.valueOf('.'));
                firstVariable = Double.valueOf(displayTextView.getText().toString());
                break;
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
            case R.id.addButton:
                secondVariable = firstVariable;
                display2TextView.setText(Double.toString(secondVariable));
                displayTextView.setText("");
                operationTextView.setText("+");
                break;
            case R.id.substractButton:
                secondVariable = firstVariable;
                display2TextView.setText(Double.toString(secondVariable));
                displayTextView.setText("");
                operationTextView.setText("-");
                break;
            case R.id.multiplyButton:
                secondVariable = firstVariable;
                display2TextView.setText(Double.toString(secondVariable));
                displayTextView.setText("");
                operationTextView.setText("*");
                break;
            case R.id.divideButton:
                secondVariable = firstVariable;
                display2TextView.setText(Double.toString(secondVariable));
                displayTextView.setText("");
                operationTextView.setText("/");
                break;
            case R.id.equalsButton:
                Double result;
                switch(operationTextView.getText().toString().charAt(0)){
                case '+':
                    result =
                            Double.valueOf(display2TextView.getText().toString())
                            + Double.valueOf(displayTextView.getText().toString());
                    displayTextView.setText(String.valueOf(result));
                    break;
                case '-':
                    result =
                            Double.valueOf(display2TextView.getText().toString())
                                    - Double.valueOf(displayTextView.getText().toString());
                    displayTextView.setText(String.valueOf(result));
                    break;
                case '/':
                    result =
                            Double.valueOf(display2TextView.getText().toString())
                                    / Double.valueOf(displayTextView.getText().toString());
                    displayTextView.setText(String.valueOf(result));
                    break;
                case '*':
                    result =
                            Double.valueOf(display2TextView.getText().toString())
                                    * Double.valueOf(displayTextView.getText().toString());
                    displayTextView.setText(String.valueOf(result));
                    break;
                }
                break;
            case R.id.clearButton:
                displayTextView.setText("");
                break;
            case R.id.clearEverythingButton:
                displayTextView.setText("");
                display2TextView.setText("");
                operationTextView.setText("");
                firstVariable = 0.0;
                secondVariable = 0.0;
                break;
            case R.id.eraseButton:
                if(number.length() < 0)
                    displayTextView.setText(number.substring(0, number.length() - 1));
                break;
        }
    }
}
