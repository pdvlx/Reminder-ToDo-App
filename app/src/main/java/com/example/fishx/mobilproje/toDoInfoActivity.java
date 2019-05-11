package com.example.fishx.mobilproje;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class toDoInfoActivity extends AppCompatActivity {
    Context context = this;
    SQLiteAdapter db = new SQLiteAdapter(context);
    //toolbar sonra eklenecek 24:22 part1
    private EditText reminderText;
    private TextView dateText, timeText, alarmText, repeatNumberText, repeatTypeText;
    private FloatingActionButton yildizli1, yildizli2;
    listAdapter selectedToDo;
    private long repeatTime;
    private Switch repeated;
    boolean yeni;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_info);
        reminderText = (EditText) findViewById(R.id.reminder);
        dateText =(TextView) findViewById(R.id.dateTxt);
        timeText = (TextView) findViewById(R.id.timeTxt);
        intent = getIntent();
        int id = intent.getIntExtra("todo",1);


        Log.i("gelenid", String.valueOf(id));
        selectedToDo = db.contentRead(id);
        reminderText.setText(selectedToDo.getToDo());
        if(selectedToDo.getGun()!=null)
            dateText.setText(selectedToDo.getGun() + "/" + selectedToDo.getAy() +"/"+ selectedToDo.getYil());
        if(selectedToDo.getSaat()!=null)
            timeText.setText(selectedToDo.getSaat() + ":" + selectedToDo.getDk()+"'e ayarlandı.");

    }

    public void btnSil(View v){

        new AlertDialog.Builder(this)
                .setMessage("Silmek istediğinize emin misiniz?")
                .setCancelable(false)
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deletetoDo(selectedToDo);
                        toDoInfoActivity.this.finish();
                    }
                })
                .setNegativeButton("İptal", null)
                .show();
    }
    public void btnUpdate(View v){


        String dayInfoA = dateText.getText().toString();
        Log.i("xd",dayInfoA);
        String timeInfoA = timeText.getText().toString();
        String[] dayInfo = dayInfoA.split("/");
        String[] timeInfo = timeInfoA.split(":");

        selectedToDo.setToDo(reminderText.getText().toString());
        if(!dayInfoA.equals("Tarih")){
            selectedToDo.setYil(dayInfo[2]);
            selectedToDo.setAy(dayInfo[1]);
            selectedToDo.setGun(dayInfo[0]);
        }
        if(!timeInfoA.equals("Saat")){
            selectedToDo.setSaat(timeInfo[0]);
            selectedToDo.setDk(timeInfo[1]);
        }
        db.updateContent(selectedToDo);
        finish();
    }



    public void saatSec(View v) {
        Calendar mcurrentTime = Calendar.getInstance();//
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
        int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
        TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk


        timePicker = new TimePickerDialog(toDoInfoActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(selectedHour <10)
                    timeText.setText( "0"+selectedHour + ":" + selectedMinute);
                else
                    timeText.setText( selectedHour + ":" + selectedMinute);
                if(selectedMinute <10)
                    timeText.setText( selectedHour + ":" +"0"+ selectedMinute);
                else
                    timeText.setText( selectedHour + ":" +selectedMinute);

            }
        }, hour, minute, true);//true 24 saatli sistem için
        timePicker.setTitle("Saat Seçiniz");
        timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
        timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);

        timePicker.show();
    }



    public void tarihSec(View v) {

        Calendar mcurrentTime = Calendar.getInstance();
        int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
        int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

        DatePickerDialog datePicker;//Datepicker objemiz
        datePicker = new DatePickerDialog(toDoInfoActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                dateText.setText(dayOfMonth + "/" + monthOfYear + "/" + year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

            }
        }, year, month, day);//başlarken set edilcek değerlerimizi atıyoruz
        datePicker.setTitle("Tarih Seçiniz");
        datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
        datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

        datePicker.show();

    }
}

