package wikipedia.autotests.pages

import android.content.Context
import android.content.res.Resources
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.kotlin.Allure.step
import org.wikipedia.R
import wikipedia.autotests.actions.swipeFromViewBottomToTop
import wikipedia.autotests.actions.waitFor

open class BasePage {
    val resources: Resources =
        androidx.test.core.app.ApplicationProvider.getApplicationContext<Context?>().resources

    fun swipeViewUp() {
        onView(withId(R.id.recycler_view)).perform(swipeUp())
    }

    fun waitForView(delay: Long = 1000) {
        onView(isRoot()).perform(waitFor(delay))
    }

    fun back(): ViewInteraction =
        step("Нажимаем кнопку \"Назад\"") {
            onView(isRoot())
                .perform(pressBack())
        }

    fun swipeSubMenuUp() {
        onView(isRoot())
            .perform(swipeFromViewBottomToTop())
    }
}