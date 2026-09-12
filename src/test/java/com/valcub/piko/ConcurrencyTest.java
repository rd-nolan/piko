package com.valcub.piko;

import com.valcub.piko.captcha.CaptchaResult;
import com.valcub.piko.captcha.PikoCaptcha;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ConcurrencyTest {
    @Test
    void oneFacadeCanBeUsedByManyThreads() throws Exception {
        assertConcurrent(PikoCaptcha.builder().seed(2026).build());
    }

    @Test
    void unseededFacadeCanBeUsedByManyThreads() throws Exception {
        assertConcurrent(PikoCaptcha.builder().build());
    }

    private static void assertConcurrent(PikoCaptcha captcha) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        try {
            List<Future<CaptchaResult>> futures = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                futures.add(executor.submit(captcha::generate));
            }
            for (Future<CaptchaResult> future : futures) {
                CaptchaResult result = future.get();
                assertEquals(4, result.code().length());
                assertFalse(result.svg().isBlank());
                assertFalse(result.svg().contains("<text"));
            }
        } finally {
            executor.shutdownNow();
        }
    }
}
