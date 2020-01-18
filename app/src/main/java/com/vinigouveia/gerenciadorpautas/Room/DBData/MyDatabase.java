package com.vinigouveia.gerenciadorpautas.Room.DBData;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vinigouveia.gerenciadorpautas.Room.DBDao.AgendaDao;
import com.vinigouveia.gerenciadorpautas.Room.DBDao.UserDao;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.AgendaEntity;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.UserEntity;

@Database(entities = {UserEntity.class, AgendaEntity.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract AgendaDao agendaDao();
}
