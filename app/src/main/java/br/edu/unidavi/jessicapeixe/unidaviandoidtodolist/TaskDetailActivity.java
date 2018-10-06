package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        helper = new DataBaseHelper(this);

        task = getIntent().getParcelableExtra("task");
        setTitle(task.getTitle());

        Button botaoDelete = findViewById(R.id.botton_delete);
        botaoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteTask(task);
                finish();
            }
        });

        Button botaoConcluir = findViewById(R.id.botton_done);
        botaoConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.concluirTask(task);
                finish();
            }
        });

    }
}
