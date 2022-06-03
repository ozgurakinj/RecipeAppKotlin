package com.ozgur.recipeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ozgur.recipeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun allRecipes(view : View){
        val intent = Intent(applicationContext, ListRecipeActivity::class.java)
        intent.putExtra("filter",ArrayList<String>())
        startActivity(intent)
    }

    fun listIngredients(view : View){
        val intent = Intent(applicationContext, ListIngredientsActivity::class.java)
        startActivity(intent)
    }
}