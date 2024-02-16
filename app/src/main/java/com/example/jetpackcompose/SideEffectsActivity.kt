package com.example.jetpackcompose

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SideEffectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Counter()
            //LaunchEffectComposable()
            //RememberCoroutine()
            //CounterSet()
            //Example2()
            //DisposableEffectComposable()
            //MediaComposable()
            //KeyboardStateExample()
            //CounterProduceState()
            //Loader()
            DerivedState()
        }
    }
}

/* ---------------- Launched Effect ----------------*/

@Composable
fun Counter() {
    val count = remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val key = count.intValue % 3 == 0
        // when key value update then LaunchedEffect Logged
        // 0 % 3 == 0; true, Log 0
        // 1 % 3 == 1; false, Log 1
        // 2 % 3 == 1; false, no Log
        // 3 % 3 == 0; true, Log 3
        LaunchedEffect(key1 = key) {
            Log.d("SideEffect", "LaunchedEffect: ${count.intValue}")
        }
        Text(text = "Increment Counter: ${count.intValue}")
        Button(onClick = {
            count.intValue++
        }) {
            Text(text = "Increment Counter")
        }
    }
}

@Composable
fun LaunchEffectComposable() {
    val count = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        Log.d("LaunchEffect", "LaunchEffect Started...")
        try {
            for (i in 1..10) {
                count.intValue = i
                delay(1000)
            }
        } catch (e: Exception) {
            Log.d("LaunchEffect", "Exception -: ${e.message.toString()}")
        }
    }

    var text = "Counter is running : ${count.intValue}"
    if (count.intValue == 10) text = "Counter Stopped"
    Text(text = text)
}

@Composable
fun RememberCoroutine() {
    val count = remember {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()

    var text = "Counter is running : ${count.intValue}"
    if (count.intValue == 10) text = "Counter Stopped"

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
        Log.d("LaunchEffect", text)

        Button(onClick = {
            //event we can rememberCoroutineScope
            scope.launch {
                try {
                    for (i in 1..10) {
                        count.intValue = i
                        delay(1000)
                    }
                } catch (e: Exception) {
                    Log.d("LaunchEffect", "Exception -: ${e.message.toString()}")
                }
            }
        }) {
            Text(text = "Start")
        }
    }
}

/*  RememberUpdatedState  */
@Composable
fun CounterSet() {
    val counter = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        delay(1000)
        Log.d("RememberUpdatedState1", counter.intValue.toString())
        for (i in 1..5)
            counter.intValue = 10 + i
    }

    CounterShow(counter.intValue)
}

@Composable
fun CounterShow(value: Int) {
    Log.d("RememberUpdatedState3", value.toString())
    val state = rememberUpdatedState(newValue = value)

    //only latest update value call
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        Log.d("RememberUpdatedState2", state.value.toString())
    }
    Text(text = value.toString())
}

fun a() {
    Log.d("ExampleRememberUpdatedState", "I am from a")
}

fun b() {
    Log.d("ExampleRememberUpdatedState", "I am from b")
}

@Composable
fun Example2() {
    val state = remember {
        mutableStateOf(::a)
    }
    Button(onClick = { state.value = ::b }) {
        Text(text = "Click to change state")
    }

    LandingScreen(state.value)
}

@Composable
fun LandingScreen(value: () -> Unit) {
    val currentOnValue by rememberUpdatedState(newValue = value)

    LaunchedEffect(key1 = Unit) {
        delay(5000)
        currentOnValue()
    }
}

/* ---------------- Disposable Effect ----------------*/
@Composable
fun DisposableEffectComposable() {
    val state = remember {
        mutableStateOf(false)
    }
    DisposableEffect(key1 = state.value) {
        Log.d("DisposableEffect", "DisposableEffectComposable: Started")
        onDispose {
            Log.d("DisposableEffect", "Cleaning DisposableEffect")
        }
    }
    Button(onClick = { state.value = !state.value }) {
        Text(text = "Change Start")
    }
}

/* Example */
@Composable
fun MediaComposable() {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.christmas)
        mediaPlayer.start()
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}

/* Example */
@Composable
fun KeyboardStateExample() {
    TextField(value = "", onValueChange = {})

    val view = LocalView.current
    DisposableEffect(Unit) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(view)
            val isKeyboardVisible = insets?.isVisible(WindowInsetsCompat.Type.ime())
            Log.d("Keyboard", isKeyboardVisible.toString())
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}

/* ---------------- ProduceState ----------------*/
@Composable
fun CounterProduceState() {
    //State + LaunchedEffect combine
    val state = produceState(initialValue = 0) {
        for (i in 1..5) {
            delay(1000)
            value += 1
        }
    }
    Text(
        text = state.value.toString(),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun Loader() {
    val degree = produceState(initialValue = 0){
        while (true){
            delay(16)
            value = (value + 10) % 360
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f),
        content = {
            Image(
                imageVector = Icons.Default.Refresh,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .rotate(degree.value.toFloat())
            )
        }
    )
}

/* ---------------- DerivedState ----------------*/

@Composable
fun DerivedState() {
    val tableOf = remember {
        mutableIntStateOf(5)
    }
//    val index = remember {
//        mutableIntStateOf(1)
//    }

    val index = produceState(initialValue = 1) {
        repeat(9){
            delay(1000)
            value+=1
        }
    }

    //multiple state to single state combine
    val message = remember(tableOf.intValue, index.value) {
        derivedStateOf {
            "${tableOf.intValue} * ${index.value} = ${tableOf.intValue * index.value}"
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Text(
            text = message.value,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
