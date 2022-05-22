package wikipedia.autotests.tests

import android.content.Context
import android.content.res.Resources
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.wikipedia.R
import wikipedia.autotests.pages.AboutWikipediaPage
import wikipedia.autotests.pages.MainPage
import wikipedia.autotests.pages.SettingsPage

@RunWith(Parameterized::class)
class CheckAboutWikipediaTest(val block: String) : TestBase() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<String> {
            val resources: Resources =
                androidx.test.core.app.ApplicationProvider.getApplicationContext<Context?>().resources
            val contributors = resources.getString(R.string.about_contributors_heading)
            val translators = resources.getString(R.string.about_translators_heading)
            val license = resources.getString(R.string.about_app_license_heading)
            return listOf(contributors, translators, license)
        }
    }

    @Test
    @DisplayName("Проверка блоков на экране \"О приложении\"")
    fun testCheckAboutWikipedia() {
        MainPage {
            pressMore()
            pressSettings()
        }
        SettingsPage {
            swipeViewUp()
            waitForView()
            pressAboutWikipedia()
        }
        AboutWikipediaPage {
            checkBlocks(block)
        }
    }
}