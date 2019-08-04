package com.github.hcsp.exception;

import com.github.hcsp.test.helper.JavaASTReader;
import com.github.hcsp.test.helper.ProjectSourceFileReader;
import com.github.javaparser.ast.stmt.TryStmt;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseReaderTest {
    @Test
    public void hasFinallyOrTryWithResources() {
        List<TryStmt> tryStmts = JavaASTReader.findAll(DatabaseReader.class, TryStmt.class);
        Assertions.assertTrue(tryStmts.stream().anyMatch(this::hasFinallyOrTryWithResources));
    }

    @Test
    public void hasClose() {
        Assertions.assertTrue(
                ProjectSourceFileReader.readAsString(DatabaseReader.class).contains(".close()"));
    }

    private boolean hasFinallyOrTryWithResources(TryStmt stmt) {
        return stmt.getFinallyBlock().isPresent() || stmt.getResources().isNonEmpty();
    }
}
