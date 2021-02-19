package com.jinseong.soft.hash;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;

class Entity {
    private String first;
    private String second;

    public Entity(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

class HashTest {
    @DisplayName("hashcode()만 정의했을 동등한 두 entity가 HashSet 다른 entity로 인식되는지 확인")
    @Test
    void testDefineOnlyHashcode() {
        //given
        Entity entity = new Entity("first", "second");
        Entity entity2 = new Entity("first", "second");
        //when
        Set<Entity> entitySet = new HashSet<>();
        entitySet.add(entity);
        //then
        assertThat(entity.hashCode()).isEqualTo(entity2.hashCode());
        assertThat(entitySet).doesNotContain(entity2);
    }
}
