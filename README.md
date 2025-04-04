# Summon

Summon is a Kotlin Multiplatform (JVM/JS) library that enables developers to generate static websites using Compose-like syntax. With Summon, you can write your website content in a declarative style familiar to Compose developers, and the library will generate the corresponding HTML, CSS, and JavaScript.

## Overview

- **Declarative UI**: Use Compose-style syntax to define your website structure
- **Kotlin Multiplatform**: Works on both JVM and JavaScript platforms
- **Static Site Generation**: Outputs clean HTML, CSS, and JavaScript
- **Type Safety**: Leverage Kotlin's type system for safer web development
- **Responsive Design**: Create adaptive layouts with MediaQuery
- **Consistent Styling**: Maintain visual harmony with the Spacing system

## Key Benefits

- Write once, deploy anywhere with Kotlin Multiplatform
- Familiar syntax for developers already using Compose
- No runtime dependencies required for the generated websites
- Fully static output for optimal performance and hosting flexibility

## Features

### Spacing System

The Spacing system provides a consistent spacing scale throughout your application:

```kotlin
// Use predefined spacing values
Text(
    text = "Hello World",
    modifier = Modifier()
        .spacingPadding(Spacing.md)
        .spacingMargin(
            top = Spacing.lg,
            bottom = Spacing.md
        )
)

// Available spacing constants
Spacing.xs  // 4px
Spacing.sm  // 8px
Spacing.md  // 16px 
Spacing.lg  // 24px
Spacing.xl  // 32px
Spacing.xxl // 48px

// Custom spacing
Spacing.custom(3) // 12px (3 * base unit of 4px)
```

### Media Query System

The MediaQuery system enables responsive design with a simple API:

```kotlin
// Create responsive components
Card(
    modifier = Modifier()
        // Base styles for all screen sizes
        .padding("20px")
        // Apply different styles based on screen size
        .responsive(
            MediaQuery.mobile(
                Modifier().padding("10px")
            ),
            MediaQuery.desktop(
                Modifier().maxWidth("800px")
            )
        ),
    content = listOf(/* your content */)
)

// Using predefined breakpoints
MediaQuery.Breakpoints.xs // 0px (mobile)
MediaQuery.Breakpoints.sm // 600px (tablet)
MediaQuery.Breakpoints.md // 960px (small desktop)
MediaQuery.Breakpoints.lg // 1280px (desktop)
MediaQuery.Breakpoints.xl // 1920px (large desktop)

// Special media query helpers
MediaQuery.darkMode(Modifier().background("#222"))
MediaQuery.lightMode(Modifier().background("#fff"))
MediaQuery.orientation(isPortrait = true, styleModifier)
MediaQuery.reducedMotion(styleModifier)
```

Combine both systems to create beautiful, responsive layouts with consistent spacing:

```kotlin
Column(
    modifier = Modifier()
        .fillMaxWidth()
        .spacingPadding(Spacing.md)
        .responsive(
            MediaQuery.mobile(
                Modifier().spacingPadding(Spacing.sm)
            )
        ),
    content = listOf(/* your content */)
)
```

---

# Design & Development Reference

## 1. Executive Summary

Summon is a Kotlin Multiplatform UI library that enables developers to create SEO-friendly web applications using a declarative, Compose-like syntax. The library generates HTML, CSS, and JavaScript for both server-side and client-side rendering, bridging the gap between modern UI development patterns and traditional web technologies. Summon can be used standalone or integrated with popular JVM backend frameworks including Spring Boot, Quarkus, and Ktor.

## 2. Vision and Goals

### 2.1 Vision

To provide a unified, declarative UI development experience across JVM and JavaScript platforms that generates SEO-friendly web content while maintaining the developer experience of modern UI frameworks like Jetpack Compose.

### 2.2 Goals

- Create a consistent API that mirrors Compose's declarative paradigm
- Enable code sharing between JVM and JavaScript platforms
- Generate semantic, accessible HTML that is SEO-friendly
- Provide seamless integration with Spring, Quarkus, and Ktor
- Support both server-side rendering and client-side interactivity
- Maintain a low learning curve for developers familiar with Compose
- Deliver high performance through efficient rendering strategies

## 3. Architecture Overview

Summon follows a multi-layered architecture designed to abstract platform-specific implementation details while providing a consistent API across environments:

1. **API Layer**: Compose-like declarative API for defining UI components
2. **Intermediate Representation Layer**: Platform-agnostic model of UI state
3. **Rendering Layer**: Platform-specific renderers for JVM and JS targets
4. **Framework Integration Layer**: Adapters for backend frameworks

This architecture allows developers to write UI code once and deploy it across different platforms and frameworks.

## 4. Feature Implementation Status

### 4.1 Core Components

- **Text Component** ✅: Enhanced with support for styled text, overflow handling, accessibility features, and utility functions for common text styles.
- **Button Component** ✅: For triggering actions with click event handling.
- **Image Component** ✅: For displaying images with accessibility support including alt text and loading strategies.
- **Link Component** ✅: For navigation with SEO-friendly attributes and proper security features.
- **Icon Component** ✅: For vector icons and font icons
- **Divider Component** ✅: For visual separation with customizable appearance.
- **Card Component** ✅: For grouped content with styling including elevation and border radius.
- **Alert Component** ✅: For notifications and messages
- **Badge Component** ✅: For status indicators
- **Tooltip Component** ✅: For additional information on hover
- **Progress Component** ✅: For loading and progress indication

### 4.2 Form Components

- **TextField** ✅: For text input with validation and various input types.
- **TextArea** ✅: For multi-line text input
- **Checkbox** ✅: For boolean input
- **RadioButton** ✅: For exclusive selection
- **Select/Dropdown** ✅: For option selection
- **DatePicker** ✅: For date selection
- **TimePicker** ✅: For time selection
- **RangeSlider** ✅: For range selection
- **Switch** ✅: For toggle controls
- **FileUpload** ✅: For file selection and uploads
- **Form** ✅: Container with validation and submission handling.
- **FormField** ✅: Wrapper for form controls with labels and validation

### 4.3 Layout Components

- **Row** ✅: Horizontal layout container using Flexbox.
- **Column** ✅: Vertical layout container using Flexbox.
- **Box** ✅: For positioning and stacking elements
- **Grid** ✅: CSS Grid-based responsive layout
- **Spacer** ✅: For creating empty space with customizable dimensions.
- **AspectRatio** ✅: For maintaining width-to-height ratios
- **ResponsiveLayout** ✅: For adaptive layouts based on screen size
- **LazyColumn** ✅: For virtualized vertical lists
- **LazyRow** ✅: For virtualized horizontal lists
- **TabLayout** ✅: For tabbed interfaces
- **ExpansionPanel** ✅: For collapsible sections

### 4.4 Styling System

- **Modifier** ✅: Chainable styling API similar to Compose
- **Theme**: Global styling configuration
- **StyleSheet**: Component for defining reusable styles
- **ColorSystem**: Predefined color schemes with dark/light modes
- **Typography**: Text styling presets
- **Spacing** ✅: Consistent spacing system
- **MediaQuery** ✅: Responsive style adjustments

### 4.5 Navigation

- **Router** ✅: Client-side routing system with path-based navigation.
- **NavLink**: Navigation component with active state
- **Redirect**: Declarative redirection
- **RouteParams**: Access to route parameters
- **DeepLinking**: Support for direct navigation to specific states
- **History**: Navigation history management

### 4.6 State Management

- **State** ✅: Observable state container for reading values.
- **MutableState** ✅: Modifiable state container for reading and writing values.
- **RememberSaveable**: Persisted state across recompositions
- **ViewModel Integration**: Connection to platform ViewModels
- **FlowBinding**: Integration with Kotlin Flows
- **StateFlow Integration**: Support for StateFlow and SharedFlow

### 4.7 Effects and Lifecycle

- **LaunchedEffect**: For side effects on composition
- **DisposableEffect**: For cleanup on decomposition
- **SideEffect**: For non-cancellable side effects
- **DerivedState**: For computed values from other states
- **LifecycleAware Components**: Integration with platform lifecycles

### 4.8 Animations

- **AnimatedVisibility**: For appearance/disappearance animations
- **AnimatedContent**: For content change animations
- **Transition**: For state-based animations
- **InfiniteTransition**: For continuous animations
- **Spring Animation**: Physics-based animations
- **Tween Animation**: Time-based animations

### 4.9 Framework Integration

#### 4.9.1 Spring Integration

- **SpringViewResolver**: Custom ViewResolver for Summon components
- **ThymeleafSummonDialect**: Custom dialect for using Summon in Thymeleaf
- **ModelAttributeBinding**: Connection to Spring model attributes
- **SpringSecuritySupport**: Integration with authentication/authorization
- **SpringFormBinding**: Integration with Spring form binding
- **SpringValidation**: Integration with Bean Validation
- **SpringBootStarter**: Auto-configuration for Spring Boot

#### 4.9.2 Quarkus Integration

- **SummonQuteExtension**: Integration with Qute templates
- **QuarkusExtension**: Dedicated Quarkus extension
- **QuteTagExtension**: Custom tags for Qute templates
- **ResteasyIntegration**: Integration with RESTEasy
- **CDISupport**: Injection of beans in components
- **NativeImageSupport**: Support for GraalVM native image

#### 4.9.3 Ktor Integration

- **SummonApplicationCall**: Extension functions for ApplicationCall
- **KtorTemplating**: Integration with Ktor's templating features
- **KtorRouting**: Extensions for routing DSL
- **KtorSessionState**: Integration with Ktor sessions
- **KtorAuthentication**: Integration with authentication

### 4.10 SEO Enhancements

- **MetaTags**: Easy configuration of meta tags
- **StructuredData**: JSON-LD generation helpers
- **SemanticHTML**: Semantic HTML element helpers
- **SitemapGeneration**: Sitemap creation utilities
- **CanonicalLinks**: Management of canonical URLs
- **OpenGraphTags**: Social media metadata support
- **TwitterCards**: Twitter-specific metadata

### 4.11 Accessibility

- **ARIA Attributes** ✅: Built-in accessibility attributes for components.
- **KeyboardNavigation**: Support for keyboard interaction
- **FocusManagement**: Utilities for managing focus
- **ScreenReaderText** ✅: Utilities for screen reader content including hidden text.
- **AccessibilityTree**: Inspection of accessibility hierarchy
- **ContrastChecking**: Color contrast validation

### 4.12 Developer Tools

- **Inspector**: Component inspection tool
- **StateVisualizer**: State visualization and debugging
- **PerformanceMonitoring**: Performance measurement utilities
- **LoggingUtilities**: Enhanced logging for development
- **HotReload**: Development time hot reloading
- **TestingUtilities**: Component testing helpers

### 4.13 Server-Side Rendering

- **StaticRendering**: Generation of static HTML
- **DynamicRendering**: Server-side rendering with dynamic data
- **HydrationSupport**: Client-side reactivation of server-rendered HTML
- **PartialHydration**: Selective hydration of interactive elements
- **StreamingSSR**: Streaming server-side rendering for large pages
- **SEOPrerender**: Pre-rendering for search engine crawlers

## 5. Component Usage Examples

```kotlin
// Basic component usage
val greeting = Column(
    modifier = Modifier()
        .padding("20px")
        .background("#f5f5f5"),
    content = listOf(
        Text("Hello, World!", 
            Modifier()
                .fontSize("24px")
                .color("#333333")
        ),
        Spacer("10px", true),
        Button(
            "Click Me",
            onClick = { println("Button clicked!") },
            modifier = Modifier()
                .padding("10px")
                .background("#4CAF50")
                .color("white")
        )
    )
)

// Spring controller example
@Controller
class HomeController {
    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("title", "Home Page")
        model.addAttribute("greeting", "Welcome to Summon!")
        
        return "home" // Uses SummonViewResolver
    }
}

// Ktor route example
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondSummon(HomeScreen())
        }
    }
}

// Quarkus resource example
@Path("/")
class HomeResource {
    @Inject
    lateinit var summonRenderer: SummonRenderer
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    fun home(): String {
        return summonRenderer.render(HomeScreen())
    }
}