package br.com.fiap.localwebmail.model

data class Email(
    val id: String,
    val subject: String,
    val sender: String,
    val timestamp: String,
    val body: String
)

val emails = listOf(
    Email("1", "Reunião importante", "João Silva", "10:30", "Prezados, venho por meio deste e-mail convidá-los para uma reunião..."),
    Email("2", "Promoção imperdível!", "Loja XYZ", "Ontem", "Não perca a nossa promoção de aniversário...")
)