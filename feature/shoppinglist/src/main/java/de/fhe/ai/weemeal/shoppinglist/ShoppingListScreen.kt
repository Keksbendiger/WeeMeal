package de.fhe.ai.weemeal.shoppinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.ShoppingList
import de.fhe.ai.weemeal.mocks.domain.ShoppingListMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.compose.material.FabPosition.Companion as FabPosition1

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ShoppingListScreen(vm: ShoppingListScreenViewModel) {
    Scaffold(
        //            topBar = {
        //                AppBar(title = "ShoppingList")
        //            },
        //            bottomBar = { BottomBar(navController) },

        floatingActionButtonPosition = FabPosition1.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(6.dp),
                text = { Text(text = "Teilen") },
                icon = { Icon(Filled.Share, "") }
            )
        }

    ) {
        val shoppingList = vm.shoppingList
        Box(modifier = Modifier.padding()) {
            Column {
                ShoppingList(shoppingList)
            }
        }
    }
}

@Composable
private fun ShoppingList(shoppingList: ShoppingList) {
    LazyColumn {
        itemsIndexed(
            items = shoppingList.items
        ) { _, ingredient ->
            ShoppingListItem(ingredient)
        }
    }
}

@Composable
fun ShoppingListItem(ingredient: Ingredient) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)

    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //            TODO: Use Image of Food/Recipe
                Image(
                    painterResource(id = ingredient.image),
                    contentDescription = "Dummy-Image",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(50.dp))
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
                    text = ingredient.quantity.quantity.toString() + " " + ingredient.quantity.unit,
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
