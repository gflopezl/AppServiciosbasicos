package com.example.appserviciosbasicos.ui.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appserviciosbasicos.R
import com.example.appserviciosbasicos.data.Registro
import com.example.appserviciosbasicos.ui.ListaRegistrosViewModel
import java.time.LocalDate

@Composable
fun OpcionesCategoriaUI(
    onCategoriaSelected: (String) -> Unit
) {
    val categorias = listOf("AGUA", "LUZ", "GAS")
    var categoriaSeleccionada by rememberSaveable { mutableStateOf(categorias[0]) }

    Column(Modifier.selectableGroup()) {
        categorias.forEach { categoria ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .selectable(
                        selected = (categoria == categoriaSeleccionada),
                        onClick = {
                            categoriaSeleccionada = categoria
                            onCategoriaSelected(categoria)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (categoria == categoriaSeleccionada),
                    onClick = null
                )
                Text(
                    text = categoria,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PantallaFormRegistro(
    navigateBack: () -> Unit,
    vmListaRegistros: ListaRegistrosViewModel = viewModel(factory = ListaRegistrosViewModel.Factory)
){

    var monto by rememberSaveable { mutableIntStateOf(0) }
    var fecha by rememberSaveable { mutableStateOf("")}
    var categoriaSeleccionada by rememberSaveable { mutableStateOf("") }

    val contexto = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 80.dp, vertical = 50.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 60.dp),
                text = (contexto.getString(R.string.app_name)),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = monto.toString(),
                onValueChange = { monto = it.toIntOrNull() ?: 0 },
                label = { Text("Medidor") },
                placeholder = { Text("12300") }
            )
            TextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha") },
                placeholder = { Text("2023-01-01") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text("Medidor de:")
            OpcionesCategoriaUI { categoria ->
                categoriaSeleccionada = categoria
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    vmListaRegistros.insertarRegistro(
                        Registro(
                            null,
                            monto.toLong(),
                            LocalDate.parse(fecha),
                            categoriaSeleccionada
                        )
                    )
                    vmListaRegistros.obtenerRegistros()
                    navigateBack()
                },
                modifier = Modifier.padding(horizontal = 50.dp)
            ) {
                Text(contexto.getString(R.string.btn_text_registrar))
            }
        }
    }
}