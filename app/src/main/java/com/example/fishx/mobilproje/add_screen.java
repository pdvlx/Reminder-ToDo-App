package com.example.fishx.mobilproje;
import java.util.Calendar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class add_screen extends AppCompatActivity {
    Context context = this;
    SQLiteAdapter db = new SQLiteAdapter(context);
    //toolbar sonra eklenecek 24:22 part1
    private int secilenSaat,secilenDk,secilenYil,secilenAy,secilenGun;
    private EditText reminderText;
    private TextView dateText, timeText, alarmText, repeatNumberText, repeatTypeText;
    private FloatingActionButton yildizli1, yildizli2;

    private long repeatTime;
    private Switch repeated;
    boolean yeni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);

        reminderText = findViewById(R.id.reminder);
        dateText = findViewById(R.id.dateTxt);
        timeText = findViewById(R.id.timeTxt);





    }



    public void toDoEkle(View v){
        db.onUpgrade(db.getWritableDatabase(),1,2);
        if(secilenYil==0|secilenSaat==0)
            db.contentAdd(new listAdapter(reminderText.getText().toString(),null,null,null,null,null,null));
        else
            db.contentAdd(new listAdapter(reminderText.getText().toString(),Integer.toString(secilenYil),Integer.toString(secilenAy),Integer.toString(secilenGun),Integer.toString(secilenSaat),Integer.toString(secilenDk),null));


        Toast.makeText(getApplicationContext(), "Aktivite Başarıyla Eklendi.", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void saatSec(View v) {
        Calendar mcurrentTime = Calendar.getInstance();//
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
        int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
        TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk


        timePicker = new TimePickerDialog(add_screen.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(selectedHour <10)
                    timeText.setText( "0"+selectedHour + ":" + selectedMinute+"'e ayarlandı.");
                else
                    timeText.setText( selectedHour + ":" + selectedMinute+"'e ayarlandı.");
                if(selectedMinute <10)
                    timeText.setText( selectedHour + ":" +"0"+ selectedMinute+"'e ayarlandı.");
                else
                    timeText.setText( selectedHour + ":" +selectedMinute+"'e ayarlandı.");

                secilenSaat = selectedHour;
                secilenDk = selectedMinute;
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
        datePicker = new DatePickerDialog(add_screen.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                dateText.setText(dayOfMonth + "/" + monthOfYear + "/" + year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                secilenGun = dayOfMonth;
                secilenAy = monthOfYear;
                secilenYil = year;
            }

        }, year, month, day);//başlarken set edilcek değerlerimizi atıyoruz
        datePicker.setTitle("Tarih Seçiniz");
        datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
        datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

        datePicker.show();

    }
}


