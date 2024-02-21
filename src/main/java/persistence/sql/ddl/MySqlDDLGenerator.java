package persistence.sql.ddl;

public class MySqlDDLGenerator implements DDLGenerator {

    @Override
    public String generateCreate(Class<?> entity) {
        MySqlTable table = MySqlTable.from(entity);

        return table.createTable();
    }
}
