package com.phoenixwings7.shoppinglisterassignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ShoppingList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String APP_DB_NAME = "shopping_lister_db";

    // Only use this method to get a database instance to avoid creating multiple instances.
    public static AppDatabase getInstance(Context appContext) {
        if (INSTANCE == null) {
            appContext.getApplicationContext();
            INSTANCE = Room.databaseBuilder(appContext.getApplicationContext(),
                    AppDatabase.class, APP_DB_NAME).build();
        }
        return INSTANCE;
    }

    public abstract ShoppingListDao shoppingListDao();
}
