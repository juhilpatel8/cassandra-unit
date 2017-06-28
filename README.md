# Cassandra-JUnit Project

The Cassandra Junit Project is a java project which used an embedded cassandra available from cassandra-unit in order to carry out Junit Testing.


## Types of Test

```
// Test using CassandraCQLUnit by defining Junit Rule

   CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("cql file", "keyspace to be created"));

// Test using Spring annotation {@EmbeddedCassandra & @CassandraDataSet} OR @CassandraUnit

   @EmbeddedCassandra default configuration is:

   configuration: a Cassandra configuration file which is cu-cassandra.yaml by default and provided by cassandra-unit
   clusterName: name of Cassandra cluster, "Test Cluster" by default
   host: host of Cassandra cluster; "127.0.0.1" by default
   port: port of Cassandra cluster; 9142 by default (CQL)

   @CassandraDataSet default configuration is:

   value: a set of classpath file path; all files must be the same type
   keyspace: keyspace in which you want to load dataset; by default is "cassandra_unit_keyspace" and only needed CQL case
   type: type of dataset, cql by default. xml, json, and yaml are also available

```
