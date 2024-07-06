package com.example.appfitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProductCaloriesFragment : Fragment() {

    private lateinit var ingredientsContainer: LinearLayout
    private lateinit var caloriesTextView: TextView
    private val ingredientViews = mutableListOf<View>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_calories, container, false)

        ingredientsContainer = view.findViewById(R.id.ingredientsContainer)
        caloriesTextView = view.findViewById(R.id.caloriesTextView)
        val addIngredientButton: Button = view.findViewById(R.id.addIngredientButton)
        val calculateButton: Button = view.findViewById(R.id.calculateButton)
        val clearIngredientsButton: Button = view.findViewById(R.id.clearIngredientsButton)

        addIngredientButton.setOnClickListener {
            addIngredientView()
        }

        calculateButton.setOnClickListener {
            calculateCalories()
        }

        clearIngredientsButton.setOnClickListener {
            clearIngredients()
        }

        return view
    }

    private fun addIngredientView() {
        val ingredientView = layoutInflater.inflate(R.layout.item_ingredient, ingredientsContainer, false)
        ingredientsContainer.addView(ingredientView)
        ingredientViews.add(ingredientView)
    }

    private fun calculateCalories() {
        var totalCalories = 0.0
        for (view in ingredientViews) {
            val weightEditText: EditText = view.findViewById(R.id.weightEditText)
            val ingredientSpinner: Spinner = view.findViewById(R.id.ingredientSpinner)
            val weight = weightEditText.text.toString().toDoubleOrNull()
            if (weight != null) {
                val caloriesPerGram = getCaloriesPerGram(ingredientSpinner.selectedItem.toString())
                totalCalories += weight * caloriesPerGram
            }
        }
        caloriesTextView.text = "Całkowita ilość kalorii: %.2f kcal".format(totalCalories)
    }

    private fun getCaloriesPerGram(ingredient: String): Double {
        return when (ingredient) {
            "Pierś z kurczaka" -> 1.65 // 1g = 1.65 kcal
            "Chleb" -> 2.5
            "Ser" -> 4.0
            "Jabłko" -> 0.52
            "Banany" -> 0.89
            "Pomidor" -> 0.18
            "Ogórek" -> 0.16
            // Dodaj więcej składników tutaj
            else -> 0.0
        }
    }

    private fun clearIngredients() {
        ingredientsContainer.removeAllViews()
        ingredientViews.clear()
        caloriesTextView.text = "Całkowita ilość kalorii: 0.0 kcal"
    }
}
