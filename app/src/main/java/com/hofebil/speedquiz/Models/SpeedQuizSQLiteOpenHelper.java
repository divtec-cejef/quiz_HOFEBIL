package com.hofebil.speedquiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {
    static String DB_NAME = "SpeedQuiz.db";
    static int DB_VERSION = 1;

    public SpeedQuizSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatatableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY, question TEXT, reponse INTEGER);";
        db.execSQL(sqlCreateDatatableQuiz);
        db.execSQL("INSERT INTO quiz VALUES(1,\"le chocolat peut posséder plusieurs couleurs\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"la meme chose peut posséder plusieurs couleurs\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"les chats peuvent posséder plusieurs couleurs\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"une Toyota AE86 est disponible en plusieurs couleurs\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"l'air peut posséder plusieurs couleurs\", 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
}
