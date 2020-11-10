package com.example.learnforeignlanguage.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqlite extends SQLiteOpenHelper {

    public Sqlite(@Nullable Context context) {
        super(context, "data.db", null, 1);
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Custom ="CREATE TABLE Custom (" +
                "idCustom INTEGER NOT NULL,"+
                "PRIMARY KEY(idCustom))";
        db.execSQL(Custom);
        String CustomDetail ="CREATE TABLE CustomDetail(" +
                "idCustomDetail INTEGER NOT NULL," +
                "idCustom INTEGER," +
                "idVocabulary INTEGER," +
                "FOREIGN KEY(idVocabulary) REFERENCES Vocabulary(IdVocabulary)," +
                "FOREIGN KEY(idCustom) REFERENCES Custom(idCustom)," +
                "PRIMARY KEY(idCustomDetail))";
        db.execSQL(CustomDetail);
        String History ="CREATE TABLE History (" +
                "idHistory INTEGER NOT NULL,"+
                "idCustom INTEGER," +
                "idUser INTEGER," +
                "FOREIGN KEY(idUser) REFERENCES User(idUser)," +
                "FOREIGN KEY(idCustom) REFERENCES Custom(idCustom)," +
                "PRIMARY KEY(idHistory))";
        db.execSQL(History);
        String Theme = "CREATE TABLE Theme (" +
                "idTheme INTEGER NOT NULL," +
                "idCustom INTEGER," +
                "theme TEXT," +
                "PRIMARY KEY(idTheme)," +
                "FOREIGN KEY(idCustom) REFERENCES Custom(idCustom))";
        db.execSQL(Theme);
        String User = "CREATE TABLE User(" +
                "idUser INTEGER NOT NULL," +
                "Email TEXT," +
                "PhoneNumber TEXT,"+
                "UserName TEXT,"+
                "Password TEXT,"+
                "PRIMARY KEY(idUser))";
        db.execSQL(User);
        String Vocabulary = "CREATE TABLE Vocabulary(" +
                "IdVocabulary INTEGER NOT NULL," +
                "Vocabulary TEXT," +
                "Means TEXT," +
                "Pronounce TEXT," +
                "PRIMARY KEY(IdVocabulary))";
        db.execSQL(Vocabulary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
