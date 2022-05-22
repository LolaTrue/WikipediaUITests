package wikipedia.autotests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.kotlin.Allure.step

class AboutWikipediaPage : BasePage() {

    fun checkBlocks(block: String) =
        step("Проверяем, что на экране есть блок \"$block\"") {
            onViewMatches(block)
            allureScreenshot("ss_step_checkBlocks", 90, 1.0f)
        }

    private fun onViewMatches(text: String) {
        onView(withText(text))
            .check(matches(isDisplayed()))
    }

    companion object {
        inline operator fun invoke(block: AboutWikipediaPage.() -> Unit) =
            AboutWikipediaPage().block()
    }
}