package de.fhe.ai.weemeal.shoppinglist


import android.widget.RemoteViews
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.components.CustomChip
import de.fhe.ai.weemeal.common.components.ListComponent
import de.fhe.ai.weemeal.common.components.SearchAppBar
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.domain.models.ShoppingList
import de.fhe.ai.weemeal.mocks.ShoppingListMock
import kotlinx.coroutines.ExperimentalCoroutinesApi

// @Preview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ShoppingListScreen(
//    shoppingListState: ShoppingListState,
//    navHostController: NavHostController,
//    onTriggerEvent: (ShoppingListEvents) -> Unit,
//    onClickOpenShoppingList: (Int) -> Unit,
) {
    WeeMealTheme(
//        displayProgressBar = shoppingListState.isLoading,
    ) {
        Scaffold(
            //            topBar = {
            //                AppBar(title = "ShoppingList")
            //            },
            //            bottomBar = { BottomBar(navController) },

            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp),
                    text =  {  Text(text = "Teilen") },
                    icon = { Icon(Filled.Share, "")}
                )
            }

        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column {
                    var shoppingList: ShoppingList = ShoppingListMock.generateShoppingList()
                    shoppingList?.let {
                        ShoppingList(shoppingList)
                    } ?: kotlin.run {
//                        TODO: String ressource location correct?
                        //Text(stringResource(de.fhe.ai.weemeal.recipe.R.string.no_recipes))
                    }

                }
            }
        }
    }
}

@Composable
private fun ShoppingList(shoppingList: ShoppingList) {
    LazyColumn {
        itemsIndexed(
            items = shoppingList.items
        ) { index, ingredient ->
            ShoppingListItem(ingredient)
        }
    }

}

@Composable
fun ShoppingListItem(ingredient: Ingredient) {
    Card(
        backgroundColor = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)

    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //            TODO: Use Image of Food/Recipe
                Icon(
                    imageVector = Filled.Face,
                    contentDescription = "Dummy-Image",
                    modifier = Modifier.size(70.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = ingredient.name,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )

                Text(
                    text = ingredient.quantity.quantity.toString()+" "+ingredient.quantity.unit,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold

                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )


            }
        }
    }

}














