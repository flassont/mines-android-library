package mines.flassont.library.activities.library

import com.google.gson.Gson
import mines.flassont.library.model.Book
import mines.flassont.library.services.HenriPotierService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import java.lang.reflect.Modifier


/**
 * Tests of {@link LibraryPresenter}
 */
class LibraryPresenterTest {

    @get:Rule val mockito = MockitoJUnit.rule()

    @Mock private lateinit var activity: LibraryActivity
    @InjectMocks private lateinit var presenter: LibraryPresenter
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        setServiceUrl(server.url("/").toString())
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun fetchBooksShouldCallSetBookWhenSuccess() {
        // Given
        val expected = listOf(Book("2f02436952f", "Henri Potier à l'école des sorciers", "35", "", arrayListOf("")))
        val fromServer = Gson().toJson(expected)
        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(fromServer))

        // When
        presenter.fetchBooks()

        // Then
        verify(activity, timeout(500).times(1)).setBooks(expected)
    }

    @Test
    fun fetchBooksShouldCallSetErrorWhenFailure() {
        // Given
        val fromServer = "???"
        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(fromServer))

        // When
        presenter.fetchBooks()

        // Then
        verify(activity, timeout(500).times(1)).setError(any())
    }

    /**
     * Override the API_URL from {@link mines.flassont.library.services.HenriPottierService$Companion}
     */
    private fun setServiceUrl(url: String) {
        HenriPotierService.Companion::class.java.getDeclaredField("API_URL").let { field ->
            field.isAccessible = true
            field.javaClass.getDeclaredField("modifiers").let {
                it.isAccessible = true
                it.setInt(field, field.modifiers and Modifier.FINAL.inv())
            }
            field.set(null, url)
        }
    }

    /**
     * Match an argument of T type in Kotlin-friendly way
     *
     * @see "https://medium.com/@elye.project/befriending-kotlin-and-mockito-1c2e7b0ef791#.6bvflycrd"
     */
    private fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }
}
