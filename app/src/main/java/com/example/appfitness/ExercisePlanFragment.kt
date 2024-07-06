package com.example.appfitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class ExercisePlanFragment : Fragment() {

    private lateinit var exerciseEditText: EditText
    private lateinit var exercisePlanTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_plan, container, false)

        exerciseEditText = view.findViewById(R.id.exerciseEditText)
        exercisePlanTextView = view.findViewById(R.id.exercisePlanTextView)
        val addButton: Button = view.findViewById(R.id.addButton)
        val clearButton: Button = view.findViewById(R.id.clearButton)

        addButton.setOnClickListener {
            addExercise()
        }

        clearButton.setOnClickListener {
            clearExercises()
        }

        return view
    }

    private fun addExercise() {
        val exercise = exerciseEditText.text.toString()
        if (exercise.isNotEmpty()) {
            exercisePlanTextView.append("\n$exercise")
            exerciseEditText.text.clear()
        }
    }

    private fun clearExercises() {
        exercisePlanTextView.text = "Plan ćwiczeń na dziś"
    }
}
