@file:OptIn(ExperimentalMaterial3Api::class)

package com.jhtest.storibanktest.ui.theme.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.theme.components.utils.clickableNoRipple
import com.jhtest.storibanktest.utils.Empty
import com.jhtest.storibanktest.utils.containsNoEmojis
import com.jhtest.storibanktest.utils.isEmailValid
import com.jhtest.storibanktest.utils.isValidLength
import io.mockk.core.ValueClassSupport.boxedValue

enum class TextFieldValidation {
    EMAIL,
    NAME,
    USER_NAME,
    LAST_NAME,
    PASSWORD,
    NONE
}

@Composable
fun TextFieldScreen(
    modifier: Modifier = Modifier,
    label: String = String.Empty,
    defaultValue: String = String.Empty,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    validationType: TextFieldValidation = TextFieldValidation.NONE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChange: (Pair<String, Boolean>) -> Unit,
    onClick: (() -> Unit)? = null
) {
    var error by remember { mutableStateOf(String.Empty) }
    var isFocused by remember { mutableStateOf(false) }
    val interaction: MutableInteractionSource = remember { MutableInteractionSource() }
    var currentTextState by remember(key1 = defaultValue) { mutableStateOf(defaultValue) }

    val textSize = if (isFocused) 12.sp else 16.sp

    val colors = getTextColors(enabled = enabled, readOnly = readOnly)
    val borderColor = getBorderColor(error)
    val backgroundColor = getBackgroundColor(readOnly, enabled)

    val errorEmail = stringResource(id = R.string.authentication_error_textfield_email)
    val errorName = stringResource(id = R.string.authentication_error_textfield_name)
    val errorUserName = stringResource(id = R.string.authentication_error_textfield_user_name)
    val errorPassword = stringResource(id = R.string.authentication_error_textfield_password)
    val errorLastName = stringResource(id = R.string.authentication_error_textfield_last_name)

    LaunchedEffect(key1 = true) {
        interaction.interactions.collect {
            isFocused = it.boxedValue is PressInteraction.Release
        }
    }

    Column(
        modifier = modifier
            .clickableNoRipple {
                onClick?.invoke()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .background(color = backgroundColor, shape = RoundedCornerShape(size = 12.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .indicatorLine(
                        enabled = true,
                        isError = false,
                        interactionSource = interaction,
                        colors = colors
                    )
                    .onFocusChanged {
                        if (it.isFocused) {
                            onClick?.invoke()
                        }
                    },
                value = currentTextState,
                interactionSource = interaction,
                enabled = enabled,
                readOnly = readOnly,
                singleLine = true,
                maxLines = 1,
                textStyle = MaterialTheme.typography.labelMedium,
                visualTransformation = if (validationType != TextFieldValidation.PASSWORD) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = keyboardOptions,
                decorationBox = @Composable { innerTextField ->
                    TextDecorationBox(
                        currentTextState,
                        innerTextField,
                        enabled,
                        interaction,
                        colors,
                        label,
                        textSize
                    )
                },
                onValueChange = { text ->
                    when (validationType) {
                        TextFieldValidation.EMAIL -> {
                            currentTextState = text
                            textIsNotEmpty(
                                text,
                                validation = emailValidations(text),
                                onSuccess = {
                                    onTextChange.invoke(it to true)
                                    error = String.Empty
                                },
                                onError = {
                                    onTextChange.invoke(String.Empty to false)
                                    error = errorEmail
                                }
                            )
                        }

                        TextFieldValidation.NAME -> {
                            currentTextState = text
                            textIsNotEmpty(
                                text = text,
                                validation = text.trim().isNotEmpty() &&
                                        text.containsNoEmojis() &&
                                        text.isValidLength(1, 20),
                                onSuccess = {
                                    onTextChange.invoke(it to true)
                                    error = String.Empty
                                },
                                onError = {
                                    onTextChange.invoke(String.Empty to false)
                                    error = errorName
                                }
                            )
                        }

                        TextFieldValidation.USER_NAME -> {
                            currentTextState = text
                            textIsNotEmpty(
                                text = text,
                                validation = text.trim().isNotEmpty() &&
                                        text.containsNoEmojis() &&
                                        text.isValidLength(1, 15),
                                onSuccess = {
                                    onTextChange.invoke(it to true)
                                    error = String.Empty
                                },
                                onError = {
                                    onTextChange.invoke(String.Empty to false)
                                    error = errorUserName
                                }
                            )
                        }

                        TextFieldValidation.LAST_NAME -> {
                            currentTextState = text
                            textIsNotEmpty(
                                text,
                                validation = text.containsNoEmojis() && text.isValidLength(1, 20),
                                onSuccess = {
                                    onTextChange.invoke(it to true)
                                    error = String.Empty
                                },
                                onError = {
                                    onTextChange.invoke(String.Empty to false)
                                    error = errorLastName
                                }
                            )
                        }

                        TextFieldValidation.PASSWORD -> {
                            currentTextState = text
                            textIsNotEmpty(
                                text,
                                validation = text.containsNoEmojis() && text.isValidLength(1, 15),
                                onSuccess = {
                                    onTextChange.invoke(it to true)
                                    error = String.Empty
                                },
                                onError = {
                                    onTextChange.invoke(String.Empty to false)
                                    error = errorPassword
                                }
                            )
                        }

                        else -> Unit
                    }
                }
            )
        }
        AnimatedVisibility(visible = error.isNotEmpty()) {
            TextErrorComponent(
                modifier = Modifier.padding(top = 8.dp),
                error
            )
        }
    }
}

private fun emailValidations(text: String) =
    text.isEmailValid() && text.containsNoEmojis() && text.isValidLength(5, 60)

@Composable
private fun getBorderColor(error: String) =
    if (error.isNotEmpty()) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiaryContainer

private fun textIsNotEmpty(
    text: String,
    validation: Boolean,
    onSuccess: (text: String) -> Unit,
    onError: () -> Unit
) {
    if (text.isNotEmpty() && validation) {
        onSuccess.invoke(text)
    } else {
        onError.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextDecorationBox(
    currentTextState: String,
    innerTextField: @Composable () -> Unit,
    enabled: Boolean,
    interaction: MutableInteractionSource,
    colors: TextFieldColors,
    label: String,
    textSize: TextUnit
) {
    TextFieldDefaults.DecorationBox(
        value = currentTextState,
        innerTextField = innerTextField,
        enabled = enabled,
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        interactionSource = interaction,
        colors = colors,
        contentPadding = PaddingValues(0.dp),
        label = {
            if (label.isNotEmpty()) {
                LabelItem(label, textSize)
            }
        }
    )
}

@Composable
private fun LabelItem(
    label: String,
    textSize: TextUnit
) {
    Text(
        text = label,
        fontSize = textSize,
        color = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Composable
private fun getTextColors(
    enabled: Boolean,
    readOnly: Boolean
): TextFieldColors {
    val containerColor = getBackgroundColor(readOnly, enabled)
    return TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedTextColor = MaterialTheme.colorScheme.primaryContainer,
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = containerColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLabelColor = MaterialTheme.colorScheme.secondaryContainer,
        cursorColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = containerColor,
        disabledIndicatorColor = Color.Transparent
    )
}

@Composable
private fun getBackgroundColor(
    readOnly: Boolean,
    enabled: Boolean
) = when {
    readOnly && enabled.not() -> MaterialTheme.colorScheme.background
    enabled -> MaterialTheme.colorScheme.background
    else -> MaterialTheme.colorScheme.tertiaryContainer
}

@Preview
@Composable
fun EmailCheckoutPreview() {
    TextFieldScreen(
        modifier = Modifier.padding(horizontal = 24.dp),
        enabled = true,
        readOnly = false,
        label = "label",
        onTextChange = { }
    ) { }
}
