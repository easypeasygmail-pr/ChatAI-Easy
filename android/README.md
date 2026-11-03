# ChatAI-Easy Android wrapper

This is an experimental Android WebView shell around the deployed personal web interface. It requests JavaScript, DOM storage and cookies, hides system navigation/status bars for immersive display, and attempts to load target=_blank links in the same WebView.

## Build
Run `gradle assembleDebug` from this directory with Android SDK installed, or use the GitHub Actions workflow.

## Important compatibility note
The official ChatGPT website may restrict authentication or some features in embedded WebViews. This wrapper does not bypass those restrictions. If OpenAI blocks login or embedded use, use the official ChatGPT Android app or a supported browser flow. No OpenAI API key is used.
