package udb.edu.sv

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private lateinit var inputName : EditText
    private lateinit var inputSalario : EditText
    private lateinit var btnCalcular : Button
    private lateinit var txtNombre : TextView
    private lateinit var txtRenta : TextView
    private lateinit var txtAFP : TextView
    private lateinit var txtISSS : TextView
    private lateinit var txtSalarioNeto : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputName = findViewById(R.id.inputName)
        inputSalario = findViewById(R.id.inputSalario)
        btnCalcular = findViewById(R.id.btnCalcular)
        txtNombre = findViewById(R.id.nombre)
        txtRenta = findViewById(R.id.renta)
        txtAFP = findViewById(R.id.afp)
        txtISSS = findViewById(R.id.isss)
        txtSalarioNeto = findViewById(R.id.salarioNeto)

        btnCalcular.setOnClickListener{
            calcularSalario()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun calcularSalario() {
        var nombre = inputName.text.toString()
        var txtSalario = inputSalario.text.toString()

        //Validaciones
        if(nombre.isEmpty() || txtSalario.isEmpty()){
            Toast.makeText(this, "Por favor, complete ambos campos", Toast.LENGTH_SHORT).show()
            return
        }

        var salario = txtSalario.toDouble()
        var renta: Double
        var porcentajeRenta = 0.0
        var exceso = 0.0
        var cuotaFja = 0.0

        var afp: Double
        var isss: Double
        var salarioNeto = 0.0

        //CALCULO ISSS
        isss = salario * 0.03
        isss = BigDecimal(isss).setScale(2, RoundingMode.HALF_UP).toDouble()
        isss = if (isss > 30.0) 30.0 else isss //ISSS tiene un techo de $30

        //CALCULO AFP
        afp = salario * 0.0725
        afp = BigDecimal(afp).setScale(2, RoundingMode.HALF_UP).toDouble()

        salario -= (isss + afp) //Salario menos AFP y ISSS


        //CALCULO RENTA
        when(salario){
            in 0.0..472.0 -> {
                porcentajeRenta = 0.0
                exceso = 0.0
                cuotaFja = 0.0
            }
            in 472.1..895.24 -> {
                porcentajeRenta = 0.10
                exceso = 472.0
                cuotaFja = 17.67
            }
            in 895.25..2038.10 -> {
                porcentajeRenta = 0.20
                exceso = 895.24
                cuotaFja = 60.0
            }
            in 2038.11..Double.MAX_VALUE -> {
                porcentajeRenta = 0.30
                exceso = 2038.10
                cuotaFja = 288.57
            }

        }
        renta = ((salario - exceso) * porcentajeRenta) + cuotaFja
        renta = BigDecimal(renta).setScale(2, RoundingMode.HALF_UP).toDouble()


        salario -= renta
        salario = BigDecimal(salario).setScale(2, RoundingMode.HALF_UP).toDouble()

        //Asignar variables calculadas a textView
        txtNombre.text = nombre
        txtRenta.text =  "$ " + renta.toString()
        txtAFP.text =  "$ " +  afp.toString()
        txtISSS.text =  "$ " +  isss.toString()
        txtSalarioNeto.text =  "$ " +  salario.toString()
    }
}