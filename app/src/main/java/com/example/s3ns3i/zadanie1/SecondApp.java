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

    private Double result = 0.0;
//    private Double firstVariable
//            , secondVariable;
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
//        firstVariable = 0.0;
//        secondVariable = 0.0;
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
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
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
        //Double firstVariable = 0.0;
        String number = displayTextView.getText().toString();
        Character operation = operationTextView.getText().charAt(0);
        //result = Double.valueOf(display2TextView.getText().toString());
        switch(v.getId()){
            case R.id.zeroButton:
                displayTextView.setText(number + String.valueOf(0));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.oneButton:
                displayTextView.setText(number + String.valueOf(1));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.twoButton:
                displayTextView.setText(number + String.valueOf(2));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.threeButton:
                displayTextView.setText(number + String.valueOf(3));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.fourButton:
                displayTextView.setText(number + String.valueOf(4));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.fiveButton:
                displayTextView.setText(number + String.valueOf(5));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.sixButton:
                displayTextView.setText(number + String.valueOf(6));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.sevenButton:
                displayTextView.setText(number + String.valueOf(7));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.eightButton:
                displayTextView.setText(number + String.valueOf(8));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.nineButton:
                displayTextView.setText(number + String.valueOf(9));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.dotButton:
                displayTextView.setText(number + String.valueOf('.'));
                //result = Double.valueOf(displayTextView.getText().toString());
                break;
            case R.id.addButton:
                operationButtonAction('+');
                //operationButtonAction('+', '+');
//                if(secondVariable != 0.0 && operation != '\0' && firstVariable != 0.0)
//                    secondVariable = doMath(firstVariable, secondVariable, operation);
//                else
//                    secondVariable = firstVariable;
//                display2TextView.setText(Double.toString(secondVariable));
//                displayTextView.setText("");
//                operationTextView.setText("+");
                break;
            case R.id.substractButton:
                operationButtonAction('-');
                //operationButtonAction('-', '-');
//                secondVariable = firstVariable;
//                display2TextView.setText(Double.toString(secondVariable));
//                displayTextView.setText("");
//                operationTextView.setText("-");
                break;
            case R.id.multiplyButton:
                operationButtonAction('*');
                //operationButtonAction('*', '*');
//                secondVariable = firstVariable;
//                display2TextView.setText(Double.toString(secondVariable));
//                displayTextView.setText("");
//                operationTextView.setText("*");
                break;
            case R.id.divideButton:
                operationButtonAction('/');
                //operationButtonAction('/', '/');
//                secondVariable = firstVariable;
//                display2TextView.setText(Double.toString(secondVariable));
//                displayTextView.setText("");
//                operationTextView.setText("/");
                break;
            case R.id.equalsButton:
                operationButtonAction(operation);
//                Double a = Double.valueOf(display2TextView.getText().toString())
//                        , b = Double.valueOf(displayTextView.getText().toString());
//                Double result;
//                switch(operation){
//                    case '+':
//                        result = doMath(a, b, '+');
////                                Double.valueOf(display2TextView.getText().toString())
////                                        + Double.valueOf(displayTextView.getText().toString());
//                        displayTextView.setText(String.valueOf(result));
//                        break;
//                    case '-':
//                        result = doMath(a, b, '-');
////                                Double.valueOf(display2TextView.getText().toString())
////                                        - Double.valueOf(displayTextView.getText().toString());
//                        displayTextView.setText(String.valueOf(result));
//                        break;
//                    case '/':
//                        result = doMath(a, b, '/');
////                                Double.valueOf(display2TextView.getText().toString())
////                                        / Double.valueOf(displayTextView.getText().toString());
//                        displayTextView.setText(String.valueOf(result));
//                        break;
//                    case '*':
//                        result = doMath(a, b, '*');
////                                Double.valueOf(display2TextView.getText().toString())
////                                        * Double.valueOf(displayTextView.getText().toString());
//                        displayTextView.setText(String.valueOf(result));
//                        break;
//                    default:
//                        //display2TextView.setText(displayTextView.getText().toString());
//                        //displayTextView.setText("");
//                        break;
//                }
//                displayTextView.setText("");
                break;
            case R.id.clearButton:
                displayTextView.setText("");
                break;
            case R.id.clearEverythingButton:
                displayTextView.setText("");
                resultTextView.setText("");
                operationTextView.setText("");
                result = 0.0;
                //secondVariable = 0.0;
                break;
            case R.id.eraseButton:
                if(number.length() > 0)
                    displayTextView.setText(number.substring(0, number.length() - 1));
                break;
        }
    }
    private Double doMath(Double a, Double b, Character operation){
        Double result = Double.valueOf(0.0);
        switch(operation){
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '/':
                try {
                    result = a / b;
                } catch (ArithmeticException e){
                    Toast.makeText(this, "You really tried to divide by zero. Dude. You want to destroy Universe?", Toast.LENGTH_LONG);
                }
                break;
            case '*':
                result = a * b;
                break;
        }
        return result;
    }
    private void operationButtonAction(Character operation){
    //private void operationButtonAction(Character operation, Character nextOperation){
        Double a, b;
        if(displayTextView.getText().toString().isEmpty())
            a = 0.0;
        else
            a = Double.valueOf(displayTextView.getText().toString());
        if(resultTextView.getText().toString().isEmpty())
            b = 0.0;
        else
            b = Double.valueOf(resultTextView.getText().toString());
        if(displayTextView.getText().toString().isEmpty()){}
        else if(!displayTextView.getText().toString().isEmpty() && resultTextView.getText().toString().isEmpty()){
            result = b;
            resultTextView.setText(Double.toString(a));
            displayTextView.setText("");
            operationTextView.setText(String.valueOf(operation));
        }
        else if(!displayTextView.getText().toString().isEmpty()){
            result = doMath(a, b, operation);
            resultTextView.setText(Double.toString(result));
            displayTextView.setText("");
            operationTextView.setText(String.valueOf(operation));
        }

//        if(display2TextView.getText().charAt(0) != '\0' && operation != '\0')
//            result = doMath(a, b, operation);
//        //else if (a != 0.0)
//
//        display2TextView.setText(Double.toString(result));
//        displayTextView.setText("");
//        operationTextView.setText(String.valueOf(nextOperation));
//        if(secondVariable != 0.0 && operation != '\0' && operationTextView.getText().charAt(0) != '\0')
//            secondVariable = doMath(secondVariable, result, operation);
//        else if (result != 0.0)
//            secondVariable = result;
//        else
//            return;
//        display2TextView.setText(Double.toString(secondVariable));
//        displayTextView.setText("");
//        operationTextView.setText(String.valueOf(nextOperation));
        return;
    }
}
