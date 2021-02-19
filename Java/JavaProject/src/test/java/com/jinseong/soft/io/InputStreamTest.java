package com.jinseong.soft.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputStreamTest {

    @DisplayName("주어진 File을 BufferedInputStream을 사용여부에 따라 시간 비교를 했을 때 사용한 경우 더 빠른 것을 확인")
    @Test
    void testFileInputStream() throws IOException {
        String filePath = "data/sample.txt";
        File file = new File(filePath);
        long durationUseBuffered = readBytes(createInputStreamUseBufferedStream(file));
        long durationNotUseBuffered = readBytes(createInputStreamNotUseBufferedStream(file));
        System.out.println(String.format("durationUseBuffered: %d, durationNotUseBuffered: %s", durationUseBuffered, durationNotUseBuffered));
        assertThat(durationUseBuffered).isLessThan(durationNotUseBuffered);
    }

    long readBytes(InputStream inputstream) throws IOException {
        long start = System.currentTimeMillis();
        byte[] data = new byte[1024];
        int bytesRead = inputstream.read(data);

        while (bytesRead != -1) {
            bytesRead = inputstream.read(data);
        }
        inputstream.close();
        return System.currentTimeMillis() - start;
    }

    InputStream createInputStreamNotUseBufferedStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    InputStream createInputStreamUseBufferedStream(File file) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(file), 1024 * 1024);
    }

    @DisplayName("Multiple 리소스 인 경우 try-with-resources의 바이트 코드를 확인")
    @Test
    void testMultipleResourceTryWithResources() {
        String filePath = "data/sample.txt";
        File file = new File(filePath);

        try (InputStream inputStreamUseBufferedStream = createInputStreamUseBufferedStream(file);) {
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
