package com.acadgild.weightlossmagic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.acadgild.weightlossmagic.R;
import com.acadgild.weightlossmagic.BMICalculator;

/**
 * Created by DivyaVipin on 3/2/2017.
 */

public class BMICalculator extends AppCompatActivity {
   private  Button btnClear;
   private Button btnCalculate;
   private float weight,height;
   private float height_value;
   private float bmi_value;
   private Toolbar toolbar;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private TextView txtViewResult;
    private String str1,str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_calculator);
        init();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextHeight.setText("");
                editTextWeight.setText("");
                txtViewResult.setText("");

            }
        });
        btnCalculate.setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @Override
            public void onClick(View v) {

                 str1 = editTextWeight.getText().toString();
                 str2 = editTextHeight.getText().toString();
                checkEmpty();


//Get the user values from the widget reference
                weight = Float.parseFloat(str1);
                height = Float.parseFloat(str2)/100;

//Calculate BMI value
                float bmiValue = calculateBMI(weight, height);

//Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);
                Intent i=new Intent(BMICalculator.this,BMIResult.class);
                i.putExtra("value",bmiValue);

                startActivity(i);
               // txtViewResult.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));

            }
        });

    }
public void init()
{
    toolbar=(Toolbar)findViewById(R.id.toolbar) ;
    setSupportActionBar(toolbar);
    toolbar.setTitleTextColor(Color.WHITE);
    btnClear=(Button)findViewById(R.id.btnClear);
    btnCalculate=(Button)findViewById(R.id.btnCalculate);
// Get the references to the widgets
     editTextHeight = (EditText) findViewById(R.id.etHeight);
     editTextWeight = (EditText) findViewById(R.id.etWeight);
     txtViewResult = (TextView) findViewById(R.id.tvResult);
}
    public void checkEmpty()
    {
        if(TextUtils.isEmpty(str1)){
            editTextWeight.setError("Please enter your weight");
            editTextWeight.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(str2)){
            editTextHeight.setError("Please enter your height");
            editTextHeight.requestFocus();
            return;
        }
    }
    //Calculate BMI
    private float calculateBMI (float weight, float height) {
        ;
        height_value=height * height;
        bmi_value = weight / height_value;
        return bmi_value;
    }

    // Interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "Severely underweight";
        } else if ((bmiValue >16) &&(bmiValue < 18.5)) {

            return "Underweight";
        } else if ((bmiValue >18.5)&&(bmiValue < 25)) {

            return "Normal";
        } else if ((bmiValue > 25)&&(bmiValue < 30) ){

            return "Overweight";
        } else {
            return "Obese";
        }

    }
}