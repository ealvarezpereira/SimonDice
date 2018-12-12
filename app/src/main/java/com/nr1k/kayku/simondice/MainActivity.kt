package com.nr1k.kayku.simondice

import android.content.Context
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
//import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import android.media.MediaPlayer
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity() {
    var valor = 0
    var secuencia: ArrayList<Int> = ArrayList()
    var botones: ArrayList<Int> = ArrayList()
    var nuevoTurno = false
    var cambiar = false
    var m = 0
    private lateinit var mpRojo: MediaPlayer
    private lateinit var mpAzul: MediaPlayer
    private lateinit var mpAmarillo: MediaPlayer
    private lateinit var mpVerde: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_main)
        layoutVectores.visibility = View.INVISIBLE
        val rojo = findViewById<ImageView>(R.id.vRojo)
        val azul = findViewById<ImageView>(R.id.vAzul)
        val amarillo = findViewById<ImageView>(R.id.vAmarillo)
        val verde = findViewById<ImageView>(R.id.vVerde)
        //val mitexto = findViewById<TextView>(R.id.mitexto)


        mpRojo = MediaPlayer.create(this, R.raw.nota1)
        mpAzul = MediaPlayer.create(this, R.raw.nota2)
        mpAmarillo = MediaPlayer.create(this, R.raw.nota3)
        mpVerde = MediaPlayer.create(this, R.raw.nota4)

        rojo.setOnClickListener {
            if (cambiar) {
                botones.add(1)
                lateinit var mpbotones: MediaPlayer
                mpbotones = MediaPlayer.create(this, R.raw.nota1)
                mpbotones.start()

                GlobalScope.launch {
                    rojo.setColorFilter(resources.getColor(R.color.lightred))
                    delay(100L)
                    rojo.setColorFilter(resources.getColor(R.color.red))
                    delay(400L)
                    mpbotones.stop()
                    mpbotones.release()
                }
                comparacion(botones, m)
                if (!nuevoTurno) {
                    m++
                }
            }
        }

        azul.setOnClickListener {
            if (cambiar) {
                botones.add(0)
                lateinit var mpBotones: MediaPlayer
                mpBotones = MediaPlayer.create(this, R.raw.nota2)
                mpBotones.start()

                GlobalScope.launch {
                    azul.setColorFilter(resources.getColor(R.color.lightblue))
                    delay(100L)
                    azul.setColorFilter(resources.getColor(R.color.blue))
                    delay(400L)
                    mpBotones.stop()
                    mpBotones.release()
                }

                comparacion(botones, m)
                if (!nuevoTurno) {
                    m++
                }
            }
        }

        amarillo.setOnClickListener {
            if (cambiar) {
                botones.add(2)
                lateinit var mpBotones: MediaPlayer
                mpBotones = MediaPlayer.create(this, R.raw.nota3)
                mpBotones.start()

                GlobalScope.launch {
                    amarillo.setColorFilter(resources.getColor(R.color.lightyellow))
                    delay(100L)
                    amarillo.setColorFilter(resources.getColor(R.color.yellow))
                    delay(400L)
                    mpBotones.stop()
                    mpBotones.release()
                }
                comparacion(botones, m)
                if (!nuevoTurno) {
                    m++
                }
            }
        }

        verde.setOnClickListener {
            if (cambiar) {
                botones.add(3)
                lateinit var mpBotones: MediaPlayer
                mpBotones = MediaPlayer.create(this, R.raw.nota4)
                mpBotones.start()
                GlobalScope.launch {
                    verde.setColorFilter(resources.getColor(R.color.lightgreen))
                    delay(100L)
                    verde.setColorFilter(resources.getColor(R.color.green))
                    delay(400L)
                    mpBotones.stop()
                    mpBotones.release()
                }
                comparacion(botones, m)
                if (!nuevoTurno) {
                    m++
                }
            }
        }

        start.setOnClickListener {
            layoutMenu.visibility = View.INVISIBLE
            layoutVectores.visibility = View.VISIBLE

            GlobalScope.launch {
                delay(500L)
                secuenciaBotones(this@MainActivity)
            }
        }
    }


    fun secuenciaBotones(contexto: Context) {
        cambiar = false

        val random = Random().nextInt(4)
        secuencia.add(random)


        GlobalScope.launch(Dispatchers.Main) {
            for (i in 0..secuencia.size - 1) {

                when (secuencia.get(i)) {
                    0 -> {

                        lateinit var mpSecuencia: MediaPlayer
                        mpSecuencia = MediaPlayer.create(contexto, R.raw.nota2)
                        mpSecuencia.start()
                        vAzul.setColorFilter(getResources().getColor(R.color.lightblue))
                        delay(500L)
                        vAzul.setColorFilter(getResources().getColor(R.color.blue))
                        mpSecuencia.stop()
                        mpSecuencia.release()
                    }

                    1 -> {
                        lateinit var mpSecuencia: MediaPlayer
                        mpSecuencia = MediaPlayer.create(contexto, R.raw.nota1)
                        mpSecuencia.start()
                        vRojo.setColorFilter(getResources().getColor(R.color.lightred))
                        delay(500L)
                        vRojo.setColorFilter(getResources().getColor(R.color.red))
                        mpSecuencia.stop()
                        mpSecuencia.release()
                    }

                    2 -> {
                        lateinit var mpSecuencia: MediaPlayer
                        mpSecuencia = MediaPlayer.create(contexto, R.raw.nota3)
                        mpSecuencia.start()
                        vAmarillo.setColorFilter(getResources().getColor(R.color.lightyellow))
                        delay(500L)
                        vAmarillo.setColorFilter(getResources().getColor(R.color.yellow))
                        mpSecuencia.stop()
                        mpSecuencia.release()
                    }

                    3 -> {
                        lateinit var mpSecuencia: MediaPlayer
                        mpSecuencia = MediaPlayer.create(contexto, R.raw.nota4)
                        mpSecuencia.start()
                        vVerde.setColorFilter(getResources().getColor(R.color.lightgreen))
                        delay(500L)
                        vVerde.setColorFilter(getResources().getColor(R.color.green))
                        mpSecuencia.stop()
                        mpSecuencia.release()
                    }
                }
                delay(500L)
            }
            cuentaAtras()
        }
    }

    fun cuentaAtras() {

        GlobalScope.launch {

            runOnUiThread {
                run() {
                    mitexto.setText("Adelante!")
                }
            }
            cambiar = true
            delay(500L)
            runOnUiThread {
                run() {
                    mitexto.setText("")
                }
            }
        }
    }

    fun comparacion(boton: ArrayList<Int>, i: Int) {

        //es una gallina
        if (boton.size < secuencia.size) {
            nuevoTurno = false
            if (secuencia.get(i) != boton.get(i)) {
                cambiar = false
                m = 0
                secuencia.clear()
                botones.clear()
                nuevoTurno = true

                GlobalScope.launch {
                    runOnUiThread {
                        run() {
                            mitexto.setText("Has Perdido!")
                        }
                    }
                    delay(1000L)
                    runOnUiThread {
                        run() {
                            mitexto.setText("")
                            layoutMenu.visibility = View.VISIBLE
                            layoutVectores.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        } else if (boton.size == secuencia.size) {
            if (secuencia.get(i) != boton.get(i)) {
                cambiar = false
                m = 0
                secuencia.clear()
                botones.clear()
                nuevoTurno = true
                GlobalScope.launch {

                    runOnUiThread {
                        run() {
                            mitexto.setText("Has Perdido!")
                        }
                    }
                    delay(1000L)

                    runOnUiThread {
                        run() {
                            mitexto.setText("")
                            layoutMenu.visibility = View.VISIBLE
                            layoutVectores.visibility = View.INVISIBLE
                        }
                    }
                }
            } else {
                cambiar = false
                m = 0
                botones.clear()
                nuevoTurno = true

                GlobalScope.launch {
                    delay(1000L)
                    secuenciaBotones(this@MainActivity)
                }
            }
        }
    }
}
