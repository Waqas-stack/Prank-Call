package com.o9tech.prankcall.Screen.Apputils



import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun CameraPreviewBox() {
    val context = LocalContext.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    var hasPermission by remember { mutableStateOf(false) }

    // Request Camera Permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasPermission = granted }
    )

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        } else {
            hasPermission = true
        }
    }

    if (hasPermission) {
        Box(
            modifier = Modifier
                .size(140.dp)
//            modifier = Modifier.padding(vertical = 50.dp)
//            modifier = Modifier
//                .size(100.dp)
//                .background(Color.Black, shape = CircleShape),
//            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx).apply {
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                    startCamera(ctx, previewView, cameraExecutor)
                    previewView
                },
                modifier = Modifier.padding(top = 40.dp)
            )
        }
    } else {
        // Show black box if permission is denied
        Spacer(modifier = Modifier.height(4.dp))

//        Box(
//            modifier = Modifier
//                .size(100.dp)
//                .background(Color.Black, shape = CircleShape),
//            contentAlignment = Alignment.Center
//        ) {
//            // Optional: Show text or icon to indicate permission is required
//        }
    }
}

// Function to Start CameraX
private fun startCamera(context: Context, previewView: PreviewView, executor: ExecutorService) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener({
        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

        val preview = Preview.Builder()
            .build()
            .also { it.setSurfaceProvider(previewView.surfaceProvider) } // Important

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_FRONT) // Use front camera
            .build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                context as androidx.lifecycle.LifecycleOwner,
                cameraSelector,
                preview
            )
        } catch (exc: Exception) {
            Log.e("CameraX", "Failed to bind camera use case", exc)
        }
    }, ContextCompat.getMainExecutor(context))
}
