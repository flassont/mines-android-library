package mines.flassont.library

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Activity displaying a list of books in portrait mode, and a master/detail in landscape mode.
 */
class LibraryActivity : AppCompatActivity() {

    private val list: RecyclerView by lazy { findViewById(R.id.list) as RecyclerView }
    private val books: List<Book> = (1..10).map {
        Book("Henry Potier tome $it", Random().nextInt(30).toFloat())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        list.adapter = BookAdapter(this, books)
    }
}
