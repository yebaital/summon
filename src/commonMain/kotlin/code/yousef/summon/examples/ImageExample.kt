package code.yousef.summon.examples

import code.yousef.summon.core.Composable
import code.yousef.summon.Spacer
import code.yousef.summon.components.display.Image
import code.yousef.summon.components.display.ImageLoading
import code.yousef.summon.components.layout.Column
import code.yousef.summon.components.layout.Row
import code.yousef.summon.modifier.Modifier

/**
 * Example showing different ways to use the Image component.
 */
object ImageExample {
    /**
     * Creates a simple image example.
     */
    fun basicImage(): Composable {
        return Column(
            modifier = Modifier().padding("16px"),
            content = listOf(
                code.yousef.summon.components.display.Text(
                    "Basic Image Example",
                    modifier = Modifier().fontSize("24px").fontWeight("bold").marginBottom("16px")
                ),
                Image(
                    src = "https://via.placeholder.com/300",
                    alt = "A placeholder image",
                    modifier = Modifier().border("1px", "solid", "#ccc").borderRadius("4px")
                ),
                Spacer(size = "16px", isVertical = true),
                Text("Above: A basic image with border styling")
            )
        )
    }

    /**
     * Creates an example with multiple images and different loading strategies.
     */
    fun multipleImages(): Composable {
        return Column(
            modifier = Modifier().padding("16px"),
            content = listOf(
                code.yousef.summon.components.display.Text(
                    "Multiple Images with Different Loading Strategies",
                    modifier = Modifier().fontSize("24px").fontWeight("bold").marginBottom("16px")
                ),
                Row(
                    modifier = Modifier().margin("0px"),
                    content = listOf(
                        Column(
                            modifier = Modifier().margin("0 16px 0 0"),
                            content = listOf(
                                Image(
                                    src = "https://via.placeholder.com/150",
                                    alt = "Eager loaded image",
                                    loading = ImageLoading.EAGER,
                                    width = "150px",
                                    height = "150px",
                                    modifier = Modifier().shadow("0px", "4px", "8px", "rgba(0,0,0,0.1)")
                                ),
                                code.yousef.summon.components.display.Text(
                                    "Eager Loading",
                                    modifier = Modifier().marginTop("8px")
                                )
                            )
                        ),
                        Column(
                            modifier = Modifier().margin("0 16px 0 0"),
                            content = listOf(
                                Image(
                                    src = "https://via.placeholder.com/150",
                                    alt = "Lazy loaded image",
                                    loading = ImageLoading.LAZY,
                                    width = "150px",
                                    height = "150px",
                                    modifier = Modifier().shadow("0px", "4px", "8px", "rgba(0,0,0,0.1)")
                                ),
                                code.yousef.summon.components.display.Text(
                                    "Lazy Loading",
                                    modifier = Modifier().marginTop("8px")
                                )
                            )
                        ),
                        Column(
                            modifier = Modifier().margin("0"),
                            content = listOf(
                                Image(
                                    src = "https://via.placeholder.com/150",
                                    alt = "Auto loaded image",
                                    loading = ImageLoading.AUTO,
                                    width = "150px",
                                    height = "150px",
                                    modifier = Modifier().shadow("0px", "4px", "8px", "rgba(0,0,0,0.1)")
                                ),
                                code.yousef.summon.components.display.Text(
                                    "Auto Loading",
                                    modifier = Modifier().marginTop("8px")
                                )
                            )
                        )
                    )
                ),
                Spacer(size = "24px", isVertical = true),
                code.yousef.summon.components.display.Text(
                    "Accessibility Example",
                    modifier = Modifier().fontSize("20px").fontWeight("bold").marginBottom("16px")
                ),
                Image(
                    src = "https://via.placeholder.com/400x200",
                    alt = "Chart showing data visualization",
                    contentDescription = "Bar chart showing monthly revenue growth of 15% in Q1, 22% in Q2, 18% in Q3, and 25% in Q4 of 2023.",
                    modifier = Modifier().maxWidth("100%").height("auto")
                ),
                code.yousef.summon.components.display.Text(
                    "Above: An image with detailed description for screen readers",
                    modifier = Modifier().marginTop("8px")
                )
            )
        )
    }
} 