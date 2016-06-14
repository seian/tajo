package org.apache.tajo.storage.kudu;

import org.apache.tajo.catalog.TableDesc;
import org.apache.tajo.exception.TajoException;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * Created by hoon on 2016. 6. 14..
 */
public class TestKuduTableSpaceTests {

    @Test
    public void createTable() throws TajoException {
        String tableSpaceUri = "kudu://52.78.45.24:7051";
        KuduTablespace kuduTableSpace = new KuduTablespace("kuducluster", URI.create(tableSpaceUri), null);
        try {
            kuduTableSpace.storageInit();
            TableDesc td = new TableDesc();
            td.setName("kuduTableTest");

            kuduTableSpace.createTable(td, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        HBaseTablespace hBaseTablespace = new HBaseTablespace("cluster1", URI.create(tableSpaceUri), null);
//        hBaseTablespace.init(new TajoConf());
//        TablespaceManager.addTableSpaceForTest(hBaseTablespace);
    }
}
