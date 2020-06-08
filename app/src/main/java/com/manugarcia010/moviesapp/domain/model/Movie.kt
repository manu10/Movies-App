package com.manugarcia010.moviesapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,

    @Expose
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String,


    @Expose
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @Expose
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @Expose
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @Expose
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeDouble(popularity)
        parcel.writeDouble(voteAverage)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}
