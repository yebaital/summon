package code.yousef.summon.integrations.quarkus

import code.yousef.summon.Composable
import code.yousef.summon.JvmPlatformRenderer
import code.yousef.summon.render
import io.quarkus.qute.EngineBuilder
import io.quarkus.qute.EvalContext
import io.quarkus.qute.NamespaceResolver
import io.quarkus.qute.ValueResolver
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.function.Consumer
import java.util.function.Supplier

/**
 * Integrates Summon components with Quarkus Qute templates.
 *
 * This extension allows Summon components to be used within Qute templates.
 * It registers a custom value resolver that renders Summon components to HTML.
 *
 * Usage:
 *
 * 1. In your Quarkus application, add a configuration class:
 *
 * ```kotlin
 * @Singleton
 * class QuteConfig {
 *     @Produces
 *     fun configureSummonQuteExtension(): Consumer<EngineBuilder> {
 *         return SummonQuteExtension()
 *     }
 * }
 * ```
 *
 * 2. In your Qute template, use Summon components:
 *
 * ```html
 * {#let myComponent=summon:component(com.example.MyComponent.create())}
 *   {myComponent}
 * {/let}
 * ```
 */
class SummonQuteExtension : Consumer<EngineBuilder> {
    private val renderer = JvmPlatformRenderer()
    private var config = Config()

    override fun accept(builder: EngineBuilder) {
        // First, register the 'summon' namespace
        builder.addNamespaceResolver(NamespaceResolver.builder("summon").build())
        
        // Now register a resolver for our specific functions within the summon namespace
        builder.addValueResolver(object : ValueResolver {
            override fun getPriority(): Int = 10

            override fun appliesTo(context: EvalContext): Boolean {
                // This resolver applies to operations on the 'summon' namespace
                return context.base != null && 
                      context.base.toString() == "summon" &&
                      (context.name == "component" || context.name == "isComponent" || 
                       context.name == "withContainer")
            }

            override fun resolve(context: EvalContext): CompletionStage<Any?> {
                try {
                    // Handle the component renderer method
                    if (context.name == "component") {
                        val args = context.params
                        if (args.isEmpty()) {
                            return CompletableFuture.completedFuture(null)
                        }
                        
                        if (args[0] !is Composable) {
                            return CompletableFuture.completedFuture(null)
                        }
                        
                        val component = args[0] as Composable
                        val html = renderer.render(component)
                        
                        // Apply configuration options
                        val processedHtml = if (config.includeComments) {
                            "<!-- BEGIN SUMMON COMPONENT -->\n$html\n<!-- END SUMMON COMPONENT -->"
                        } else {
                            html
                        }
                        
                        return CompletableFuture.completedFuture(processedHtml)
                    }
                    
                    // Handle the isComponent check method
                    if (context.name == "isComponent") {
                        val args = context.params
                        if (args.isEmpty()) {
                            return CompletableFuture.completedFuture(false)
                        }
                        
                        return CompletableFuture.completedFuture(args[0] is Composable)
                    }
                    
                    // Handle the withContainer helper method
                    if (context.name == "withContainer") {
                        val args = context.params
                        if (args.isEmpty() || args[0] !is Composable) {
                            return CompletableFuture.completedFuture(null)
                        }
                        
                        val component = args[0] as Composable
                        val html = renderer.render(component)
                        
                        // Get optional ID from args
                        val id = if (args.size > 1 && true) " id=\"${args[1]}\"" else ""
                        val className = if (args.size > 2 && true) " class=\"${args[2]}\"" else " class=\"summon-component\""
                        
                        val wrapped = "<div$id$className>$html</div>"
                        return CompletableFuture.completedFuture(wrapped)
                    }
                    
                    // Unknown function in the summon namespace
                    return CompletableFuture.completedFuture(null)
                } catch (e: Exception) {
                    // Log the error and return a descriptive error message
                    println("Error in SummonQuteExtension: ${e.message}")
                    e.printStackTrace()
                    return CompletableFuture.completedFuture(null)
                }
            }
        })
    }

    /**
     * Provides additional configuration options for customizing Summon rendering in Qute templates.
     */
    class Config {
        var usePrettyPrinting: Boolean = true
        var includeComments: Boolean = false

        /**
         * Sets whether to use pretty printing when rendering Summon components.
         */
        fun usePrettyPrinting(value: Boolean): Config {
            this.usePrettyPrinting = value
            return this
        }

        /**
         * Sets whether to include HTML comments indicating Summon component boundaries.
         */
        fun includeComments(value: Boolean): Config {
            this.includeComments = value
            return this
        }
    }

    companion object {
        /**
         * Creates a Summon Qute extension with the given configuration.
         */
        fun create(configure: Config.() -> Unit): SummonQuteExtension {
            val extension = SummonQuteExtension()
            extension.config = Config().apply(configure)
            return extension
        }

        /**
         * Helper method to render a Summon component to a string.
         */
        fun renderComponent(component: Composable): String {
            return JvmPlatformRenderer().render(component)
        }

        /**
         * Creates a value supplier for a Summon component that can be used in Qute templates.
         */
        fun templateValue(component: Composable): Supplier<String> {
            return Supplier { renderComponent(component) }
        }
    }
} 