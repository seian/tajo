package org.apache.tajo.storage.kudu;

import com.google.protobuf.GeneratedMessage;
import org.apache.tajo.storage.fragment.FragmentSerde;

/**
 * Created by hoon on 2016. 6. 16..
 */
public class KuduFragmentSerde implements FragmentSerde<KuduFragment, KuduFragmentProtos> {
    @Override
    public GeneratedMessage.Builder newBuilder() {
        return null;
    }

    @Override
    public KuduFragmentProtos serialize(KuduFragment fragment) {
        return null;
    }

    @Override
    public KuduFragment deserialize(KuduFragmentProtos proto) {
        return null;
    }
}
