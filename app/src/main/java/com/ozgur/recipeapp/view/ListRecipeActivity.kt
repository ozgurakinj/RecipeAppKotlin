package com.ozgur.recipeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.ozgur.recipeapp.adapter.RecipeRecyclerAdapter
import com.ozgur.recipeapp.databinding.ActivityListRecipeBinding
import com.ozgur.recipeapp.model.Recipe
import com.ozgur.recipeapp.model.Step
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ListRecipeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListRecipeBinding
    private lateinit var db : FirebaseFirestore
    private lateinit var recipeArrayList : ArrayList<Recipe>
    private lateinit var recipeAdapter : RecipeRecyclerAdapter
    private lateinit var filter : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRecipeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        filter = intent.getStringArrayListExtra("filter")!!

        db = FirebaseFirestore.getInstance()

        recipeArrayList = ArrayList<Recipe>()

        getRecipes()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeRecyclerAdapter(recipeArrayList)
        binding.recyclerView.adapter = recipeAdapter
        recipeAdapter.setOnItemClickListener(object : RecipeRecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int, recipe: Recipe){
                intent = Intent(applicationContext,RecipeActivity::class.java)
                intent.putExtra("recipe",recipe)
                intent.putExtra("stepcount",0)
                startActivity(intent)
            }

        })
    }

    fun getRecipes(){
        db.collection("Recipes")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var ingredients = document.get("ingredients")
                    if(filter.count()>0 && checkFilter(filter, ingredients as ArrayList<String>)==false){

                    }else{
                        val recipe = Recipe(document.get("name") as String,document.get("imageURL") as String, ingredients as ArrayList<String>)
                        var steps = document.get("steps") as ArrayList<HashMap<String, String>>
                        for(step in steps){
                            var recipesteps : Step = Step(step.get("description").toString(),step.get("timerSeconds").toString())
                            recipe.steps.add(recipesteps)
                        }
                        recipeArrayList.add(recipe)
                    }
                }
                recipeAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }

    fun checkFilter(filter : ArrayList<String>, recipe : ArrayList<String>) : Boolean{
        for(item in filter){
            if(item in recipe){
                return false
            }
        }
        return true
    }
}

