package code.yousef.summon.modifier

import code.yousef.summon.runtime.getPlatformRenderer

/**
 * Extension functions for Layout Modifiers
 * These are implemented to match the test expectations in LayoutModifierTest
 */

/**
 * Sets the minimum width of the element.
 */
fun Modifier.minWidth(value: String): Modifier =
    style("min-width", value)

/**
 * Sets the minimum height of the element.
 */
fun Modifier.minHeight(value: String): Modifier =
    style("min-height", value)

/**
 * Sets the maximum height of the element.
 */
fun Modifier.maxHeight(value: String): Modifier =
    style("max-height", value)

/**
 * Sets the maximum width of the element.
 */
fun Modifier.maxWidth(value: String): Modifier =
    style("max-width", value)

/**
 * Sets width to 100%.
 */
fun Modifier.fillMaxWidth(): Modifier =
    style("width", "100%")

/**
 * Sets the width of the element.
 */
fun Modifier.width(value: String): Modifier =
    style("width", value)

/**
 * Sets the height of the element.
 */
fun Modifier.height(value: String): Modifier =
    style("height", value)

/**
 * Sets padding on all sides.
 */
fun Modifier.padding(value: String): Modifier =
    style("padding", value)

/**
 * Sets different padding for horizontal and vertical.
 */
fun Modifier.padding(vertical: String, horizontal: String): Modifier =
    style("padding", "$vertical $horizontal")

/**
 * Sets padding for the top side of the element.
 */
fun Modifier.paddingTop(value: String): Modifier =
    style("padding-top", value)

/**
 * Sets padding for the right side of the element.
 */
fun Modifier.paddingRight(value: String): Modifier =
    style("padding-right", value)

/**
 * Sets padding for the bottom side of the element.
 */
fun Modifier.paddingBottom(value: String): Modifier =
    style("padding-bottom", value)

/**
 * Sets padding for the left side of the element.
 */
fun Modifier.paddingLeft(value: String): Modifier =
    style("padding-left", value)

/**
 * Sets margin for all sides.
 */
fun Modifier.margin(value: String): Modifier =
    style("margin", value)

/**
 * Sets margin for horizontal and vertical dimensions.
 */
fun Modifier.margin(vertical: String, horizontal: String): Modifier =
    style("margin", "$vertical $horizontal")

/**
 * Sets margin for all four sides individually.
 */
fun Modifier.margin(top: String, right: String, bottom: String, left: String): Modifier =
    style("margin", "$top $right $bottom $left")

/**
 * Sets the position type of the element.
 */
fun Modifier.position(value: String): Modifier =
    style("position", value)

/**
 * Sets the top position of the element.
 */
fun Modifier.top(value: String): Modifier =
    style("top", value)

/**
 * Sets the right position of the element.
 */
fun Modifier.right(value: String): Modifier =
    style("right", value)

/**
 * Sets the bottom position of the element.
 */
fun Modifier.bottom(value: String): Modifier =
    style("bottom", value)

/**
 * Sets the left position of the element.
 */
fun Modifier.left(value: String): Modifier =
    style("left", value)

/**
 * Sets the flex property (shorthand for flex-grow, flex-shrink, flex-basis).
 */
fun Modifier.flex(value: String): Modifier =
    style("flex", value)

/**
 * Sets the flex direction of the element.
 */
fun Modifier.flexDirection(value: String): Modifier =
    style("flex-direction", value)

/**
 * Sets the flex wrap property.
 */
fun Modifier.flexWrap(value: String): Modifier =
    style("flex-wrap", value)

/**
 * Sets the flex grow property.
 */
fun Modifier.flexGrow(value: Int): Modifier =
    style("flex-grow", value.toString())

/**
 * Sets the flex shrink property.
 */
fun Modifier.flexShrink(value: Int): Modifier =
    style("flex-shrink", value.toString())

/**
 * Sets the flex basis property.
 */
fun Modifier.flexBasis(value: String): Modifier =
    style("flex-basis", value)

/**
 * Sets the align-self property.
 */
fun Modifier.alignSelf(value: String): Modifier =
    style("align-self", value)

/**
 * Sets the align-content property.
 */
fun Modifier.alignContent(value: String): Modifier =
    style("align-content", value)

/**
 * Sets the justify-items property.
 */
fun Modifier.justifyItems(value: String): Modifier =
    style("justify-items", value)

/**
 * Sets the justify-self property.
 */
fun Modifier.justifySelf(value: String): Modifier =
    style("justify-self", value)

/**
 * Sets the display property.
 */
fun Modifier.display(value: String): Modifier =
    style("display", value)

/**
 * Sets the grid-template-columns property.
 */
fun Modifier.gridTemplateColumns(value: String): Modifier =
    style("grid-template-columns", value)

/**
 * Sets the grid-template-rows property.
 */
fun Modifier.gridTemplateRows(value: String): Modifier =
    style("grid-template-rows", value)

/**
 * Sets the grid-gap property.
 */
fun Modifier.gridGap(value: String): Modifier =
    style("grid-gap", value)

/**
 * Sets the grid-column-gap property.
 */
fun Modifier.gridColumnGap(value: String): Modifier =
    style("grid-column-gap", value)

/**
 * Sets the grid-row-gap property.
 */
fun Modifier.gridRowGap(value: String): Modifier =
    style("grid-row-gap", value)

/**
 * Sets the grid-area property.
 */
fun Modifier.gridArea(value: String): Modifier =
    style("grid-area", value)

/**
 * Sets the grid-column property.
 */
fun Modifier.gridColumn(value: String): Modifier =
    style("grid-column", value)

/**
 * Sets the grid-row property.
 */
fun Modifier.gridRow(value: String): Modifier =
    style("grid-row", value)

/**
 * Sets the z-index property.
 */
fun Modifier.zIndex(value: String): Modifier =
    style("z-index", value)

/**
 * Sets the overflow property.
 */
fun Modifier.overflow(value: String): Modifier =
    style("overflow", value)

/**
 * Sets the overflow-x property.
 */
fun Modifier.overflowX(value: String): Modifier =
    style("overflow-x", value)

/**
 * Sets the overflow-y property.
 */
fun Modifier.overflowY(value: String): Modifier =
    style("overflow-y", value)

/**
 * Sets the visibility property.
 */
fun Modifier.visibility(value: String): Modifier =
    style("visibility", value)

/**
 * Sets the border property.
 */
fun Modifier.border(width: String, style: String, color: String): Modifier =
    style("border", "$width $style $color")

/**
 * Sets just the border width property.
 */
fun Modifier.border(width: String): Modifier =
    style("border-width", width)

/**
 * Sets the border-radius property.
 */
fun Modifier.borderRadius(value: String): Modifier =
    style("border-radius", value)

/**
 * Sets the border-left property.
 */
fun Modifier.borderLeft(width: String, style: String, color: String): Modifier =
    style("border-left", "$width $style $color")

/**
 * Sets the font-size property.
 */
fun Modifier.fontSize(value: String): Modifier =
    style("font-size", value)

/**
 * Sets the font-weight property.
 */
fun Modifier.fontWeight(value: String): Modifier =
    style("font-weight", value)

/**
 * Sets the color property.
 */
fun Modifier.color(value: String): Modifier =
    style("color", value)

/**
 * Sets the cursor property.
 */
fun Modifier.cursor(value: String): Modifier =
    style("cursor", value)

/**
 * Sets the onClick event handler.
 */
fun Modifier.onClick(handler: () -> Unit): Modifier =
    // This would be implemented differently depending on platform
    // For now just return this as a placeholder
    this 
