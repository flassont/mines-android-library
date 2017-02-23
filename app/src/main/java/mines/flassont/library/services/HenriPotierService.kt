package mines.flassont.library.services

import mines.flassont.library.model.Book
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * API reference for Henry Potier book collection's API
 */
interface HenriPotierService {
    @GET("books")
    fun listBooks(): Call<List<Book>>

    companion object {
        private const val API_URL = "http://henri-potier.xebia.fr"
        fun asRetrofitService(): HenriPotierService =
                Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(HenriPotierService::class.java)
    }
}