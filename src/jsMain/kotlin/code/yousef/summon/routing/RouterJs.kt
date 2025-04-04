package code.yousef.summon.routing

import code.yousef.summon.core.Composable
import kotlinx.browser.window
import kotlin.js.JsName

/**
 * Extends the Router class with JavaScript-specific functionality.
 */

/**
 * External JS function interface
 */
@JsName("js")
external fun js(code: String): dynamic

/**
 * Global router instance that is accessible from JavaScript.
 */
private var globalRouter: Router? = null

/**
 * Sets up the router for use with the browser.
 * Exposes navigation functions to JavaScript.
 */
@JsName("setupRouterForBrowser")
fun setupRouterForBrowser(router: Router) {
    // Store in global variable
    globalRouter = router
    
    // Set as current router in context
    RouterContext.setCurrent(router)
    
    // Expose the navigation function to JavaScript
    js("window.summonRouterNavigate = function(path, pushState) { summonRouterNavigate(path, pushState !== false); }")
}

/**
 * Navigate to a path using the global router.
 * This function is called from JavaScript.
 */
@JsName("summonRouterNavigate")
fun summonRouterNavigate(path: String, pushState: Boolean = true) {
    globalRouter?.navigate(path, pushState)
}

/**
 * Creates a router with browser navigation support.
 */
@JsName("createBrowserRouter")
fun createBrowserRouter(
    vararg routes: Route,
    notFoundComponent: ((RouteParams) -> Composable)? = null
): Router {
    val router = Router.create(*routes, notFoundComponent = notFoundComponent)
    setupRouterForBrowser(router)
    return router
}

/**
 * Creates a router with browser navigation support using a DSL.
 */
fun createBrowserRouter(init: Router.RouterBuilder.() -> Unit): Router {
    val router = Router.create(init)
    setupRouterForBrowser(router)
    return router
}

/**
 * Updates the browser history with the new path.
 * This is called automatically by the Router.navigate method.
 */
fun Router.updateBrowserUrl(path: String, pushState: Boolean) {
    if (pushState) {
        window.history.pushState(null, "", path)
    } else {
        window.history.replaceState(null, "", path)
    }
}

/**
 * Extension function for Router.navigate that also updates the browser URL.
 */
fun Router.navigateAndUpdateBrowser(path: String, pushState: Boolean = true) {
    navigate(path, pushState)
    updateBrowserUrl(path, pushState)
}