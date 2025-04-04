package com.summon

import kotlinx.html.TagConsumer

/**
 * Helper function to simplify composing with TagConsumer.
 * This is used for fluent code in JS platform.
 */
fun <T> composeToConsumer(composable: Composable, consumer: TagConsumer<T>): TagConsumer<T> {
    composable.compose(consumer)
    return consumer
} 