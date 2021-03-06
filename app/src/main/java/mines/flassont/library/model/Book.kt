package mines.flassont.library.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Representation of a book.
 *
 * A book has a name and a price.
 */
data class Book(
        val isbn: String,
        val title: String,
        val price: String,
        val cover: String,
        val synopsis: ArrayList<String>
) : Parcelable {

    private constructor(source: Parcel)
            : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createStringArrayList()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(isbn)
        dest.writeString(title)
        dest.writeString(price)
        dest.writeString(cover)
        dest.writeStringList(synopsis)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Book> = object : Parcelable.Creator<Book> {
            override fun newArray(size: Int): Array<out Book?> = arrayOfNulls<Book>(size)
            override fun createFromParcel(source: Parcel): Book = Book(source)
        }
    }
}