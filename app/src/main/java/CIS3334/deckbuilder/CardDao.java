package CIS3334.deckbuilder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Card Data Access Object class
 */
@Dao
public interface CardDao {

    /**
     * Interpreter to add card object to the database
     * @param card card object to be added
     */
    @Insert
    void insert(Card card);

    /**
     * Interpreter to delete card object from the database
     * @param card card object to be removed
     */
    @Delete
    void delete(Card card);

    /**
     * Interpreter to retrieve all card objects stored in the database
     * @return all saved card objects from database
     */
    @Query("SELECT * FROM Card")
    LiveData<List<Card>> getAll();
}
