package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SecondApp extends Activity implements View.OnClickListener {

    private TextView displayTextView
            , resultTextView
            , operationTextView;
    private Button zeroButton
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
    , subtractButton
    , multiplyButton
    , divideButton
    , clearEverythingButton
    , clearButton
    , eraseButton
    , equalsButton
    , dotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_app);
        displayTextView = (TextView) findViewById(R.id.displayTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        operationTextView = (TextView) findViewById(R.id.operationTextView);
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
        subtractButton = (Button) findViewById(R.id.substractButton);
        subtractButton.setOnClickListener(this);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_app, menu);
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
        String number = displayTextView.getText().toString();
        switch(v.getId()){
            case R.id.zeroButton:
                displayTextView.setText(number + String.valueOf(0));
                break;
            case R.id.oneButton:
                displayTextView.setText(number + String.valueOf(1));
                break;
            case R.id.twoButton:
                displayTextView.setText(number + String.valueOf(2));
                break;
            case R.id.threeButton:
                displayTextView.setText(number + String.valueOf(3));
                break;
            case R.id.fourButton:
                displayTextView.setText(number + String.valueOf(4));
                break;
            case R.id.fiveButton:
                displayTextView.setText(number + String.valueOf(5));
                break;
            case R.id.sixButton:
                displayTextView.setText(number + String.valueOf(6));
                break;
            case R.id.sevenButton:
                displayTextView.setText(number + String.valueOf(7));
                break;
            case R.id.eightButton:
                displayTextView.setText(number + String.valueOf(8));
                break;
            case R.id.nineButton:
                displayTextView.setText(number + String.valueOf(9));
                break;
            case R.id.dotButton:
                if(!displayTextView.getText().toString().contains("."))
                    displayTextView.setText(number + String.valueOf('.'));
                break;
            case R.id.addButton:
                operationButtonAction('+');
                break;
            case R.id.substractButton:
                operationButtonAction('-');
                break;
            case R.id.multiplyButton:
                operationButtonAction('*');
                break;
            case R.id.divideButton:
                operationButtonAction('/');
                break;
            case R.id.equalsButton:
                operationButtonAction('e');
                break;
            case R.id.clearButton:
                displayTextView.setText("");
                break;
            case R.id.clearEverythingButton:
                displayTextView.setText("");
                resultTextView.setText("");
                operationTextView.setText("");
                break;
            case R.id.eraseButton:
                if(number.length() > 0)
                    displayTextView.setText(number.substring(0, number.length() - 1));
                break;
        }
    }
    private Double doMath(Double a, Double b, Character operation){
        Double result = 0.0;
        switch(operation){
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '/':
                //We divide by zero
                if (b == 0.0)
                    Toast.makeText(this, "You really tried to divide by zero. Dude. You want to destroy the Universe?", Toast.LENGTH_LONG).show();
                else
                result = a / b;
                break;
            case '*':
                result = a * b;
                break;
            default:
                resultTextView.setText(displayTextView.getText());
                displayTextView.setText("test, mate");
                break;
        }
        return result;
    }
    private void operationButtonAction(Character nextOperation){
        Double a, b, c;
        String display = displayTextView.getText().toString()
                , result = resultTextView.getText().toString()
                , currentOperation = operationTextView.getText().toString();

        //Setting values of the variables
        if(resultTextView.getText().toString().isEmpty())
            a = 0.0;
        else
            a = Double.valueOf(resultTextView.getText().toString());
        if(displayTextView.getText().toString().isEmpty())
            b = 0.0;
        else
            b = Double.valueOf(displayTextView.getText().toString());

        //Three states:
        //Empty
        //Display
        //Display and Result
        if(!display.isEmpty() && !result.isEmpty()){
            c = doMath(a, b, currentOperation.charAt(0));
            resultTextView.setText(String.valueOf(c));
            displayTextView.setText("");
            if (nextOperation == 'e')
                operationTextView.setText("");
            else
                operationTextView.setText(nextOperation.toString());
        }
        else if(!display.isEmpty()){
            resultTextView.setText(displayTextView.getText());
            displayTextView.setText("");
            operationTextView.setText(nextOperation.toString());
        }
        else if(!result.isEmpty()){
            operationTextView.setText(nextOperation.toString());
        }
    }
}
