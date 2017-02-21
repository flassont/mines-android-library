package mines.flassont.library

/**
 * Representation of a book.
 *
 * A book has a name and a price.
 */
data class Book(
        val isbn: String,
        val title: String,
        val price: String,
        val cover: String
)