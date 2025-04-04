package com.summon

/**
 * Utility functions for the Text component.
 */

/**
 * Creates a heading text at level 1 (h1 equivalent).
 * @param text The text content
 * @param modifier Additional styling modifiers
 * @param ariaLevel Optional ARIA level for accessibility
 */
fun h1(
    text: String, 
    modifier: Modifier = Modifier(),
    ariaLevel: Int? = null
): Text = Text(
    text = text,
    modifier = modifier
        .fontSize("2rem")
        .fontWeight("bold")
        .margin("0.67em 0"),
    lineHeight = "1.2",
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
    role = "heading",
    ariaLabel = ariaLevel?.let { "Heading level $it" }
)

/**
 * Creates a heading text at level 2 (h2 equivalent).
 * @param text The text content
 * @param modifier Additional styling modifiers
 * @param ariaLevel Optional ARIA level for accessibility
 */
fun h2(
    text: String, 
    modifier: Modifier = Modifier(),
    ariaLevel: Int? = null
): Text = Text(
    text = text,
    modifier = modifier
        .fontSize("1.5rem")
        .fontWeight("bold")
        .margin("0.83em 0"),
    lineHeight = "1.2",
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
    role = "heading",
    ariaLabel = ariaLevel?.let { "Heading level $it" }
)

/**
 * Creates a heading text at level 3 (h3 equivalent).
 * @param text The text content
 * @param modifier Additional styling modifiers
 * @param ariaLevel Optional ARIA level for accessibility
 */
fun h3(
    text: String, 
    modifier: Modifier = Modifier(),
    ariaLevel: Int? = null
): Text = Text(
    text = text,
    modifier = modifier
        .fontSize("1.17rem")
        .fontWeight("bold")
        .margin("1em 0"),
    lineHeight = "1.2",
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
    role = "heading",
    ariaLabel = ariaLevel?.let { "Heading level $it" }
)

/**
 * Creates a paragraph text (p equivalent).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun paragraph(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier.margin("1em 0"),
    lineHeight = "1.5",
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif"
)

/**
 * Creates a caption text (smaller, secondary text).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun caption(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier
        .fontSize("0.85rem")
        .color("#666"),
    lineHeight = "1.4",
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif"
)

/**
 * Creates a label text (for form fields or similar).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun label(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier
        .fontSize("0.9rem")
        .fontWeight("500")
        .margin("0 0 0.5em 0"),
    lineHeight = "1.4",
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif"
)

/**
 * Creates a monospace text (for code or similar).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun monospace(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier
        .background("#f5f5f5")
        .padding("0.2em 0.4em")
        .borderRadius("3px")
        .fontSize("0.9em"),
    fontFamily = "SFMono-Regular, Consolas, 'Liberation Mono', Menlo, monospace",
    lineHeight = "1.4"
)

/**
 * Creates an error text (for error messages).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun errorText(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier.color("#d32f2f"),
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
    lineHeight = "1.4",
    role = "alert"
)

/**
 * Creates a success text (for success messages).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun successText(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier.color("#2e7d32"),
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
    lineHeight = "1.4",
    role = "status"
)

/**
 * Creates an emphasized text (italicized).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun emphasizedText(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = Modifier(modifier.styles + ("font-style" to "italic")),
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif"
)

/**
 * Creates a strong text (bold).
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun strongText(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier.fontWeight("bold"),
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif"
)

/**
 * Creates a quote/blockquote text.
 * @param text The text content
 * @param modifier Additional styling modifiers
 */
fun quoteText(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = Modifier(
        modifier.styles + 
        ("border-left" to "4px solid #e0e0e0") +
        ("padding-left" to "1em") +
        ("font-style" to "italic") +
        ("margin" to "1em 0")
    ),
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
    lineHeight = "1.5"
)

/**
 * Creates a truncated text with ellipsis after the specified number of lines.
 * @param text The text content
 * @param maxLines Maximum number of lines to display before truncating
 * @param modifier Additional styling modifiers
 */
fun truncatedText(text: String, maxLines: Int, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = modifier,
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
    overflow = "ellipsis",
    maxLines = maxLines
)

/**
 * Creates a keyboard-like text for keyboard shortcuts.
 * @param text The text content (usually a keyboard shortcut like "Ctrl+C")
 * @param modifier Additional styling modifiers
 */
fun keyboardText(text: String, modifier: Modifier = Modifier()): Text = Text(
    text = text,
    modifier = Modifier(
        modifier.styles + 
        ("background-color" to "#f5f5f5") +
        ("padding" to "0.2em 0.4em") +
        ("border" to "1px solid #d0d0d0") +
        ("border-radius" to "3px") +
        ("font-size" to "0.9em")
    ),
    fontFamily = "SFMono-Regular, Consolas, monospace",
    lineHeight = "1.4"
)

/**
 * Creates a badge-like text.
 * @param text The text content
 * @param color Background color of the badge
 * @param textColor Text color for the badge
 * @param modifier Additional styling modifiers
 */
fun badgeText(
    text: String, 
    color: String = "#e0e0e0", 
    textColor: String = "#333333",
    modifier: Modifier = Modifier()
): Text = Text(
    text = text,
    modifier = Modifier(
        modifier.styles + 
        ("background-color" to color) +
        ("color" to textColor) +
        ("padding" to "0.25em 0.6em") +
        ("border-radius" to "1em") +
        ("font-size" to "0.75em") +
        ("font-weight" to "500")
    ),
    fontFamily = "system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif"
)

/**
 * Creates a screen reader only text (visually hidden but available to screen readers).
 * @param text The text content
 */
fun screenReaderText(text: String): Text = Text(
    text = text,
    modifier = Modifier(mapOf(
        "position" to "absolute",
        "width" to "1px",
        "height" to "1px",
        "padding" to "0",
        "margin" to "-1px",
        "overflow" to "hidden",
        "clip" to "rect(0, 0, 0, 0)",
        "white-space" to "nowrap",
        "border" to "0"
    ))
) 