package com.example.nilesh.calculator;

import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private String display="";
    private String currentoperator="";
    private boolean hasresult=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen=(TextView)findViewById(R.id.textView);
        screen.setText(display);
    }
    protected void updatescreen(){
        screen.setText(display);
    }

    public void OnClickNumber(View v){
        if (hasresult){
            clear();
            updatescreen();

        }
        Button b =(Button)v;
        display +=b.getText();
        updatescreen();
    }
    public void OnClickoperator(View v){
        Button b =(Button)v;
        display +=b.getText();
        currentoperator=b.getText().toString();
        updatescreen();
    }

    private void clear(){
        display="";
        currentoperator="";
        hasresult=false;
    }

    public void OnClickClear(View v){
        clear();
        updatescreen();

    }

    private  double operate(String a, String b , String op){
        hasresult=true;
        switch (op){
            case "+":return Double.valueOf(a) + Double.valueOf(b);
            case "-":return Double.valueOf(a) - Double.valueOf(b);
            case "x":return Double.valueOf(a)* Double.valueOf(b);
            case "/": try
            {return Double.valueOf(a)/Double.valueOf(b);}
            catch (Exception e){
                Log.d("Calc",e.getMessage());
                hasresult=false;
            }
                default:return -1 ;

        }
    }
    public void OnClickEqual(View v){
        String[] operation=display.split(Pattern.quote(currentoperator));
        if (operation.length < 2)return;

        Double result=operate(operation[0],operation[1],currentoperator);
        screen.setText(display + "\n" + String.valueOf(result) );

    }


}
