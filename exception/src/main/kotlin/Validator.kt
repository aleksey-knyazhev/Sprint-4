
abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()
        val normalPhoneLength = 11

        if (value == null ||
                value.isEmpty()) {
            result.add(ErrorCode.IS_EMPTY)
            return result
        }

        if(value.any { !it.isDigit() })
            result.add(ErrorCode.PHONE_INVALID_CHARACTER_NUMBERS_ONLY)

        if(value.length != normalPhoneLength)
            result.add(ErrorCode.PHONE_INVALID_LENGTH)

        if (value[0] != '7' && value[0] != '8')
            result.add(ErrorCode.PHONE_INVALID_COUNTRY_CODE)

        return result
    }
}

class FirstNameAndLastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()
        val maxFirstNameAndLastNameLength = 32
        val regex = "[аА-яЯ]+$".toRegex()

        if (value == null ||
            value.isEmpty()) {
            result.add(ErrorCode.IS_EMPTY)
            return result
        }

        if(!value.matches(regex))
            result.add(ErrorCode.NAME_AND_SURNAME_INVALID_CHARACTER_CYRILLIC_ONLY)

        if(value.length > maxFirstNameAndLastNameLength)
            result.add(ErrorCode.NAME_AND_SURNAME_INVALID_LENGTH)

        return result
    }
}

class  EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()
        val maxFirstNameAndLastNameLength = 32
        val regexEmailFormat = "^[aA-zZ0-9]+@[aA-zZ]+\\.[aA-zZ]+$".toRegex()

        if (value == null ||
            value.isEmpty()) {
            result.add(ErrorCode.IS_EMPTY)
            return result
        }

        if(value.length > maxFirstNameAndLastNameLength)
            result.add(ErrorCode.EMAIL_INVALID_LENGTH)

        if(!value.matches(regexEmailFormat))
            result.add(ErrorCode.EMAIL_INVALID_FORMAT)

        return result
    }
}

class InsuranceNumberValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()
        val normalInsuranceNumberLength = 11

        if (value == null ||
            value.isEmpty()) {
            result.add(ErrorCode.IS_EMPTY)
            return result
        }

        if(value.any { !it.isDigit() })
            result.add(ErrorCode.INSURANCE_NUMBER_INVALID_CHARACTER)

        if(value.length != normalInsuranceNumberLength)
            result.add(ErrorCode.INSURANCE_NUMBER_INVALID_LENGTH)

        if (!validateControlNumber(value)) {
            result.add(ErrorCode.INSURANCE_NUMBER_INVALID_CONTROL_NUMBER)
        }

        return result
    }

    private fun validateControlNumber(insuranceNumber: String): Boolean {
        val inputControlNumber = "${insuranceNumber[9]}${insuranceNumber[10]}".toIntOrNull()
        var calcylatedControlNumber = 0
        var thisDigit: Int?
        if (insuranceNumber.slice(0..8) < "001001998") {
            return false
        }
        for (i in 0..8) {
            thisDigit = insuranceNumber[i].digitToIntOrNull()
            if (thisDigit != null) {
                calcylatedControlNumber += thisDigit * (9 - i)
            } else {
                return false
            }
        }
        if (inputControlNumber != null) {
            if (calcylatedControlNumber > 101) {
                return inputControlNumber == calcylatedControlNumber % 101
            } else if (calcylatedControlNumber > 99) {
                return inputControlNumber == 0
            } else {
                return inputControlNumber == calcylatedControlNumber
            }
        }
        return false
    }
}