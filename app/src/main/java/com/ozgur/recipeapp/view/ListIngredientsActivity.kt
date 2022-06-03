package com.ozgur.recipeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.ozgur.recipeapp.R
import com.ozgur.recipeapp.databinding.ActivityListIngredientsBinding

class ListIngredientsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListIngredientsBinding

    private lateinit var listViewData : ListView
    private lateinit var adapter : ArrayAdapter<String>
    val choices = arrayOf<String>("Kabak","Soğan","Pirinç","Salça","Makarna","Mantar","Krema","Dana Eti","Kuru Fasulye","Domates","Tavuk","Biber").sorted()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListIngredientsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setTitle("Choose Available Ingredients")
        listViewData = binding.listViewData

        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,choices)
        listViewData.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ingredients_menu,menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id : Int = item.itemId
            if(id==R.id.item_done){
                var notSelected : ArrayList<String> = ArrayList<String>()
                for(i in 0..listViewData.count-1){
                    if(listViewData.isItemChecked(i) == false){
                        notSelected.add(listViewData.getItemAtPosition(i).toString())
                    }
                }
                var intent = Intent(applicationContext, ListRecipeActivity::class.java)
                intent.putExtra("filter",notSelected)
                startActivity(intent)


            }
        return super.onOptionsItemSelected(item)
    }
}