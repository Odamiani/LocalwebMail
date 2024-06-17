package br.com.fiap.localwebmail.model

data class Email(
    val id: String,
    val subject: String,
    val sender: String,
    val timestamp: String,
    val body: String,
    var isFavorite: Boolean = false
)

val emails = listOf(
    Email("1", "Reunião importante", "João Silva", "10:30", "Prezados, venho por meio deste e-mail convidá-los para uma reunião...", false),
    Email("2", "Promoção imperdível!", "Loja XYZ", "Ontem", "Não perca a nossa promoção de aniversário...", true),
    Email("3", "fatura cartão", "Loja casas bahia", "10/06/2024", "Sua fatura está disponivel", false),
    Email("4", "Seguro de vida", "Funeraria ", "09/06/2024", "Cuide de sua saude e não seja nosso cliente", false),
    Email("5", "Comprovante de pagamento", "Loja XYZ", "07/06/2024", "Segue seu comprovante de compra...", true),
    Email("6", "Alinhamento mensal", "SANTANDER COLABORADORES", "01/06/2024", "Alinhamento sobre metas mensais da equipe...", false)
)