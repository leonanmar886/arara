package com.example.arara.ui.screens.clothes

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.activities.ImageSelectionActivity
import com.example.arara.ui.components.CustomDialog
import com.example.arara.ui.components.InputField
import com.example.arara.ui.navigation.NavigationDestination

object ClothesRegisterDestination: NavigationDestination {
		override val route: String = "clothes_register"
		override val titleRes: Int = R.string.clothes_register_title
}

@Composable
fun ClothesRegisterScreen(
	modifier: Modifier = Modifier,
	viewModel: ClothesRegisterViewModel = viewModel(factory = AppViewModelProvider.Factory),
	navigateToList: () -> Unit
) {
	
	val clothesUiState = viewModel.clothesUiState
	val clothesDetails = clothesUiState.clothesDetails
	
	Scaffold(
		topBar = {
			Row(
				modifier = Modifier
					.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Logo(modifier = modifier)
				IconButton(
					onClick = { navigateToList() },
					content = {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Voltar"
						)
					}
				)
			}
		},
		bottomBar = {
			Row {
				Footer(modifier = modifier)
			}
		}
	){ innerPadding ->
		ClothesRegisterScreenContent(
			registerDetails = clothesDetails,
			onRegisterInfoChange = viewModel::updateUiState,
			onRegister = { viewModel.registerClothes() },
		)
	}
	
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesRegisterScreenContent(
	modifier: Modifier = Modifier,
	registerDetails: ClothesRegisterDetails,
	onRegisterInfoChange: (ClothesRegisterDetails) -> Unit,
	onRegister: () -> Unit
){
	val context = LocalContext.current
	val activity = context as AppCompatActivity
	val startForResult = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
		if (result.resultCode == Activity.RESULT_OK) {
			val uri = result.data?.data
			if (uri != null) {
				onRegisterInfoChange(registerDetails.copy(image = uri.toString()))
			}
		}
	}
	
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(start = 30.dp, end = 30.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Column {
			Text(
				text = "Criar Roupa",
				color = Color.Black,
				fontSize = 20.sp,
				fontWeight = FontWeight(900),
				fontFamily = FontFamily(Font(R.font.outfit_eb)),
				textAlign = TextAlign.Left
			)
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.fillMaxWidth()
			){
				IconButton(
					onClick = {
						val intent = Intent(activity, ImageSelectionActivity::class.java)
						startForResult.launch(intent)
					},
					modifier = Modifier.size(165.dp),
					content = {
						Image(
							painter = painterResource(id = R.drawable.add_photo),
							contentDescription = "Adicionar foto",
							modifier = Modifier.size(130.dp)
						)
					}
				)
				Text(text = "Adicione uma foto da sua roupa", color = Color.Gray, fontSize = 14.sp)
			}
			Text(
				text = "Dados",
				color = Color.Black,
				fontSize = 20.sp,
				fontWeight = FontWeight(900),
				fontFamily = FontFamily(Font(R.font.outfit_eb)),
				textAlign = TextAlign.Left,
				modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
			)
			InputField(
				value = registerDetails.title,
				onValueChange = { onRegisterInfoChange(registerDetails.copy(title = it)) },
				label = "Título da peça",
				errorMessage = if (registerDetails.errorMessages.image == -1) {""} else {
					stringResource(id = registerDetails.errorMessages.image)
				},
				modifier = modifier
					.clip(RoundedCornerShape(10.dp))
					.fillMaxWidth()
					.height(50.dp)
			)
			Box(modifier = Modifier.height(10.dp))
			InputField(
				value = registerDetails.description,
				onValueChange = { onRegisterInfoChange(registerDetails.copy(description = it)) },
				label = "Descrição",
				errorMessage = if (registerDetails.errorMessages.image == -1) {""} else {
					stringResource(id = registerDetails.errorMessages.image)
				},
				multiline = true,
				modifier = modifier
					.clip(RoundedCornerShape(10.dp))
					.fillMaxWidth()
					.height(100.dp)
			)
			Box(modifier = Modifier.height(10.dp))
			Row(
				modifier = modifier
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Box(modifier = Modifier.fillMaxWidth()){
					SelectComponent(
						options = listOf("PP", "P", "M", "G", "GG", "XG", "34", "36", "38", "40", "42", "44", "46", "48", "50", "52", "54", "56", "58", "60")
					)
				}
			}
			Box(modifier = Modifier.height(10.dp))
			Box(modifier = Modifier.fillMaxWidth()){
				AutocompleteComponent(
					options = registerDetails.tagsOptions,
					selectedOptions = registerDetails.tags,
					onChangeSelectedOptions = { onRegisterInfoChange(registerDetails.copy(tags = it)) }
				)
			}
		}
	}
}

@Composable
fun AutocompleteComponent(
	options: List<String>,
	selectedOptions: List<String>,
	onChangeSelectedOptions: (List<String>) -> Unit = {}
) {
	var expanded by remember { mutableStateOf(false) }
	val scrollState = rememberScrollState()
	
	Box(modifier = Modifier
		.fillMaxWidth()
		.border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
		.padding(16.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			if (selectedOptions.isEmpty()) {
				Text(
					text = "Adicione tags",
					style = MaterialTheme.typography.labelMedium
				)
			} else {
				Row(
					modifier = Modifier.horizontalScroll(scrollState)
				) {
					selectedOptions.forEach { option ->
						InputChip(
							label = { Text(text = option, style = MaterialTheme.typography.labelMedium) },
							onClick = { onChangeSelectedOptions(selectedOptions.filter { it != option }) },
							selected = false,
							trailingIcon = {
								Icon(
									imageVector = Icons.Outlined.Close,
									contentDescription = "Remover tag",
									modifier = Modifier.size(16.dp)
								)
							},
							modifier = Modifier.padding(end = 8.dp)
						)
					}
				}
			}
			Row(
				horizontalArrangement = Arrangement.End,
				verticalAlignment = Alignment.CenterVertically,
			) {
				val createTagDialog = remember { mutableStateOf(false) }
				IconButton(
					onClick = { createTagDialog.value = true},
					modifier = Modifier.size(24.dp),
					content = {
						Icon(
							imageVector = Icons.Outlined.Edit,
							contentDescription = "Criar tag"
						)
					},
				)
				when {
					createTagDialog.value -> {
						CustomDialog(
							title = "Crie uma nova tag",
							content = {
								InputField(
									value = "",
									onValueChange = {},
									label = "Nome",
									errorMessage = "",
									modifier = Modifier)
							},
							onDismiss = { createTagDialog.value = false }
						)
					}
				}
				Spacer(modifier = Modifier.width(5.dp))
				IconButton(
					onClick = { expanded = !expanded },
					modifier = Modifier.size(24.dp),
					content = {
						Icon(
							imageVector = Icons.Outlined.Add,
							contentDescription = "Selecionar tag"
						)
					}
				)
			}
		}
		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false },
			modifier = Modifier
				.background(Color.White)
				.height(200.dp),
		) {
			options.forEach { option ->
				DropdownMenuItem(
					onClick = {
						onChangeSelectedOptions(
							selectedOptions
								.toMutableList()
								.apply {
									if (!contains(option)) {
										add(option)
									}
								}
						)
						expanded = false
					},
					text = { Text(text = option, style = MaterialTheme.typography.labelMedium) })
			}
		}
	}
}

@Composable
fun SelectComponent(
	options: List<String>
) {
	var expanded by remember { mutableStateOf(false) }
	var selectedOption by remember { mutableStateOf("Tamanho") }
	
	Box(modifier = Modifier
		.fillMaxWidth()
		.border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
		.padding(start = 16.dp, top = 16.dp, end = 25.dp, bottom = 16.dp)
		.clickable { expanded = !expanded }
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			Text(
				text = selectedOption,
				style = MaterialTheme.typography.labelMedium
			)
			Icon(
				imageVector = if (expanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
				contentDescription = if (expanded) "Menu aberto" else "Menu fechado"
			)
		}
		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false },
			modifier = Modifier
				.background(Color.White)
				.height(200.dp),
		) {
			options.forEach { option ->
				DropdownMenuItem(
					onClick = {
						selectedOption = option
						expanded = false
					},
					text = { Text(text = option, style = MaterialTheme.typography.labelMedium) })
			}
		}
	}
}

@Preview
@Composable
fun ClothesRegisterScreenPreview(
	modifier: Modifier = Modifier,
	navigateToList: () -> Unit = {},
	clothesDetails: ClothesRegisterDetails = ClothesRegisterDetails(
		image = "image_path",
		title = "title",
		description = "description",
		color = "color",
		size = "size",
		tags = listOf("tag1", "tag2"),
		isLoading = false,
		isRegistered = false,
		errorMessages = ClothesRegisterErrorMessages()
	)
) {
	Scaffold(
		topBar = {
			Row(
				modifier = Modifier
					.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Logo(modifier = modifier)
				IconButton(
					onClick = { navigateToList() },
					content = {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Voltar"
						)
					}
				)
			}
		},
		bottomBar = {
			Row {
				Footer(modifier = modifier)
			}
		}
	){ innerPadding ->
		ClothesRegisterScreenContent(
			modifier = Modifier.padding(innerPadding),
			registerDetails = clothesDetails,
			onRegisterInfoChange = {},
			onRegister = { }
		)
	}
	
}