package udb.edu.calculadorapromedio_01

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    lateinit var inputName: EditText
    lateinit var inputNota01: EditText
    lateinit var inputNota02: EditText
    lateinit var inputNota03: EditText
    lateinit var inputNota04: EditText
    lateinit var inputNota05: EditText
    lateinit var btnCalcular: Button
    lateinit var txtPromedio: TextView
    lateinit var txtEstado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asignaciones de elementos a variables locales
        inputName = findViewById(R.id.inputName)
        inputNota01 = findViewById(R.id.inputNota01)
        inputNota02 = findViewById(R.id.inputNota02)
        inputNota03 = findViewById(R.id.inputNota03)
        inputNota04 = findViewById(R.id.inputNota04)
        inputNota05 = findViewById(R.id.inputNota05)
        btnCalcular = findViewById(R.id.btnCalcular)
        txtPromedio = findViewById(R.id.txtPromedio)
        txtEstado = findViewById(R.id.txtEstado)

        btnCalcular.setOnClickListener{
            calcularPromedio()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun calcularPromedio() {

        val inputNameText = inputName.text.toString()
        val inputNota01Text = inputNota01.text.toString()
        val inputNota02Text = inputNota02.text.toString()
        val inputNota03Text = inputNota03.text.toString()
        val inputNota04Text = inputNota04.text.toString()
        val inputNota05Text = inputNota05.text.toString()

        // Verificación de los campos de entrada no estén vacíos
        if (inputNameText.isEmpty() || inputNota01Text.isEmpty() || inputNota02Text.isEmpty() || inputNota03Text.isEmpty() || inputNota04Text.isEmpty() || inputNota05Text.isEmpty()) {
            Toast.makeText(this, "Por favor, no deje campos vacios",
                Toast.LENGTH_SHORT).show()
            return
        }

        // Conversión de las entradas de texto a números de tipo Double
        val nota1 = inputNota01Text.toDouble()
        val nota2 = inputNota02Text.toDouble()
        val nota3 = inputNota03Text.toDouble()
        val nota4 = inputNota04Text.toDouble()
        val nota5 = inputNota05Text.toDouble()

        // Verificación de las notas entre 0 y 10
        if (!verificarNotas(nota1) || !verificarNotas(nota2) || !verificarNotas(nota3) ||
            !verificarNotas(nota4) || !verificarNotas(nota5) ) {
            Toast.makeText(this, "Las notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
            return
        }

        var total = (nota1 * 0.15) + (nota2 * 0.15) + (nota3 * 0.2) + (nota4 * 0.25) + (nota5 * 0.25)
        total = BigDecimal(total).setScale(2, RoundingMode.HALF_UP).toDouble()

        txtPromedio.text =  "Promedio: " + total.toString()

        if(total >= 6){
            txtEstado.text = "APROBADO"
        }else{
            txtEstado.text = "REPROBADO"
        }


    }

    private fun verificarNotas(nota: Double?): Boolean {
        return nota != null && nota in 0.0..10.0
    }
}

