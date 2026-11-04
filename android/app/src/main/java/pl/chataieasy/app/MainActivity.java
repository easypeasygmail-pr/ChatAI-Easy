package pl.chataieasy.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.browser.trusted.TrustedWebActivityIntentBuilder;

public class MainActivity extends Activity {
    private static final String HOME = "https://chat-ai-easy.vercel.app/";
    private CustomTabsServiceConnection connection;
    private boolean launched = false;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        launchBrowserExperience();
    }

    private void launchBrowserExperience() {
        String chromePackage = CustomTabsClient.getPackageName(this, null);
        if (chromePackage == null) {
            openCustomTab();
            return;
        }

        connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(android.content.ComponentName name, CustomTabsClient client) {
                client.warmup(0L);
                CustomTabsSession session = client.newSession(null);
                if (session != null && !launched) {
                    try {
                        launched = true;
                        new TrustedWebActivityIntentBuilder(Uri.parse(HOME))
                            .build(session)
                            .launchActivity(MainActivity.this);
                    } catch (Exception error) {
                        launched = false;
                        openCustomTab();
                    }
                } else {
                    openCustomTab();
                }
            }

            @Override
            public void onServiceDisconnected(android.content.ComponentName name) {
                if (!launched) openCustomTab();
            }
        };

        boolean bound = CustomTabsClient.bindCustomTabsService(this, chromePackage, connection);
        if (!bound) openCustomTab();
    }

    private void openCustomTab() {
        if (launched) return;
        launched = true;
        try {
            new CustomTabsIntent.Builder().build().launchUrl(this, Uri.parse(HOME));
        } catch (Exception error) {
            Toast.makeText(this, "Zainstaluj lub zaktualizuj Chrome.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (connection != null) {
            try { unbindService(connection); } catch (IllegalArgumentException ignored) { }
        }
        super.onDestroy();
    }
}
