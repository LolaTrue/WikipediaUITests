package wikipedia.autotests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Allure.step
import org.wikipedia.R

class AboutWikipediaPage: BasePage() {
    val contributors = resources.getString(R.string.about_contributors_heading)
    val translators = resources.getString(R.string.about_translators_heading)
    val license = resources.getString(R.string.about_app_license_heading)

    fun checkBlocks() =
        step("Проверяем, что на экране есть блок \"Авторы\", \"Переводчики\" и \"Лицензия\"") {
            onViewMatches(contributors)
            onViewMatches(translators)
            onViewMatches(license)
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