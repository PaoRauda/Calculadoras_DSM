package udb.edu.sv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    //Declaracion de variables de interfaz
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var  textViewResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializacion de varibles de la interfaz
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        btnAdd = findViewById(R.id.buttonAdd)
        btnSubtract = findViewById(R.id.buttonSubtract)
        btnMultiply = findViewById(R.id.buttonMultiply)
        btnDivide = findViewById(R.id.buttonDivide)
        textViewResult = findViewById(R.id.textViewResult)

        btnAdd.setOnClickListener { calculate(Operation.ADD) }
        btnSubtract.setOnClickListener { calculate(Operation.SUBTRACT) }
        btnMultiply.setOnClickListener { calculate(Operation.MULTIPLY) }
        btnDivide.setOnClickListener { calculate(Operation.DIVIDE) }
    }

    private fun calculate(operation: Operation) {

        val input1Text = input1.text.toString()
        val input2Text = input2.text.toString()

        //Verificaciones
        if (input1Text.isEmpty() || input2Text.isEmpty()){
            Toast.makeText(this, "Debe ingresar 2 números", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = input1Text.toDouble()
        val num2 = input2Text.toDouble()
        var result = 0.0

        when(operation) {
            Operation.ADD -> result = num1 + num2
            Operation.SUBTRACT -> result = num1 - num2
            Operation.MULTIPLY -> result = num1 * num2
            Operation.DIVIDE -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                    return
                }
                result = num1 / num2
            }
        }

        result = BigDecimal(result).setScale(15, RoundingMode.HALF_UP).toDouble()
        textViewResult.text = getString(R.string.result) + result.toString()

    }
}