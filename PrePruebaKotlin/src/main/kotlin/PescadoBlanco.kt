class PescadoBlanco(
    nombre: String,
    precioxkilos: Int,
    stock: Int
)
    : Pescado(nombre, precioxkilos, stock) {
        override fun descripcion(): String {
            return "Pescado blanco de nombre: $nombre \n Precio por kilo: $$precioxkilo\nStock disponible: $stock kg"
        }


    }