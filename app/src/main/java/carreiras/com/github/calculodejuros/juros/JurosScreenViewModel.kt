package carreiras.com.github.calculodejuros.juros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JurosScreenViewModel : ViewModel() {
    private val _capital = MutableLiveData<String>()
    val capital: LiveData<String> = _capital

    private val _taxa = MutableLiveData<String>()
    val taxaState: LiveData<String> = _taxa

    private val _tempo = MutableLiveData<String>()
    val tempoState: LiveData<String> = _tempo

    fun onCapitalChanged(novoCapital: String) {
        _capital.value = novoCapital
    }

    fun onTaxaChanged(novaTaxa: String) {
        _taxa.value = novaTaxa
    }

    fun onTempoChanged(novoTempo: String) {
        _tempo.value = novoTempo
    }
}