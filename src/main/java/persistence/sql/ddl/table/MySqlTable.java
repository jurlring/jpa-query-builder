package persistence.sql.ddl.table;

import persistence.sql.ddl.column.MySqlColumn;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MySqlTable {

    private final TableName name;
    private final List<MySqlColumn> columns;

    private MySqlTable(TableName name, List<MySqlColumn> columns) {
        this.name = name;
        this.columns = columns;

        checkHasIdAnnotation(columns);
    }

    public static MySqlTable from(Class<?> entity) {
        TableName name = TableName.from(entity);

        List<MySqlColumn> columns = Arrays.stream(entity.getDeclaredFields())
                .map(MySqlColumn::from)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new MySqlTable(name, columns);
    }

    private void checkHasIdAnnotation(List<MySqlColumn> columns) {
        boolean hasId = columns.stream()
                .anyMatch(MySqlColumn::hasId);

        if (!hasId) {
            throw new IllegalArgumentException(String.format("%s table should have primary key", name.getName()));
        }
    }

    public String getName() {
        return name.getName();
    }

    public String getColumnsDefinition() {
        return columns.stream()
                .map(MySqlColumn::defineColumn)
                .collect(Collectors.joining(", "));
    }
}
