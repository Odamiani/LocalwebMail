package br.com.fiap.localwebmail.model

import android.util.Log

import br.com.fiap.localwebmail.service.RetrofitFactory
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Email(
    val id: String = "",
    @SerializedName("titulo") val subject: String = "",
    @SerializedName("idRemetente") val sender: String = "",
    @SerializedName("data") val timestamp: String = "",
    @SerializedName("corpo") val body: String = "",
    var isFavorite: Boolean = false
)

// Lista dinâmica de emails
var emails = mutableListOf<Email>()
val displayedEmailIds = mutableSetOf<String>()


val emailService = RetrofitFactory()


fun fetchEmails(param: (Any) -> Unit) {
    val call = RetrofitFactory().getEmailService().getEmails()

    call.enqueue(object : Callback<List<Email>> {
        override fun onResponse(
            call: Call<List<Email>>,
            response: Response<List<Email>>
        ) {
            if (response.isSuccessful) {
                response.body()?.let { emailList ->

                    // Filtrar os emails que ainda não foram exibidos
                    val newEmails = emailList.filter { email ->
                        !displayedEmailIds.contains(email.id)
                    }

                    // Adicionar os IDs dos novos emails à lista de IDs exibidos
                    displayedEmailIds.addAll(newEmails.map { it.id })

                    // Atualizar a lista de emails com os novos emails filtrados
                    emails.clear()


                    emails.addAll(emailList) // Adicionar os emails da API à lista
                    // Aqui você pode atualizar a UI ou fazer outra ação

                    Log.i("FIAP","Emails carregados com sucesso")
                }
            } else {
                println("Erro na resposta da API")
            }
        }

        override fun onFailure(call: Call<List<Email>>, t: Throwable) {
            println("Erro ao fazer requisição: ${t.message}")
        }
    })
}


//Emails teste sem a necessidade de comunicar com a API:

//val emails = listOf(
//    Email("1", "Reunião importante", "João Silva", "10:30", "Prezados, venho por meio deste e-mail convidá-los para uma reunião...", false),
//    Email("2", "Promoção imperdível!", "Loja XYZ", "Ontem", "Não perca a nossa promoção de aniversário...", true),
//    Email("3", "fatura cartão", "Loja casas bahia", "10/06/2024", "Sua fatura está disponivel", false),
//    Email("4", "Seguro de vida", "Funeraria ", "09/06/2024", "Cuide de sua saude e não seja nosso cliente", false),
//    Email("5", "Comprovante de pagamento", "Loja XYZ", "07/06/2024", "Segue seu comprovante de compra...", true),
//    Email("6", "Alinhamento mensal", "SANTANDER COLABORADORES", "01/06/2024", "Alinhamento sobre metas mensais da equipe...", false)
//)
