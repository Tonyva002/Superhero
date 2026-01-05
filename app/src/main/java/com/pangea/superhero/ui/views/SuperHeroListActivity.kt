package com.pangea.superhero.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pangea.superhero.R
import com.pangea.superhero.databinding.ActivitySuperHeroListBinding
import com.pangea.superhero.ui.adapters.SuperHeroAdapter
import com.pangea.superhero.ui.state.SuperHeroUiState
import com.pangea.superhero.ui.viewModel.SuperHeroViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SuperHeroListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "hero_id"
    }

    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var adapter: SuperHeroAdapter
    private val viewModel: SuperHeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
        initSearchView()
        observeUiState()
    }

    private fun initRecyclerView() {
        adapter = SuperHeroAdapter { heroId ->
            navigateToDetail(heroId)
        }

        binding.rvSuperhero.apply {
            layoutManager = LinearLayoutManager(this@SuperHeroListActivity)
            setHasFixedSize(true)
            adapter = this@SuperHeroListActivity.adapter
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is SuperHeroUiState.Idle -> {
                            binding.progressBar.isVisible = false
                            binding.tvTitle.isVisible = true
                        }

                        is SuperHeroUiState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.tvTitle.isVisible = false
                        }

                        is SuperHeroUiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.tvTitle.isVisible = false
                            adapter.submitList(state.heroes)
                        }

                        is SuperHeroUiState.Error -> {
                            binding.progressBar.isVisible = false
                            showError(state.message)
                        }
                    }
                }
            }
        }
    }

    private fun showError(message: String? = null) {
        Toast.makeText(this, message ?: "Error loading superheroes", Toast.LENGTH_LONG).show()
    }


    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }


}