import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - phone is empty`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_is_empty.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.IS_EMPTY)
    }

    @Test
    fun `fail save client - phone invalid length`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_invalid_length.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.PHONE_INVALID_LENGTH)
    }

    @Test
    fun `fail save client - phone invalid character (numbers only)`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_invalid_character_numbers_only.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.PHONE_INVALID_CHARACTER_NUMBERS_ONLY)
    }

    @Test
    fun `fail save client - country code`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_invalid_country_code.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.PHONE_INVALID_COUNTRY_CODE)
    }

    @Test
    fun `fail save client - name and surname is empty`() {
        val client = getClientFromJson("/fail/user_with_bad_name_and_surname_is_empty.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.IS_EMPTY)
    }

    @Test
    fun `fail save client - name and surname invalid character cyrillic only`() {
        val client = getClientFromJson("/fail/user_with_bad_name_and_surname_invalid_character_cyrillic_only.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.NAME_AND_SURNAME_INVALID_CHARACTER_CYRILLIC_ONLY)
    }

    @Test
    fun `fail save client - name and surname invalid length`() {
        val client = getClientFromJson("/fail/user_with_bad_name_and_surname_invalid_length.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.NAME_AND_SURNAME_INVALID_LENGTH)
    }

    @Test
    fun `fail save client - email corrupted`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.EMAIL_INVALID_FORMAT)
    }

    @Test
    fun `fail save client - bad insurance number`() {
        val client = getClientFromJson("/fail/user_with_bad_insurance_number.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INSURANCE_NUMBER_INVALID_CONTROL_NUMBER)
    }

    @Test
    fun `fail save client - data corrupted`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.toSet(),
            setOf(ErrorCode.PHONE_INVALID_LENGTH,
                ErrorCode.PHONE_INVALID_COUNTRY_CODE,
                ErrorCode.IS_EMPTY,
                ErrorCode.NAME_AND_SURNAME_INVALID_LENGTH,
                ErrorCode.EMAIL_INVALID_FORMAT,
                ErrorCode.INSURANCE_NUMBER_INVALID_CONTROL_NUMBER))
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}