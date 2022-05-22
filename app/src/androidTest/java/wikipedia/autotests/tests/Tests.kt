package wikipedia.autotests.tests

import wikipedia.autotests.pages.MainPage
import androidx.test.espresso.intent.Intents
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Test
import org.junit.runner.RunWith
import wikipedia.autotests.pages.*

@RunWith(AllureAndroidJUnit4::class)
@DisplayName("Wikipedia UI tests")
class Tests : TestBase() {

    @Test
    @DisplayName("Проверка перехода в браузер")
    fun testCheckRedirectToBrowser() {
        MainPage {
            pressMore()
            Intents.init()
            pressDonate()
            checkRedirect()
            Intents.release()
        }
    }

    @Test
    @DisplayName("Проверка, что пароль скрывается, если нажать на глазик два раза")
    fun testHidePassword() {
        MainPage {
            pressMore()
            pressLogin()
        }
        CreateAccountPage {
            typePassword(PASSWORD)
            pressEye()
            checkPassword(PASSWORD)
            pressEye()
            checkPassword(PASSWORD_TRANSFORMED)
        }
    }

    @Test
    @DisplayName("Проверка валидации поля установки пароля")
    fun testValidatePasswordField() {
        MainPage {
            pressMore()
            pressLogin()
        }
        CreateAccountPage {
            typeUsername(USERNAME)
            typePassword(PWD)
            typeRepeatPassword(PWD)
            typeEmail(EMAIL)
            pressSubmit()
            checkPasswordHeaderColor()
            checkErrorMessage()
            checkErrorMessageColor()
        }
    }

    @Test
    @DisplayName("Проверка добавления статьи в избранное")
    fun testAddArticleToFavourite() {
        MainPage {
            pressSearch()
        }
        SearchPage {
            typeSearchText(SEARCHTEXT)
            waitForView(3000)
            openArticle()
        }
        ArticlePage {
            pressSave()
            waitForView()
            addToList()
            createList(LISTNAME)
            pressOk()
            back()
            back()
            back()
        }
        MainPage {
            pressSavedArticles()
        }
        SavedArticlesPage {
            pressList(LISTNAME)
            checkArticleInList()
        }
    }

    @Test
    @DisplayName("Проверка удаления статьи из избранного")
    fun testRemoveArticleToFavourite() {
        testAddArticleToFavourite()
        SavedArticlesPage {
            longPressArticle()
            swipeSubMenuUp()
            deleteArticleFromList()
            checkDeleteMessage()
        }
    }
}