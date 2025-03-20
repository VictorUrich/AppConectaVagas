package com.example.appestagionovo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appestagionovo.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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

        binding.btnCadastrarVaga.setOnClickListener {
            val tituloVaga = binding.edtTituloVaga.text.toString()
            val nomeEmpresa = binding.edtnomeEmpresa.text.toString()
            val descricaoVaga = binding.edtDescricaoVaga.text.toString()
            val requisitosVaga = binding.edtRequisitosVaga.text.toString()
            val localizacao = binding.edtLocalizacao.text.toString()
            val contato = binding.edtContato.text.toString()

            if(tituloVaga.isEmpty() || nomeEmpresa.isEmpty() || descricaoVaga.isEmpty() || requisitosVaga.isEmpty() || localizacao.isEmpty() || contato.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
            else{

             salvarVaga(tituloVaga, nomeEmpresa, descricaoVaga, requisitosVaga, localizacao, contato)

            }
        }


    }
    private fun salvarVaga(tituloVaga : String, nomeEmpresa : String, descricaoVaga : String, requisitosVaga: String, localizacao: String, contato :String ){

        val mapVaga = hashMapOf(
            "Título" to tituloVaga,
            "Empresa" to nomeEmpresa,
            "Descrição" to descricaoVaga,
            "Requisitos" to requisitosVaga,
            "Localização" to localizacao,
            "Contato" to contato
        )


        db.collection("Vagas").document().set(mapVaga).addOnCompleteListener { tarefa ->
            if(tarefa.isSuccessful){
                Toast.makeText(this, "Vaga cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
                limparCampo()
            } else {
                Toast.makeText(this, "Erro ao cadastrar vaga!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun limparCampo(){
        binding.edtTituloVaga.setText("")
        binding.edtnomeEmpresa.setText("")
        binding.edtDescricaoVaga.setText("")
        binding.edtRequisitosVaga.setText("")
        binding.edtLocalizacao.setText("")
        binding.edtContato.setText("")
    }

}