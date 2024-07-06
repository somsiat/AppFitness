package com.example.appfitness

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.math.pow

class BmiIndicatorFragment : Fragment() {

    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_indicator, container, false)

        heightEditText = view.findViewById(R.id.heightEditText)
        weightEditText = view.findViewById(R.id.weightEditText)
        ageEditText = view.findViewById(R.id.ageEditText)
        resultTextView = view.findViewById(R.id.resultTextView)
        radioGroup = view.findViewById(R.id.radioGroup)
        maleRadioButton = view.findViewById(R.id.maleRadioButton)
        femaleRadioButton = view.findViewById(R.id.femaleRadioButton)
        val calculateButton: Button = view.findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener {
            calculateBmiAndCalories()
        }

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun calculateBmiAndCalories() {
        val height = heightEditText.text.toString().toDoubleOrNull()
        val weight = weightEditText.text.toString().toDoubleOrNull()
        val age = ageEditText.text.toString().toIntOrNull()
        val isMale = maleRadioButton.isChecked

        if (height != null && weight != null && age != null) {
            val bmi = weight / (height / 100).pow(2)
            val bmr = if (isMale) {
                10 * weight + 6.25 * height - 5 * age + 5
            } else {
                10 * weight + 6.25 * height - 5 * age - 161
            }
            resultTextView.text = "BMI: %.2f\nDzienne zapotrzebowanie kaloryczne: %.2f kcal".format(bmi, bmr)
        } else {
            resultTextView.text = "Podaj wartości!"
        }
    }
}
