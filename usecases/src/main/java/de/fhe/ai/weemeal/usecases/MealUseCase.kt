// package de.fhe.ai.weemeal.usecases
//
// import de.fhe.ai.weemeal.common.DataState
// import de.fhe.ai.weemeal.domain.models.Meal
// import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
// import kotlinx.coroutines.flow.Flow
// import kotlinx.coroutines.flow.flow
// import org.koin.core.component.KoinComponent
// import org.koin.core.component.inject
//
// class SearchRecipes : KoinComponent {
//
//    private val recipeRepository: RecipeRepository by inject()
//
//    /**
//     *
//     * search all meals with the cooking date from today or ahead
//     * emits the result in a data object
//     *
//     * @return DataState
//     */
//    fun execute(): Flow<DataState<List<Meal>>> = flow {
//        try {
//            emit(DataState.loading())
//
//            // TODO: UseCase
//            //  Lade alle Meals mit dem Kochdatum ab heute oder in der Zukunft liegend
//            //  Mappe alle Datenbank MealsEntity zu DomainModels Meal
//
// //            emit(DataState.data(data = LISTE_VON_MEALS))
//        } catch (e: Exception) {
// //            logger.log(e.toString())
//        }
//    }
// }
