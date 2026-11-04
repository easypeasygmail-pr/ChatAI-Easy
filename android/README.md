# ChatAI-Easy Android

Android launcher for the ChatAI-Easy PWA. It first requests a Trusted Web Activity (TWA) for https://chat-ai-easy.vercel.app/ and falls back to a Chrome Custom Tab when the domain is not verified or TWA is unavailable.

## Build
Run `gradle assembleDebug` from this directory with Android SDK and JDK 17, or use the GitHub Actions workflow. The debug APK is a test build, not a Play Store release.

## Important: domain verification still required
For a fully trusted, browser-chrome-free TWA, the website must serve:
`https://chat-ai-easy.vercel.app/.well-known/assetlinks.json`
The JSON must contain the Android package name `pl.chataieasy.app` and the SHA-256 fingerprint of the certificate used to sign the installed APK. Until that file and matching signing certificate are configured, Android/Chrome will use the Custom Tabs fallback.

## ChatGPT sign-in
ChatGPT itself is not embedded in a WebView. It must be opened through the official ChatGPT website/browser flow. OpenAI may choose its own supported login flow. This app does not bypass site or OAuth restrictions and does not use an OpenAI API key.
