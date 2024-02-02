package com.example.istrazi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.istrazi.R.*

class MainActivity : AppCompatActivity(){

    private var trenutniBroj=StringBuilder()
    //klasa stringBuilder pravi prazan string u koji se dodatni charovi ili stringovi ubacuju pozivom fje append
    private lateinit var dugmadCifre:Array<Button>
    private lateinit var dugmadOperacije:Array<Button>
    private var operator: Operator = Operator.NONE
    private var kliknutOperator = false
    //lateinit -> kasnije ce se inicijalizovati (later initialized)
    private lateinit var prikazBroja: TextView
    private var prviBroj = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        prikazBroja = findViewById(R.id.prikazBroja)
        inicijalizacijaBajo()
    }

    private fun inicijalizacijaBajo() {

        val button9: Button = findViewById(R.id.button9)
        val button8: Button = findViewById(R.id.button8)
        val button7: Button = findViewById(R.id.button7)
        val button6: Button = findViewById(R.id.button6)
        val button5: Button = findViewById(R.id.button5)
        val button4: Button = findViewById(R.id.button4)
        val button3: Button = findViewById(R.id.button3)
        val button2: Button = findViewById(R.id.button2)
        val button1: Button = findViewById(R.id.button1)
        val button0: Button = findViewById(R.id.button0)
        dugmadCifre = arrayOf(button0,button1,button2,button3,button4,button5,button6,button7,button8,button9)
        for (i in dugmadCifre){
            i.setOnClickListener { klikNaCifru(i) }
        }

        val dugmePlus:Button = findViewById(R.id.dugmePlus)
        val dugmeMinus:Button = findViewById(R.id.dugmeMinus)
        val dugmePuta:Button = findViewById(R.id.dugmePuta)
        val dugmePodeljeno:Button = findViewById(R.id.dugmePodeljeno)
        dugmadOperacije = arrayOf(dugmePlus,dugmeMinus,dugmePuta,dugmePodeljeno)
        for(i in dugmadOperacije){
            i.setOnClickListener { klikNaOperator(i) }
        }
        val dugmeJednako:Button = findViewById(R.id.dugmeJednako)
        dugmeJednako.setOnClickListener {klikNaJednako()}
    }

    private fun klikNaJednako() {
        val drugiBroj = trenutniBroj.toString().toInt()
        var rezultat:Int
        when(operator){
            Operator.PLUS -> rezultat = prviBroj+drugiBroj
            Operator.MINUS -> rezultat = prviBroj-drugiBroj
            Operator.MNOZI -> rezultat = prviBroj*drugiBroj
            Operator.DELI -> rezultat = if (drugiBroj != 0) prviBroj / drugiBroj else 0
            else -> rezultat=0
        }
        trenutniBroj.clear()
        trenutniBroj.append(rezultat.toString())
        prikazBroja.text=trenutniBroj
        kliknutOperator=true
    }


private fun klikNaOperator(i: Button) {
    operator = when (i.text) {
        "+" -> Operator.PLUS
        "-" -> Operator.MINUS
        "*" -> Operator.MNOZI
        "/" -> Operator.DELI
        else -> Operator.NONE
    }
    kliknutOperator = true
}


    private fun klikNaCifru(dugme: Button) {
        if(kliknutOperator){
            prviBroj = trenutniBroj.toString().toInt()
            trenutniBroj.clear()
            kliknutOperator = false
        }
        trenutniBroj.append(dugme.text)
        prikazBroja.text = trenutniBroj
    }
}

enum class Operator {PLUS, MINUS, MNOZI, DELI, NONE}