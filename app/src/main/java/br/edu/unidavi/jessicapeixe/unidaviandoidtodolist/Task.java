package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private final Integer id;

    private final String title;

    private final boolean done;

    private final Date data;

    @Ignore
    public Task(String title, boolean done) {
        this.id = null;
        this.data = new Date();
        this.title = title;
        this.done = done;
    }

    public Task(Integer id, String title, boolean done, Date data) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    @Nullable
    public Date getData() {
        return data;
    }
}
