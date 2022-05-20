package wikipedia.autotests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Allure.step
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import wikipedia.autotests.elements.Elements.clickByText

class SettingsPage: BasePage() {
    val exploreFeed = resources.getString(R.string.preference_title_customize_explore_feed)
    private val aboutWikipedia = resources.getString(R.string.about_description)

    fun pressExploreFeed() =
        step("Нажимаем на кнопку $exploreFeed") {
            clickByText(exploreFeed)
        }

    fun pressAboutWikipedia(): ViewInteraction =
        step("Нажимаем на кнопку \"О приложении \"Википедия\"\"") {
            onView(allOf(withText(aboutWikipedia), isCompletelyDisplayed()))
                .perform(click())
        }

    companion object {
        inline operator fun invoke(block: SettingsPage.() -> Unit) = SettingsPage().block()
    }
}