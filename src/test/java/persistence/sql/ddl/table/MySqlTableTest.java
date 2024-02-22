package persistence.sql.ddl.table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.MySqlDDLGenerator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

class MySqlTableTest {

    @Test
    @DisplayName("테이블에 @Id 가 없을 경우 예외가 발생한다")
    void failByNotFoundIdAnnotation() {
        // given
        MySqlDDLGenerator mySqlDDLGenerator = new MySqlDDLGenerator();

        // when
        Throwable throwable = catchThrowable(() -> mySqlDDLGenerator.generateCreate(PersonNotHaveIdAnnotation.class));

        // then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("person_not_have_id_annotation table should have primary key");
    }
}
