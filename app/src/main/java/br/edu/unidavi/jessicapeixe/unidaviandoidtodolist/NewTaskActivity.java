package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        dbHelper = new DataBaseHelper(this);

        Button botaoSalvar = findViewById(R.id.botao_nova_tarefa);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText texto = findViewById(R.id.campo_nova_tarefa);
                String value = texto.getText().toString();
                if (!value.isEmpty()) {
                    dbHelper.createTask(value);
                    finish();
                }
            }
        });
    }
}
