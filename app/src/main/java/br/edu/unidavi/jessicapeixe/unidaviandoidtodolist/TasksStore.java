package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Task.class, version = 1)
public abstract class TasksStore extends RoomDatabase{

    public abstract TaskDao getTasksDao();

    private static TasksStore instance = null;

    public static TasksStore getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, TasksStore.class, "Tasks.db").allowMainThreadQueries().build();
        }

        return instance;
    }
}
