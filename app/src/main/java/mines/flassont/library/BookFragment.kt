package mines.flassont.library

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mines.flassont.library.databinding.BookFragmentBinding

/**
 * Fragment displaying a single book
 */
class BookFragment() : Fragment() {

    private lateinit var binding: BookFragmentBinding

    constructor(bundle: Bundle) : this() {
        arguments = bundle
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.book_fragment, container, false)
        binding.model = arguments?.getParcelable(BookActivity.EXTRA_BOOK)
        return binding.root
    }
}