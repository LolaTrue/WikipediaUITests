package wikipedia.autotests.pages

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.kotlin.Allure.step
import org.wikipedia.BuildConfig
import org.wikipedia.R
import org.wikipedia.WikipediaApp
import wikipedia.autotests.elements.Elements.clickByContentDescription
import wikipedia.autotests.elements.Elements.clickById

class MainPage : BasePage() {
    private val moreButton = R.id.nav_more_container
    private val donateButton = R.id.main_drawer_donate_container
    private val settingsButton = R.id.main_drawer_settings_container
    private val loginButton = R.id.main_drawer_account_container
    private val searchContainerId = R.id.search_container
    private val savedButton = resources.getString(R.string.nav_item_saved)
    val address: Uri = Uri.parse(
        resources.getString(
            R.string.donate_url,
            BuildConfig.VERSION_NAME, WikipediaApp.getInstance().language().systemLanguageCode
        )
    )
    private val intentUrlView = Intent(ACTION_VIEW, address)
    val chooserIntent: Intent = Intent.createChooser(intentUrlView, "Open chooser")

    fun pressMore() = step("Нажимаем на кнопку \"Еще\"") {
        clickById(moreButton)
        allureScreenshot("ss_step_pressMore", 90, 1.0f)
    }

    fun pressDonate() = step("Нажимаем на кнопку \"Пожертвовать\"") {
        clickById(donateButton)
        allureScreenshot("ss_step_pressDonate", 90, 1.0f)
    }

    fun pressSettings() = step("Нажимаем на кнопку \"Настройки\"") {
        clickById(settingsButton)
        allureScreenshot("ss_step_pressSettings", 90, 1.0f)
    }

    fun pressLogin() = step("Нажимаем на кнопку \"Войти в Википедию\"") {
        clickById(loginButton)
        allureScreenshot("ss_step_pressLogin", 90, 1.0f)
    }

    fun pressSearch() = step("Нажимаем на поле поиска") {
        clickById(searchContainerId)
        allureScreenshot("ss_step_pressSearch", 90, 1.0f)
    }

    fun pressSavedArticles() = step("Нажимаем кнопку \"Сохранено\"") {
        clickByContentDescription(savedButton)
        allureScreenshot("ss_step_pressSavedArticles", 90, 1.0f)
    }

    fun checkRedirect() =
        step("Проверяем, что случился переход на окно выбора браузера для открытия") {
            intended(hasAction(chooserIntent.action))
            allureScreenshot("ss_checkRedirect", 90, 1.0f)
        }

    companion object {
        inline operator fun invoke(block: MainPage.() -> Unit) = MainPage().block()
    }
}