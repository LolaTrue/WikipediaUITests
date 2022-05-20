package wikipedia.autotests.actions

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.*
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher


fun waitFor(delay: Long): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = isRoot()
        override fun getDescription(): String = "wait for $delay milliseconds"
        override fun perform(uiController: UiController, v: View?) {
            uiController.loopMainThreadForAtLeast(delay)
        }
    }
}

fun swipeFromViewBottomToTop(): ViewAction {

    val coordinatesProvider = CoordinatesProvider() { view ->
        val coordinates = GeneralLocation.BOTTOM_CENTER.calculateCoordinates(view)
        coordinates[1] = (coordinates[1] - coordinates[1] * 0.1).toFloat()
        coordinates
    }

    return GeneralSwipeAction(
        Swipe.FAST, coordinatesProvider,
        GeneralLocation.TOP_CENTER, Press.FINGER
    )

}
