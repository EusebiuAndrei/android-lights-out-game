package com.example.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int numar1;
    int numar2;
    String operatie = "Plus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void GoToGame(View view) {
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }

    public void SelectOperatie(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.calc_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String value = item.getTitle().toString();
                operatie = value;

                TextView Btn = findViewById(R.id.operatie);
                Btn.setText(value);

                return false;
            }
        });
        popupMenu.show();
    }

    public void ShowDialog(View view) {
        String rezultat = GetRezultat();
        if (rezultat.equals("")) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rezultat");
        builder.setMessage(rezultat);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Share", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("smsto:0740123456");
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", rezultat);
                startActivity(it);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public String GetRezultat() {
        EditText Numar1 = findViewById(R.id.numar_1);
        EditText Numar2 = findViewById(R.id.numar_2);

        numar1 = Integer.parseInt(Numar1.getText().toString());
        numar2 = Integer.parseInt(Numar2.getText().toString());

        if (numar2 == 0) {
            String text = "Impartirea la 0 imposibila";
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT );
            toast.show();
            return "";
        }

        int rezultat = 0;
        String semn = "";

        switch (operatie) {
            case "Plus":
                rezultat = numar1 + numar2;
                semn = "+";
                break;
            case "Minus":
                rezultat = numar1 - numar2;
                semn = "-";
                break;
            case "Ori":
                rezultat = numar1 * numar2;
                semn = "*";
                break;
            case "Impartit":
                rezultat = numar1 / numar2;
                semn = "/";
                break;
        }

        return numar1 + " " + semn + " " + numar2 + " = " + rezultat;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}