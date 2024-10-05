package com.df.uaa_papdm_grupo_1_tp_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            AppTheme {
                AplicacionDeProductos()
            }
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

@Composable
fun ListaDeProductos(productos: MutableList<Producto>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(productos) { producto ->
            TarjetaDeProducto(
                producto,
                onRemove = { productos.remove(producto) })
        }
    }
}

@Composable
fun TarjetaDeProducto(producto: Producto, onRemove: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth().height(150.dp)
            ) {
                val anchoPantalla = maxWidth
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item { InfoProducto(titulo="Descripción", valor=producto.descripcion, ancho=anchoPantalla, isImage = false) }

                    item { InfoProducto(titulo="Marca", valor=producto.marca, ancho=anchoPantalla, isImage = false) }

                    item { InfoProducto(titulo="Precio", valor=producto.precio, ancho=anchoPantalla, isImage = false) }

                    item { InfoProducto(titulo="Cantidad", valor=producto.cantidad, ancho=anchoPantalla, isImage = false) }

                    item { InfoProducto(titulo="Imagen", valor=producto.imagenUrl, ancho=anchoPantalla, isImage = true) }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { onRemove() },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = "Eliminar")
                }
            }
        }
    }
}

@Composable
fun InfoProducto(titulo: String, valor: String, ancho: androidx.compose.ui.unit.Dp, isImage: Boolean) {
    Column(
        modifier = Modifier
            .width(ancho)
            .fillMaxSize()
            .padding(end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = titulo,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (isImage) {
            AsyncImage(
                model = valor,
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_error),
                contentDescription = "Imagen Producto",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = valor,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
    @Composable
    fun AplicacionDeProductos() {
        val productos = remember { mutableStateListOf<Producto>() }
        var nombre by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var marca by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("") }
        var cantidad by remember { mutableStateOf("") }
        var imagenUrl by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .padding(16.dp)
        ) {
            FormularioDeProducto(
                nombre = nombre,
                descripcion = descripcion,
                marca = marca,
                precio = precio,
                cantidad = cantidad,
                imagenUrl = imagenUrl,
                onNombreChange = { nombre = it },
                onDescripcionChange = { descripcion = it },
                onMarcaChange = { marca = it },
                onPrecioChange = { precio = it },
                onCantidadChange = { cantidad = it },
                onImagenUrlChange = { imagenUrl = it },
                onAgregarProducto = {
                    val nuevoProducto = Producto(nombre, descripcion, marca, precio, cantidad, imagenUrl)
                    productos.add(nuevoProducto)
                    nombre = ""
                    descripcion = ""
                    marca = ""
                    precio = ""
                    cantidad = ""
                    imagenUrl = ""
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ListaDeProductos(productos = productos)
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun PreviewThemedContent() {
        AppTheme {
            AplicacionDeProductos()
        }
    }