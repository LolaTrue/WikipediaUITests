package wikipedia.autotests.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.kotlin.Allure.step
import org.wikipedia.R

class SearchPage : BasePage() {
    private val searchTextField = R.id.search_src_text
    private val searchResultsList = R.id.search_results_list

    fun typeSearchText(searchText: String) =
        step("Вводим текст в поле поиска") {
            onView(withId(searchTextField))
                .perform(replaceText(searchText))
            allureScreenshot("ss_step_typeSearchText", 90, 1.0f)
        }

    fun openArticle() =
        step("Открываем статью") {
            onView(withId(searchResultsList))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                    )
                )
            allureScreenshot("ss_step_openArticle", 90, 1.0f)
        }

    companion object {
        inline operator fun invoke(block: SearchPage.() -> Unit) = SearchPage().block()
    }
}