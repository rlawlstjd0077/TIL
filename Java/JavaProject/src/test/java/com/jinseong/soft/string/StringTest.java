package com.jinseong.soft.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringTest {

  @Test
  void testIsBlank() {
    String emptyString = "";
    String allWhiteSpaceString = "  ";

    allWhiteSpaceString.isBlank();

    assertThat(emptyString).isBlank();
    assertThat(allWhiteSpaceString).isBlank();
  }
}
