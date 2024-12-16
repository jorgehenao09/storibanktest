package com.jhtest.storibanktest.ui.authentication.faceId.compose

import android.Manifest
import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.theme.components.SecondaryButton
import com.jhtest.storibanktest.utils.createFile

@Composable
internal fun FaceIdPhoto(
    modifier: Modifier = Modifier,
    onImageCaptured: (Uri) -> Unit,
) {
    var permissionGranted by rememberSaveable { mutableStateOf(false) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var capturedUri by remember { mutableStateOf<Uri?>(null) }
    val cameraPermissionErrorMessage =
        stringResource(R.string.authentication_view_face_id_permission_denied)

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val cameraController = remember { LifecycleCameraController(context) }
    cameraController.bindToLifecycle(lifecycleOwner)

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                permissionGranted = true
            } else {
                showPermissionError(context, cameraPermissionErrorMessage)
            }
        }

    val file = context.createFile()
    val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

    ElevatedCard(
        modifier =
            modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            ),
        shape = RoundedCornerShape(12.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text =
                    if (capturedUri == null) {
                        stringResource(R.string.authentication_view_face_id_finishing)
                    } else {
                        stringResource(
                            R.string.authentication_view_face_id_process_complete,
                        )
                    },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )

            if (capturedUri == null) {
                if (permissionGranted) {
                    AndroidView(
                        modifier =
                            Modifier
                                .size(175.dp)
                                .align(Alignment.CenterHorizontally),
                        factory = {
                            val previewView =
                                PreviewView(context).apply {
                                    layoutParams = ViewGroup.LayoutParams(200, 200)
                                }
                            cameraProviderFuture.addListener(
                                {
                                    val cameraProvider = cameraProviderFuture.get()
                                    val preview =
                                        Preview.Builder().build().also {
                                            it.surfaceProvider = previewView.surfaceProvider
                                        }

                                    imageCapture = ImageCapture.Builder().build()
                                    try {
                                        cameraProvider.unbindAll()
                                        cameraProvider.bindToLifecycle(
                                            lifecycleOwner,
                                            CameraSelector.DEFAULT_BACK_CAMERA,
                                            preview,
                                            imageCapture,
                                        )
                                    } catch (_: Exception) {
                                    }
                                },
                                ContextCompat.getMainExecutor(context),
                            )
                            previewView
                        },
                    )
                } else {
                    Image(
                        modifier =
                            Modifier
                                .size(200.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 24.dp),
                        painter = painterResource(id = R.drawable.ic_face_id),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                        contentScale = ContentScale.Fit,
                        contentDescription = "faceId",
                    )
                }
            } else {
                Image(
                    modifier =
                        Modifier
                            .size(270.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 24.dp),
                    painter = rememberAsyncImagePainter(capturedUri),
                    contentDescription = "Captured Image",
                )
            }

            SecondaryButton(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                isButtonEnabled = capturedUri == null,
                textValue =
                    if (permissionGranted.not()) {
                        stringResource(R.string.authentication_view_face_id_open_camera)
                    } else {
                        stringResource(
                            R.string.authentication_view_face_id_take_photo,
                        )
                    },
            ) {
                if (permissionGranted.not()) {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                } else {
                    imageCapture?.takePicture(
                        outputOptions,
                        ContextCompat.getMainExecutor(context),
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                val uri = Uri.fromFile(file)
                                capturedUri = uri
                                onImageCaptured(uri)
                            }

                            override fun onError(exception: ImageCaptureException) {
                                onError(exception)
                            }
                        },
                    )
                }
            }
        }
    }
}

private fun showPermissionError(
    context: Context,
    cameraPermissionErrorMessage: String,
) {
    Toast.makeText(context, cameraPermissionErrorMessage, Toast.LENGTH_SHORT).show()
}
