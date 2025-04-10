package code.yousef.summon.modifier

import code.yousef.summon.runtime.PlatformRendererProvider
import code.yousef.summon.runtime.PlatformRenderer

/**
 * Extension functions for Accessibility Modifiers
 * These add ARIA attributes through HTML attributes applied to elements
 */

/**
 * Creates a copy of this Modifier with an additional HTML attribute.
 * We use a special "__attr:" prefix to distinguish attributes from CSS properties.
 */
fun Modifier.attribute(name: String, value: String): Modifier {
    return style("__attr:$name", value)
}

/**
 * Removes an attribute from the Modifier
 */
fun Modifier.removeAttribute(name: String): Modifier {
    val attributeKey = "__attr:$name"
    if (!styles.containsKey(attributeKey)) return this
    return Modifier(styles.filterKeys { it != attributeKey })
}

/**
 * Sets the ARIA role attribute
 */
fun Modifier.role(value: String): Modifier = attribute("role", value)

/**
 * Sets the aria-label attribute
 */
fun Modifier.ariaLabel(value: String): Modifier = attribute("aria-label", value)

/**
 * Sets the aria-labelledby attribute
 */
fun Modifier.ariaLabelledBy(value: String): Modifier = attribute("aria-labelledby", value)

/**
 * Sets the aria-describedby attribute
 */
fun Modifier.ariaDescribedBy(value: String): Modifier = attribute("aria-describedby", value)

/**
 * Sets the aria-hidden attribute
 */
fun Modifier.ariaHidden(value: Boolean): Modifier = attribute("aria-hidden", value.toString())

/**
 * Sets the aria-expanded attribute
 */
fun Modifier.ariaExpanded(value: Boolean): Modifier = attribute("aria-expanded", value.toString())

/**
 * Sets the aria-pressed attribute
 */
fun Modifier.ariaPressed(value: Boolean): Modifier = attribute("aria-pressed", value.toString())

/**
 * Sets the aria-checked attribute
 */
fun Modifier.ariaChecked(value: Boolean): Modifier = attribute("aria-checked", value.toString())

/**
 * Sets the aria-checked attribute with a custom value (for "mixed" state)
 */
fun Modifier.ariaChecked(value: String): Modifier = attribute("aria-checked", value)

/**
 * Sets the aria-selected attribute
 */
fun Modifier.ariaSelected(value: Boolean): Modifier = attribute("aria-selected", value.toString())

/**
 * Sets the aria-disabled attribute
 */
fun Modifier.ariaDisabled(value: Boolean): Modifier = attribute("aria-disabled", value.toString())

/**
 * Sets the aria-invalid attribute
 */
fun Modifier.ariaInvalid(value: Boolean): Modifier = attribute("aria-invalid", value.toString())

/**
 * Sets the aria-invalid attribute with a custom value (like "grammar")
 */
fun Modifier.ariaInvalid(value: String): Modifier = attribute("aria-invalid", value)

/**
 * Sets the aria-required attribute
 */
fun Modifier.ariaRequired(value: Boolean): Modifier = attribute("aria-required", value.toString())

/**
 * Sets the aria-current attribute
 */
fun Modifier.ariaCurrent(value: String): Modifier = attribute("aria-current", value)

/**
 * Sets the aria-live attribute
 */
fun Modifier.ariaLive(value: String): Modifier = attribute("aria-live", value)

/**
 * Sets the tabindex attribute
 */
fun Modifier.tabIndex(value: Int): Modifier = attribute("tabindex", value.toString())

/**
 * Gets a style property value or null if not present
 */
fun Modifier.getStyle(name: String): String? = styles[name]

/**
 * Gets an attribute value or null if not present
 */
fun Modifier.getAttribute(name: String): String? = styles["__attr:$name"]

/**
 * Checks if a style property exists
 */
fun Modifier.hasStyle(name: String): Boolean = styles.containsKey(name)

/**
 * Checks if an attribute exists
 */
fun Modifier.hasAttribute(name: String): Boolean = styles.containsKey("__attr:$name")

/**
 * Adds an aria-controls attribute to the element.
 * This attribute indicates which element is controlled by the current element.
 *
 * @param id The ID of the controlled element.
 */
fun Modifier.ariaControls(id: String): Modifier = attribute("aria-controls", id)

/**
 * Adds an aria-haspopup attribute to the element.
 * This attribute indicates whether the element has a popup context menu or dialog.
 *
 * @param value True if the element has a popup.
 */
fun Modifier.ariaHasPopup(value: Boolean = true): Modifier = attribute("aria-haspopup", value.toString())

/**
 * Adds an aria-busy attribute to the element.
 * This attribute indicates that an element is being modified and assistive technologies may want
 * to wait until the modifications are complete before exposing them to the user.
 *
 * @param value True if the element is currently being modified.
 */
fun Modifier.ariaBusy(value: Boolean = true): Modifier = attribute("aria-busy", value.toString())

/**
 * Makes an element focusable but not in the tab order.
 * Equivalent to setting tabindex="-1".
 */
fun Modifier.focusable(): Modifier = tabIndex(-1)

/**
 * Makes an element focusable and in the tab order.
 * Equivalent to setting tabindex="0".
 */
fun Modifier.tabbable(): Modifier = tabIndex(0)

/**
 * Marks an element as disabled and not focusable.
 * Adds both the disabled attribute and aria-disabled.
 */
fun Modifier.disabled(): Modifier = 
    attribute("disabled", "")
        .ariaDisabled(true)
        .tabIndex(-1)

/**
 * Marks an element for autofocus.
 * The element will be focused when it's rendered.
 */
fun Modifier.autoFocus(): Modifier = attribute("autofocus", "") 
