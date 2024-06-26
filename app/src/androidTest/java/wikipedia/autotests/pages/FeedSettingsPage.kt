package wikipedia.autotests.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.kotlin.Allure.step
import org.wikipedia.R
import wikipedia.autotests.matchers.atPosition

class FeedSettingsPage {
    private val contentTypesRecyclerId = R.id.content_types_recycler

    fun scroll(position: Int) {
        onView(withId(contentTypesRecyclerId))
            .perform(
                scrollToPosition<RecyclerView.ViewHolder>(position)
            )
    }

    fun checkSwitch(position: Int) =
        step("Проверяем, что каждый свитч в состоянии checked") {
            onView(withId(contentTypesRecyclerId))
                .check(
                    matches(
                        atPosition(
                            position,
                            hasDescendant(
                                isChecked()
                            )
                        )
                    )
                )
            allureScreenshot("ss_step_checkSwitch", 90, 1.0f)
        }

    companion object {
        inline operator fun invoke(block: FeedSettingsPage.() -> Unit) = FeedSettingsPage().block()
    }
}