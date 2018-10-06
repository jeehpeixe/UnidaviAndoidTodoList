package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        int id = getIntent().getIntExtra("id", 0);
        task = TasksStore.getInstance(getApplicationContext()).getTasksDao().findById(id);
        setTitle(task.getTitle());

        Button botaoDelete = findViewById(R.id.botton_delete);
        botaoDelete.setOnClickListener(v -> {
            TasksStore.getInstance(getApplicationContext()).getTasksDao().delete(task);
            finish();
        });

        Button botaoConcluir = findViewById(R.id.botton_done);
        botaoConcluir.setOnClickListener(v -> {
            TasksStore.getInstance(getApplicationContext()).getTasksDao().update(
                new Task(task.getId(), task.getTitle(), true, task.getData())
            );
            finish();
        });

    }
}
