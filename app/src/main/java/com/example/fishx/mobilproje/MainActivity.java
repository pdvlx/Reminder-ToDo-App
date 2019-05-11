package com.example.fishx.mobilproje;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.TransactionTooLargeException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    private FloatingActionButton addEventButton;
    private Toolbar toolbar1;
    ListView reminderList;
    List<listAdapter> list;
    SQLiteAdapter db = new SQLiteAdapter(context);
    //Part1de buralara bir şeyler ekleniyor unutma.
    ArrayAdapter<String> lAdapter;
    ArrayAdapter<String> iAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List view için aktivitelere tıklandığında onların neler içerdiğini
        //görebilmek için buraya bir clicklistener ekleniyor.

        listele();


        addEventButton = findViewById(R.id.eventAdd);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hatırlatıcı bilgisi girme aktivitesini açıyor.
                Intent intent = new Intent(v.getContext(),add_screen.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        listele();
    }

    public void listele(){
        reminderList = findViewById(R.id.liste);

       // db.contentAdd(new listAdapter("xddddd amllll sadgasdas sadfasdgasdjn sagjsadgkjasd dsags","2019","3","12","15","34","0"));
       // db.contentAdd(new listAdapter("xddddd amllll","2018","3","12","15","34","0"));

        list = db.getContents();


        List<String> listContent = new ArrayList<>();
        for(int i = 0; i <list.size() ; i++){
            listContent.add(i,list.get(i).getToDo());

        }

        lAdapter = new ArrayAdapter<String>(context,R.layout.alarmlar,R.id.baslik,listContent);
        reminderList.setAdapter(lAdapter);
        reminderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,toDoInfoActivity.class);
                intent.putExtra("todo",list.get(position).getId());
                Log.i("id=",String.valueOf(list.get(position).getId()));
                startActivityForResult(intent,1);
            }
        });
    }


}

