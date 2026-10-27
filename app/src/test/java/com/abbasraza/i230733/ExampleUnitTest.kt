package com.abbasraza.i230733

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun initials_areBuiltFromFirstLetters() {
        val name = "Abbas Raza"
        val initials = name.split(" ").take(2).joinToString("") { it.first().uppercase() }
        assertEquals("AR", initials)
    }
}
