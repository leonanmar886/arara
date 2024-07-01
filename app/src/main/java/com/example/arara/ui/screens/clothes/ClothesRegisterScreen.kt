package com.example.arara.ui.screens.clothes

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
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
	val context = LocalContext.current
	
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
			onRegister = {
				viewModel.registerClothes(context)
				navigateToList()
		 },
		)
	}
}

@Composable
fun ClothesRegisterScreenContent(
	modifier: Modifier = Modifier,
	registerDetails: ClothesRegisterDetails,
	onRegisterInfoChange: (ClothesRegisterDetails) -> Unit,
	onRegister: () -> Unit
){
	val painter = rememberAsyncImagePainter(model = registerDetails.image)
	
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(start = 30.dp, end = 30.dp)
			.background(Color.White),
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
				ImagePicker(
					onImagePicked = { uri ->
						if (uri != null) {
							onRegisterInfoChange(registerDetails.copy(image = uri.toString()))
						}
					},
					painter = painter,
					registerDetails = registerDetails
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
					InputField(
						value = registerDetails.size,
						onValueChange = { onRegisterInfoChange(registerDetails.copy(size = it)) },
						label = "Tamanho",
						errorMessage = if (registerDetails.errorMessages.size == -1) {""} else {
							stringResource(id = registerDetails.errorMessages.size)
						},
						modifier = modifier.fillMaxWidth()
					)
				}
			}
			Box(modifier = Modifier.height(10.dp))
			Box(modifier = Modifier.fillMaxWidth()){
				AutocompleteComponent(
					options = registerDetails.tagsOptions,
					selectedOptions = registerDetails.tags,
					onChangeSelectedOptions = { onRegisterInfoChange(registerDetails.copy(tags = it)) },
					onChangeOptions = { onRegisterInfoChange(registerDetails.copy(tagsOptions = it)) }
				)
			}
			Row (
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.End
			){
				Button(
					onClick = onRegister,
					colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B8A9)),
					modifier = Modifier
						.height(60.dp)
						.padding(10.dp)
				) {
					Text(
						text = "SALVAR",
						fontSize = 12.sp
					)
				}
			}
		}
	}
}

@Composable
fun AutocompleteComponent(
	options: List<String>,
	selectedOptions: List<String>,
	onChangeSelectedOptions: (List<String>) -> Unit = {},
	onChangeOptions: (List<String>) -> Unit = {}
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
			modifier = Modifier.fillMaxWidth()
		) {
			Box(
				modifier = Modifier
					.weight(1f)
					.horizontalScroll(scrollState)
			) {
				if (selectedOptions.isEmpty()) {
					Text(
						text = "Adicione tags",
						style = MaterialTheme.typography.labelMedium
					)
				} else {
					Row {
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
			}
			Row(
				horizontalArrangement = Arrangement.End,
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.clip(RoundedCornerShape(10.dp))
					.background(Color.LightGray)
					.height(32.dp)
					.border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
					.padding(5.dp)
			) {
				val createTagDialog = remember { mutableStateOf(false) }
				val tagToAdd = remember { mutableStateOf("") }
				
				IconButton(
					onClick = { createTagDialog.value = true },
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
								Column(
									modifier = Modifier
										.padding(0.dp, 5.dp, 15.dp, 0.dp),
									verticalArrangement = Arrangement.spacedBy(10.dp),
									horizontalAlignment = Alignment.CenterHorizontally
								) {
									InputField(
										value = tagToAdd.value,
										onValueChange = { tagToAdd.value = it },
										errorMessage = "",
										modifier = Modifier
											.fillMaxWidth()
											.clip(RoundedCornerShape(10.dp))
											.background(Color.White)
									)
									Row(
										horizontalArrangement = Arrangement.Center
									) {
										Button(
											onClick = {
												onChangeSelectedOptions(
													selectedOptions
														.toMutableList()
														.apply {
															if (!contains(tagToAdd.value)) {
																add(tagToAdd.value)
															}
														}
												)
												onChangeOptions(
													options
														.toMutableList()
														.apply {
															if (!contains(tagToAdd.value)) {
																add(tagToAdd.value)
															}
														}
												)
												createTagDialog.value = false
											},
											modifier = Modifier.fillMaxWidth(),
										) {
											Text(text = "Criar")
										}
									}
								}
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
fun ImagePicker(
	onImagePicked: (Uri?) -> Unit,
	painter: AsyncImagePainter,
	registerDetails: ClothesRegisterDetails
) {
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent(),
		onResult = { uri: Uri? ->
			onImagePicked(uri)
		}
	)
	
	IconButton(
		onClick = { launcher.launch("image/*") },
		modifier = Modifier.size(165.dp),
		content = {
			if (registerDetails.image.isNotBlank()) {
				Image(
					painter = painter,
					contentDescription = "Adicionar foto",
					modifier = Modifier.size(130.dp)
				)
			} else {
				Image(
					painter = painterResource(id = R.drawable.add_photo),
					contentDescription = "Adicionar foto",
					modifier = Modifier.size(130.dp)
				)
			}
		}
	)
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