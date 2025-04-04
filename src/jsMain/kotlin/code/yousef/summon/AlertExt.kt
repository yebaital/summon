package code.yousef.summon

import code.yousef.summon.components.feedback.Alert
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

/**
 * Sets up a JavaScript action handler for the Alert component.
 * This extension function is called from JsPlatformRenderer.
 *
 * @param actionId The ID of the action button element in the DOM
 */
fun Alert.setupJsActionHandler(actionId: String) {
    // Get the action button element from the DOM
    val element = document.getElementById(actionId) as? HTMLElement ?: return

    // Add click event listener
    element.onclick = { event ->
        // Call the onAction handler if available
        onAction?.invoke()
        // Prevent default action and stop propagation
        event.preventDefault()
        event.stopPropagation()
    }

    // Add keyboard event listener for accessibility (Enter and Space keys)
    element.onkeydown = { event ->
        if (event.key == "Enter" || event.key == " ") {
            // Call the onAction handler if available
            onAction?.invoke()
            // Prevent default action and stop propagation
            event.preventDefault()
            event.stopPropagation()
        }
    }
}

/**
 * Sets up a JavaScript dismiss handler for the Alert component.
 * This extension function is called from JsPlatformRenderer.
 *
 * @param dismissId The ID of the dismiss button element in the DOM
 * @param alertId The ID of the alert element in the DOM
 */
fun Alert.setupJsDismissHandler(dismissId: String, alertId: String) {
    // Get the dismiss button element from the DOM
    val dismissButton = document.getElementById(dismissId) as? HTMLElement ?: return
    val alertElement = document.getElementById(alertId) as? HTMLElement ?: return

    // Add click event listener
    dismissButton.onclick = { event ->
        // Hide the alert
        alertElement.style.display = "none"

        // Call the onDismiss handler if available
        onDismiss?.invoke()

        // Prevent default action and stop propagation
        event.preventDefault()
        event.stopPropagation()
    }

    // Add keyboard event listener for accessibility (Enter and Space keys)
    dismissButton.onkeydown = { event ->
        if (event.key == "Enter" || event.key == " ") {
            // Hide the alert
            alertElement.style.display = "none"

            // Call the onDismiss handler if available
            onDismiss?.invoke()

            // Prevent default action and stop propagation
            event.preventDefault()
            event.stopPropagation()
        }
    }
} 