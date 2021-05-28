/**
 * 
 */
package org.apache.drill.contrib.function;

import io.netty.buffer.DrillBuf;
import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.annotations.Workspace;
import org.apache.drill.exec.expr.holders.BigIntHolder;
import org.apache.drill.exec.expr.holders.IntHolder;
import org.apache.drill.exec.expr.holders.VarCharHolder;

/**
 * A trivial Drill UDF that takes an int input and always returns 1.
 * 
 * SELECT jfmario_trivial_udf(age) from dfs.`/data/employees.json`.
 */
@FunctionTemplate(
    name = "jfmario_trivial_udf",
    scope = FunctionTemplate.FunctionScope.SIMPLE,
    nulls = FunctionTemplate.NullHandling.NULL_IF_NULL
)
public class JfmarioTrivialUdfInt implements DrillSimpleFunc {

  @Param
  IntHolder in;

  @Output IntHolder out; 

  public void setup() {
  }

  public void eval() {
    out.value = 1;
  }
}
