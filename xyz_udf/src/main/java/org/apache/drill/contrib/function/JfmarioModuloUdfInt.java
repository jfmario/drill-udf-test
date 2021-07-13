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
import org.apache.drill.exec.expr.holders.IntHolder;

/**
 * A trivial Drill UDF that takes an int input and always returns 1.
 * 
 * SELECT jfmario_trivial_udf(age) from dfs.`/data/employees.json`.
 */
@FunctionTemplate(
    name = "modulo",
    scope = FunctionTemplate.FunctionScope.SIMPLE,
    nulls = FunctionTemplate.NullHandling.NULL_IF_NULL
)
public class JfmarioModuloUdfInt implements DrillSimpleFunc {

  @Param
  IntHolder in1;

  @Param
  IntHolder in2;

  @Output IntHolder out; 

  public void setup() {
  }

  public void eval() {
    out.value = in1.value % in2.value;
  }
}
