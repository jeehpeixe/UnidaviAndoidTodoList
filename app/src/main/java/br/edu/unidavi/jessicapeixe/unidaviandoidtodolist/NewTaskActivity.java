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

        adicionaObservadores();
        onSaveItem();
    }

    private void onSaveItem() {
        Button botaoSalvar = findViewById(R.id.botao_nova_tarefa);
        botaoSalvar.setOnClickListener(v -> {
            EditText texto = findViewById(R.id.campo_nova_tarefa);
            String value = texto.getText().toString();
            if (!value.isEmpty()) {
                getViewModel().insert(new Task(value, false));
            }
        });
    }

    private void adicionaObservadores() {
        getViewModel().success.observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                finish();
            }
        });
    }

    private TasksViewModel getViewModel(){
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        }
        return viewModel;
    }
}
