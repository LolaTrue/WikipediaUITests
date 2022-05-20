package wikipedia.autotests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.android.material.textview.MaterialTextView
import io.qameta.allure.kotlin.Allure.step
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import wikipedia.autotests.elements.Elements.clickById

class SavedArticlesPage : BasePage() {
    private val articleTitleId = R.id.page_list_item_title
    private val removeButtonId = R.id.reading_list_item_remove
    private val snackbarTextId = R.id.snackbar_text

    fun pressList(listName: String): ViewInteraction =
        step("Нажимаем на список $listName") {
            onView(
                allOf(
                    instanceOf(MaterialTextView::class.java),
                    withText(listName)
                )
            )
                .perform(click())
        }

    fun checkArticleInList(): ViewInteraction =
        step("Проверяем, что название статьи отображается в списке") {
            onView(withId(articleTitleId))
                .check(matches(isDisplayed()))
        }

    fun longPressArticle(): ViewInteraction =
        step("Долгое нажатие на статью в списке") {
            onView(withId(articleTitleId))
                .perform(longClick())
        }

    fun deleteArticleFromList() =
        step("Нажимаем на кнопку \"Удалить\"") {
            clickById(removeButtonId)
        }

    fun checkDeleteMessage(): ViewInteraction =
        step("Проверяем, что появляется уведомление о том, что список удален") {
            onView(withId(snackbarTextId))
                .check(matches(isDisplayed()))
        }

    companion object {
        inline operator fun invoke(block: SavedArticlesPage.() -> Unit) =
            SavedArticlesPage().block()
    }
}