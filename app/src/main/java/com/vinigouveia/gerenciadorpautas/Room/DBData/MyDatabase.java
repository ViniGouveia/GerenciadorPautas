package com.vinigouveia.gerenciadorpautas.Room.DBData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vinigouveia.gerenciadorpautas.Room.DBDao.AgendaDao;
import com.vinigouveia.gerenciadorpautas.Room.DBDao.UserDao;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.AgendaEntity;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.UserEntity;

@Database(entities = {UserEntity.class, AgendaEntity.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase INSTANCE;
    public abstract UserDao userDao();
    public abstract AgendaDao agendaDao();

    public static MyDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
