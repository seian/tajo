package org.apache.tajo.storage.kudu;

import com.google.protobuf.GeneratedMessage;
import org.apache.tajo.storage.fragment.FragmentSerde;
/**
 * Created by hoon on 2016. 6. 16..
 */
public class KuduFragmentSerde implements FragmentSerde<KuduFragment, KuduFragmentProtos.KuduFragmentProto> {
    @Override
    public GeneratedMessage.Builder newBuilder() {
        return KuduFragmentProtos.KuduFragmentProto.newBuilder();
    }

    @Override
    public KuduFragmentProtos.KuduFragmentProto serialize(KuduFragment fragment) {
        return KuduFragmentProtos.KuduFragmentProto.newBuilder()
                .setUri(fragment.getUri().toASCIIString())
                .setInputSource(fragment.getInputSourceId())
                .setKuduTableName(fragment.)
                .setStartKey(fragment.getStartKey())
                .setStopKey(fragment.getEndKey())
                .setLength(fragment.getLength())
                .setHosts(fragment.getHostNames().get(0))
                .build();
    }


    @Override
    public KuduFragment deserialize(KuduFragmentProtos.KuduFragmentProto proto) {
        return null;
    }

}
