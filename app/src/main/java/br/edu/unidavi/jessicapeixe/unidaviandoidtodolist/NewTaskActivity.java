package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    private TasksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        Button botaoSalvar = findViewById(R.id.botao_nova_tarefa);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText texto = findViewById(R.id.campo_nova_tarefa);
                String value = texto.getText().toString();
                if (!value.isEmpty()) {
                    viewModel.insert(new Task(value, false));
                    finish();
                }
            }
        });
    }
}
