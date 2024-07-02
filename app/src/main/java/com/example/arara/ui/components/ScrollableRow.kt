import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableRow(items: List<String>) {
	val scrollState = rememberScrollState()
	
	Box(
		modifier = Modifier
			.fillMaxWidth()
	) {
		Row(
			modifier = Modifier.horizontalScroll(scrollState)
		) {
			items.forEach { item ->
				Box(
					modifier = Modifier
						.padding(end = 8.dp)
						.background(Color.LightGray, RoundedCornerShape(20.dp))
						.padding(8.dp)
				) {
					Text(
						text = item,
						style = MaterialTheme.typography.labelSmall
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ScrollableRowPreview() {
	ScrollableRow(items = listOf("linho", "camisa", "branca", "Item 4", "Item 5"))
}