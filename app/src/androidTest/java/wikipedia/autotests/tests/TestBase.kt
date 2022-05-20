package wikipedia.autotests.tests

import android.content.Intent
import android.text.method.PasswordTransformationMethod
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.wikipedia.main.MainActivity
import wikipedia.autotests.pages.OnboardingPage

open class TestBase {
    private lateinit var launchedActivity: MainActivity

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun before() {
        setup()
        skipOnboardingPresentation()
    }

    @After
    fun tearDown() {
    }

    private fun setup() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        launchedActivity = activityRule.launchActivity(intent)
    }

    private fun skipOnboardingPresentation() {
        OnboardingPage {
            skipPresentation()
        }
    }

    companion object {
        const val USERNAME = "Olga Trubina"
        const val PASSWORD = "password"
        const val PWD = "pwd"
        const val EMAIL = "test@test.com"
        val PASSWORD_TRANSFORMED = PasswordTransformationMethod().getTransformation(PASSWORD, null).toString()
        const val SEARCHTEXT = "Тинькофф премьер лига"
        const val LISTNAME = "Тинькофф"
    }
}
