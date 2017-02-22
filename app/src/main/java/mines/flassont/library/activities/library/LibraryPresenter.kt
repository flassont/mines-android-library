package mines.flassont.library.activities.library

import mines.flassont.library.model.Book
import mines.flassont.library.services.HenriPotierService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class LibraryPresenter(private val activity: LibraryActivity) {
    /**
     * Fetch books remotely and provide them to the activity once ready.
     */
    fun fetchBooks() {
        HenriPotierService.asRetrofitService()
                .listBooks()
                .enqueue(object : Callback<List<Book>> {
                    override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                        activity.setError(t)
                    }

                    override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                        activity.setBooks(response.body())
                    }
                })
    }
}