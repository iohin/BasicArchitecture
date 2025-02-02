package ru.otus.basicarchitecture.interests

import javax.inject.Inject

class InterestsSource @Inject constructor() {
    val interests = listOf(
        "спорт",
        "музыка",
        "еда",
        "технологии",
        "семья",
        "автомобили",
        "мода",
        "здоровье",
        "программирование",
        "бизнес"
    )
}