package com.example.appestagionovo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appestagionovo.api.EndPoint
import com.example.appestagionovo.util.NetworkUtils
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("API_DEBUG", "Chamando getEstagios() dentro de onCreate()")
        getEstagios() // Agora está dentro da classe e pode ser chamada corretamente
    }

    private fun getEstagios() {
        val retrofitClient = NetworkUtils.GetRetrofitInstace("http://192.168.15.6:8080/api/")
        val endpoint = retrofitClient.create(EndPoint::class.java)
        endpoint.getEstagios().enqueue(object : Callback<JsonArray> {  // Alterado para JsonArray
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                Log.d("API_DEBUG", "onResponse chamado")

                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("API_RESPONSE", "Resposta: $data")
                } else {
                    Log.e("API_ERROR", "Erro na resposta: ${response.code()} - ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.e("API_FAILURE", "Falha na requisição: ${t.message}")
            }
        })
    }
}
