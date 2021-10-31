package ai.andromeda.mlstarter.ui.selector

import ai.andromeda.mlstarter.ui.theme.MLStarterTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class SelectorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MLStarterTheme {
                    SelectorScreen(
                        onLivePredictionClick = ::navigateToLivePredictionFragment,
                        onImageClassificationClick = ::navigateToImageClassificationFragment,
                        onObjectDetectionClick = ::navigateToObjectDetectionFragment
                    )
                }
            }
        }
    }

    private fun navigateToLivePredictionFragment() {
        findNavController().navigate(
            SelectorFragmentDirections.actionSelectorFragmentToLivePredictionFragment()
        )
    }

    private fun navigateToImageClassificationFragment() {
        findNavController().navigate(
            SelectorFragmentDirections.actionSelectorFragmentToImageClassifiactionFragment()
        )
    }

    private fun navigateToObjectDetectionFragment() {
        findNavController().navigate(
            SelectorFragmentDirections.actionSelectorFragmentToObjectDetectionFragment()
        )
    }
}