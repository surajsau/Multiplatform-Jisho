//package `in`.surajsau.jisho.data
//
//import `in`.surajsau.jisho.data.db.Jisho
//import `in`.surajsau.jisho.data.expected.TestDispatcherProvider
//import `in`.surajsau.jisho.data.expected.testDbConnection
//import `in`.surajsau.jisho.data.real.NotesRepositoryImpl
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runTest
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertNotEquals
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class NotesRepositoryTest {
//
//    private lateinit var db: Jisho
//
//    @BeforeTest
//    fun setup() {
//        db = Jisho.invoke(testDbConnection())
//    }
//
//    @Test
//    fun `test insertNote`() = runTest {
//        val repository = NotesRepositoryImpl(db, TestDispatcherProvider(testScheduler))
//
//        val id = repository.insertNote("Sample note added")
//        assertEquals(id, 1L)
//
//        val notes = db.jishoQueries.getAllNotes().executeAsList()
//        assertEquals(notes.size, 1)
//        assertEquals(notes[0].id, 1L)
//        assertEquals(notes[0].text, "Sample note added")
//    }
//
//    @Test
//    fun `test updateNote`() = runTest {
//        val repository = NotesRepositoryImpl(db, TestDispatcherProvider(testScheduler))
//
//        repository.insertNote("Sample note added")
//        repository.updateNotes(1L, "Note has been updated")
//
//        val notes = db.jishoQueries.getAllNotes().executeAsList()
//        assertEquals(notes.size, 1)
//        assertEquals(notes[0].id, 1L)
//        assertNotEquals(notes[0].text, "Sample note added")
//        assertEquals(notes[0].text, "Note has been updated")
//    }
//
//    @Test
//    fun `test removeNote`() = runTest {
//        val repository = NotesRepositoryImpl(db, TestDispatcherProvider(testScheduler))
//
//        repository.insertNote("Sample note added")
//        repository.removeNote(1L)
//
//        val notes = db.jishoQueries.getAllNotes().executeAsList()
//        assertEquals(notes.size, 0)
//    }
//
//    @Test
//    fun `test getNote`() = runTest {
//        val repository = NotesRepositoryImpl(db, TestDispatcherProvider(testScheduler))
//
//        repository.insertNote("Sample note added")
//        val note = repository.getNote(1L)
//
//        assertEquals(note.id, 1L)
//        assertEquals(note.text, "Sample note added")
//    }
//}
