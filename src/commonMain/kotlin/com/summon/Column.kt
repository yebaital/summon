package com.summon

import kotlinx.html.DIV
import kotlinx.html.TagConsumer
import kotlinx.html.div
import kotlinx.html.style

/**
 * A layout composable that places its children in a vertical sequence.
 * @param modifier The modifier to apply to this layout
 * @param content The list of composables to place in the column
 */
data class Column(
    val modifier: Modifier = Modifier(),
    val content: List<Composable>
) : Composable {
    /**
     * Renders this Column composable using the platform-specific renderer.
     * @param receiver TagConsumer to render to
     * @return The TagConsumer for method chaining
     */
    override fun <T> compose(receiver: T): T {
        if (receiver is TagConsumer<*>) {
            @Suppress("UNCHECKED_CAST")
            return getPlatformRenderer().renderColumn(this, receiver as TagConsumer<T>)
        }
        return receiver
    }
} 