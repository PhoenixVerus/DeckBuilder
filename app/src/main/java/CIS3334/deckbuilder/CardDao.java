package CIS3334.deckbuilder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {
    @Insert
    void insert(Card card);

    @Delete
    void delete(Card card);

    @Query("SELECT * FROM Card")
    LiveData<List<Card>> getAll();
}
