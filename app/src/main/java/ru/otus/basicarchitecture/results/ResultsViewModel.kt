package ru.otus.basicarchitecture.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.otus.basicarchitecture.data.WizardCache
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class ResultsViewModel @Inject constructor(
    private val wizardCache: WizardCache
) {
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    private val _results = MutableLiveData(Results(
        wizardCache.name,
        wizardCache.surname,
        wizardCache.birthdate?.let { dateFormat.format(it) } ?: "",
        wizardCache.address,
        wizardCache.interests
    ))
    val results: LiveData<Results> = _results
}