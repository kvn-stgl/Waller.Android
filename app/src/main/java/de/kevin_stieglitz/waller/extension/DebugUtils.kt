package de.kevin_stieglitz.waller.extension

import androidx.sqlite.db.SupportSQLiteDatabase
import de.kevin_stieglitz.waller.BuildConfig
import java.util.*

fun SupportSQLiteDatabase.setInMemoryDebugDatabase() {
    if (BuildConfig.DEBUG) {
        try {
            val debugDB = Class.forName("com.amitshekhar.DebugDB")
            val argTypes = arrayOf<Class<*>>(HashMap::class.java)
            val inMemoryDatabases = HashMap<String, SupportSQLiteDatabase>()
            // set your inMemory databases
            inMemoryDatabases["InMemoryDb"] = this
            val setRoomInMemoryDatabase = debugDB.getMethod("setInMemoryRoomDatabases", *argTypes)
            setRoomInMemoryDatabase.invoke(null, inMemoryDatabases)
        } catch (ignore: Exception) {

        }
    }
}