package com.example.calculatorappmandal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView result_TV, solution_TV;
    MaterialButton button_C, button_Open_Brack, button_Close_Brack;
    MaterialButton button_Div, button_Mult, button_Plus, button_Minus, button_Equal;
    MaterialButton button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8
            ,button_9, button_0;
    MaterialButton button_ac, button_dot;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_TV = findViewById(R.id.resultTV);
        solution_TV = findViewById(R.id.solutionTV);

        assignID(button_C, R.id.buttonC);
        assignID(button_Open_Brack, R.id.buttonBracketOpen);
        assignID(button_Close_Brack, R.id.buttonBracketClose);
        assignID(button_Div, R.id.buttonDivide);
        assignID(button_Mult, R.id.buttonMultiply);
        assignID(button_Plus, R.id.buttonPlus);
        assignID(button_Minus, R.id.buttonMinus);
        assignID(button_Equal, R.id.buttonEquals);
        assignID(button_1, R.id.button1);
        assignID(button_2, R.id.button2);
        assignID(button_3, R.id.button3);
        assignID(button_4, R.id.button4);
        assignID(button_5, R.id.button5);
        assignID(button_6, R.id.button6);
        assignID(button_7, R.id.button7);
        assignID(button_8, R.id.button8);
        assignID(button_9, R.id.button9);
        assignID(button_0, R.id.button0);
        assignID(button_ac, R.id.buttonAC);
        assignID(button_C, R.id.buttonC);
        assignID(button_dot, R.id.buttonDot);


    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String calcData = solution_TV.getText().toString();

        if(buttonText.equalsIgnoreCase("AC")){
            solution_TV.setText("");
            result_TV.setText("0");
            return;
        }

        if(buttonText.equalsIgnoreCase("=")){
            solution_TV.setText(result_TV.getText());
            return;
        }
        if (buttonText.equalsIgnoreCase("C")) {
            calcData = calcData.substring(0, calcData.length() - 1);
        }else{
            calcData = calcData+buttonText;
        }


        solution_TV.setText(calcData);
        String finalResult = getResult(calcData);
        if(!finalResult.equalsIgnoreCase("error")){
            result_TV.setText(finalResult);
        }

    }
    // includes imported libraries popularly used for advanced operations
    // such as the open and closed bracket, and order of operations.
    String getResult(String data){
    try{
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable = context.initStandardObjects();
        String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
        if(finalResult.endsWith(".0")){
            finalResult = finalResult.replace(".0", "");
        }
        return finalResult;
    }catch (Exception e){
        return "Error";
    }

    }
}