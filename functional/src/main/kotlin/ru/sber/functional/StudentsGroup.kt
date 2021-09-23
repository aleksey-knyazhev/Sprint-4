package ru.sber.functional

class StudentsGroup {
    private var students: List<Student> = listOf(
        Student(firstName = "Иван", lastName = "Иванов", averageRate = 5.0),
        Student(firstName = "Петр", lastName = "Петров", averageRate = 4.5, middleName = "Петрович"),
        Student(firstName = "Сидор", lastName = "Сидоров", averageRate = 4.0, age = 42),
    )

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> = students.filter { predicate(it) }
}

fun main() {
    val studentsGroup = StudentsGroup().filterByPredicate { it.averageRate >= 4.5 }
    for (student in studentsGroup)
        println("${student.firstName} ${student.lastName} ${student.averageRate}")
}
