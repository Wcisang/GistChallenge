package com.wcisang.testutils

import io.mockk.MockKVerificationScope
import io.mockk.coVerify
import io.mockk.verify

fun coVerifyOnce(verifyBlock: suspend MockKVerificationScope.() -> Unit) = coVerify(exactly = 1, verifyBlock = verifyBlock)

fun coVerifyNever(verifyBlock: suspend MockKVerificationScope.() -> Unit) = coVerify(exactly = 0, verifyBlock = verifyBlock)