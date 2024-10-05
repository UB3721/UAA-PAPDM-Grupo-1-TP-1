package com.df.uaa_papdm_grupo_1_tp_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.df.prueba.ui.theme.AppTheme

data class Producto (
    val nombre: String,
    val descripcion: String,
    val marca: String,
    val precio: String,
    val cantidad: String,
    val imagenUrl: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {  }
        }
    }
}

@Composable
fun FormularioDeProducto(
    nombre: String,
    descripcion: String,
    marca: String,
    precio: String,
    cantidad: String,
    imagenUrl: String,
    onNombreChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onMarcaChange: (String) -> Unit,
    onPrecioChange: (String) -> Unit,
    onCantidadChange: (String) -> Unit,
    onImagenUrlChange: (String) -> Unit,
    onAgregarProducto: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "Ingrese un producto",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = marca,
            onValueChange = onMarcaChange,
            label = { Text("Marca") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = precio,
            onValueChange = onPrecioChange,
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cantidad,
            onValueChange = onCantidadChange,
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = imagenUrl,
            onValueChange = onImagenUrlChange,
            label = { Text("ImagenUrl") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onAgregarProducto,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Agregar Producto")
        }
    }
}