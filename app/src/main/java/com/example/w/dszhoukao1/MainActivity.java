package com.example.w.dszhoukao1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.net.DatagramSocket;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Toast scratchTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] name={"快乐","好看","五彩缤纷"};
        scratchTv.setText(name[getRandom()]);

    }

    public int getRandom() {
        Random random = new Random();
        int i = random.nextInt();
        return i;
    }

}
