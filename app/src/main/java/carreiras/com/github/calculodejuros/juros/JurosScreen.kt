package carreiras.com.github.calculodejuros.juros

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carreiras.com.github.calculodejuros.calculos.calcularJuros
import carreiras.com.github.calculodejuros.calculos.calcularMontante
import carreiras.com.github.calculodejuros.components.CaixaDeEntrada
import carreiras.com.github.calculodejuros.components.CardResultado
import carreiras.com.github.calculodejuros.ui.theme.CalculoDeJurosTheme

@Composable
fun JurosScreen(jurosScreenViewModel: JurosScreenViewModel) {
    val capital by jurosScreenViewModel
        .capital
        .observeAsState(initial = "")
    val taxa by jurosScreenViewModel
        .taxaState
        .observeAsState(initial = "")
    val tempo by jurosScreenViewModel
        .tempoState
        .observeAsState(initial = "")
    var juros by remember { mutableStateOf(0.0) }
    var montante by remember { mutableStateOf(0.0) }
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Text(
                text = "Cálculo de Juros Simples",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            // Formulário para entrada de dados
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Dados do Investimento",
                        fontWeight = FontWeight.Bold
                    )
                    // Caixas de entrada da aplicação
                    CaixaDeEntrada(
                        value = capital,
                        placeholder = "Quanto deseja investir",
                        label = "Valor do investimento",
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ) {
                        jurosScreenViewModel.onCapitalChanged(it)
                    }
                    CaixaDeEntrada(
                        value = taxa,
                        placeholder = "Qual a taxa de juros mensal?",
                        label = "Taxa de juros mensal",
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ){
                        jurosScreenViewModel.onTaxaChanged(it)
                    }
                    CaixaDeEntrada(
                        value = tempo,
                        placeholder = "Qual o período do investimento em meses?",
                        label = "Período em meses",
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ){
                        jurosScreenViewModel.onTempoChanged(it)
                    }
                    Button(
                        onClick = {
                            juros = calcularJuros(
                                capital = capital.toDouble(),
                                taxa = taxa.toDouble(),
                                tempo = tempo.toDouble()
                            )
                            montante = calcularMontante(
                                capital = capital.toDouble(),
                                juros = juros
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                    ) {
                        Text(text = "CALCULAR")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Resultado da aplicação
            CardResultado(juros = juros, montante = montante)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JurosScreenPreview() {
    CalculoDeJurosTheme {
        JurosScreen(JurosScreenViewModel())
    }
}