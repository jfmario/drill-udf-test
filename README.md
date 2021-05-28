
# Drill UDF Testing

This repository is a few examples of user-defined functions (UDFs) for 
[Apache Drill](http://drill.apache.org/). Mostly for my own reference.

## Getting Started

This reference repository assumes you know the basics of Apache Drill.

## Usage

### UDFs

Each of the following directories is a workspace containing UDF defintions:

* `/trivial_udf/`: This defines "jfmario_trival_udf", a function that takes an INT or BIGINT column and returns an INT column of the number 1.

### Building a UDF

From inside a UDF workspace, run this command when ready:

```
mvn clean package -DskipTests
```

That will create .jar files in the `target/` directory of the workspace.

Then run:

```
cp target/*.jar /path/to/drill/jars/3rdparty/
```

Then restart Drill and you can run a query like this:

```sql
SELECT full_name, jfmario_trivial_udf(department_id) FROM cp.`employee.json` LIMIT 10;
```

## Author

jfmario