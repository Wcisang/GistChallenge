package com.wcisang.domain.utils

import io.mockk.unmockkAll
import org.junit.After

interface UseCaseTest {

    @After
    fun tearsDown() {
        unmockkAll()
    }
}
