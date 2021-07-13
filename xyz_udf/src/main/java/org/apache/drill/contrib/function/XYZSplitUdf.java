/**
 * 
 */
package org.apache.drill.contrib.function;

import io.netty.buffer.DrillBuf;
import org.apache.drill.common.exceptions.DrillRuntimeException;
import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.annotations.Workspace;
import org.apache.drill.exec.expr.fn.impl.StringFunctionHelpers;
import org.apache.drill.exec.expr.holders.VarCharHolder;
import org.apache.drill.exec.vector.complex.writer.BaseWriter.ComplexWriter;
import org.apache.drill.exec.vector.complex.writer.BaseWriter.MapWriter;

/**
 * A trivial Drill UDF that takes an int input and always returns 1.
 * 
 * SELECT jfmario_trivial_udf(age) from dfs.`/data/employees.json`.
 */
@FunctionTemplate(
    name = "xyz_split",
    scope = FunctionTemplate.FunctionScope.SIMPLE,
    nulls = FunctionTemplate.NullHandling.NULL_IF_NULL
)
public class XYZSplitUdf implements DrillSimpleFunc {

  @Param
  VarCharHolder input;
  
  @Output
  ComplexWriter outWriter;

  public void setup() {
  }

  public void eval() {
    
    String s = StringFunctionHelpers.toStringFromUTF8(input.start,
      input.end, input.buffer);
    String[] sList = s.split(",", 3);
    
    if (sList.length != 3) {
      throw new DrillRuntimeException("There must be three elements separated by commas.");
    }
    
    String aStr = sList[0];
    String bStr = sList[1];
    String cStr = sList[2];
    
    float x = Float.parseFloat(aStr);
    float y = Float.parseFloat(bStr);
    float z = Float.parseFloat(cStr);
    
    MapWriter mr = outWriter.rootAsMap();
    
    mr.float8("x").writeFloat8(x);
    mr.float8("y").writeFloat8(y);
    mr.float8("z").writeFloat8(z);
  }
}
