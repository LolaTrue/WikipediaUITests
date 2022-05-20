package wikipedia.autotests.pages

import android.graphics.Color
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.qameta.allure.kotlin.Allure.step
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertEquals
import org.wikipedia.R
import org.wikipedia.views.PlainPasteEditText
import wikipedia.autotests.elements.Elements.clickById
import wikipedia.autotests.elements.Elements.fillTextField
import wikipedia.autotests.matchers.getTextColor
import wikipedia.autotests.matchers.getTopHintColor

class CreateAccountPage : BasePage() {
    private val passwordHint = resources.getString(R.string.account_creation_password_hint)
    private val usernameHint = resources.getString(R.string.create_account_username_hint)
    private val repeatPasswordHint =
        resources.getString(R.string.create_account_password_repeat_hint)
    private val emailHint = resources.getString(R.string.create_account_email_hint)
    private val submitButtonId = R.id.create_account_submit_button
    private val redColor = Color.RED
    private val errorMessageId = R.id.textinput_error
    private val createAccountPasswordInputId = R.id.create_account_password_input
    private val iconEyeId = R.id.text_input_end_icon

    fun typePassword(password: String) =
        step("Заполняем поле \"Пароль\"") {
            fillTextField(passwordHint, password)
        }

    fun pressEye(): ViewInteraction =
        step("Нажимаем иконку \"глазик\"") {
            onView(
                allOf(
                    withParent(
                        withParent(
                            withParent(
                                withParent(
                                    withId(createAccountPasswordInputId)
                                )
                            )
                        )
                    ),
                    withId(iconEyeId)
                )
            )
                .perform(click())
        }

    fun checkPassword(password: String): ViewInteraction =
        step("Проверяем, что отображается введенный пароль") {
            onView(
                allOf(
                    instanceOf(PlainPasteEditText::class.java),
                    withHint(passwordHint)
                )
            )
                .check(matches(withText(password)))
        }

    fun typeUsername(username: String) =
        step("Заполняем поле \"Имя участника\"") {
            fillTextField(usernameHint, username)
        }

    fun typeRepeatPassword(password: String) =
        step("Заполняем поле \"Повторите пароль\"") {
            fillTextField(repeatPasswordHint, password)
        }

    fun typeEmail(email: String) =
        step("Заполняем поле \"Адрес электронной почты\"") {
            fillTextField(emailHint, email)
        }

    fun pressSubmit() =
        step("Нажимаем на кнопку \"Далее\"") {
            clickById(submitButtonId)
        }

    fun checkErrorMessage(): ViewInteraction =
        step("Проверяем, что появилось сообщение об ошибке: \"Пароль должен состоять не менее чем из 8 символов\"") {
            onView(withId(errorMessageId))
                .check(matches(withText("Пароль должен состоять не менее чем из 8 символов.")))
        }

    fun checkErrorMessageColor() =
        step("Проверяем, что у сообщения об ошибке краснй цвет") {
            assertEquals(redColor, getTextColor(withId(errorMessageId)))
        }

    fun checkPasswordHeaderColor() =
        step("Проверяем, что у заголовка \"Пароль\" красный цвет") {
            assertEquals(redColor, getTopHintColor(withId(createAccountPasswordInputId)))
        }

    companion object {
        inline operator fun invoke(block: CreateAccountPage.() -> Unit) =
            CreateAccountPage().block()
    }
}