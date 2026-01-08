package com.jinnylee.customgallery.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.jinnylee.customgallery.presentation.screen.detail.DetailRoot
import com.jinnylee.customgallery.presentation.screen.gallery.GalleryRoot

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    // 제네릭 타입을 명시하여 타입 미스매치 방지
    val backStack = rememberNavBackStack(Route.Gallery)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Gallery> {
                GalleryRoot(
                    onNavigateToDetail = { image ->
                        backStack.add(Route.Detail(image))
                    }
                )
            }

            entry<Route.Detail> { route ->
                DetailRoot(
                    image = route.image,
                    onBack = {
                        // 특정 route 인스턴스를 제거하여 뒤로 가기 수행
                        backStack.remove(route)
                    }
                )
            }
        }
    )
}
