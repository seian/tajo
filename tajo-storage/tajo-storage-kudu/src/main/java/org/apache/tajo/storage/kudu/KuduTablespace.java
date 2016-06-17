package org.apache.tajo.storage.kudu;

import net.minidev.json.JSONObject;
import org.apache.hadoop.fs.Path;
import org.apache.tajo.ExecutionBlockId;
import org.apache.tajo.OverridableConf;
import org.apache.tajo.catalog.*;
import org.apache.tajo.catalog.Schema;
import org.apache.tajo.exception.TajoException;
import org.apache.tajo.exception.UnsupportedException;
import org.apache.tajo.plan.LogicalPlan;
import org.apache.tajo.plan.expr.EvalNode;
import org.apache.tajo.plan.logical.LogicalNode;
import org.apache.tajo.schema.*;
import org.apache.tajo.storage.FormatProperty;
import org.apache.tajo.storage.StorageProperty;
import org.apache.tajo.storage.Tablespace;
import org.apache.tajo.storage.TupleRange;
import org.apache.tajo.storage.fragment.Fragment;
import org.kududb.ColumnSchema;
import org.kududb.Type;
import org.kududb.client.CreateTableOptions;
import org.kududb.client.KuduClient;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Tablespace for Kudu
 */
public class KuduTablespace extends Tablespace {

    private KuduClient client;

    public KuduTablespace(String name, URI uri, JSONObject config) {
        super(name, uri, config);
    }

    @Override
    protected void storageInit() throws IOException {
        this.client = new KuduClient.KuduClientBuilder(uri.getHost()).build();
    }

    @Override
    public long getTableVolume(TableDesc table, Optional<EvalNode> filter) throws UnsupportedException {
        return 0;
    }

    @Override
    public URI getTableUri(String databaseName, String tableName) {
        return null;
    }

    @Override
    public List<Fragment> getSplits(String inputSourceId, TableDesc tableDesc, boolean requireSort, @Nullable EvalNode filterCondition) throws IOException, TajoException {
        return null;
    }

    @Override
    public StorageProperty getProperty() {
        return null;
    }

    @Override
    public FormatProperty getFormatProperty(TableMeta meta) {
        return null;
    }

    @Override
    public void close() {
        if(client != null) {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TupleRange[] getInsertSortRanges(OverridableConf queryContext, TableDesc tableDesc, Schema inputSchema, SortSpec[] sortSpecs, TupleRange dataRange) throws IOException {
        return new TupleRange[0];
    }

    @Override
    public void verifySchemaToWrite(TableDesc tableDesc, Schema outSchema) throws TajoException {

    }

    @Override
    public void createTable(TableDesc tableDesc, boolean ifNotExists) throws TajoException, IOException {
        List<ColumnSchema> columns = new ArrayList<>();

//        for(Column tajoCol : tableDesc.getSchema().getAllColumns()) {
//            columns.add(new ColumnSchema.ColumnSchemaBuilder(tajoCol.getQualifiedName(),
//                    KuduDataTypeMapper.convert(tajoCol.getType())).build());
//        }

        columns.add(new ColumnSchema.ColumnSchemaBuilder("test", Type.INT8).key(true).build());
        org.kududb.Schema schema = new org.kududb.Schema(columns);

        CreateTableOptions tableOptions = new CreateTableOptions();


        try {
            List<String> list = new ArrayList<>();
            list.add("test");
            tableOptions.setRangePartitionColumns(list);
            client.createTable(tableDesc.getName(), schema, tableOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void purgeTable(TableDesc tableDesc) throws IOException, TajoException {

    }

    @Override
    public void prepareTable(LogicalNode node) throws IOException, TajoException {

    }

    @Override
    public Path commitTable(OverridableConf queryContext, ExecutionBlockId finalEbId, LogicalPlan plan, Schema schema, TableDesc tableDesc) throws IOException {
        return null;
    }

    @Override
    public void rollbackTable(LogicalNode node) throws IOException, TajoException {

    }

    @Override
    public URI getStagingUri(OverridableConf context, String queryId, TableMeta meta) throws IOException {
        return null;
    }
}
