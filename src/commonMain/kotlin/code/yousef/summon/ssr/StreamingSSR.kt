package code.yousef.summon.ssr

import code.yousef.summon.annotation.Composable
import code.yousef.summon.runtime.MigratedPlatformRenderer
import code.yousef.summon.runtime.getPlatformRenderer
import code.yousef.summon.runtime.CommonComposer
import code.yousef.summon.runtime.ComposeManagerContext
import code.yousef.summon.runtime.LocalPlatformRenderer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.html.stream.createHTML
import kotlinx.coroutines.delay

/**
 * Implementation of streaming server-side rendering for Summon components
 * This allows rendering large pages as a stream of HTML chunks
 */
class StreamingRenderer(
    private val hydrationSupport: HydrationSupport = StandardHydrationSupport(),
    private val platformRenderer: MigratedPlatformRenderer = getPlatformRenderer(),
    private val chunkSize: Int = 4096
) : StreamingServerSideRenderer {
    /**
     * Render a composable to an HTML stream
     *
     * @param composable The composable to render
     * @param context Optional rendering context with additional metadata
     * @return Flow of HTML chunks
     */
    override fun renderStream(composable: @Composable () -> Unit, context: RenderContext): Flow<String> = flow {
        // First, emit the HTML header
        emit(generateHtmlHeader(context))

        // Then, emit the body opening tag and root div
        emit("<body>\n<div id=\"root\" data-summon-hydration=\"root\">")

        // Render the component in chunks using a special chunking renderer
        // In a real implementation, this would use a more sophisticated chunking approach
        // For this example, we'll simulate chunking by splitting a full render
        val fullHtml = renderToString(composable)
        val chunks = fullHtml.chunked(chunkSize)

        // Emit each chunk
        chunks.forEach { chunk ->
            emit(chunk)
        }

        // Emit the closing tags and scripts
        emit("</div>")

        // Add initial state script if needed
        if (context.initialState.isNotEmpty()) {
            emit(generateInitialStateScript(context))
        }

        // Add hydration script if needed
        if (context.enableHydration) {
            emit("<script src=\"/summon-hydration.js\"></script>")

            // Generate hydration data
            val hydrationData = hydrationSupport.generateHydrationData(composable, HydrationStrategy.FULL)
            
            // Add the hydration markers
            emit(
                """
                <script type="application/json" id="summon-hydration-data" style="display:none">
                    $hydrationData
                </script>
            """.trimIndent()
            )
        }

        // Close the body and html tags
        emit("\n</body>\n</html>")
    }

    /**
     * Generates the HTML header section
     */
    private fun generateHtmlHeader(context: RenderContext): String {
        val seo = context.seoMetadata

        val metaTags = buildMetaTags(seo, context.metadata)
        val openGraphTags = buildOpenGraphTags(seo.openGraph)
        val twitterCardTags = buildTwitterCardTags(seo.twitterCard)
        val structuredDataScript = if (seo.structuredData.isNotEmpty()) {
            """<script type="application/ld+json">${seo.structuredData}</script>"""
        } else ""

        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                $metaTags
                $openGraphTags
                $twitterCardTags
                $structuredDataScript
                ${generateCanonicalLink(seo.canonical)}
                <title>${seo.title}</title>
                <link rel="stylesheet" href="/summon.css">
                <script>
                    // Script for progressive rendering support
                    document.addEventListener('DOMContentLoaded', () => {
                        console.log('Summon Streaming SSR completed');
                    });
                </script>
            </head>
        """.trimIndent()
    }

    /**
     * Renders a composable to a string
     */
    internal fun renderToString(composable: @Composable () -> Unit): String {
        // Using createHTML from kotlinx.html to render the component
        val composer = CommonComposer()
        return createHTML().let { consumer ->
            ComposeManagerContext.withComposer(composer) {
                platformRenderer.renderComposable(composable, consumer)
            }
            consumer.finalize()
        }
    }

    /**
     * Builds meta tags from SEO metadata
     */
    private fun buildMetaTags(seo: SeoMetadata, additionalMetadata: Map<String, String>): String {
        val tags = mutableListOf<String>()

        if (seo.description.isNotEmpty()) {
            tags.add("""<meta name="description" content="${escapeHtml(seo.description)}">""")
        }

        if (seo.keywords.isNotEmpty()) {
            tags.add("""<meta name="keywords" content="${escapeHtml(seo.keywords.joinToString(", "))}">""")
        }

        tags.add("""<meta name="robots" content="${escapeHtml(seo.robots)}">""")

        // Add custom meta tags
        seo.customMetaTags.forEach { (name, content) ->
            tags.add("""<meta name="${escapeHtml(name)}" content="${escapeHtml(content)}">""")
        }

        // Add additional metadata
        additionalMetadata.forEach { (name, content) ->
            tags.add("""<meta name="${escapeHtml(name)}" content="${escapeHtml(content)}">""")
        }

        return tags.joinToString("\n")
    }

    /**
     * Builds OpenGraph tags from OpenGraph metadata
     */
    private fun buildOpenGraphTags(og: OpenGraphMetadata): String {
        val tags = mutableListOf<String>()

        if (og.title.isNotEmpty()) {
            tags.add("""<meta property="og:title" content="${escapeHtml(og.title)}">""")
        }

        if (og.description.isNotEmpty()) {
            tags.add("""<meta property="og:description" content="${escapeHtml(og.description)}">""")
        }

        if (og.type.isNotEmpty()) {
            tags.add("""<meta property="og:type" content="${escapeHtml(og.type)}">""")
        }

        if (og.url.isNotEmpty()) {
            tags.add("""<meta property="og:url" content="${escapeHtml(og.url)}">""")
        }

        if (og.image.isNotEmpty()) {
            tags.add("""<meta property="og:image" content="${escapeHtml(og.image)}">""")
        }

        if (og.siteName.isNotEmpty()) {
            tags.add("""<meta property="og:site_name" content="${escapeHtml(og.siteName)}">""")
        }

        return tags.joinToString("\n")
    }

    /**
     * Builds Twitter Card tags from Twitter Card metadata
     */
    private fun buildTwitterCardTags(twitter: TwitterCardMetadata): String {
        val tags = mutableListOf<String>()

        if (twitter.card.isNotEmpty()) {
            tags.add("""<meta name="twitter:card" content="${escapeHtml(twitter.card)}">""")
        }

        if (twitter.site.isNotEmpty()) {
            tags.add("""<meta name="twitter:site" content="${escapeHtml(twitter.site)}">""")
        }

        if (twitter.creator.isNotEmpty()) {
            tags.add("""<meta name="twitter:creator" content="${escapeHtml(twitter.creator)}">""")
        }

        if (twitter.title.isNotEmpty()) {
            tags.add("""<meta name="twitter:title" content="${escapeHtml(twitter.title)}">""")
        }

        if (twitter.description.isNotEmpty()) {
            tags.add("""<meta name="twitter:description" content="${escapeHtml(twitter.description)}">""")
        }

        if (twitter.image.isNotEmpty()) {
            tags.add("""<meta name="twitter:image" content="${escapeHtml(twitter.image)}">""")
        }

        return tags.joinToString("\n")
    }

    /**
     * Generates a canonical link if a canonical URL is provided
     */
    private fun generateCanonicalLink(canonical: String): String {
        return if (canonical.isNotEmpty()) {
            """<link rel="canonical" href="${escapeHtml(canonical)}">"""
        } else ""
    }

    /**
     * Generates a script tag with initial state for hydration
     */
    private fun generateInitialStateScript(context: RenderContext): String {
        // In a real implementation, this would serialize the state to JSON
        // For this example, we'll return a placeholder
        return """<script>window.__SUMMON_INITIAL_STATE__ = {};</script>"""
    }

    /**
     * Escapes HTML special characters in a string
     */
    private fun escapeHtml(text: String): String {
        return text
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#039;")
    }
}

/**
 * Utility object for streaming server-side rendering
 */
object StreamingSSR {
    private val renderer = StreamingRenderer()

    /**
     * Render a composable to an HTML stream
     *
     * @param composable The composable to render
     * @param context Optional rendering context with additional metadata
     * @return Flow of HTML chunks
     */
    fun renderStream(composable: @Composable () -> Unit, context: RenderContext = RenderContext()): Flow<String> {
        return renderer.renderStream(composable, context)
    }

    /**
     * Create a custom streaming renderer with specific configuration
     *
     * @param hydrationSupport The hydration support implementation to use
     * @param chunkSize The size of HTML chunks to emit
     * @return A configured StreamingRenderer
     */
    fun createRenderer(
        hydrationSupport: HydrationSupport = StandardHydrationSupport(),
        platformRenderer: MigratedPlatformRenderer = getPlatformRenderer(),
        chunkSize: Int = 4096
    ): StreamingRenderer {
        return StreamingRenderer(hydrationSupport, platformRenderer, chunkSize)
    }

    /**
     * Renders a composable function to a Flow of String chunks.
     *
     * @param content The root composable function to render.
     * @return A Flow emitting HTML chunks as strings.
     */
    fun renderToFlow(content: @Composable () -> Unit): Flow<String> = flow {
        println("StreamingSSR.renderToFlow called.")

        // 1. Create a streaming renderer context
        val context = RenderContext(enableHydration = true)
        val composer = CommonComposer() // Use CommonComposer instead of JvmComposer
        
        // 2. Set up document structure
        // Emit header part first for faster first contentful paint
        emit("<!DOCTYPE html>\n<html>\n<head>")
        emit("<meta charset=\"UTF-8\">\n")
        emit("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
        emit("<title>Summon Streaming SSR</title>\n")
        
        // Emit streaming-specific script that helps with progressive rendering
        emit("""
            <script>
                // Progressive rendering support
                window.__SUMMON_CHUNKS_LOADED = 0;
                window.__SUMMON_STREAMING = true;
                window.__summonChunkLoaded = function() {
                    window.__SUMMON_CHUNKS_LOADED++;
                    document.dispatchEvent(new CustomEvent('summon:chunk-loaded'));
                };
            </script>
        """.trimIndent())
        emit("</head>\n<body>")
        
        // 3. Begin content container with streaming attributes
        emit("<div id=\"summon-root\" data-summon-streaming=\"true\">\n")
        
        // 4. Execute content within composition context to generate HTML in chunks
        val chunks = mutableListOf<String>()
        
        // Simplified implementation - just render the full HTML and split into chunks
        val fullHtml = renderer.renderToString(content)
        val chunkSize = 4096
        chunks.addAll(fullHtml.chunked(chunkSize))
        
        // 5. Emit each chunk with progress markers for client-side processing
        chunks.forEachIndexed { index, chunk ->
            // Wrap each chunk with a progress marker for client-side tracking
            emit("<div data-summon-chunk=\"$index\">$chunk</div>\n")
            emit("<script>window.__summonChunkLoaded();</script>\n")
            
            // Small delay between chunks to simulate network streaming
            // In a real implementation, this would be controlled by network backpressure
            kotlinx.coroutines.delay(10)
        }
        
        // 6. Close the content container
        emit("</div>\n")
        
        // 7. Add hydration script for client-side reactivation
        val hydrationData = StreamingHydrationSupport.generateHydrationData(content)
        emit("""
            <script id="summon-hydration-data" type="application/json">
                $hydrationData
            </script>
            <script>
                // Signal that streaming is complete
                window.__SUMMON_STREAMING_COMPLETE = true;
                document.dispatchEvent(new CustomEvent('summon:streaming-complete', {
                    detail: { chunkCount: ${chunks.size} }
                }));
            </script>
            <script src="/summon-hydration.js"></script>
        """.trimIndent())
        
        // 8. Close document
        emit("</body>\n</html>")
    }
}

/**
 * A simple interface for hydration support in streaming SSR
 */
private object StreamingHydrationSupport {
    /**
     * Generates hydration data for client-side reactivation
     */
    fun generateHydrationData(content: @Composable () -> Unit): String {
        // In a real implementation, this would generate proper hydration data
        // For this example, we'll return a placeholder
        return """{ "hydrationVersion": 1, "components": [] }"""
    }
} 
