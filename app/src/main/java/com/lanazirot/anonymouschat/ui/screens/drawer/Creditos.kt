package com.lanazirot.anonymouschat.ui.screens.drawer


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.DesignServices
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.preferences.ThemeViewModel
import com.lanazirot.anonymouschat.ui.theme.Anonymous

@Composable
fun Creditos() {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkThemeEnabled by themeViewModel.isDarkThemeEnabled.collectAsState()
    val navController = GlobalProvider.current.navController

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        TopBar(
            title = stringResource(R.string.credits),
            icon = painterResource(R.drawable.icreditos),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.popBackStack() }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                if(isDarkThemeEnabled){
                    Image(
                        painter = painterResource(id = R.drawable.logo), contentDescription = "", modifier =
                        Modifier
                            .width(150.dp)
                            .height(100.dp)
                    )
                }else{

                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Computer,
                    contentDescription = "",
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = " Backend ",
                    color = MaterialTheme.colors.secondary,
                    fontFamily = Anonymous,
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Right
                )
            }
            Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End) {
                Text(
                    text = "Alan Peña Ortiz",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Right
                )
                Text(
                    text = "Alan Castro Cruz",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Right
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = " Frontend ",
                    color = MaterialTheme.colors.secondary,
                    fontFamily = Anonymous,
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Left
                )
                Icon(
                    imageVector = Icons.Outlined.DesignServices,
                    contentDescription = "",
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(
                text = "Ezequiel Cantu",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Left
            )
            Text(
                text = "Andrea Martinez",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Left
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notes,
                    contentDescription = "",
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = " Testing ",
                    color = MaterialTheme.colors.secondary,
                    fontFamily = Anonymous,
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Right
                )
            }
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End) {
                Text(
                    text = "Eduardo Jared",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}
