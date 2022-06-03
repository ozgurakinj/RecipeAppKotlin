package com.ozgur.recipeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ozgur.recipeapp.databinding.ActivityRecipeBinding
import com.ozgur.recipeapp.model.Recipe
import com.squareup.picasso.Picasso

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecipeBinding
    private lateinit var recipe: Recipe
    private var stepcount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        stepcount = 0
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recipe = intent.extras!!.getSerializable("recipe") as Recipe
        setTitle(recipe.name)
        loadStep(stepcount)
        Picasso.get().load(recipe.imageURL).into(binding.recipeImage)

        binding.previousStep.setOnClickListener(){
            previousStep()
        }
        binding.nextStep.setOnClickListener(){
            nextStep()
        }
        binding.endRecipeButton.setOnClickListener(){
            finish()
        }
    }

    fun nextStep(){
        stepcount += 1
        loadStep(stepcount)
    }
    fun previousStep(){
        stepcount -= 1
        loadStep(stepcount)
    }
    fun loadStep(stepcount : Int){

        if(stepcount > 0){
            binding.previousStep.visibility = View.VISIBLE
            if(stepcount == recipe.steps.size-1) {
                binding.nextStep.visibility = View.INVISIBLE
                binding.endRecipeButton.visibility = View.VISIBLE
            }else{
                binding.nextStep.visibility = View.VISIBLE
                binding.endRecipeButton.visibility = View.INVISIBLE
            }
        }else {
            binding.previousStep.visibility = View.INVISIBLE
            binding.nextStep.visibility = View.VISIBLE
            binding.endRecipeButton.visibility = View.INVISIBLE
        }

        binding.stepDescription.text = recipe.steps[stepcount].description
    }


}