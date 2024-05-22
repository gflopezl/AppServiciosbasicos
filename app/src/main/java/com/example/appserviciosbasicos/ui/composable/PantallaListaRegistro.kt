package com.example.appserviciosbasicos.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appserviciosbasicos.R
import com.example.appserviciosbasicos.data.CategoriaIcono
import com.example.appserviciosbasicos.ui.ListaRegistrosViewModel


@Composable

fun PantallaListaRegistros(
    vmListaRegistros: ListaRegistrosViewModel,
    navController: NavHostController
) {
    val registros by vmListaRegistros.registros.collectAsState()
    val contexto = LocalContext.current

    val categoriasConIconos = listOf(
        CategoriaIcono("AGUA", R.drawable.agua),
        CategoriaIcono("LUZ", R.drawable.luz),
        CategoriaIcono("GAS", R.drawable.gas)
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("form") }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = contexto.getString(R.string.btn_agregar)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(vertical = it.calculateTopPadding())
        ) {
            items(registros) { registro ->
                val categoriaIcono = categoriasConIconos.find { it.nombre == registro.categoria }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    categoriaIcono?.let {
                        Image(
                            painter = painterResource(id = it.icono),
                            contentDescription = "Icono de ${it.nombre}",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = registro.categoria,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(80.dp)
                    )
                    Text(
                        text = String.format("%,d", registro.monto),
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(100.dp)
                    )
                    Text(
                        text = registro.fecha.toString(),
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(150.dp)
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color.LightGray
                )
            }
        }
    }
}