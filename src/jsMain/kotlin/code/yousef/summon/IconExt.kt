package code.yousef.summon

import code.yousef.summon.components.display.Icon
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

/**
 * Sets up a JavaScript click handler for the Icon component.
 * This extension function is called from JsPlatformRenderer.
 *
 * @param iconId The ID of the icon element in the DOM
 */
fun Icon.setupJsClickHandler(iconId: String) {
    // Get the icon element from the DOM
    val element = document.getElementById(iconId) as? HTMLElement ?: return

    // Add click event listener
    element.onclick = { event ->
        // Call the onClick handler if available
        onClick?.invoke()
        // Prevent default action and stop propagation
        event.preventDefault()
        event.stopPropagation()
    }

    // Add keyboard event listener for accessibility (Enter and Space keys)
    element.onkeydown = { event ->
        if (event.key == "Enter" || event.key == " ") {
            // Call the onClick handler if available
            onClick?.invoke()
            // Prevent default action and stop propagation
            event.preventDefault()
            event.stopPropagation()
        }
    }
} 