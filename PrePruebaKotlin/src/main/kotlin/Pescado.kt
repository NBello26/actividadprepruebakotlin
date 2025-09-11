// ------------------------------
// Clase abstracta Pescado
// ------------------------------
abstract class Pescado(
    val nombre: String,
    val precioxkilo: Int,
    val stock: Int
) {
    abstract fun descripcion(): String // Cada tipo de pescado implementará su descripción

    // Método para determinar si es caro (precio > 5000)
    fun esCaro(): Boolean = precioxkilo > 5000

    // Método para calcular el valor total del stock
    fun valorTotal(): Int = precioxkilo * stock
}