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

/**
 * Activity displaying a list of books in portrait mode, and a master/detail in landscape mode.
 */
class LibraryActivity : AppCompatActivity() {

    private lateinit var presenter: LibraryPresenter
    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        Timber.plant(Timber.DebugTree())

        list = findViewById(R.id.list) as RecyclerView
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        presenter = LibraryPresenter(this)
        presenter.fetchBooks()
    }

    fun setBooks(books: List<Book>) {
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
}
