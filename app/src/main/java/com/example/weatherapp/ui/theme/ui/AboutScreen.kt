import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text("☁️", fontSize = 80.sp)

        Text("Weather", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(10.dp))

        Text("v1.0")

        Spacer(modifier = Modifier.height(30.dp))

        Text("Developed by")
        Text("Rajiv Mishra", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(30.dp))

        // 🔥 CLICKABLE ICONS
        Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {

            Text(
                "🐙 GitHub",
                modifier = Modifier.clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/Sakssmama01/RajivMishra")
                    )
                    context.startActivity(intent)
                }
            )

            Text(
                "💼 LinkedIn",
                modifier = Modifier.clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/rajiv-mishra-6107712b2")
                    )
                    context.startActivity(intent)
                }
            )
        }
    }
}