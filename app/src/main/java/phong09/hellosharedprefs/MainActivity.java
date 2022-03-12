package phong09.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private TextView textCount;
    private String colorCheck = "";

    private static final String Count_number = "Count_number";
    private static final String BLACK = "BLACK";
    private static final String RED = "RED";
    private static final String BLUE = "BLUE";
    private static final String GREEN = "GREEN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences(Count_number, MODE_PRIVATE);

        textCount = findViewById(R.id.txtCount);

        TextView txtBlack = findViewById(R.id.txtBlack);
        TextView txtRed = findViewById(R.id.txtRed);
        TextView txtBlue = findViewById(R.id.txtBlue);
        TextView txtGreen = findViewById(R.id.txtGreen);
        TextView btnCount = findViewById(R.id.btnCount);
        TextView btnReset = findViewById(R.id.btnReset);


        txtBlack.setOnClickListener(view -> pickColor(BLACK));
        txtRed.setOnClickListener(view -> pickColor(RED));
        txtBlue.setOnClickListener(view -> pickColor(BLUE));
        txtGreen.setOnClickListener(view -> pickColor(GREEN));
        btnCount.setOnClickListener(view -> countNumber());
        btnReset.setOnClickListener(view -> clearNumber());
    }


    private void pickColor(String COLOR_KEY) {
        colorCheck = COLOR_KEY;
        int numberInSharedPref = sharedPref.getInt(COLOR_KEY, 0);
        textCount.setTextColor(Color.parseColor(colorCheck));
        textCount.setText(String.valueOf(numberInSharedPref));
    }
    private void clearNumber() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        colorCheck = "";
        textCount.setText("0");
    }
    private void countNumber() {
        if (colorCheck.isEmpty()) {
            return;
        }
        int increase = Integer.parseInt(textCount.getText().toString()) + 1;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(colorCheck, increase);
        editor.apply();
        textCount.setText(String.valueOf(increase));
    }


}