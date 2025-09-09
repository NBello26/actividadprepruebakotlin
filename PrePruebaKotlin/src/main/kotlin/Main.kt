sealed class Resultado

data class Exito(val mensaje: String): Resultado()//positivo

data class Error(val mensaje: String): Resultado()//negativo

//Función que valide el resultado y muestre un mensaje de error o positivo
fun mostrarMensaje(resultado: Resultado){
    //utilizar when para evaluar el tipo de resultado
    when(resultado){
        //-> lambda = anónimas= no tienen nombre y automaticas
        is Exito -> println("${resultado.mensaje}") //exíto
        is Error -> println("${resultado.mensaje}") //error
    }
}
fun main() {
    val productos: List<PescadoBlanco>
    val pescadoBlanco1 = PescadoBlanco("Pescadito", 6000, 100)
    val pescadoBlanco2 = PescadoBlanco("Pescadito full", 8000, 70)
    val pescadoBlanco3 = PescadoBlanco("Pescadito small", 4000, 25)

    val salmon1 = PescadoBlanco("Salmoncito", 8000, 85)
    val salmon2 = PescadoBlanco("Salmoncito full", 10000, 122)
    val salmon3 = PescadoBlanco("Salmoncito small", 5800, 15)

    productos.
    var montovalor = 0
    var montostock = 0
    var flag = "true"
    println("Ingrese un nombre para el pescado que desea ingresar:")
    val entradanombre = readLine()

    while (flag == "true") {
        try {
            //leer info del usuario en un input
            println("Ingrese un valor para el pescado ${entradanombre}:")
            val entrada = readLine()

            //detectar si un valor
            //Convertir de string = int o lanzamos una excepción
            montovalor = entrada?.toInt() ?: throw Exception("Ingresa un dato, la entrada está vacía")

            // números negativos : recibe un argumento no válido
            if (montovalor <= 0) {
                throw IllegalArgumentException("El precio del pescado debe ser mayor a 0")
            }
            flag = "false"
            println("El valor para precio ingresado cumple los estandares")
        } catch (e: NumberFormatException) {
            println("Ingresa un número válido")
            //en caso de que sea negativo
        } catch (e: IllegalArgumentException) {
            println("Error de lógica ${e.message}")
        }//Cubro cualquier error no anticipado
        catch (e: Exception) {
            println("Error inesperado: ${e.message}")
        }//se ejecuta si o si hay o no haya un error
        finally {
            println("Gracias por ingresar datos al sistema")
        }
    }

    while (flag == "false") {
        try {
            //leer info del usuario en un input
            println("Ingrese un stock para el pescado ${entradanombre}:")
            val entradastock = readLine()

            //detectar si un valor
            //Convertir de string = int o lanzamos una excepción
            montostock = entradastock?.toInt() ?: throw Exception("Ingresa un dato, la entrada está vacía")

            // números negativos : recibe un argumento no válido
            if (montostock < 0) {
                throw IllegalArgumentException("El stock del pescado debe ser mayor o igual a 0")
            }
            println("El valor para precio ingresado cumple los estandares")
            flag = "true"
        } catch (e: NumberFormatException) {
            println("Ingresa un número válido")
            //en caso de que sea negativo
        } catch (e: IllegalArgumentException) {
            println("Error de lógica ${e.message}")
        }//Cubro cualquier error no anticipado
        catch (e: Exception) {
            println("Error inesperado: ${e.message}")
        }//se ejecuta si o si hay o no haya un error
        finally {
            println("Gracias por ingresar datos al sistema")
        }
    }

    while (flag == "true") {
        try {
            //leer info del usuario en un input
            println("Su pescado a ingresar es:\n1.- Salmon\n2.-Pescado Blanco")
            println("Ingrese el numero correspondiente a lo que desea:")
            val entradatipo = readLine()

            //detectar si un valor
            //Convertir de string = int o lanzamos una excepción
            val tipo = entradatipo?.toInt() ?: throw Exception("Ingresa un dato, la entrada está vacía")

            // números negativos : recibe un argumento no válido
            if (tipo < 1 || tipo > 2) {
                throw IllegalArgumentException("La opcion ingresada debe ser un número valido correspondiente a las opciones entregadas")
            }
            println("la opcion ingresada cumple los estandares")
            if (tipo == 1){
                val salmon = Salmon(entradanombre.toString(),montovalor,montostock)
                println(salmon.descripcion())
                println("")
                flag = "false"
            }
            else if (tipo == 2){
                val Pescado = PescadoBlanco(entradanombre.toString(),montovalor,montostock)
                print(Pescado.descripcion())
                println("")
                flag = "false"
            }
        } catch (e: NumberFormatException) {
            println("Ingresa un número válido")
            //en caso de que sea negativo
        } catch (e: IllegalArgumentException) {
            println("Error de lógica ${e.message}")
        }//Cubro cualquier error no anticipado
        catch (e: Exception) {
            println("Error inesperado: ${e.message}")
        }//se ejecuta si o si hay o no haya un error
        finally {
            println("Gracias por ingresar datos al sistema")
        }
    }

    //Intentar guardaro los pescados en una lista para poder usar el filter y la opcion esCaro()
    Pescado.filterIsInstance<Salmon>().filter {it.Escaro}.forEach {
        println("- ${it.descripcion()}")
    }



    val r1 : Resultado = Exito("Acceso correcto puedes pasar al dashboard")
    val r2 : Resultado = Error("Acceso incorrecto, vuelve a intentarlo")
    println(r1)
    println(r2)
}