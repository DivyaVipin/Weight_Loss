package com.acadgild.weightlossmagic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;

import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by DivyaVipin on 4/1/2017.
 */

public class BMIResult  extends AppCompatActivity{
    PieChart mChart;
    TextView result;
    Float bmiResult;
    int bmiValue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_chart);
        mChart = (PieChart) findViewById(R.id.piechart);
        result=(TextView)findViewById(R.id.txtViewResult) ;
        Intent iin= getIntent();

        Bundle b = iin.getExtras();

        if(b!=null)
        {

            bmiResult =b.getFloat("value");

           // result.setText(i);

        }
addData();

    }
    private void addData() {
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(16f,0));

        yvalues.add(new Entry(18.5f,1));
        yvalues.add(new Entry(25f,2));
        yvalues.add(new Entry(30f,3));
        yvalues.add(new Entry(100f,4));
        PieDataSet dataSet = new PieDataSet(yvalues, "BMI Result");
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Severly Underweight");
        xVals.add("UnderWeight");
        xVals.add("Normal");
        xVals.add("Overweight");
        xVals.add("Obese");
        PieData data = new PieData(xVals, dataSet);

        mChart.setData(data);

        mChart.setDescription("This is Pie Chart");
        mChart.setDrawHoleEnabled(true);
        mChart.setTransparentCircleRadius(25f);
        mChart.setHoleRadius(25f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        mChart.setCenterText("BMI:" + bmiResult);
        mChart.invalidate();

    }

}
