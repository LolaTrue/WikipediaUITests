package wikipedia.autotests.pages

import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.kotlin.Allure.step
import org.wikipedia.R
import wikipedia.autotests.elements.Elements.clickById

class OnboardingPage {
    private val skipButton = R.id.fragment_onboarding_skip_button

    fun skipPresentation() = step("Пропускаем презентацию") {
        clickById(skipButton)
        allureScreenshot("ss_step_skipPresentation", 90, 1.0f)
    }

    companion object {
        inline operator fun invoke(block: OnboardingPage.() -> Unit) = OnboardingPage().block()
    }
}