package mines.flassont.library.activities.library

import android.app.Instrumentation
import android.content.pm.ActivityInfo
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import mines.flassont.library.R
import mines.flassont.library.activities.book.BookActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LibraryActivityTest {

    @get:Rule val activityTestRule = ActivityTestRule(LibraryActivity::class.java)

    private lateinit var instrumentation: Instrumentation

    @Before
    fun setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation()
    }

    /**
     * Given device is in portrait mode,
     * When a book is selected from the list,
     * Then the activity should start BookActivity
     */
    @Test
    fun shouldStartBookActivityInPortrait() {
        // Given
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val monitor = instrumentation.addMonitor(BookActivity::class.java.name, null, false)

        // When
        Thread.sleep(1000)
        onView(isAssignableFrom(RecyclerView::class.java))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Then
        instrumentation.checkMonitorHit(monitor, 1)
    }

    /**
     * Given device is in landscape mode,
     * When a book is selected from the list,
     * Then the activity should open a BookFragment containing the selected item
     */
    @Test
    fun shouldStartBookActivityInLandscape() {
        // Given
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // When
        Thread.sleep(1000)
        onView(isAssignableFrom(RecyclerView::class.java))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        // Then
        onView(withId(R.id.book_detail))
                .check(matches(hasDescendant(isAssignableFrom(ImageView::class.java))))
    }
}