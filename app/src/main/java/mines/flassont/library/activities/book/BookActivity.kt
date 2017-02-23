package mines.flassont.library.activities.book

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mines.flassont.library.R
import mines.flassont.library.activities.shared.BookFragment
import mines.flassont.library.model.Book

class BookActivity : AppCompatActivity() {

    private val item: Book by lazy { intent.getParcelableExtra<Book>(EXTRA_BOOK) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_activity)

        if (resources.getBoolean(R.bool.is_landscape)) {
            finish()
        } else {
            supportActionBar?.apply {
                title = item.title
                setDisplayHomeAsUpEnabled(true)
            }

            supportFragmentManager.beginTransaction()
                    .replace(R.id.book_activity, BookFragment(intent.extras))
                    .commit()
        }
    }

    companion object {
        const val EXTRA_BOOK = "@@book/item"
    }
}
