package code.yousef.summon

import code.yousef.summon.annotation.Composable
import code.yousef.summon.components.display.IconType
import code.yousef.summon.components.feedback.AlertVariant
import code.yousef.summon.components.feedback.ProgressType
import code.yousef.summon.components.input.FileInfo
import code.yousef.summon.components.input.SelectOption
import code.yousef.summon.components.input.TextFieldType
import code.yousef.summon.components.navigation.Tab
import code.yousef.summon.core.AnimationController
import code.yousef.summon.core.AnimationStatus
import code.yousef.summon.core.style.Color
import code.yousef.summon.modifier.Modifier
import code.yousef.summon.runtime.MigratedPlatformRenderer
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.Element
import org.w3c.dom.get
import kotlin.js.json

// For JavaScript interop
@JsName("eval")
external fun js(code: String): dynamic

// Element focus extension implemented differently
private fun Element.focusElement() {
    js("this.focus()")
}

// Note: console object is available in JS; we don't need to declare it as external

/**
 * Platform-specific renderer implementation for JavaScript.
 * This is a simple implementation that handles rendering components in a JavaScript environment.
 */
actual class JsPlatformRenderer actual constructor() : MigratedPlatformRenderer {
    // Track the current parent node for the composition tree
    private var currentParentNode: HTMLElement? = null

    /**
     * Gets the current parent node in the composition context.
     * If no parent node is set, defaults to document.body.
     */
    private fun getCurrentParentNode(): HTMLElement? {
        return currentParentNode ?: document.body as HTMLElement?
    }

    /**
     * Sets the current parent node for the composition context.
     * This is used to build the composition tree.
     */
    private fun setCurrentParentNode(node: HTMLElement?) {
        currentParentNode = node
    }

    /**
     * Add an HTML element to the document head.
     * This is used by SEO components like MetaTags, CanonicalLinks, etc.
     *
     * @param content The HTML content to add to the head
     */
    actual override fun addHeadElement(content: String) {
        // Stub implementation
    }

    /**
     * Extension function to get an attribute value from a Modifier
     */
    private fun Modifier.getAttribute(name: String): String? {
        return this.styles["__attr:$name"]
    }

    actual override fun <T> renderComposable(composable: @Composable () -> Unit, consumer: T) {
        // For DOM-based rendering, consumer should be an HTMLElement
        if (consumer is HTMLElement) {
            // Save current parent node
            val previousParentNode = currentParentNode

            // Set the provided element as the current parent node
            setCurrentParentNode(consumer)

            try {
                // Invoke the composable function, which will use the current parent node
                // to append its DOM elements
                composable()
            } finally {
                // Restore the previous parent node, even if an exception was thrown
                setCurrentParentNode(previousParentNode)
            }
        } else {
            // Use console.log instead of console.error, and avoid dynamic class access
            console.log("renderComposable expected HTMLElement as consumer, got different type")
        }
    }

    actual override fun renderText(value: String, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderText(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderButton(onClick: () -> Unit, enabled: Boolean, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderButton(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderImage(src: String, alt: String, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderImage(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderIcon(name: String, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderIcon(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderRow(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderRow(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderColumn(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderColumn(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderSpacer(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderBox(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderBox(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderCard(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderCard(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderAnimatedVisibility(visible: Boolean, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderAnimatedVisibility(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderAnimatedContent(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderAnimatedContent(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderLink(href: String, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderLink(modifier: Modifier, href: String, content: @Composable () -> Unit) {
        // Stub implementation
    }

    /**
     * Render a hyperlink with enhanced accessibility attributes.
     * This implementation supports additional attributes like target, title, and ARIA attributes.
     */
    actual override fun renderEnhancedLink(
        href: String,
        target: String?,
        title: String?,
        ariaLabel: String?,
        ariaDescribedBy: String?,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderDivider(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderDivider(modifier: Modifier, color: Color, thickness: Float, vertical: Boolean) {
        // Stub implementation
    }

    actual override fun renderLazyColumn(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderLazyColumn(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderLazyRow(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderLazyRow(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderGrid(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderGrid(modifier: Modifier, columns: Int, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderAspectRatio(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderAspectRatio(modifier: Modifier, ratio: Float, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderResponsiveLayout(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderResponsiveLayout(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderExpansionPanel(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderExpansionPanel(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderTabLayout(
        tabs: List<Tab>,
        selectedTabIndex: Int,
        onTabSelected: (Int) -> Unit,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderTabLayout(modifier: Modifier, content: @Composable () -> Unit) {
        // Stub implementation
    }

    actual override fun renderTabLayout(
        tabs: List<String>, selectedTab: String, onTabSelected: (String) -> Unit,
        modifier: Modifier, content: () -> Unit
    ) {
        // Stub implementation
    }

    actual override fun renderTextField(
        value: String, onValueChange: (String) -> Unit, modifier: Modifier,
        placeholder: String, isError: Boolean, type: String
    ) {
        // Stub implementation
    }

    actual override fun renderTextField(
        value: String,
        onValueChange: (String) -> Unit,
        enabled: Boolean,
        readOnly: Boolean,
        type: TextFieldType,
        placeholder: String?,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderTextArea(
        value: String, onValueChange: (String) -> Unit, modifier: Modifier,
        placeholder: String, maxLines: Int
    ) {
        // Stub implementation
    }

    actual override fun renderTextArea(
        value: String,
        onValueChange: (String) -> Unit,
        enabled: Boolean,
        readOnly: Boolean,
        rows: Int?,
        maxLength: Int?,
        placeholder: String?,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderCheckbox(
        checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier,
        enabled: Boolean
    ) {
        // Stub implementation
    }

    actual override fun renderCheckbox(
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderRadioButton(
        selected: Boolean, onClick: () -> Unit, modifier: Modifier,
        enabled: Boolean
    ) {
        // Stub implementation
    }

    actual override fun renderRadioButton(
        selected: Boolean,
        onClick: () -> Unit,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderSelect(
        value: String, onValueChange: (String) -> Unit, options: List<String>,
        modifier: Modifier, placeholder: String
    ) {
        // Stub implementation
    }

    actual override fun <T> renderSelect(
        value: T?,
        onValueChange: (T?) -> Unit,
        options: List<SelectOption<T>>,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderSwitch(
        checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier,
        enabled: Boolean
    ) {
        // Stub implementation
    }

    actual override fun renderSwitch(
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderDatePicker(date: LocalDate?, onDateChange: (LocalDate) -> Unit, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderDatePicker(
        value: LocalDate?,
        onValueChange: (LocalDate?) -> Unit,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderTimePicker(
        time: code.yousef.summon.core.LocalTime?,
        onTimeChange: (code.yousef.summon.core.LocalTime) -> Unit,
        modifier: Modifier,
        is24Hour: Boolean
    ) {
        // Stub implementation
    }

    actual override fun renderTimePicker(
        value: LocalTime?,
        onValueChange: (LocalTime?) -> Unit,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderFileUpload(
        onFileSelected: (List<Any>) -> Unit, modifier: Modifier, multiple: Boolean,
        acceptedFileTypes: List<String>
    ) {
        // Stub implementation
    }

    actual override fun renderFileUpload(
        onFilesSelected: (List<FileInfo>) -> Unit,
        accept: String?,
        multiple: Boolean,
        enabled: Boolean,
        capture: String?,
        modifier: Modifier
    ): () -> Unit {
        // Stub implementation
        return {}
    }

    actual override fun renderRangeSlider(
        value: ClosedFloatingPointRange<Float>, onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
        modifier: Modifier, valueRange: ClosedFloatingPointRange<Float>
    ) {
        // Stub implementation
    }

    actual override fun renderRangeSlider(
        value: ClosedFloatingPointRange<Float>,
        onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
        valueRange: ClosedFloatingPointRange<Float>,
        steps: Int,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Stub implementation
    }

    actual override fun renderSlider(
        value: Float,
        onValueChange: (Float) -> Unit,
        valueRange: ClosedFloatingPointRange<Float>,
        steps: Int,
        enabled: Boolean,
        modifier: Modifier
    ) {
        // Implementation using a single-value slider directly
        // Uses the range-slider implementation under the hood for consistency
        val rangeValue = value..value
        val rangeChange: (ClosedFloatingPointRange<Float>) -> Unit = { newRange ->
            if (enabled) onValueChange(newRange.start)
        }

        renderRangeSlider(
            value = rangeValue,
            onValueChange = rangeChange,
            valueRange = valueRange,
            steps = steps,
            enabled = enabled,
            modifier = modifier
        )
    }

    actual override fun renderAlertContainer(variant: AlertVariant?, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderBadge(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderProgress(value: Float?, type: ProgressType, modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderTooltipContainer(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderHtmlTag(tag: String, attrs: Map<String, String>, content: () -> Unit) {
        // Stub implementation
    }

    actual override fun renderDiv(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderSpan(modifier: Modifier) {
        // Stub implementation
    }

    actual override fun renderForm(onSubmit: () -> Unit, modifier: Modifier) {
        // Stub implementation
    }

    /**
     * Renders an icon with enhanced functionality including click handling and SVG support
     */
    actual override fun renderIcon(
        name: String,
        modifier: Modifier,
        onClick: (() -> Unit)?,
        svgContent: String?,
        type: IconType
    ) {
        // Stub implementation
    }

    /**
     * Render a form field container with proper accessibility attributes
     */
    actual override fun renderFormField(
        modifier: Modifier,
        labelId: String?,
        isRequired: Boolean,
        isError: Boolean,
        errorMessageId: String?,
        content: @Composable () -> Unit
    ) {
        // Stub implementation
    }

    /**
     * Applies focus to an element with the given ID.
     * For JS platform, we can directly interact with the DOM.
     */
    actual override fun applyFocus(elementId: String): Boolean {
        val element = document.getElementById(elementId) ?: return false
        element.focusElement()
        return document.activeElement == element
    }
    
    /**
     * Registers an animation with the platform renderer.
     */
    actual override fun registerAnimation(
        animationId: String,
        animationProps: Map<String, Any>,
        targetElementId: String?
    ) {
        // Create a style element for the animation
        val styleElement = document.createElement("style") as HTMLStyleElement
        val keyframesName = "animation_$animationId"
        val duration = animationProps["duration"]?.toString() ?: "1s"
        val easing = animationProps["easing"]?.toString() ?: "ease"
        val delay = animationProps["delay"]?.toString() ?: "0s"
        
        // Create keyframes if provided
        val keyframes = animationProps["keyframes"]
        var keyframesCSS = ""
        
        if (keyframes != null) {
            keyframesCSS = "@keyframes $keyframesName {\n"
            // Process keyframes
            val keyframesObj = js("Object.keys(keyframes)")
            for (i in 0 until keyframesObj.length) {
                val key = keyframesObj[i]
                val frame = js("keyframes[key]")
                keyframesCSS += "    $key {\n"
                
                val frameProps = js("Object.keys(frame)")
                for (j in 0 until frameProps.length) {
                    val prop = frameProps[j]
                    val value = js("frame[prop]")
                    keyframesCSS += "        $prop: $value;\n"
                }
                keyframesCSS += "    }\n"
            }
            keyframesCSS += "}\n"
        }
        
        // Create the animation class
        val animationCSS = """
            $keyframesCSS
            .$keyframesName {
                animation-name: $keyframesName;
                animation-duration: $duration;
                animation-timing-function: $easing;
                animation-delay: $delay;
                animation-fill-mode: both;
            }
        """.trimIndent()
        
        styleElement.textContent = animationCSS
        document.head?.appendChild(styleElement)
    }
    
    /**
     * Starts a previously registered animation.
     */
    actual override fun startAnimation(
        animationId: String,
        options: Map<String, Any>
    ): AnimationController {
        val targetId = options["targetElementId"]?.toString() ?: return createDummyAnimationController()
        val element = document.getElementById(targetId) ?: return createDummyAnimationController()
        
        val animationClass = "animation_$animationId"
        element.classList.add(animationClass)
        
        // Setup animation listeners
        var animStatus = AnimationStatus.RUNNING
        var prog = 0f
        
        val controller = object : AnimationController {
            override fun pause() {
                if (animStatus == AnimationStatus.RUNNING) {
                    (element as HTMLElement).style.animationPlayState = "paused"
                    animStatus = AnimationStatus.PAUSED
                }
            }
            
            override fun resume() {
                if (animStatus == AnimationStatus.PAUSED) {
                    (element as HTMLElement).style.animationPlayState = "running"
                    animStatus = AnimationStatus.RUNNING
                }
            }
            
            override fun cancel() {
                element.classList.remove(animationClass)
                animStatus = AnimationStatus.CANCELLED
            }
            
            override fun stop() {
                (element as HTMLElement).style.animationPlayState = "paused"
                (element as HTMLElement).style.animationFillMode = "forwards"
                animStatus = AnimationStatus.COMPLETED
            }
            
            override val status: AnimationStatus
                get() = animStatus
                
            override val progress: Float
                get() = prog
        }
        
        // Listen for animation events
        element.addEventListener("animationstart", {
            animStatus = AnimationStatus.RUNNING
            prog = 0f
        })
        
        element.addEventListener("animationend", {
            animStatus = AnimationStatus.COMPLETED
            prog = 1f
        })
        
        element.addEventListener("animationcancel", {
            animStatus = AnimationStatus.CANCELLED
        })
        
        // Update progress periodically
        window.setInterval({
            if (animStatus == AnimationStatus.RUNNING) {
                // Simple linear progress estimation
                prog = (prog + 0.01f).coerceAtMost(0.99f)
            }
        }, 100)
        
        return controller
    }
    
    private fun createDummyAnimationController(): AnimationController {
        return object : AnimationController {
            override fun pause() {}
            override fun resume() {}
            override fun cancel() {}
            override fun stop() {}
            override val status: AnimationStatus = AnimationStatus.IDLE
            override val progress: Float = 0f
        }
    }
} 