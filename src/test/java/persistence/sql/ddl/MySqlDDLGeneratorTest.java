package persistence.sql.ddl;

import domain.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MySqlDDLGeneratorTest {

    @Test
    void createPersonCreateQuery() {
        // given
        MySqlDDLGenerator mySqlDdlGenerator = new MySqlDDLGenerator();

        // when
        String createSql = mySqlDdlGenerator.generateCreate(Person.class);

        // then
        assertThat(createSql).isEqualTo("CREATE TABLE person (" +
                "id BIGINT AUTO_INCREMENT not null, " +
                "nick_name VARCHAR(255) null, " +
                "old INT null, " +
                "email VARCHAR(255) not null" +
                ", PRIMARY KEY(id));");
    }

}