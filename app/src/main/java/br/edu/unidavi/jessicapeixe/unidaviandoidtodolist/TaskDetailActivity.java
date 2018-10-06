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

        int id = getIntent().getIntExtra("id", 0);

        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        viewModel.taskLiveData.observe(this, task -> {
            if (task != null) {
                this.task = task;
                setTitle(task.getTitle());
            }
        });

        viewModel.findTaskById(id);

        Button botaoDelete = findViewById(R.id.botton_delete);
        botaoDelete.setOnClickListener(v -> {
            viewModel.delete(task);
            finish();
        });

        Button botaoConcluir = findViewById(R.id.botton_done);
        botaoConcluir.setOnClickListener(v -> {
            viewModel.update(new Task(task.getId(), task.getTitle(), true, task.getData()));
            finish();
        });

    }
}
