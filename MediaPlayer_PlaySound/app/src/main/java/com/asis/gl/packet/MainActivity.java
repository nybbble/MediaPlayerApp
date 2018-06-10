package com.asis.gl.week44;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private RadioButton s1,s2,s3;
    private Button ply;
    private TextView txt;
    private MediaPlayer mp;
    private EditText word;
    private HashMap<String, String> dict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s1 = (RadioButton) findViewById(R.id.s1);
        s2 = (RadioButton) findViewById(R.id.s2);
        s3 = (RadioButton) findViewById(R.id.s3);
        ply = (Button) findViewById(R.id.btnPlay);
        txt = (TextView)findViewById(R.id.fileTxt);
        word = (EditText) findViewById(R.id.word);
        mp = MediaPlayer.create(this,R.raw.startvehicle);

        dict = new HashMap<>();
        fileRead();
    }

    public void playSound(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.s1:
                mp = null;
                mp = MediaPlayer.create(this, R.raw.startvehicle);
                s1.setChecked(true);
                break;
            case R.id.s2:
                mp = null;
                mp = MediaPlayer.create(this, R.raw.h1);
                s2.setChecked(true);
                break;
            case R.id.s3:
                break;
            case R.id.btnPlay:
                mp.start();
                break;
            default:
                String w = word.getText().toString();
                String res = dict.get(w);
                if(res != null){
                    txt.setText(res);
                }
                else {
                    txt.setText("Not found!!!");
                }

        }

    }

    private void fileRead() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.dict));
        while(scan.hasNext()){
            String line = scan.nextLine();
            String[] pieces =line.split("\t") ;
            dict.put(pieces[0], pieces[1]);
        }
    }
}
