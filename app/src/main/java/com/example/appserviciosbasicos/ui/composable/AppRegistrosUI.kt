package com.example.appserviciosbasicos.ui.composable

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appserviciosbasicos.ui.ListaRegistrosViewModel


@Composable
fun AppRegistrosUI(
    navController: NavHostController = rememberNavController(),
    vmListaRegistros: ListaRegistrosViewModel = viewModel(factory = ListaRegistrosViewModel.Factory)
) {
    NavHost(
        navController = navController,
        startDestination = "inicio")
    {
        composable("inicio") {
            PantallaListaRegistros(
                vmListaRegistros = vmListaRegistros,
                navController = navController
            )
        }
        composable("form") {
            PantallaFormRegistro(
                navigateBack = { navController.popBackStack()},
                vmListaRegistros = vmListaRegistros
            )
        }
    }
}