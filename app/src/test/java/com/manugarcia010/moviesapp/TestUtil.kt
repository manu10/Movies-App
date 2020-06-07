package com.manugarcia010.moviesapp

import androidx.lifecycle.LiveData
import com.manugarcia010.moviesapp.ui.Event
import org.junit.Assert.assertEquals

fun assertLiveDataEventTriggered(
    liveData: LiveData<Event<Int>>,
    movieId: Int
) {
    val value = LiveDataTestUtil.getValue(liveData)
    assertEquals(value.getContentIfNotHandled(), movieId)
}
