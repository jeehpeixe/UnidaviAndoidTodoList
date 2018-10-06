package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String dbName = "Tasks.db";
    private static final int dbVersion = 1;

    public DataBaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tasks(_id integer primary key autoincrement, title text, done boolean)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTask(String title){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("done", false);
        db.insert("tasks", null, values);
    }

    public List<Task> fetchTasks(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                "tasks",
                 new String[] {"_id", "title", "done"},
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();

        List<Task> lista = new ArrayList<>();

        while(!cursor.isAfterLast()){
            /*Task task = new Task(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getInt(cursor.getColumnIndex("done")) == 1
            );*/
            //lista.add(task);
            cursor.moveToNext();
        }

        cursor.close();
        return lista;
    }

    public void deleteTask(Task task){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tasks", "_id=" + task.getId(), null);
    }

    public void concluirTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("done", true);
        db.update("tasks", values, "_id=" + task.getId(), null);
    }
}
