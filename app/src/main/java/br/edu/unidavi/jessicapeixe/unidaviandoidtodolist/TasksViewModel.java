package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    public final MutableLiveData<List<Task>> tasks = new MutableLiveData<>();
    public final MutableLiveData<Task> taskLiveData = new MutableLiveData<>();

    public TasksViewModel(@NonNull Application application) {
        super(application);

    }

    public void fetchTasks() {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                tasks.postValue(TasksStore.getInstance(getApplication()).getTasksDao().fetchTasks());
                return null;
            }
        }.execute();
    }

    public void findTaskById(int id) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Task task = TasksStore.getInstance(getApplication()).getTasksDao().findById(id);
                taskLiveData.postValue(task);
                return null;
            }
        }.execute();
    }

    public void insert(Task task){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                TasksStore.getInstance(getApplication()).getTasksDao().insert(task);
                return null;
            }
        }.execute();
    }

    public void update(Task task) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                TasksStore.getInstance(getApplication()).getTasksDao().update(task);
                return null;
            }
        }.execute();
    }

    public void delete(Task task) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                TasksStore.getInstance(getApplication()).getTasksDao().delete(task);
                return null;
            }
        }.execute();
    }
}
