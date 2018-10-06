package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Button botaoSalvar = findViewById(R.id.botao_nova_tarefa);
        botaoSalvar.setOnClickListener(v -> {
            EditText texto = findViewById(R.id.campo_nova_tarefa);
            String value = texto.getText().toString();
            if (!value.isEmpty()) {
                TasksStore.getInstance(getApplicationContext()).getTasksDao().insert(new Task(value, false));
                finish();
            }
        });
    }
}
