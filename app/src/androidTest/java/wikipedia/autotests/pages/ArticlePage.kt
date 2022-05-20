package wikipedia.autotests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import io.qameta.allure.kotlin.Allure.step
import org.wikipedia.R
import wikipedia.autotests.elements.Elements.clickById
import wikipedia.autotests.elements.Elements.clickByText

class ArticlePage : BasePage() {
    private val saveButton = resources.getString(R.string.article_menu_bar_save_button)
    private val snackbarAction = R.id.snackbar_action
    private val textInput = R.id.text_input
    private val okButton = resources.getString(R.string.text_input_dialog_ok_button_text)

    fun pressSave() =
        step("Нажимаем на иконку \"Сохранить\"") {
            clickByText(saveButton)
        }

    fun addToList() =
        step("Нажимаем в диалоговом окне \"добавить в список\"") {
            clickById(snackbarAction)
        }

    fun createList(listName: String): ViewInteraction =
        step("Вводим название списка") {
            onView(withId(textInput))
                .perform(replaceText(listName))
        }

    fun pressOk() {
        onView(withText(okButton))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(click())
    }

    companion object {
        inline operator fun invoke(block: ArticlePage.() -> Unit) = ArticlePage().block()
    }
}