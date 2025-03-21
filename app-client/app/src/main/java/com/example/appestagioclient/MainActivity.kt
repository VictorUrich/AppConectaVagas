package com.example.appestagioclient

import android.os.Bundle
import android.text.SpannableString
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appestagioclient.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recuperarVaga()

    }

    private fun recuperarVaga(){

        db.collection("Vagas").document("w8tTNxfooHmUQjgLdbhm").get().addOnCompleteListener { documento ->
            if (documento.isSuccessful){
                val titulo = documento.result.get("Título").toString()
                val empresa = documento.result.get("Empresa").toString()
                val descricao = documento.result.get("Descrição").toString()
                val requisitos = documento.result.get("Requisitos").toString()
                val localizacao = documento.result.get("Localização").toString()
                val contato = documento.result.get("Contato").toString()

                binding.txtTituloVaga.text = "Cargo: $titulo"
                binding.txtNomeEmpresa.text = "Empresa: $empresa"
                binding.txtDescricaoVaga.text = "Descrição: $descricao"
                binding.txtRequisitos.text = "Requisitos: $requisitos"
                binding.txtLocalizacao.text = "Localização: $localizacao"
                binding.txtContato.text = "Contato: $contato"

            }
        }



        }






}