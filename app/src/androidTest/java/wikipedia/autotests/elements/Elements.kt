package wikipedia.autotests.elements

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers
import org.wikipedia.views.PlainPasteEditText

object Elements {

    fun clickById(id: Int) {
        onView(withId(id)).perform(click())
    }

    fun clickByContentDescription(contentDescription: String) {
        onView(withContentDescription(contentDescription)).perform(click())
    }

    fun clickByText(text: String) {
        onView(withText(text)).perform(click())
    }

    fun fillTextField(field: String, text: String) {
        onView(
            Matchers.allOf(
                Matchers.instanceOf(PlainPasteEditText::class.java),
                withHint(field)
            )
        )
            .perform(ViewActions.typeText(text))
    }
}