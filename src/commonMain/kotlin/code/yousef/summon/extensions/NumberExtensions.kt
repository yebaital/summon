package code.yousef.summon.extensions

/**
 * Extensions for Number to provide CSS unit helpers
 */

/**
 * Converts a number to a CSS pixel value (e.g., 16.px -> "16px")
 */
val Number.px: String
    get() = "${this}px"

/**
 * Converts a number to a CSS rem value (e.g., 1.5.rem -> "1.5rem")
 */
val Number.rem: String
    get() = "${this}rem"

/**
 * Converts a number to a CSS em value (e.g., 1.2.em -> "1.2em")
 */
val Number.em: String
    get() = "${this}em"

/**
 * Converts a number to a CSS percentage value (e.g., 50.percent -> "50%")
 */
val Number.percent: String
    get() = "$this%"

/**
 * Converts a number to a CSS viewport width value (e.g., 100.vw -> "100vw")
 */
val Number.vw: String
    get() = "${this}vw"

/**
 * Converts a number to a CSS viewport height value (e.g., 100.vh -> "100vh")
 */
val Number.vh: String
    get() = "${this}vh"

/**
 * Converts a number to a CSS viewport min value (e.g., 50.vmin -> "50vmin")
 */
val Number.vmin: String
    get() = "${this}vmin"

/**
 * Converts a number to a CSS viewport max value (e.g., 50.vmax -> "50vmax")
 */
val Number.vmax: String
    get() = "${this}vmax" 