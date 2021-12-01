package com.example.razmovies

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.razmovies.adapter.MoviesAdapter
import com.example.razmovies.databinding.FragmentPopularMoviesBinding
import com.example.razmovies.model.MovieViewModel


class PopularMoviesFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()
    private var binding: FragmentPopularMoviesBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPopularMoviesBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel.
        binding.viewModel = viewModel
        binding.photosGrid.adapter = MoviesAdapter()
        viewModel.getPopularMovies()

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.Comedy -> viewModel.getMoviesdiscover(18)
            R.id.Drama -> viewModel.getMoviesdiscover(35)
            R.id.famely -> viewModel.getMoviesdiscover(10751)
            R.id.action -> viewModel.getMoviesdiscover(28)

            else -> viewModel.getPopularMovies()
        }
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}


