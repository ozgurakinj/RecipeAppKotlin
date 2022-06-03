package com.ozgur.recipeapp.model
import java.io.Serializable


data class Step(val description: String, val timerSeconds: String) : Serializable{
}