
package com.ozgur.recipeapp.model
import java.io.Serializable

data class Recipe(val name: String, val imageURL: String, val ingredients: ArrayList<String>) : Serializable{
    var steps : ArrayList<Step> = ArrayList<Step>()
}