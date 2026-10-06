package com.abbasraza.i230733

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Local unit test (runs on the computer, no emulator needed).
 */
class ExampleUnitTest {

    @Test
    fun initials_areBuiltFromFirstLetters() {
        // Same logic the screens use to turn a name into avatar initials
        val name = "Abbas Raza"
        val initials = name.split(" ").take(2).joinToString("") { it.first().uppercase() }
        assertEquals("AR", initials)
    }
}
