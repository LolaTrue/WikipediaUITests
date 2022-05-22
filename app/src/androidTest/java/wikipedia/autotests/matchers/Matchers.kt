package wikipedia.autotests.matchers

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher


fun getTextColor(matcher: Matcher<View>): Int {
    var colorOut: Int = Color.BLACK

    onView(matcher).perform(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(AppCompatTextView::class.java)
        }

        override fun getDescription(): String {
            return "getting text color from a TextView"
        }

        override fun perform(uiController: UiController, view: View) {
            val tv = view as TextView
            colorOut = tv.currentTextColor
        }
    })
    return colorOut
}

fun getTopHintColor(matcher: Matcher<View>): Int {
    var colorOut: Int = Color.BLACK

    onView(matcher).perform(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(TextInputLayout::class.java)
        }

        override fun getDescription(): String {
            return "getting text color from a TextView"
        }

        override fun perform(uiController: UiController, view: View) {
            val tv = view as TextInputLayout
            colorOut = tv.errorCurrentTextColors
        }
    })
    return colorOut
}

fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                ?:
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}