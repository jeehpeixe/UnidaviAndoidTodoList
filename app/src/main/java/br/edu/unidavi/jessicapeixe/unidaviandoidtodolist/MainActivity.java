package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private TasksViewModel viewModel;

    private TasksAdapter adapter = new TasksAdapter(task -> {
        Intent intent = new Intent(getApplicationContext(), TaskDetailActivity.class);
        intent.putExtra("id", task.getId());
        startActivity(intent);
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adicionaObservadores();
        carregaListaTarefas();
        onCreateItem();
    }

    private void onCreateItem() {
        FloatingActionButton botaoAdicionar = findViewById(R.id.botao_adicionar);
        botaoAdicionar.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), NewTaskActivity.class)));
    }

    private void carregaListaTarefas(){
        RecyclerView taskList = findViewById(R.id.task_list);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(adapter);
    }

    private void adicionaObservadores() {
        getViewModel().tasks.observe(this, tasks -> {
            if (tasks != null) {
                adapter.setup(tasks);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().fetchTasks();
    }

    private TasksViewModel getViewModel(){
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        }
        return viewModel;
    }
}
