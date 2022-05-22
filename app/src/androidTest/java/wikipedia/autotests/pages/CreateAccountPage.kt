package wikipedia.autotests.pages

import android.graphics.Color
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.qameta.allure.android.allureScreenshot
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
            allureScreenshot("ss_step_typePassword", 90, 1.0f)
        }

    fun pressEye() =
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
            allureScreenshot("ss_step_pressEye", 90, 1.0f)
        }

    fun checkPassword(password: String) =
        step("Проверяем, что отображается введенный пароль") {
            onView(
                allOf(
                    instanceOf(PlainPasteEditText::class.java),
                    withHint(passwordHint)
                )
            )
                .check(matches(withText(password)))
            allureScreenshot("ss_step_checkPassword", 90, 1.0f)
        }

    fun typeUsername(username: String) =
        step("Заполняем поле \"Имя участника\"") {
            fillTextField(usernameHint, username)
            allureScreenshot("ss_step_typeUsername", 90, 1.0f)
        }

    fun typeRepeatPassword(password: String) =
        step("Заполняем поле \"Повторите пароль\"") {
            fillTextField(repeatPasswordHint, password)
            allureScreenshot("ss_step_typeRepeatPassword", 90, 1.0f)
        }

    fun typeEmail(email: String) =
        step("Заполняем поле \"Адрес электронной почты\"") {
            fillTextField(emailHint, email)
            allureScreenshot("ss_step_typeEmail", 90, 1.0f)
        }

    fun pressSubmit() =
        step("Нажимаем на кнопку \"Далее\"") {
            clickById(submitButtonId)
            allureScreenshot("ss_step_pressSubmit", 90, 1.0f)
        }

    fun checkErrorMessage() =
        step("Проверяем, что появилось сообщение об ошибке: \"Пароль должен состоять не менее чем из 8 символов\"") {
            onView(withId(errorMessageId))
                .check(matches(withText("Пароль должен состоять не менее чем из 8 символов.")))
            allureScreenshot("ss_step_checkErrorMessage", 90, 1.0f)
        }

    fun checkErrorMessageColor() =
        step("Проверяем, что у сообщения об ошибке краснй цвет") {
            assertEquals(redColor, getTextColor(withId(errorMessageId)))
            allureScreenshot("ss_step_checkErrorMessageColor", 90, 1.0f)
        }

    fun checkPasswordHeaderColor() =
        step("Проверяем, что у заголовка \"Пароль\" красный цвет") {
            assertEquals(redColor, getTopHintColor(withId(createAccountPasswordInputId)))
            allureScreenshot("ss_step_checkPasswordHeaderColor", 90, 1.0f)
        }

    companion object {
        inline operator fun invoke(block: CreateAccountPage.() -> Unit) =
            CreateAccountPage().block()
    }
}