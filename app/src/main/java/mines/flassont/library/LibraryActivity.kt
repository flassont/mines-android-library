package mines.flassont.library

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Activity displaying a list of books in portrait mode, and a master/detail in landscape mode.
 */
class LibraryActivity : AppCompatActivity() {

    private val list: RecyclerView by lazy { findViewById(R.id.list) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        Timber.plant(Timber.DebugTree())

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        HenriPotierService.asRetrofitService()
                .listBooks()
                .enqueue(object : Callback<List<Book>> {
                    override fun onFailure(call: Call<List<Book>>, t: Throwable?) {
                        Timber.e(t, "Cannot consume HenriPottierService APIs")
                        Toast.makeText(this@LibraryActivity, R.string.fetch_books_error, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                        list.adapter = BookAdapter(this@LibraryActivity, response.body(), { onBookSelected(it) })
                    }
                })
    }

    private infix fun onBookSelected(item: Book): Unit {
        val intent = Intent(this, BookActivity::class.java)
                .putExtra(BookActivity.EXTRA_BOOK, item)
        startActivity(intent)
    }
}
