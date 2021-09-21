class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    NAME_AND_SURNAME_INVALID_CHARACTER_CYRILLIC_ONLY(1, " Недопустимый символ. Допустима только кириллица"),
    NAME_AND_SURNAME_INVALID_LENGTH(2, " Недопустимая длина. Допустимо не более 16 символов (на каждое поле)"),
    PHONE_INVALID_COUNTRY_CODE(3, " Недопустимый код страны. Номер телефона должен начинаться с 7 или 8"),
    PHONE_INVALID_CHARACTER_NUMBERS_ONLY(4, " Недопустимый символ. Допустимы только цифры"),
    PHONE_INVALID_LENGTH(5, " Недопустимая длина. Допустимо равно 11 знаков"),
    EMAIL_INVALID_FORMAT(7, " Недопустимый формат. Не определено @имя_домена"),
    EMAIL_INVALID_LENGTH(8, " Недопустимая длина. Допустимо не более 32 символов"),
    INSURANCE_NUMBER_INVALID_CHARACTER(9, " Недопустимый символ. Допустимы только цифры"),
    INSURANCE_NUMBER_INVALID_LENGTH(10, " Недопустимая длина. Допустимо равно 11 знаков"),
    INSURANCE_NUMBER_INVALID_CONTROL_NUMBER(11, " Недопустимый СНИЛС. Некорректное контрольное число"),
    IS_EMPTY(12, " Значение не заполнено")
}