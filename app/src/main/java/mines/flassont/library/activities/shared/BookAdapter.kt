package mines.flassont.library.activities.shared

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mines.flassont.library.databinding.BookItemBinding
import mines.flassont.library.model.Book

/**
 * Adapter for a RecyclerView
 */
class BookAdapter(
        ctx: Context,
        private val books: List<Book>,
        var onBookSelected: ((Book) -> Unit)? = null
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.binding.model = books[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener { onBookSelected?.invoke(binding.model) }
        return BookViewHolder(binding)
    }

    inner class BookViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)
}
