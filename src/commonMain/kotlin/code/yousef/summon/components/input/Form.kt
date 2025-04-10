package code.yousef.summon.components.input

import code.yousef.summon.components.LayoutComponent
import code.yousef.summon.core.Composable
import code.yousef.summon.core.PlatformRendererProvider
import code.yousef.summon.modifier.Modifier
import code.yousef.summon.runtime.PlatformRendererProviderLegacy.getRenderer
import kotlinx.html.TagConsumer

/**
 * A composable that represents an HTML form.
 * It manages form state, validation, and submission.
 *
 * @param content The form content containing input fields and submit buttons
 * @param onSubmit Callback that is invoked when the form is submitted
 * @param modifier The modifier to apply to this composable
 */
class Form(
    val content: List<Composable>,
    val onSubmit: (Map<String, String>) -> Unit = {},
    val modifier: Modifier = Modifier()
) : Composable, LayoutComponent {
    private val formFields = mutableListOf<FormField>()

    /**
     * Base interface for form fields
     */
    interface FormField {
        val label: String?
        val value: String
        fun validate(): Boolean
    }

    /**
     * Registers a form field with this form.
     * This allows the form to track all input fields for validation and submission.
     */
    fun registerField(field: FormField) {
        formFields.add(field)
    }

    /**
     * Validates all form fields.
     * @return True if all fields are valid, false otherwise
     */
    fun validate(): Boolean {
        return formFields.all { it.validate() }
    }

    /**
     * Submits the form if validation passes.
     * @return True if form was submitted, false if validation failed
     */
    fun submit(): Boolean {
        if (validate()) {
            // Collect all form values
            val formData = formFields.associate { field ->
                (field.label ?: field.hashCode().toString()) to field.value
            }

            // Call the onSubmit callback
            onSubmit(formData)
            return true
        }
        return false
    }

    /**
     * Renders this Form composable using the platform-specific renderer.
     * @param receiver TagConsumer to render to
     * @return The TagConsumer for method chaining
     */
    override fun <T> compose(receiver: T): T {
        if (receiver is TagConsumer<*>) {
            @Suppress("UNCHECKED_CAST")
            val renderer = getRenderer()
            renderer.renderForm(
                onSubmit = { this.submit() },
                modifier = modifier
            )
        }
        return receiver
    }
} 