import kotlinx.coroutines.*

// Sealed class para resultados de control de calidad
sealed class ControlResultado
data class Exitoso(val mensaje: String) : ControlResultado()
data class Fallido(val mensaje: String) : ControlResultado()

// ------------------------------
// Función suspend para controlar calidad de un pescado
// ------------------------------
suspend fun controlarCalidad(pescado: Pescado): ControlResultado {
    println("Iniciando control de calidad para: ${pescado.nombre}")
    delay(2000L) // Simula un proceso lento de 2 segundos

    // Condición simple de control: stock >= 10 y precio >= 1000
    return if (pescado.stock >= 10 && pescado.precioxkilo >= 1000) {
        Exitoso("El pescado ${pescado.nombre} pasó el control de calidad")
    } else {
        Fallido("El pescado ${pescado.nombre} NO pasó el control de calidad")
    }
}

// Función para mostrar resultados de control de calidad
fun mostrarResultadoControl(resultado: ControlResultado) {
    when (resultado) {
        is Exitoso -> println("✅ ${resultado.mensaje}")
        is Fallido -> println("❌ ${resultado.mensaje}")
    }
}

// ------------------------------
// Función principal
// ------------------------------
fun main() = runBlocking {
    // Lista inicial de productos
    val productos = mutableListOf<Pescado>(
        PescadoBlanco("Pescadito", 6000, 100),
        PescadoBlanco("Pescadito full", 8000, 70),
        PescadoBlanco("Pescadito small", 4000, 25),
        Salmon("Salmoncito", 8000, 85),
        Salmon("Salmoncito full", 10000, 122),
        Salmon("Salmoncito small", 5800, 15)
    )

    // ------------------------------
    // Solicitar ingreso de un nuevo pescado
    // ------------------------------
    println("Ingrese un nombre para el pescado que desea agregar:")
    val nombreNuevo = readLine() ?: ""

    // Solicitar precio
    var precioNuevo = 0
    while (true) {
        try {
            println("Ingrese un valor para el pescado $nombreNuevo:")
            val entrada = readLine() ?: throw Exception("Entrada vacía")
            precioNuevo = entrada.toInt()
            if (precioNuevo <= 0) throw IllegalArgumentException("El precio debe ser mayor a 0")
            break // Salir del loop si todo está correcto
        } catch (e: Exception) {
            println("Error: ${e.message}, intenta nuevamente")
        }
    }

    // Solicitar stock
    var stockNuevo = 0
    while (true) {
        try {
            println("Ingrese el stock para el pescado $nombreNuevo:")
            val entrada = readLine() ?: throw Exception("Entrada vacía")
            stockNuevo = entrada.toInt()
            if (stockNuevo < 0) throw IllegalArgumentException("El stock debe ser mayor o igual a 0")
            break // Salir del loop si todo está correcto
        } catch (e: Exception) {
            println("Error: ${e.message}, intenta nuevamente")
        }
    }

    // Seleccionar tipo de pescado
    var pescadoNuevo: Pescado
    while (true) {
        try {
            println("Seleccione el tipo de pescado a ingresar:\n1.- Salmon\n2.- Pescado Blanco")
            val entrada = readLine() ?: throw Exception("Entrada vacía")
            val tipo = entrada.toInt()
            pescadoNuevo = when (tipo) {
                1 -> Salmon(nombreNuevo, precioNuevo, stockNuevo)
                2 -> PescadoBlanco(nombreNuevo, precioNuevo, stockNuevo)
                else -> throw IllegalArgumentException("Opción inválida")
            }
            break // Salir del loop si todo está correcto
        } catch (e: Exception) {
            println("Error: ${e.message}, intenta nuevamente")
        }
    }

    // Agregar el nuevo pescado a la lista
    productos.add(pescadoNuevo)
    println("Pescado agregado correctamente:")
    println(pescadoNuevo.descripcion())
    println("")

    // ------------------------------
    // Mostrar pescados caros (precio > 5000)
    // ------------------------------
    println("---\nPescados con precio mayor a 5000:")
    productos.filter { it.esCaro() }.forEach { println(it.descripcion()) }

    // ------------------------------
    // Mostrar valor total de cada pescado
    // ------------------------------
    println("---\nValor total de cada pescado:")
    productos.forEach { println(it.descripcion()) }

    // ------------------------------
    // Calcular y mostrar el valor total de todos los productos
    // ------------------------------
    val totalValor = productos.sumOf { it.valorTotal() }
    println("---\nValor total de todos los pescados: $totalValor")

    // ------------------------------
    // Control de calidad para cada pescado
    // ------------------------------
    println("---\nIniciando control de calidad de todos los pescados:")
    productos.forEach { pescado ->
        val resultado = controlarCalidad(pescado)
        mostrarResultadoControl(resultado)
    }
}