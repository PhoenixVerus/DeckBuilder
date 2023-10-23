package CIS3334.deckbuilder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Card.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class CardDatabase extends RoomDatabase {

    public abstract CardDao cardDao();

    private static volatile CardDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CardDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CardDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CardDatabase.class, "card_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
