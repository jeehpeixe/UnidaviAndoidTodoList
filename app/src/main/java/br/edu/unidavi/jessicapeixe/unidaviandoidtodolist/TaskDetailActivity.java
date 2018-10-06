package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;
    private TasksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        adicionaObservadores();

        getViewModel().findTaskById(getIntent().getIntExtra("id", 0));

        onDeleteItem();
        onFinishItem();
    }

    private void onDeleteItem() {
        Button botaoDelete = findViewById(R.id.botton_delete);
        botaoDelete.setOnClickListener(v -> {
            getViewModel().delete(task);
        });
    }

    private void onFinishItem() {
        Button botaoConcluir = findViewById(R.id.botton_done);
        botaoConcluir.setOnClickListener(v -> {
            getViewModel().update(new Task(task.getId(), task.getTitle(), true, task.getData()));
        });
    }

    private TasksViewModel getViewModel(){
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        }
        return viewModel;
    }

    private void adicionaObservadores(){
        getViewModel().taskLiveData.observe(this, task -> {
            if (task != null) {
                this.task = task;
                setTitle(task.getTitle());
            }
        });

        getViewModel().success.observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                finish();
            }
        });
    }
}
