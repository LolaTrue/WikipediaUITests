package wikipedia.autotests.tests

import wikipedia.autotests.pages.MainPage
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import wikipedia.autotests.pages.FeedSettingsPage
import wikipedia.autotests.pages.SettingsPage

@RunWith(Parameterized::class)
class FeedDefaultSettingsTest (val position: Int): TestBase() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Int> {
            val positions = mutableListOf<Int>()
            for (count in 0..7) {
                positions.add(count)
            }
            return positions
        }
    }

    @Test
    @DisplayName("Проверка настройки ленты по умолчанию")
    fun testFeedDefaultSettings() {
        MainPage {
            pressMore()
            pressSettings()
        }
        SettingsPage {
            pressExploreFeed()
        }
        FeedSettingsPage {
                scroll(position)
                checkSwitch(position)
            }
    }
}