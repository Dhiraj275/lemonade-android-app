package `in`.coverallweb.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.coverallweb.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}


@Composable
fun LemonTextAndImage(
    lemonImageId: Int,
    lemonText: String,
    clickFun: ()-> Unit
){
    Button(
        onClick = clickFun,
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ){
        Image(
            painter = painterResource(lemonImageId),
            contentDescription = lemonText,
            modifier = Modifier.padding(30.dp)
            )
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
    Text(lemonText)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var currentStep by remember {mutableStateOf(1)}
    var currentSqueezeCount by remember {mutableStateOf(1)}
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ){
            innerPadding -> Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when(currentStep){
                1->{
                    LemonTextAndImage(
                        lemonImageId = R.drawable.lemon_tree,
                        lemonText = "Tap the lemon tree to select a lemon",
                        clickFun = {
                            currentStep=2
                            currentSqueezeCount = (2..4).random()
                        }
                    )
                }
                2->{
                    LemonTextAndImage(
                        lemonImageId = R.drawable.lemon_squeeze,
                        lemonText = "Keep tapping the lemon to squeeze it",
                        clickFun = {
                            currentSqueezeCount--
                            if(currentSqueezeCount==0){
                                currentStep=3
                            }
                        }
                    )
                }
                3->{
                    LemonTextAndImage(
                        lemonImageId = R.drawable.lemon_drink,
                        lemonText = "Tap the lemonade to drink it",
                        clickFun = { currentStep=4 }
                    )
                }
                4->{
                    LemonTextAndImage(
                        lemonImageId = R.drawable.lemon_restart,
                        lemonText = "Tap the empty glass to start again",
                        clickFun = { currentStep=1 }
                    )
                }
            }



        }
    }
    }


}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}
