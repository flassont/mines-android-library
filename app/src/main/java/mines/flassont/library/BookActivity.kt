package mines.flassont.library

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mines.flassont.library.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {

    private val item: Book by lazy { intent.getParcelableExtra<Book>(EXTRA_BOOK) }
    private lateinit var binding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book)
        binding.model = item


        supportActionBar?.apply {
            title = item.title
            setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        const val EXTRA_BOOK = "@@book/item"
    }
}
