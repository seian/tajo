package org.apache.tajo.storage.kudu;

import org.apache.tajo.common.TajoDataTypes;
import org.apache.tajo.type.Type;

/**
 * Created by hoon on 2016. 6. 14..
 */
public class KuduDataTypeMapper {
    public static org.kududb.Type convert(Type type) {

        String t = type.toString();

        if(t.equals("BOOL")) {
            return org.kududb.Type.BOOL;
        } else if(t.equals("FLOAT4")) {
            return org.kududb.Type.FLOAT;
        } else if(t.equals("FLOAT8")) {
            return org.kududb.Type.DOUBLE;
        } else if(t.equals("INT8")) {
            return org.kududb.Type.INT8;
        } else if(t.equals("TEXT")) {
            return org.kududb.Type.STRING;
        }

        return null;
    }

}
