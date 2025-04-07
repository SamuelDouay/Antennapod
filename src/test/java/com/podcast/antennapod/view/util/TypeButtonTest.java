package com.podcast.antennapod.view.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Button type test")
class TypeButtonTest {

    @Test
    void testEnumValues() {
        TypeButton[] values = TypeButton.values();
        assertEquals(3, values.length);
        assertArrayEquals(new TypeButton[] {
                TypeButton.PRIMARY,
                TypeButton.SECONDARY,
                TypeButton.TERTIARY
        }, values);
    }

    @Test
    void testValueOf() {
        assertEquals(TypeButton.PRIMARY, TypeButton.valueOf("PRIMARY"));
        assertEquals(TypeButton.SECONDARY, TypeButton.valueOf("SECONDARY"));
        assertEquals(TypeButton.TERTIARY, TypeButton.valueOf("TERTIARY"));
    }

    @Test
    void testValueOfThrowsExceptionForInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> TypeButton.valueOf("QUATERNARY"));
    }

    @Test
    void testEnumConstructor() {
        // Cette énumération n'a pas de constructeur personnalisé ni de propriétés,
        // mais nous pouvons vérifier qu'elle est instanciable correctement
        assertNotNull(TypeButton.PRIMARY);
        assertNotNull(TypeButton.SECONDARY);
        assertNotNull(TypeButton.TERTIARY);
    }

    @Test
    void testToString() {
        assertEquals("PRIMARY", TypeButton.PRIMARY.toString());
        assertEquals("SECONDARY", TypeButton.SECONDARY.toString());
        assertEquals("TERTIARY", TypeButton.TERTIARY.toString());
    }
}