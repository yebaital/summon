package code.yousef.summon

import code.yousef.summon.components.layout.Card
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

/**
 * Sets up a JavaScript click handler for the Card component.
 * This extension function is called from JsPlatformRenderer.
 *
 * @param cardId The ID of the card element in the DOM
 */
fun Card.setupJsClickHandler(cardId: String) {
    // Get the card element from the DOM
    val element = document.getElementById(cardId) as? HTMLElement ?: return

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