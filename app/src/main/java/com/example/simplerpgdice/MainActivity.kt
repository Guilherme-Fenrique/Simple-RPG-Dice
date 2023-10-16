package com.example.simplerpgdice

import android.app.Activity
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.simplerpgdice.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var biding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        biding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(biding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()


        var qtdDado = biding.qtdDado.text.toString().dropLast(1).toInt()
        var mod = biding.qtdMod.text.toString().toInt()

        biding.btnAdd.setOnClickListener() {
            qtdDado++
            biding.qtdDado.text = "$qtdDado" + "d"
        }
        biding.btnRemove.setOnClickListener() {
            if (qtdDado > 1)
                qtdDado--
            biding.qtdDado.text = "$qtdDado" + "d"
        }

        biding.btnAddMod.setOnClickListener() {
            mod++
            biding.qtdMod.text = "$mod"
        }
        biding.btnRemoveMod.setOnClickListener() {
            mod--
            biding.qtdMod.text = "$mod"
        }

        biding.d4.setOnClickListener() {
            rolarDado(qtdDado, 4, mod)
        }
        biding.d6.setOnClickListener() {
            rolarDado(qtdDado, 6, mod)
        }
        biding.d8.setOnClickListener() {
            rolarDado(qtdDado, 8, mod)
        }
        biding.d10.setOnClickListener() {
            rolarDado(qtdDado, 10, mod)
        }
        biding.d12.setOnClickListener() {
            rolarDado(qtdDado, 12, mod)
        }
        biding.d20.setOnClickListener() {
            rolarDado(qtdDado, 20, mod)
        }
        biding.d100.setOnClickListener() {
            rolarDado(qtdDado, 100, mod)
        }


    }

    private fun rolarDado(qtdDado: Int, numeroLados: Int, mod: Int) {



        var rollArray = IntArray(qtdDado)

        for (i in 0 until qtdDado) {
            rollArray[i] = Random.nextInt(1, numeroLados + 1)

        }

        biding.tvResultado.text = (rollArray.sum() + mod).toString()

        if (qtdDado > 1 || mod != 0) {
            biding.tvDadosRolados.visibility = View.VISIBLE
            biding.tvDadosRolados.text = "Rolou   D$numeroLados: ${rollArray.joinToString()}"
            biding.tvModResultado.visibility = View.VISIBLE
            biding.tvModResultado.text = "Modificador: +($mod)"

        } else {
            biding.tvDadosRolados.visibility = View.GONE
            biding.tvModResultado.visibility = View.GONE

        }


        if (numeroLados == 20 && rollArray.sum() == 1) {
            var natOne = MediaPlayer.create(this, R.raw.laugh)

            natOne.start()


        }
        if (numeroLados == 20 && rollArray.sum() == 20) {
            var natTwenty = MediaPlayer.create(this, R.raw.cheer)
            natTwenty.start()

        }


    }
}


