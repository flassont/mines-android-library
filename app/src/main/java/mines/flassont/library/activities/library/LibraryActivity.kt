package mines.flassont.library.activities.library

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import mines.flassont.library.R
import mines.flassont.library.activities.book.BookActivity
import mines.flassont.library.activities.shared.BookAdapter
import mines.flassont.library.model.Book
import timber.log.Timber
import java.util.*

/**
 * Activity displaying a list of books in portrait mode, and a master/detail in landscape mode.
 */
class LibraryActivity : AppCompatActivity() {

    private lateinit var presenter: LibraryPresenter

    private lateinit var list: RecyclerView
    private var books: List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        Timber.plant(Timber.DebugTree())

        list = findViewById(R.id.list) as RecyclerView
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        presenter = LibraryPresenter(this)
        if (savedInstanceState == null) {
            presenter.fetchBooks()
        }
    }

    override fun onSaveInstanceState(state: Bundle) {
        state.putParcelable(LIST_KEY, list.layoutManager.onSaveInstanceState())
        state.putParcelableArrayList(LIBRARY_KEY, ArrayList(books))
        super.onSaveInstanceState(state)
    }

    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)
        setBooks(state.getParcelableArrayList<Book>(LIBRARY_KEY))
        list.layoutManager.onRestoreInstanceState(state.getParcelable(LIST_KEY))
    }

    fun setBooks(books: List<Book>) {
        this.books = books
        list.adapter = BookAdapter(this, books, { onBookSelected(it) })
    }

    fun setError(error: Throwable) {
        Timber.e(error, "An error occured fetching books")
        Toast.makeText(this, R.string.fetch_books_error, Toast.LENGTH_SHORT).show()
    }

    private fun onBookSelected(item: Book): Unit {
        val intent = Intent(this, BookActivity::class.java)
                .putExtra(BookActivity.EXTRA_BOOK, item)
        startActivity(intent)
    }

    companion object {
        private const val LIST_KEY = "mines.flassont.library/library/recycler"
        private const val LIBRARY_KEY = "mines.flassont.library/library/data"
    }
}
