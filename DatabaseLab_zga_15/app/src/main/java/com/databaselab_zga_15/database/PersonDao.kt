package com.databaselab_zga_15.database



import androidx.room.*
import com.databaselab_zga_15.model.Person
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {
    @Query("SELECT * FROM persons")
    fun getAllPersons(): Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}
