package com.pangea.superhero.ui.views

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pangea.superhero.R
import com.pangea.superhero.databinding.ActivityDetailSuperHeroBinding
import com.pangea.superhero.domain.model.PowerStats
import com.pangea.superhero.domain.model.SuperHeroDetail
import com.pangea.superhero.ui.state.SuperHeroDetailUiState
import com.pangea.superhero.ui.viewModel.DetailSuperHeroViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperHeroBinding
    private val viewModel: DetailSuperHeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val heroId = intent.getStringExtra(SuperHeroListActivity.EXTRA_ID) ?: return
        viewModel.loadHero(heroId)

        observeUiState()

    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect { state ->
                    when (state) {
                        is SuperHeroDetailUiState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is SuperHeroDetailUiState.Success -> {
                            binding.progressBar.isVisible = false
                            createUI(state.hero)
                        }

                        is SuperHeroDetailUiState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(this@DetailSuperHeroActivity, state.message, Toast.LENGTH_LONG).show()
                        }

                    }
                }
            }
        }
    }

    private fun createUI(hero: SuperHeroDetail) {
        Picasso.get()
            .load(hero.imageUrl)
            .into(binding.imgSuperhero)

        binding.tvSuperheroName.text = hero.name
        binding.tvSuperheroRealName.text = hero.fullName
        binding.tvPublisher.text = hero.publisher
        
        prepareStats(hero.powerStats)
        
    }

    private fun prepareStats(stats: PowerStats) {
        updateHeight(binding.viewCombat, stats.combat)
        updateHeight(binding.viewDurability, stats.durability)
        updateHeight(binding.viewSpeed, stats.speed)
        updateHeight(binding.viewStrength, stats.strength)
        updateHeight(binding.viewIntelligence, stats.intelligence)
        updateHeight(binding.viewPower, stats.power)
        
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloatOrNull() ?: 0f)
        view.layoutParams = params
    }

    private fun pxToDp(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        ).roundToInt()
    }
}