package com.example.test8;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    EditText genreView, titleView, contentView, searchView;
    TextView resultGenreView, resultContentView;
    Button btn1, saveBtn, searchBtn;
    private int selectedItemIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genreView = findViewById(R.id.janre);
        titleView = findViewById(R.id.title);
        contentView = findViewById(R.id.content);
        btn1 = findViewById(R.id.btn1);
        saveBtn = findViewById(R.id.save_btn);
        searchView = findViewById(R.id.search);
        resultGenreView = findViewById(R.id.result_janre);
        resultContentView = findViewById(R.id.result_content);
        searchBtn = findViewById(R.id.btn2);

        TabHost host = findViewById(R.id.host);
        host.setup();
        TabHost.TabSpec spec;
        spec = host.newTabSpec("tab1");
        spec.setIndicator("입력");
        spec.setContent(R.id.tab1);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator("조회");
        spec.setContent(R.id.tab2);
        host.addTab(spec);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("노래 장르 선택");
                builder.setSingleChoiceItems(R.array.genre, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItemIndex = which;
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] arr = getResources().getStringArray(R.array.genre);
                        genreView.setText(arr[selectedItemIndex]);
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper = new DBHelper(MainActivity.this);
                helper.addStudent(genreView.getText().toString(), titleView.getText().toString(), contentView.getText().toString());

                Toast.makeText(MainActivity.this, "저장 완료되었습니다", Toast.LENGTH_SHORT).show();
                genreView.setText("");
                titleView.setText("");
                contentView.setText("");
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper = new DBHelper(MainActivity.this);
                Cursor c = helper.getStudent(searchView.getText().toString());

                if (c != null)
                    c.moveToFirst();

                try {
                    resultGenreView.setText(c.getString(1));
                    resultContentView.setText(c.getString(3));
                } catch (Exception e){
                    resultGenreView.setText("");
                    resultContentView.setText("");
                    Toast.makeText(MainActivity.this, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
